package com.bridgeLabz.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bridgeLabz.dao.SendDataToDB;
import com.bridgeLabz.model.Registration;

/**
 * @author Sourabh Magdum
 * @Purpose - Servlet for Register new user Functionality
 * Date - 11/11/2019
 */
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		Registration register = new Registration();
		register.setName(request.getParameter("name"));
		register.setPhoneNo(request.getParameter("phoneNo"));
		register.setEmail(request.getParameter("email"));
		register.setPassword(request.getParameter("password"));
		register.setCheckPassword(request.getParameter("checkPassword"));
		PrintWriter out = response.getWriter();

		try {
			if(SendDataToDB.inseretIntoDB(register)!=true) 
			{
				response.setContentType("text/html");
				 out.println("<script type=\"text/javascript\">");
				 out.println("alert('User already exits');");
				 out.println("location='register.jsp';");
				 out.println("</script>");
			}
			else
			{
				 response.setContentType("text/html");
				 out.println("<script type=\"text/javascript\">");
				 out.println("alert('User registered Successfully');");
				 out.println("location='login.jsp';");
				 out.println("</script>");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
