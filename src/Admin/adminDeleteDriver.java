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

import Login.DBManager;
import addDriver.*;

/**
 * Servlet implementation class adminDeleteDriver
 */
@WebServlet("/adminDeleteDriver")
public class adminDeleteDriver extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminDeleteDriver() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter write=response.getWriter();

		Driver driver=new Driver();
		
		driver.setuName(request.getParameter("unameForDelete"));
		DBManager db = new DBManager();
		Connection conn = db.getConnection();
		
		if (conn == null)
			write.write("Connection Not Established");

		else {
			write.write("Connection Established "+driver.getuName());
	
			String sql = "delete from driver where username='"+driver.getuName()+"'";
			try {
				Statement st = conn.createStatement();
				st.executeUpdate(sql);  
		        
		        RequestDispatcher rd = request.getRequestDispatcher("/adminViewDriver");
				rd.forward(request, response);
				
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
