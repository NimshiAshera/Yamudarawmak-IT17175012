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
 * Servlet implementation class adminDetails
 */
@WebServlet("/adminDetails")
public class adminDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		User user=new User();
		
		HttpSession session=request.getSession();  
		user.setUid((String)session.getAttribute("adminId"));
		
		DBManager db = new DBManager();
		Connection conn = db.getConnection();
		
		try{
			
			Statement st = conn.createStatement();
			String sql = "select adminId,fname,lname,email,gender,country,city,telNo,uname,password,imageName,path from admins where adminId = '"+user.getUid()+"'";
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()){
				  
				user.setUid(rs.getString(1));
				user.setFname(rs.getString(2));
				user.setLname(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setGender(rs.getString(5));
				user.setCountry(rs.getString(6));
				user.setCity(rs.getString(7));
				user.setTelNo(rs.getString(8));
				user.setUname(rs.getString(9));
				user.setPassword(rs.getString(10));
				user.setImageName(rs.getString(11));
				user.setPath(rs.getString(12));	
			}
			
			request.setAttribute("user", user);
			request.getRequestDispatcher("/adminProfile.jsp").forward(request,response);
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
