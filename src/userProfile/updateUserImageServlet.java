package userProfile;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import Register.User;
import Login.DBManager;

/**
 * Servlet implementation class updateUserImageServlet
 */
@WebServlet("/updateUserImageServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class updateUserImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public updateUserImageServlet() {
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

		user.setUid((String) session.getAttribute("uid"));

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		if (ServletFileUpload.isMultipartContent(request)) {
			Part part = request.getPart("image");
			user.setImageName(user.extractImageName(part));
			user.setPath("C:\\Users\\Nimshi\\Desktop\\new workspace\\yamudarawmak-master-new\\WebContent\\usersImages"
					+ File.separator + user.getImageName());
			File fileSaveDir = new File(user.getPath());
			part.write(user.getPath() + File.separator);
		}


		DBManager db = new DBManager();
		Connection conn = db.getConnection();

		if (conn == null) {
			out.write("Connection Not Established");
		} else {
			try {
				String sql = "update users set " + "imageName='"
						+ user.getImageName() + "'," + "path='"
						+ user.getPath() + "'" + " where uid='" + user.getUid()
						+ "'";

				Statement st = conn.createStatement();
				st.executeUpdate(sql);

				session.setAttribute("loggedAs", "user");
				session.setAttribute("imageName", user.getImageName());

				request.getRequestDispatcher("/home.jsp").forward(request,
						response);
				request.getRequestDispatcher("/afterLoginHeader.jsp").forward(
						request, response);
			} catch (Exception e) {
				System.out.println("Got an exception");
				System.out.println(e.getMessage());
			}
		}
	}

}
