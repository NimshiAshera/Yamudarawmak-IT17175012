<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
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
	
	<div class="container"  align="center"  style="padding:50px; border: 1px solid black;background-image: linear-gradient(to top, #fad0c4 0%, #ffd1ff 100%);">
		<h1>If you want to delete your account, <b>Please</b> fill this form </h1><br>
		<h2><b>After getting request admin will consider about your request..You will receive a email and please come to our head Branch and Get your resigning documents..</b></h2>
		<form action="deleteDriverRequestServlet" method="post" style="padding: 10px;">
			<table>
				<tr>
					<td>Enter your current email</td>
					<td><input type="email" name="email" required></td>
				</tr>
				<tr>
					<td>Enter the Reason</td>
					<td><textarea style="font-size: 25px" name="message"
						id="message" placeholder="reason" rows="6" cols="28" required></textarea></td>
				</tr>
				<tr>
					<td><input type="submit" value="SUBMIT"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
<%@include file="footer.jsp"%>
</html>