package com.jobportal.listings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

import com.jdbcservice.JdbcConnection;
import com.jobportal.users.JobSeeker;

public class Application {
	static Scanner sc = new Scanner(System.in);
	public Application(int applicationId, int jobId, int jobSeekerId, String status, Date applicationDate) {
		
		super();
		this.applicationId = applicationId;
		this.jobId = jobId;
		this.jobSeekerId = jobSeekerId;
		this.status = status;
		this.applicationDate = applicationDate;
	}

	private int applicationId;
	private int jobId;
	private int jobSeekerId;
	private String status;
	private Date applicationDate;
	

	

	// Constructors
	public Application() {
	}

	public Application(int applicationId, int jobId, int jobSeekerId, Date applicationDate, String status) {
		this.applicationId = applicationId;
		this.jobId = jobId;
		this.jobSeekerId = jobSeekerId;
		this.applicationDate = applicationDate;

		this.status = status;
	}

	// Getters and setters
	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public int getJobSeekerId() {
		return jobSeekerId;
	}

	public void setJobSeekerId(int jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static void viewApplicationStatus(int id) {
	    Connection con = null;
	    PreparedStatement statement = null;

	    try {
	        con = JdbcConnection.connectdatabase();
	        String sql = "SELECT ja.APPLICATION_ID, ja.JOB_ID, ja.status, j.job_title, e.COMPANYNAME " +
	                     "FROM job_applications ja " +
	                     "JOIN jobs j ON ja.JOB_ID = j.job_id " +
	                     "JOIN employers e ON j.employer_id = e.id " +
	                     "WHERE ja.JOB_SEEKER_ID = ?";
	        
	        statement = con.prepareStatement(sql);
	        statement.setInt(1, id);

	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                do {
	                    int aId = resultSet.getInt("APPLICATION_ID");
	                    int jobId = resultSet.getInt("JOB_ID");
	                    String status = resultSet.getString("status");
	                    String jobTitle = resultSet.getString("job_title");
	                    String companyName = resultSet.getString("COMPANYNAME");
	                    System.out.println("***************************************");
	                    System.out.println("Job Title: " + jobTitle);
	                    System.out.println("Company Name: " + companyName);
	                    System.out.println("Job ID: " + jobId);
	                    System.out.println("Application ID: " + aId);
	                    System.out.println("Application Status: " + status);
	                   
	                } while (resultSet.next());
	            } else {
	                System.out.println("No applications found for job seeker ID: " + id);
	            }
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (statement != null) {
	                statement.close();
	            }
	            if (con != null) {
	                con.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    } 
	}


	public static void updateApplicationStatu(int applicationId, int jobId) throws ClassNotFoundException {
	    Connection con = null;
	    PreparedStatement statement = null;
	    String sql = "";

	    try {
	        // Establish the database connection
	        con = JdbcConnection.connectdatabase();

	        // Prompt the user to select the status
	        System.out.println("1. Rejected");
	        System.out.println("2. Waiting");
	        System.out.println("3. Selected");
	        System.out.println("Enter the option:");
	        int option = sc.nextInt();

	        // Determine the SQL statement based on the selected option
	        switch (option) {
	            case 1:
	                sql = "UPDATE job_applications SET status = 'Rejected' WHERE APPLICATION_ID = ? AND JOB_ID = ?";
	                break;
	            case 2:
	                sql = "UPDATE job_applications SET status = 'Waiting' WHERE APPLICATION_ID = ? AND JOB_ID = ?";
	                break;
	            case 3:
	                sql = "UPDATE job_applications SET status = 'Selected' WHERE APPLICATION_ID = ? AND JOB_ID = ?";
	                break;
	            default:
	                throw new IllegalArgumentException("Invalid option");
	        }

	        // Prepare and execute the SQL statement
	        statement = con.prepareStatement(sql);
	        statement.setInt(1, applicationId);
	        statement.setInt(2, jobId);
	        int rowsUpdated = statement.executeUpdate();

	        // Check if any rows were updated
	        if (rowsUpdated > 0) {
	            System.out.println("Application status updated successfully.");
	        } else {
	            System.out.println("No application found for the provided application ID and job ID.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Print the exception stack trace
	    } finally {
	        // Close resources in a finally block to ensure they are released
	        try {
	            if (statement != null) {
	                statement.close();
	            }
	            if (con != null) {
	                con.close();
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace(); // Print the exception stack trace
	        }
	    }
	}

	

	    public static void updateApplicationStatus(int applicationId, int jobId) throws ClassNotFoundException {
	        Connection con = null;
	        PreparedStatement statement = null;
	        String sql = "";

	        try {
	            con = JdbcConnection.connectdatabase();

	            System.out.println("1. Rejected");
	            System.out.println("2. Waiting");
	            System.out.println("3. Selected");
	            System.out.println("Enter the option:");
	            int option = sc.nextInt();

	            switch (option) {
	                case 1:
	                    sql = "UPDATE job_applications SET status = 'Rejected' WHERE APPLICATION_ID = ? AND JOB_ID = ?";
	                    break;
	                case 2:
	                    sql = "UPDATE job_applications SET status = 'Waiting' WHERE APPLICATION_ID = ? AND JOB_ID = ?";
	                    break;
	                case 3:
	                    sql = "UPDATE job_applications SET status = 'Selected' WHERE APPLICATION_ID = ? AND JOB_ID = ?";
	                    break;
	                default:
	                    throw new IllegalArgumentException("Invalid option");
	            }

	            statement = con.prepareStatement(sql);
	            statement.setInt(1, applicationId);
	            statement.setInt(2, jobId);

	            int rowsUpdated = statement.executeUpdate();

	            if (rowsUpdated > 0) {
	                System.out.println("Application status updated successfully.");
	            } else {
	                System.out.println("No application found for the provided application ID and job ID.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (statement != null) {
	                    statement.close();
	                }
	                if (con != null) {
	                    con.close();
	                }
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
	    }

	  


	// toString method to display Application information
	@Override
	public String toString() {
		return "Application{" + "applicationId=" + applicationId + ", jobId=" + jobId + ", jobSeekerId=" + jobSeekerId
				+ ", applicationDate=" + applicationDate + ", resume= [byte array]" +

				", status='" + status + '\'' + '}';
	}
}
