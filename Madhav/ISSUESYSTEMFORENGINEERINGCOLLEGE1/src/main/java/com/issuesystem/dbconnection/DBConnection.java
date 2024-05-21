package com.issuesystem.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	final private static String username="madhavaprasad";
	final private static String password="oracle123";
	public static Connection getDBConnextion(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","madhavaprasad","oracle123");
			return conn;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	

}
