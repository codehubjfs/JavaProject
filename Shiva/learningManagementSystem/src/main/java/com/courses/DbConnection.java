package com.courses;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	public Connection getconnection()
	{
		Connection con = null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
			//c=con;
			//System.out.println(con);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return con;
	}
}
