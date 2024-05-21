package com.ecommerce.users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	private static Connection connection;
	
	public DbConnection() {
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ecommercesystem","oracle123");
		}catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static DbConnection dbConnection;
	
	public  Connection getConnection() {
		return connection;
	}
	
	public static DbConnection getDbConnection() {
		if(dbConnection == null) {
			dbConnection = new DbConnection();
		}
		return dbConnection;
	}

}
