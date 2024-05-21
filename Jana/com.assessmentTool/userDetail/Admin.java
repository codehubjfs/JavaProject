package com.userDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.courseDetail.Course;
import com.exceptionDetails.EmailException;
import com.exceptionDetails.InvalidOptionException;
import com.exceptionDetails.PasswordException;
import com.exceptionDetails.UserNameException;
import com.exceptionDetails.Validate;
import com.smartcliff.assessmentTool.App;
import com.smartcliff.assessmentTool.DbmsConnection;

public class Admin {

//	private int adminId;
//	private String adminName;
//	private int eid;
//	private String firstName;
//	private String lastName;
//	private String Dept;
//	private String dob;
//	private String gender;
//	private String city;
//	private String country;
//	private String courseId;
//	private String courseName;
	private static String email;
	private static String password;
	private int cid;
	private String cName;

	static Scanner sc = new Scanner(System.in);

	public static void addEducator() throws SQLException, InvalidOptionException, UserNameException {
		boolean flag = true;
		String firstName = "";
		String lastName = "";
		String city = "";
		String country = "";
		String gender = "";

		while (flag) {

			System.out.println("Enter Email");
			email = sc.next();
			try {
				Validate.validateEmailValidate(email);
				flag = false;
			} catch (EmailException e) {
				System.out.println(e.getMessage());

			}
		}
		while (!flag) {
			System.out.println("set password");
			password = sc.next();
			try {
				Validate.passwordValidate(password);
				flag = true;
			} catch (PasswordException e) {
				System.out.println(e.getMessage());
			}
		}

		while (true) {
			System.out.println("Enter FirstName");
			firstName = sc.next();
			sc.nextLine();
			if (firstName.length() > 20) {
				System.out.println("**Length of First Name Should not Exceed 20 Characters**");
				continue;
			}
			if (Validate.validateName(firstName)) {
				break;
			} else {
				System.out.println("First name cannot contain numeric or special characters.");
			}
		}

		while (true) {
			System.out.println("Enter LastName");
			lastName = sc.next();
			sc.nextLine();
			if (lastName.length() > 20) {
				System.out.println("**Length of Last Name Should not Exceed 20 Characters**");
				continue;
			}
			if (Validate.validateName(lastName)) {
				break;
			} else {
				System.out.println("Last name cannot contain numeric or special characters.");
			}
		}

		while (true) {
			System.out.println("Enter  Gender(M/F/T)");
			gender = sc.next().toUpperCase();
			if (Validate.validateGender(gender)) {
				break;
			} else {
				System.out.println("Invalid gender entered. Please enter M, F, or T.");
			}
		}

		sc.nextLine();

		while (true) {
			System.out.println("Enter  City");
			city = sc.nextLine();
			if (city.length() > 20) {
				System.out.println("**Length of City Name Should not Exceed 20 Characters**");
				continue;
			}
			if (Validate.validateName(city)) {
				break;
			} else {
				System.out.println("City Name cannot contain numeric or special characters.");
			}
		}
		// sc.nextLine();
		while (true) {
			System.out.println("Enter  Country");
			country = sc.nextLine();
			if (country.length() > 20) {
				System.out.println("**Length of Country Name Should not Exceed 20 Characters**");
				continue;
			}
			if (Validate.validateName(country)) {
				break;
			} else {
				System.out.println("Country Name cannot contain numeric or special characters.");
			}
		}
		System.out.println("---------------------Available Courses------------------------");
		try {
			String sql1 = "select * from course order by cid";
			PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				System.out.println("Course ID  :" + rs.getInt("cid"));
				System.out.println("Course Name:" + rs.getString("cname"));
				System.out.println(
						"|---------------------------------------------------------------------------------------------------------------------------------------------------|");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		int courseId = 0;
		while (true) {
			System.out.println("Select CourseId");
			try {
				courseId = Integer.parseInt(sc.next());
			} catch (NumberFormatException nf) {
				System.err.println("Only Numbers are allowed!");
				continue;
			}
			if (checkCourse(courseId)) {
				break;
			} else {
				System.out.println("No Such Course was Found!");

			}
		}

		Educator e = new Educator(email, password, firstName, lastName, gender, city, country);

		PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement(
				"INSERT INTO Educator (eId, email , password , fname ,lname,gender ,city ,country) VALUES (EduSeq.nextval, ?, ?, ?, ?, ?, ?,?)");

		stmt.setString(1, e.getEmail());
		stmt.setString(2, e.getPassword());
		stmt.setString(3, e.getEfirstName());
		stmt.setString(4, e.getelastName());
		stmt.setString(5, e.getegender());
		stmt.setString(6, e.getecity());
		stmt.setString(7, e.getecountry());

		int rows = stmt.executeUpdate();
		PreparedStatement selectIdstmt = DbmsConnection.getConnection()
				.prepareStatement("select eid from educator where email=? and password=?");
		selectIdstmt.setString(1, e.getEmail());
		selectIdstmt.setString(2, e.getPassword());

		ResultSet rs = selectIdstmt.executeQuery();
		int eid = 0;
		while (rs.next()) {
			eid = rs.getInt("eid");
		}

		PreparedStatement insertCoursestmt = DbmsConnection.getConnection()
				.prepareStatement("INSERT INTO educatorcourse(eId, cid ) VALUES (?,?)");
		insertCoursestmt.setInt(1, eid);
		insertCoursestmt.setInt(2, courseId);

		int update = insertCoursestmt.executeUpdate();
		if (rows == 1 && update == 1) {
			System.out.println("Inserted Successfully!");
		}

		App.adminFunctionalities(1);

	}

	public static boolean checkEducator(int id) {
		try {
			String sql1 = "select * from educator where eid=?";
			PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement(sql1);
			stmt.setInt(1, id);
			// ResultSet rs = stmt.executeQuery();

			int rows = stmt.executeUpdate();
			if (rows == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void removeEducator(int delete) throws InvalidOptionException, UserNameException {

		try {
			PreparedStatement estmt = DbmsConnection.getConnection()
					.prepareStatement("delete from educatorcourse where eid=?");
			estmt.setInt(1, delete);
			if (estmt.executeUpdate() != 0) {
				PreparedStatement stmt = DbmsConnection.getConnection()
						.prepareStatement("delete from educator where eid=?");
				stmt.setInt(1, delete);

				int rows = stmt.executeUpdate();
				if (rows == 0) {
					System.out.println("Educator ID doesn't exist!");
					App.adminFunctionalities(1);
				} else {
					System.out.println(rows + " record Deleted");
					App.adminFunctionalities(1);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//	public static void editEmail(int id) throws InvalidOptionException, UserNameException {
//		System.out.println("Enter new Email ID");
//		String email = sc.next();
//		try {
//			PreparedStatement stmt = DbmsConnection.getConnection()
//					.prepareStatement("update educator set email=? where eid=?");
//			stmt.setString(1, email);
//			stmt.setInt(2, id);
//			int rows = stmt.executeUpdate();
//			System.out.println(rows + " Updated");
//			App.adminFunctionalities(1);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//	}

	public static void editPassword(int id) throws InvalidOptionException, UserNameException {
		boolean flag = true;
		while (flag) {
			System.out.println("Set New password");
			password = sc.next();
			try {
				Validate.passwordValidate(password);
				flag = false;
			} catch (PasswordException e) {
				System.out.println(e.getMessage());
			}
		}
//		System.out.println("Enter new password");
//		String password = sc.next();
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update educator set password=? where eid=?");
			stmt.setString(1, password);
			stmt.setInt(2, id);
			int rows = stmt.executeUpdate();
			System.out.println(rows + " Updated");
			App.adminFunctionalities(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void editFirstName(int id) throws InvalidOptionException, UserNameException {

		String ename = "";
		while (true) {
			System.out.println("Enter New FirstName");
			ename = sc.nextLine();
			//sc.nextLine();
			if (ename.length() > 20) {
				System.out.println("**Length of First Name Should not Exceed 20 Characters**");
				continue;
			}
			if (Validate.validateName(ename)) {
				break;
			} else {
				System.out.println("First name cannot contain numeric or special characters.");
			}
		}
//		System.out.println("Enter new First Name");
//		String ename = sc.next();
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update educator set fname=? where eid=?");
			stmt.setString(1, ename);
			stmt.setInt(2, id);
			int rows = stmt.executeUpdate();
			System.out.println(rows + " Updated");
			App.adminFunctionalities(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void editLastName(int id) throws InvalidOptionException, UserNameException {

		String lName = "";
		while (true) {
			System.out.println("Enter New LastName");
			lName = sc.nextLine();
			//sc.nextLine();
			if (lName.length() > 20) {
				System.out.println("**Length of Last Name Should not Exceed 20 Characters**");
				continue;
			}
			if (Validate.validateName(lName)) {
				break;
			} else {
				System.out.println("Last name cannot contain numeric or special characters.");
			}
		}
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update educator set lname=? where eid=?");
			stmt.setString(1, lName);
			stmt.setInt(2, id);
			int rows = stmt.executeUpdate();
			System.out.println(rows + " Updated");
			App.adminFunctionalities(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void editCity(int id) throws InvalidOptionException, UserNameException {

		String city = "";
		while (true) {
			System.out.println("Enter New  City");
			city = sc.nextLine();
			//sc.nextLine();
			if (city.length() > 20) {
				System.out.println("**Length of City Name Should not Exceed 20 Characters**");
				continue;
			}
			if (Validate.validateName(city)) {
				break;
			} else {
				System.out.println("City Name cannot contain numeric or special characters.");
			}
		}
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update educator set city=? where eid=?");
			stmt.setString(1, city);
			stmt.setInt(2, id);
			int rows = stmt.executeUpdate();
			System.out.println(rows + " Updated");
			App.adminFunctionalities(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void editCountry(int id) throws InvalidOptionException, UserNameException {

		String country = "";
		while (true) {
			//sc.nextLine();
			System.out.println("Enter New Country");
			country = sc.nextLine();
			
			if (country.length() > 20) {
				System.out.println("**Length of Country Name Should not Exceed 20 Characters**");
				continue;
			}
			if (Validate.validateName(country)) {
				break;
			} else {
				System.out.println("Country Name cannot contain numeric or special characters.");
			}
		}
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update educator set country=? where eid=?");
			stmt.setString(1, country);
			stmt.setInt(2, id);
			int rows = stmt.executeUpdate();
			System.out.println(rows + " Updated");
			App.adminFunctionalities(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void editCid(int id) throws InvalidOptionException, UserNameException {

		System.out.println("Enter new Course ID");
		int cid = sc.nextInt();
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update educator set cid=? where eid=?");
			stmt.setInt(1, cid);
			stmt.setInt(2, id);
			int rows = stmt.executeUpdate();
			System.out.println(rows + " Updated");
			App.adminFunctionalities(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

//	public static void showEducators() throws InvalidOptionException, UserNameException {
//		try {
//			String sql1 = "select * from educator order by eid";
//			PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement(sql1);
//			ResultSet rs = stmt.executeQuery();
//
//			while (rs.next()) {
//
//				System.out.println("ID          :" + rs.getInt("eid"));
//				System.out.println("First Name  :" + rs.getString("fname"));
//				System.out.println("Last Name   :" + rs.getString("lname"));
//				System.out.println("Mail        :" + rs.getString("email"));
//				//System.out.println("Password    :" + rs.getString("password"));
//				System.out.println("Gender      :" + rs.getString("gender"));
//				System.out.println("City        :" + rs.getString("city"));
//				System.out.println("Country     :" + rs.getString("country"));
//				System.out.println("Course ID   :" + rs.getString("cid"));
//				System.out.println(
//						"|---------------------------------------------------------------------------------------------------------------|");
//			}
//			int rows = stmt.executeUpdate();
//			System.out.println(rows + " Selected");
//			App.adminFunctionalities(1);
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public static void showEducators() throws InvalidOptionException, UserNameException {
		List<Educator> educators = new ArrayList<>();

		try {
			String sql1 = "select * from educator order by eid";
			PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Educator educator = new Educator(rs.getInt("eid"), rs.getString("email"), rs.getString("password"),
						rs.getString("fname"), rs.getString("lname"), rs.getString("gender"), rs.getString("city"),
						rs.getString("country")

				);
				educators.add(educator);
			}

			// Print educators using streams
			StringBuilder sb = new StringBuilder();
			String format = "| %-10s | %-15s | %-15s | %-30s | %-15s | %-6s | %-15s | %-15s |%n";
			String lineSeparator = "+------------+-----------------+-----------------+--------------------------------+-----------------+--------+-----------------+-----------------+%n";

			sb.append(String.format(lineSeparator));
			sb.append(String.format(format, "ID", "First Name", "Last Name", "Mail", "Password", "Gender", "City",
					"Country"));
			sb.append(String.format(lineSeparator));

			System.out.print(sb.toString());
			educators.stream().forEach(System.out::println);

			App.adminFunctionalities(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addStudent() throws SQLException, InvalidOptionException, UserNameException {

		boolean flag = true;

		while (flag) {

			System.out.println("Enter Email");
			email = sc.next();
			try {
				Validate.validateEmailValidate(email);
				flag = false;
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());

			}
		}
		while (!flag) {
			System.out.println("set password");
			password = sc.next();
			try {
				Validate.passwordValidate(password);
				flag = true;
			} catch (PasswordException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		String sfirstName = "";
		String slastName = "";
		while (true) {
			System.out.println("Enter FirstName");
			sfirstName = sc.next();
			sc.nextLine();
			if (sfirstName.length() > 20) {
				System.out.println("**Length of First Name Should not Exceed 20 Characters**");
				continue;
			}
			if (Validate.validateName(sfirstName)) {
				break;
			} else {
				System.out.println("First name cannot contain numeric or special characters.");
			}
		}

		while (true) {
			System.out.println("Enter LastName");
			slastName = sc.next();
			sc.nextLine();
			if (slastName.length() > 20) {
				System.out.println("**Length of Last Name Should not Exceed 20 Characters**");
				continue;
			}
			if (Validate.validateName(slastName)) {
				break;
			} else {
				System.out.println("Last name cannot contain numeric or special characters.");
			}
		}
		String sgender = "";
		while (true) {
			System.out.println("Enter  Gender(M/F/T)");
			sgender = sc.next().toUpperCase();
			if (Validate.validateGender(sgender)) {
				break;
			} else {
				System.out.println("Invalid gender entered. Please enter M, F, or T.");
			}
		}
		sc.nextLine();
		String sCity = "";
		String sCountry = "";
		while (true) {
			System.out.println("Enter  City");
			sCity = sc.nextLine();
			if (sCity.length() > 20) {
				System.out.println("**Length of City Name Should not Exceed 20 Characters**");
				continue;
			}
			if (Validate.validateName(sCity)) {
				break;
			} else {
				System.out.println("City Name cannot contain numeric or special characters.");
			}
		}
		// sc.nextLine();
		while (true) {
			System.out.println("Enter  Country");
			sCountry = sc.nextLine();
			if (sCountry.length() > 20) {
				System.out.println("**Length of Country Name Should not Exceed 20 Characters**");
				continue;
			}
			if (Validate.validateName(sCountry)) {
				break;
			} else {
				System.out.println("Country Name cannot contain numeric or special characters.");
			}
		}
		System.out.println("---------------------Available Courses------------------------");
		try {
			String sql1 = "select * from course order by cid";
			PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				System.out.println("Course ID  :" + rs.getInt("cid"));
				System.out.println("Course Name:" + rs.getString("cname"));
				System.out.println(
						"|---------------------------------------------------------------------------------------------------------------------------------------------------|");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		int scid = 0;
		while (true) {
			System.out.println("Select CourseId");
			try {
				scid = Integer.parseInt(sc.next());
			} catch (NumberFormatException nf) {
				System.err.println("Only Numbers are allowed!");
				continue;
			}
			if (checkCourse(scid)) {
				break;
			} else {
				System.out.println("No Such Course was Found!");

			}
		}
		Student s = new Student(email, password, sfirstName, slastName, sgender, sCity, sCountry);

		PreparedStatement stustmt = DbmsConnection.getConnection().prepareStatement(
				"INSERT INTO student (sId, email , password , fname ,lname,gender ,city ,country ) VALUES (StuSeq.nextval, ?, ?, ?, ?, ?, ?,?)");

		stustmt.setString(1, s.getEmail());
		stustmt.setString(2, s.getPassword());
		stustmt.setString(3, s.getSfirstName());
		stustmt.setString(4, s.getSlastName());
		stustmt.setString(5, s.getsGender());
		stustmt.setString(6, s.getsCity());
		stustmt.setString(7, s.getsCountry());
		int rows = stustmt.executeUpdate();

		PreparedStatement selectIdstmt = DbmsConnection.getConnection()
				.prepareStatement("select sid from student where email=? and password=?");
		selectIdstmt.setString(1, s.getEmail());
		selectIdstmt.setString(2, s.getPassword());

		ResultSet rs = selectIdstmt.executeQuery();
		int sid = 0;
		while (rs.next()) {
			sid = rs.getInt("sid");
		}
		System.out.println("course" + scid);
		System.out.println("student" + sid);
		PreparedStatement insertCoursestmt = DbmsConnection.getConnection()
				.prepareStatement("INSERT INTO studentcourse(cId, sid ) VALUES (?,?)");
		insertCoursestmt.setInt(1, scid);
		insertCoursestmt.setInt(2, sid);

		int update = insertCoursestmt.executeUpdate();
		if (rows == 1 && update == 1) {
			System.out.println("Inserted Successfully!");
		}
		App.adminFunctionalities(2);

	}

	public static boolean checkStudent(int id) {
		try {
			String sql1 = "select * from student where sid=?";
			PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement(sql1);
			stmt.setInt(1, id);
			// ResultSet rs = stmt.executeQuery();

			int rows = stmt.executeUpdate();
			if (rows == 1) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static void removeStudent(int delete) throws InvalidOptionException, UserNameException {

		try {
			PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement("delete from student where sid=?");
			stmt.setInt(1, delete);

			int rows = stmt.executeUpdate();
			System.out.println(rows + " Deleted");
			App.adminFunctionalities(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void editStuEmail(int id) throws InvalidOptionException, UserNameException {

		System.out.println("Enter new Email ID");
		String email = sc.next();
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update student set email=? where sid=?");
			stmt.setString(1, email);
			stmt.setInt(2, id);
			int rows = stmt.executeUpdate();
			System.out.println(rows + " Updated");
			App.adminFunctionalities(2);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void editStuPassword(int id) throws InvalidOptionException, UserNameException {

		System.out.println("Enter new password");
		String password = sc.next();
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update student set password=? where sid=?");
			stmt.setString(1, password);
			stmt.setInt(2, id);
			int rows = stmt.executeUpdate();
			System.out.println(rows + " Updated");
			App.adminFunctionalities(2);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void editStuFirstName(int id) throws InvalidOptionException, UserNameException {

		System.out.println("Enter new First Name");
		String ename = sc.next();
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update student set fname=? where sid=?");
			stmt.setString(1, ename);
			stmt.setInt(2, id);
			int rows = stmt.executeUpdate();
			System.out.println(rows + " Updated");
			App.adminFunctionalities(2);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void editStuLastName(int id) throws InvalidOptionException, UserNameException {

		System.out.println("Enter new Last Name");
		String lname = sc.next();
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update student set lname=? where sid=?");
			stmt.setString(1, lname);
			stmt.setInt(2, id);
			int rows = stmt.executeUpdate();
			System.out.println(rows + " Updated");
			App.adminFunctionalities(2);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void editStuCity(int id) throws InvalidOptionException, UserNameException {

		System.out.println("Enter new city Name");
		String city = sc.next();
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update student set city=? where sid=?");
			stmt.setString(1, city);
			stmt.setInt(2, id);
			int rows = stmt.executeUpdate();
			System.out.println(rows + " Updated");
			App.adminFunctionalities(2);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void editStuCountry(int id) throws InvalidOptionException, UserNameException {

		System.out.println("Enter new Country Name");
		String country = sc.next();
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update student set country=? where sid=?");
			stmt.setString(1, country);
			stmt.setInt(2, id);
			int rows = stmt.executeUpdate();
			System.out.println(rows + " Updated");
			App.adminFunctionalities(2);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void editStuCid(int id) throws InvalidOptionException, UserNameException, SQLException {

		System.out.println("Enter new Course ID");
		int cid = 0;
		try {
			cid = Integer.parseInt(sc.next());
		} catch (NumberFormatException nf) {
			System.err.println("Only Numbers are allowed!");
			App.adminFunctionalities(2);
			;
		}
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update student set cid=? where sid=?");
			stmt.setInt(1, cid);
			stmt.setInt(2, id);
			int rows = stmt.executeUpdate();
			System.out.println(rows + " Updated");
			App.adminFunctionalities(2);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

//	public static void showStudents() throws InvalidOptionException, UserNameException {
//		try {
//			String sql1 = "select * from student order by sid";
//			PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement(sql1);
//			ResultSet rs = stmt.executeQuery();
//
//			while (rs.next()) {
//				// System.out.println("|--------------------------------------------------------------------------------|");
//				System.out.println("ID        :" + rs.getInt("sid"));
//				System.out.println("First Name:" + rs.getString("fname"));
//				System.out.println("Last Name :" + rs.getString("lname"));
//				System.out.println("Mail      :" + rs.getString("email"));
//				//System.out.println("Password  :" + rs.getString("password"));
//				System.out.println("Gender    :" + rs.getString("gender"));
//				System.out.println("City      :" + rs.getString("city"));
//				System.out.println("Country   :" + rs.getString("country"));
//				System.out.println("Course ID :" + rs.getString("cid"));
//				System.out.println(
//						"|---------------------------------------------------------------------------------------------------------------------------------------------------|");
//
//			}
//			int rows = stmt.executeUpdate();
//			// System.out.println(rows+" Selected");
//			App.adminFunctionalities(2);
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public static void showStudents() throws InvalidOptionException, UserNameException {
		List<Student> students = new ArrayList<>();

		try {
			String sql1 = "select * from student order by sid";
			PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Student student = new Student(rs.getInt("sid"), rs.getString("email"), rs.getString("password"),
						rs.getString("fname"), rs.getString("lname"), rs.getString("gender"), rs.getString("city"),
						rs.getString("country")
				// rs.getInt("cid")
				);
				students.add(student);
			}

			// Print students using streams
			StringBuilder sb = new StringBuilder();
			String format = "| %-10s | %-15s | %-15s | %-30s | %-15s | %-10s | %-15s | %-15s | \n";
			System.out.println(
					"+------------+-----------------+-----------------+--------------------------------+-----------------+------------+-----------------+-----------------+");
			sb.append(String.format(format, "ID", "First Name", "Last Name", "Mail", "Password", "Gender", "City",
					"Country"));
			sb.append(
					"+------------+-----------------+-----------------+--------------------------------+-----------------+------------+-----------------+-----------------+");
			System.out.println(sb.toString());
			students.stream().forEach(System.out::println);

			App.adminFunctionalities(2);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addCourse() throws SQLException, InvalidOptionException {
		System.out.println("Enter Course Name");
		sc.nextLine();
		String cName = sc.nextLine();
		Course c = new Course(cName);
		try {
			PreparedStatement cstmt = DbmsConnection.getConnection()
					.prepareStatement("INSERT INTO course (cid, cname ) VALUES (courseSeq.nextval, ?)");
			// cstmt.setInt(1, cid);
			cstmt.setString(1, c.getcName());

			System.out.println("Inserted Successfully!");

			int rows = cstmt.executeUpdate();
			System.out.println(rows + " Added");
			App.adminFunctionalities(3);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static boolean checkCourse(int id) {
		try {
			String sql1 = "select * from course where cid=?";
			PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement(sql1);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			int rows = stmt.executeUpdate();
			if (rows == 1) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static void removeCourse(int delete) throws InvalidOptionException, UserNameException {

		try {
			PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement("delete from course where cid=?");
			stmt.setInt(1, delete);

			int rows = stmt.executeUpdate();
			System.out.println(rows + " Deleted");
			App.adminFunctionalities(3);
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void editCourseName(int id) throws InvalidOptionException, UserNameException {

		System.out.println("Enter new course Name");
		String cname = sc.next();
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update course set cname=? where cid=?");
			stmt.setString(1, cname);
			stmt.setInt(2, id);

			int rows = stmt.executeUpdate();
			System.out.println(rows + " row Updated");

			App.adminFunctionalities(3);
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void ViewCourses() throws InvalidOptionException, UserNameException {
		try {
			String sql1 = "select * from course order by cid";
			PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				System.out.println("Course ID  :" + rs.getInt("cid"));
				System.out.println("Course Name:" + rs.getString("cname"));
				System.out.println(
						"|---------------------------------------------------------------------------------------------------------------------------------------------------|");
			}

			int rows = stmt.executeUpdate();
			App.adminFunctionalities(3);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
