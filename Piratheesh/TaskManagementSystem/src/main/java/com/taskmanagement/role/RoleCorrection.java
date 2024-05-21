package com.taskmanagement.role;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.taskmanagement.authentication.Validate;
import com.taskmanagement.exception.InvalidNumberException;
import com.taskmanagement.exception.NumberInputException;
import com.taskmanagement.exception.UserNameException;

public class RoleCorrection {

	public static int editCheck(int op1, Scanner sc) {

		boolean inputflag = true;
		while (inputflag) {
			try {
				System.out.println("Enter your choice: ");
				op1 = Integer.parseInt(sc.next());
				System.out.println();

				if (op1 != 1 && op1 != 2 && op1 != 3 && op1 != 4 && op1 != 5) {
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
		return op1;
	}

	public static int EditCheck(int Editop1, Scanner sc) {

		boolean inputflag = true;
		while (inputflag) {
			try {
				System.out.println("Enter your choice: ");
				Editop1 = Integer.parseInt(sc.next());
				System.out.println();

				if (Editop1 != 1 && Editop1 != 2 && Editop1 != 3) {
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
		return Editop1;
	}

	public static boolean Editop(Scanner sc) {
		boolean flag = true;
		while (flag) {
			System.out.println("*".repeat(200));
			System.out.println("1.Edit For Employee");
			System.out.println("2.Edit For Manager");
			System.out.println("3.Back");
			System.out.println("*".repeat(200));
			int op1 = 0;

			op1 = EditCheck(op1, sc);

			switch (op1) {
			case 1:
				// for employee
				RoleCorrection.editemp(sc);

				break;

			case 2:
				// for manager
				RoleCorrection.editMag(sc);
				break;

			case 3:
				flag = false;

				System.out.println("Getting back to Menu");

				break;
			default:
				System.out.println("Invalid Option");
			}
		}

		return false;

	}

	public static boolean editemp(Scanner sc) {
		boolean flag = true;
		while (flag) {
			// editing employee
			System.out.println("*".repeat(200));
			System.out.println("Editing Employee: ");
			System.out.println("1.Edit Name");
			System.out.println("2.Edit Phone Number");
			System.out.println("3.Edit Role");
			System.out.println("4.Edit City");
			System.out.println("5.Back");
			System.out.println("*".repeat(200));
			int op1 = 0;
			boolean flag1 = true;
			op1 = editCheck(op1, sc);

			switch (op1) {
			case 1:
				int id=0;
				String name=null;
				while(flag1) {
					try {
						Admin.showemp();
						System.out.println("Enter Employee Id: ");
						id = Integer.parseInt(sc.next());
						flag1=false;
					}catch(NumberFormatException e) {
						System.out.println("Enter Valid Employee ID.!");
					}
				}
				while(!flag1) {
					System.out.println("Enter New Name: ");
					name = sc.next();
					try {
						Validate.userNameValidate(name);
		                flag = true;
		                break;
					}catch(UserNameException e) {
						System.out.println(e.getMessage());
					}
				}
				
				Admin.editNameEmp(id, name);
				break;

			case 2:
				
				
				int id2=0;
				String phone=null;
				while(flag1) {
					try {
						Admin.showemp();
						System.out.println("Enter Employee Id: ");
						id2 = Integer.parseInt(sc.next());
						flag1=false;
					}catch(NumberFormatException e) {
						System.out.println("Enter Valid Employee ID.!");
					}
				}
				while(!flag1) {
					System.out.println("Enter New Phone Number: ");
					phone = sc.next();
					try {
						Validate.numberValidate(phone);
		                flag1 = true;
						
					} catch (NumberInputException e) {
						 System.out.println(e.getMessage());
					}
				}
				Admin.editPhoneEmp(id2, phone);
				break;

			case 3:
				
				int id3=0;
				String role=null;
				while(flag1) {
					try {
						Admin.showemp();
						System.out.println("Enter Employee Id: ");
						id2 = Integer.parseInt(sc.next());
						flag1=false;
					}catch(NumberFormatException e) {
						System.out.println("Enter Employee ID.!");
					}
				}
				role=EmployeeManagement.roleCheck(role,sc);
				Admin.editRoleEmp(id3, role);
				break;

			case 4:
				int id4=0;
				String city=null;
				while(flag1) {
					try {
						Admin.showemp();
						System.out.println("Enter Employee Id: ");
						id4 = Integer.parseInt(sc.next());
						flag1=false;
					}catch(NumberFormatException e) {
						System.out.println("Enter Employee ID.!");
					}
				}
				try {
					System.out.println("Enter New City: ");
					city = sc.next();
				} catch (InputMismatchException e) {
					System.out.println("Invalid input. Please enter a valid employee ID.");
				}
				Admin.editCityEmp(id4, city);
				break;

			case 5:
				flag = false;
				System.out.println("Getting back to Menu");
				break;

			default:
				System.out.println("Invalid Option");
			}
		}

		return false;

	}

	public static boolean editMag(Scanner sc) {
		boolean flag = true;
		while (flag) {
			// editing manager
			System.out.println("*".repeat(200));
			System.out.println("Editing Manager: ");
			System.out.println("1.Edit Name");
			System.out.println("2.Edit Phone Number");
			System.out.println("3.Edit Role");
			System.out.println("4.Edit City");
			System.out.println("5.Back");
			System.out.println("*".repeat(200));
			int op1 = 0;

			boolean flag1 = true;
			op1 = editCheck(op1, sc);
			
			switch (op1) {
			case 1:
				int id=0;
				String name=null;
				while(flag1) {
					try {
						Admin.showmag();
						System.out.println("Enter Manager Id: ");
						id = Integer.parseInt(sc.next());
						flag1=false;
					}catch(NumberFormatException e) {
						System.out.println("Enter Valid Manager ID.!");
					}
				}
				while(!flag1) {
					System.out.println("Enter New Name: ");
					name = sc.next();
					try {
						Validate.userNameValidate(name);
		                flag = true;
		                break;
					}catch(UserNameException e) {
						System.out.println(e.getMessage());
					}
				}
				Admin.editNameMag(id, name);

				break;

			case 2:

				int id2=0;
				String phone=null;
				while(flag1) {
					try {
						Admin.showmag();
						System.out.println("Enter Manager Id: ");
						id2 = Integer.parseInt(sc.next());
						flag1=false;
					}catch(NumberFormatException e) {
						System.out.println("Enter Valid Manager ID.!");
					}
				}
				while(!flag1) {
					System.out.println("Enter New Phone Number: ");
					phone = sc.next();
					try {
						Validate.numberValidate(phone);
		                flag1 = true;
						
					} catch (NumberInputException e) {
						 System.out.println(e.getMessage());
					}
				}
				Admin.editPhoneMag(id2, phone);
				break;

			case 3:
				
				int id3=0;
				String role=null;
				while(flag1) {
					try {
						Admin.showmag();
						System.out.println("Enter Manager Id: ");
						id2 = Integer.parseInt(sc.next());
						flag1=false;
					}catch(NumberFormatException e) {
						System.out.println("Enter Manager ID.!");
					}
				}
				role=EmployeeManagement.roleCheck(role,sc);
				Admin.editRoleMag(id3, role);
				break;

			case 4:

                int id4=0;
				String city=null;
				while(flag1) {
					try {
						Admin.showmag();
						System.out.println("Enter Manager Id: ");
						id4 = Integer.parseInt(sc.next());
						flag1=false;
					}catch(NumberFormatException e) {
						System.out.println("Enter Manager ID.!");
					}
				}
				try {
					System.out.println("Enter New City: ");
					city = sc.next();
				} catch (InputMismatchException e) {
					System.out.println("Invalid input. Please enter a valid employee ID.");
				}
				Admin.editCityMag(id4, city);
				break;

			case 5:
				flag = false;

				System.out.println("Getting back to Menu");

				break;
			default:
				System.out.println("Invalid Option");
			}
		}

		return false;

	}

}
