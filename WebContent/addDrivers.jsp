<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Drivers</title>
<link rel="stylesheet" type="text/css" href="AddDriver.css">
<style type="text/css">

	input[type="text"] {
  background: transparent;
  border: 0.5px solid black;
}
input[type="password"]{
	  background: transparent;
  border: 0.5px solid black;
}
	::-webkit-input-placeholder {
    color: #909;
}
:-moz-placeholder {
  color: #909;...
  }
</style>
</head>
<%@include file="header.jsp"%>
<body>
<div class="container" style="background-image: linear-gradient(to right, #ff8177 0%, #ff867a 0%, #ff8c7f 21%, #f99185 52%, #cf556c 78%, #b12a5b 100%);">
		<h1 style="text-align: center; font-size: 50px">Add New Drivers</h1>
		<div class="dform">
			<form action="AddDrivers" method="post" id="form" enctype="multipart/form-data">
				<table class="table">
					<tr>
						<td>First Name</td>
						<td><input id="input" type="text"
							placeholder="Enter First Name" name="fname" value ="${vfname}"  required></td>
					</tr>
					<tr>
						<td>Last Name</td>
						<td><input id="input" type="text"
							placeholder="Enter Last Name" name="lname" value ="${vlname}" required></td>
					</tr>
					<tr>
						<td>E mail</td>
						<td><input id="input" type="text"
							placeholder="Enter Email Address" name="email" value ="${vemail}" required></td>
					</tr>
					<tr>
						<td></td>
						<td style="font-size: 20px; color: red;">${email}</td>
					</tr>
					<tr>
						<td>NIC</td>
						<td><input id="input" type="text" placeholder="000000000v"
							name="nic" value ="${vnic}" required></td>
					</tr>
					<tr>
						<td></td>
						<td style="font-size: 20px; color: red;">${nic}</td>
					</tr>
					<tr>
						<td>Mobile</td>
						<td><input id="input" type="text"
							placeholder="+94 00 000 0000" name="mobile" value ="${vmobile}" required></td>
					</tr>
					<tr>
						<td></td>
						<td style="font-size: 20px; color: red;">${mobile}</td>
					</tr>
					<tr>
						<td>User name</td>
						<td><input id="input" type="text"
							placeholder="Enter user name" name="uname" value ="${vuser}" required></td>
					</tr>
					<tr>
						<td></td>
						<td style="font-size: 20px; color: red;">${unameExist}</td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input id="input" type="password"
							placeholder="Enter a Password" name="password" required></td>
					</tr>
					<tr>
						<td>Confirm password</td>
						<td><input id="input" type="password"
							placeholder="Confirm the Password" name="confirmPassword"
							required></td>
					</tr>
					<tr>
						<td></td>
						<td style="font-size: 20px; color: red;">${passwordMatchingErr}</td>
					</tr>
					<tr>
						<td></td>
						<td style="font-size: 20px; color: red;">${emptyfill}</td>
					</tr>
					<tr>
						<td>Upload Image</td>
						<td><input id="input" type="file" name="image" required></td>
					</tr>
				</table>
				<br /> <input type="submit" value="ADD" name="submitButton"
					id="sButton">
			</form>
		</div>
		
		<h1 style="font-size: 40px; color: red;">${insertUnseccess}</h1>

	</div>

</body>
<%@include file="footer.jsp"%>
</html>