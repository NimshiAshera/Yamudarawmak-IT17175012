package userProfile;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Register.User;
import Login.DBManager;

/**
 * Servlet implementation class deleteUserServlet
 */
@WebServlet("/deleteUserServlet")
public class deleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter write=response.getWriter();
		
		HttpSession session = request.getSession();
		
		User user=new User();
		
		user.setUid((String)session.getAttribute("uid"));
		
		DBManager db = new DBManager();
		Connection conn = db.getConnection();
		
		if (conn == null)
			write.write("Connection Not Established");

		else {
			write.write("Connection Established"+user.getUid());
	
			String sql = "delete from users where uid='"+user.getUid()+"'";
			try {
				Statement st = conn.createStatement();
				st.executeUpdate(sql);
				
				session.invalidate();  
		        
		        RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
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
		
		
	}
}
