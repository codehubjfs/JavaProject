package com.users;
import java.sql.*;
import java.util.*;
public class DBConnection {
	public static Connection getconnection()
	{
		Connection con=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","shivasankaran","oracle123");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return con;
	}
}
