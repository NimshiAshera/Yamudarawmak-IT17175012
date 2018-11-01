<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="vehicle.css">
<title>Insert vehicle</title>
</head>
	<%@include file="header.jsp"%>
<body>
	<div class="container" style="background:#d6d6c2">
		<h1 style="text-align: center; font-size: 50px">Register your vehicle...</h1>
		<div class="form">
			<form action="addVehicle" method="post" enctype="multipart/form-data">
				<table class="table">
												                	              										
					<tr>
                        <td>Select Vehicle Category :</td>            
                        <td><input style="height: 20px; width: 20px;" type="radio" name="vehicle" value="Car" > Car
  						 	<input style="height: 20px; width: 20px;" type="radio" name="vehicle" value="Jeep" >Jeep
  						 	<input style="height: 20px; width: 20px;" type="radio" name="vehicle" value="MiniVan" >Mini-Van
  						 	<input style="height: 20px; width: 20px;" type="radio" name="vehicle" value="Van" >Van
  						 	<input style="height: 20px; width: 20px;" type="radio" name="vehicle" value="Bus" >Bus</td>
                        
                    </tr>
                    
                    <tr>  
                    	 <td>Vehicle Type :</td>  
                        <td><input id="input" type="text" name="type" required></td>
					</tr>
                    
                    <tr>
                        <td>No Plate.:</td>
                        <td><input id="input" type="text" placeholder="0000XX" name="model" required></td>

                    </tr>
                    <tr>
						<td></td>
						<td style="font-size: 20px; color: red;">${modelExist}</td>
					</tr>
                    
                    <tr>
						<td>Upload Vehicle Image</td>
						<td><input id="input" type="file" name="vImage" required></td>
					</tr>
					
					<tr>
                        <td>Hire per day:</td>
                        <td><input id="input" type="number" min="0" name="hire" required></td>

                    </tr>
                    
                    <tr>
                    	<td>AC Available:</td>
                    	<td><input style="height: 20px; width: 20px;" type="radio" name="ac" value="ac" > A/C
  						 	<input style="height: 20px; width: 20px;" type="radio" name="ac" value="non_ac" >Non A/C</td>
                	</tr>
                	
                	<tr>
                    	<td>Mini-Bar Available:</td>
                    	<td><input style="height: 20px; width: 20px;" type="radio" name="bar" value="bar" > Available
  						 	<input style="height: 20px; width: 20px;" type="radio" name="bar" value="non_bar" >Non Available</td>
                	</tr>
                
                	<tr>
                    	<td>Hire for :</td>
                    	<td><input style="height: 20px; width: 20px;" type="checkbox" name="reason" value="Airport Transfer" > Airport Transfer<br>
  						 	<input style="height: 20px; width: 20px;" type="checkbox" name="reason" value="Wedding Transfer" > Wedding Transfer<br>
  						 	<input style="height: 20px; width: 20px;" type="checkbox" name="reason" value="Trip Hires" > Trip Hires<br>
  						 	<input style="height: 20px; width: 20px;" type="checkbox" name="reason" value="Other" > Other
  						 	<textarea style="height: 50px; width: 100px;" name="reason" id="reason">Type here</textarea><br><br></td>
                	</tr>
                
                	<tr>
                    	<td>Region :</td>
                    	<td><input style="height: 20px; width: 20px;" type="checkbox" name="place" value="Colombo Areas only" > Colombo Areas only<br>
  						 	<input style="height: 20px; width: 20px;" type="checkbox" name="place" value="Western Province" > Western Province<br>
  						 	<input style="height: 20px; width: 20px;" type="checkbox" name="place" value="Central Province" > Central Province<br>
  						 	<input style="height: 20px; width: 20px;" type="checkbox" name="place" value="All-Island" > All-Island<br>
  						 	<input style="height: 20px; width: 20px;" type="checkbox" name="place" value="Other" > Other
  						 	<textarea style="height: 50px; width: 100px;" name="place" id="place">Type here</textarea><br><br></td>
                	</tr>

				</table>
				<br /> <input type="submit" value="Register" name="submitButton"
					id="sButton">
			</form>
		</div>

		<h1 style="font-size: 40px; color: red;">${insertUnsuccess}</h1>
	</div>

</body>
	<%@include file="footer.jsp"%>
</html>