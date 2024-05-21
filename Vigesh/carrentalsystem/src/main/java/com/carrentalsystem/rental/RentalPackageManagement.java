package com.carrentalsystem.rental;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import com.carrentalsystem.car.Car;
import com.carrentalsystem.exception.NumberException;
import com.carrentalsystem.exception.Validate;
import com.gotrip.CarRentalSystem.DbConnection;

import user.LoggedInUser;

public class RentalPackageManagement {
	
	static Connection con = DbConnection.getConnection();
	Scanner sc= new Scanner(System.in);
	static BufferedReader scanner=new BufferedReader(new InputStreamReader(System.in));
	//adding rental packages 
    public void addrental() throws SQLException {
    	 System.out.println("Enter Duration:");
	        String Duration = sc.nextLine();
	        System.out.println("Enter the car Type:");
	        String carType = sc.nextLine();
	        System.out.println("Enter the Amount:");
	        Double amount = sc.nextDouble();
	        RentalPackage rent=new RentalPackage(Duration,carType,amount);
	        Statement st=con.createStatement();
	     ResultSet rs = st.executeQuery("SELECT seq_packid.nextval FROM dual");
	     int packageId = 0; 
	     if (rs.next()) {
	         packageId = rs.getInt(1); 
	     }
	     int r = st.executeUpdate("INSERT INTO rental_package (duration, car_type, amount, packageid) " +
	             "VALUES ('" + rent.getDuration() + "', '" + rent.getCarType() + "'," + rent.getAmount() + "," + packageId + ")");
                 System.out.println("added successfully");
    }
    
    //--------------------------------------------------------------------//
    //updating the values in rental packages
    public void updaterental() throws Exception {
    	Statement st=con.createStatement();
    	ResultSet rs = st.executeQuery("SELECT * FROM rental_package");
    	System.out.println("+----------+----------+----------+----------+");
    	System.out.println("| PACKAGEID | CAR_TYPE |  AMOUNT  | DURATION|");
    	System.out.println("+----------+----------+----------+----------+");
    	while (rs.next()) {
    	    String duration = rs.getString("duration");
    	    String carType = rs.getString("car_type");
    	    int amount = rs.getInt("amount");
    	    int packageId = rs.getInt("packageid");
    	    System.out.printf("|%8d | %-8s | %8d |  %-8s |%n",packageId , carType, amount, duration);
    	}
    	System.out.println("+----------+----------+----------+----------+");
        System.out.println("Enter a Package Id To Update:");
         int PackageId=sc.nextInt();    
         
         System.out.println("Choose the option to update:");
         System.out.println("1. DURATION");
         System.out.println("2. CAR_TYPE");
         System.out.println("3. AMOUNT");
         
         int option = sc.nextInt();
         String columnName = null;

         switch (option) {
             case 1:
                 columnName = "DURATION";
                 break;
             case 2:
                 columnName = "CAR_TYPE";
                 break;
             case 3:
                 columnName = "AMOUNT";
                 break;
             
             default:
                 System.out.println("Invalid option.");
                 updaterental();
                 break;
                // return true;
         }
         
         System.out.println("Enter New Value to Insert:");
         sc.nextLine();
         String newValue=sc.nextLine();
          st=con.createStatement();
	      int r= st.executeUpdate("UPDATE rental_package SET " + columnName + " = '" + newValue + "' WHERE packageid = " + PackageId);
		

        System.out.println("Updated Successfully");
    	
    }
    //displaying the rental packages
    public void viewrental() throws SQLException {
    	Statement st=con.createStatement();
    	ResultSet rs = st.executeQuery("SELECT * FROM rental_package");
    	System.out.println("+----------+----------+----------+----------+");
    	System.out.println("| PACKAGEID | CAR |  AMOUNT  | DURATION|");
    	System.out.println("+----------+----------+----------+----------+");
    	while (rs.next()) {
    	    String duration = rs.getString("duration");
    	    String carType = rs.getString("car_type");
    	    int amount = rs.getInt("amount");
    	    int packageId = rs.getInt("packageid");
    	    System.out.printf("| %8d | %-8s | %8d | %-8s |%n",packageId , carType, amount, duration);
    	}
    	System.out.println("+----------+----------+----------+----------+");
    }
    //delete the rental packages 
    public void deleterental() throws SQLException, NumberException, IOException {
    	Statement st=con.createStatement();
    	ResultSet rs = st.executeQuery("SELECT * FROM rental_package");
    	System.out.println("+----------+----------+----------+----------+");
    	System.out.println("| PACKAGEID | CAR_TYPE |  AMOUNT  | DURATION|");
    	System.out.println("+----------+----------+----------+----------+");
    	while (rs.next()) {
    	    String duration = rs.getString("duration");
    	    String carType = rs.getString("car_type");
    	    int amount = rs.getInt("amount");
    	    int packageId = rs.getInt("packageid");
    	    System.out.printf("| %8d | %-8s | %8d | %-8s |%n",packageId , carType, amount, duration);
    	}
    	System.out.println("+----------+----------+----------+----------+");
    	     System.out.println("Enter Id for delete");
		     int packageid= Validate.ValidateNumber(scanner.readLine());;;
		     int set=st.executeUpdate("delete from rental_package where packageid="+packageid+"  ");
		     if(set>0)
		     System.out.println("Deleted Successfully");
		     else
		     System.out.println("Enter Valid Id");
    	
    }
    public void bookrental() throws SQLException {
    	System.out.println("Choose Package: ");
          System.out.println("+----------+----------+----------+----------+");
      	System.out.println("| PACKAGEID | CAR |  AMOUNT  | DURATION|");
      	System.out.println("+----------+----------+----------+----------+");
      	Statement st=con.createStatement();
    	ResultSet rs = st.executeQuery("SELECT * FROM rental_package");
    	
      	while (rs.next()) {
      	    String duration = rs.getString("duration");
      	    String carType = rs.getString("car_type");
      	    int amount = rs.getInt("amount");
      	    int packageId = rs.getInt("packageid");
      	    System.out.printf("| %8d | %-8s | %8d | %-8s |%n",packageId , carType, amount, duration);
      	}
      	System.out.println("+----------+----------+----------+----------+");
      	System.out.println("Enter Package Id:");
      	int packageId=sc.nextInt();
      	System.out.println("Enter Start Date and Time: yyyy-MM-dd HH:mm");
      	sc.nextLine();
      	String startDate=sc.nextLine();
      	 String carNameQuery="select car_type, amount,duration from rental_package where packageid ="+ packageId+" ";
      	 //System.out.println("suc1");
      	 ResultSet resultSet = st.executeQuery(carNameQuery);
       	String carName= null;
       	double amt = 0;
       	String duration=null;
       	if(resultSet.next()) {
       		carName=resultSet.getString("car_type");
       		amt = resultSet.getDouble("amount");
       		duration=resultSet.getString("duration");
       	}
        //System.out.println(carName);
        String carIdQuery="select car_id from car where car_name ='"+carName+"' and availability = 'Available'";
         st=con.createStatement();
        // System.out.println("suc3");
  	  //int user_id=
  	   resultSet = st.executeQuery(carIdQuery);
      	int carId=0;
      	while(resultSet.next()) {
      		carId=resultSet.getInt("car_id");
      	}
      	//System.out.println("suc4");
         bookCars(carId,packageId,startDate,amt,duration);
      	
      	
    	
    }
    public static boolean bookCars(int carId,int packageId,String startDate,double totalAmount,String duration) throws SQLException {
		
    	   
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
         
        

         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
         LocalDateTime timestamp = LocalDateTime.parse(startDate, formatter);

         // Add one hour
         LocalDateTime later =null;
         if(duration.equals("HOURLY"))
        	  later = timestamp.plusHours(1);
          if((duration.equals("WEEKLY")))
        		 later = timestamp.plusWeeks(1);
          if((duration.equals("MONTHLY")))
        	     later=timestamp.plusMonths(1);

         // Convert back to a string
         String endDateTimeStr = later.format(formatter);

         
         LocalDateTime startDateTime = LocalDateTime.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

//         System.out.println("Enter end date and time (YYYY-MM-DD HH:mm):");
//         String endDateTimeStr = sc.nextLine();
         LocalDateTime endDateTime = LocalDateTime.parse(endDateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        // Timestamp timestamp = Timestamp.valueOf(startDateTimeStr);
//         Duration duration = Duration.between(startDateTime, endDateTime);
//         long hours = duration.toHours();
        
        

        // double rentalRate = getRentalRate(connection, carId);
         
         
         
//         int r= st.executeUpdate("INSERT INTO booking(booking_id, user_id,start_date,end_date,total_amount,rental_status,car_id) " +
//       	      "VALUES (" + bookid + ", " + userId + ", '" + startDateTimeStr + "', '" + endDateTimeStr + "', " + totalAmount + "," +"Booked" + "," + carId + "  "+")"
//       	    		  );
       if( totalAmount>0 && processPayment(totalAmount,bookid)&&(LocalDateTime.now().isEqual(startDateTime)||LocalDateTime.now().isBefore(startDateTime) )&&startDateTime.isBefore(endDateTime)) {
         String query = "INSERT INTO booking (booking_id, user_id,start_date,end_date, total_amount, rental_status, car_id,package_id) " +
                 "VALUES (?, ?, ?, ?, ?,?,?,?)";
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
      statement.setInt(8, packageId);
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
    	 public void viewBooking() {
    		 String sql = "SELECT START_DATE, END_DATE, TOTAL_AMOUNT, RENTAL_STATUS, CAR_NAME " +
                     "FROM booking b " +
                     "LEFT JOIN car c ON b.CAR_ID = c.CAR_ID " +
                     "WHERE b.USER_ID = (SELECT USER_ID FROM users WHERE USERNAME = ?)";

    		 PreparedStatement statement;
			try {
				//statement = con.prepareStatement(sql);
				String user=LoggedInUser.getInstance().getUserName();
				 PreparedStatement stat = con.prepareStatement(sql);
		            stat.setString(1, user);
		            ResultSet resultSet = stat.executeQuery();

		            // Print table header
		            System.out.printf("%-20s %-25s %-25s %-10s %-15s %-15s%n", "User Name", "Start Date", "End Date", "Amount", "Status", "Car Name");
		            System.out.println("-----------------------------------------------------------------------------------------------------------");

		            // Process the result set and print each row
		            while (resultSet.next()) {
		                String startDate = resultSet.getString("START_DATE");
		                String endDate = resultSet.getString("END_DATE");
		                int totalAmount = resultSet.getInt("TOTAL_AMOUNT");
		                String rentalStatus = resultSet.getString("RENTAL_STATUS");
		                String carName = resultSet.getString("CAR_NAME");

		                System.out.printf("%-20s %-25s %-25s %-10d %-15s %-15s%n", user, startDate, endDate, totalAmount, rentalStatus, carName != null ? carName : "N/A");
		            }
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				// TODO Auto-generated catch block
				
			}
    		 
             
    	 }
    
}
