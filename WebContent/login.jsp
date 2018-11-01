<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="login.css">
<title>Login</title>
</head>
<%@include file="header.jsp"%>
<body>
	<div class="container">
		<h1 style="text-align: center; font-size: 50px">SIGN IN</h1>
		<div class="form">
			<form action="loginCheck" method="post">
				<table class="table">
					<tr>
						<td>User name</td>
						<td><input id="input" type="text" placeholder="User name"
							name="uname" required></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input id="input" type="password" placeholder="Password"
							name="password" required></td>
					</tr>
					<tr>
						<td></td>
						<td style="font-size: 20px; color: red;">${message1}</td>
					</tr>
				</table>
				<br /> <input type="submit" value="Sign in" name="submitButton"
					id="sButton">
			</form>
		</div>

		<div class="formDriverLogin">
		<a href="driverLogin.jsp" class="driverLogin">Click here to LOGIN AS A DRIVER</a>
		</div>

	</div>
</body>
<%@include file="footer.jsp"%>