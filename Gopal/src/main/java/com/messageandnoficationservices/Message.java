package com.messageandnoficationservices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Scanner;

import com.jdbcservice.JdbcConnection;
import com.jobportal.functionality.Main;
import com.jobportal.users.Employer;
import com.jobportal.users.JobSeeker;
import com.jobportal.users.UserType;
import com.userdefiendexception.CheckUserNameException;
import com.userdefiendexception.CompanyNameValidationException;
import com.userdefiendexception.OptionException;
import com.userdefiendexception.Validation;
import com.userdefiendexception.checkUserName;

public class Message {
	private int id;
    private int senderId;
    private int receiverId;
    private String content;
    
     
	static Scanner sc = new Scanner(System.in);
	
    public Message(int id, int senderId, int receiverId, String content) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
    }
   
    public Message() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
        return id;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public String getContent() {
        return content;
    }
    
   
    		
    		
    	
    	
  public void messages(Employer em) throws ClassNotFoundException {
	  boolean flag =true;
	  do {
		  System.out.println("+------------------------------------+");
		  System.out.println("|            Message Menu            |");
		  System.out.println("+------------------------------------+");
		  System.out.println("| 1. NotificationSend                |");
		  System.out.println("| 2. Back                            |");
		  System.out.println("| 3. Exit                            |");
		  System.out.println("+------------------------------------+");

		  
		  int option = 0;

	        while (true) {
	            System.out.print("Enter menu option number: ");
	            String input = sc.next();

	            try {
	                option = Validation.validateOption(input);
	                break;
	            } catch (OptionException e) {
	                System.out.println(e.getMessage());
	            }
	        }
		  
		  switch(option) {
		  case 1: {
			  
			 
			  
			  System.out.println("Enter reciver id :");
			  int receiverId =sc.nextInt();
			  sc.nextLine();
			  System.out.println("Enter the message :");
              String content =sc.nextLine();
            
              sendEmployerToJobSeekerNotification(em.getId(),receiverId, content);
			  break;
			  
		  }
		  
		  case 2: {
			  
			 
			new Employer().emoloyerMenu(em);
			  break;
		  }
		  case 3: {
			  System.out.println("Thank you for using Job Portal!  Have a great day!");
			  System.exit(0);
			  break;
		  }
		  }
		  
		  
		  
	  }while(flag);
	  
	  
	  
  }
  public void message(JobSeeker seeker) throws ClassNotFoundException, SQLException, OptionException, CheckUserNameException, CompanyNameValidationException {
	  boolean flag =true;
	  do {
		  System.out.println("+------------------------------------+");
		  System.out.println("|            Message Menu            |");
		  System.out.println("+------------------------------------+");
		  System.out.println("| 1. Receive notification            |");
		  System.out.println("| 2. Back                            |");
		  System.out.println("| 3. Exit                            |");
		  System.out.println("+------------------------------------+");

		  
		  
		  int option = 0;

	        while (true) {
	            System.out.print("Enter menu option number: ");
	            String input = sc.next();

	            try {
	                option = Validation.validateOption(input);
	                break;
	            } catch (OptionException e) {
	                System.out.println(e.getMessage());
	            }
	        }
		  switch(option) {
		  
		  case 1: {
			  
			  receiveNotificationsForJobSeeker(seeker);
			
			  break;
			  
		  }
		  case 2: {
			  
		     new JobSeeker().jobSeekerMenu(seeker);
			  break;
		  }
		  case 3: {
			  System.out.println("Thank you for using Job Portal!  Have a great day!");
			  System.exit(0);
			  break;
		  }
		 
		  
		  }
		  
	  }while(flag);
	  
	  
	  
  } 	
    
    
    

  public static void sendEmployerToJobSeekerNotification(int employerId, int jobSeekerId, String notificationContent) {
	    Connection con = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        // Establish database connection
	        con = JdbcConnection.connectdatabase();

	        // SQL statement to insert notification
	        String sql = "INSERT INTO notifications (id, employer_id, job_seeker_id, notification_content) " +
	                "VALUES (not_seq.NEXTVAL, ?, ?, ?)";
	        // Create prepared statement
	        preparedStatement = con.prepareStatement(sql);
	        preparedStatement.setInt(1, employerId);
	        preparedStatement.setInt(2, jobSeekerId);
	        preparedStatement.setString(3, notificationContent);

	        // Execute the insert statement
	        int rowsAffected = preparedStatement.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Employer to job seeker notification sent successfully.");
	        } else {
	            System.out.println("Failed to send employer to job seeker notification.");
	        }
	    } catch (ClassNotFoundException | SQLException e) {
	        System.err.println("Error sending employer to job seeker notification: " + e.getMessage());
	    } finally {
	        // Close resources in the finally block
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (con != null) {
	                con.close();
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}

  public void receiveNotificationsForJobSeeker(JobSeeker seeker) {
      Connection con = null;
      PreparedStatement preparedStatement = null;
      ResultSet resultSet = null;

      try {
          // Establish database connection
          con = JdbcConnection.connectdatabase();

          // SQL statement to retrieve notifications for the job seeker
          String sql = "SELECT * FROM notifications WHERE job_seeker_id = ?";

          // Create prepared statement
          preparedStatement = con.prepareStatement(sql);
          preparedStatement.setInt(1, seeker.getJobSeekerId());

          // Execute the query
          resultSet = preparedStatement.executeQuery();

          // Check if any notifications are found
          boolean hasNotifications = false;
          while (resultSet.next()) {
              hasNotifications = true;
              int notificationId = resultSet.getInt("id");
              int employerId = resultSet.getInt("employer_id");
              String notificationContent = resultSet.getString("notification_content");

              // Print or process the notification data
              System.out.println("Notification ID: " + notificationId);
              System.out.println("Employer ID: " + employerId);
              System.out.println("Notification Content: " + notificationContent);
              System.out.println("----------------------");
          }

          // If no notifications were found, print a message
          if (!hasNotifications) {
              System.out.println("No notifications found for job seeker ID: " + seeker.getJobSeekerId());
          }
      } catch (ClassNotFoundException | SQLException e) {
          System.err.println("Error receiving notifications for job seeker: " + e.getMessage());
      } finally {
          // Close resources in the finally block
          try {
              if (resultSet != null) {
                  resultSet.close();
              }
              if (preparedStatement != null) {
                  preparedStatement.close();
              }
              if (con != null) {
                  con.close();
              }
          } catch (SQLException ex) {
              ex.printStackTrace();
          }
      }
  }




  
}
	

	

