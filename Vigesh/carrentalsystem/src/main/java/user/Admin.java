package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.carrentalsystem.car.Car;
import com.gotrip.CarRentalSystem.DbConnection;

public class Admin extends Person{
	Connection con = DbConnection.getConnection();
	private long adminId;
      public Admin(String firstName, String lastName, String email, String gender, long phoneNumber, String password,
			String accountStatus,long adminId) {
		super(firstName, lastName, email, gender, phoneNumber, password, accountStatus);
		// TODO Auto-generated constructor stub
		this.adminId=adminId;
	}
	
	public long getAdminId() {
		return adminId;
	}
	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}
	public Admin() {
		
	}
	
	public void carManagement(int option,Car car) throws SQLException {
		if(option==1) {
			Statement st=con.createStatement();
		      int r= st.executeUpdate("INSERT INTO car(car_id, car_name,model,rental_rate,availability,seat,baggage,fuel_type,mileage) " +
		      "VALUES (" + car.getCarId() + ", '" + car.getCarName() + "', '" + car.getCarModel() + "', '" + car.getRentalRate() + "', '" + car.getAvailability() + "'," + car.getSeatNumber() + "," + car.getBaggage() + ", '" + car.getFuelType() + "', '" + car.getMileage()+ "' "+")"
		    		  );
		      System.out.println("added successfully");
		}
	}
	
    
	
//	insert into admin(admin_id,first_name,last_name,email,gender,phone_number,password
//			,account_status,username)values(1,'vignesh','s','vicky@gmail.com','MALE',875,'@vicky123','Active','Vicky123');

	
}
