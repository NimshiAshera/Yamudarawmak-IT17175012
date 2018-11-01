package addDriver;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class adminViewDriver
 */
@WebServlet("/adminViewDriver")
public class adminViewDriver extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminViewDriver() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String uName = request.getParameter("uname");
		RequestDispatcher rd = request.getRequestDispatcher("/afterLoginHeader.jsp");
		rd.include(request, response);
		out.print("<body>");
		out.print("<div class=\"container\" align='center' style='background-image: linear-gradient(to right top, #051937, #004d7a, #008793, #00bf72, #a8eb12);'>");
		out.print("<h1>Display the records of Drivers for Admin</h1>");
		out.print("<table border='1'style='color:blue;background-color: powderblue;width:60%;'><tr style='color:red;font-size: 30px;'><th style='padding:15px;'>User Name</th><th>First Name</th><th>Last Name</th><th>Email</th><th>Mobile</th><th>NIC</th></tr>");
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test1", "root", "root");
		
			Statement stmt = con.createStatement();
			String sql = "select username,fname,lname,email,mobile,NIC from driver";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(((ResultSet) rs).next()){
				out.print("<tr style='font-size: 30px;text-align: center;'><td style=' padding:7px;'>");
				out.println(((ResultSet) rs).getString(1));
				out.print("</td>");
				out.print("<td>");
				out.print(((ResultSet) rs).getString(2));
				out.print("</td>");
				out.print("<td>");
				out.print(((ResultSet) rs).getString(3));
				out.print("</td>");
				out.print("<td>");
				out.print(((ResultSet) rs).getString(4));
				out.print("</td>");
				out.print("<td>");
				out.print(((ResultSet) rs).getString(5));
				out.print("</td>");
				out.print("<td>");
				out.print(((ResultSet) rs).getString(6));
				out.print("</td>");
				out.print("</tr>");
			}
				out.print("<th><form action style='margin-top:5px;padding:5px;'><input type='button' onclick=\"window.location.href='adminDeleteDriver.jsp'\" value='delete driver' style='padding:5px;background:#4CAF50;"
				+ "transition-duration: 0.4s;display: inline-block;cursor: pointer;-webkit-transition-duration: 0.4s;color:white;font-size:20px;font-weight:bold;border: none;'></form></th>");
				out.print("<th><form action style='margin-top:5px;padding:5px;'><input type='submit' action='' method='get' value='disable driver' style='padding:5px;background:#4CAF50;"
				+ "transition-duration: 0.4s;display: inline-block;cursor: pointer;-webkit-transition-duration: 0.4s;color:white;font-size:20px;font-weight:bold;border: none;'></form></th>");
			
		
		}
		catch(Exception p){
			System.out.println(p);
		}
		
		out.print("</table>");
		out.print("<form action='driverRequest.jsp' style='padding: 10px;'><input type='submit' value='Click to see driver request' style='width: 20%;padding: 10px;font-size:30px;'><form>");
		out.print("</div>");
		out.print("</body>");
		RequestDispatcher rd2 = request.getRequestDispatcher("/footer.jsp");
		rd2.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
