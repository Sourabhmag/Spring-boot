package com.bridgeLabz.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bridgeLabz.dao.SendDataToDB;
/**
 * @author Sourabh Magdum
 * @Purpose - Servlet for Password check Functionality
 * Date - 11/11/2019
 */
@WebServlet("/password")
public class PasswordServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response)
	{
		String password = request.getParameter("password");
		
		try {
			boolean check = SendDataToDB.resetPassword(ForgotServlet.login.getUsername(),password);
			PrintWriter out = response.getWriter();
			if(check)
			{
				
				 response.setContentType("text/html");
				 out.println("<script type=\"text/javascript\">");
				 out.println("alert('Password updated Successfully...');");
				 out.println("location='login.jsp';");
				 out.println("</script>");
				 
				//response.sendRedirect("login.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
