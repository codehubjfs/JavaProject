package com.mms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	
	private static final String USERNAME = "system";
	
	private static final String PASSWORD = "oracle";
	
	private static Connection connection = null;
	
	public static Connection openConnection() {
		if (connection != null) return connection;
        else {
            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } 
            return connection;
        }
	}

}
