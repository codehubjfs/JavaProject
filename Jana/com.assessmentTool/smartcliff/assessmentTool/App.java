package com.smartcliff.assessmentTool;

//import java.sql.DriverManager;
import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.InputMismatchException;
import java.util.Scanner;

import com.courseDetail.Assessment;
//import com.courseDetail.Course;
//import com.exceptionDetails.EmailException;
import com.exceptionDetails.InvalidOptionException;
//import com.exceptionDetails.PasswordException;
import com.exceptionDetails.UserNameException;
//import com.exceptionDetails.Validate;
import com.userDetail.Admin;
import com.userDetail.Authentication;
import com.userDetail.Educator;
import com.userDetail.Student;

public class App {
	static Scanner sc = new Scanner(System.in);

	static int eduOrStu;

	public static void main(String[] args) {
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("|                          ASSESSMENT TOOL                            |");
		System.out.println("-----------------------------------------------------------------------");
		try {
			home();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InvalidOptionException e) {
			e.printStackTrace();
		} catch (UserNameException e) {
			e.printStackTrace();
		}
	}

	public static void home() throws SQLException, InvalidOptionException, UserNameException {
//		boolean check = true;
//		while (check) {
		try {
			System.out.println("Log in as?");
			System.out.println("1.Admin");
			System.out.println("2.Educator");
			System.out.println("3.Student");
			System.out.println("4.Exit");
			System.out.println("--------------------------------------------------------------------");
			eduOrStu = Integer.parseInt(sc.next());
			if (eduOrStu == 4) {
				System.out.println("***Exit Successfull***");
				System.exit(0);
			} else if (eduOrStu > 4) {
				System.out.println("***Pease select an appropriate Option!***");
				home();
			} else if (eduOrStu < 4) {
//					check = false;
				login(eduOrStu);

			}
		} catch (NumberFormatException n) {
			System.out.println("***Only Numbers are allowed!***");
			home();
		}
	}

	public static void login(int eduOrStu) throws SQLException, InvalidOptionException, UserNameException {
		Authentication a = new Authentication();
		boolean validated = false;

		do {
			System.out.println("Please enter your Email ID: ");
			String email = sc.next();

			System.out.println("Please enter your password(Ex:Password@123)");
			String password = sc.next();

			switch (eduOrStu) {

			case 1:
				if (email.equals("admin") && password.equals("123")) {
					System.out.println("Welcome Admin");
					validated = true;
					adminMenu();
					break;
				} else {

					System.out.println("Invalid Credentials!");
					validated = false;
					confirmation(eduOrStu);
				}

			case 2:
				Educator e = a.validateEducatorLogin(email, password);
				// boolean validated =false;

				if (e != null) {
					System.out.println("Login Successfull!");
					System.out.println("Welcome " + e.getEfirstName() + " " + e.getelastName());
					validated = true;
					educatorMenu(e);
					break;
				} else {
					System.out.println("Invalid credentials! Please Try Again");
					validated = false;
					confirmation(eduOrStu);
				}

			case 3:
				Student s = a.validateStudentLogin(email, password);
				if (s != null) {
					System.out.println("Login Successfull!");
					System.out.println("Welcome " + s.getSfirstName() + " " + s.getSlastName());
					validated = true;
					studentFunctionalities(s);
					break;
				} else {
					System.out.println("Invalid credentials! Please Try Again");
					validated = false;
					confirmation(eduOrStu);

				}

			}
		} while (!validated);

	}

	public static void confirmation(int eduOrStu) throws SQLException, InvalidOptionException, UserNameException {
		boolean check = true;
		while (check) {
			int n = 0;
			System.out.println("Do you want to continue?");
			System.out.println("1.Yes");
			System.out.println("2.No");
			try {
				n = Integer.parseInt(sc.next());
			} catch (NumberFormatException nf) {
				System.err.println("Only Numbers are allowed!");
				confirmation(eduOrStu);
			}
			if (n == 1) {
				check = false;
				login(eduOrStu);
			} else if (n == 2) {
				System.out.println("Exit Successfull");
				System.exit(0);
			} else if (n > 2) {
				new InvalidOptionException("Please select appropriate Option!");
				confirmation(eduOrStu);
			}

		}
	}

	public static void adminMenu() throws InvalidOptionException, SQLException, UserNameException {

		System.out.println("1.Educator Management");
		System.out.println("2.Student Management");
		System.out.println("3.Course Management");
		System.out.println("4.Logout");
		System.out.println("5.Exit");
		System.out.println("-----------------------------------------------------------------------");
		int admMenu = 0;
		try {
			admMenu = Integer.parseInt(sc.next());
		} catch (NumberFormatException nf) {
			System.err.println("Only Numbers are allowed!");
			adminMenu();
		}
		switch (admMenu) {
		case 1:
			adminFunctionalities(admMenu);
			break;
		case 2:
			adminFunctionalities(admMenu);
			break;
		case 3:
			adminFunctionalities(admMenu);
			break;
		case 4:
			System.out.println("Successfully Logged Out!");
			home();
			break;
		case 5:
			System.out.println("Exit Successfull!");
			break;
		default:
			System.out.println("Please select valid Option!(1-5)");
			adminMenu();
		}

	}

	public static void adminFunctionalities(int admMenu)
			throws SQLException, InvalidOptionException, UserNameException {
		int choice = 0;
		switch (admMenu) {

		case 1:
			System.out.println("----------------------EDUCATOR MANAGEMENT------------------------------");
			System.out.println("1.Add Educator");
			System.out.println("2.Edit Educator");
			System.out.println("3.Remove Educator");
			System.out.println("4.View Educators Detail");
			System.out.println("5.Back");
			System.out.println("6.Exit");
			System.out.println("-----------------------------------------------------------------------");

			try {
				choice = Integer.parseInt(sc.next());
			} catch (NumberFormatException nf) {
				System.err.println("Only Numbers are allowed!");
				adminFunctionalities(admMenu);
			}

			switch (choice) {
			case 1:
				Admin.addEducator();
				break;
			case 2:
				System.out.println("Enter Educator id");
				int id = 0;
				try {
					id = Integer.parseInt(sc.next());
				} catch (NumberFormatException nf) {
					System.err.println("Only Numbers are allowed!");
					adminFunctionalities(admMenu);
				}
				if (Admin.checkEducator(id)) {
					//System.out.println("1.Edit email");
					System.out.println("1.Edit Password");
					System.out.println("2.Edit First Name");
					System.out.println("3.Edit Last Name");
					System.out.println("4.Edit City");
					System.out.println("5.Edit Country");
					System.out.println("6.Edit course Id");
					System.out.println("7.Back");
					System.out.println("-----------------------------------------------------------------------");
				} else {
					System.out.println("Educator ID doesn't Exist!");
					adminFunctionalities(admMenu);
				}
				int opt = 0;

				try {
					opt = Integer.parseInt(sc.next());
				} catch (NumberFormatException nf) {
					System.err.println("Only Numbers are allowed!");
					adminFunctionalities(admMenu);
				}
				switch (opt) {
//				case 1:
//					Admin.editEmail(id);
//					break;
				case 1:
					Admin.editPassword(id);
					break;
				case 2:
					Admin.editFirstName(id);
					break;
				case 3:
					Admin.editLastName(id);
					break;
				case 4:
					Admin.editCity(id);
					break;
				case 5:
					Admin.editCountry(id);
					break;
				case 6:
					Admin.editCid(id);
					break;
				case 7:
					adminMenu();
					break;
				default:
					System.out.println("Please Select Valid Option!(1-7)");
					adminFunctionalities(admMenu);
					break;

				}
				break;
			case 3:
				System.out.println("Enter Employee Id to remove:");
				int delete = sc.nextInt();
				if (Admin.checkEducator(delete)) {
					Admin.removeEducator(delete);
					break;
				} else {
					System.err.println("Educator ID Not Found!");
					adminFunctionalities(admMenu);
				}
				break;
			case 4:
				Admin.showEducators();
				break;
			case 5:
				adminMenu();
				break;
			case 6:
				System.out.println("Exit Success!");
				System.exit(0);
			default:
				System.out.println("Please Select Valid Option!(1-6)");
				adminFunctionalities(admMenu);
				break;

			}
		case 2:
			System.out.println("----------------------STUDENT MANAGEMENT------------------------------");
			System.out.println("1.Add Student");
			System.out.println("2.Edit Student");
			System.out.println("3.Remove Student");
			System.out.println("4.View Students Detail");
			System.out.println("5.Back");
			System.out.println("6.Exit");
			System.out.println("-----------------------------------------------------------------------");
			try {
				choice = Integer.parseInt(sc.next());
			} catch (NumberFormatException nf) {
				System.err.println("Only Numbers are allowed!");
				adminFunctionalities(admMenu);
			}

			switch (choice) {
			case 1:
				Admin.addStudent();
				break;
			case 2:
				System.out.println("Enter Student ID");
				int id = 0;
				try {
					id = Integer.parseInt(sc.next());
				} catch (NumberFormatException nf) {
					System.err.println("Only Numbers are allowed!");
					adminFunctionalities(admMenu);
				}

				if (Admin.checkStudent(id)) {

					System.out.println("1.Edit email");
					System.out.println("2.Edit Password");
					System.out.println("3.Edit First Name");
					System.out.println("4.Edit Last Name");
					System.out.println("5.Edit City");
					System.out.println("6.Edit Country");
					System.out.println("7.Edit course Id");
					System.out.println("8.Back ");
					System.out.println("-----------------------------------------------------------------------");
				} else {
					System.err.println("Student ID not Found!");
					adminFunctionalities(2);
					break;
				}
				int opt = 0;
				try {
					opt = Integer.parseInt(sc.next());
				} catch (NumberFormatException nf) {
					System.err.println("Only Numbers are allowed!");
					adminFunctionalities(admMenu);
				}
				switch (opt) {
				case 1:
					Admin.editStuEmail(id);
					break;
				case 2:
					Admin.editStuPassword(id);
					break;
				case 3:
					Admin.editStuFirstName(id);
					break;
				case 4:
					Admin.editStuLastName(id);
					break;
				case 5:
					Admin.editStuCity(id);
					break;
				case 6:
					Admin.editStuCountry(id);
					break;
				case 7:
					Admin.editStuCid(id);
					break;
				case 8:
					adminFunctionalities(2);
					break;
				default:
					System.out.println("Please select valid option(1-8)");
					adminFunctionalities(2);
					break;
				}
				break;
			case 3:
				System.out.println("Enter student Id to remove:");
				int delete = 0;
				try {
					delete = Integer.parseInt(sc.next());
				} catch (NumberFormatException nf) {
					System.err.println("Only Numbers are allowed!");
					adminFunctionalities(admMenu);
				}
				if (Admin.checkStudent(delete)) {
					Admin.removeStudent(delete);
				} else {
					System.err.println("Student ID not Found!");
					adminFunctionalities(2);
					break;
				}
			case 4:
				Admin.showStudents();
				break;
			case 5:
				adminMenu();
				break;
			case 6:
				System.out.println("Exit Success!");
				System.exit(0);
			default:
				System.out.println("Please Select Valid Option!(1-6)");
				adminFunctionalities(admMenu);
				break;

			}

		case 3:
			System.out.println("----------------------COURSE MANAGEMENT------------------------------");
			System.out.println("1..Add Course");
			System.out.println("2.Edit Course");
			System.out.println("3.Remove Course");
			System.out.println("4.View Course Details");
			System.out.println("5.Back");
			System.out.println("6.Exit");
			System.out.println("-----------------------------------------------------------------------");
			try {
				choice = Integer.parseInt(sc.next());
			} catch (NumberFormatException nf) {
				System.err.println("Only Numbers are allowed!");
				adminFunctionalities(admMenu);
			}

			switch (choice) {

			case 1:
				Admin.addCourse();
				break;
			case 2:

				int cid = 0;
				while (true) {
					try {
						System.out.println("Enter Course ID");
						cid = Integer.parseInt(sc.next());
						break;
					} catch (NumberFormatException nf) {
						System.err.println("Only Numbers are allowed!");
					}
				}
				Admin.editCourseName(cid);
				break;
			case 3:

				int delete = 0;
				while (true) {
					try {
						System.out.println("Enter course Id to remove:");
						delete = Integer.parseInt(sc.next());
						break;
					} catch (NumberFormatException nf) {
						System.err.println("Only Numbers are allowed!");
					}
				}

				if (Admin.checkCourse(delete)) {
					Admin.removeCourse(delete);
				} else {
					System.err.println("Course ID not Found!");
					adminFunctionalities(admMenu);
					break;
				}
			case 4:
				Admin.ViewCourses();
				break;
			case 5:
				adminMenu();
				break;
			case 6:
				System.out.println("Exit Successfull!");
				System.exit(0);
			default:
				System.out.println("Please Select Valid Option!(1-6)");
				adminFunctionalities(admMenu);
				break;

			}
		case 4:
			home();
			break;
		case 5:
			System.out.println("Exit Successfull!");
			System.exit(0);

		default:
			new InvalidOptionException("Please Select Valid Option!(1-5)");
			adminFunctionalities(admMenu);
			break;
		}

	}

	public static void educatorMenu(Educator e) throws SQLException, InvalidOptionException, UserNameException {

		System.out.println("----------------------EDUCATOR MENU--------------------------------");
		System.out.println("1.Assessment Management");
		System.out.println("2.Question Management");
		System.out.println("3.Logout");
		System.out.println("4.Exit");
		int eduChoice = 0;
		try {
			eduChoice = Integer.parseInt(sc.next());
		} catch (NumberFormatException nf) {
			System.err.println("Only Numbers are allowed!");
			educatorMenu(e);
		}

		if (eduChoice == 1 || eduChoice == 2) {
			educatorFunctionalities(eduChoice, e);
		}
		if(eduChoice==3) {
			System.out.println("Successfully Logged Out!");
			home();
		}
		if(eduChoice==4) {
			System.out.println("Exit Successfull!");
			System.exit(0);
		}
		
				else {
			System.out.println("Please Select Valid Option!(1-4)");
			educatorMenu(e);
		}
	}

	public static void educatorFunctionalities(int eduChoice, Educator e)
			throws SQLException, InvalidOptionException, UserNameException {
		switch (eduChoice) {

		case 1:

			System.out.println("1.Add Assessment");
			System.out.println("2.Remove Assessment");
			System.out.println("3.Edit Assessment");
			System.out.println("4.View Assessment");
			System.out.println("5.View All Assessments");
			System.out.println("6.Back");
			System.out.println("7.Logout");
			System.out.println("8.Exit");
			int eduOpt1 = 0;
			try {
				eduOpt1 = Integer.parseInt(sc.next());
			} catch (NumberFormatException nf) {
				System.err.println("Only Numbers are allowed!");
				educatorFunctionalities(eduChoice, e);
			}

			switch (eduOpt1) {

			case 1:
				e.addAssessment();
				break;
			case 2:
				e.removeAssessment();
				break;
			case 3:
				e.editAssessment();
				break;
			case 4:

				int aId = 0;
				while (true) {
					try {
						System.out.println("Enter Assessment ID");
						aId = Integer.parseInt(sc.next());
						break;
					} catch (NumberFormatException nf) {
						System.err.println("Only Numbers are allowed!");
					}
				}
				e.viewAssessmentById(aId);
				break;
			case 5:
				e.viewAssessment();
				break;
			case 6:
				educatorMenu(e);
				break;
			case 7:
				System.out.println("Successfully Logged Out!");
				home();
				break;
			case 8:
				System.out.println("Exit Successfull!");
				System.exit(0);
				break;
			default:
				System.out.println("Please Select a Valid Option!(1-8)");
				educatorFunctionalities(eduChoice, e);

			}
			break;
		case 2:
			System.out.println("1.Add Question to Question Bank");
			System.out.println("2.Add/Assign Questions to Assessment");
			System.out.println("3.Remove Question");
			System.out.println("4.Edit Question");
			System.out.println("5.View Question Bank");
			System.out.println("6.View Assignment Questions");
			System.out.println("7.View Student Performance");
			System.out.println("8.Back");
			System.out.println("9.Logout");
			System.out.println("10.Exit");
			int eduOpt2 = 0;
			try {
				eduOpt2 = Integer.parseInt(sc.next());
			} catch (NumberFormatException nf) {
				System.err.println("Only Numbers are allowed!");
				educatorMenu(e);
			}

			switch (eduOpt2) {

			case 1:
				e.addQuestion();
				break;
			case 2:
				int aId = 0;
				while(true) {
				System.out.println("Enter Assessment ID");
				try {
					aId = Integer.parseInt(sc.next());
					break;
				} catch (NumberFormatException nf) {
					System.err.println("Only Numbers are allowed!");
					//educatorFunctionalities(eduChoice, e);
				}
				}
				Assessment a = e.checkAssessment(aId);
				if (a != null) {
					e.assignQuestion(a);
				} else {
					System.out.println("No such Assessment was Found!");
					educatorFunctionalities(eduChoice, e);
				}

			case 3:
				e.removeQuestion();
				break;
			case 4:
				e.editQuestions();
				break;
			case 5:
				e.viewQuestions();
				break;
			case 6:
				System.out.println("Enter Assessment ID");
				int assessId = 0;
				try {
					assessId = Integer.parseInt(sc.next());
				} catch (NumberFormatException nf) {
					System.err.println("Only Numbers are allowed!");
					educatorFunctionalities(eduChoice, e);
				}
				Assessment assessObj = e.checkAssessment(assessId);
				if (assessObj != null) {
					e.viewAssessmentQuestions(assessObj);
				} else {
					System.out.println("No Such Assessment was Found!");
					educatorFunctionalities(eduChoice, e);
				}
				break;
			case 7:
				e.viewPerformance();
				break;
			case 8:
				educatorMenu(e);
				break;
			case 9:
				System.out.println("Successfully Logged Out!");
				home();
				break;
			case 10:
				System.out.println("Exit Successfull");

				System.exit(0);
			default:
				System.out.println("Please select valid Option(1-10)");
				educatorFunctionalities(eduChoice, e);
				break;

			}
//		case 3:
//			System.out.println("Successfully Logged Out!");
//			home();
//			break;
//		case 4:
//			System.out.println("Exit Successfull!");
//			System.exit(0);
		}
	}

	public static void studentFunctionalities(Student s)
			throws InvalidOptionException, SQLException, UserNameException {
		System.out.println("-----------------------------------------------------");
		System.out.println("----------------------ASSESSMENT TOOL------------------------------");
		System.out.println("1.View Assessments");
		System.out.println("2.Take Assessment");
		System.out.println("3.View Marks");
		System.out.println("4.Logout");
		System.out.println("5.Exit");

		int stuOpt = 0;
		try {
			stuOpt = Integer.parseInt(sc.next());
		} catch (NumberFormatException nf) {
			System.err.println("Only Numbers are allowed!");
			studentFunctionalities(s);
		}

		switch (stuOpt) {
		case 1:
			System.out.println("--------------------------------Available Assessments------------------------------");
			Student.showAssessmentDetails(s);
			break;
		case 2:

			int aId = 0;
			while (true) {
				try {
					System.out.println("Enter Assessment ID");
					aId = Integer.parseInt(sc.next());
					break;
				} catch (NumberFormatException nf) {
					System.err.println("Only Numbers are allowed!");
				}
			}
			Assessment a = Educator.checkAssessment(aId);
			if (a != null) {
				Student.takeAssessment(a, s);
			} else {
				System.err.println("No such Assessment were Found!");
				studentFunctionalities(s);
				break;
			}
			break;
		case 3:
			// System.out.println("Enter Assessment ID");
			int assessId = 0;
			while (true) {
				try {
					System.out.println("Enter Assessment ID");
					assessId = Integer.parseInt(sc.next());
					break;
				} catch (NumberFormatException nf) {
					System.err.println("Only Numbers are allowed!");
//					studentFunctionalities(s);
				}
			}
			Assessment aObj = Educator.checkAssessment(assessId);
			if (aObj != null) {
				Student.ViewMarks(s, aObj);
				break;
			} else {
				System.err.println("No such Assessments were Found!");
				App.studentFunctionalities(s);
			}
			break;
		case 4:
			System.out.println("Successfully Logged Out!");
			home();
			break;
		case 5:
			System.out.println("Exit Successfull");
			System.exit(0);
		default:
			System.out.println("Please Select valid Option)(0-5)");
			studentFunctionalities(s);
		}
	}

}
