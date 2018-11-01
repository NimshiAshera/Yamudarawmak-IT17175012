package Admin;

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
import Register.User;

/**
 * Servlet implementation class getAdminImageServlet
 */
@WebServlet("/getAdminImageServlet")
public class getAdminImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getAdminImageServlet() {
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
		PrintWriter out = response.getWriter();
		
		User user=new User();
		
		HttpSession session=request.getSession();  
		user.setUid((String)session.getAttribute("adminId"));
		
		DBManager db = new DBManager();
		Connection conn = db.getConnection();
		
		try{
			
			Statement st = conn.createStatement();
			String sql = "select imageName,path from admins where adminId = '"+user.getUid()+"'";
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()){
				
				user.setImageName(rs.getString(1));
				user.setPath(rs.getString(2));
				
			}
			
			request.setAttribute("user", user);
			request.getRequestDispatcher("/getAdminForImageUpdate.jsp").forward(request,response);
		}
		catch(Exception p){
			System.out.println(p);
		}
	}

}
