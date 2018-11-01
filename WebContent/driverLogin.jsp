<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="login.css">
<title>Driver Login</title>
<%@include file="header.jsp"%>
<style type="text/css">
input { font-size: 26px;text-align: center; }
</style>
</head>
<body>
	<div class="container" style="background-image: linear-gradient(to top, #e8198b 0%, #c7eafd 100%);">
		<div class="form" style="margin-top:200px;">
			<form action="DriverLoginCheck" method="post" style="margin-top:20px;">
				<table align="center">
					<tr>
						<td>User name</td>
						<td><input type="text" name="uname" required></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="password" name="password" required></td>
					</tr>
					<tr>
						<td></td>
						<td style="font-size: 20px; color: red;">${message}</td>
					</tr>
				</table>
				<br /> <input type="submit" value="Sign in as a Driver"
					name="submitButton" id="sButton">
			</form>
		</div>
	</div>
</body>
<%@include file="footer.jsp"%>
</html>