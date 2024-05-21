package com.jobportal.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

import com.jdbcservice.JdbcConnection;
import com.jobportal.functionality.Main;
import com.jobportal.listings.Application;
import com.jobportal.listings.Job;
import com.jobportal.listings.Search;
import com.messageandnoficationservices.Message;
import com.userdefiendexception.*;
import com.usersregisterlogin.LoginRegister;

public class JobSeeker extends User implements Search {
	private int jobSeekerId;

	private String contactDetails;
	private String education;
	private String experience;
	private String skills;
	private String firstname;
	private String lastName;
	private int jobId;
	private int applicationId;
	private String exprience;
	private String gender;
	private String Address;
	private String languageSkills;
	private String achivements;
	private String objective;
	static Scanner sc = new Scanner(System.in);

	public JobSeeker(int applicationId, int jobId, String fName, String lName, String gender, String education,
			String language, String skills, String exprience, String achivements, String conatcDetails,
			String address) {
		this.applicationId = applicationId;
		this.jobId = jobId;
		this.firstname = fName;
		this.lastName = lName;
		this.gender = gender;
		this.education = education;
		this.languageSkills = language;
		this.skills = skills;
		this.achivements = achivements;
		this.contactDetails = conatcDetails;
		this.Address = address;
		this.exprience = exprience;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public String getFastname() {
		return firstname;
	}

	public void setFastname(String fastname) {
		this.firstname = fastname;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getExprience() {
		return exprience;
	}

	public void setExprience(String exprience) {
		this.exprience = exprience;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getLanguageSkills() {
		return languageSkills;
	}

	public void setLanguageSkills(String languageSkills) {
		this.languageSkills = languageSkills;
	}

	public String getAchivements() {
		return achivements;
	}

	public void setAchivements(String achivements) {
		this.achivements = achivements;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public JobSeeker(String username, String email, String password, UserType userType) {
		super(username, email, password, userType);

	}

	public JobSeeker() {
		// TODO Auto-generated constructor stub
	}

	public JobSeeker(String objective, String firstName, String lastName, String gender, String education,
			String languageSkills, String exprience, String skills, String achievements, String contactDetails,
			String address) {
		// TODO Auto-generated constructor stub
		this.jobSeekerId = jobSeekerId;
		this.contactDetails = contactDetails;
		this.education = education;
		this.skills = skills;
		this.firstname = firstName;
		this.lastName = lastName;
		this.exprience = exprience;
		this.gender = gender;
		this.Address = address;
		this.languageSkills = languageSkills;
		this.achivements = achievements;
		this.objective = objective;
	}

	public JobSeeker(int jobSeekerId2, String username, String email, String skills2, String education2,
			String experience2) {
		// TODO Auto-generated constructor stub
	}

	public static Scanner getSc() {
		return sc;
	}

	public static void setSc(Scanner sc) {
		JobSeeker.sc = sc;
	}

	public int getJobSeekerId() {
		return jobSeekerId;
	}

	public void setJobSeekerId(int jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}

	public String getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public void jobSeekerRegister() throws ClassNotFoundException, SQLException, CheckUserNameException, OptionException {
		String userName, email, password;

		while (true) {
			System.out.println(
					"Enter the username : Usernames must be 3-20 characters long and can contain letters, digits, underscores, and hyphens.");
			userName = sc.nextLine();
			if (!Validation.checkUserName(userName)) {
				System.out.println(
						"Invalid username format! Usernames must be 3-20 characters long and can contain letters, digits, underscores, and hyphens.");
				continue;
			} else {
				new LoginRegister();
				if (LoginRegister.isSeekerUsernameExists(userName)) {
					System.out.println("Username already exists! Please choose a different username.!");
					continue;
				} else {
					break;
				}
			}
		}
		while (true) {
			System.out.println(
					"Enter the password : password format! Passwords must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character]");

			password = sc.nextLine();

			if (!Validation.checkPassword(password)) {
				System.out.println(
						"Invalid password format! Passwords must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character !");

			} else {
				break;
			}
		}
		while (true) {
			System.out.println("Enter the email :");
			email = sc.nextLine();
			if (!Validation.checkEmail(email)) {
				System.out.println("Invalid email format! Please enter valid email !");

			} else {
				break;
			}
		}

		JobSeeker jobseeker = new JobSeeker(userName, email, password, UserType.JOBSEEKER);

		LoginRegister.registerJobSeeker(jobseeker);
		
		//jobSeekerLogin();

	}

	public void jobSeekerLogin() throws ClassNotFoundException, SQLException, CheckUserNameException, OptionException, CompanyNameValidationException {

		String userName, password;

		while (true) {
			System.out.println("Enter the username :");

			userName = sc.nextLine();
			if (!Validation.checkUserName(userName)) {
				System.out.println("UserName Wrong Enter Correct userName :");
				continue;
			} else {
				break;
			}
		}

		while (true) {
			System.out.println("Enter the password :");
			password = sc.nextLine();
			if (!Validation.checkPassword(password)) {
				System.out.println("Password wrong Enter correct password !");
			} else {
				break;
			}
		}

		Object loggedIn = LoginRegister.userslogin(userName, password, UserType.JOBSEEKER);

		if (loggedIn instanceof JobSeeker) {
			JobSeeker seeker = (JobSeeker) loggedIn;

			System.out.println("+>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>+");
			System.out.println("        Welcome " + userName + "       ");
			System.out.println("+<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<+");

			jobSeekerMenu(seeker);

		} else {
			System.out.println("Login failed. Incorrect username or password !");

		}
	}

	public void profile(int userId) throws ClassNotFoundException, SQLException, OptionException, CheckUserNameException, CompanyNameValidationException {
		boolean flag = true;
		do {

			System.out.println("+------------------------------------+");
			System.out.println("|          Resume Menu               |");
			System.out.println("+------------------------------------+");
			System.out.println("| 1. Create Resume                   |");
			System.out.println("| 2. View Resume                     |");
			System.out.println("| 3. Update Resume                   |");
			System.out.println("| 4. Back                            |");
			System.out.println("| 5. Exit                            |");
			System.out.println("+------------------------------------+");
			System.out.println("Enter your option: ");

			int option = sc.nextInt();
			switch (option) {
			case 1: {
				sc.nextLine();
				System.out.println("Enter your objective: ");
				String objective = sc.nextLine();
				System.out.println("Enter your first name: ");
				String firstName = sc.nextLine();
				System.out.println("Enter your last name: ");
				String lastName = sc.nextLine();

				String gender;
				while (true) {
					System.out.println("Enter your gender:['male', 'female', or 'other] ");
					gender = sc.nextLine();
					if (!Validation.isValidGender(gender)) {
						System.out.println("Invalid gender! Please enter 'male', 'female', or 'other'.");
					} else {
						break;
					}
				}
				System.out.println("Enter your education: ");
				String education = sc.nextLine();
				System.out.println("Enter your language skills: ");
				String languageSkills = sc.nextLine();
				System.out.println("Enter your experience: ");
				String experience = sc.nextLine();
				System.out.println("Enter your skills: ");
				String skills = sc.nextLine();
				System.out.println("Enter your achievements: ");
				String achievements = sc.nextLine();
				System.out.println("Enter your contact details: ");
				String contactDetails = sc.nextLine();
				System.out.println("Enter your address: ");
				String address = sc.nextLine();
				JobSeeker jobseeker = new JobSeeker(objective, firstName, lastName, gender, education, languageSkills,
						experience, skills, achievements, contactDetails, address);

				profileCreating(jobseeker, userId);
				break;
			}
			case 2: {

				viewResume(userId);
				break;
			}
			case 3: {

				sc.nextLine();
				System.out.println("Enter your objective: ");
				String objective = sc.nextLine();
				System.out.println("Enter your first name: ");
				String firstName = sc.nextLine();
				System.out.println("Enter your last name: ");
				String lastName = sc.nextLine();
				System.out.println("Enter your gender: ");
				String gender = sc.nextLine();
				System.out.println("Enter your education: ");
				String education = sc.nextLine();
				System.out.println("Enter your language skills: ");
				String languageSkills = sc.nextLine();
				System.out.println("Enter your experience: ");
				String experience = sc.nextLine();
				System.out.println("Enter your skills: ");
				String skills = sc.nextLine();
				System.out.println("Enter your achievements: ");
				String achievements = sc.nextLine();
				System.out.println("Enter your contact details: ");
				String contactDetails = sc.nextLine();
				System.out.println("Enter your address: ");
				String address = sc.nextLine();
				JobSeeker jobseeker = new JobSeeker(objective, firstName, lastName, gender, education, languageSkills,
						experience, skills, achievements, contactDetails, address);

				profileCreating(jobseeker, userId);
				break;

			}
			case 4: {

				jobSeekerMenu(this);
				break;
			}
			case 5: {
				System.exit(0);
				break;

			}
			default:
				System.out.println("Enter the correct Option ");

			}
		} while (flag);
	}

	public void profileCreating(JobSeeker js, int id) throws ClassNotFoundException {
		Connection con = null;
		PreparedStatement statement = null;

		try {
			con = JdbcConnection.connectdatabase();
			String sql = "UPDATE job_seekers SET OBJECTIVE = ?, FNAME = ?, LNAME = ?, GENDER = ?, EDUCATION = ?, LANGUAGE = ?, EXPERIENCE = ?, SKILLS = ?, ACHIVEMENTS = ?, CONTACT_DETAILS = ?, ADDRESS = ? WHERE  JOB_SEEKER_ID = ?";

			statement = con.prepareStatement(sql);

			statement.setString(1, js.getObjective());
			statement.setString(2, js.getFastname());
			statement.setString(3, js.getLastName());
			statement.setString(4, js.getGender());
			statement.setString(5, js.getEducation());
			statement.setString(6, js.getLanguageSkills());
			statement.setString(7, js.getExprience());
			statement.setString(8, js.getSkills());
			statement.setString(9, js.getAchivements());
			statement.setString(10, js.getContactDetails());
			statement.setString(11, js.getAddress());
			statement.setInt(12, id);

			int rowsUpdated = statement.executeUpdate();

			if (rowsUpdated > 0) {
				System.out.println("Profile updated successfully.");
			} else {
				System.out.println("Failed to update profile. User not found or no changes made.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void viewResume(int id) throws ClassNotFoundException {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			
			con = JdbcConnection.connectdatabase();
			String sql = "SELECT OBJECTIVE, FNAME, LNAME, GENDER, EDUCATION, LANGUAGE,EXPERIENCE,SKILLS,ACHIVEMENTS,CONTACT_DETAILS,ADDRESS FROM JOB_SEEKERS WHERE JOB_SEEKER_ID = ?";
			statement = con.prepareStatement(sql);
			statement.setInt(1, id);
			rs = statement.executeQuery();
			while (rs.next()) {

				String objective = rs.getString("OBJECTIVE");
				String fName = rs.getString("FNAME");
				String lName = rs.getString("LNAME");
				String gender = rs.getString("GENDER");
				String education = rs.getString("EDUCATION");
				String language = rs.getString("LANGUAGE");
				String experience = rs.getString("EXPERIENCE");
				String skills = rs.getString("EDUCATION");
				String achivements = rs.getString("ACHIVEMENTS");
				String contactDetils = rs.getString("CONTACT_DETAILS");
				String address = rs.getString("ADDRESS");
				
				System.out.println("------------------------------------");
				System.out.println("Objective                : " + objective);
				System.out.println("FirstNae                 : " + fName);
				System.out.println("LastName                 : " + lName);
				System.out.println("Gender                   : " + gender);
				System.out.println("Education                : " + education);
				System.out.println("Language                 : " + language);
				System.out.println("Exprience                : " + experience);
				System.out.println("Skills                   : " + skills);
				System.out.println("Achiveents               : " + achivements);
				System.out.println("ContactDetils            : " + contactDetils);
				System.out.println("Address                  : " + address);

				System.out.println("------------------------------------");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void seachjobs() throws ClassNotFoundException, SQLException, OptionException, CheckUserNameException, CompanyNameValidationException {
		boolean flag = true;
		do {
			System.out.println("+------------------------------------+");
			System.out.println("|          Search Options            |");
			System.out.println("+------------------------------------+");
			System.out.println("| 1. View All Jobs                   |");
			System.out.println("| 2. Title-based Search              |");
			System.out.println("| 3. Location-based Search           |");
			System.out.println("| 4. Back                            |");
			System.out.println("| 5. Exit                            |");
			System.out.println("+------------------------------------+");
			System.out.println("Enter your option :");
			int option = sc.nextInt();
			switch (option) {
			case 1: {

				viewAllJobs();

				break;
			}
			case 2: {
				sc.nextLine();
				System.out.println("Enter job title :");
				String title = sc.nextLine();
				searchJobs(title);
				break;
			}
			case 3: {
				sc.nextLine();
				System.out.println("Enter location :");
				String location = sc.nextLine();
				searchJobsforLocation(location);
				break;

			}
			case 4: {
				try {
					jobSeekerMenu(this);
				} catch (ClassNotFoundException | SQLException | OptionException | CheckUserNameException
						| CompanyNameValidationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			}
			case 5: {
				System.out.println("Thank you for using Job Portal!  Have a great day!");
				System.exit(0);
				break;
			}
			default:
				System.out.println("[.....Enter The Corrct Option.....]");
			}

		} while (flag);

	}

	@Override
	public void searchJobs(String JOB_TITLE) throws ClassNotFoundException, SQLException {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			con = JdbcConnection.connectdatabase();

			String sqlBuilder = "SELECT * FROM jobs WHERE JOB_TITLE = ?";

			statement = con.prepareStatement(sqlBuilder);
			statement.setString(1, JOB_TITLE);

			rs = statement.executeQuery();

			if (!rs.isBeforeFirst()) {
				System.out.println("[....No jobs found for the given title....]");
			} else {
				System.out.println("Welcome");
				while (rs.next()) {
					int jobId = rs.getInt("job_id");
					String jobTitle = rs.getString("job_title");
					String jobDescription = rs.getString("job_description");
					String location = rs.getString("location");
					String requiredSkills = rs.getString("required_skills");
					String jobType = rs.getString("job_type");
					String experienceLevel = rs.getString("experience_level");
					String applicationDeadline = rs.getString("application_deadline");
					int numberOfOpenings = rs.getInt("number_of_openings");
					System.out.println("------------------------------------");
					System.out.println("Job ID: " + jobId);
					System.out.println("Job Title: " + jobTitle);
					System.out.println("Description: " + jobDescription);
					System.out.println("Location: " + location);
					System.out.println("Required Skills: " + requiredSkills);
					System.out.println("Job Type: " + jobType);
					System.out.println("Experience Level: " + experienceLevel);
					System.out.println("Application Deadline: " + applicationDeadline);
					System.out.println("Number of Openings: " + numberOfOpenings);
					System.out.println("------------------------------------");
					// matchingJobs.add(new
					// Job(jobId,employerId,jobTitle,jobDescription,location,requiredSkills,experienceLevel,applicationDeadline,numberOfOpenings));
				}
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	public void searchJobsforLocation(String location) throws ClassNotFoundException, SQLException {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			con = JdbcConnection.connectdatabase();

			String sqlBuilder = "SELECT * FROM jobs WHERE LOCATION = ? ";

			statement = con.prepareStatement(sqlBuilder);
			statement.setString(1, location);

			rs = statement.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("[....No jobs found for the given location....]");
			} else {
				while (rs.next()) {

					int jobId = rs.getInt("job_id");

					String jobTitle = rs.getString("job_title");
					String jobDescription = rs.getString("job_description");
					location = rs.getString("location");
					String requiredSkills = rs.getString("required_skills");
					String jobType = rs.getString("job_type");
					String experienceLevel = rs.getString("experience_level");
					String applicationDeadline = rs.getString("application_deadline");
					int numberOfOpenings = rs.getInt("number_of_openings");
					System.out.println("------------------------------------");
					System.out.println("Job ID: " + jobId);

					System.out.println("Job Title: " + jobTitle);
					System.out.println("Description: " + jobDescription);
					System.out.println("Location: " + location);
					System.out.println("Required Skills: " + requiredSkills);
					System.out.println("Job Type: " + jobType);
					System.out.println("Experience Level: " + experienceLevel);
					System.out.println("Application Deadline: " + applicationDeadline);
					System.out.println("Number of Openings: " + numberOfOpenings);
					System.out.println("------------------------------------");
					// matchingJobs.add(new
					// Job(jobId,employerId,jobTitle,jobDescription,location,requiredSkills,experienceLevel,applicationDeadline,numberOfOpenings));
				}
			}
			// System.out.println(matchingJobs);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

//	public void viewAllJobs() throws ClassNotFoundException, SQLException {
//		Connection con = null;
//		PreparedStatement statement = null;
//		ResultSet rs = null;
//
//		try {
//			con = JdbcConnection.connectdatabase();
//
//			String sqlBuilder = "SELECT * FROM jobs ";
//
//			statement = con.prepareStatement(sqlBuilder);
//
//			rs = statement.executeQuery();
//			//System.out.println("welcome");
//			while (rs.next()) {
//
//				int jobId = rs.getInt("job_id");
//
//				String jobTitle = rs.getString("job_title");
//				String jobDescription = rs.getString("job_description");
//				String location = rs.getString("location");
//				String requiredSkills = rs.getString("required_skills");
//				String jobType = rs.getString("job_type");
//				String experienceLevel = rs.getString("experience_level");
//				String applicationDeadline = rs.getString("application_deadline");
//				int numberOfOpenings = rs.getInt("number_of_openings");
//				String job_status = rs.getString("Job_status");
//				System.out.println("------------------------------------");
//				System.out.println("Job ID: " + jobId);
//
//				System.out.println("Job Title        : " + jobTitle);
//				System.out.println("Description      : " + jobDescription);
//				System.out.println("Location         : " + location);
//				System.out.println("Required Skills  : " + requiredSkills);
//				System.out.println("Job Type: " + jobType);
//				System.out.println("Experience Level : " + experienceLevel);
//				System.out.println("Application Deadline: " + applicationDeadline);
//				System.out.println("Number of Openings: " + numberOfOpenings);
//				System.out.println("Job Status :" + job_status);
//				System.out.println("------------------------------------");
//			}
//
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//		}
//
//	}
	public void viewAllJobs() throws ClassNotFoundException, SQLException {
	    Connection con = null;
	    PreparedStatement statement = null;
	    ResultSet rs = null;

	    try {
	        con = JdbcConnection.connectdatabase();

	        String sqlBuilder = "SELECT * FROM jobs ";

	        statement = con.prepareStatement(sqlBuilder);

	        rs = statement.executeQuery();

	        boolean foundJobs = false; // Flag to check if any jobs are found

	        while (rs.next()) {
	            foundJobs = true; // At least one job is found
	            int jobId = rs.getInt("job_id");
	            String jobTitle = rs.getString("job_title");
	            String jobDescription = rs.getString("job_description");
	            String location = rs.getString("location");
	            String requiredSkills = rs.getString("required_skills");
	            String jobType = rs.getString("job_type");
	            String experienceLevel = rs.getString("experience_level");
	            String applicationDeadline = rs.getString("application_deadline");
	            int numberOfOpenings = rs.getInt("number_of_openings");
	            String job_status = rs.getString("Job_status");
	            System.out.println("------------------------------------");
	            System.out.println("Job ID: " + jobId);
	            System.out.println("Job Title        : " + jobTitle);
	            System.out.println("Description      : " + jobDescription);
	            System.out.println("Location         : " + location);
	            System.out.println("Required Skills  : " + requiredSkills);
	            System.out.println("Job Type: " + jobType);
	            System.out.println("Experience Level : " + experienceLevel);
	            System.out.println("Application Deadline: " + applicationDeadline);
	            System.out.println("Number of Openings: " + numberOfOpenings);
	            System.out.println("Job Status :" + job_status);
	            System.out.println("------------------------------------");
	        }

	        if (!foundJobs) {
	            System.out.println("No jobs available.");
	        }

	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    } finally {
	        // Close resources in the finally block
	        if (rs != null) {
	            rs.close();
	        }
	        if (statement != null) {
	            statement.close();
	        }
	        if (con != null) {
	            con.close();
	        }
	    }
	}


	public void manageProfie(int userId) throws ClassNotFoundException, SQLException, OptionException, CheckUserNameException, CompanyNameValidationException {
		boolean lap = true;
		do {
			System.out.println("╔═══════════════════════════════════╗");
			System.out.println("║      Profile Management Menu      ║");
			System.out.println("╠═══════════════════════════════════╣");
			System.out.println("║ 1. Update Password                ║");
			System.out.println("║ 2. Update Email                   ║");
			System.out.println("║ 3. Back                           ║");
			System.out.println("║ 4. Exit                           ║");
			System.out.println("╚═══════════════════════════════════╝");
			System.out.println("Enter the Option : ");
			int option = sc.nextInt();
			switch (option) {
			case 1: {
//				sc.nextLine();
//				System.out.println("Enter the update password:");
//				String password = sc.nextLine();
//				updatePassword(userId, password);
//				break;
				String password;
				System.out.println(
						"[Enter the Update password :[ password format! Passwords must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.]");
				sc.nextLine();
				while (true) {
					System.out.println("Enter the password :");

					password = sc.nextLine();
					if (!Validation.checkPassword(password)) {
						System.out.println(
								"[.....Invalid password format! Passwords must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character......]");

					} else {
						break;
					}
				}
				updatePassword(userId, password);
				break;
			}

			case 2: {

//				System.out.println("Enter the update email:");
//				String email = sc.nextLine();
//				updateEmail(userId, email);
//				break;
				String email;
				sc.nextLine();
				while (true) {
					System.out.println("Enter the Udate email :");
					email = sc.nextLine();
					if (!Validation.checkEmail(email)) {
						System.out.println("[.....Invalid email format! Please enter valid email.....]");

					} else {
						break;
					}
				}
				updateEmail(userId, email);
				break;
			}
			case 3: {

				jobSeekerMenu(this);

				break;
			}
			case 4: {
				System.out.println("Thank you for using Job Portal!  Have a great day!");
				System.exit(0);
				break;
			}
			default: {

				System.out.println("[....Enter the correct option....]");
			}

			}

		} while (lap);
	}

	public static void updatePassword(int userId, String password) throws ClassNotFoundException {

		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = JdbcConnection.connectdatabase();
			String sql = "UPDATE JOB_SEEKERS SET PASSWORD = ? WHERE JOB_SEEKER_ID = ? ";

			statement = con.prepareStatement(sql);

			statement.setString(1, password);
			statement.setInt(2, userId);

			int rowsInserted = statement.executeUpdate();

			if (rowsInserted > 0) {
				System.out.println(" Password Update successfully !");
			} else {
				System.out.println("[.....Failed to  Password update.....]");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateEmail(int userId, String email) throws ClassNotFoundException {

		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = JdbcConnection.connectdatabase();
			String sql = "UPDATE JOB_SEEKERS SET Email = ? WHERE JOB_SEEKER_ID = ? ";

			statement = con.prepareStatement(sql);

			statement.setString(1, email);
			statement.setInt(2, userId);

			int rowsInserted = statement.executeUpdate();

			if (rowsInserted > 0) {
				System.out.println(" Email Update  successfully !");
			} else {
				System.out.println("[.....Failed to Email update.....]");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private boolean isAlreadyApplied(int jobId, int jobSeekerId) throws ClassNotFoundException {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean alreadyApplied = false;

		try {
			con = JdbcConnection.connectdatabase();
			String query = "SELECT COUNT(*) FROM job_applications WHERE JOB_ID = ? AND JOB_SEEKER_ID = ?";
			statement = con.prepareStatement(query);
			statement.setInt(1, jobId);
			statement.setInt(2, jobSeekerId);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				int count = resultSet.getInt(1);
				alreadyApplied = count > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
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

		return alreadyApplied;
	}

	public void applyForJob(int jobId, JobSeeker seeker) throws ClassNotFoundException {
		// System.out.println(jobSeekerId);
		Connection con = null;
		PreparedStatement statement = null;
		int employerId = Employer.getEmployerIdForJob(jobId);
		if (isAlreadyApplied(jobId, seeker.getJobSeekerId())) {
			System.out.println("You have already applied for this job.");
			return;
		}

		try {
			con = JdbcConnection.connectdatabase();
			String sql = "INSERT INTO job_applications (APPLICATION_ID, JOB_ID, JOB_SEEKER_ID, EMP_ID, APPLICATION_DATE) VALUES (app_id_seq.NEXTVAL, ?, ?, ?, CURRENT_DATE)";
			statement = con.prepareStatement(sql);
			statement.setInt(1, jobId);
			statement.setInt(2, seeker.getJobSeekerId());
			statement.setInt(3, employerId);

			int rowsInserted = statement.executeUpdate();

			if (rowsInserted > 0) {
				System.out.println("Application submitted successfully.");
			} else {
				System.out.println("Failed to submit application.");
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

	public void jobSeekerMenu(JobSeeker seeker)
			throws ClassNotFoundException, SQLException, OptionException, CheckUserNameException, CompanyNameValidationException {
		boolean flag = false;
		do {

			System.out.println("+------------------------------------+");
			System.out.println("|          Job Seeker Menu           |");
			System.out.println("+------------------------------------+");
			System.out.println("| 1. Resume                          |");
			System.out.println("| 2. Search Jobs                     |");
			System.out.println("| 3. Apply for Jobs                  |");
			System.out.println("| 4. Profile Management              |");
			System.out.println("| 5. Notification                    |");
			System.out.println("| 6. LogOut                          |");
			System.out.println("| 7. Exit                            |");
			System.out.println("+------------------------------------+");
			System.out.println("Enter your option:");

			int option = Validation.validateOption(sc.next());
			switch (option) {

			case 1: {

				seeker.profile(seeker.getJobSeekerId());
				break;

			}
			case 2: {
				seeker.seachjobs();
				break;
			}

			case 3: {
				applicationStatus(seeker);
				break;
			}
			case 4: {
				seeker.manageProfie(seeker.getJobSeekerId());
				break;

			}
			case 5: {
				new Message().message(seeker);
				;

				break;
			}
			case 6: {
				System.out.println("Logged out successfully. Thank you for using Job Portal!");

				new Main().jobSeeker();

				break;

			}
			case 7: {
				System.out.println("Thank you for using Job Portal!  Have a great day!");

				System.exit(0);
				break;
			}
			default:
				System.out.println("Pleace Enter the correct option");

			}
		} while (flag);
	}

	public void applicationStatus(JobSeeker seeker)
			throws ClassNotFoundException, SQLException, OptionException, CheckUserNameException, CompanyNameValidationException {
		boolean label = true;
		do {
			System.out.println("+------------------------------------+");
			System.out.println("|     Apply job and view Status      |");
			System.out.println("+------------------------------------+");
			System.out.println("| 1. Apply for Job                   |");
			System.out.println("| 2. View Application Status         |");
			System.out.println("| 3. Back                            |");
			System.out.println("| 4. Exit                            |");
			System.out.println("+------------------------------------+");

			System.out.println("Enter the option :");

			int option = Validation.validateOption(sc.next());

			switch (option) {
			case 1: {
				if (seeker.getContactDetails() == null) {
				    System.out.println("[...Please create a resume before applying for a job...]");
				} else {
				    System.out.println("Enter the job id:");
				    int jobId = sc.nextInt();
				    seeker.applyForJob(jobId, seeker);
				     // Assuming you want to exit the loop after applying for a job
				}
                       break;
				}

			
			case 2: {

				new Application();
				Application.viewApplicationStatus(seeker.getJobSeekerId());
				break;

			}
			case 3: {

				try {
					jobSeekerMenu(seeker);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (OptionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CheckUserNameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CompanyNameValidationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			}
			case 4: {
				System.out.println("Thank you for using Job Portal!  Have a great day!");
				System.exit(0);
				break;
			}
			default:
				System.out.println("Pleace Enter the correct option ");

			}

		} while (label);

	}

}
