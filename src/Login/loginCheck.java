package Login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class loginCheck
 */
@WebServlet("/loginCheck")
public class loginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public loginCheck() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String uname = request.getParameter("uname").trim();
		String password = request.getParameter("password").trim();
		String dbuname = null, dbpassword = null, dbUid = null;

		String imageName = null;

		response.setContentType("text/html");
		PrintWriter write = response.getWriter();

		DBManager db = new DBManager();
		Connection conn = db.getConnection();

		if (conn == null)
			write.write("Connection Not Established");

		else {
			write.write("Connection Established");

			String message = null;
			String sql = "select uid,uname,password,imageName from users where uname='"
					+ uname + "'";

			String sql2 = "select adminId,uname,password,imageName from admins where uname = '"
					+ uname + "'";
			try {
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql);

				Statement st2 = conn.createStatement();
				ResultSet rs2 = st2.executeQuery(sql2);

				int count = 0;
				int countAdmin = 0;
		
					while (rs.next()) {

						dbUid = (rs.getString(1));
						dbuname = (rs.getString(2));
						dbpassword = (rs.getString(3));
						imageName = (rs.getString(4));
						count += 1;
					}

					if (count == 1 && dbuname.equals(uname)
							&& dbpassword.equals(password)) {
						HttpSession session = request.getSession();

						session.setAttribute("loggedAs", "user");
						session.setAttribute("uid", dbUid);
						session.setAttribute("username", dbuname);
						session.setAttribute("password", dbpassword);
						if (imageName != null)
							session.setAttribute("imageName", imageName);

						else {
							imageName = "defaultUser";
							session.setAttribute("imageName", imageName);
						}

						message = (String) session.getAttribute("username");
						request.setAttribute("message", message);
						request.setAttribute("imageName", imageName);

						request.getRequestDispatcher("/home.jsp").forward(
								request, response);
						request.getRequestDispatcher("/afterLoginHeader.jsp")
								.forward(request, response);

					}

					else if (count == 1 && dbuname.equals(uname)
							&& !dbpassword.equals(password)) {
						message = "Incorrect password";
						request.setAttribute("message1", message);
						request.getRequestDispatcher("/login.jsp").forward(
								request, response);
					}

					else if (count > 1) {
						message = "Dupplicate user " + uname;
						request.setAttribute("message1", message);
						request.getRequestDispatcher("/login.jsp").forward(
								request, response);
					}

					else{
						
						while (rs2.next()) {

							dbUid = (rs2.getString(1));
							dbuname = (rs2.getString(2));
							dbpassword = (rs2.getString(3));
							imageName = (rs2.getString(4));
							countAdmin += 1;
						}
						
						if (countAdmin == 1 && dbuname.equals(uname)
								&& dbpassword.equals(password)) {
							HttpSession session = request.getSession();

							session.setAttribute("loggedAs", "admin");
							session.setAttribute("adminId", dbUid);
							session.setAttribute("username", dbuname);
							session.setAttribute("password", dbpassword);
							if (imageName != null)
								session.setAttribute("imageName", imageName);

							else {
								imageName = "defaultUser";
								session.setAttribute("imageName", imageName);
							}

							message = (String) session.getAttribute("username");
							request.setAttribute("message", message);
							request.setAttribute("imageName", imageName);

							request.getRequestDispatcher("/home.jsp").forward(
									request, response);
							request.getRequestDispatcher("/afterLoginHeader.jsp")
									.forward(request, response);

						}

						else if (countAdmin == 1 && dbuname.equals(uname)
								&& !dbpassword.equals(password)) {
							message = "Incorrect password";
							request.setAttribute("message1", message);
							request.getRequestDispatcher("/login.jsp").forward(
									request, response);
						}

						else if (countAdmin > 1) {
							message = "Dupplicate user " + uname;
							request.setAttribute("message1", message);
							request.getRequestDispatcher("/login.jsp").forward(
									request, response);
						}
						
						else {
							message = "Cannot find user " + uname;
							request.setAttribute("message1", message);
							request.getRequestDispatcher("/login.jsp").forward(request,
									response);
						}			
					}
					
				

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
