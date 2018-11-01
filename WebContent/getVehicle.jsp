<%@page import="Vehicle.Vehicle"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="getVehicle.css">
<title>Vehicle</title>
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
	<div class="VehicleDetailsContainer" style="background:#d6d6c2">
		<br> <br>
		<div  class="vehicleDetails" align="left">
			<table border="1" cellpadding="12">
			<%
					Vehicle vehicle = (Vehicle) request.getAttribute("vehicle");
					
				%>
				<caption>
					<h2>Details of User ID <%=vehicle.getUid()%> </h2>
				</caption>
				<tr>
					<th>User ID</th>
					<th>Vehicle</th>
					<th>Type</th>
					<th>Model</th>
					<th>Image</th>
					<th>Hire</th>
					<th>AC Availability</th>
					<th>Mini-Bar Availability</th>
					<th>Reason</th>
					<th>Place</th>
					<th>Options</th>
				</tr>
				
				<tr>
					<td><%=vehicle.getUid()%></td>
					<td><%=vehicle.getVehicle()%></td>
					<td><%=vehicle.getType()%></td>
					<td><%=vehicle.getModel()%></td>
					<td><img src="vehicleImages/<%=vehicle.getVImage() %>"  width="150"
						height="150">
						<form method="POST" action="getVehicleImage">
							<input type="hidden" name="uid>"
								value="<%=session.getAttribute("uid")%>" /> <input type="submit"
								value="Change" class="select-button" />
						</form>
						</td>
					<td><%=vehicle.getHire()%></td>
					<td><%=vehicle.getAC()%></td>
					<td><%=vehicle.getBar()%></td>
					<td><%=vehicle.getReason()%></td>
					<td><%=vehicle.getPlace()%></td>
					
					<td>
						<form method="POST" action="getVehicle">
							<input type="hidden" name="uid>"
								value="<%=session.getAttribute("uid")%>" /> <input type="submit"
								value="Edit Vehicle" class="select-button" />
						</form>
					</td>
				</tr>

			</table>
		</div>

	</div>
</body>
<%@include file="footer.jsp"%>
</html>