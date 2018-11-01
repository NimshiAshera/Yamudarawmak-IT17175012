package addDriver;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Login.DBManager;

/**
 * Servlet implementation class deleteDriverRequestServlet
 */
@WebServlet("/deleteDriverRequestServlet")
public class deleteDriverRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteDriverRequestServlet() {
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
		
		String email= (String) request.getParameter("email");
		String reason= (String) request.getParameter("message");
		
		/*System.out.println(email);
		System.out.println(reason);
		*/
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
						
						String sql = "insert into driverrequest (username,email,reason) values(?,?,?)";
			
						
						try {
							
							PreparedStatement pre = conn.prepareStatement(sql);
							
							pre.setString(1, sname);
							pre.setString(2, email);
							pre.setString(3, reason);
							
							pre.execute();
							
							Object message = "Successfully got your request";
							request.setAttribute("message", message);
							request.getRequestDispatcher("/index.jsp").forward(request, response);
							request.getRequestDispatcher("/header.jsp").forward(request, response);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();

						}
						
						
					}
					else{
						response.sendRedirect("driverLogin.jsp");
					}
				}
			}
	}
}
