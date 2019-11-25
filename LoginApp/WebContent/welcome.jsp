<%@page import="com.bridgeLabz.model.Registration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>




<style type="text/css">
	h1 {text-align:center;}
	
	
	div.container {
  text-align: center;
  margin: 100px auto 0;
}

a {
  display: inline-block;
  background-color: #1984c3;
  color: #fff;
  font-size: 1.5em;
  font-family: Courier, sans-serif;
  padding: 1em 5em;
  text-decoration: none;
  text-transform: uppercase;
  border-radius: 0.5em;
  border-bottom: 0.25em solid #0f5177;
  -moz-transition: 0.1s;
  -webkit-transition: 0.1s;
  transition: 0.1s;
  box-shadow: 0 2px 3px #ccc;
  position: relative;
}

a:hover {
  background-color: #1e9feb;
  border-bottom-width: 0;
  margin-top: 0.2em;
}
</style>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>
<%@page import= "java.util.ArrayList"%>
<%@page import ="java.util.List"%> 
<%@page import ="com.bridgeLabz.model.*"%>
<%@page import ="com.bridgeLabz.dao.*"%>
<h1 >Welcome</h1>
<%
	response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
	if(session.getAttribute("username")==null)
	{
		response.sendRedirect("login.jsp");
	}
   	Login login = (Login)session.getAttribute("login");
 	RetriveDataFromDB getData = new RetriveDataFromDB();
	List<Registration> list = RetriveDataFromDB.getData();%>
	
<table border="1" align="center">
	<tr>
		<th><%out.println("Name"); %></th>
		<th><%out.println("Phone Number"); %></th>
		<th><%out.println("Email ID"); %></th>
		<th><%out.println("Password"); %></th>
	</tr>
<%
	for(int i=0;i<list.size();i++)
	{%>
		<tr>
			<th><% out.println(list.get(i).getName()+"\n"); %></th>
			<th><% out.println(list.get(i).getPhoneNo()+"\n"); %></th>
			<th><% out.println(list.get(i).getEmail()+"\n"); %></th>
			<th><% out.println(list.get(i).getPassword()+"\n"); %></th>
		</tr>
		
	<%} 
	%>
	
</table>
<div class="container">
  <a href="logout">Logout</a>
</div>
</body>
</html>