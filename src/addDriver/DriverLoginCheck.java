package addDriver;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Login.DBManager;

/**
 * Servlet implementation class DriverLoginCheck
 */
@WebServlet("/DriverLoginCheck")
public class DriverLoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DriverLoginCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uname = request.getParameter("uname").trim();
		String password = request.getParameter("password").trim();
		String dbuname = null,dbpassword=null,dbfname=null,dblname=null
				,dbemail=null,dbmobile=null,dbNIC=null,dbImage=null;

		response.setContentType("text/html");
		PrintWriter write=response.getWriter();
		
		DBManager db = new DBManager();
		Connection conn = db.getConnection();
		
		if (conn == null)
			write.write("Connection Not Established");

		else {
			write.write("Connection Established");

			String message=null;
			String sql = "select username,fname,lname,email,mobile,NIC,password,image from driver where username = '"+uname+"'";
			
				
			try {
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql);
				
				int count = 0;
				while (rs.next()) {
					dbuname=(rs.getString(1));
					dbfname=(rs.getString(2));
					dblname=(rs.getString(3));
					dbemail=(rs.getString(4));
					dbmobile=(rs.getString(5));
					dbNIC=(rs.getString(6));
					dbpassword=(rs.getString(7));
					dbImage=(rs.getString(8));
					count += 1;
				}

				if (count == 1 && dbuname.equals(uname) && dbpassword.equals(password)) {
					
					HttpSession session = request.getSession();
					
					session.setAttribute("username", dbuname);
					session.setAttribute("loggedAs", "driver");
					session.setAttribute("fname", dbfname);
					session.setAttribute("lname", dblname);
					session.setAttribute("email", dbemail);
					session.setAttribute("NIC", dbNIC);
					session.setAttribute("mobile", dbmobile);
					session.setAttribute("password", dbpassword);
					
					if (dbImage != null)
						session.setAttribute("imageName", dbImage);

					else {
						dbImage = "defaultUser";
						session.setAttribute("imageName", dbImage);
					}
					
					message="Welcome "+uname;
					request.setAttribute("message", message);
					request.getRequestDispatcher("viewDriver.jsp").forward(request,response);
					request.getRequestDispatcher("/header.jsp").forward(request,response);
					
				}

				else if (count == 1 && dbuname.equals(uname) && !dbpassword.equals(password)) {
					message="Incorrect password";
					request.setAttribute("message", message);
					request.getRequestDispatcher("/driverLogin.jsp").forward(request,response);
				}
				
				else if (count > 1) {
					message="Dupplicate user "+uname;
					request.setAttribute("message", message);
					request.getRequestDispatcher("/driverLogin.jsp").forward(request,response);
				}

				else {
					message="Cannot find user "+uname;
					request.setAttribute("message", message);
					request.getRequestDispatcher("/driverLogin.jsp").forward(request,response);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
