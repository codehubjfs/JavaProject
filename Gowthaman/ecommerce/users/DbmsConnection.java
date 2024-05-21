package com.ecommerce.users;

import java.sql.*;

public class DbmsConnection {
	private static Connection connection;
	
	public static Connection getConnection(){
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ecommercesystem","oracle123");
		//return connection;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return connection;
	}	

}
