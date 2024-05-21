package user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import com.carrentalsystem.exception.NumberException;
import com.carrentalsystem.exception.Validate;
import com.gotrip.CarRentalSystem.DbConnection;

public class UserManagement {
	static BufferedReader scanner=new BufferedReader(new InputStreamReader(System.in));
	//Altering the customer details
    public void manageuser() throws SQLException, NumberException, IOException {
    	Scanner sc= new Scanner(System.in);
    	Connection con = DbConnection.getConnection();
    	Statement st= con.createStatement();
    	ResultSet rs = st.executeQuery("SELECT USER_ID, USERNAME FROM users");
    	System.out.println("+---------+----------------+");
    	System.out.println("| USER_ID |    USERNAME    |");
    	System.out.println("+---------+----------------+");
    	while (rs.next()) {
    	    int userId = rs.getInt("USER_ID");
    	    String username = rs.getString("USERNAME");
    	    System.out.printf("| %-7d | %-14s |%n", userId, username);
    	}
    	System.out.println("+---------+----------------+");
    	System.out.println("Enter Id To Modify:");
    	//Scanner sc= new Scanner(System.in);
    	
    	int userId=Validate.ValidateNumber(scanner.readLine());;
    	String columnName="account_status";
    	System.out.println("Enter Account Status: (Active/InActive/Blocked)");
    	String newValue=sc.nextLine();
    	int r= st.executeUpdate("UPDATE users SET " + columnName + " = '" + newValue + "' WHERE user_id = " + userId);
    	System.out.println("Updated Successfully");
    	
    }
   
}
