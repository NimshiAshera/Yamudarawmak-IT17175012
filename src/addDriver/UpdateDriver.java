package addDriver;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import addDriver.Driver;
import Login.DBManager;

/**
 * Servlet implementation class UpdateDriver
 */
@WebServlet("/UpdateDriver")
public class UpdateDriver extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDriver() {
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
		
		Driver driver = new Driver();
		
		RequestDispatcher rd = request.getRequestDispatcher("/afterLoginHeader.jsp");
		rd.include(request, response);
		
		String fName = request.getParameter("fname"); // first name
		String lName = request.getParameter("lname"); // last name
		String email = request.getParameter("email"); // email
		String NIC   = request.getParameter("NIC"); // NIC
		String mobile= request.getParameter("mobile"); // mobile number
		String uName = request.getParameter("uname"); // user name
		String pass  = request.getParameter("password"); // password
		
		response.setContentType("text/html");
		
		PrintWriter write =response.getWriter();
		
			DBManager db=new DBManager();
			Connection conn = db.getConnection();
			
			if(conn==null){
				write.write("Connection Not Established");
			}
			else{
				HttpSession session = request.getSession();
				if(session != null){
					if(session.getAttribute("username")!= null){
						String sname = (String) session.getAttribute("username");
						String spassword = (String)session.getAttribute("password");
					/*	Object message = "Welcome " + name;
						request.setAttribute("message", message);
						request.getRequestDispatcher("/index.jsp").forward(request, response);
						request.getRequestDispatcher("/header.jsp").forward(request, response);*/
					}
					else{
						response.sendRedirect("driverLogin.jsp");
					}
				}
				
				try{
					driver.setuName((String) session.getAttribute("username"));
					driver.setFname((String) session.getAttribute("fname"));
					driver.setLname((String) session.getAttribute("lname"));
					driver.setEmail((String) session.getAttribute("email"));
					driver.setNIC((String) session.getAttribute("NIC"));
					driver.setMobile((String) session.getAttribute("mobile"));
					driver.setPass((String) session.getAttribute("password"));
					
					String sql2 = "update driver set username = ? ,fname = ? ,lname = ? ,email = ? ,NIC = ? ,mobile = ? ,password = ?"
							+ "where username = '"+driver.getuName()+"'";
					
					
					PreparedStatement pre = conn.prepareStatement(sql2);
					
					pre.setString(1, uName);
					pre.setString(2, fName);
					pre.setString(3, lName);
					pre.setString(4, email);
					pre.setString(5, NIC);
					pre.setString(6, mobile);
					pre.setString(7, pass);
					
					pre.execute();
					
					Object message = "Successfully updated";
					request.setAttribute("message", message);
					request.getRequestDispatcher("/viewDriver.jsp").forward(request, response);
					request.getRequestDispatcher("/header.jsp").forward(request, response);
				
				}
				catch(Exception e){
					System.out.println("Got an exception");
					System.out.println(e.getMessage());
				}
			}
	}

}
