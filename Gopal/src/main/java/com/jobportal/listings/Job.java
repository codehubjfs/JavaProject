package com.jobportal.listings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.jdbcservice.JdbcConnection;
import com.jobportal.users.Employer;
import com.userdefiendexception.CharachterSequenceException;
import com.userdefiendexception.JobIdAlreadyExistsException;
import com.userdefiendexception.OptionException;
import com.userdefiendexception.Validation;

public class Job {
	private int jobId;
	private int employerId;
	private String jobTitle;
	private String jobDescription;
	private String location;
	private String requiredSkills;
	private String jobType;
	private String experienceLevel;
	private String applicationDeadline;
	private int numberOfOpenings;
	private JobStatus jobstatus;
	static Scanner sc =new Scanner(System.in);

	// Constructors

	public Job( int employerId, String jobTitle, String jobDescription, String location,
			String requiredSkills, String jobType, String experienceLevel, String applicationDeadline2,
			int numberOfOpenings) {
		
		this.employerId = employerId;
		this.jobTitle = jobTitle;
		this.jobDescription = jobDescription;
		this.location = location;
		this.requiredSkills = requiredSkills;
		this.jobType = jobType;
		this.experienceLevel = experienceLevel;
		this.applicationDeadline = applicationDeadline2;
		this.numberOfOpenings = numberOfOpenings;
	}

	public Job() {
		// TODO Auto-generated constructor stub
	}

	

	// Getters and setters
	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public int getEmployerId() {
		return employerId;
	}

	public void setEmployerId(int employerId) {
		this.employerId = employerId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRequiredSkills() {
		return requiredSkills;
	}

	public void setRequiredSkills(String requiredSkills) {
		this.requiredSkills = requiredSkills;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getExperienceLevel() {
		return experienceLevel;
	}

	public void setExperienceLevel(String experienceLevel) {
		this.experienceLevel = experienceLevel;
	}

	public String getApplicationDeadline() {
		return applicationDeadline;
	}

	public void setApplicationDeadline(String applicationDeadline) {
		this.applicationDeadline = applicationDeadline;
	}

	public int getNumberOfOpenings() {
		return numberOfOpenings;
	}

	public void setNumberOfOpenings(int numberOfOpenings) {
		this.numberOfOpenings = numberOfOpenings;
	}
	public void jobManagement(Employer emp)
			throws ClassNotFoundException, OptionException, JobIdAlreadyExistsException {
		try {
			Connection con = JdbcConnection.connectdatabase();
			boolean flags = true;
			do {

				System.out.println("+------------------------------------+");
				System.out.println("|              Job Menu              |");
				System.out.println("+------------------------------------+");
				System.out.println("| 1. Post New Jobs                   |");
				System.out.println("| 2. View All Jobs                   |");
				System.out.println("| 3. Update Jobs                     |");
				System.out.println("| 4. Change Job status               |");
				System.out.println("| 5. Back                            |");
				System.out.println("| 6. Exit                            |");
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
				switch (option) {
				case 1: {

					System.out.println("**********************************************");

					System.out.println("post your job ");

					System.out.println("**********************************************");
		
					sc.nextLine();
					String title;
				
					while(true) {
				    System.out.println("Enter The Job Title :");
					 title = sc.nextLine();
					 try {
						if(!Validation.ValidateCharacterSequences(title)) {
							 continue;
						 }
						 else {
							 break;
						 }
					} catch (CharachterSequenceException e) {
						// TODO Auto-generated catch block
				       System.out.println(e.getMessage());
					}
					}
					
					String jobDescription;
					while(true) {
						System.out.println("Enter The JobDescription :");
					    jobDescription = sc.nextLine();
						 try {
							if(!Validation.ValidateCharacterSequences(jobDescription)) {
								 continue;
							 }
							 else {
								 break;
							 }
						} catch (CharachterSequenceException e) {
							// TODO Auto-generated catch block
					       System.out.println(e.getMessage());
						}
						}
				
					String location;
					
					while(true) {
						System.out.println("Enter The Location :");
					    location = sc.nextLine();
						 try {
							if(!Validation.ValidateCharacterSequences(location)) {
								 continue;
							 }
							 else {
								 break;
							 }
						} catch (CharachterSequenceException e) {
							// TODO Auto-generated catch block
					       System.out.println(e.getMessage());
						}
						}
				
					String requiredSkills;;
					while(true) {
						System.out.println("Enter The RequiredSkills :");
						requiredSkills = sc.nextLine();
						 try {
							if(!Validation.ValidateCharacterSequences(requiredSkills)) {
								 continue;
							 }
							 else {
								 break;
							 }
						} catch (CharachterSequenceException e) {
							// TODO Auto-generated catch block
					       System.out.println(e.getMessage());
						}
						}
					String jobType;
					while(true) {
						System.out.println("Enter The JobType :");
						jobType = sc.nextLine();
						 try {
							if(!Validation.ValidateCharacterSequences(jobType)) {
								 continue;
							 }
							 else {
								 break;
							 }
						} catch (CharachterSequenceException e) {
							// TODO Auto-generated catch block
					       System.out.println(e.getMessage());
						}
						}
					
					System.out.println("Enter The ExperienceLevel");
					String experienceLevel = sc.nextLine();

					String applicationDeadline;

					while (true) {
						System.out.println("applicationDeadline, Enter the date (dd-MM-yyyy) ");
						applicationDeadline = sc.nextLine();

						if (Validation.isValidDate(applicationDeadline)) {

							break;
						} else {
							System.out.println("Invalid date format. Please enter the date in the format dd-MM-yyyy.");
						}
					}


					System.out.println("NumberOfOpenings :");
					int numberOfOpenings = sc.nextInt();
                    int c =emp.getId();
					postNewJob(con, new Job(c, title, jobDescription, location, requiredSkills,
							jobType, experienceLevel, applicationDeadline, numberOfOpenings));

					break;
				}

				case 2: {

					System.out.println("View all the job :");
					viewJobs(con, emp.getId());
					break;

				}
				case 3: {
				updateJobInformation(emp.getId());
					break;
					

				}
				case 4: {

					System.out.println("Enter the job id :");
					int job_Id = sc.nextInt();
					updateJobStatus(job_Id);
					break;
				
				}
				case 5: {
					emp.emoloyerMenu(emp);

					break;
				}
				case 6: {
					System.out.println("Thank you for using Job Portal!  Have a great day!");
					System.exit(0);

				}
				default: {

					//System.out.println("Invalid choice. Please try again !");

				}

				}

			} while (flags);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	public static void postNewJob(Connection con, Job job) {
		try {
			// Use JDBC to insert the job information into the database
			String sql = "INSERT INTO jobs (job_id, employer_id, job_title, job_description, location, required_skills, job_type, experience_level, application_deadline, number_of_openings) VALUES (job_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement insertStatement = con.prepareStatement(sql);

			// Set the parameters for the INSERT statement
			insertStatement.setInt(1, job.getEmployerId());
			insertStatement.setString(2, job.getJobTitle());
			insertStatement.setString(3, job.getJobDescription());
			insertStatement.setString(4, job.getLocation());
			insertStatement.setString(5, job.getRequiredSkills());
			insertStatement.setString(6, job.getJobType());
			insertStatement.setString(7, job.getExperienceLevel());
			insertStatement.setString(8, job.getApplicationDeadline());
			insertStatement.setInt(9, job.getNumberOfOpenings());

			// Execute the INSERT statement
			int rowsInserted = insertStatement.executeUpdate();

			if (rowsInserted > 0) {
				// Retrieve the generated keys (job ID)
				ResultSet generatedKeys = insertStatement.getGeneratedKeys();
				if (generatedKeys.next()) {
					int jobId = generatedKeys.getInt(1);
					System.out.println("Job posted successfully. Job ID: " + jobId);
				} else {
					System.out.println("Failed to retrieve job ID.");
				}
			} else {
				System.out.println("Failed to post job.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean isJobIdExists(int job_id) throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean exists = false;
		try {
			con = JdbcConnection.connectdatabase();
			String query = "SELECT COUNT(*) AS count FROM jobs WHERE job_id = ?";
			statement = con.prepareStatement(query);
			statement.setInt(1, job_id);
			resultSet = statement.executeQuery();
			resultSet.next(); // Move cursor to first row
			int count = resultSet.getInt("count");
			exists = (count > 0);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exists;
	}

	public static void viewJobs(Connection con, int employee_id) {

		try {
			String sql = "SELECT * FROM jobs WHERE EMPLOYER_ID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, employee_id);
			ResultSet rs = statement.executeQuery();

			if (!rs.isBeforeFirst()) {
				System.out.println(" You Are No Post jobs");
			} else {
				while (rs.next()) {
					int jobId = rs.getInt("job_id");
					//int employerId = rs.getInt("employer_id");
					String jobTitle = rs.getString("job_title");
					String jobDescription = rs.getString("job_description");
					String location = rs.getString("location");
					String requiredSkills = rs.getString("required_skills");
					String jobType = rs.getString("job_type");
					String experienceLevel = rs.getString("experience_level");
					String applicationDeadline = rs.getString("application_deadline");
					int numberOfOpenings = rs.getInt("number_of_openings");
					String jobStatus =rs.getString("Job_status");
					System.out.println("------------------------------------");
					System.out.println("Job ID: " + jobId);
					//System.out.println("Employer ID: " + employerId);
					System.out.println("Job Title: " + jobTitle);
					System.out.println("Description: " + jobDescription);
					System.out.println("Location: " + location);
					System.out.println("Required Skills: " + requiredSkills);
					System.out.println("Job Type: " + jobType);
					System.out.println("Experience Level: " + experienceLevel);
					System.out.println("Application Deadline: " + applicationDeadline);
					System.out.println("Number of Openings: " + numberOfOpenings);
					System.out.println("Job Status : "+jobStatus);
					System.out.println("------------------------------------");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); // or handle the exception as needed
		}
	}
	public static void updateJobInformation(int employee_id) throws SQLException {
		try {
			Connection con = JdbcConnection.connectdatabase();

			boolean lap = true;
			do {
				System.out.println("╔═══════════════════════════════╗");
				System.out.println("║   Job Modification Menu       ║");
				System.out.println("╠═══════════════════════════════╣");
				System.out.println("║ 1. Update job title           ║");
				System.out.println("║ 2. Update job description     ║");
				System.out.println("║ 3. Update job location        ║");
				System.out.println("║ 4. Update required skills     ║");
				System.out.println("║ 5. Update job type            ║");
				System.out.println("║ 6. Update experience level    ║");
				System.out.println("║ 7. Update application deadline║");
				System.out.println("║ 8. Update number of openings  ║");
				System.out.println("║ 9. Update all job information ║");
				System.out.println("║ 10.Back                       ║");
				System.out.println("╚═══════════════════════════════╝");
                System.out.println("Enter The Option :");
				int n = sc.nextInt();
				int job_id;
				switch (n) {
				case 1: {

					System.out.println("Enter the job_Id");
					job_id = sc.nextInt();
					sc.nextLine();
					System.out.println("Enter the updated job title");
					String title = sc.nextLine();
					updateJobTitle(con, job_id, title, employee_id);
					break;
				}
				case 2: {
					System.out.println("Enter the job_Id");
					job_id = sc.nextInt();
					sc.nextLine();
					System.out.println("Enter the updated job jobDescription");
					String jobDescription = sc.nextLine();
					updatejobDescription(con, job_id, jobDescription, employee_id);
					break;
				}
				case 3: {
					System.out.println("Enter the job_Id");
					job_id = sc.nextInt();
					sc.nextLine();
					System.out.println("Enter the updated job location");
					String location = sc.nextLine();
					updateJobLocation(con, job_id, location, employee_id);
					break;
				}
				case 4: {
					System.out.println("Enter the job_Id");
					job_id = sc.nextInt();
					sc.nextLine();
					System.out.println("Enter the updated job updateRequiredSkills");
					String skill = sc.nextLine();
					updateRequiredSkills(con, job_id, skill, employee_id);
					break;
				}
				case 5: {
					System.out.println("Enter the job_Id");
					job_id = sc.nextInt();
					sc.nextLine();
					System.out.println("Enter the  update JobType");
					String job_Type = sc.nextLine();
					updateJobType(con, job_id, job_Type, employee_id);
					break;
				}
				case 6: {
					System.out.println("Enter the job_Id");
					job_id = sc.nextInt();
					sc.nextLine();
					System.out.println("Enter the update ExperienceLevel ");
					String experience = sc.nextLine();
					updateExperienceLevel(con, job_id, experience, employee_id);
					break;
				}
				case 7: {
					System.out.println("Enter the job_Id");
					job_id = sc.nextInt();
					sc.nextLine();
					System.out.println("Enter the updateApplicationDeadLine ");
					String deadLine = sc.nextLine();
					updateApplicationDeadLine(con, job_id, deadLine, employee_id);
					break;
				}
				case 8: {
					System.out.println("Enter the job_Id");
					job_id = sc.nextInt();
					sc.nextLine();
					System.out.println("Enter the updateNumberOfOpenigs");
					int no = sc.nextInt();
					updateNumberOfOpenigs(con, job_id, no, employee_id);
					break;
				}
				case 9: {
					System.out.println("Enter the job_Id");
					job_id = sc.nextInt();
					sc.nextLine();
					System.out.println("Enter the updated job title");
					String title = sc.nextLine();
					System.out.println("Enter the updated job jobDescription");
					String jobDescription = sc.nextLine();
					System.out.println("Enter the updated job location");
					String location = sc.nextLine();
					System.out.println("Enter the updated job updateRequiredSkills");
					String skill = sc.nextLine();
					System.out.println("Enter the  update JobType");
					String job_Type = sc.nextLine();
					System.out.println("Enter the update ExperienceLevel ");
					String experience = sc.nextLine();
					System.out.println("Enter the updateApplicationDeadLine ");
					String deadLine = sc.nextLine();
					System.out.println("Enter the updateNumberOfOpenigs");
					int no = sc.nextInt();
					updateAllRecord(con, job_id, title, jobDescription, location, skill, job_Type, experience, deadLine,
							no, employee_id);
					break;
				}
				case 10: {

					lap = false;

					break;

				}
				default: {
					System.out.println("enter the correct option");

				}

				}
			} while (lap);

		} catch (Exception e) {

		}

	}

	public static void updateJobTitle(Connection con, int job_id, String jobTitle, int employer_id)
			throws SQLException {
		try {
			String sql = "UPDATE JOBS SET JOB_TITLE = ? WHERE JOB_ID = ? AND employer_id = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, jobTitle);
			statement.setInt(2, job_id);
			statement.setInt(3, employer_id);

			int rs = statement.executeUpdate();

			if (rs > 0) {
				System.out.println("Job with ID " + job_id + " updated successfully.");
			} else {
				System.out.println("Failed to update job with ID " + job_id + ".");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updatejobDescription(Connection con, int job_id, String jobDescription, int employer_id)
			throws SQLException {

		try {
			String sql = "UPDATE JOBS SET JOB_DESCRIPTION = ?  WHERE JOB_ID = ? AND employer_id = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, jobDescription);
			statement.setInt(2, job_id);
			statement.setInt(3, employer_id);
			int rs = statement.executeUpdate();

			if (rs > 0) {
				System.out.println("Job with ID " + job_id + " updated successfully.");
			} else {
				System.out.println("Failed to update job with ID " + job_id + ".");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void updateJobLocation(Connection con, int job_id, String location, int employer_id)
			throws SQLException {

		try {
			String sql = "UPDATE JOBS SET Location = ?  WHERE JOB_ID = ? AND employer_id = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, location);
			statement.setInt(2, job_id);
			statement.setInt(3, employer_id);

			int rs = statement.executeUpdate();

			if (rs > 0) {
				System.out.println("Job with ID " + job_id + " updated successfully.");
			} else {
				System.out.println("Failed to update job with ID " + job_id + ".");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void updateRequiredSkills(Connection con, int job_id, String skills, int employer_id)
			throws SQLException {

		try {
			String sql = "UPDATE JOBS SET REQUIRED_SKILLS = ?  WHERE JOB_ID = ? AND employer_id = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, skills);
			statement.setInt(2, job_id);
			statement.setInt(3, employer_id);
			int rs = statement.executeUpdate();

			if (rs > 0) {
				System.out.println("Job with ID " + job_id + " updated successfully.");
			} else {
				System.out.println("Failed to update job with ID " + job_id + ".");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void updateJobType(Connection con, int job_id, String jobType, int employer_id) throws SQLException {

		try {
			String sql = "UPDATE JOBS SET JOB_TYPE = ?  WHERE JOB_ID = ? AND employer_id = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, jobType);
			statement.setInt(2, job_id);
			statement.setInt(3, employer_id);
			int rs = statement.executeUpdate();

			if (rs > 0) {
				System.out.println("Job with ID " + job_id + " updated successfully.");
			} else {
				System.out.println("Failed to update job with ID " + job_id + ".");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void updateExperienceLevel(Connection con, int job_id, String experienceLevel, int employer_id)
			throws SQLException {

		try {
			String sql = "UPDATE JOBS SET EXPERIENCE_LEVEL = ?  WHERE JOB_ID = ? AND employer_id = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, experienceLevel);
			statement.setInt(2, job_id);
			statement.setInt(3, employer_id);

			int rs = statement.executeUpdate();

			if (rs > 0) {
				System.out.println("Job with ID " + job_id + " updated successfully.");
			} else {
				System.out.println("Failed to update job with ID " + job_id + ".");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void updateApplicationDeadLine(Connection con, int job_id, String applicationDeadLine,
			int employer_id) throws SQLException {

		try {
			String sql = "UPDATE JOBS SET APPLICATION_DEADLINE = ?  WHERE JOB_ID = ? AND employer_id = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, applicationDeadLine);
			statement.setInt(2, job_id);
			statement.setInt(3, employer_id);
			int rs = statement.executeUpdate();

			if (rs > 0) {
				System.out.println("Job with ID " + job_id + " updated successfully.");
			} else {
				System.out.println("Failed to update job with ID " + job_id + ".");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void updateNumberOfOpenigs(Connection con, int job_id, int numberOfOpenings, int employer_id)
			throws SQLException {

		try {
			String sql = "UPDATE JOBS SET number_of_openings = ?  WHERE JOB_ID = ? AND employer_id = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, numberOfOpenings);
			statement.setInt(2, job_id);
			statement.setInt(3, employer_id);

			int rs = statement.executeUpdate();

			if (rs > 0) {
				System.out.println("Job with ID " + job_id + " updated successfully.");
			} else {
				System.out.println("Failed to update job with ID " + job_id + ".");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void updateAllRecord(Connection con, int job_id, String jobTitle, String jobDescription,
			String location, String requiredSkills, String jobType, String experienceLevel, String applicationDeadline,
			int numberOfOpenings, int employer_id) {

		try {
			String sql = "UPDATE JOBS SET job_title = ?, job_description = ?, location = ?, required_skills = ?, job_type = ?, experience_level = ?, application_deadline = ?, number_of_openings = ? WHERE JOB_ID = ? AND employer_id = ?";

			PreparedStatement statement = con.prepareStatement(sql);

			statement.setString(1, jobTitle); // Set the new job title
			statement.setString(2, jobDescription); // Set the new job description
			statement.setString(3, location); // Set the new location
			statement.setString(4, requiredSkills); // Set the new required skills
			statement.setString(5, jobType); // Set the new job type
			statement.setString(6, experienceLevel); // Set the new experience level
			statement.setString(7, applicationDeadline); // Set the new application deadline
			statement.setInt(8, numberOfOpenings); // Set the new number of openings
			statement.setInt(9, job_id);
			statement.setInt(10, employer_id);

			int rs = statement.executeUpdate();

			if (rs > 0) {
				System.out.println("Job with ID " + job_id + " updated successfully.");
			} else {
				System.out.println("Failed to update job with ID " + job_id + ".");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	
	   public static void updateJobStatus(int jobId) {
		   Connection con = null;
			PreparedStatement statement = null;
			int choice;
			String status;
	        try {
	        	con = JdbcConnection.connectdatabase();
	            		String sql= "UPDATE jobs SET job_status = ? WHERE job_id = ?";
	            		statement = con.prepareStatement(sql);

	            
	            System.out.println("1. Set job status to Active");
	            System.out.println("2. Set job status to Inactive");
	            choice = sc.nextInt();

	            
	            switch (choice) {
	                case 1:
	                    status = "ACTIVE";
	                   
	                    break;
	                case 2:
	                    status = "INACTIVE";
	                    break;
	                default:
	                    System.out.println("Invalid choice.");
	                    return;
	            }
	        
	            statement.setString(1, status);
	            statement.setInt(2, jobId);
	        
	            int rowsUpdated = statement.executeUpdate();
	            System.out.println(rowsUpdated);

	            if (rowsUpdated > 0) {
	                System.out.println("Job status updated successfully.");
	            } else {
	                System.out.println("No job found with the given ID.");
	            }

	        } catch (SQLException | ClassNotFoundException | InputMismatchException e) {
	            e.printStackTrace();
	        }
	   }

	// toString method to display Job information
	@Override
	public String toString() {
		return "Job{" + "jobId=" + jobId + ", employerId=" + employerId + ", jobTitle='" + jobTitle + '\''
				+ ", jobDescription='" + jobDescription + '\'' + ", location='" + location + '\'' + ", requiredSkills='"
				+ requiredSkills + '\'' + ", jobType='" + jobType + '\'' + ", experienceLevel='" + experienceLevel
				+ '\'' + ", applicationDeadline=" + applicationDeadline + ", numberOfOpenings=" + numberOfOpenings
				+ '}';
	}

}
