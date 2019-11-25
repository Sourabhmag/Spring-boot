package com.bridgeLabz.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Sourabh Magdum
 * @purpose - Used to filter data inserted by user 
 * 				while using functionality of registration
 * Data - 11/11/2019
 */
@WebFilter("/register")
public class RegistrationFilter implements javax.servlet.Filter {

	/**
	 * Default constructor.
	 */
	public RegistrationFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see RegistrationFilter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see RegistrationFilter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		String name = httpRequest.getParameter("name");
		String phoneNo = httpRequest.getParameter("phoneNo");
		String email = httpRequest.getParameter("email");
		String password = httpRequest.getParameter("password");
		String checkPassword = httpRequest.getParameter("checkPassword");
		PrintWriter out = httpResponse.getWriter();
		
		if(validateName(name)!=true	)
		{
			 response.setContentType("text/html");
			 out.println("<script type=\"text/javascript\">");
			 out.println("alert('Entered name is not in format');");
			 out.println("location='register.jsp';");
			 out.println("</script>");
		}
		else if(phoneNo.length()!=10)
		{
			 response.setContentType("text/html");
			 out.println("<script type=\"text/javascript\">");
			 out.println("alert('Phone number must contain 10 numbers');");
			 out.println("location='register.jsp';");
			 out.println("</script>");
		}
		else if(validateEmail(email)!=true)
		{
			 response.setContentType("text/html");
			 out.println("<script type=\"text/javascript\">");
			 out.println("alert('Entered email id is not in format');");
			 out.println("location='register.jsp';");
			 out.println("</script>");
		}
		else if(password.length()==0)
		{
			response.setContentType("text/html");
			 out.println("<script type=\"text/javascript\">");
			 out.println("alert('Password Field can not be empty');");
			 out.println("location='register.jsp';");
			 out.println("</script>");
		}
		else if(password.equals(checkPassword)!=true)
		{
			response.setContentType("text/html");
			 out.println("<script type=\"text/javascript\">");
			 out.println("alert('Password is not matching');");
			 out.println("location='register.jsp';");
			 out.println("</script>");
		}
		else 	
			chain.doFilter(httpRequest, httpResponse);
	}

	/**
	 * @see RegistrationFilter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	private boolean validateName(String name)
	{
		String regexName = "\\p{Upper}(\\p{Lower}+\\s?)";
		String patternName = "(" + regexName + "){2,3}";
		return name.matches(patternName);
	}
	

		public boolean validateEmail(String email) {
			final Pattern VALID_EMAIL_ADDRESS_REGEX = 
				    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
		        return matcher.find();
		}

}
