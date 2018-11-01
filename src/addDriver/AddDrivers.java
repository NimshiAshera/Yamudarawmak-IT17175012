package addDriver;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;




import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import Login.DBManager;


/**
 * Servlet implementation class AddDrivers
 */
@WebServlet("/AddDrivers")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class AddDrivers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public AddDrivers() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Driver driver = new Driver();
		
		driver.setuName(request.getParameter("uname"));
		driver.setFname(request.getParameter("fname"));
		driver.setLname(request.getParameter("lname"));
		driver.setMobile(request.getParameter("mobile"));
		driver.setEmail(request.getParameter("email"));
		driver.setNIC(request.getParameter("nic"));
		driver.setPass(request.getParameter("password"));
		driver.setConpass(request.getParameter("confirmPassword"));

	/*	String fName = request.getParameter("fname"); // first name
		String lName = request.getParameter("lname"); // last name
		String email = request.getParameter("email"); // email
		String NIC   = request.getParameter("nic"); // NIC
		String mobile= request.getParameter("mobile"); // mobile number
		String uName = request.getParameter("uname"); // user name
		String pass  = request.getParameter("password"); // password
		String coPass= request.getParameter("confirmPassword"); // confirm password
	*/
		response.setContentType("text/html");
		
		PrintWriter write =response.getWriter();

		
			DBManager db=new DBManager();
			Connection conn = db.getConnection();
			
			if(conn==null){
				write.write("Connection Not Established");
			}
			else{
				try{
					if(driver.getEmail().isEmpty()&&driver.getFname().isEmpty()&&driver.getLname().isEmpty()
							&&driver.getMobile().isEmpty()&&driver.getNIC().isEmpty()&&driver.getPass().isEmpty()
							&&driver.getPass().isEmpty()&&driver.getuName().isEmpty()&&driver.getConpass().isEmpty()){
						Object message = "Cannot have empty feiled...Fill all";
						request.setAttribute("emptyfill", message);
						request.setAttribute("vfname", driver.getFname());
						request.setAttribute("vlname", driver.getLname());
						request.setAttribute("vemail", driver.getEmail());
						request.setAttribute("vnic", driver.getNIC());
						request.setAttribute("vmobile", driver.getMobile());
						request.setAttribute("vuser", driver.getuName());
						request.getRequestDispatcher("/addDrivers.jsp").forward(request,response);
					}
					Statement st = conn.createStatement();
					String sql = "select * from driver where username = '"+driver.getuName()+"'";
					ResultSet rs = st.executeQuery(sql);
					
					if(rs.next()){
						Object message = "User is exist";
						request.setAttribute("unameExist", message);
						request.setAttribute("vfname", driver.getFname());
						request.setAttribute("vlname", driver.getLname());
						request.setAttribute("vemail", driver.getEmail());
						request.setAttribute("vnic", driver.getNIC());
						request.setAttribute("vmobile", driver.getMobile());
						request.setAttribute("vuser", driver.getuName());
						request.getRequestDispatcher("/addDrivers.jsp").forward(request,response);
						request.getRequestDispatcher("/header.jsp").forward(request,response);
					}
				}
				catch(Exception e){
					System.out.println("Error: " + e.getMessage());
				}
				
				if(!driver.getPass().equals(driver.getConpass())){
					Object message = "Password not maching";
					request.setAttribute("passwordMatchingErr", message);
					request.setAttribute("vfname", driver.getFname());
					request.setAttribute("vlname", driver.getLname());
					request.setAttribute("vemail", driver.getEmail());
					request.setAttribute("vnic", driver.getNIC());
					request.setAttribute("vmobile", driver.getMobile());
					request.setAttribute("vuser", driver.getuName());
					request.getRequestDispatcher("/addDrivers.jsp").forward(request,response);
					request.getRequestDispatcher("/header.jsp").forward(request,response);
				}
				else if(!driver.getEmail().matches("^[A-Z0-9a-z._%+-]+@[A-Z0-9a-z]+\\.[A-Za-z]{2,6}$")){
					Object message = "Use Standered email";
					request.setAttribute("email", message);
					request.setAttribute("vfname", driver.getFname());
					request.setAttribute("vlname", driver.getLname());
					request.setAttribute("vemail", driver.getEmail());
					request.setAttribute("vnic", driver.getNIC());
					request.setAttribute("vmobile", driver.getMobile());
					request.setAttribute("vuser", driver.getuName());
					request.getRequestDispatcher("/addDrivers.jsp").forward(request,response);
					request.getRequestDispatcher("/header.jsp").forward(request,response);
				}
				else if(!driver.getNIC().matches("^[0-9]{9}[vVxX]$")){
					Object message = "Invalid NIC";
					request.setAttribute("nic", message);
					request.setAttribute("vfname", driver.getFname());
					request.setAttribute("vlname", driver.getLname());
					request.setAttribute("vemail", driver.getEmail());
					request.setAttribute("vnic", driver.getNIC());
					request.setAttribute("vmobile", driver.getMobile());
					request.setAttribute("vuser", driver.getuName());
					request.getRequestDispatcher("/addDrivers.jsp").forward(request,response);
					request.getRequestDispatcher("/header.jsp").forward(request,response);
				}
				else if (!driver.getMobile().matches("^[0-9]{10}$")){
					Object message = "Invalid mobile";
					request.setAttribute("mobile", message);
					request.setAttribute("vfname", driver.getFname());
					request.setAttribute("vlname", driver.getLname());
					request.setAttribute("vemail", driver.getEmail());
					request.setAttribute("vnic", driver.getNIC());
					request.setAttribute("vmobile", driver.getMobile());
					request.setAttribute("vuser", driver.getuName());
					request.getRequestDispatcher("/addDrivers.jsp").forward(request,response);
					request.getRequestDispatcher("/header.jsp").forward(request,response);
				}
				else{
					String sql2 = "insert into driver (username,fname,lname,email,NIC,mobile,password,image,path)"
							+ "values (?,?,?,?,?,?,?,?,?);";
					try{
						PreparedStatement pre = conn.prepareStatement(sql2);
						
						pre.setString(1, driver.getuName());
						pre.setString(2, driver.getFname());
						pre.setString(3, driver.getLname());
						pre.setString(4, driver.getEmail());
						pre.setString(5, driver.getNIC());
						pre.setString(6, driver.getMobile());
						pre.setString(7, driver.getPass());
												
						if (ServletFileUpload.isMultipartContent(request)) {
							Part part = request.getPart("image");
							driver.setImageName(driver.extractImageName(part));
							driver.setPath("C:\\Users\\Nimshi\\Desktop\\new workspace\\yamudarawmak-master-new\\WebContent\\usersImages"
									+ File.separator + driver.getImageName());
							File fileSaveDir = new File(driver.getPath());
							part.write(driver.getPath() + File.separator);
						
						pre.setString(8, driver.getImageName());
						pre.setString(9, driver.getPath());
						}
						pre.execute();

						request.getRequestDispatcher("/home.jsp").forward(request,
								response);

					}
					catch(Exception e){
						System.out.println("Got an exception");
						System.out.println(e.getMessage());
					}
				}
			}		
	}
}
