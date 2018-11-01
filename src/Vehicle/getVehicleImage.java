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

import Vehicle.Vehicle;
import Login.DBManager;

/**
 * Servlet implementation class getVehicleImage
 */
@WebServlet("/getVehicleImage")
public class getVehicleImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getVehicleImage() {
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
			String sql = "select vImage,path from vehicle where model = '"+vehicle.getModel()+"'";
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()){
				
				vehicle.setVImage(rs.getString(1));
				vehicle.setPath(rs.getString(2));
				
			}
			
			request.setAttribute("vehicle", vehicle);
			request.getRequestDispatcher("/getVehicleImageUpdate.jsp").forward(request,response);
		}
		catch(Exception p){
			System.out.println(p);
		}
		
	}

}
