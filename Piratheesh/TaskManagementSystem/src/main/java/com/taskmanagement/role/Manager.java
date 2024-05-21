package com.taskmanagement.role;


//

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import com.exception.InvalidNumberException;
//import com.task.management.Task;
//import com.task.management.*;
//
//public class Manager {
//
//    private String mail;
//    private String password;
//    private String gender;
//    private String phonenumber;
//    private String city;
//    private String name;
//
//    private int task_id;
//    private int task_status;
//
//    public Manager(String mail, String password, String gender, String phonenumber, String city, String name,
//            int task_id, int task_status) {
//        super();
//        this.mail = mail;
//        this.password = password;
//        this.gender = gender;
//        this.phonenumber = phonenumber;
//        this.city = city;
//        this.name = name;
//
//        this.task_id = task_id;
//        this.task_status = task_status;
//    }
//
//    public String getMail() {
//        return mail;
//    }
//
//    public void setMail(String mail) {
//        this.mail = mail;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public String getPhonenumber() {
//        return phonenumber;
//    }
//
//    public void setPhonenumber(String phonenumber) {
//        this.phonenumber = phonenumber;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getTask_id() {
//        return task_id;
//    }
//
//    public void setTask_id(int task_id) {
//        this.task_id = task_id;
//    }
//
//    public int getTask_status() {
//        return task_status;
//    }
//
//    public void setTask_status(int task_status) {
//        this.task_status = task_status;
//    }
//
//    public static int opt1Check(int opt2, BufferedReader reader) throws IOException {
//
//        boolean inputflag = true;
//        while (inputflag) {
//            try {
//                System.out.println("Enter your choice: ");
//                opt2 = Integer.parseInt(reader.readLine());
//                System.out.println();
//
//                if (opt2 != 1 && opt2 != 2 && opt2 != 3 && opt2 != 4 && opt2 != 5 && opt2 != 6) {
//                    throw new InvalidNumberException("Please enter a valid number");
//                }
//                inputflag = false;
//            } catch (NumberFormatException e) {
//                System.out.println("You have entered a character!!!... Please enter a number!");
//                System.out.println();
//                reader.readLine();
//            } catch (InvalidNumberException e) {
//                System.out.println("!" + e.getMessage() + "!");
//            }
//        }
//        return opt2;
//    }
//
//    public static boolean maguser(BufferedReader reader) throws IOException {
//
//        boolean flag = true;
//        while (flag) {
//            System.out.println("*".repeat(200));
//            System.out.println("Manager Menu");
//            System.out.println("1.Create Task");
//            System.out.println("2.Task Description");
//            System.out.println("3.Edit Task");
//            System.out.println("4.View Employee");
//            System.out.println("5.Assinge Task");
//            System.out.println("6.Back");
//            System.out.println("*".repeat(200));
//            int opt2 = 0;
//            opt2 = opt1Check(opt2, reader);
//
//            switch (opt2) {
//                case 1:
//                    System.out.println("Enter Required details for Task Creation: ");
//
//                    System.out.println("Enter Task Name: ");
//                    String name = reader.readLine();
//
//                    System.out.println("Enter Task Description: ");
//                    String des = reader.readLine();
//
//                    System.out.println("Enter Task Start Date(dd-MMM-yyyy): ");
//                    String startdate = reader.readLine();
//
//                    System.out.println("Enter Task Start Date(dd-MMM-yyyy): ");
//                    String enddate = reader.readLine();
//
//                    System.out.println("Task Priority (High/Medium/Low) : ");
//                    String priority = reader.readLine();
//
//                    System.out.println("Assigned To Employee ID : ");
//                    int ass_emp_id = Integer.parseInt(reader.readLine());
//
//                    Task t = new Task(name, des, startdate, enddate, priority, ass_emp_id);
//                    TaskManager.createtask(t);
//
//                    break;
//
//                case 2:
//
//                    Manager.TaskDescription(reader);
//
//                    break;
//
//                case 3:
//                    break;
//
//                case 4:
//                    break;
//
//                case 5:
//                    break;
//
//                case 6:
//                    flag = false;
//
//                    System.out.println("Getting Back to Main Menu");
//
//                    break;
//
//                default:
//                    System.out.println("Invalid Choice....");
//
//            }
//        }
//        return false;
//
//    }
//
//    private static int optCheck(int opt2, BufferedReader reader) throws IOException {
//
//        boolean inputflag = true;
//        while (inputflag) {
//            try {
//                System.out.println("Enter your choice: ");
//                opt2 = Integer.parseInt(reader.readLine());
//                System.out.println();
//
//                if (opt2 != 1 && opt2 != 2 && opt2 != 3) {
//                    throw new InvalidNumberException("Please enter a valid number");
//                }
//                inputflag = false;
//            } catch (NumberFormatException e) {
//                System.out.println("You have entered a character!!!... Please enter a number!");
//                System.out.println();
//                reader.readLine();
//            } catch (InvalidNumberException e) {
//                System.out.println("!" + e.getMessage() + "!");
//            }
//
//        }
//        return opt2;
//    }
//
//    private static boolean TaskDescription(BufferedReader reader) throws IOException {
//
//        boolean flag = true;
//        while (flag) {
//            System.out.println("*".repeat(200));
//            System.out.println("Task Description Menu");
//            System.out.println("1.Display All Tasks");
//            System.out.println("2.Display Particular Task Description");
//            ;
//            System.out.println("3.Back Manager Menu");
//            System.out.println("*".repeat(200));
//            int opt2 = 0;
//            opt2 = optCheck(opt2, reader);
//
//            switch (opt2) {
//                case 1:
//
//                    TaskManager.DisplayAll();
//                    break;
//
//                case 2:
//
//                    System.out.println("Enter the Task Number: ");
//                    int id = Integer.parseInt(reader.readLine());
//                    TaskManager.DisplayOneTask(id);
//                    break;
//
//                case 3:
//                    flag = false;
//
//                    System.out.println("Back to Manager Menu");
//
//                    break;
//
//                default:
//                    System.out.println("Invalid Choice....");
//
//            }
//        }
//        return false;
//
//    }
//
//}

import java.util.Scanner;
import com.taskmanagement.exception.InvalidNumberException;
import com.taskmanagement.task.managements.*;

public class Manager {

	public static int opt1Check(int opt2, Scanner sc) {

		boolean inputflag = true;
		while (inputflag) {
			try {
				System.out.println("Enter your choice: ");
				opt2 = Integer.parseInt(sc.next());
				System.out.println();

				if (opt2 != 1 && opt2 != 2 && opt2 != 3 && opt2 != 4 && opt2 != 5 && opt2 != 6 && opt2 != 7) {
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
		return opt2;
	}

	public static boolean maguser(Scanner sc, String mail, int empid) {

		boolean flag = true;
		while (flag) {
			System.out.println("*".repeat(200));
			System.out.println("Manager Menu");
			System.out.println("1.Create Task");
			System.out.println("2.View Task Description");
			System.out.println("3.Edit Task");
			System.out.println("4.View Employee");
			System.out.println("5.Assinge Task");
			System.out.println("6.Update Task");
			System.out.println("7.Back");
			System.out.println("*".repeat(200));
			int opt2 = 0;
			opt2 = opt1Check(opt2, sc);

			switch (opt2) {
			case 1:

				TaskManager.addTask(sc);
				break;

			case 2:

				TaskDescription(sc, empid);
				break;

			case 3:

				editTask(sc,empid);
				break;

			case 4:

				viewEmployee(sc, mail, empid);
				break;

			case 5:

				displayEmployee(mail,empid,sc);
				break;

			case 6:
				
				updateTask(mail,empid,sc);
				break;

			case 7:
				flag = false;
				System.out.println("Getting Back to Main Menu");
				break;

			default:
				System.out.println("Invalid Choice....");

			}
		}
		return false;

	}
	
	private static void viewEmployee(Scanner sc, String mail, int empid) {
		boolean flag1 = true;
		while (flag1) {
			System.out.println("*".repeat(200));
			System.out.println("1.View All Employee's Task");
			System.out.println("2.View Particular Employee's Task");
			System.out.println("3.Back To Manager Menu");

			System.out.println("*".repeat(200));

			int option1 = 0;
			option1 = option1Check(option1, sc);

			switch (option1) {
			case 1:
				System.out.println("Displaying All Employees Task: ");
				TaskManager.DisplayEmployee(mail, empid);
				break;

			case 2:
				TaskManager.DisplayEmployee(mail, empid);
				System.out.println("Enter Employee Id: ");
				int id = sc.nextInt();
				TaskManager.DisplayParticular(id, empid);
				break;

			case 3:
				flag1 = false;

				System.out.println("Getting Back to Manager Menu");
				break;
			}
		}

	}

	private static void displayEmployee(String mail, int empid, Scanner sc) {
	    TaskManager.DisplayEmployee(mail, empid);
	    int id1 = 0;
	    int task_id = 0;
	    boolean flag1 = true;
	    while (flag1) {
	        try {
	            System.out.println("Enter Employee ID: ");
	            id1 = Integer.parseInt(sc.next());
	            TaskManager.DisplayParticular(id1, empid); // Moved here to confirm valid employee
	            flag1 = false;
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid Character...!");
	        }
	    }

	    flag1 = true;
	    while (flag1) {
	        try {
	        	TaskManager.DisplayAll(empid);
	            System.out.println("Enter Task ID to Assign: ");
	            task_id = Integer.parseInt(sc.next());
	            flag1 = false;
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid Character...!");
	        }
	    }

	    TaskManager.AssignTask(id1, task_id, empid);
	}

	private static void updateTask(String mail, int empid,Scanner sc) {
		
		TaskManager.DisplayEmployee(mail, empid);
		int id = 0;
		int taskid=0;
		int task=0;
		boolean flag2 = true;
		while (flag2) {
			try {
				System.out.println("Enter Employee ID: ");
				id = Integer.parseInt(sc.next());
				flag2 = false;
				break;
			} catch (NumberFormatException e) {
				System.out.println("Invaid Character...!");
			}
		}
		TaskManager.DisplayParticular(id, empid);
		while (!flag2) {
			try {
				System.out.println("Enter Task ID: ");
				taskid=Integer.parseInt(sc.next());
				flag2 = true;
				break;
			} catch (NumberFormatException e) {
				System.out.println("Invaid Character...!");
			}
		}
		
		task = taskOp(task,sc);
		
		TaskManager.updateTask(id, task,taskid);
		
	}
	
	public static int taskOp(int task, Scanner sc) {

		boolean inputflag = true;
		while (inputflag) {
			try {
				System.out.println(
						"Enter Task Status (1-Not Assinged,2-Assigned,3-Started,4-Pending,5-Progress,6-Completed): ");
				task = Integer.parseInt(sc.next());
				System.out.println();

				if (task != 1 && task != 2 && task != 3 && task != 4 && task != 5 && task != 6 ) {
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
		return task;
	}

	private static int option1Check(int option1, Scanner sc) {

		boolean inputflag = true;
		while (inputflag) {
			try {
				System.out.println("Enter your choice: ");
				option1 = Integer.parseInt(sc.next());
				System.out.println();

				if (option1 != 1 && option1 != 2 && option1 != 3) {
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
		return option1;
	}

	public static int optEditCheck(int optEdit, Scanner sc) {

		boolean inputflag = true;
		while (inputflag) {
			try {
				System.out.println("Enter your choice: ");
				optEdit = Integer.parseInt(sc.next());
				System.out.println();

				if (optEdit != 1 && optEdit != 2 && optEdit != 3 && optEdit != 4 && optEdit != 5 && optEdit != 6
						&& optEdit != 7) {
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
		return optEdit;
	}

	private static boolean editTask(Scanner sc,int empid) {

		boolean flag = true;
		while (flag) {
			System.out.println("*".repeat(200));
			System.out.println("Task Edit Menu");
			System.out.println("1.Edit Task Name");
			System.out.println("2.Edit Task Description");
			System.out.println("3.Edit Start Date");
			System.out.println("4.Edit End Date");
			System.out.println("5.Edit Task Priority");
			System.out.println("6.Edit Employee Id");
			System.out.println("7.Back To Manager Menu");
			System.out.println("*".repeat(200));
			int optEdit = 0;
			optEdit = optEditCheck(optEdit, sc);

			boolean flag1=true;
			switch (optEdit) {
			case 1:

				int id=0;
				while(flag1) {
					try {
						TaskManager.DisplayAll(empid);
						System.out.println("Enter Task ID: ");
						id=Integer.parseInt(sc.next());
						flag1=false;
						break;
					}catch(NumberFormatException e) {
						System.out.println("Invaid Character...!");
					}
				}
				sc.nextLine();
				System.out.println("Enter New Task Name: ");
				String name = sc.nextLine();

				TaskManager.editName(id, name);

				break;

			case 2:

				flag1=true;
				int id1=0;
				while(flag1) {
					try {
						TaskManager.DisplayAll(empid);
						System.out.println("Enter Task ID: ");
						id1=Integer.parseInt(sc.next());
						flag1=false;
						break;
					}catch(NumberFormatException e) {
						System.out.println("Invaid Character...!");
					}
				}
				
				sc.nextLine();
				System.out.println("Enter New Description: ");
				String desp = sc.nextLine();

				TaskManager.editDesp(id1, desp);

				break;

			case 3:

				flag1=true;
				int id2=0;
				while(flag1) {
					try {
						TaskManager.DisplayAll(empid);
						System.out.println("Enter Task ID: ");
						id2=Integer.parseInt(sc.next());
						flag1=false;
						break;
					}catch(NumberFormatException e) {
						System.out.println("Invaid Character...!");
					}
				}
				
				System.out.println("Enter The New Start Date(dd-MMM-yyyy): ");
				String sdate = sc.next();

				TaskManager.editSDate(id2, sdate);

				break;

			case 4:

				flag1=true;
				int id3=0;
				while(flag1) {
					try {
						TaskManager.DisplayAll(empid);
						System.out.println("Enter Task ID: ");
						id3=Integer.parseInt(sc.next());
						flag1=false;
						break;
					}catch(NumberFormatException e) {
						System.out.println("Invaid Character...!");
					}
				}
				
				System.out.println("Enter The New End Date(dd-MMM-yyyy): ");
				String edate = sc.next();

				TaskManager.editEDate(id3, edate);

				break;

			case 5:

				flag1=true;
				int id4=0;
				while(flag1) {
					try {
						TaskManager.DisplayAll(empid);
						System.out.println("Enter Task ID: ");
						id4=Integer.parseInt(sc.next());
						flag1=false;
						break;
					}catch(NumberFormatException e) {
						System.out.println("Invaid Character...!");
					}
				}
				

				System.out.println("Enter New Task Priority(High/Medium/Low): ");
				String pri = sc.next();

				TaskManager.editTaskPriority(id4, pri);

				break;

			case 6:

				flag1=true;
				int id5=0;
				while(flag1) {
					try {
						TaskManager.DisplayAll(empid);
						System.out.println("Enter Task ID: ");
						id5=Integer.parseInt(sc.next());
						flag1=false;
						break;
					}catch(NumberFormatException e) {
						System.out.println("Invaid Character...!");
					}
				}
				System.out.println("");
				int empyid = sc.nextInt();
				TaskManager.editAssign(id5, empyid);

				break;

			case 7:
				flag = false;

				System.out.println("Getting Back to Main Menu");

				break;

			default:
				System.out.println("Invalid Choice....");

			}
		}
		return false;

	}

	private static int optCheck(int opt2, Scanner sc) {

		boolean inputflag = true;
		while (inputflag) {
			try {
				System.out.println("Enter your choice: ");
				opt2 = Integer.parseInt(sc.next());
				System.out.println();

				if (opt2 != 1 && opt2 != 2 && opt2 != 3) {
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
		return opt2;
	}

	private static boolean TaskDescription(Scanner sc, int empid) {

		boolean flag = true;
		while (flag) {
			System.out.println("*".repeat(200));
			System.out.println("Task Description Menu");
			System.out.println("1.Display All Tasks");
			System.out.println("2.Display Particular Task Description");

			System.out.println("3.Back Manager Menu");
			System.out.println("*".repeat(200));
			int opt2 = 0;
			opt2 = optCheck(opt2, sc);

			boolean flag1=true;
			int id=0;
			switch (opt2) {
			case 1:

				TaskManager.DisplayAll(empid);
				break;

			case 2:

				while(flag1) {
					try {
						TaskManager.DisplayAll(empid);
						System.out.println("Enter the Task ID: ");
						id = Integer.parseInt(sc.next());
						TaskManager.DisplayOneTask(id);
						flag1=false;
						break;
					}catch(NumberFormatException e) {
						System.out.println("Invaid Character...!Enter the valid Task ID!");
					}
				}
				
				
				break;

			case 3:
				flag = false;

				System.out.println("Back to Manager Menu");

				break;

			default:
				System.out.println("Invalid Choice....");

			}
		}
		return false;

	}

}

