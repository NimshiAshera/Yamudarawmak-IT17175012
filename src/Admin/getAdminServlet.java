package Admin;

import java.io.IOException;
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
 * Servlet implementation class getAdminServlet
 */
@WebServlet("/getAdminServlet")
public class getAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getAdminServlet() {
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
		Admin admin=new Admin();
		
		HttpSession session=request.getSession();  
		admin.setAdminId((String)session.getAttribute("adminId"));
		
		DBManager db = new DBManager();
		Connection conn = db.getConnection();
		
		try{
			
			Statement st = conn.createStatement();
			String sql = "select fname,lname,email,gender,country,city,telNo,uname,password from admins where adminId = '"+admin.getAdminId()+"'";
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()){
				
				admin.setFname(rs.getString(1));
				admin.setLname(rs.getString(2));
				admin.setEmail(rs.getString(3));
				admin.setGender(rs.getString(4));
				admin.setCountry(rs.getString(5));
				admin.setCity(rs.getString(6));
				admin.setTelNo(rs.getString(7));
				admin.setUname(rs.getString(8));
				admin.setPassword(rs.getString(9));
				
			}
			
			request.setAttribute("admin", admin);
			request.getRequestDispatcher("/getAdminForUpdate.jsp").forward(request,response);
		}
		catch(Exception p){
			System.out.println(p);
		}
		
		
	}

}
