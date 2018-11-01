<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="member.css">
<title>${message}</title>

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

<body>
	<div class="container">

		<div class="box">
			<h1>${message}</h1>
			This is the member page

			
		</div>
	</div>
</body>
<%@include file="footer.jsp"%>
</html>