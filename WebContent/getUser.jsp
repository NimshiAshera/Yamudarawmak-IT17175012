<%@page import="Register.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="getUser.css">
<title>User</title>
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
	<div class="containerForUserDetails" >
		<br> 
		<div id="userNav">
		<table>
			<tr>
				<td><a href="AddVehicle.jsp" />Add Your Own Vehicle & Start Earnning</td>
			</tr>
		</table>
	</div>
		<br><br>
		
		<div id="userNavListVehicle">
		<table>
			<tr>
				<td><a href="vehicleDetails" />Get Your Vehicles Info</td>
			</tr>
		</table>
	</div>
	
	<br><br>
		
		<div  class="userDetails" align="left">
			<table border="1" cellpadding="12">
			<%
					User user = (User) request.getAttribute("user");
					
				%>
				<caption>
					<h2>Details of <%=user.getFname()%> <%=user.getLname()%></h2>
				</caption>
				<tr>
					<th>User ID</th>
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
					<td><%=user.getUid()%></td>
					<td><%=user.getFname()%></td>
					<td><%=user.getLname()%></td>
					<td><%=user.getEmail()%></td>
					<td><%=user.getGender()%></td>
					<td><%=user.getCountry()%></td>
					<td><%=user.getCity()%></td>
					<td><%=user.getTelNo()%></td>
					<td><%=user.getUname()%></td>
					<td><%=user.getPassword()%></td>
					<td><img src="usersImages/<%=user.getImageName() %>"  width="150"
						height="150">
						<form method="POST" action="getUserImageServlet">
							<input type="hidden" name="uid>"
								value="<%=session.getAttribute("uid")%>" /> <input type="submit"
								value="Change" class="select-button" />
						</form>
						</td>
					<td>
						<form method="POST" action="getUserServlet">
							<input type="hidden" name="uid>"
								value="<%=session.getAttribute("uid")%>" /> <input type="submit"
								value="Edit User" class="select-button" />
						</form>
					</td>
				</tr>

			</table>
		</div>

	</div>
</body>
<%@include file="footer.jsp"%>
</html>