<%@page import="Register.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="getUserForUpdate.css">
<title>Get Admin</title>
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
	<div class="container">

		<h1 style="text-align: center; font-size: 50px">Update Admin Image</h1>
		<br> <br>
		<%
		User user=(User)request.getAttribute("user");
	%>
	
		<div class="form">
			<form action="updateAdminImageServlet" method="post" enctype="multipart/form-data">
				<table class="table">
					
					<tr>
						<td>Admin ID</td>
						<td><input id="input" type="text"
							value="<%=user.getUid() %>" name="uid" disabled="disabled"></td>
					</tr>	
					
 					<tr> 
 						<td>Upload Image</td> 
 						<td><input id="input" type="file" name="image" required></td> 
 					</tr> 


				</table>
				<br /> <input type="submit" value="Update Image" name="submitButton"
					id="sButton">
			</form>
		</div>

		<h1 style="font-size: 40px; color: red;">${insertUnsuccess}</h1>

	</div>
</body>
<%@include file="footer.jsp"%>
</html>