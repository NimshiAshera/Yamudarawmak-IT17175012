<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="getUserForUpdate.css">
<title>Get UserId To Delete</title>
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

	<div class="form">
			<form action="adminDeleteDriver">
				<table class="table">

					<tr>
						<td>Driver user name</td>
						<td><input id="input" type="text"
							name="unameForDelete"></td>
					</tr>
				</table>
				<br /> <input type="submit" value="Delete Permanently" name="submitButton"
					id="dButton">
			</form>
		</div>


	</div>
</body>
<%@include file="footer.jsp"%>
</html>