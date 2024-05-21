package com.issuesystem.users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.issuesystem.dbconnection.DBConnection;

public class Admin {
	public static String authenticate_Admin(String username,String password) throws SQLException{
		
		
		try {
		    String sql = "SELECT username, password,name FROM Admin WHERE username=?";
		    PreparedStatement stmt = DBConnection.getDBConnextion().prepareStatement(sql);
		    stmt.setString(1, username);
		    ResultSet result = stmt.executeQuery();
		    Person person=new Person(username,password);
		    if (result.next()) { // Check if ResultSet has any rows
		        String storedUsername = result.getString("username");
		        String storedPassword = result.getString("password");
		        String name = result.getString("name");
		       
		        if (storedUsername.equalsIgnoreCase(person.getUsername()) && storedPassword.equals(person.getPassword())) {
		            
		            return "......................welcome"+" "+name+"........................"+ "\nlogged as: ADMIN";
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
