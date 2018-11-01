package userProfile;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Register.User;
import Login.DBManager;

/**
 * Servlet implementation class updateUserServlet
 */
@WebServlet("/updateUserServlet")
public class updateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public updateUserServlet() {
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
		User user = new User();

		HttpSession session = request.getSession();
			
		user.setUid((String)session.getAttribute("uid"));
		user.setFname(request.getParameter("fname"));
		user.setLname(request.getParameter("lname"));
		user.setEmail(request.getParameter("email"));
		user.setGender(request.getParameter("gender"));
		user.setCountry(request.getParameter("country"));
		user.setCity(request.getParameter("city"));
		user.setTelNo(request.getParameter("telNo"));
		user.setUname(request.getParameter("uname"));
		user.setPassword(request.getParameter("password"));
		user.setConfPassword(request.getParameter("confirmPassword"));

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		
		DBManager db = new DBManager();
		Connection conn = db.getConnection();

		if (conn == null) {
			out.write("Connection Not Established");
		} else {
			try {
				Statement st = conn.createStatement();
				String sql = "select * from users where (uname = '"
						+ user.getUname() + "' or email = '" + user.getEmail()
						+ "') and uid<>'" + user.getUid() + "'";
				ResultSet rs = st.executeQuery(sql);
				
				Statement st2 = conn.createStatement();
				String sql2 = "select * from admins where uname = '"
						+ user.getUname() + "' or email = '" + user.getEmail()
						+ "'";
				ResultSet rs2 = st2.executeQuery(sql2);

				if (rs.next()) {
					Object message = "Username or Email exist";
					request.setAttribute("unameExist", message);
					request.getRequestDispatcher("/getUserServlet")
							.forward(request, response);
					
//					out.println("<script>");
//					out.println("function myFunction() {");
//					out.println("alert(\"I am an alert box!\");}");
//					out.println("</script>");
				}
				
				else if (rs2.next()) {
					Object message = "Username or Email exist";
					request.setAttribute("unameExist", message);
					request.getRequestDispatcher("/getUserServlet").forward(
							request, response);					
				}

				else if (!user.getPassword().equals(user.getConfPassword())) {
					Object message = "Password not maching";
					request.setAttribute("passwordMatchingErr", message);
					request.getRequestDispatcher("/getUserServlet")
							.forward(request, response);
				}

				else if (!user.getEmail().matches(
						"^[A-Z0-9a-z._%+-]+@[A-Z0-9a-z]+\\.[A-Za-z]{2,6}$")) {
					Object message = "Use Standered email";
					request.setAttribute("emailErr", message);
					request.getRequestDispatcher("/getUserServlet")
							.forward(request, response);
				}

				else {

					String sql3 = "update users set " + "fname='"
							+ user.getFname() + "'," + "lname='"
							+ user.getLname() + "'," + "email='"
							+ user.getEmail() + "'," + "gender='"
							+ user.getGender() + "'," + "country='"
							+ user.getCountry() + "'," + "city='"
							+ user.getCity() + "'," + "telNo='"
							+ user.getTelNo() + "'," + "uname='"
							+ user.getUname() + "'," + "password='"
							+ user.getPassword() + "'" + " where uid='"
							+ user.getUid() + "'";

					Statement st1=conn.createStatement();
					st1.executeUpdate(sql3);
					
					session.setAttribute("loggedAs", "user");
					session.setAttribute("uid", user.getUid());
					session.setAttribute("username", user.getUname());
					session.setAttribute("password", user.getPassword());

					request.getRequestDispatcher("/home.jsp").forward(request,response);
				}
			} catch (Exception e) {
				System.out.println("Got an exception");
				System.out.println(e.getMessage());
			}
		}

	}

}
