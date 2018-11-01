package Admin;

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
import javax.servlet.http.HttpSession;

import Login.DBManager;

/**
 * Servlet implementation class adminViewUsers
 */
@WebServlet("/adminViewUsers")
public class adminViewUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminViewUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		out.print("<title>View Users");
		out.print("</title>");
		RequestDispatcher rd = request.getRequestDispatcher("/afterLoginHeader.jsp");
		rd.include(request, response);
		out.print("<body>");
		out.print("<div class=\"container\" align='center' style='background-image: linear-gradient(to top, #d5dee7 0%, #ffafbd 0%, #c9ffbf 100%);margin-bottom: -200px;'>");
		out.print("<h1>Display the records of Users for Admin</h1>");
		out.print("<table border='1'><tr>"+
					"<th>User ID</th>"+
					"<th>First Name</th>"+
					"<th>Last Name</th>"+
					"<th>Email</th>"+
					"<th>Gender</th>"+
					"<th>Country</th>"+
					"<th>City</th>"+
					"<th>telNo</th>"+
					"<th>User Name</th>"+
					"<th>Password</th>"+
					"<th>Image name</th>"+
					"<th>Image Path</th>"+
					"<th>Image</th>"+
					"</tr>");
		try{
			DBManager db = new DBManager();
			Connection conn = db.getConnection();
		
			Statement stmt = conn.createStatement();
			String sql = "select * from  users";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(((ResultSet) rs).next()){
				out.print("<tr><td>");
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
				out.print("<td>");
				out.print(((ResultSet) rs).getString(7));
				out.print("</td>");
				out.print("<td>");
				out.print(((ResultSet) rs).getString(8));
				out.print("</td>");
				out.print("<td>");
				out.print(((ResultSet) rs).getString(9));
				out.print("</td>");
				out.print("<td>");
				out.print(((ResultSet) rs).getString(10));
				out.print("</td>");
				out.print("<td>");
				out.print(((ResultSet) rs).getString(11));
				out.print("</td>");
				out.print("<td>");
				out.print(((ResultSet) rs).getString(12));
				out.print("</td>");
				out.print("<td>");
				out.print("<img src=\"usersImages/"+((ResultSet) rs).getString(11)+"\" width=\"150\" height=\"150\">");
				out.print("</td>");
				out.print("</tr>");
				out.print("<th><button onclick=\"window.location.href='adminDeleteUser.jsp'\">Delete User</button></th>");
				//out.print("<th><form action=\"adminDeleteUser\"><input type='submit' value='delete user'></form></th>");
				//out.print("<th><form action><input type='submit' value='disable user'></form></th>");
				
			}
		}
		catch(Exception p){
			System.out.println(p);
		}
		out.print("</table>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
