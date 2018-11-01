package Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Login.DBManager;
import Register.User;

/**
 * Servlet implementation class updateAdminServlet
 */
@WebServlet("/updateAdminServlet")
public class updateAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateAdminServlet() {
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
		Admin admin = new Admin();

		HttpSession session = request.getSession();
			
		admin.setAdminId((String)session.getAttribute("adminId"));
		admin.setFname(request.getParameter("fname"));
		admin.setLname(request.getParameter("lname"));
		admin.setEmail(request.getParameter("email"));
		admin.setGender(request.getParameter("gender"));
		admin.setCountry(request.getParameter("country"));
		admin.setCity(request.getParameter("city"));
		admin.setTelNo(request.getParameter("telNo"));
		admin.setUname(request.getParameter("uname"));
		admin.setPassword(request.getParameter("password"));
		admin.setConfPassword(request.getParameter("confirmPassword"));

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		
		DBManager db = new DBManager();
		Connection conn = db.getConnection();

		if (conn == null) {
			out.write("Connection Not Established");
		} else {
			out.write("Connection Established");
			try {
				Statement st = conn.createStatement();
				String sql = "select * from admins where (uname = '"
						+ admin.getUname() + "' or email = '" + admin.getEmail()
						+ "') and adminId<>'" + admin.getAdminId() + "'";
				ResultSet rs = st.executeQuery(sql);
				
				Statement st2 = conn.createStatement();
				String sql2 = "select * from users where uname = '"
						+ admin.getUname() + "' or email = '" + admin.getEmail()
						+ "'";
				ResultSet rs2 = st2.executeQuery(sql2);

				if (rs.next()) {
					Object message = "Username or Email exist";
					request.setAttribute("unameExist", message);
					request.getRequestDispatcher("/getAdminServlet")
							.forward(request, response);
				
				}
				
				else if (rs2.next()) {
					Object message = "Username or Email exist";
					request.setAttribute("unameExist", message);
					request.getRequestDispatcher("/getAdminServlet").forward(
							request, response);					
				}

				else if (!admin.getPassword().equals(admin.getConfPassword())) {
					Object message = "Password not maching";
					request.setAttribute("passwordMatchingErr", message);
					request.getRequestDispatcher("/getAdminServlet")
							.forward(request, response);
				}

				else if (!admin.getEmail().matches(
						"^[A-Z0-9a-z._%+-]+@[A-Z0-9a-z]+\\.[A-Za-z]{2,6}$")) {
					Object message = "Use Standered email";
					request.setAttribute("emailErr", message);
					request.getRequestDispatcher("/getAdminServlet")
							.forward(request, response);
				}

				else {

					String sql3 = "update admins set " + "fname='"
							+ admin.getFname() + "'," + "lname='"
							+ admin.getLname() + "'," + "email='"
							+ admin.getEmail() + "'," + "gender='"
							+ admin.getGender() + "'," + "country='"
							+ admin.getCountry() + "'," + "city='"
							+ admin.getCity() + "'," + "telNo='"
							+ admin.getTelNo() + "'," + "uname='"
							+ admin.getUname() + "'," + "password='"
							+ admin.getPassword() + "'" + " where adminId='"
							+ admin.getAdminId() + "'";

					Statement st1=conn.createStatement();
					st1.executeUpdate(sql3);
					
					session.setAttribute("loggedAs", "admin");
					session.setAttribute("adminId", admin.getAdminId());
					session.setAttribute("username", admin.getUname());
					session.setAttribute("password", admin.getPassword());

					request.getRequestDispatcher("/home.jsp").forward(request,response);
				}
			} catch (Exception e) {
				System.out.println("Got an exception");
				System.out.println(e.getMessage());
			}
		}
	}

}
