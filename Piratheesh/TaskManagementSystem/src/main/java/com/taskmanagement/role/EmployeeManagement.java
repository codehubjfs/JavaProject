package com.taskmanagement.role;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.taskmanagement.authentication.Validate;
import com.taskmanagement.exception.EmailException;
import com.taskmanagement.exception.InvalidInputException;
import com.taskmanagement.exception.InvalidNumberException;
import com.taskmanagement.exception.NumberInputException;
import com.taskmanagement.exception.PasswordException;
import com.taskmanagement.exception.UserNameException;

public class EmployeeManagement {

	public static int optionCheck(int option, Scanner sc) {

		boolean inputflag = true;
		while (inputflag) {
			try {
				System.out.println("Enter your choice: ");
				option = Integer.parseInt(sc.next());
				System.out.println();

				if (option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6
						&& option != 7 && option != 8) {
					throw new InvalidNumberException("Please enter a valid number");
				}
				inputflag = false;
			} catch (NumberFormatException e) {
				System.out.println("You have entered a character!!!... Please enter a number!");
				System.out.println();
				sc.nextLine();

			} catch (InvalidNumberException e) {
				System.out.println("!" + e.getMessage() + "!");
			}

		}
		return option;
	}

	public static boolean adminmanagement(Scanner sc) {
		boolean flag = true;
		while (flag) {
			// menu of Admin
			System.out.println("*".repeat(200));
			System.out.println("1.Add Employee");
			System.out.println("2.View All Employees");
			System.out.println("3.View Employee");
			System.out.println("4.View Managers");
			System.out.println("5.Edit Employee");
			System.out.println("6.Delete Employee");
			System.out.println("7.Add Admin");
			System.out.println("8.Back to Admin Menu");

			System.out.println("*".repeat(200));
			int option = 0;

			try {

				option = optionCheck(option, sc);

				switch (option) {
				case 1:

					addEmployee(sc);

					break;

				case 2:
					
					System.out.println("Every Employee:  ");
					Admin.showeveEmp();
					break;

				case 3:
					
					System.out.println("Employees: ");
					Admin.showemp();
					break;

				case 4:

					System.out.println("Managers: ");
					Admin.showmag();
					break;

				case 5:
					
					RoleCorrection.Editop(sc);
					break;

				case 6:

					
					deleteEmployee(sc);
					break;

				case 7:

					addAdmin(sc);
					break;

				case 8:
					flag = false;

					System.out.println("Getting back to Menu");

					break;
				default:
					System.out.println("Invalid Option");
				}

			} catch (InvalidInputException e) {
				System.out.println("Invalid input! " + e.getMessage());
			}

			catch (Exception e) {
				System.out.println("An error occurred: " + e.getMessage());
				e.printStackTrace();
			}
		}

		return false;

	}
	
	private static List<Employee> employeeCollection = new ArrayList<>();

	private static void addEmployee(Scanner sc) throws InvalidInputException {
	    String mail = null;
	    String password = null;
	    String role = null;
	    String number = null;
	    String gender = null;
	    String name = null;
	    try {
	        boolean flag = true;
	        System.out.println("Enter the required details to add a new employee.");

	        while (flag) {
	            System.out.println("Name: ");
	            name = sc.next();
	            try {
	                Validate.userNameValidate(name);
	                flag = false;
	            } catch (UserNameException e) {
	                System.out.println(e.getMessage());
	            }
	        }

	        while (!flag) {
	            System.out.println("Email Id: ");
	            mail = sc.next();
	            try {
	                Validate.validateEmailValidate(mail);
	                flag = true;
	            } catch (EmailException e) {
	                System.out.println(e.getMessage());
	            }
	        }

	        while (flag) {
	            System.out.println("Password length must be at least 8 characters\n"
	                    + "Password must contain at least one uppercase character\n"
	                    + "Password must contain at least one special character: !@#$%^&*()_+\n"
	                    + "Password must contain at least one digit");
	            System.out.println("Password (Eg: 'Password1$'): ");
	            password = sc.next();
	            try {
	                Validate.passwordValidate(password);
	                flag = false;
	            } catch (PasswordException e) {
	                System.out.println(e.getMessage());
	            }
	        }

	        gender = genderCheck(gender, sc);

	        while (!flag) {
	            System.out.println("Phone number: ");
	            number = sc.next();
	            try {
	                Validate.numberValidate(number);
	                flag = true;
	            } catch (NumberInputException e) {
	                System.out.println(e.getMessage());
	            }
	        }

	        System.out.println("City: ");
	        String city = sc.next();

	        role = roleCheck(role, sc);

	      

	        System.out.println("Enter HireDate (dd-MMM-yyyy): ");
	        String hiredate = sc.next();

	        Admin.showmag();
	        System.out.println("Enter Manager ID: ");
	        int magid = sc.nextInt();

	        Employee emp = new Employee(name, mail, gender, number, city, password, role, hiredate, magid);
	        employeeCollection.add(emp); // Add employee to collection

	        System.out.println("Employee added to database. Do you want to add another employee? (yes/no): ");
	        String response = sc.next();
	        if (!response.equalsIgnoreCase("yes")) {
	            Admin.addEmpList(employeeCollection); // Batch insert when user is done
	            employeeCollection.clear(); // Clear the collection after insertion
	        }

	    } catch (Exception e) {
	        System.out.println("An error occurred: " + e.getMessage());
	        e.printStackTrace();
	    }
	}


//	private static void addEmployee(Scanner sc) throws InvalidInputException {
//
//		String mail = null;
//		String password = null;
//		String role = null;
//		String number = null;
//		String gender=null;
//		String name=null;
//		try {
//
//			boolean flag = true;
//
//			System.out.println("Enter the required details to add a new employee.");
//
//			while(flag) {
//				System.out.println("Name : ");
//				name = sc.next();
//				try {
//					Validate.userNameValidate(name);
//					flag=false;
//				}catch(UserNameException e) {
//					System.out.println(e.getMessage());
//				}
//			}
//			
//
//			while (!flag) {
//				System.out.println("Email Id : ");
//				mail = sc.next();
//				try {
//					Validate.validateEmailValidate(mail);
//					flag = true;
//				} catch (EmailException e) {
//					System.out.println(e.getMessage());
//				}
//			}
//
//			while (flag) {
//				System.out.println(
//						"Password length must be at least 8 characters\nPassword length must be at least 8 characters\nPassword must contain at least one uppercase character\n"
//								+ "Password must contain at least one special character: !@#$%^&*()_+\nPassword must contain at least one digit");
//				System.out.println("Password (Eg:'Password1$') : ");
//				password = sc.next();
//				try {
//					Validate.passwordValidate(password);
//					flag = false;
//
//				} catch (PasswordException e) {
//					System.out.println(e.getMessage());
//
//				}
//
//			}
//			
//			gender=genderCheck(gender,sc);
//
//			while (!flag) {
//
//				System.out.println("Phone number : ");
//				number = sc.next();
//				try {
//					Validate.numberValidate(number);
//					flag = true;
//				} catch (NumberInputException e) {
//					System.out.println(e.getMessage());
//				}
//
//			}
//
//			System.out.println("City : ");
//			String city = sc.next();
//
//			role = roleCheck(role, sc);
//
////			System.out.println("Enter Task Status (1-Not Assinged): ");
//			int status = 1;
//
//			System.out.println("Enter HireDate (dd-MMM-yyyy): ");
//			String  hiredate = sc.next();
//
//			Admin.showmag();
//			System.out.println("Enter Manager ID: ");
//			int magid = sc.nextInt();
//
//			Employee emp = new Employee(name, mail, gender, number, city, password, role, status, hiredate, magid);
//			Admin.addEmpList(emp);
//
//		} catch (Exception e) {
//			System.out.println("An error occurred: " + e.getMessage());
//			e.printStackTrace();
//		}
//	}

	public static String roleCheck(String role, Scanner sc) {
		boolean flag = true;
		while (flag) {
			System.out.println("Role (Manager/Employee) : ");
			role = sc.next().toLowerCase();
			if (role.equalsIgnoreCase("manager") || role.equalsIgnoreCase("employee")) {
				flag = false;
			} else {
				System.out.println("Enter The Correct Role...");
			}
		}
		return role;

	}
	
	private static String genderCheck(String gender,Scanner sc) {
		boolean flag=true;
		while(flag) {
			System.out.println("Gender(M/F/T) : ");
			gender = sc.next().toUpperCase();
			if(gender.equals("M")||gender.equals("F")||gender.equals("T")) {
				flag=false;
			}else {
				System.out.println("Enter Correct Gender....");
			}
		}
		return gender;
	}

	private static void deleteEmployee(Scanner sc) throws InvalidInputException {
	    int emp_ID = 0;
	    boolean flag = true;
	    while (flag) {
	        try {
	            Admin.showeveEmp();
	            System.out.println("Enter the Employee ID to delete:");
	            emp_ID = sc.nextInt();
	            sc.nextLine(); // Clear the newline character

	            System.out.println("Are you sure you want to delete Employee ID " + emp_ID + "? (yes/no):");
	            String confirmation = sc.nextLine().trim().toLowerCase();
	            if (confirmation.equals("yes")) {
	                Admin.delemp(emp_ID);
	                flag = false;
	            } else {
	                System.out.println("Deletion cancelled.");
	                flag = false;
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid input! Please enter a valid number.");
	            sc.nextLine(); // Clear the invalid input
	        }
	    }
	    
	}
	    

	private static void addAdmin(Scanner sc) throws InvalidInputException {
		String ad_mail =null;
		String ad_password =null;
		String ad_name=null;
		try {

			boolean flag = true;

			System.out.println("Enter the Required Details to Add a New Admin.");

			while (flag) {
	            System.out.println("Name: ");
	            ad_name = sc.next();
	            try {
	                Validate.userNameValidate(ad_name);
	                flag = false;
	            } catch (UserNameException e) {
	                System.out.println(e.getMessage());
	            }
	        }
			while (!flag) {
				System.out.println("Email Id: ");
				ad_mail = sc.next();
				try {
					Validate.validateEmailValidate(ad_mail);
					flag = true;
				} catch (EmailException e) {
					System.out.println(e.getMessage());
				}
			}

			while (flag) {
				System.out.println("Password: ");
				ad_password = sc.next();
				try {
					Validate.passwordValidate(ad_password);
					flag = false;

				} catch (PasswordException e) {
					System.out.println(e.getMessage());

				}

			}

			Admin admin = new Admin(ad_name, ad_mail, ad_password);
			Admin.addadmin(admin);
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
			e.printStackTrace();
		}

	}
}
