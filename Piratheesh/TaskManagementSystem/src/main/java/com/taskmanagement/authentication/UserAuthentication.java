package com.taskmanagement.authentication;

import java.sql.*;

import com.smartcliff.TaskManagementSystem.DbmsConnection;

public class UserAuthentication {

	public boolean loginemp(String mail, String password) {
		boolean isValidUser = false;
		try {
			Connection connection = DbmsConnection.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("SELECT name FROM employee WHERE email=? AND password=?");
			stmt.setString(1, mail);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				isValidUser = true;
				System.out.println("Welcome, " + rs.getString("name") + "!"); // Print welcome message with user's name
			} else {
				System.out.println("Invalid email or password. Please try again."); // Print invalid message
			}
			rs.close();
			stmt.close();
			connection.close(); // Close connection, statement, and result set
		} catch (SQLException e) {
			System.out.println("An error occurred: " + e.getMessage()); // Print error message
		}
		return isValidUser;
	}

	public static int manager(String mail) {
		int id = 0;
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("SELECT emp_id FROM employee WHERE email=?");
			stmt.setString(1, mail);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				id = rs.getInt("emp_id");
			}

		} catch (SQLException e) {

			e.getMessage();
		}
		return id;

	}

	public boolean loginman(String mail, String password) {
		boolean isValidUser = false;

		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("SELECT * FROM employee WHERE email=? AND password=?");
			stmt.setString(1, mail);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {

				isValidUser = true;
				System.out.println("Welcome, " + rs.getString("name") + "!"); // Print welcome message with user's name

			} else {
				System.out.println("Invalid email or password. Please try again."); // Print invalid message
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("An error occurred: " + e.getMessage()); // Print error message
		}
		return isValidUser;
	}

	public boolean loginadmin(String mail, String password) {
		boolean isValidUser = false;
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("SELECT name FROM admin WHERE email=? AND admin_password=?");
			stmt.setString(1, mail);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				isValidUser = true;
				System.out.println("Welcome, " + rs.getString("name") + "!"); // Print welcome message with user's name
			} else {
				System.out.println("Invalid email or password. Please try again."); // Print invalid message
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("An error occurred: - " + e.getMessage()); // Print error message
		}
		return isValidUser;
	}
}
