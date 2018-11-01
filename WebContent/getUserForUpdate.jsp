<%@page import="Register.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="getUserForUpdate.css">
<title>Get User</title>
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

		<h1 style="text-align: center; font-size: 50px">Update User
			Information</h1>
		<br> <br>
		<%
		User user=(User)request.getAttribute("user");
	%>

		<div class="form">
			<form action="updateUserServlet" method="post">
				<table class="table">

					<tr>
						<td>User ID</td>
						<td><input id="input" type="text" value='<%=user.getUid() %>'
							name="uid" disabled="disabled"></td>
					</tr>
					<tr>
						<td>First Name</td>
						<td><input id="input" type="text"
							value='<%=user.getFname() %>' name="fname"></td>
					</tr>
					<tr>
						<td>Last Name</td>
						<td><input id="input" type="text"
							value='<%=user.getLname() %>' name="lname"></td>
					</tr>
					<tr>
						<td>E mail</td>
						<td><input id="input" type="text"
							value='<%=user.getEmail() %>' name="email"></td>
					</tr>
					<tr>
						<td></td>
						<td style="font-size: 20px; color: red;">${emailErr}</td>
					</tr>
					<tr>
						<td>Gender</td>
						<td><input style="height: 20px; width: 20px;" type="radio"
							name="gender" value="female">female <input
							style="height: 20px; width: 20px;" type="radio" name="gender"
							value="male">male</td>
					</tr>
					<tr>
						<td>Country</td>
						<td><input id="input" type="text"
							value='<%=user.getCountry() %>' name="country"></td>
					</tr>
					<tr>
						<td>City</td>
						<td><input id="input" type="text"
							value='<%=user.getCity() %>' name="city"></td>
					</tr>
					<tr>
						<td>Mobile</td>
						<td><input id="input" type="text"
							value='<%=user.getTelNo() %>' name="telNo"></td>
					</tr>
					<tr>
						<td>User name</td>
						<td><input id="input" type="text"
							value='<%=user.getUname() %>' name="uname"></td>
					</tr>
					<tr>
						<td></td>
						<td style="font-size: 25px; color: red;">${unameExist}</td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input id="input" type="text"
							value='<%=user.getPassword() %>' name="password"></td>
					</tr>
					<tr>
						<td>Confirm password</td>
						<td><input id="input" type="password"
							value='<%=user.getPassword() %>' name="confirmPassword" required></td>
					</tr>
					<tr>
						<td></td>
						<td style="font-size: 25px; color: red;">${passwordMatchingErr}</td>
					</tr>


				</table>
				<br /> <input type="submit" value="Update" name="submitButton"
					id="sButton">
			</form>
		</div>

		<h1 style="font-size: 40px; color: red;">${insertUnsuccess}</h1>

		<div class="form">
		<div class="table">
		<button id="dButton" onclick="myFunction()">Delete User</button>
		<script>
		function myFunction() {
		    if (confirm("THINK TWICE ! If you pressed \"ok\" your user account will be deleted permanently..")) {
    			window.location.href = "deleteUserServlet";
    		} else {
    			window.location.href = "home.jsp";
    		}
		}
		</script>
		</div>
		</div>
		
		<br>
		<br>
		


	</div>
</body>
<%@include file="footer.jsp"%>
</html>