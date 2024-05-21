package com.gotrip.CarRentalSystem;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	private static Connection connection;
	
	public static Connection getConnection(){
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","carrentalsystem","oracle123");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return connection;
	}
}
