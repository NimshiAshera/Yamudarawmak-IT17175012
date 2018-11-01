package Register;

import java.io.File;

import javax.servlet.http.Part;

public class User {

	private String uid;
	private String fname; // first name
	private String lname; // last name
	private String email; // email
	private String gender;
	private String country;
	private String city;
	private String telNo;
	private String uname;
	private String password;
	private String confPassword;
	private String imageName;
	private String path;
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfPassword() {
		return confPassword;
	}

	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}


	@Override
	public String toString() {
		return "User [uid="+uid+", fname=" + fname + ", lname=" + lname
				+ ", email=" + email + ", gender=" + gender + ", country="
				+ country + ", city=" + city + ", telNo=" + telNo + ", uname="
				+ uname + ", password=" + password + ", confPassword="
				+ confPassword + ", imageName=" + imageName + ", path=" + path
				+ "]";
	}

	public String extractImageName(Part part){
		String contentDisp=part.getHeader("content-disposition");
		String[] items=contentDisp.split(";");
		for(String s:items){
			if(s.trim().startsWith("filename")){
				return s.substring(s.indexOf("=") + 2,s.length() -1);
			}
		}
		return "";
	}

}
