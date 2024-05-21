package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.gotrip.CarRentalSystem.DbConnection;

public class Customer extends Person {
    private int customerId;
    private String licenseNumber;
    private String userName;
    Connection con = DbConnection.getConnection();
	public Customer(String firstName, String lastName, String email, String gender, long phoneNumber, String password,
			String accountStatus,int customerId,String licenseNumber,String userName) {
		super(firstName, lastName, email, gender, phoneNumber, password, accountStatus);
		this.customerId=customerId;
		this.licenseNumber=licenseNumber;
		this.userName=userName;
		
	}
	public Customer() {
		
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
//	create table users(
//		     user_id number(10) primary key, first_name varchar2(20),last_name varchar2(20), email varchar2(30),gender varchar2(20),
//		     phone_number number(10),password varchar2(20),account_status varchar2(20),license_id varchar2(20)
//
//		);

	

}
