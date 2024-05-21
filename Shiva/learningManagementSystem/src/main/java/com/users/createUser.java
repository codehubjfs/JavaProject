package com.users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.courses.nonIntegerException;
import com.exceptions.ExceptionHandler;
import com.exceptions.exceptionHandlers;
import com.exceptions.InvalidEmailException;
import com.exceptions.InvalidPasswordException;
import com.exceptions.IsNumberException;
import com.exceptions.LengthException;
import com.exceptions.NotIntegerException;
import com.exceptions.SpecialCharacterException;

public class createUser {
	public static void createUser(Statement st) throws SQLException, NotIntegerException, IOException
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Create user for admin,instructor,student\n1.Admin\n2.Instructor\n3.Student");
		int choice2=sc.nextInt();
		switch(choice2)
		{
		case 1:
		{
			System.out.println("Enter username");
			String username1="";
			while(true)
			{
				try
				{
			username1=sc.next();
			ExceptionHandler.containsSpecialCharacters(username1);
			ExceptionHandler.isLengthEnough(username1);
			ExceptionHandler.isNumber(username1);
				}
				catch(IsNumberException e)
				{
					System.out.println(e.getMessage());
					continue;
				}
				catch(LengthException e)
				{
					System.out.println(e.getMessage());
					continue;
				}
				catch(SpecialCharacterException e)
				{
					System.out.println(e.getMessage());
					continue;
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
					continue;
				}
				break;
			}
			System.out.println("Enter password");
			String password1=sc.next();
			while(true)
			{
				try
				{
				ExceptionHandler.isLengthEnough(password1);
				ExceptionHandler.passwordValidation(password1);
				}
				catch(LengthException e)
				{
					System.out.println(e.getMessage());
				}
				catch(InvalidPasswordException e)
				{
					System.out.println(e.getMessage());
					continue;
				}
				break;
			}
			st.executeUpdate("insert into admin(username,password) values('"+username1+"','"+password1+"')");
			System.out.println("Admin User created successfully");
			break;
		}
		case 2:
		{
			System.out.println("Hi");
			//System.out.println("Enter instructorid");
			//int instructorid=sc.nextInt();
			Scanner fc=new Scanner(System.in);
			System.out.println("Enter FirstName");
			String firstname=fc.next();
			System.out.println("Enter last name");
			String lastname=fc.next();
			fc.nextLine();
			String email="";
			while(true)
			{
			System.out.println("Enter email");
			email=fc.next();
			try
			{
			ExceptionHandler.isValidEmail(email);
			}
			catch(InvalidEmailException e)
			{
				System.out.println(e.getMessage());
				continue;
			}
			break;
			}
			System.out.println("Enter department");
			String department="";
			while(true)
			{
			try
			{
			department=fc.next();
			ExceptionHandler.isLengthEnough(department);
			}
			catch(LengthException e)
			{
				System.out.println(e.getMessage());
				continue;
			}
			break;
			}
			String username1="";
			while(true)
			{
				System.out.println("Enter username");
				username1=fc.next();
				try
				{
					ExceptionHandler.isLengthEnough(username1);
					ExceptionHandler.containsSpecialCharacters(username1);
					ExceptionHandler.isNumber(username1);
				}
				catch(LengthException e)
				{
					System.out.println(e.getMessage());
					continue;
				}
				catch(SpecialCharacterException e)
				{
					System.out.println(e.getMessage());
					continue;
				}
				catch(IsNumberException e)
				{
					System.out.println(e.getMessage());
					continue;
				}
				catch(Exception e)
				{
					System.out.println("Enter valid username");
					continue;
				}
				break;
			}
			String password1="";
			while(true)
			{
				try
				{
			System.out.println("Enter password");
			password1=fc.next();
			ExceptionHandler.isLengthEnough(password1);
			ExceptionHandler.containsSpecialCharacters(password1);
				}
				catch(LengthException e)
				{
					System.out.println(e.getMessage());
					continue;
				}
				catch(SpecialCharacterException e)
				{
					System.out.println(e.getMessage());
					continue;
				}
				catch(Exception e)
				{
					System.out.println("Password Invalid.\nEnter Valid Password");
					continue;
				}
				break;
			}
			st.executeUpdate("insert into instructor(instructorid,firstname,lastname,email,department,username,password) values(instructorseq.nextval,'"+firstname+"','"+lastname+"','"+email+"','"+department+"','"+username1+"','"+password1+"')");
			System.out.println("User created successfully");
			break;
		}
		case 3:
		{
			Scanner fc=new Scanner(System.in);
			//System.out.println("Enter Student id");
			//int student_id=fc.nextInt();
			//fc.nextLine();
			BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
			String name="";
			while(true)
			{
			System.out.println("Enter Name");
			name=bf.readLine();
			try
			{
				ExceptionHandler.isLengthEnough(name);
				ExceptionHandler.containsSpecialCharacters(name);
				ExceptionHandler.isNumber(name);
			}
			catch(LengthException e)
			{
				System.out.println(e.getMessage());
				continue;
			}
			catch(SpecialCharacterException e)
			{
				System.out.println(e.getMessage());
				continue;
			}
			catch(IsNumberException e)
			{
				System.out.println(e.getMessage());
				continue;
			}
			catch(Exception e)
			{
				System.out.println("Enter valid Name");
				continue;
			}
			break;
			}
			System.out.println("Enter DOB");
			String dob=fc.next();
			int grade=0;
			while(true)
			{
			System.out.println("Enter grade between 1 to 10");
			try
			{
			grade=Integer.parseInt(fc.next());
			}
			catch(Exception e)
			{
				System.out.println("Enter valid Number 1 to 10");
				continue;
			}
			break;
			}
			fc.nextLine();
			System.out.println("Enter username");
			String username1=fc.nextLine();
			String password1="";
			while(true)
			{
				System.out.println("Enter pasword");
				try
				{
				password1=fc.next();
				ExceptionHandler.passwordValidation(password1);
				}
				catch(InvalidPasswordException e)
				{
					System.out.println(e.getMessage());
					continue;
				}
				break;
			}
			String email="";
			while(true)
			{
			System.out.println("Enter email");
			email=fc.next();
			try
			{
				ExceptionHandler.isValidEmail(email);;
			}
			catch(InvalidEmailException e)
			{
				System.out.println(e.getMessage());
				continue;
			}
			break;
			}
			int courseId=0;
			while(true)
			{
			System.out.println("Enter Course Id");
			try
			{
			courseId=Integer.parseInt(fc.next());
			}
			catch(Exception e)
			{
				System.out.println("Enter valid number");
			}
			break;
			}
			fc.nextLine();
			String major="";
			while(true)
			{
			System.out.println("Enter Major");
			major=bf.readLine();
			try
			{
			ExceptionHandler.isNumber(major);
			ExceptionHandler.isLengthEnough(major);
			}
			catch(IsNumberException e)
			{
				System.out.println(e.getMessage());
				continue;
			}
			catch(LengthException e)
			{
				System.out.println(e.getMessage());
				continue;
			}
			break;
			}
			st.executeUpdate("insert into student(student_id,name,dob,grade,username,password,email,courseid,major) values(studentseq.nextval,'"+name+"','"+dob+"',"+grade+",'"+username1+"','"+password1+"','"+email+"',"+courseId+",'"+major+"')");
			System.out.println("User created successfully");
			break;
		}
		}
	}
}
