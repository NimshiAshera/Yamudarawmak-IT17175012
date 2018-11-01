package Vehicle;

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

//import addDriver.Driver;
import Vehicle.Vehicle;
import Login.DBManager;

/**
 * Servlet implementation class updateVehicle
 */
@WebServlet("/updateVehicle")
public class updateVehicle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateVehicle() {
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
		
		Vehicle v = new Vehicle();
				
		HttpSession session = request.getSession();
		
		v.setUid((String)session.getAttribute("uid"));
		v.setVehicle(request.getParameter("vehicle"));
		v.setType(request.getParameter("type"));
		v.setModel(request.getParameter("model"));
		v.setHire(request.getParameter("hire"));
		v.setAC(request.getParameter("ac"));
		v.setBar(request.getParameter("bar"));
		v.setReason(request.getParameter("reason"));
		v.setPlace(request.getParameter("place"));
	
		response.setContentType("text/html");
		
		PrintWriter write =response.getWriter();
		
			DBManager db=new DBManager();
			Connection conn = db.getConnection();
			
			if(conn==null){
				write.write("Connection Not Established");
			}
			else {
				try {
					Statement st = conn.createStatement();
					String sql = "select * from vehicle where uid'" + v.getUid() + "'";
					ResultSet rs = st.executeQuery(sql);

					if (rs.next()) {
						Object message = "Vehicle is already available.";
						request.setAttribute("modelExist", message);
						request.getRequestDispatcher("/getVehicle").forward(
								request, response);

					}

					else if (!v.getModel().matches("^[A-Za-z]{2}[0-9]{4}$")) {
						Object message = "Invalid model number.";
						request.setAttribute("modelExist", message);
						request.getRequestDispatcher("/getVehicle").forward(
								request, response);

					}

					else {
						//System.out.println("IN ELSE");
						String sql3 = "update vehicle set " 
								+ "vehicle='"+ v.getVehicle() 
								+ "'," + "type='"+ v.getType() 
								+ "'," + "model='"+ v.getModel() 
								+ "'," + "hire='"+ v.getHire() 
								+ "'," + "ac='"+ v.getAC() 
								+ "'," + "bar='"+ v.getBar() 
								+ "'," + "reason='"+ v.getReason() 
								+ "'," + "place='"+ v.getPlace() 
								+ "'," + " where uid='"+ v.getUid() + "'";

						Statement st1=conn.createStatement();
						st1.executeUpdate(sql3);
						
						session.setAttribute("loggedAs", "user");
						session.setAttribute("uid", v.getUid());
						session.setAttribute("model", v.getModel());
						//session.setAttribute("vehicle", v.getVehicle());
						//session.setAttribute("type", v.getType());
						

						request.getRequestDispatcher("/home.jsp").forward(request,response);
					}
				} catch (Exception e) {
					System.out.println("Got an exception");
					System.out.println(e.getMessage());
				}
			}
	}

}
