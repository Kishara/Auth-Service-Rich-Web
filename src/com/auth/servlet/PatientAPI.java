package com.auth.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth.service.PatientService;

@WebServlet("/PatientAPI")
public class PatientAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PatientService patientService = new PatientService();
	
	public PatientAPI() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String output = patientService.insertPatient(
				request.getParameter("type"),
				request.getParameter("email"),
				request.getParameter("password"),
				request.getParameter("contactNo"), 
				request.getParameter("firstName"),
				request.getParameter("lastName"),
				request.getParameter("DOB"),
				request.getParameter("age"),
				request.getParameter("sex"),
				request.getParameter("NIC"),
				request.getParameter("address"));

		response.getWriter().write(output);

	}

	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request){
	 Map<String, String> map = new HashMap<String, String>();
	try
	 {
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	 String queryString = scanner.hasNext() ?
	 scanner.useDelimiter("\\A").next() : "";
	 scanner.close();
	 String[] params = queryString.split("&");
	 for (String param : params)
	 { 
	
	String[] p = param.split("=");
	 map.put(p[0], p[1]);
	 }
	 }
	catch (Exception e)
	 {
	 }
	return map;
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		Map<String, String> paras = getParasMap(request);
		
		String output = patientService.updatePatient(paras.get("hidPatientIDSave").toString(),
		 paras.get("type").toString().replace("+", " "),
		 paras.get("email").toString().replace("%40", "@"),
		 paras.get("password").toString(),
		 paras.get("contactNo").toString(),
		 paras.get("firstName").toString().replace("+", " "),
		 paras.get("lastName").toString().replace("+", " "),
		 paras.get("DOB").toString(),
		 paras.get("age").toString(),
		 paras.get("sex").toString().replace("+", " "),
		 paras.get("NIC").toString(),
		 paras.get("address").toString().replace("+", " "));
		response.getWriter().write(output); 
	}


	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map paras = getParasMap(request);
		 String output = patientService.deletePatient(paras.get("ID").toString()); 
						response.getWriter().write(output); 
	}

}
