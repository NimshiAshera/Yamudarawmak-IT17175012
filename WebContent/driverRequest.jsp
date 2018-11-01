<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Login.DBManager" %>
<%@page import="java.sql.DriverManager" %>
<%@page import="javax.servlet.http.HttpSession" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<%
	if (session.getAttribute("username") == null) {
%>
<jsp:include page="header.jsp"></jsp:include>
<%
	} else {
%>
<jsp:include page="afterLoginHeader.jsp"></jsp:include>
<%
	}
%>
<body>
<div  align='center' style='background-image: linear-gradient(to top, #d5dee7 0%, #ffafbd 0%, #c9ffbf 100%);margin-top: 250px;padding: 50px;padding-bottom: 250px;'>
<h1>Display the request of Drivers</h1>

	<%
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/test1", "root", "root");
	
	Statement stmt = con.createStatement();
	String sql = "select username,email,reason from driverrequest";
	ResultSet rs = stmt.executeQuery(sql);
	String username = null;
	String email = null;
	String reason = null;
	while(rs.next()){

		username = rs.getString(1);
		email = rs.getString(2);
		reason = rs.getString(3);
		System.out.print(username);
		System.out.print(email);
		System.out.print(reason);
	}
	out.print("<table border='1' style='color:blue;background-color: powderblue;width:60%;'><tr style='color:red;font-size: 30px;'><th style='padding:15px;'>User Name</th><th>email</th><th>reason</th></tr>");
	
			out.print("<tr style='font-size: 30px;text-align: center;'><td style=' padding:7px;'>");
			out.print(username);
			out.print("</td>");
			out.print("<td>");
			out.print(email);
			out.print("</td>");
			out.print("<td>");
			out.print(reason);
			out.print("</td>");
		
	out.print("</table>");

	%>
</div>
</body>
<%@include file="footer.jsp"%>
</html>