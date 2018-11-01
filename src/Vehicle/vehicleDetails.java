package Vehicle;

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
import Vehicle.Vehicle;

/**
 * Servlet implementation class vehicleDetails
 */
@WebServlet("/vehicleDetails")
public class vehicleDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public vehicleDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("Success");

		PrintWriter out = response.getWriter();
		
		Vehicle vehicle=new Vehicle();
		
		HttpSession session=request.getSession();  
		vehicle.setUid((String)session.getAttribute("uid"));
		
		DBManager db = new DBManager();
		Connection conn = db.getConnection();
		
		//System.out.println("Success");
		
		try{
			
						Statement st = conn.createStatement();
						String sql = "select uid,vehicle,type,model,vImage,path,hire,ac,bar,reason,place from vehicle where uid= '"+vehicle.getUid()+"'";
						ResultSet rs = st.executeQuery(sql);
						
						
						while(rs.next()){
							  
							vehicle.setUid(rs.getString(1));
							vehicle.setVehicle(rs.getString(2));
							vehicle.setType(rs.getString(3));
							vehicle.setModel(rs.getString(4));
							vehicle.setVImage(rs.getString(5));
							vehicle.setPath(rs.getString(6));
							vehicle.setHire(rs.getString(7));
							vehicle.setAC(rs.getString(8));
							vehicle.setBar(rs.getString(9));
							vehicle.setReason(rs.getString(10));
							vehicle.setPlace(rs.getString(11));
							
						}
						
						request.setAttribute("vehicle", vehicle);
						//request.getRequestDispatcher("/getUser.jsp").forward(request,response);
						request.getRequestDispatcher("/getVehicle.jsp").forward(request,response);
						System.out.println("Success");
					}
					catch(Exception p){
						System.out.println(p);
					}
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
