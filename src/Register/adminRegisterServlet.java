package Register;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import Login.DBManager;

/**
 * Servlet implementation class adminRegisterServlet
 */
@WebServlet("/adminRegisterServlet")
public class adminRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminRegisterServlet() {
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
		User user = new User();

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
				String sql = "select * from users where uname = '"
						+ user.getUname() + "' or email = '" + user.getEmail()
						+ "'";
				ResultSet rs = st.executeQuery(sql);
				
				Statement st2 = conn.createStatement();
				String sql2 = "select * from admins where uname = '"
						+ user.getUname() + "' or email = '" + user.getEmail()
						+ "'";
				ResultSet rs2 = st2.executeQuery(sql2);

				if (rs.next()) {
					Object message = "Username or Email exist";
					request.setAttribute("unameExist", message);
					request.getRequestDispatcher("/adminRegisterForm.jsp").forward(
							request, response);
				}
				
				else if (rs2.next()) {
					Object message = "Username or Email exist";
					request.setAttribute("unameExist", message);
					request.getRequestDispatcher("/adminRegisterForm.jsp").forward(
							request, response);
				}
				
				else if (!user.getPassword().equals(user.getConfPassword())) {
					Object message = "Password not matching";
					request.setAttribute("passwordMatchingErr", message);
					request.getRequestDispatcher("/adminRegisterForm.jsp").forward(
							request, response);
				}

				else if (!user.getEmail().matches(
						"^[A-Z0-9a-z._%+-]+@[A-Z0-9a-z]+\\.[A-Za-z]{2,6}$")) {
					Object message = "Use Standered email";
					request.setAttribute("emailErr", message);
					request.getRequestDispatcher("/adminRegisterForm.jsp").forward(
							request, response);
				}

				else {

					String sql3 = "insert into admins (fname,lname,email,gender,country,city,telNo,uname,password,imageName,path)"
							+ "values(?,?,?,?,?,?,?,?,?,?,?)";

					PreparedStatement pre = conn.prepareStatement(sql3);

					
					pre.setString(1, user.getFname());
					pre.setString(2, user.getLname());
					pre.setString(3, user.getEmail());
					pre.setString(4, user.getGender());
					pre.setString(5, user.getCountry());
					pre.setString(6, user.getCity());
					pre.setString(7, user.getTelNo());
					pre.setString(8, user.getUname());
					pre.setString(9, user.getPassword());
					
					if (ServletFileUpload.isMultipartContent(request)) {
						Part part = request.getPart("image");
						user.setImageName(user.extractImageName(part));
						user.setPath("C:\\Users\\sangeeth\\workspaceNew\\yamudarawmak\\WebContent\\adminImages\\"
								+ File.separator + user.getImageName());
						File fileSaveDir = new File(user.getPath());
						part.write(user.getPath() + File.separator);
					
					pre.setString(10, user.getImageName());
					pre.setString(11, user.getPath());
					}
					
					pre.execute();

//					Object message = user.getUname();
//					String imageName = user.getImageName();
//					request.setAttribute("message", message);
//					request.setAttribute("imageName", imageName);
					request.getRequestDispatcher("/adminProfile.jsp").forward(request,
							response);

				}
				out.println("<h1>ConnectionEstablished</h1>"+user.getFname()+" "+user.getConfPassword());
				
			} catch (Exception e) {
				System.out.println("Got an exception");
				System.out.println(e.getMessage());
				System.out.println("<h1>ConnectionEstablished</h1>"+request.getParameter("fname1")+" "+user.getConfPassword());
			}
		}	
	}

}
