package com.jobportal.functionality;

import java.sql.*;
import java.util.Scanner;
import com.jdbcservice.JdbcConnection;
import com.jobportal.users.*;
import com.messageandnoficationservices.Message;
import com.usersregisterlogin.LoginRegister;
import com.jobportal.listings.*;
import com.userdefiendexception.*;

public class Main {
	static Scanner sc = new Scanner(System.in);

	public void runProject() throws CompanyNameValidationException {

		boolean flag = true;
		do {
			try {
				System.out.println("+------------------------------------+");
				System.out.println("|             Main Menu              |");
				System.out.println("+------------------------------------+");
				System.out.println("| 1. Admin                           |");
				System.out.println("| 2. Employer                        |");
				System.out.println("| 3. Jobseeker                       |");
				System.out.println("| 4. Exit                            |");
				System.out.println("+------------------------------------+");
				System.out.print("Please select an option: ");

				int option = Validation.validateOption(sc.next());
				sc.nextLine();
				switch (option) {
				case 1: {

					admin();
					break;
				}
				case 2: {

					employee();

					break;
				}
				case 3: {

					jobSeeker();

					break;
				}
				case 4: {
					System.out.println("Thank you for using Job Portal!  Have a great day!");
					System.exit(0);

					break;

				}
				default:
					System.out.println("Invalid choice. Please try again.");

				}

			} catch (OptionException | ClassNotFoundException | SQLException | CheckUserNameException
					| JobIdAlreadyExistsException e) {
				System.out.println(e.getMessage());
			}
		} while (flag);
	}

	public static void admin() {
		boolean flag = true;
		do {
			try {

				System.out.println("+------------------------------------+");
				System.out.println("|           Admin Menu               |");
				System.out.println("+------------------------------------+");
				System.out.println("| 1. Admin User Create               |");
				System.out.println("| 2. Admin User Login                |");
				System.out.println("| 3. Exit                            |");
				System.out.println("+------------------------------------+");
				System.out.print("Please select an option: ");
				int option = Validation.validateOption(sc.next());
				sc.nextLine();

				switch (option) {

				case 1: {
					String userName, email, password;
					while (true) {
						System.out.println("Enter the username :");

						userName = sc.nextLine();

						if (!Validation.checkUserName(userName)) {
							System.out.println(
									"....Invalid username format! [Usernames must be 3-20 characters long and can contain letters, digits, underscores, and hyphens....");
							continue;
						} else {
							break;
						}
					}
					System.out.println(
							"Enter the password : [password format! Passwords must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.]");

					while (true) {
						System.out.println("Enter the password");

						password = sc.nextLine();
						if (!Validation.checkPassword(password)) {
							System.out.println(
									"....Invalid password format! Passwords must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character....");

						} else {
							break;
						}
					}

					while (true) {
						System.out.println("Enter the email :");
						email = sc.nextLine();
						if (!Validation.checkEmail(email)) {
							System.out.println("...Invalid email format! Please enter valid email !");

						} else {
							break;
						}
					}

					LoginRegister.registerAdmin(new Admin(userName, email, password, UserType.ADMIN));

					break;
				}
				case 2: {
					String userName, password;
					while (true) {
						System.out.println("Enter the username :");

						userName = sc.nextLine();

						try {
							if (!Validation.checkUserName(userName)) {
								System.out.println(
										"Invalid username format! Usernames must be 3-20 characters long and can contain letters, digits, underscores, and hyphens !");
								continue;
							} else {
								break;
							}
						} catch (CheckUserNameException e) {

							e.printStackTrace();
						}
					}
					while (true) {
						System.out.println("Enter the password :");

						password = sc.nextLine();
						if (!Validation.checkPassword(password)) {
							System.out.println("Password Wrong !");

						} else {
							break;
						}
					}

					Object loggedIn = LoginRegister.userslogin(userName, password, UserType.ADMIN);

					if (loggedIn instanceof Admin) {
						Admin admin = (Admin) loggedIn;
						System.out.println(admin.getAdminID());
						System.out.println("Login successful.");

					} else {
						System.out.println("Login failed. Incorrect username or password !");

					}

					break;
				}

				case 3: {
					System.out.println("Thank you for using Job Portal!  Have a great day!");
					System.exit(0);
					break;

				}
				default:
					System.out.println("Invalid choice. Please try again !");

				}
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println(e.getMessage());

			} catch (OptionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (CheckUserNameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} while (flag);
	}

	public void employee() throws com.userdefiendexception.JobIdAlreadyExistsException, CompanyNameValidationException {
		boolean flag = false;
		do {
			try {

				System.out.println("+------------------------------------+");
				System.out.println("|        Employer Menu               |");
				System.out.println("+------------------------------------+");
				System.out.println("| 1. Employer User Registration      |");
				System.out.println("| 2. Employer User Login             |");
				System.out.println("| 3. Back                            |");
				System.out.println("| 4. Exit                            |");
				System.out.println("+------------------------------------+");
				System.out.print("Please select an option : ");
				Employer em = new Employer();
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
				} // Switch case for handling user selection

				switch (option) {

				case 1: {
					em.emloyerUserCreate();
					break;

				}

				case 2: {

					em.employersUserLogin();

					break;
				}
				case 3: {

					new Main().runProject();
					break;
				}
				case 4: {
					System.out.println("Thank you for using Job Portal!  Have a great day!");
					System.exit(0);

					break;
				}
				default: {

					System.out.println("Pleace Enter the correct option");

				}
				}
			} catch (ClassNotFoundException | SQLException |CheckUserNameException e) {
				System.out.println(e.getMessage());

			}

		} while (flag);
	}

	public void jobSeeker() throws ClassNotFoundException, SQLException, CheckUserNameException, OptionException, CompanyNameValidationException {
		boolean flag = true;
		do {
			System.out.println("+------------------------------------+");
			System.out.println("|        jobSeeker Menu              |");
			System.out.println("+------------------------------------+");
			System.out.println("| 1. Job Seeker Registration         |");
			System.out.println("| 2. Job Seeker Login                |");
			System.out.println("| 3. Back                            |");
			System.out.println("| 4. Exit                            |");
			System.out.println("+------------------------------------+");
			System.out.print("Please select an option: ");
			JobSeeker seeker = new JobSeeker();
			int option = Validation.validateOption(sc.next());
			switch (option) {
			case 1: {

				seeker.jobSeekerRegister();

			}
			case 2: {
				seeker.jobSeekerLogin();

				break;
			}
			case 3: {

				runProject();

				break;
			}
			case 4: {
				System.out.println("Thank you for using Job Portal!  Have a great day!");

				System.exit(0);
				break;

			}
			default: {

				System.out.println("Pleace Enter the correct option");

			}

			}

		} while (flag);
	}

	public static void main(String[] arg) throws ClassNotFoundException, SQLException, InvalidInputException,
			CheckUserNameException, JobIdAlreadyExistsException, CompanyNameValidationException {
		Main main = new Main();
		main.runProject();

	}

}
