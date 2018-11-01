package Vehicle;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import Login.DBManager;

/**
 * Servlet implementation class addVehicle
 */
@WebServlet("/addVehicle")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class addVehicle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addVehicle() {
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

		Vehicle vehicle = new Vehicle();

		HttpSession session = request.getSession();

		PrintWriter out = response.getWriter();

		vehicle.setUid((String) session.getAttribute("uid"));
		//vehicle.setUid("1");
		vehicle.setVehicle(request.getParameter("vehicle"));
		vehicle.setType(request.getParameter("type"));
		vehicle.setModel(request.getParameter("model"));
		vehicle.setHire(request.getParameter("hire"));
		vehicle.setAC(request.getParameter("ac"));
		vehicle.setBar(request.getParameter("bar"));
		vehicle.setReason(request.getParameter("reason"));
		vehicle.setPlace(request.getParameter("place"));

		// out.write("Susccess " +request.getParameter("vehicle")+"\n");
		// out.write("Susccess " +request.getParameter("type")+"\n");
		// out.write("Susccess " +request.getParameter("model")+"\n");
		// out.write("Susccess " +request.getParameter("hire")+"\n");
		// out.write("Susccess " +request.getParameter("ac")+"\n");
		// out.write("Susccess " +request.getParameter("bar")+"\n");
		// out.write("Susccess " +request.getParameter("reason")+"\n");
		// out.write("Susccess " +request.getParameter("place")+"\n");

		response.setContentType("text/html");

		PrintWriter write = response.getWriter();

		DBManager db = new DBManager();
		Connection conn = db.getConnection();

		if (conn == null) {
			write.write("Connection Not Established");
		} else {
			
			try {
				Statement st = conn.createStatement();
				String sql = "select * from vehicle where model = '"
						+ vehicle.getModel() + "'";
				ResultSet rs = st.executeQuery(sql);

				if (rs.next()) {
					Object message = "Vehicle is already available.";
					request.setAttribute("modelExist", message);
					request.getRequestDispatcher("/AddVehicle.jsp").forward(
							request, response);

				}

				else if (!vehicle.getModel().matches("^[A-Za-z]{2}[0-9]{4}$")) {
					Object message = "Invalid model number.";
					request.setAttribute("modelExist", message);
					request.getRequestDispatcher("/AddVehicle.jsp").forward(
							request, response);

				}

				else {
					String sql2 = "insert into vehicle (uid,vehicle,type,model,vImage,path,hire,ac,bar,reason,place)"
							+ "values (?,?,?,?,?,?,?,?,?,?,?)";

					PreparedStatement pre = conn.prepareStatement(sql2);

					pre.setString(1, vehicle.getUid());
					pre.setString(2, vehicle.getVehicle());
					pre.setString(3, vehicle.getType());
					pre.setString(4, vehicle.getModel());

					if (ServletFileUpload.isMultipartContent(request)) {
						Part part = request.getPart("vImage");
						vehicle.setVImage(vehicle.extractVImage(part));
						vehicle.setPath("C:\\Users\\Nimshi\\Desktop\\new workspace\\yamudarawmak-master-new\\WebContent\\vehicleImages"
								+ File.separator + vehicle.getVImage());
						File fileSaveDir = new File(vehicle.getPath());
						part.write(vehicle.getPath() + File.separator);
					}
					pre.setString(5, vehicle.getVImage());
					pre.setString(6, vehicle.getPath());
					pre.setString(7, vehicle.getHire());
					pre.setString(8, vehicle.getAC());
					pre.setString(9, vehicle.getBar());
					pre.setString(10, vehicle.getReason());
					pre.setString(11, vehicle.getPlace());

					pre.execute();

					//Object message = vehicle.getModel() + " Registered";
					//request.setAttribute("message", message);
					//request.getRequestDispatcher("/member.jsp").forward(
							//request, response);
					//request.getRequestDispatcher("/header.jsp").forward(
							//request, response);
					
					request.getRequestDispatcher("/home.jsp").forward(request,
							response);
				}

			} catch (Exception e) {
				System.out.println("Got an exception");
				System.out.println(e.getMessage());
				// System.out.println(vehicle.getUid());
			}
		}
	}

}
