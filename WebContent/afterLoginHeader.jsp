<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width initial-scale=1">
<link rel="stylesheet" type="text/css" href="header.css">
<title>header style</title>
</head>
<body style="background-color: whitesmoke">

	<%
		String imageName = (String) session.getAttribute("imageName");
		if(imageName==null)
			imageName="defaultUser.png";
		String uname=(String)session.getAttribute("username");
	%>


	<div class="headerBar">
		<header> <img src="homeImages/LOGO.png"></header>

		<ul>
			<table>
				<tr>
					<td><h1><%=uname%></h1></td>
					<%
					if ((session.getAttribute("loggedAs") == "driver")||(session.getAttribute("loggedAs") == "user")) {
					%>
					<td><img src="usersImages/<%=imageName%>" width="100"
						height="100"></td>
					<%
					}
					if (session.getAttribute("loggedAs") == "admin") {
						%>
					<td><img src="adminImages/<%=imageName%>" width="100"
						height="100"></td>
					<%
							}
					%>
				</tr>
			</table>
			<li><a href="logoutCheck">Logout</a></li>
		</ul>
	</div>

	<div id="nav1">
		<table>
			<tr>
				<td><a href="home.jsp" />Home</td>
				<td><a href="#" />Services</td>
				<td><a href="#" />Shop</td>
				<td><a href="aboutUs.jsp" />About Us</td>
				<td><a href="#" />Gallery</td>
				<td><a href="contact.jsp" />Contact Us</td>
				<%
					if (session.getAttribute("loggedAs") == "driver") {
				%>
				<td><a href="ViewDriver" />Profile</td>
				<%
					}
					if (session.getAttribute("loggedAs") == "user") {
				%>
				<td><a href="userDetails" />Profile</td>
				<%
					}
					if (session.getAttribute("loggedAs") == "admin") {
				%>
				<td><a href="adminDetails" />Admin Profile</td>
				<%
				}%>
			</tr>
		</table>
	</div>

</body>
</html>