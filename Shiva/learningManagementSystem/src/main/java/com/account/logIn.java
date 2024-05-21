package com.account;
import com.exceptions.*;
import com.courses.*;
import com.courses.Module;
import com.users.Admin;
import com.users.DBConnection;
import com.users.Student;
import com.users.StudentChoices;
import com.users.createUser;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;
public class logIn {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			System.out.println("\n\t------------------------------SDEMY-----------------------------------\n");
			Scanner sc=new Scanner(System.in);
			Statement st= DBConnection.getconnection().createStatement();
			while(true)
			{
				
			int choice=0;
			while(true)
			{
			System.out.println("1.Admin 2.Instructor 3.Students 4.Exit");
			try
			{
			choice=Integer.parseInt(sc.next());
			}
			catch(Exception e)
			{
				System.out.println("Enter valid number");
				continue;
			}
			break;
			}
			switch(choice)
			{
			case 1:
			{
				System.out.println("1.Log In");
				int ch=0;
				while(true)
				{
					try
					{
						ch=Integer.parseInt(sc.next());
					}
					catch(Exception e)
					{
						System.out.println("Enter valid Integer");
						continue;
					}
				break;
				}
				switch(ch)
				{
				case 1:
				{
				System.out.println("Username");
				String username="";
				while(true)
				{
				try
				{
				ExceptionHandler.isNumber(username=sc.next());
				ExceptionHandler.isLengthEnough(username);
				ExceptionHandler.containsSpecialCharacters(username);
				}
				catch(SpecialCharacterException e)
				{
					System.out.println("Enter UserName without Special Character");
					continue;
				}
				catch(IsNumberException e)
				{
					System.out.println("Enter UserName without number");
					continue;
				}
				catch(LengthException e)
				{
					System.out.println(e.getMessage());
					continue;
				}
				break;
				}
				String password="";
				System.out.println("Password");
				while(true)
				{
					try
					{
						password=sc.next();
						ExceptionHandler.passwordValidation(password);
					}
					catch(InvalidPasswordException e)
					{
						System.out.println("Invalid Password: "+e.getMessage());
						continue;
					}
				break;
				}
				ResultSet r=st.executeQuery("select username,password from admin where username='"+username+"' and password='"+password+"'");
				while(r.next())
				{
					System.out.println("Logged In successfully");
					if(r.getString("username").equals(username)&&r.getString("password").equals(password))
					{
						Admin.adminChoices(st);
					}
				}
				//DBConnection.getconnection().close();
				//System.out.println("Admin choices closed");
				break;
				}
				}
				System.out.println("Successfully loged out");
				break;
			}
			case 2:
			{
				System.out.println("enter username");
				String userName="";
				while(true)
				{
				try
				{
				 userName=sc.next();
				 ExceptionHandler.isNumber(userName);
				 ExceptionHandler.isLengthEnough(userName);
				 ExceptionHandler.containsSpecialCharacters(userName);
				}
				catch(IsNumberException e)
				{
					System.out.println(e.getMessage());
				}
				catch(SpecialCharacterException e)
				{
					System.out.println(e.getMessage());
					continue;
				}
				catch(LengthException e)
				{
					System.out.println(e.getMessage());
					System.out.println(e.getMessage());
					continue;
				}
				break;
				}
				System.out.println("Enter password");
				
				String password=sc.next();
				ResultSet r=st.executeQuery("select username,password from instructors where username='"+userName+"' and password='"+password+"'");
				while(r.next())
				{
					System.out.println(r.getString("username")+" "+r.getString("password"));
					System.out.println("Instructor Logged in successfully");
				}
				System.out.println("Successfully loged out");
				break;
			}
			case 3:
			{

				System.out.println("enter username");
				String username="";
				//username=sc.next();
				while(true)
				{
				try
				{
				 username=sc.next();
				 ExceptionHandler.isNumber(username);
				 ExceptionHandler.isLengthEnough(username);
				 ExceptionHandler.containsSpecialCharacters(username);
				}
				catch(IsNumberException e)
				{
					System.out.println(e.getMessage());
					continue;
				}
				catch(SpecialCharacterException e)
				{
					System.out.println(e.getMessage());
					continue;
				}
				catch(LengthException e)
				{
					System.out.println(e.getMessage());
					System.out.println(e.getMessage());
					continue;
				}
				break;
				}
				String password="";
				while(true)
				{
				System.out.println("enter password");
				password=sc.next();
				try
				{
				ExceptionHandler.passwordValidation(password);
				ExceptionHandler.isLengthEnough(password);
				}
				catch(InvalidPasswordException e)
				{
					System.out.println("Invalid Password "+e.getMessage());
					continue;
				}
				catch(LengthException e)
				{
					System.out.println(e.getMessage());
					continue;
				}
				catch(Exception e)
				{
					System.out.println("Enter valid Username");
					continue;
				}
				break;
				}
				ResultSet r=st.executeQuery("select username,password from student where username='"+username+"' and password='"+password+"'");
				while(r.next())
				{
					System.out.println(r.getString("username")+" "+r.getString("password"));
					//System.out.println(r.getInt("studentid"));
					//System.out.println(r.getString("name"));
					//System.out.println(r.getString("username"));
					//System.out.println(r.getString("password"));
					//Student student=new Student(r.getInt("student_id"),r.getString("name"),r.getString("username"),r.getString("password"),r.getString("email"),r.getString("major"));
					while(true)
					{
						if(r.getString("username").equals(username)&&r.getString("password").equals(password))
						{
							Student.choices(username, password, st);
							break;
						}
					}
					System.out.println("Student Logged out sucessfully");
				}
				System.out.println("Successfully loged out");
				break;
			}
			case 4:
				DBConnection.getconnection().close();
				System.out.println("Successfully loged out");
				System.exit(0);
			default:
				System.out.println("Invalid choice");
			}
			}
			//System.out.println("Hello");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

}
