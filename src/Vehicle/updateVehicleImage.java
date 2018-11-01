package Vehicle;

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
import Vehicle.Vehicle;
import Login.DBManager;
/**
 * Servlet implementation class updateVehicleImage
 */
@WebServlet("/updateVehicleImage")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class updateVehicleImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateVehicleImage() {
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
			
		Vehicle vehicle = new Vehicle();

		HttpSession session = request.getSession();

		vehicle.setUid((String) session.getAttribute("uid"));

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		
		//System.out.println("Success");

		if (ServletFileUpload.isMultipartContent(request)) {
			Part part = request.getPart("vImage");
			vehicle.setVImage(vehicle.extractVImage(part));
			vehicle.setPath("C:\\Users\\Nimshi\\Desktop\\new workspace\\yamudarawmak-master\\WebContent\\vehicleImages"
					+ File.separator + vehicle.getVImage());
			File fileSaveDir = new File(vehicle.getPath());
			part.write(vehicle.getPath() + File.separator);
		}

		System.out.println("Success");
		DBManager db = new DBManager();
		Connection conn = db.getConnection();

		if (conn == null) {
			out.write("Connection Not Established");
		} else {
			try {
				String sql = "update vehicle set " + "vImage='"
						+ vehicle.getVImage() + "'," + "path='"
						+ vehicle.getPath() + "'" + " where uid='" + vehicle.getUid()
						+ "'";

				Statement st = conn.createStatement();
				st.executeUpdate(sql);

				session.setAttribute("loggedAs", "user");
				session.setAttribute("vImage", vehicle.getVImage());

				request.getRequestDispatcher("/home.jsp").forward(request,
						response);
			} catch (Exception e) {
				System.out.println("Got an exception");
				System.out.println(e.getMessage());
			}
		}
		
	}

}
