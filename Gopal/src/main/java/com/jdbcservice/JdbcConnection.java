package com.jdbcservice;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Scanner;

import com.jobportal.listings.Job;
import com.jobportal.users.Admin;
import com.jobportal.users.Employee;
import com.jobportal.users.JobSeeker;
import com.jobportal.users.User;
public class JdbcConnection {
	static Scanner sc = new Scanner(System.in);
	static String URL = "jdbc:oracle:thin:@localhost:1522:xe";
	static String userName = "JOBPORTAL";
	static String password = "ORACLE1234";

	public static Connection connectdatabase() throws ClassNotFoundException, SQLException {
		Connection con = null;
		// Step1: Register the class.
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// Step2 - Create Connections.
		con = DriverManager.getConnection(URL, userName, password);
		// checking connection established or not.
	

		if (con ==null)
		{
			//System.out.println("Connection Not Established!");
		}
		else {
			//System.out.println("Connection established!");
		}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return con;

	}



	public static void registerEmloyee(Connection con, Employee employee) throws SQLException {
		try {
			String sql = "INSERT INTO EMPLOYERS (EMPLOYER_ID, USERNAME,PASSWORD,EMAIL,DOMAIN,COMPANY_NAME,INDUSTRY,FIRSTNAME, LASTNAME,PHONENUMBER,DOB, GENDER,ADDRESS) VALUES (?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = con.prepareStatement(sql);

			statement.setInt(1, employee.getEmployerId());
			statement.setString(2, employee.getUsername());
			statement.setString(3, employee.getPassword());
			statement.setString(4, employee.getEmail());
			statement.setString(5, employee.getDomain());
			statement.setString(6, employee.getCompanyName());
			statement.setString(7, employee.getIndustry());
			statement.setString(8, employee.getFirstName());
			statement.setString(9, employee.getLastName());
			statement.setLong(10, employee.getPhoneNumber());
			statement.setString(11, employee.getDateOfBirth());
			statement.setString(12, employee.getGender());
			statement.setString(13, employee.getAddress());
			int rowsInserted = statement.executeUpdate();

			if (rowsInserted > 0) {
				System.out.println("Employee registered successfully.");
			} else {
				System.out.println("Failed to register admin.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public static boolean employerLogin(Connection con, String username, String password) throws SQLException {
		String sql = "SELECT * FROM EMPLOYERS WHERE USERNAME = ? AND PASSWORD = ?";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, username);
		statement.setString(2, password);
		ResultSet resultSet = statement.executeQuery();
		return resultSet.next();
	}
//	public static boolean jobSeekerLogin(Connection con, String username, String password) throws SQLException {
//		String sql = "SELECT * FROM Job_Seekers WHERE USERNAME = ? AND PASSWORD = ?";
//		PreparedStatement statement = con.prepareStatement(sql);
//		statement.setString(1, username);
//		statement.setString(2, password);
//		ResultSet resultSet = statement.executeQuery();
//		return resultSet.next();
//	}
//	
		
	}
	

	
	


