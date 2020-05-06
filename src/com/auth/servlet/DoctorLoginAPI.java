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

import com.auth.model.Doctor;
import com.auth.service.DoctorService;


@WebServlet("/DoctorLoginAPI")
public class DoctorLoginAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	DoctorService doctorService = new DoctorService();
    
    public DoctorLoginAPI() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession(false);
	        if (session != null) {
	            session.removeAttribute("doctor");
	             
	            RequestDispatcher dispatcher = request.getRequestDispatcher("LoginD.jsp");
	            dispatcher.forward(request, response);
	        }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
        String password = request.getParameter("password");
         
       
        try {
            Doctor doctor = doctorService.checkLogin(email, password);
            String destPage = "LoginD.jsp";
             
            if (doctor != null) {
                HttpSession session = request.getSession();
                session.setAttribute("doctor", doctor);
                destPage = "homeD.jsp";
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
