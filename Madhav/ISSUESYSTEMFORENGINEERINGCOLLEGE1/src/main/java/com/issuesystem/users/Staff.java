package com.issuesystem.users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.issuesystem.dbconnection.DBConnection;

public class Staff {

	public static String authenticateStaff(String username,String password) {
		try {
		    String sql = "SELECT username, password, name FROM staff WHERE username=?";
		    PreparedStatement stmt = DBConnection.getDBConnextion().prepareStatement(sql);
		    stmt.setString(1, username);
		    ResultSet result = stmt.executeQuery();
		    Person person=new Person(username,password);
		    if (result.next()) { // Check if ResultSet has any rows
		        String storedUsername = result.getString("username");
		        String storedPassword = result.getString("password");
		        String name = result.getString("name");

		        if (storedUsername.equalsIgnoreCase(person.getUsername()) && storedPassword.equals(person.getPassword())) {
		            
		        	return "......................welcome"+" "+name+"........................"+ "\nlogged as Staff";
		        } else {
		            
		            return "Wrong";
		        }
		    } 
		      
		  
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		  return "User not found";
	}

}
