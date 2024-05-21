package com.taskmanagement.role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.smartcliff.TaskManagementSystem.DbmsConnection;
import com.taskmanagement.exception.InvalidNumberException;

public class Admin {

	private static String name;
	private static String mail;
	private static String password;

	public Admin(String name, String mail, String password) {
		super();
		this.name = name;
		this.mail = mail;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static int optCheck(int opt1, Scanner sc) {

		boolean inputflag = true;
		while (inputflag) {
			try {
				System.out.println("Enter your choice: ");
				opt1 = Integer.parseInt(sc.next());
				System.out.println();

				if (opt1 != 1 && opt1 != 2) {
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
		return opt1;
	}

	public static boolean adminuser(Scanner sc) {
	    // Initialize flag to true to start the loop
	    boolean flag = true;
	    
	    // Loop until flag is false
	    while (flag) {
	        // Print menu options
	        System.out.println("*".repeat(200));
	        System.out.println("1.Employee Management");
	        System.out.println("2.Back to Menu");
	        System.out.println("*".repeat(200));

	        int opt1 = 0;

	        // Validate user input for menu option
	        opt1 = optCheck(opt1, sc);

	        // Switch case based on user input
	        switch (opt1) {
	            case 1:
	                // If user selects option 1, go to employee management
	                EmployeeManagement.adminmanagement(sc);
	                break;

	            case 2:
	                // If user selects option 2, exit loop and return to main menu
	                flag = false;
	                System.out.println("Getting back to Menu");
	                break;

	            default:
	                // If user enters an invalid option, display error message
	                System.out.println("Invalid Option");
	        }
	    }
	    // Return false when the method completes execution
	    return false;
	}

	public static void addEmpList(List<Employee> empList) {
	    if (empList.isEmpty()) {
	        System.out.println("No employees to add.");
	        return;
	    }

	    try {
	        PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement(
	            "INSERT INTO employee(emp_id, name, email, password, gender, phone_number, role, city, mag_id, hiredate) VALUES(empseq.nextval,?,?,?,?,?,?,?,?,?)");

	        for (Employee emp : empList) {
	            stmt.setString(1, emp.getName());
	            stmt.setString(2, emp.getMail());
	            stmt.setString(3, emp.getPassword());
	            stmt.setString(4, emp.getGender());
	            stmt.setString(5, emp.getNumber());
	            stmt.setString(6, emp.getRole());
	            stmt.setString(7, emp.getCity());
	            stmt.setInt(8, emp.getMag_id());
	            stmt.setString(9, emp.getHireDate());
	            stmt.addBatch(); // Add to batch
	        }

	        int[] rows = stmt.executeBatch(); // Execute batch insert
	        for (int row : rows) {
	            System.out.println(row + " row(s) inserted");
	        }

	        stmt.close(); // Close the PreparedStatement
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public static void showeveEmp() {
		try {
			PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement("select * from employee order by emp_id");
			ResultSet rs = stmt.executeQuery();

			List<Map<String, Object>> employees = new ArrayList<>();

			while (rs.next()) {
				Map<String, Object> employeeData = new HashMap<>();
				employeeData.put("ID", rs.getInt("emp_id"));
				employeeData.put("Name", rs.getString("name"));
				employeeData.put("Mail", rs.getString("email"));
				employeeData.put("Role", rs.getString("role"));
				employeeData.put("Gender", rs.getString("gender"));
				employeeData.put("Phone Number", rs.getString("phone_number"));
				employeeData.put("City", rs.getString("city"));
				employeeData.put("HireDate", rs.getDate("hiredate"));
				employees.add(employeeData);
			}

			// Print table header
			System.out
			.println("|-----|--------------------|---------------------------------|-----------|--------|--------------|--------------|------------|");
	System.out
			.println("| ID  |        Name        |               Mail              |    Role   | Gender | Phone Number | City         |  Hiredate  |");
	System.out
			.println("|-----|--------------------|---------------------------------|-----------|--------|--------------|--------------|------------|");

			// Print employee data using streams
			employees.stream().forEach(employee -> {
				System.out.printf("| %-4s| %-19s| %-32s| %-10s| %-7s| %-13s| %-13s| %-11s|%n", employee.get("ID"),
						employee.get("Name"), employee.get("Mail"), employee.get("Role"), employee.get("Gender"),
						employee.get("Phone Number"), employee.get("City"), employee.get("HireDate"));
			});

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void showemp() {
		try {
			String sql = "select * from employee where role ='employee' order by emp_id";
			PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			List<Map<String, Object>> employees = new ArrayList<>();

			while (rs.next()) {
				Map<String, Object> employeeData = new HashMap<>();
				employeeData.put("ID", rs.getInt("emp_id"));
				employeeData.put("Name", rs.getString("name"));
				employeeData.put("Mail", rs.getString("email"));
				employeeData.put("Gender", rs.getString("gender"));
				employeeData.put("Phone Number", rs.getString("phone_number"));
				employeeData.put("City", rs.getString("city"));
				employeeData.put("HireDate", rs.getDate("hiredate"));
				employees.add(employeeData);
			}

			// Print table header
			System.out
			.println("|-----|--------------------|---------------------------------|--------|--------------|--------------|------------|");
	System.out
			.println("| ID  |        Name        |               Mail              | Gender | Phone Number | City         |  Hiredate  |");
	System.out
			.println("|-----|--------------------|---------------------------------|--------|--------------|--------------|------------|");

			// Print employee data using streams
			employees.stream().forEach(employee -> {
				System.out.printf("| %-4s| %-19s| %-32s| %-7s| %-13s| %-13s| %-11s|%n", employee.get("ID"),
						employee.get("Name"), employee.get("Mail"), employee.get("Gender"),
						employee.get("Phone Number"), employee.get("City"), employee.get("HireDate"));
			});

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void showmag() {
		List<Map<String, Object>> managers = new ArrayList<>();

		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("select * from employee where role='manager' order by emp_id");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Map<String, Object> managerData = new HashMap<>();
				managerData.put("ID", rs.getInt("emp_id"));
				managerData.put("Name", rs.getString("name"));
				managerData.put("Mail", rs.getString("email"));
				managerData.put("Gender", rs.getString("gender"));
				managerData.put("Phone Number", rs.getString("phone_number"));
				managerData.put("City", rs.getString("city"));
				managerData.put("HireDate", rs.getDate("hiredate"));
				managers.add(managerData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Print table header
		System.out
				.println("|-----|--------------------|---------------------------------|--------|--------------|--------------|------------|");
		System.out
				.println("| ID  |        Name        |               Mail              | Gender | Phone Number | City         |  Hiredate  |");
		System.out
				.println("|-----|--------------------|---------------------------------|--------|--------------|--------------|------------|");

		// Print manager data using streams
		managers.stream().forEach(manager -> {
			System.out.printf("| %-4s| %-19s| %-32s| %-7s| %-13s| %-13s| %-11s|%n", manager.get("ID"),
					manager.get("Name"), manager.get("Mail"), manager.get("Gender"),
					manager.get("Phone Number"), manager.get("City"), manager.get("HireDate"));
		});
	}

	// Method to edit the name of an employee
    public static void editNameEmp(int id, String name) {
        try {
            // Prepare SQL statement to update employee name
            PreparedStatement stmt = DbmsConnection.getConnection()
                    .prepareStatement("update employee set name=? where emp_id=? and role='employee'");
            stmt.setString(1, name);
            stmt.setInt(2, id);

            // Execute update and check the result
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println(rows + " row(s) updated");
            } else {
                System.out.println("Employee ID " + id + " not found.");
            }
        } catch (SQLException e) {
        	e.getMessage();
        }
    }

    // Method to edit the phone number of an employee
    public static void editPhoneEmp(int id, String phone) {
        try {
            // Prepare SQL statement to update employee phone number
            PreparedStatement stmt = DbmsConnection.getConnection()
                    .prepareStatement("update employee set phone_number=? where emp_id=? and role='employee'");
            stmt.setString(1, phone);
            stmt.setInt(2, id);

            // Execute update and check the result
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println(rows + " row(s) updated");
            } else {
                System.out.println("Employee ID " + id + " not found.");
            }
        } catch (SQLException e) {
        	e.getMessage();
        }
    }

    // Method to edit the role of an employee
    public static void editRoleEmp(int id, String role) {
        try {
            // Prepare SQL statement to update employee role
            PreparedStatement stmt = DbmsConnection.getConnection()
                    .prepareStatement("update employee set role=? where emp_id=? and role='employee'");
            stmt.setString(1, role);
            stmt.setInt(2, id);

            // Execute update and check the result
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println(rows + " row(s) updated");
            } else {
                System.out.println("Employee ID " + id + " not found.");
            }
        } catch (SQLException e) {
        	e.getMessage();
        }
    }

    // Method to edit the city of an employee
    public static void editCityEmp(int id, String city) {
        try {
            // Prepare SQL statement to update employee city
            PreparedStatement stmt = DbmsConnection.getConnection()
                    .prepareStatement("update employee set city=? where emp_id=? and role='employee'");
            stmt.setString(1, city);
            stmt.setInt(2, id);

            // Execute update and check the result
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println(rows + " row(s) updated");
            } else {
                System.out.println("Employee ID " + id + " not found.");
            }
        } catch (SQLException e) {
        	e.getMessage();
        }
    }

 // Method to edit the name of a manager
    public static void editNameMag(int id, String name2) {
        System.out.println(name2);
        try {
            // Prepare SQL statement to update manager name
            PreparedStatement stmt = DbmsConnection.getConnection()
                    .prepareStatement("update employee set name=? where emp_id=? and role='manager'");
            stmt.setString(1, name2);
            stmt.setInt(2, id);

            // Execute update and check the result
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println(rows + " row(s) updated");
            } else {
                System.out.println("Manager ID " + id + " not found.");
            }
        } catch (SQLException e) {
        	e.getMessage();
        }
    }

    // Method to edit the phone number of a manager
    public static void editPhoneMag(int id, String phone) {
        try {
            // Prepare SQL statement to update manager phone number
            PreparedStatement stmt = DbmsConnection.getConnection()
                    .prepareStatement("update employee set phone_number=? where emp_id=? and role='manager'");
            stmt.setString(1, phone);
            stmt.setInt(2, id);

            // Execute update and check the result
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println(rows + " row(s) updated");
            } else {
                System.out.println("Manager ID " + id + " not found.");
            }
        } catch (SQLException e) {
        	e.getMessage();
        }
    }

    // Method to edit the role of a manager
    public static void editRoleMag(int id, String role) {
        try {
            // Prepare SQL statement to update manager role
            PreparedStatement stmt = DbmsConnection.getConnection()
                    .prepareStatement("update employee set role=? where emp_id=? and role='manager'");
            stmt.setString(1, role);
            stmt.setInt(2, id);

            // Execute update and check the result
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println(rows + " row(s) updated");
            } else {
                System.out.println("Manager ID " + id + " not found.");
            }
        } catch (SQLException e) {
        	e.getMessage();
        }
    }

    // Method to edit the city of a manager
    public static void editCityMag(int id, String city) {
        try {
            // Prepare SQL statement to update manager city
            PreparedStatement stmt = DbmsConnection.getConnection()
                    .prepareStatement("update employee set city=? where emp_id=? and role='manager'");
            stmt.setString(1, city);
            stmt.setInt(2, id);

            // Execute update and check the result
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println(rows + " row(s) updated");
            } else {
                System.out.println("Manager ID " + id + " not found.");
            }
        } catch (SQLException e) {
        	e.getMessage();
        }
    }

    // Method to delete an employee
    public static void delemp(int emp_ID) {
        try {
            // Prepare SQL statement to delete an employee
            PreparedStatement stmt = DbmsConnection.getConnection()
                    .prepareStatement("delete from employee where emp_id=? and role='employee'");
            stmt.setInt(1, emp_ID);

            // Execute delete and print the number of rows affected
            int rows = stmt.executeUpdate();
            System.out.println(rows + " Deleted");
        } catch (SQLException e) {
        	e.getMessage();
        }
    }

    // Method to add a new admin
    public static void addadmin(Admin a) {
        try {
            // Prepare SQL statement to insert a new admin
            PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement(
                    "insert into admin(admin_id,name,admin_password,email)values(adseq.nextval,?,?,?)");
            stmt.setString(1, a.getName());
            stmt.setString(2, a.getPassword());
            stmt.setString(3, a.getMail());

            // Execute insert and print the number of rows affected
            int rows = stmt.executeUpdate();
            System.out.println(rows + " Updated");
        } catch (SQLException e) {
        	e.getMessage();
        }
    }

}





//public static void addEmpList(Employee emp) {
//	List<Employee> empList = new ArrayList<Employee>();
//    try {
//       
//        PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement(
//            "INSERT INTO employee(emp_id, name, email, password, gender, phone_number, role, status_id, city, mag_id, hiredate) VALUES(empseq.nextval,?,?,?,?,?,?,?,?,?,?)");
//
//        
//        for (Employee i : empList) {
//            stmt.setString(1, i.getName());
//            stmt.setString(2, i.getMail());
//            stmt.setString(3, i.getPassword());
//            stmt.setString(4, i.getGender());
//            stmt.setString(5, i.getNumber());
//            stmt.setString(6, i.getRole());
//            stmt.setInt(7, i.getTask_status());
//            stmt.setString(8, i.getCity());
//            stmt.setInt(9, i.getMag_id());
//            stmt.setString(10, i.getHireDate());
//            stmt.addBatch(); // Add the current employee to the batch
//        }
//
//        int[] rows = stmt.executeBatch(); // Execute batch insert
//        for (int row : rows) {
//            System.out.println(row + " rows updated");
//        }
//
//        // closing the PreparedStatement
//        stmt.close();
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//}
