<%@page import="Vehicle.Vehicle"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="getVehicleUpdate.css">
<title>Get Vehicle</title>
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
	<div class="container" style="background:#d6d6c2">

		<h1 style="text-align: center; font-size: 50px">Update Vehicle
			Information</h1>
		<br> <br>
		<%
		Vehicle vehicle=(Vehicle)request.getAttribute("vehicle");
	%>

		<div class="form">
			<form action="updateVehicle" method="post">
				<table class="table">

					<tr>
						<td>User ID</td>
						<td><input id="input" type="text" value='<%=vehicle.getUid() %>'
							name="uid" disabled="disabled"></td>
					</tr>
					<tr>
						<td>Vehicle Category</td>
						<td><input style="height: 20px; width: 20px;" type="radio" name="vehicle" value="Car" > Car
  						 	<input style="height: 20px; width: 20px;" type="radio" name="vehicle" value="Jeep" >Jeep
  						 	<input style="height: 20px; width: 20px;" type="radio" name="vehicle" value="MiniVan" >Mini-Van
  						 	<input style="height: 20px; width: 20px;" type="radio" name="vehicle" value="Van" >Van
  						 	<input style="height: 20px; width: 20px;" type="radio" name="vehicle" value="Bus" >Bus</td>
					</tr>
					<tr>
						<td>Type</td>
						<td><input id="input" type="text"
							value='<%=vehicle.getType() %>' name="type"></td>
					</tr>
					<tr>
						<td>No. Plate</td>
						<td><input id="input" type="text"
							value='<%=vehicle.getModel() %>' name="model"></td>
					</tr>
					<tr>
						<td></td>
						<td style="font-size: 20px; color: red;">${modelExist}</td>
					</tr>
					<tr>
						<td>Hire</td>
						<td><input id="input" type="text"
							value='<%=vehicle.getHire() %>' name="hire"></td>
					</tr>
					<tr>
						<td>AC Availability</td>
						<td><input style="height: 20px; width: 20px;" type="radio" name="ac" value="ac" > A/C
  						 	<input style="height: 20px; width: 20px;" type="radio" name="ac" value="non_ac" >Non A/C</td>
					</tr>					
					<tr>
						<td>Bar Availability</td>
						<td><input style="height: 20px; width: 20px;" type="radio" name="bar" value="bar" > Available
  						 	<input style="height: 20px; width: 20px;" type="radio" name="bar" value="non_bar" >Non Available</td>
					</tr>
					<tr>
						<td>Hire For</td>
						<td><input style="height: 20px; width: 20px;" type="checkbox" name="reason" value="f1" > Airport Transfer<br>
  						 	<input style="height: 20px; width: 20px;" type="checkbox" name="reason" value="f2" > Wedding Transfer<br>
  						 	<input style="height: 20px; width: 20px;" type="checkbox" name="reason" value="f3" > Trip Hires<br>
  						 	<input style="height: 20px; width: 20px;" type="checkbox" name="reason" value="f4" > Other
  						 	<textarea style="height: 50px; width: 100px;" name="reason" id="reason">Type here</textarea><br><br></td>
					</tr>
					<tr>
						<td>Region</td>
						<td><input style="height: 20px; width: 20px;" type="checkbox" name="place" value="r1" > Colombo Areas only.<br>
  						 	<input style="height: 20px; width: 20px;" type="checkbox" name="place" value="r2" > Western Province<br>
  						 	<input style="height: 20px; width: 20px;" type="checkbox" name="place" value="r3" > Southern Province<br>
  						 	<input style="height: 20px; width: 20px;" type="checkbox" name="place" value="r4" > Central Province<br>
  						 	<input style="height: 20px; width: 20px;" type="checkbox" name="place" value="r5" > All-Island<br>
  						 	<input style="height: 20px; width: 20px;" type="checkbox" name="place" value="r6" > Other
  						 	<textarea style="height: 50px; width: 100px;" name="place" id="place">Type here</textarea><br><br></td>
					</tr>
					
				</table>
				<br /> <input type="submit" value="Update" name="submitButton"
					id="sButton">
			</form>
		</div>

		<h1 style="font-size: 40px; color: red;">${insertUnsuccess}</h1>

		<div class="form">
		<div class="table">
		<button id="dButton" onclick="myVFunction()">Delete Vehicle Details</button>
		<script>
		function myVFunction() {
		    if (confirm("THINK TWICE ! If you pressed \"ok\" your vehicle details will be deleted permanently..")) {
    			window.location.href = "deleteVehicle";
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