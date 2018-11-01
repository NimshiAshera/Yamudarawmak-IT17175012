<%@page import="addDriver.Driver"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Login.DBManager" %>
<%@page import="java.sql.DriverManager" %>
<%@page import="javax.servlet.http.HttpSession" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Driver</title>
<style type="text/css">
	body{
	background-image: linear-gradient(to top, #9be15d 0%, #00e3ae 100%);
	}
	.table{
		margin-top:250px;
		margin-bottom: 100px;
		
	}
	input { font-size: 26px;text-align: center; }
</style>
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
		<%
		Driver  driver = new Driver();
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/test1", "root", "root");

		if(session != null){
			if(session.getAttribute("username")!= null){
				String name = (String) session.getAttribute("username");
				String password = (String)session.getAttribute("password");
				out.print("Hello " + name + " Welcome <br><br>");
			}
			else{
				response.sendRedirect("driverLogin.jsp");
			}
		}
		String username = (String) session.getAttribute("username");
		Statement stmt = con.createStatement();
		String sql = "select username,fname,lname,email,NIC,mobile,password from driver where username = '"+username+"'";
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()){

			driver.setuName(rs.getString(1));

			driver.setFname(rs.getString(2));

			driver.setLname(rs.getString(3));

			driver.setEmail(rs.getString(4));

			driver.setNIC(rs.getString(5));

			driver.setMobile(rs.getString(6));
			
			driver.setPass(rs.getString(7));

		}
		%>
	<table border='1' class="table" align="center" style='color:blue;background-color: powderblue;width:60%;'>
		<form action="UpdateDriver" method="post"> 
		<tr style='color:red;font-size: 30px;' align="center">
		<td>User Name</td><td><input type="text" value='<%=driver.getuName() %>' name="uname" style="width: 95%;padding: 10px;"></td><td><input type="submit" value="Update" style="width: 100%;padding: 10px;"></td>
		</tr>
		<tr style='color:red;font-size: 30px;' align="center">
		<td>First Name</td><td><input type="text" value='<%=driver.getFname() %>' name="fname" style="width: 95%;padding: 10px;"></td><td><input type="submit" value="Update" style="width: 100%;padding: 10px;"></td>
		</tr>
		<tr style='color:red;font-size: 30px;' align="center">
		<td>Last Name</td><td><input type="text" value='<%=driver.getLname()%>' name="lname" style="width: 95%;padding: 10px;"></td><td><input type="submit" value="Update" style="width: 100%;padding: 10px;"></td>
		</tr>
		<tr style='color:red;font-size: 30px;' align="center">
		<td>E-mail</td><td><input type="text" value='<%=driver.getEmail()%>' name="email" style="width: 95%;padding: 10px;"></td><td><input type="submit" value="Update" style="width: 100%;padding: 10px;"></td>
		</tr>
		<tr style='color:red;font-size: 30px;' align="center">
		<td>Mobile</td><td><input type="text" value='<%=driver.getMobile()%>' name="mobile" style="width: 95%;padding: 10px;"></td><td><input type="submit" value="Update" style="width: 100%;padding: 10px;"></td>
		</tr>
		<tr style='color:red;font-size: 30px;' align="center">
		<td>NIC</td><td><input type="text" value='<%=driver.getNIC()%>' name="NIC" style="width: 95%;padding: 10px;"></td><td><input type="submit" value="Update" style="width: 100%;padding: 10px;"></td>
		</tr>
		<tr style='color:red;font-size: 30px;'  align="center">
		<td>Password</td><td><input type="password" value='<%=driver.getPass()%>' name="password" style="width: 95%;padding: 10px;"></td><td><input type="submit" value="Update" style="width: 100%;padding: 10px;"></td>
		</tr>
		</form>
		<tr>
		<td><input type="button" id="dButton" onclick="myFunction()" value="DELETE YOUR ACOOUNT" style="width: 100%;padding: 5px; font-size: 25px;"></td>
				<script>
		function myFunction() {
		    if (confirm("YOU CANNOT DELETE YOUR ACCOUNT YOURSELF !!! Please click /OK/ to get a request form..and there you can request to admin...")) {
    			window.location.href = "deleteDriverRequest.jsp";
    		} else {
    			window.location.href = "viewDriver.jsp";
    		}
		}
		</script>
		</tr>
	</table>
</body>
<%@include file="footer.jsp"%>
</html>