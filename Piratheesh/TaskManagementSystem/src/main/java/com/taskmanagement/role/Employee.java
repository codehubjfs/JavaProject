package com.taskmanagement.role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.smartcliff.TaskManagementSystem.DbmsConnection;
import com.taskmanagement.exception.InvalidNumberException;
import com.taskmanagement.task.managements.Task;

public class Employee{
	private String name;
	private String mail;
	private String gender;
	private String number;
	private String city;
	private String password;
	private String role;
	private int task_status;
	private String hiredate;
	private int mag_id;
	
	
	public Employee(String name, String mail, String gender, String number, String city, String password, String role,
			 String hiredate,int mag_id) {
		super();
		this.name = name;
		this.mail = mail;
		this.gender = gender;
		this.number = number;
		this.city = city;
		this.password = password;
		this.role = role;
		this.task_status = task_status;
		this.hiredate = hiredate;
		this.mag_id=mag_id;
	}


	public int getMag_id() {
		return mag_id;
	}


	public void setMag_id(int mag_id) {
		this.mag_id = mag_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public int getTask_status() {
		return task_status;
	}


	public void setTask_status(int task_status) {
		this.task_status = task_status;
	}


	public String getHireDate() {
		return hiredate;
	}


	public void setHireDate(String hiredate) {
		this.hiredate = hiredate;
	}
	
	public static int opt1Check(int opt2, Scanner sc) {

		boolean inputflag = true;
		while (inputflag) {
			try {
				System.out.println("Enter your choice: ");
				opt2 = Integer.parseInt(sc.next());
				System.out.println();

				if (opt2 != 1 && opt2 != 2 && opt2 != 3 && opt2 != 4 && opt2 != 5 && opt2 !=6 && opt2 !=7) {
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
	
	public static boolean empuser(Scanner sc,String mail) {
		
		boolean flag=true;
		while(flag) {
			System.out.println("*".repeat(200));
			System.out.println("Employee Menu");
			System.out.println("1.View Assigned Task");
			System.out.println("2.Change Task Status");
			System.out.println("3.Create Personal Task");
			System.out.println("4.Update Personal Task");
			System.out.println("5.Delete Personal Task");
			System.out.println("6.Update Status");
			System.out.println("7.Back");
			System.out.println("*".repeat(200));
			int opt2 = 0;
			opt2 = opt1Check(opt2,sc);
			int task=0;
			switch(opt2) {
			case 1:
				
				System.out.println("Assigned Tasks: ");
				assignedTask(mail);
				
				break;
				
			case 2:
				
				System.out.println("Change Task Status: ");
				
				System.out.println("Enter Task Status (1-Not Assinged,2-Assigned,3-Started,4-Pending,5-Progress,6-Completed): ");
				task=sc.nextInt();
				
				changeStatus(task,mail);
				break;
				
			case 3:
				
				System.out.println("Creating Personal Task");
				addTask(sc);
				break;
				
			case 4:
				
//				System.out.println("Enter Task Status (1-Not Assinged,2-Assigned,3-Started,4-Pending,5-Progress,6-Completed): ");
//				task=sc.nextInt();
//				updatePersonalTask(mail,task);
				break;
				
			case 5:
				
				System.out.println("Delete Personal Task");
				System.out.println("Enter The Task ID: ");
				int taskid=sc.nextInt();
				
				deleteTask(taskid);
				
				break;
				
			case 6:
				System.out.println("Enter Task Status (1-Not Assinged,2-Assigned,3-Started,4-Pending,5-Progress,6-Completed): ");
				task=sc.nextInt();
				updateTask(mail,task);
				break;
				
			case 7:
				flag = false;

				System.out.println("Getting Back to Main Menu");

				break;
			
			default :
				System.out.println("Invalid Choice....");
				
			}
		}
		return false;
		
	}

//too edit.....
	private static void updateTask(String mail, int task) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement("update set status_id= ? where email=? ");
			stmt.setInt(1, task);
			stmt.setString(2, mail);
			
			int rows = stmt.executeUpdate();
			System.out.println(rows + "\t updated");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	public static void addTask(Scanner sc) {

		try {
			System.out.println("Enter Requried detials fro Task Creation: ");

			sc.nextLine();
			System.out.println("Enter Task Name: ");
			String name = sc.nextLine();

			System.out.println("Enter Task Description: ");
			String des = sc.nextLine();

			System.out.println("Enter Task Start Date(dd-MMM-yyyy): ");
			String startdate = sc.next();

			System.out.println("Enter Task Start Date(dd-MMM-yyyy): ");
			String enddate = sc.next();

			System.out.println("Task Priority (High/Medium/Low) : ");
			String priority = sc.next();

			Task t = new Task(name, des, startdate, enddate, priority);
			createTask(t);
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}


	public static void createTask(Task t) {
		try {
			PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement(
					"INSERT INTO personal_task (task_id, task_name, task_desp, start_date, end_date, task_priority) VALUES (personalseq.nextval,?,?,?,?,?)");

			stmt.setString(1, t.getTaskName());
			stmt.setString(2, t.getDescription());
			stmt.setString(3, t.getStartDate());
			stmt.setString(4, t.getEndDate());
			stmt.setString(5, t.getPriority());

			int rows = stmt.executeUpdate();
			System.out.println(rows + "\t updated");

		} catch (SQLException e) {
			e.getMessage();
		}
	}


//	private static void updatePersonalTask(String mail,int task) {
//		
//		try {
//			PreparedStatement stmt=DbmsConnection.getConnection().prepareStatement("update personal set status_id=? where email=?");
//			stmt.setInt(1, task);
//			stmt.setString(2, mail);
//			
//			int rows = stmt.executeUpdate();
//			System.out.println(rows + " Updated");
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		
//	}


	private static void deleteTask(int taskid) {
		
	}


	private static void changeStatus(int task,String mail) {
		
		try {
			PreparedStatement stmt=DbmsConnection.getConnection().prepareStatement("update employee set status_id=? where email=?");
			stmt.setInt(1, task);
			stmt.setString(2, mail);
			
			int rows = stmt.executeUpdate();
			System.out.println(rows + " Updated");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}


	private static void assignedTask(String mail) {
		
		try {
			PreparedStatement stmt=DbmsConnection.getConnection().prepareStatement("select e.name,t.task_id,t.task_name,t.task_desp,t.start_date,t.end_date,t.task_priortiy from employee e join transcation ts on e.emp_id=ts.emp_id join task t on t.task_id=ts.task_id where e.email=? ");
			stmt.setString(1, mail);
			
			ResultSet rs=stmt.executeQuery();
			
			while(rs.next()) {
				System.out.println("Name : " + rs.getString("name"));
				System.out.println("Task ID : " + rs.getInt("task_id"));
				System.out.println("Task Name : " + rs.getString("task_name"));
				System.out.println("Task Description : " + rs.getString("task_desp"));
				System.out.println("Start Date : " + rs.getDate("start_date"));
				System.out.println("End Date : " + rs.getString("end_date"));
				System.out.println("Task Priority : " + rs.getString("task_priortiy"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



}