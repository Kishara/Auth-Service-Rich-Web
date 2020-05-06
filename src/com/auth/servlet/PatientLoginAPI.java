package com.auth.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.auth.model.Patient;
import com.auth.model.Patient;
import com.auth.service.PatientService;
import com.auth.service.PatientService;


@WebServlet("/PatientLoginAPI")
public class PatientLoginAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	PatientService patientService = new PatientService();
    
    public PatientLoginAPI() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession(false);
	        if (session != null) {
	            session.removeAttribute("patient");
	             
	            RequestDispatcher dispatcher = request.getRequestDispatcher("LoginP.jsp");
	            dispatcher.forward(request, response);
	        }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
        String password = request.getParameter("password");
         
       
        try {
            Patient patient = patientService.checkLogin(email, password);
            String destPage = "LoginP.jsp";
             
            if (patient != null) {
                HttpSession session = request.getSession();
                session.setAttribute("patient", patient);
                destPage = "homeP.jsp";
            } else {
                String message = "Invalid email/password";
                request.setAttribute("message", message);
            }
             
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
             
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
	}

}
