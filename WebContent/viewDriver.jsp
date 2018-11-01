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
	<div class="container">

		<div
			style="height: 50%; width: 50%; margin-top: 250px; margin-left: 200px; margin-bottom: 200px; padding: 30px; border: 1px solid black">

			<%
				if (session != null) {
					if (session.getAttribute("username") != null) {
						String fname = (String) session.getAttribute("fname");
						String lname = (String) session.getAttribute("lname");
						out.print("Hello Welcome ");
					} else {
						response.sendRedirect("driverLogin.jsp");
					}
				}
			%>
			<form action="ViewDriver">
				<table>
					<tr>
						<br>
						<br>
						<td colspan="1"><input type="submit"
							value="Click to see your frofile"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>

</body>
<%@include file="footer.jsp"%>
</html>