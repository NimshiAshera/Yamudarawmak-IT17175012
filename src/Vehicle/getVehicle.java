package Vehicle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
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
import Vehicle.Vehicle;
/**
 * Servlet implementation class getVehicle
 */
@WebServlet("/getVehicle")
public class getVehicle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getVehicle() {
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
		
		PrintWriter out = response.getWriter();
		
		Vehicle vehicle=new Vehicle();
		
		HttpSession session=request.getSession();  
		vehicle.setUid((String)session.getAttribute("uid"));
		
		DBManager db = new DBManager();
		Connection conn = db.getConnection();
		
		try{
			
			Statement st = conn.createStatement();
			String sql = "select vehicle,type,model,hire,ac,bar,reason,place from vehicle where uid = '"+vehicle.getUid()+"'";
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()){
				
				vehicle.setVehicle(rs.getString(1));
				vehicle.setType(rs.getString(2));
				vehicle.setModel(rs.getString(3));
				vehicle.setHire(rs.getString(4));
				vehicle.setAC(rs.getString(5));
				vehicle.setBar(rs.getString(6));
				vehicle.setReason(rs.getString(7));
				vehicle.setPlace(rs.getString(8));
				
				
			}
			
			request.setAttribute("vehicle", vehicle);
			request.getRequestDispatcher("/getVehicleUpdate.jsp").forward(request,response);
		}
		catch(Exception p){
			System.out.println(p);
		}
		
	}

}
