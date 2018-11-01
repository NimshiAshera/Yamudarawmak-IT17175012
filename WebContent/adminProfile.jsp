<%@page import="Admin.Admin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="adminProfile.css">
<title>Admin</title>
<%
if(session.getAttribute("username")==null) {
%>
<jsp:include page="header.jsp"></jsp:include>
<%
} else {
%>
<jsp:include page="afterLoginHeader.jsp"></jsp:include>
<%
}%>
</head>
<body>
	<div class="container">
	
	<div id="adminNav">
		<table>
			<tr>
				<td><a href="adminViewUsers" />Get Users</td>
				<td><a href="adminViewDriver" />Get Drivers</td>
				<td><a href="adminViewAdmin" />Get Admins</td>
				<td style="background-color:Orange;"><a href="adminRegisterForm.jsp" />Add Admins</td>
			</tr>
		</table>
	</div>
	
	<div class="containerForAdminDetails">
		<br> <br>
		<div  class="adminDetails" align="left">
			<table border="1" cellpadding="12">
			<%
					Admin admin= (Admin) request.getAttribute("admin");
					
			%>
			
				<caption>
					<h2><%=admin.getUname()%> Details</h2>
				</caption>
				<tr>
					<th>Admin ID</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Gender</th>
					<th>Country</th>
					<th>City</th>
					<th>telNo</th>
					<th>User Name</th>
					<th>Password</th>
					<th>Image</th>
					<th>Options</th>
				</tr>
				
				<tr>
					<td><%=admin.getAdminId()%></td>
					<td><%=admin.getFname()%></td>
					<td><%=admin.getLname()%></td>
					<td><%=admin.getEmail()%></td>
					<td><%=admin.getGender()%></td>
					<td><%=admin.getCountry()%></td>
					<td><%=admin.getCity()%></td>
					<td><%=admin.getTelNo()%></td>
					<td><%=admin.getUname()%></td>
					<td><%=admin.getPassword()%></td>
					<td><img src="adminImages/<%=admin.getImageName() %>"  width="150"
						height="150">
						<form method="POST" action="getAdminImageServlet">
							<input type="hidden" name="uid>"
								value="<%=session.getAttribute("adminId")%>" /> <input type="submit"
								value="Change" class="select-button" />
						</form>
						</td>
					<td>
						<form method="POST" action="getAdminServlet">
							<input type="hidden" name="uid>"
								value="<%=session.getAttribute("adminId")%>" /> <input type="submit"
								value="Edit Admin" class="select-button" />
						</form>
					</td>
				</tr>

			</table>
		</div>

	</div>

	</div>
</body>
<%@include file="footer.jsp"%>
</html>