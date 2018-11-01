package Vehicle;

import javax.servlet.http.Part;

public class Vehicle implements IVehicle{		
			private String vehicle; //vehicle category
			private String type;
			private String model; //model no.
			private String vImage;
			private String path;
			private String hire; //hire per day
			private String AC; //AC availability
			private String bar;//Mini-bar available
			private String reason; //for which type of reasons vehicles are providing
			private String place; //place our services spread
			private String uid; //user id
			
			
		public String getVehicle() {
			// TODO Auto-generated method stub
			return vehicle;
		}
		
		public void setVehicle(String vehicle){
			this.vehicle=vehicle;
		}
		
		public String getType() {
			// TODO Auto-generated method stub
			return type;
		}
		
		public void setType(String type){
			this.type=type;
		}

		public String getModel() {
			// TODO Auto-generated method stub
			return model;
		}
		
		public void setModel(String model){
			this.model=model;
		}

		public String getVImage() {
			return vImage;
		}

		public void setVImage(String vImage) {
			this.vImage = vImage;
		}
		
		public String getPath(){
			return path;
		}
		
		public void setPath(String path){
			this.path=path;
		}
		
		public String getHire() {
			return hire;
		}
		
		public void setHire(String hire){
			this.hire=hire;
		}
		
		public String getAC() {
			// TODO Auto-generated method stub
			return AC;
		}
		
		public void setAC(String AC){
			this.AC=AC;
		}
		
		public String getBar() {
			// TODO Auto-generated method stub
			return bar;
		}
		
		public void setBar(String bar){
			this.bar=bar;
		}
		
		public String getReason() {
			// TODO Auto-generated method stub
			return reason;
		}
		
		public void setReason(String reason){
			this.reason=reason;
		}

		public String getPlace() {
			// TODO Auto-generated method stub
			return place;
		}
		
		public void setPlace(String place){
			this.place=place;
		}
		
		public String getUid() {
			return uid;
		}

		public void setUid(String uid) {
			this.uid = uid;
		}
		
		
		public String toString(){
			return "Vehicle = " + vehicle+ "\n" + "Type = " + type + "\n" +" Image=" + vImage+ "\n"+"Pat="+ path +"\n" +"Model = " + model  + "\n" + "Hire= " + hire+"\n"+ "AC = " + AC + "\n" +"Bar = " + bar +"\n" + "Reason = " + reason + "\n" + "Place = " + place + "\n"
					+ "Uid="+ uid ;
			
		}

		public String extractVImage(Part part) {
			
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
