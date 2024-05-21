package com.jobportal.users;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Collection;
import java.util.HashMap;

import com.userdefiendexception.*;
import com.usersregisterlogin.LoginRegister;
import com.jdbcservice.JdbcConnection;
import com.jobportal.functionality.Main;
import com.jobportal.listings.Application;
import com.jobportal.listings.Job;
import com.messageandnoficationservices.Message;

public class Employer extends User {
    
	private String companyName;
	private int registerNumber;
	private String domain;
	private String contact;
	private String phone;
	private LocalDate date;
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	private int id;
	ArrayList<Job> jobArrayList = new ArrayList<Job>();
 HashMap<String,String> hm = new HashMap<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	static Scanner sc = new Scanner(System.in);

	public Employer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(int registerNumber) {
		this.registerNumber = registerNumber;
	}

	public Employer(String username, String email, String password, UserType userType, String domain,
			String companyName, int registerNumber, String phone) {
		super(username, email, password, userType);
		this.domain = domain;
		this.companyName = companyName;
		this.registerNumber = registerNumber;
		this.phone = phone;
		// TODO Auto-generated constructor stub
	}

	public Employer(int id, String username, String password, String email) {
		// TODO Auto-generated constructor stub
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public void emloyerUserCreate() throws CheckUserNameException, ClassNotFoundException, SQLException, JobIdAlreadyExistsException, CompanyNameValidationException {
		
		String userName, email, password ,phone;
		// Loop for entering and validating username

		while (true) {

			System.out.println(
					"Enter the username : [Usernames must be 3-20 characters long and can contain letters, digits, underscores, and hyphens.]");
			userName = sc.nextLine();
			if (!Validation.checkUserName(userName)) {
				
				continue;
			} else {
				new LoginRegister();
				if (LoginRegister.isUsernameExists(userName)) {
					System.out.println("Username already exists! Please choose a different username.");
					continue;
				} else {
					
					break;
				}
			}
		}
		// Loop for entering and validating password
		while (true) {
			System.out.println(
					"Enter the password : [password format! Passwords must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.]");

			password = sc.nextLine();
			if (!Validation.checkPassword(password)) {
				
			} else {
				break;
			}
		}
	
		String cName ;
		while(true) {
			System.out.println("Enter The Company Name :[ name contain only alphabetic characters.]");
			cName = sc.nextLine();
			try {
				if(!Validation.validateCompanyName(cName)) {
					
					continue;
				}
				else {
					
					break;
				}
			} catch (CompanyNameValidationException e) {
				System.out.println(e.getMessage());
			
			}
		}
		while (true) {                                      // Loop for entering and validating email

			System.out.println("Enter the email :");
			email = sc.nextLine();
			if (!Validation.checkEmail(email)) {
				continue;
			} else {

				break;
			}
		}

		
		int cRegisterNumber;
		while (true) {
		    try {
		        System.out.println("Enter The CompanyRegisterNumber : [Enter only numeric value]");
		        cRegisterNumber = sc.nextInt();
		        sc.nextLine(); // Consume the newline character after reading the integer
		        break; // If no exception occurs, break out of the loop
		    } catch (InputMismatchException e) {
		       
		        sc.nextLine(); // Clear the invalid input
		    }
		}
		String domain;
		while(true) {
		System.out.println("Enter The domain :[Enter only charachter Sequence]");
		domain = sc.nextLine();
		try {
			if(!Validation.ValidateCharacterSequences(domain)) {
				
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
        while(true) {
		System.out.println("Enter The Phone Number : [ Contact number should be 10 digits long and start with 9, 7, 8, or 6]");
		phone = sc.nextLine();
		if(!Validation.isValidPhoneNumber(phone)) {
			
			continue;
		} else {
			break;
		}
        }
		                                                       // Registering the employer user
		LoginRegister.registerEmployee(new Employer(userName, email, password, UserType.EMPLOYER, domain, cName,  
			
				cRegisterNumber, phone));
		new Main().employee();
		
	}

	public void employersUserLogin() throws ClassNotFoundException, SQLException, CheckUserNameException {
		String userName, password;
		 // Loop for entering and validating username
		int max =3,at=0;
		while (at < max) {
		while (true) {
			System.out.println("Enter The Username :");

			userName = sc.nextLine();
			if (!Validation.checkUserName(userName)) {
				System.out.println("Wrong User Name Enter corret User Name :");
				at++;
				continue;
			} else {
				break;
			}
		}
		// Loop for entering and validating password
		while (true) {
			System.out.println("Enter the password :");
			password = sc.nextLine();
			if (!Validation.checkPassword(password)) {
				System.out.println("Password Wrong Enter Correct Password :");
                at++;
			} else {
				break;
			}
		}
        if (at == max) {
            System.out.println("Too many failed attempts. Exiting...");
            break;
        }

		// Attempting user login
		Object loggedIn = LoginRegister.userslogin(userName, password, UserType.EMPLOYER);

		if (loggedIn instanceof Employer) {
			Employer employer = (Employer) loggedIn;

			System.out.println("+>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>+");
			System.out.println("        Welcome " + userName + "       ");
			System.out.println("+<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<+");
		// Redirecting to employer menu
			emoloyerMenu(employer);

		} else {
			System.out.println("Login failed. Incorrect username or password.");
		}
		}
	}

	public void emoloyerMenu(Employer employer) {

		try {
			boolean flags = false;
			do {   // Displaying the menu options
				System.out.println("+------------------------------------+");
				System.out.println("|            Services Menu           |");
				System.out.println("+------------------------------------+");
				System.out.println("| 1. Job Management                  |");
				System.out.println("| 2. Candidate Search                |");
				System.out.println("| 3. Application View                |");
				System.out.println("| 4. Profile Management              |");
				System.out.println("| 5. Message Management              |");
				System.out.println("| 6. LogOut                          |");
				System.out.println("| 7. Exit                            |");
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
		        }// Switch case for handling user selection
				switch (option) {
				case 1: {

					new Job().jobManagement(employer); // Calling job management method
					break;
				}
				case 2: {

					employer.searchCandidate();// Calling candidate search method
					break;

				}
				case 3: {

					employer.fetchJobSeekersInfo(employer);// Viewing application status
					emoloyerMenu(employer);// Returning to employer menu
					break;
				}
				case 4: {

					employer.profileManagement(employer);// Managing employer profile
					break;
				}
				case 5: {

					new Message().messages(employer);// Managing Notifications
					break;

				}
				case 6: {
					System.out.println("Logged out successfully. Thank you for using Job Portal!");
					new Main().employee();// Logging out
					break;

				}
				case 7: {
					System.out.println("Thank you for using Job Portal!  Have a great day!");
					System.exit(0);// Exiting the application

					break;

				}

				default: {

					System.out.println("Invalid choice. Please try again !");
				}

				}

			} while (flags);// Loop continues until 'flags' is true
		} catch (Exception e) {

		}

	}

	public void candidateSearch(Employer employer) throws ClassNotFoundException, OptionException {
		employer.searchCandidate();
	}

	public void profileManagement(Employer employer)
			throws ClassNotFoundException, OptionException, JobIdAlreadyExistsException {
		boolean flag = true;
		do {
			System.out.println("╔═══════════════════════════════════╗");
			System.out.println("║      Profile Management Menu      ║");
			System.out.println("╠═══════════════════════════════════╣");
			System.out.println("║ 1. View Profile                   ║");
			System.out.println("║ 2. Update Profile                 ║");
			System.out.println("║ 3. Update Password                ║");
			System.out.println("║ 4. Update Email                   ║");
			System.out.println("║ 5. Update Company Name            ║");
			System.out.println("║ 6. Update Company Register Number ║");
			System.out.println("║ 7. Update PhoneNumber             ║");
			System.out.println("║ 8. Update Domain                  ║");
			System.out.println("║ 9. Back                           ║");
			System.out.println("║ 10. Exit                          ║");
			System.out.println("╚═══════════════════════════════════╝");
			
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

				viewProfile(employer);// Viewing profile

				break;
			}
			case 2: {
				sc.nextLine(); // Updating profile
				System.out.println("Enter the update email:");
				String email = sc.nextLine();
				System.out.println("Enter the update password:");
				String password = sc.nextLine();
				System.out.println("Enter the update company Name:");
				String cName = sc.nextLine();
				System.out.println("Enter the update company Register Number:");
				int cRegisterNumber = sc.nextInt();
				sc.nextLine();
				while(true) {
					System.out.println("Enter The Update Phone Number :");
					phone = sc.nextLine();
					if(!Validation.isValidPhoneNumber(phone)) {
						continue;
					} else {
						break;
					}
			        }
				
				System.out.println("Enter the domain:");
				String domain = sc.nextLine();
				updateProfile(employer, email, password, cName, cRegisterNumber, phone, domain);
				break;
			}
			case 3: {
				sc.nextLine();
				System.out.println("Enter the update password:");
				String password = sc.nextLine();
				updatePassword(employer, password); // Updating password
				break;
			}
			case 4: {
				sc.nextLine();
				System.out.println("Enter the update email:");
				String email = sc.nextLine();
				updateEmail(employer, email); // Updating email
				break;
			}
			case 5: {
				sc.nextLine();
				System.out.println("Enter the update company Name:");
				String cName = sc.nextLine();
				updateCompanyName(employer, cName); // Updating company name
				break;
			}
			case 6: {
				System.out.println("Enter the update company Register Number:");// Updating company register number
				int cRegisterNumber = sc.nextInt();
				sc.nextLine();
				updateRegisterNumber(employer, cRegisterNumber);
				break;

			}
			case 7: {
				sc.nextLine();
				while(true) {
					System.out.println("Enter The update Phone Number :");// Updating phone number details
					phone = sc.nextLine();
					if(!Validation.isValidPhoneNumber(phone)) {
						continue;
					} else {
						break;
					}
			        }
//				System.out.println("Enter the update phone number :");// Updating contact details
//				long contact = sc.nextLong();
				updateContact(employer, phone);
//				break;
				break;
			}
			case 8: {
				sc.nextLine();
				System.out.println("Enter the domain:"); // Updating domain
				String domain = sc.nextLine();
				updateDomain(employer, domain);
				break;

			}
			case 9: {

				emoloyerMenu(this);// Returning to employer menu

				break;

			}
			case 10: {

				System.exit(0);// Exiting the profile management
				break;

			}
			default: 
				//System.out.println("enter the correct option");
			
			}
		} while (flag);
	}

	public void viewProfile(Employer employer) throws ClassNotFoundException {

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			con = JdbcConnection.connectdatabase();// Establishing database connection


			String sql = "SELECT ID, EMAIL, COMPANYNAME, REGISTERNUMBER, PHONE_NUMBER, DOMAIN FROM EMPLOYERS WHERE ID = ?";

			statement = con.prepareStatement(sql);
			statement.setInt(1, employer.getId());
			rs = statement.executeQuery();// Executing the query

			while (rs.next()) {
				int id = rs.getInt("id");

				String email = rs.getString("EMAIL");
				String companyName = rs.getString("COMPANYNAME");
				int registerNumber = rs.getInt("REGISTERNUMBER");
				String phone = rs.getString("PHONE_NUMBER");
				String domain = rs.getString("DOMAIN");
				// Displaying the profile information
				System.out.println("------------------------------------");
				System.out.println("User ID               : " + id);
				System.out.println("Email                 : " + email);
				System.out.println("CompanyName           : " + companyName);
				System.out.println("Company RegisterNumber: " + registerNumber);
				System.out.println("Contact Number        : " + phone);
				System.out.println("domain                : " + domain);
				System.out.println("------------------------------------");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void updateProfile(Employer employer, String email, String password, String companyName,
			int registerNumber, String phone, String domain) throws ClassNotFoundException {
		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = JdbcConnection.connectdatabase();
			String sql = "UPDATE EMPLOYERS SET Email = ?, PASSWORD = ?,COMPANYNAME = ?,REGISTERNUMBER =?, PHONE_NUMBER= ?,DOMAIN =? WHERE ID = ? ";

			statement = con.prepareStatement(sql);

			statement.setString(1, email);
			statement.setString(2, password);
			statement.setString(3, companyName);
			statement.setInt(4, registerNumber);
			statement.setString(5, phone);
			statement.setString(6, domain);
			statement.setInt(7, employer.getId());
			int rowsInserted = statement.executeUpdate();

			if (rowsInserted > 0) {
				System.out.println(" profile Update  successfully.");
			} else {
				System.out.println("Failed to  profile update.");
			}
		} catch (SQLException e) {
		    if (e.getErrorCode() == 1) {
		        System.out.println("Failed to update profile: Duplicate values found.");
		    } else {
		        e.printStackTrace(); // For other SQL errors, print the stack trace
		    }
		}

	}

	public void updateEmail(Employer employer, String email) throws ClassNotFoundException {

		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = JdbcConnection.connectdatabase();// Establishing database connection
			String sql = "UPDATE EMPLOYERS SET Email = ? WHERE ID = ? ";

			statement = con.prepareStatement(sql);

			statement.setString(1, email);
			statement.setInt(2, employer.getId());

			int rowsInserted = statement.executeUpdate();

			if (rowsInserted > 0) {
				System.out.println(" Email Update  successfully.");
			} else {
				System.out.println("Failed to  Email update.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateContact(Employer employer, String phone) throws ClassNotFoundException {

	    Connection con = null;
	    PreparedStatement statement = null;
	    try {
	        con = JdbcConnection.connectdatabase();
	        String sql = "UPDATE EMPLOYERS SET PHONE_NUMBER = ? WHERE ID = ? ";

	        statement = con.prepareStatement(sql);

	        statement.setString(1, phone);
	        statement.setInt(2, employer.getId());

	        int rowsUpdated = statement.executeUpdate();

	        if (rowsUpdated > 0) {
	            System.out.println("Contact updated successfully.");
	        } else {
	            System.out.println("Failed to update contact. User ID not found.");
	        }
	    } catch (SQLException e) {
	        if (e.getSQLState().equals("23000")) {
	            System.out.println("Failed to update contact: Duplicate phone number.");
	        } else {
	            e.printStackTrace();
	        }
	    } finally {
	        // Close resources in finally block
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


	public void updateCompanyName(Employer employer, String cName) throws ClassNotFoundException {

		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = JdbcConnection.connectdatabase();
			String sql = "UPDATE EMPLOYERS SET COMPANYNAME = ? WHERE ID = ? ";

			statement = con.prepareStatement(sql);

			statement.setString(1, cName);
			statement.setInt(2, employer.getId());

			int rowsInserted = statement.executeUpdate();

			if (rowsInserted > 0) {
				System.out.println(" Compay Name Update  successfully.");
			} else {
				System.out.println("Failed to  profile update.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updatePassword(Employer employer, String password) throws ClassNotFoundException {

		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = JdbcConnection.connectdatabase();
			String sql = "UPDATE EMPLOYERS SET PASSWORD = ? WHERE ID = ? ";

			statement = con.prepareStatement(sql);

			statement.setString(1, password);
			statement.setInt(2, employer.getId());

			int rowsInserted = statement.executeUpdate();

			if (rowsInserted > 0) {
				System.out.println(" Password Update  successfully.");
			} else {
				System.out.println("Failed to  Password update.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateRegisterNumber(Employer employer, int rNumber) throws ClassNotFoundException {

		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = JdbcConnection.connectdatabase();
			String sql = "UPDATE EMPLOYERS SET REGISTERNUMBER = ? WHERE Id = ? ";

			statement = con.prepareStatement(sql);

			statement.setInt(1, rNumber);
			statement.setInt(2, employer.getId());

			int rowsInserted = statement.executeUpdate();

			if (rowsInserted > 0) {
				System.out.println(" Company Register Number  Update  successfully.");
			} else {
				System.out.println("Failed to  Company Register Number update.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateDomain(Employer employer, String domin) throws ClassNotFoundException {

		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = JdbcConnection.connectdatabase();
			String sql = "UPDATE EMPLOYERS SET DOMAIN = ? WHERE Id = ? ";

			statement = con.prepareStatement(sql);

			statement.setString(1, domin);
			statement.setInt(2, employer.getId());

			int rowsInserted = statement.executeUpdate();

			if (rowsInserted > 0) {
				System.out.println("Domain Update  successfully.");
			} else {
				System.out.println("Failed to  Domain update.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int getEmployerIdForJob(int jobId) throws ClassNotFoundException {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		int employerId = -1; // Default value if no employer found

		try {
			con = JdbcConnection.connectdatabase();
			String query = "SELECT EMPLOYER_ID FROM jobs WHERE job_id = ?";
			statement = con.prepareStatement(query);
			statement.setInt(1, jobId);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				employerId = resultSet.getInt("EMPLOYER_ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employerId;
	}

	public List<JobSeeker> fetchJobSeekersInfo(Employer employer) throws ClassNotFoundException {
		List<JobSeeker> jobSeekers = new ArrayList<>();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			con = JdbcConnection.connectdatabase();
			String sql = "SELECT " + "e.companyname AS employer_name, " + "j.job_id, " + "ja.application_id, " + // Include
																													// job_application_id
					"j.job_title, " + "js.job_seeker_id, " + "js.FName AS job_seeker_firstname, "
					+ "js.LName AS job_seeker_lastname, " + "js.EMAIL, " + "js.CONTACT_DETAILS, " + "js.EDUCATION, "
					+ "js.EXPERIENCE, " + "js.SKILLS, " + "js.PHONENUMBER, " + "js.DOB, " + "js.GENDER, "
					+ "js.ADDRESS, " + "js.LANGUAGE, " + "js.CERTIFICATION, " + "js.ACHIVEMENTS, " + "js.OBJECTIVE "
					+ "FROM " + "employers e " + "JOIN jobs j ON e.id = j.employer_id "
					+ "JOIN job_applications ja ON j.job_id = ja.job_id "
					+ "JOIN job_seekers js ON ja.job_seeker_id = js.job_seeker_id " + "WHERE " + "e.id = ?";
			statement = con.prepareStatement(sql);
			statement.setInt(1, employer.getId()); // Set the employer ID parameter
			rs = statement.executeQuery();

			if (!rs.next()) {
				System.out.println("No one has applied for the jobs.");
			} else {
				// Print column headers if needed

				// Process the result set
				do {
					int applicationId = rs.getInt("application_id");
					int jobId = rs.getInt("job_id");
					String fName = rs.getString("job_seeker_firstname");
					String lName = rs.getString("job_seeker_lastname");
					String gender = rs.getString("GENDER");
					String education = rs.getString("EDUCATION");
					String language = rs.getString("LANGUAGE");
					String skills = rs.getString("SKILLS");
					String experience = rs.getString("EXPERIENCE");
					String achivements = rs.getString("ACHIVEMENTS");
					String conatcDetails = rs.getString("CONTACT_DETAILS");
					String address = rs.getString("ADDRESS");
					// checking purpose creating sop
					System.out.println("Application ID: " + applicationId);
					System.out.println("Job ID: " + jobId);
					System.out.println("First Name: " + fName);
					System.out.println("Last Name: " + lName);
					System.out.println("Gender: " + gender);
					System.out.println("Education: " + education);
					System.out.println("Language: " + language);
					System.out.println("Skills: " + skills);
					System.out.println("Achievements: " + achivements);
					System.out.println("Contact Details: " + conatcDetails);
					System.out.println("Address: " + address);
					System.out.println("--------------------");

					// Create and add JobSeeker object to the list
					jobSeekers.add(new JobSeeker(applicationId, jobId, fName, lName, gender, education, language,
							skills, experience, achivements, conatcDetails, address));
				} while (rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Close resources in finally block
			try {
				if (rs != null)
					rs.close();
				if (statement != null)
					statement.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return jobSeekers;
	}

	public void searchCandidate() throws ClassNotFoundException, OptionException {
		boolean flag = true;
		do {

			System.out.println("+------------------------------------+");
			System.out.println("|            Search Menu             |");
			System.out.println("+------------------------------------+");
			System.out.println("| 1. Skill                           |");
			System.out.println("| 2. Education                       |");
			System.out.println("| 3. Experience                      |");
			System.out.println("| 4. Back                            |");
			System.out.println("| 5. Exit                            |");
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
				sc.nextLine();
				System.out.println("Enter the skill based search :");
				String skill = sc.nextLine();

				searchCandidates(skill, "skill");
				break;

			}
			case 2: {
				sc.nextLine();
				System.out.println("Enter the education based search :");
				String education = sc.nextLine();

				searchCandidates(education, "education");
				break;

			}
			case 3: {
				sc.nextLine();
				System.out.println("Enter the experience based search :");
				String experience = sc.nextLine();
				searchCandidates(experience, "experience");
				break;

			}

			case 4: {

				emoloyerMenu(this);
				break;
			}
			case 5: {
				System.out.println("Thank you for using Job Portal!  Have a great day!");
				System.exit(0);
				break;

			}
			

			}

		} while (flag);

	}

	public static List<JobSeeker> searchCandidates(String criteria, String searchType) throws ClassNotFoundException {
		List<JobSeeker> matchingCandidates = new ArrayList<>();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			con = JdbcConnection.connectdatabase();
			String sql = "";
			switch (searchType.toLowerCase()) {
			case "skill":
				sql = "SELECT * FROM job_seekers WHERE SKILLS LIKE ?";
				break;
			case "education":
				sql = "SELECT * FROM job_seekers WHERE EDUCATION LIKE ?";
				break;
			case "experience":
				sql = "SELECT * FROM job_seekers WHERE EXPERIENCE LIKE ?";
				break;
			default:
				throw new IllegalArgumentException("Invalid search type: " + searchType);
			}
			statement = con.prepareStatement(sql);
			statement.setString(1, "%" + criteria + "%");
			rs = statement.executeQuery();
			boolean candidateFound = false;
			while (rs.next()) {
				candidateFound = true;
				// Retrieve candidate information from the result set
				int jobSeekerId = rs.getInt("JOB_SEEKER_ID");
				String username = rs.getString("USERNAME");
				String email = rs.getString("EMAIL");
				String skills = rs.getString("SKILLS");
				String education = rs.getString("EDUCATION");
				String experience = rs.getString("EXPERIENCE");
				// Add candidate to the list
				matchingCandidates.add(new JobSeeker(jobSeekerId, username, email, skills, education, experience));
				System.out.println("Job Seeker ID : " + jobSeekerId);
				// System.out.println("Username: " + email);
				System.out.println("Email         : " + email);
				System.out.println("Skills        : " + skills);
				System.out.println("Education     : " + education);
				System.out.println("Experience    : " + experience);
				System.out.println("--------------------");
			}   
			// Print details using streams
//			matchingCandidates.stream().forEach(jobSeeker -> {
//			    System.out.println("Job Seeker ID: " + jobSeeker.getJobSeekerId());
//			    System.out.println("Username: " + jobSeeker.getUsername());
//			    System.out.println("Email: " + jobSeeker.getEmail());
//			    System.out.println("Skills: " + jobSeeker.getSkills());
//			    System.out.println("Education: " + jobSeeker.getEducation());
//			    System.out.println("Experience: " + jobSeeker.getExperience());
//			    System.out.println("--------------------");
//			});

			if (!candidateFound) {
				System.out.println("No candidates found for the given criteria.");
			}
		} catch (SQLException e) {
			// Log the exception instead of printing stack trace
			System.err.println("Error while searching candidates: " + e.getMessage());
		}

		return matchingCandidates;
	}

}
