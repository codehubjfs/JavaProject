package com.taskmanagement.task.managements;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.smartcliff.TaskManagementSystem.DbmsConnection;

public class TaskManager {

	public static void addTask(Scanner sc) {

		String priority=null;
		try {
			System.out.println("Enter Requried detials fro Task Creation: ");

			sc.nextLine();
			System.out.println("Enter Task Name: ");
			String name = sc.nextLine();

			System.out.println("Enter Task Description: ");
			String des = sc.nextLine();

			// UPDATE STARTDATE AND ENDDATE :
       	    String startdate = "";
            String enddate = "";
            
            LocalDate startdateDate = null;
            LocalDate enddateDate = null;

            do {
                // Prompt the user to enter the check-in date
            	System.out.println("Enter Task Start Date(yyyy-MM-dd): ");
                startdate = sc.next();

                try {
                    // Parse the input into a LocalDate object
                	startdateDate = LocalDate.parse(startdate);

                    // Check if the check-in date is in the past
                    if (startdateDate.isBefore(LocalDate.now())) {
                        System.out.println("Cannot create task for the past. Please choose a future date for task!");
                        continue; // Continue the loop to prompt the user again
                    }

                    // Prompt the user to enter the check-out date
                    System.out.println("Enter Task End Date(yyyy-MM-dd): ");
                    enddate = sc.next();

                    // Parse the input into a LocalDate object
                    enddateDate = LocalDate.parse(enddate);

                    // Check if the check-out date is before the check-in date
                    if (enddateDate.isBefore(startdateDate)) {
                        System.out.println("End date must be after the task start date. Please enter a valid Task-End date.");
                        continue; // Continue the loop to prompt the user again
                    }

                } catch (DateTimeParseException e) {
                    // Handle parsing errors
                    System.out.println("Please enter the date in the format: yyyy-MM-dd");
                    continue; // Continue the loop to prompt the user again
                }

                // If the code reaches here, both dates are valid
                break; // Exit the loop

            } while (true);
            System.out.println("Task Start Date: " + startdate);
            System.out.println("Task End Date: " + enddate);
			
			
            priority=TaskPriCheck(priority,sc);
			

			Task t = new Task(name, des, startdate, enddate, priority);
			TaskManager.createtask(t);
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static String TaskPriCheck(String priority, Scanner sc) {
		boolean flag = true;
		while (flag) {
			System.out.println("Task Priority (High/Medium/Low) : ");
			priority = sc.next().toLowerCase();
			if (priority.equalsIgnoreCase("low") || priority.equalsIgnoreCase("medium") || priority.equalsIgnoreCase("high")) {
				flag = false;
			} else {
				System.out.println("Enter The Correct Priority...");
			}
		}
		return priority;

	}
	
	public static void createtask(Task t) {
		try {
			PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement(
					"INSERT INTO task (task_id, task_name, task_desp, start_date, end_date, task_priortiy) VALUES (taskseq.nextval,?,?,To_DATE(?,'YYYY-MM-DD'),To_DATE(?,'YYYY-MM-DD'),?)");

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

	public static void DisplayAll(int id) {
        try {
            PreparedStatement stmt = DbmsConnection.getConnection()
                    .prepareStatement("SELECT task_id, task_name FROM task ORDER BY task_id");
            ResultSet rs = stmt.executeQuery();

            // Print the table header
            System.out.println("+----------+----------------------+");
            System.out.println("|  Task ID  | Task Name            |");
            System.out.println("+----------+----------------------+");

            // Print each row of the result set
            while (rs.next()) {
                int taskId = rs.getInt("task_id");
                String taskName = rs.getString("task_name");

                // Print the table row
                System.out.printf("| %-10d | %-20s |\n", taskId, taskName);
            }

            // Print the table footer
            System.out.println("+--------+----------------------+");

            // Close the ResultSet and the PreparedStatement
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("SQL error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

//	public static List<Task> displayAll(int id) {
//		List<Task> tasks = new ArrayList<>();
//		try {
//			PreparedStatement stmt = DbmsConnection.getConnection()
//					.prepareStatement("SELECT task_id, task_name FROM task order by task_id");
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				int taskId = rs.getInt("task_id");
//				String taskName = rs.getString("task_name");
//				// Create a new Task object with only task_id and task_name
//				Task task = new Task(taskId, taskName);
//				tasks.add(task);
//			}
//			tasks.forEach(task -> {
//				System.out.println("|-------------------|");
//				System.out.println("ID: " + task.getTaskId());
//				System.out.println("Task Name: " + task.getTaskName());
//			});
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return tasks;
//	}
	
	public static void DisplayOneTask(int id) {
        try {
            PreparedStatement stmt = DbmsConnection.getConnection()
                    .prepareStatement("SELECT task_id, task_name, task_desp, start_date, end_date, task_priortiy FROM task WHERE task_id=?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Print the table header
                System.out.println("+----------------------+--------------------------------------------------------+");
                System.out.printf("| %-20s | %-55s |\n", "Field", "Value");
                System.out.println("+----------------------+--------------------------------------------------------+");

                // Print each field and its value
                System.out.printf("| %-20s | %-55d |\n", "Task ID", rs.getInt("task_id"));
                System.out.printf("| %-20s | %-55s |\n", "Task Name", rs.getString("task_name"));
                System.out.printf("| %-20s | %-55s |\n", "Task Description", rs.getString("task_desp"));
                System.out.printf("| %-20s | %-55s |\n", "Start Date", rs.getDate("start_date").toString());
                System.out.printf("| %-20s | %-55s |\n", "End Date", rs.getDate("end_date").toString());
                System.out.printf("| %-20s | %-55s |\n", "Task Priority", rs.getString("task_priortiy"));

                // Print the table footer
                System.out.println("+----------------------+--------------------------------------------------------+");
            } else {
                System.out.println("Task with ID " + id + " not found.");
            }

            // Close the ResultSet and the PreparedStatement
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("SQL error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

//	public static void displayOneTask(int id) {
//		try {
//			PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement(
//					"SELECT task_id, task_name, task_desp, start_date, end_date, task_priortiy FROM task WHERE task_id=?");
//			stmt.setInt(1, id);
//			ResultSet rs = stmt.executeQuery();
//
//			List<Task> tasks = new ArrayList<>();
//			while (rs.next()) {
//				int taskId = rs.getInt("task_id");
//				String taskName = rs.getString("task_name");
//				String taskDesp = rs.getString("task_desp");
//				String startDate = rs.getString("start_date");
//				String endDate = rs.getString("end_date");
//				String taskPriority = rs.getString("task_priortiy");
//
//				Task task = new Task(taskId, taskName, taskDesp, startDate, endDate, taskPriority);
//				tasks.add(task);
//			}
//
//			// Use streams to print the task
//			tasks.stream().forEach(task -> {
//				System.out.println("|--------------------------|");
//				System.out.println("ID: " + task.getTaskId());
//				System.out.println("Task Name: " + task.getTaskName());
//				System.out.println("Task Description: " + task.getDescription());
//				System.out.println("Start Date: " + task.getStartDate());
//				System.out.println("End Date: " + task.getEndDate());
//				System.out.println("Task Priority: " + task.getPriority());
//			});
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

	public static void editName(int id, String name) {

		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update task set task_name=? where task_id=?");
			stmt.setString(1, name);
			stmt.setInt(2, id);

			int rows = stmt.executeUpdate();
			if (rows > 0) {
	            System.out.println(rows + " row(s) updated");
	        } else {
	            System.out.println("Task ID " + id + " not found.");
	        }

		} catch (SQLException e) {

			e.getMessage();
		}

	}

	public static void editDesp(int id, String desp) {

		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update task set task_desp=? where task_id=?");

			stmt.setString(1, desp);
			stmt.setInt(2, id);

			int rows = stmt.executeUpdate();
			if (rows > 0) {
	            System.out.println(rows + " row(s) updated");
	        } else {
	            System.out.println("Task ID " + id + " not found.");
	        }
		} catch (SQLException e) {

			e.getMessage();
		}

	}

	public static void editSDate(int id, String sdate) {

		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update task set start_date=? where task_id=?");

			stmt.setString(1, sdate);
			stmt.setInt(2, id);

			int rows = stmt.executeUpdate();
			if (rows > 0) {
	            System.out.println(rows + " row(s) updated");
	        } else {
	            System.out.println("Task ID " + id + " not found.");
	        }
			
		} catch (SQLException e) {

			e.getMessage();
		}

	}

	public static void editEDate(int id, String edate) {

		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update task set end_date=? where task_id=?");

			stmt.setString(1, edate);
			stmt.setInt(2, id);

			int rows = stmt.executeUpdate();
			if (rows > 0) {
	            System.out.println(rows + " row(s) updated");
	        } else {
	            System.out.println("Task ID " + id + " not found.");
	        }
			
			} catch (SQLException e) {

			e.getMessage();
		}
	}

	public static void editTaskPriority(int id, String pri) {

		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update task set TASK_PRIORTIY=? where task_id=?");

			stmt.setString(1, pri);
			stmt.setInt(2, id);

			int rows = stmt.executeUpdate();
			if (rows > 0) {
	            System.out.println(rows + " row(s) updated");
	        } else {
	            System.out.println("Task ID " + id + " not found.");
	        }

		} catch (SQLException e) {

			e.getMessage();
		}

	}

	public static void editAssign(int id, int empid) {

		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update transcation set emp_id=? where task_id=?");

			stmt.setInt(1, empid);
			stmt.setInt(2, id);

			int rows = stmt.executeUpdate();
			if (rows > 0) {
	            System.out.println(rows + " row(s) updated");
	        } else {
	            System.out.println("Task ID " + id + " not found.");
	        }

		} catch (SQLException e) {

			e.getMessage();
		}

	}

	public static void DisplayEmployee(String mail, int magid) {


		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement(" select e.emp_id,e.name,ts.task_id from employee e join transcation t on t.emp_id=e.emp_id join task ts on ts.task_id=t.task_id where e.mag_id=? and e.role='employee'");
			stmt.setInt(1, magid);

			ResultSet rs = stmt.executeQuery();
			System.out.println("+-------------+-------------------+----------+");
            System.out.printf("| %-11s | %-17s | %-8s |\n", "Employee Id", "Employee Name", "Task ID");
            System.out.println("+-------------+-------------------+----------+");

            // Print each employee's details in a formatted row
            while (rs.next()) {
                int empId = rs.getInt("emp_id");
                String empName = rs.getString("name");
                int taskId = rs.getInt("task_id");

                System.out.printf("| %-11d | %-17s | %-8d |\n", empId, empName, taskId);
            }
            System.out.println("+-------------+-------------------+----------+");

            // Close the ResultSet and the PreparedStatement
            rs.close();
            stmt.close();

		} catch (SQLException e) {

			e.getMessage();
		}

	}

	public static void DisplayParticular(int id, int magid) {

		try {

			PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement(
					"select e.emp_id,e.name,ts.task_id,ts.task_name,s.status from employee e join transcation t on t.emp_id=e.emp_id join task ts on ts.task_id=t.task_id join task_status s on t.status_id=s.id where e.emp_id=? and e.mag_id=?");
			stmt.setInt(1, id);
			stmt.setInt(2, magid);
			ResultSet rs = stmt.executeQuery();

			String format = "| %-11s | %-17s | %-8s | %-15s | %-13s |%n";
            System.out.format("+-------------+-------------------+----------+-----------------+---------------+%n");
            System.out.format("| Employee Id | Employee Name     | Task ID  | Task Name       | Status        |%n");
            System.out.format("+-------------+-------------------+----------+-----------------+---------------+%n");

            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                // Print each row in the table format
                System.out.format(format,
                        rs.getInt("emp_id"),
                        rs.getString("name"),
                        rs.getInt("task_id"),
                        rs.getString("task_name"),
                        rs.getString("status"));
            }

            if (!hasResults) {
                System.out.println("Employee with ID " + id + " not found for this Manager.");
                
            } else {
                System.out.format("+-------------+-------------------+----------+-----------------+---------------+%n");
            }

            // Close the ResultSet and the PreparedStatement
            rs.close();
            stmt.close();

		} catch (SQLException e) {

			e.getMessage();
		}
	}

	public static void AssignTask(int id, int task_id, int magid) {
		int status_id=1;
	    try {
	        PreparedStatement stmt = DbmsConnection.getConnection()
	                .prepareStatement("INSERT INTO transcation (task_id, emp_id,status_id) VALUES (?, ?,?)");

	        stmt.setInt(1, task_id);
	        stmt.setInt(2, id);
	        stmt.setInt(3, status_id);

	        int rows = stmt.executeUpdate();
	        if (rows > 0) {
	            System.out.println(rows + " row(s) updated");
	        } else {
	            System.out.println("Task ID " + task_id + " not found.");
	        }

	        stmt.close();

	    } catch (SQLException e) {
	        System.out.println("SQL error occurred: " + e.getMessage());
	        e.getMessage();
	    }
	}

	public static void updateTask(int id, int task,int taskid) {

		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update transcation set status_id= ? where emp_id=? and task_id=? ");
			stmt.setInt(1, task);
			stmt.setInt(2, id);
			stmt.setInt(3, taskid);

			int rows = stmt.executeUpdate();
			if (rows > 0) {
	            System.out.println(rows + " row(s) updated");
	        } else {
	            System.out.println("Task ID " + taskid + " not found.");
	        }

	        stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
