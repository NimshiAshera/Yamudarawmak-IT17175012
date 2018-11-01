package Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Login.DBManager;
import Register.User;

/**
 * Servlet implementation class adminDeleteAdmin
 */
@WebServlet("/adminDeleteAdmin")
public class adminDeleteAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public adminDeleteAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter write = response.getWriter();

		HttpSession session = request.getSession();

		Admin admin = new Admin();

		admin.setAdminId(request.getParameter("uidForDelete"));

		DBManager db = new DBManager();
		Connection conn = db.getConnection();

		if (conn == null)
			write.write("Connection Not Established");

		else {
			write.write("Connection Established" + admin.getAdminId());

			if (admin.getAdminId().equalsIgnoreCase((String) session.getAttribute("adminId"))) {
				Object message = "Cannot delete YOURSELF";
				request.setAttribute("deleteError", message);
				request.getRequestDispatcher("/adminDeleteAdmin.jsp").forward(
						request, response);
			}

			else {
				String sql = "delete from admins where adminId='"
						+ admin.getAdminId() + "'";
				try {
					Statement st = conn.createStatement();
					st.executeUpdate(sql);

					RequestDispatcher rd = request
							.getRequestDispatcher("/adminViewAdmin");
					rd.forward(request, response);

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
