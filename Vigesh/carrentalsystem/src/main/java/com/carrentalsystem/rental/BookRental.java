package com.carrentalsystem.rental;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.Duration;
import java.time.LocalDate;

import com.gotrip.CarRentalSystem.DbConnection;

import user.Account;
import user.LoggedInUser;

public class BookRental {
	
	public static Connection con = DbConnection.getConnection();
	//book the car by user
	public static boolean bookCars(int carId) throws SQLException {
		
   
	String name=LoggedInUser.getInstance().getUserName();
   	
	  Statement st=con.createStatement();
	  //int user_id=
	  ResultSet resultSet = st.executeQuery("SELECT user_id  FROM users where username='"+name+"'");
	  int userId = 0;
   	  if(resultSet.next())
   		  userId = resultSet.getInt("user_id");
   	//Statement st=con.createStatement();
	  ResultSet rs = st.executeQuery("SELECT bookseq.nextval FROM dual");
	  int bookid = 0; 
     if (rs.next()) {
        bookid = rs.getInt(1); 
     }
     System.out.println("Enter a Start Date:");
     Scanner sc= new Scanner(System.in);
//     sc.nextLine(); 
//     System.out.println("Enter car ID:");
//     int carid = sc.nextInt();
//     sc.nextLine(); 

     System.out.println("Enter start date and time (YYYY-MM-DD HH:mm):");
     String startDateTimeStr = sc.nextLine();
     LocalDateTime startDateTime = LocalDateTime.parse(startDateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

     System.out.println("Enter end date and time (YYYY-MM-DD HH:mm):");
     String endDateTimeStr = sc.nextLine();
     LocalDateTime endDateTime = LocalDateTime.parse(endDateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    // Timestamp timestamp = Timestamp.valueOf(startDateTimeStr);
     Duration duration = Duration.between(startDateTime, endDateTime);
     long hours = duration.toHours();
     double rentalRate = 0;
      resultSet = st.executeQuery("SELECT rental_rate FROM car WHERE car_id=" + carId);
     double amount = 0;
     if (resultSet.next()) {
         amount = resultSet.getDouble(1); // Get the value from the first column
     }

    // double rentalRate = getRentalRate(connection, carId);
     double totalAmount = amount * hours;
     if(totalAmount>0)
     System.out.println("Total amount to be paid: RS." + totalAmount);
     
//     int r= st.executeUpdate("INSERT INTO booking(booking_id, user_id,start_date,end_date,total_amount,rental_status,car_id) " +
//   	      "VALUES (" + bookid + ", " + userId + ", '" + startDateTimeStr + "', '" + endDateTimeStr + "', " + totalAmount + "," +"Booked" + "," + carId + "  "+")"
//   	    		  );
   if( totalAmount>0 && processPayment(totalAmount,bookid)&&(LocalDateTime.now().isEqual(startDateTime)||LocalDateTime.now().isBefore(startDateTime) )&&startDateTime.isBefore(endDateTime)) {
     String query = "INSERT INTO booking (booking_id, user_id,start_date,end_date, total_amount, rental_status, car_id) " +
             "VALUES (?, ?, ?, ?, ?,?,?)";
PreparedStatement statement = con.prepareStatement(query);
  statement.setInt(1, bookid);
  statement.setInt(2, userId);
// statement.setString(3, "TO_TIMESTAMP('"+startDateTime+"', 'YYYY-MM-DD HH24:MI)"); // Assuming startDateTimeStr and endDateTimeStr are properly formatted date/time strings
// statement.setString(4, "TO_TIMESTAMP('"+endDateTime+"', 'YYYY-MM-DD HH24:MI')");
  statement.setDouble(5, totalAmount);
  statement.setString(6, "Booked");
  statement.setInt(7, carId);
  statement.setTimestamp(3, java.sql.Timestamp.valueOf(startDateTime));
  statement.setTimestamp(4, java.sql.Timestamp.valueOf(endDateTime));
  
  int r = statement.executeUpdate();
   
  if (r > 0) {
      System.out.println("Booked successfully!");
  } 
   }else {
	      System.out.println(" booking Failed!");
   }
		return true;
	}
	//for payment process and inserting the payment details in payment table
	 public static boolean processPayment(double totalAmount ,int bookid) throws SQLException {
	        Scanner sc = new Scanner(System.in);
	        System.out.println("Total amount to be paid: RS." + totalAmount);
	        System.out.println("Enter payment method (e.g., credit card, debit card, cash):");
	        String paymentMethod = sc.nextLine();
	        System.out.println("Enter Amount:");
	        double amountPaid=sc.nextDouble();
	        
	       
	  
	        if (amountPaid == totalAmount) {
	            try {
	                // Get the next payment ID from sequence
	                Statement st = con.createStatement();
	                ResultSet rs = st.executeQuery("SELECT paymentseq.nextval FROM dual");
	                int paymentId = 0;
	                if (rs.next()) {
	                    paymentId = rs.getInt(1);
	                }

	                // Get the current date
	                LocalDate currentDate = LocalDate.now();

	                // Convert LocalDate to java.sql.Date
	                Date sqlDate = Date.valueOf(currentDate);

	                // SQL query to insert payment details into the payment table
	                String query = "INSERT INTO payment (payment_id, booking_id, total_amount, payment_date, payment_method, payment_status) " +
	                               "VALUES (?, ?, ?, ?, ?, ?)";
	                
	                // Create a PreparedStatement for executing the query
	                PreparedStatement statement = con.prepareStatement(query);
	                statement.setInt(1, paymentId);
	                statement.setInt(2, bookid);
	                statement.setDouble(3, totalAmount);
	                statement.setDate(4, sqlDate);
	                statement.setString(5, paymentMethod); // Assuming paymentMethod is the payment method received from the user
	                statement.setString(6, "Booked"); // Assuming the payment status is always "Booked" for successful payments

	                // Execute the query
	                int rowsInserted = statement.executeUpdate();
	                
	                if (rowsInserted > 0) {
	                    System.out.println("Payment successful!");
	                    return true;
	                } else {
	                    System.out.println("Payment Failed");
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } else {
	            System.out.println("Payment failed. Please try again.");
	        }
	       
	  

	        return false;
	    }
//	public void cancelBooking() throws SQLException {
//		  System.out.println("Are You Really Want To Cancel Booking (Yes-1 No-2 ) 3.GoBack");
//		  Statement st= con.createStatement();
//		 // int r= st.executeUpdate("UPDATE car SET " + columnName + " = '" + newValue + "' WHERE CAR_ID = " + carId);
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
