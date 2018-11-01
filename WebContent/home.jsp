<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width initial-scale=1">
<link rel="stylesheet" type="text/css" href="home.css">
<script src="javascript.js" type="text/javascript"></script>
<title>Home</title>
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

<body style="background-color: whitesmoke">
	<div class="container">
		<div class="nav">
			<div class="transparent">
				<div class="color">
					<div class="text">

						<p>Welcome to Yamuda Rawmak !</p>

						We are here to go with you. Do you want to go there in a unique
						way? Try Us now!
					</div>
				</div>
			</div>

			<div class="homeImg">
				<img src="homeImages/homeImage2.jpg">
			</div>

		</div>

		<hr>

		<h1>YOUR CHOICE</h1>

		<div class="packagesBlock">

			<div id="packagesBase1">
				<img src="homeImages/homeAirport.jpg" />
				<h2>AIR PORT</h2>
			</div>

			<div id="packagesBase2">
				<img src="homeImages/homeTour.jpg" />
				<h2>TOURS</h2>
			</div>

			<div id="packagesBase3">
				<img src="homeImages/homeWedding.jpg" />
				<h2>WEDDINGS</h2>
			</div>
		</div>

		<hr>

		<div class="quote-box">
			<blockquote>
				<p id="quote">There are two great days in a person's life-the
					day we are born and the day we discover why</p>
				<hr>
				<h2>William Barclay</h2>
				<p>Scottish author, radio and television presenter, Church of
					Scotland minister and Professor of Divinity and Biblical Criticism
					at the University of Glasgow.</p>
			</blockquote>
		</div>

	</div>
</body>
<%@include file="footer.jsp"%>
</html>