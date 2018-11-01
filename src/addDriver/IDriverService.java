package addDriver;

import java.util.ArrayList;
import java.util.logging.Logger;

public interface IDriverService {
	//initialize logger
	public static final Logger log = Logger.getLogger(IDriverService.class.getName());
	//add Driver to table
	public void addDriver(Driver driver);
	//get a particular Driver
	public Driver getDriverByUname(String uName);
	//get all list of driver
	public ArrayList<Driver> addDriver();
	//update driver
	public Driver updateDriver (String uName, Driver driver);
	//remover driver
	public void removeDriver(String uName);
}
