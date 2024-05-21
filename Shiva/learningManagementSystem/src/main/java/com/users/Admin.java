package com.users;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import com.courses.Assessment;
import com.courses.Content;
import com.courses.Courses;
import com.courses.Module;
import com.courses.Question;
import com.courses.Topic;
import com.courses.DbConnection;
import com.courses.Files;
import com.exceptions.ContainsNumberException;
import com.exceptions.ExceptionHandler;
import com.exceptions.exceptionHandlers;
import com.exceptions.InvalidDateException;
import com.exceptions.InvalidFileException;
import com.exceptions.IsNumberException;
import com.exceptions.LengthException;
import com.exceptions.NotIntegerException;
import com.exceptions.SpecialCharacterException;
import com.users.DBConnection;
public class Admin {
	Scanner sc=new Scanner(System.in);
	private String username;
	private String password;
	private String name;
	private String email;
	public Admin()
	{
		
	}
	public Admin(String username, String password, String name, String email) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Admin [username=" + username + ", password=" + password + ", name=" + name + ", email=" + email
				+ ", getUsername()=" + getUsername() + ", getPassword()=" + getPassword() + ", getName()=" + getName()
				+ ", getEmail()=" + getEmail() + "]";
	}
	public static void adminChoices(Statement st) throws SQLException, ClassNotFoundException, NotIntegerException, IOException, SpecialCharacterException
	{
		Admin admin=new Admin();
		Scanner sc=new Scanner(System.in);
		int flg=0;
		while(true)
		{
		System.out.println("Enter\n1.Course Management\n2.Create User\n3.Module Management\n4.Topic Management\n5.Assessment Management\n6.Question Management\n7.View Details\n8.Content Management\n9.View Users\n11.Log Out");
		int choice3=0;
		while(true)
		{
			try
			{
		choice3=Integer.parseInt(sc.next());
			}
			catch(Exception e)
			{
				System.out.println("Enter valid choice");
				continue;
			}
			break;
		}
		switch(choice3)
		{
		case 1:
		{
			System.out.println("Instructor Table");
			admin.viewInstructors(st);
			int choices4=0;
			while(true)
			{
				try
				{
					System.out.println("Enter\n1.add course\n2.Rename course\n3.delete course\n4.Edit Start Date\n5.Edit End Date\n6.Back");
					choices4=Integer.parseInt(sc.next());
				}
				catch(Exception e)
				{
					System.out.println("Enter valid number");
					continue;
				}
				break;
			}
		switch(choices4)
		{
		case 1:
		{
			BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
			Scanner f=new Scanner(System.in);
			//String courseName="";
			int instructorId=0;
			
			String courseName="";
			while(true)
			{
				try
				{
					System.out.println("Enter Course Name");
					courseName=bf.readLine();
					ExceptionHandler.containsSpecialCharacters(courseName);
					ExceptionHandler.isLengthEnough(courseName);
					ExceptionHandler.isNumber(courseName);
					ExceptionHandler.containsNumber(courseName);
				}
				catch(SpecialCharacterException e)
				{
					System.out.println(e.getMessage());
					continue;
				}
				catch(LengthException e)
				{
					System.out.println(e.getMessage());
					continue;
				}
				catch(ContainsNumberException e)
				{
					System.out.println(e.getMessage());
					continue;
				}
				catch(IsNumberException e)
				{
					System.out.println(e.getMessage());
					System.out.println("Do Not Enter Number");
					continue;
				}
				break;
			}
			//
			boolean flag=true;
			while(flag)
			{
				int insId=0;
			try
			{
			System.out.println("Enter InstructorId");
			insId=Integer.parseInt(f.next());
			}
			catch(Exception e)
			{
				System.out.println("\nEnter valid Number");
				continue;
			}
			flag=false;
			instructorId=insId;
			//f.close();
			}
			//LocalDate startDate=LocalDate.of(2002, 12,11);
			flag=true;
			//while(flag)
			//{
			//try
			//{
			LocalDate startDate=null;
			while(true)
			{
				int day=0;
				int month=0;
				int year=0;
				System.out.println("Enter StartDate");
				//System.out.println("Enter year,month and day");
				try
				{
					while(true)
					{
					System.out.println("Enter Day");
					day=Integer.parseInt(sc.next());
					if(day>31)
					{
					System.out.println("Do not Enter Day greater than 31");
					continue;
					}
					break;
					}
					while(true)
					{
					System.out.println("Enter Month");
					month=Integer.parseInt(sc.next());
					if(month>12)
					{
						System.out.println("Enter Month lesser than or equal to 12");
						continue;
					}
					break;
					}
					while(true)
					{
					System.out.println("Enter Year in 4 Charcter length eg.2024 (YYYY)");
					year=Integer.parseInt(sc.next());
					try
					{
					ExceptionHandler.isLengthEnough(year+"");
					}
					catch(LengthException e)
					{
						System.out.println(e.getMessage());
						continue;
					}
					break;
					}
			startDate=LocalDate.of(day,month,year);
			//ExceptionHandler.isDate(startDate.toString());
			if(startDate.isBefore(LocalDate.now()))
			{
				throw new Exception("Do Not Enter Past Date");
			}
				}
				catch(InvalidDateException e)
				{
					System.out.println(e.getMessage());
				}
				catch(Exception e)
				{
					//System.out.println(e.getMessage());
					System.out.println("Enter valid Date Format (YYYY-MM-dd)");
					System.out.println("Enter Valid Number");
					continue;
				}
				break;
			}
			//startDate=sDate;
			//}
			//catch(Exception e)
			//{
				//System.out.println(e.getMessage());
				//continue;
			//}
			//flag=false;
			//}
			//flag=true;
			
			LocalDate endDate=null;
			while(true)
			{
				try {
					System.out.println("Enter EndDate");
			endDate=LocalDate.of(Integer.parseInt(sc.next()),Integer.parseInt(sc.next()),Integer.parseInt(sc.next()));
			//exceptionHandler.isDate(endDate.toString());
			ExceptionHandler.isDate(endDate.toString());
			if(!startDate.isBefore(endDate))
			{
				throw new InvalidDateException("Start Date Before ");
			}
				}
				catch(InvalidDateException e)
				{
					System.out.println("Enter valid number");
					continue;
				}
				break;
			}
			Courses courses=new Courses(courseName,instructorId,startDate,endDate);
			admin.addCourse(courses,st);
		    admin.viewCourse(st);
			break;
		}
		case 2:
		{
			admin.viewCourse(st);
			Scanner fc=new Scanner(System.in);
			boolean flag=true;
			String name="";
			while(flag)
			{
			BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
			String cname="";
			while(true)
			{
				System.out.println("\nEnter Name to rename");
		     cname=bf.readLine();
		     try
		     {
		    	 ExceptionHandler.isLengthEnough(cname);
		    	 ExceptionHandler.isNumber(cname);
		    	 ExceptionHandler.containsSpecialCharacters(cname);
		    	 ExceptionHandler.containsNumber(cname);
		     }
		     catch(ContainsNumberException e)
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
		     }
		     catch(IsNumberException e)
		     {
		    	 System.out.println(e.getMessage());
		    	 continue;
		     }
		     catch(Exception e)
		     {
		    	 System.out.println("\nEnter Valid Course Name");
		    	 continue;
		     }
		     break;
			}
			if(exceptionHandlers.isString(cname))
			{
				name=cname;
				break;
			}
			}
			int id=0;
			while(true)
			{
				int id_=0;
			System.out.println("\nEnter Course id");
			try
			{
			 id_=Integer.parseInt(fc.next());
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				continue;
			}
			id=id_;
			break;
			}
			ResultSet rs=st.executeQuery("select * from courses where courseid="+id+"");
			Courses courses;
			while(rs.next())
			{
				LocalDate startDate=rs.getDate("startdate").toLocalDate();
				LocalDate endDate=rs.getDate("enddate").toLocalDate();
			courses=new Courses(id,name,rs.getInt("instructorid"),startDate,endDate);
			System.out.println(courses.getCourseName()+" "+courses.getInstructorid()+" "+courses.getEndDate()+" "+courses.getStartDate());
			admin.renameCourse(courses,st);
			admin.viewCourse(st);
			}
			break;
		}
		case 3:
		{
			admin.viewCourse(st);
			Scanner fc=new Scanner(System.in);
			System.out.println("Delete course\nEnter Course Name");
			
			String name=fc.nextLine();
			ResultSet rs=st.executeQuery("select * from courses where coursename='"+name+"'");
			Courses courses;
			while(rs.next())
			{
				LocalDate startDate=rs.getDate("startdate").toLocalDate();
				LocalDate endDate=rs.getDate("enddate").toLocalDate();
			courses=new Courses(rs.getInt("courseid"),name,rs.getInt("instructorid"),startDate,endDate);
			System.out.println(courses.getCourseName()+" "+courses.getInstructorid()+" "+courses.getEndDate()+" "+courses.getStartDate());
			admin.deleteCourse(courses,st);
			admin.viewCourse(st);
			}
			break;
		}
		case 4:
		{
			admin.viewCourse(st);
			Scanner fc=new Scanner(System.in);
			String name="";
			while(true)
			{
			System.out.println("Enter Course Name\n");
			String name_=fc.nextLine();
			try
			{
			ExceptionHandler.containsSpecialCharacters(name_);
			ExceptionHandler.isLengthEnough(name_);
			ExceptionHandler.isNumber(name_);
			ExceptionHandler.containsNumber(name_);
			}
			catch(SpecialCharacterException e)
			{
				System.out.println(e.getMessage());
				continue;
			}
			catch(LengthException e)
			{
				System.out.println(e.getMessage());
				continue;
			}
			catch(ContainsNumberException e)
			{
				System.out.println(e.getMessage());
				continue;
			}
			catch(IsNumberException e)
			{
				System.out.println(e.getMessage());
				System.out.println("Do not Enter Number");
				continue;
			}
			catch(Exception e)
			{
				System.out.println("Enter valid Course Name");
				continue;
			}
			break;
			}
			LocalDate start=LocalDate.now();
			BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
			while(true)
			{
				System.out.println("Enter Date (YYYY-MM-dd)");
				try
				{
				String startD=bf.readLine();
				DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
				//start=LocalDate.parse(startD).format(formatter);
				start=LocalDate.parse(startD,formatter);
				System.out.println(start);
				}
				catch(DateTimeParseException e)
				{
					System.out.println("Enter valid Date (YYYY-MM-dd)");
					continue;
				}
				break;
			}
			/*while(true)
			{
			System.out.println("Edit start date\n");
			//System.out.println("Enter\nYear\nMonth\nDay");
			LocalDate start_=LocalDate.now();
			try
			{
			while(true)
			{
			System.out.println("Enter Year (YYYY)");
			try
			{
			int year=bf.read();
			ExceptionHandler.isLengthEnough(year+"");
			}
			catch(LengthException e)
			{
				System.out.println(e.getMessage());
				continue;
			}
			catch(Exception e)
			{
				System.out.println("enter valid number");
				continue;
			}
			break;
			}
			System.out.println("Enter Month (MM)");
			int month=bf.read();
			System.out.println("Enter Day (DD)");
			int day=bf.read();
			start_=LocalDate.of(Integer.parseInt(fc.next()),Integer.parseInt(fc.next()) ,Integer.parseInt(fc.next()));
			}
			catch(Exception e)
			{
				System.out.println("Enter Valid Date Format (YYYY-MM-dd)");;
				continue;
			}
			if(exceptionHandlers.isDate(start_.toString()))
			{
				start=start_;
				break;
			}
			}*/
			ResultSet rs=st.executeQuery("select * from courses where coursename='"+name+"'");
			Courses courses;
			while(rs.next())
			{
				LocalDate endDate=rs.getDate("enddate").toLocalDate();
				/*if(start.isAfter(endDate))
				{
					System.out.println("Your start date is beyond the End Date of the resepective Course");
					
				}*/
				courses=new Courses(rs.getInt("courseid"),name,rs.getInt("instructorid"),start,endDate);
				System.out.println(courses.getCourseName()+" "+courses.getInstructorid()+" "+courses.getEndDate()+" "+courses.getStartDate());
				admin.editstart(courses, st);
				admin.viewCourse(st);
			}
			break;
		}
		case 5:
		{
			Scanner fc=new Scanner(System.in);
			System.out.println("Enter Course Name\n");
			String name=fc.nextLine();
			System.out.println("Edit end date\n");
			System.out.println("Enter\nYear\nMonth\nDay");
			LocalDate end=LocalDate.now();
			while(true)
			{
				try
				{
			end=LocalDate.of(Integer.parseInt(fc.next()),Integer.parseInt(fc.next()) ,Integer.parseInt(fc.next()));
			//ExceptionHandler.isDate(name);
			//exceptionHandler.isDateAfterStartDate(start, name);
				}
				/*catch(InvalidDateException e)
				{
					System.out.println(e.getMessage());
					continue;
				}*/
				catch(Exception e)
				{
					System.out.println("Enter valid date (YYYY-MM-dd)");
					continue;
				}
			break;
			}
			ResultSet rs=st.executeQuery("select * from courses where coursename='"+name+"'");
			Courses courses;
			while(rs.next())
			{
				LocalDate startDate=rs.getDate("startdate").toLocalDate();
				courses=new Courses(rs.getInt("courseid"),name,rs.getInt("instructorid"),startDate,end);
				System.out.println(courses.getCourseName()+" "+courses.getInstructorid()+" "+courses.getEndDate()+" "+courses.getStartDate());
				admin.editend(courses, st);
				admin.viewCourse(st);
			}
			//Courses.editend(new Courses(),st);
			break;
		}
		case 6:
		{
			break;
		}
		default:
			System.out.println("Enter valid number");
			break;
		}
		break;
		}

		case 2:
		{
			createUser.createUser(st);
			
		 break;
		}
		case 3:
		{
		//System.out.println("Enter \n1.Add Module\n2.Edit Module\n3.Delete Module");
		System.out.println("Instructor Table");
		admin.viewInstructors(st);
		System.out.println("Courses Table");
		admin.viewCourse(st);
		int choice5=0;
		while(true)
		{
			System.out.println("Enter \n1.Add Module\n2.Edit Module\n3.Delete Module\n4.Back");
			try
			{
			choice5=Integer.parseInt(sc.next());
			}
			catch(Exception e)
			{
				System.out.println("Enter valid number");
				continue;
			}
			break;
		}
		switch(choice5)
		{
		case 1:
		{
			/*BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter details to create a module");
			Scanner fc=new Scanner(System.in);
			//String moduleName="";
			
			System.out.println("Enter Module Name");
			String moduleName="";
			while(true)
			{
				try
				{
			ExceptionHandler.containsSpecialCharacters(moduleName=bf.readLine());
				}
				catch(SpecialCharacterException e)
				{
					System.out.println(e.getMessage());
					continue;
				}
				break;
			}
			int instructorid=0;
			while(true)
			{
				int insid=0;
			System.out.println("Enter Instructor Id");
			try
			{
			insid=Integer.parseInt(fc.next());
			}
			catch(Exception e)
			{
				System.out.println("Enter valid number");
				continue;
			}
			instructorid=insid;
			break;
			}
			int courseid=0;
			while(true)
			{
			System.out.println("Enter Course Id");
			int cid=0;
			try
			{
			cid=Integer.parseInt(fc.next());
			}
			catch(Exception e)
			{
				e.getMessage();
				continue;
			}
			courseid=cid;
			break;
			}
			Module module=new Module(moduleName,instructorid,courseid);*/
			System.out.println("Instructor Table");
			admin.viewInstructors(st);
			admin.addModule(st);
			admin.viewModules(st);
			break;
		}
		case 2:
		{
			System.out.println("Courses Table");
			admin.viewCourse(st);
			System.out.println("Instructor Table");
			admin.viewInstructors(st);
			System.out.println("Module Table");
			admin.viewModules(st);
			admin.editModule(st);
			//admin.viewModules(st);
			break;
		}
		case 3:
		{
			System.out.println("Module Table");
			admin.viewModules(st);
			admin.deleteModule(st);
			break;
		}
		case 4:
		{
			break;
		}
		default:
		{
			System.out.println("Enter valid number");
			break;
		}
		}
		break;
		}
		case 4:
		{
			System.out.println("Modules Table");
			admin.viewModules(st);
			BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
			int choice=0;
			while(true)
			{
				System.out.println("\n1.Add Topic\n2.Edit Topic\n3.delete Topic\n4.Back");
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
			System.out.println("Module Table");
			//admin.viewModules(st);
			admin.addTopic(st);
			break;
		}
		case 2:
		{
			System.out.println("Modules Table");
			admin.viewModules(st);
			System.out.println("Topic Table");
			admin.viewTopic(st);
			admin.editTopic(st);
			break;
		}
		case 3:
		{
			admin.deleteTopic(st);
			break;
		}
		case 4:
		{
			break;
		}
		default:
		{
			System.out.println("Enter valid number");
			break;
		}
		}
		break;
		}
		case 5:
		{
			System.out.println("Topic Table");
			admin.viewTopic(st);
			BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter\n1.Add Assessment\n2.Edit Assessment\n3.Delete Assessment\n4.Back");
		int choice=0;
		while(true)
		{
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
			admin.addAssessment(st);
			break;
		}
		case 2:
		{
			admin.editAssessment(st);
			break;
		}
		case 3:
		{
			System.out.println("Assessment Table");
			admin.viewAssessment(st);
			admin.deleteAssessment(st);
			break;
		}
		case 4:
		{
			break;
		}
		default:
		{
			System.out.println("Enter valid number");
			break;
		}
		}
		break;
		}
		case 6:
		{
			//System.out.println("Assessment Table");
			System.out.println("Assessment Table");
			admin.viewAssessment(st);
			System.out.println("Enter\n1.Add Question\n2.Edit Question\n3.Delete Question\n4.Back");
			BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
			int choice=0;
			while(true)
			{
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
			admin.addQuestion(st);
			break;
		}
		case 2:
		{
			admin.editQuestion(st);
			break;
		}
		case 3:
		{
			admin.deleteQuestion(st);
			break;
		}
		case 4:
		{
			break;
		}
		default :
		{
			System.out.println("Enter valid number");
		}
		}
			break;
		}
		case 7:
		{
			BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter\n1.View Courses\n2.View Modules\n3.View Topic\n4.View Content");
			int choice=0;
			while(true)
			{
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
			admin.viewCourse(st);
			break;
		}
		case 2:
		{
			admin.viewModules(st);
			break;
		}
		case 3:
		{
			admin.viewTopic(st);
			break;
		}
		case 4:
		{
			admin.viewContents(st);
			break;
		}
		default:
		{
			System.out.println("Enter valid option");
			break;
		}
		}
		break;
		}
		case 8:
		{
			System.out.println("Enter 1.View Content\n2.Add Content\n3.Edit Content\n4.Delete Content\n5.break");
			Scanner fc=new Scanner(System.in);
			int choice=0;
			while(true)
			{
				try
				{
				choice=Integer.parseInt(fc.next());
				}
				catch(Exception e)
				{
					System.out.println("Enter valid Integer");
					continue;
				}
				break;
			}
			switch(choice)
			{
			case 1:
			{
			admin.viewContents(st);
			break;
			}
			case 2:
			{
				admin.addContent(st);
				break;
			}
			case 3:
			{
				admin.editContent(st);
				break;
			}
			case 4:
			{
				admin.delete(st);
				break;
			}
			case 5:
			{
				break;
			}
			default:
			{
				System.out.println("Enter valid number");
				break;
			}
			}
			break;
		}
		case 9:
		{
			int choice=0;
			while(true)
			{
				System.out.println("Enter\n1.View Admin\n2.View Instructors\n3.View Students");
				try
				{
				choice=Integer.parseInt(sc.next());
				if(choice>3)
				{
					throw new Exception();
				}
				}
				catch(Exception e)
				{
					System.out.println("Enter valid Choice");
					continue;
				}
				break;
			}
			switch(choice)
			{
			case 1:
			{
				Admin Admin=new Admin();
				Admin.viewAdmins(st);
				break;
			}
			case 2:
			{
				admin.viewInstructors(st);
				break;
			}
			case 3:
			{
				Student.viewStudents(st);
				break;
			}
			default:
			{
				System.out.println("Enter options within 1-3");
				break;
			}
			}
			break;
		}
		case 10:
		{
			System.out.println("Enter 1.View Files\n2.Add Files\n3.Edit Files\n4.Delete Files\n5.break");
			Scanner fc=new Scanner(System.in);
			int choice=0;
			while(true)
			{
				try
				{
				choice=Integer.parseInt(fc.next());
				}
				catch(Exception e)
				{
					System.out.println("Enter valid Integer");
					continue;
				}
				break;
			}
			switch(choice)
			{
			case 1:
			{
				
				break;
			}
			}
		}
		case 11:
		{
			flg=1;
			break;
		}
		default:
		{
			System.out.println("Enter valid number");
			break;
		}
		}//This is it
		if(flg==1)
		{
			break;
		}
		}
	}//////
	public void viewAdmins(Statement st) throws SQLException
	{
		ResultSet rs=st.executeQuery("select * from admin");
		while(rs.next())
		{
			System.out.println("Admin Id "+rs.getInt("adminid")+" Admin username "+rs.getString("username")+" Admin Password "+rs.getString("password")+" Username "+rs.getString("username")+" Email "+rs.getString("email"));
		}
	}
	public void addCourse(Courses courses,Statement st) throws SQLException
	{
		try {
			//Connection con=DBConnection.getconnection();
			//Statement st=con.createStatement();
			st.executeQuery("insert into courses values(id.nextval,'"+courses.getCourseName()+"',"+courses.getInstructorid()+",to_date('"+courses.getStartDate().toString()+"','YYYY-MM-dd'),to_date('"+courses.getEndDate().toString()+"','YYYY-MM-dd'))");
			System.out.println("Successfully Executed");
			ResultSet Rs=st.executeQuery("select * from courses");
			ArrayList<Courses>al=new ArrayList<Courses>();
			while(Rs.next())
			{
				System.out.println("Course Id "+Rs.getInt("courseid")+" Course name "+Rs.getString("coursename")+" "+" instructor id"+Rs.getString("instructorid")+" "+"start date"+Rs.getDate("startdate")+" "+"end date"+Rs.getDate("enddate"));
				Courses course=new Courses(Rs.getInt("courseid"),Rs.getString("coursename"),Rs.getInt("instructorid"),Rs.getDate("startdate").toLocalDate(),Rs.getDate("enddate").toLocalDate());
				al.add(course);
			}
			Collections.sort(al);
			//System.out.println(courses.getCal().size()); name = new ();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void renameCourse(Courses courses,Statement st) throws SQLException
	{
		//Connection con=DBConnection.getconnection();
		//Statement st;
		System.out.println("Courses Table");
		 viewCourse(st);
		int rows=0;
		try {
			//st = con.createStatement();
			rows=st.executeUpdate("update courses set coursename='"+courses.getCourseName().toLowerCase()+"' where courseid="+courses.getCourseID()+"");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rows==0)
		{
			System.out.println("No Course Found");
			renameCourse(courses, st);
		}
		else
		{
		System.out.println("Renamed Successfully");
		}
	}
	public void deleteCourse(Courses courses,Statement st) 
	{
		try
		{
		int rows=st.executeUpdate("delete from courses where coursename='"+courses.getCourseName()+"'");
		if(rows==0)
		{
			System.out.println("No Course Found");
			deleteCourse(courses, st);
		}
		else
		{
		System.out.println(rows+" rows Deleted Successfully ");
		}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			deleteCourse(courses, st);
		}
	}
	public void editstart(Courses courses,Statement st)
	{
		try
		{
		int rows=st.executeUpdate("update courses set startdate=to_date('"+courses.getStartDate().toString()+"','YYYY-MM-dd') where coursename='"+courses.getCourseName()+"'");
		if(rows>0)
		{
		System.out.println("Start Date Edited Successfully ");
		}
		else
		{
			System.out.println("Course Not Found");
			editstart(courses, st);
		}
		}
		catch(Exception e)
		{
			System.out.println("Invalid input.Enter valid input");
			editstart(courses, st);
		}
	}
	public void editend(Courses courses,Statement st)
	{
		try
		{
			int rows=st.executeUpdate("update courses set enddate=to_date('"+courses.getEndDate().toString()+"','YYYY-MM-dd') where coursename='"+courses.getCourseName()+"'");
			if(rows>0)
			{
			System.out.println("End date Edited Successfully ");
			}
			else
			{
				System.out.println("No course found");
			}
		}
		catch(Exception e)
		{
			System.out.println("Invalid input enter valid input");
			editend(courses, st);
		}
	}
	public void viewCourse(Statement st) throws SQLException
	{
		ResultSet rs=st.executeQuery("select * from courses");
		System.out.println("  "+"Course Id"+"  "+"  "+"Course Name"+" "+"  "+"Instructor Id"+" "+"  "+"start date"+" "+"  "+"end date");
		Courses courses = new Courses(0,"",0,LocalDate.now(),LocalDate.now());
		ArrayList<Courses>courseList=new ArrayList<Courses>();
		while(rs.next())
		{
			courses=new Courses(rs.getInt("courseid"),rs.getString("coursename"),rs.getInt("instructorid"),rs.getDate("startdate").toLocalDate(),rs.getDate("enddate").toLocalDate());
			//System.out.println("\t"+courses.getCourseID()+"\t\t"+courses.getCourseName()+"\t\t"+courses.getInstructorid()+"\t\t"+courses.getStartDate()+"\t"+courses.getEndDate());
			courseList.add(courses);
		}
		//courses.setCal(CAL);
		//Collections.sort(courses.getCal());
		courseList.stream().forEach((x)->System.out.println(x.getCourseID()+" "+x.getCourseName()+" "+x.getInstructorid()+" "+x.getStartDate()+" "+x.getEndDate()));
		//CAL.stream().sorted((x)->ComparingInt::)
		/*System.out.println(courses.getCal().size());
		for(int i=0;i<courses.getCal().size();i++)
		{
			System.out.println(courses.getCal().get(i));
		}*/
	}
	public void addModule(Statement st) throws IOException, SQLException
	{
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter details to create a module");
		Scanner fc=new Scanner(System.in);
		//String moduleName="";
		
		System.out.println("Enter Module Name");
		String moduleName="";
		while(true)
		{
			try
			{
		ExceptionHandler.containsSpecialCharacters(moduleName=bf.readLine());
			}
			catch(SpecialCharacterException e)
			{
				System.out.println(e.getMessage());
				continue;
			}
			break;
		}
		int instructorid=0;
		while(true)
		{
			int insid=0;
		System.out.println("Enter Instructor Id");
		try
		{
		insid=Integer.parseInt(fc.next());
		}
		catch(Exception e)
		{
			System.out.println("Enter valid number");
			continue;
		}
		instructorid=insid;
		break;
		}
		ResultSet insrs=st.executeQuery("select * from instructor where instructorid="+instructorid+"");
		if(!insrs.isBeforeFirst())
		{
			System.out.println("Instructor Id not Found");
			addModule(st);
		}
		int courseid=0;
		while(true)
		{
		System.out.println("Enter Course Id");
		int cid=0;
		try
		{
		cid=Integer.parseInt(fc.next());
		}
		catch(Exception e)
		{
			e.getMessage();
			continue;
		}
		courseid=cid;
		ResultSet coursers=st.executeQuery("select * from courses where courseid="+courseid+"");
		if(!coursers.isBeforeFirst())
		{
			System.out.println("Course Id not Found");
			addModule(st);
		}
		break;
		}
		Module module=new Module(moduleName,instructorid,courseid);
		
		try {
			st.executeQuery("insert into module values(moduleseq.nextval,'"+module.getModuleName()+"',"+module.getInstructorId()+","+module.getCourseId()+")");
			System.out.println("Module Insertion Successfully Executed");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Invalid input .Enter valid input");
			addModule( st);
		}
	}
	public void viewModules(Statement st) throws SQLException
	{
		ResultSet rs=st.executeQuery("select * from module");
		System.out.println("Module Id"+"  "+"Module Name");
		ArrayList<Module>moduleList=new ArrayList<Module>();
		while(rs.next())
		{
			Module module=new Module(rs.getInt("moduleid"),rs.getString("modulename"),rs.getInt("instructorid"),rs.getInt("courseid"));
			System.out.println(module.getModuleId()+"  "+module.getModuleName()+" "+module.getInstructorId()+" "+module.getCourseId());
			moduleList.add(module);
		}
		Courses courses=new Courses();
		courses.setModuleList(moduleList);
		courses.getModuleList().stream().forEach((x)->System.out.println(x));
	}
	public void editModule(Statement st) throws SQLException, IOException
	{
		System.out.println("Edit\n1.ModuleName\n2.instructorId\n3.courseId\4.Back");
		Scanner fc=new Scanner(System.in);
		int choice=0;
		while(true)
		{
		try
		{
		choice=Integer.parseInt(fc.next());
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
			int moduleId=0;
			while(true)
			{
			System.out.println("Enter module id");
			try
			{
			moduleId=Integer.parseInt(fc.next());
			}
			catch(Exception e)
			{
				System.out.println("Enter valid number");
				continue;
			}
			break;
			}
			fc.nextLine();
			//String moduleName="";
			//boolean flag=true;
			BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter module name");
			String moduleName="";
			while(true)
			{
				moduleName=bf.readLine();
				try
				{
					ExceptionHandler.containsSpecialCharacters(moduleName);
				}
				catch(SpecialCharacterException e)
				{
					System.out.println(e.getMessage());
					continue;
				}
				break;
			}
			ResultSet rs=st.executeQuery("select * from module where moduleid="+moduleId+"");
			if(!rs.isBeforeFirst())
			{
				System.out.println("Module Id not found");
				editModule(st);
			}
			Module module;
			while(rs.next())
			{
				module=new Module(rs.getInt("moduleid"),moduleName,rs.getInt("instructorid"),rs.getInt("courseid"));
				try {
					int rows=st.executeUpdate("update module set modulename='"+module.getModuleName()+"' where moduleid="+module.getModuleId()+"");
					System.out.println("Module Rename Successfully Executed");
					if(rows>0)
					{
						System.out.println(rows+" row affected");
					}
					else
					{
					System.out.println(rows+" rows affected");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Invalid input.Enter valid input");
					editModule(st);
				}
			}
			
			break;
		}
		case 2:
		{
			int instructorId=0;
			while(true)
			{
			System.out.println("Enter Instructor Id");
			try
			{
			instructorId=Integer.parseInt(fc.next());
			}
			catch(Exception e)
			{
				System.out.println("Enter valid number");
				continue;
			}
			break;
			}
			//fc.nextLine();
			int moduleId=0;
			while(true)
			{
			System.out.println("Enter Module Id");
			try
			{
			moduleId=Integer.parseInt(fc.next());
			}
			catch(Exception e)
			{
				System.out.println("Enter valid number");
				continue;
			}
			break;
			}
			ResultSet rs=st.executeQuery("select * from module where moduleid="+moduleId+"");
			if(!rs.isBeforeFirst())
			{
				System.out.println("Module Id not Found");
				editModule(st);
			}
			Module module;
			while(rs.next())
			{
				module=new Module(rs.getInt("moduleid"),rs.getString("modulename"),instructorId,rs.getInt("courseid"));
				try {
					int rows=st.executeUpdate("update module set instructorid='"+module.getInstructorId()+"' where moduleid="+module.getModuleId()+"");
					System.out.println("Module Rename Successfully Executed");
					if(rows>0)
					{
						System.out.println(rows+" row affected");
					}
					else
					{
					System.out.println("Module Id not Found");
					editModule(st);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Instructor Id not Found");
					editModule(st);
				}
			}
			break;
		}
		case 3:
		{
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
				continue;
			}
			break;
			}
			//fc.nextLine();
			int moduleId=0;
			while(true)
			{
			System.out.println("Enter Module Id");
			try
			{
			moduleId=Integer.parseInt(fc.next());
			}
			catch(Exception e)
			{
				System.out.println("Enter valid number");
				continue;
			}
			break;
			}
			ResultSet rs=st.executeQuery("select * from module where moduleid="+moduleId+"");
			if(!rs.isBeforeFirst())
			{
				System.out.println("Module Id not found");
				editModule(st);
			}
			Module module;
			while(rs.next())
			{
				module=new Module(rs.getInt("moduleid"),rs.getString("modulename"),rs.getInt("instructorid"),courseId);
				try {
					int rows=st.executeUpdate("update module set courseid="+courseId+" where moduleid="+module.getModuleId()+"");
					System.out.println("Module course id Renamed Successfully Executed");
					if(rows>0)
					{
						System.out.println(rows+" row affected");
					}
					else
					{
					System.out.println("Course Id not Found");
					editModule(st);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Invalid Input.Enter valid input");
					editModule(st);
				}
			}
			break;
		}
		case 4:
		{
			break;
		}
		default:
		{
			editModule(st);
		}
		}
	}//edit module
	public void deleteModule(Statement st) throws SQLException
	{
		Scanner fc=new Scanner(System.in);
		int moduleId=0;
		while(true)
		{
		System.out.println("Enter Module id to delete");
		try
		{
		moduleId=Integer.parseInt(fc.next());
		}
		catch(Exception e)
		{
			System.out.println("Enter valid number");
			//e.getMessage();
			continue;
		}
		break;
		}
		int choice=0;
		while(true)
		{
		System.out.println("Are you sure you want to delete it?\n 1.Yes \n2.Cancel");
		try
		{
		choice=Integer.parseInt(fc.next());
		}
		catch(Exception e)
		{
			System.out.println("Enter valid number");
			//e.getMessage();
			continue;
		}
		break;
		}
		switch(choice)
		{
		case 1:
		{
			int rows=st.executeUpdate("delete from module where moduleid="+moduleId+"");
			System.out.println(rows+" Module rows Deleted Successfully");
			break;
		}
		case 2:
		{
			break;
		}
		default:
			System.out.println("Enter valid number");
			deleteModule(st);
			break;
		}
	}
	public void viewInstructors(Statement st) throws SQLException
	{
		ResultSet rs=st.executeQuery("select * from instructor");
		while(rs.next())
		{
			Instructor instructorObject=new Instructor(rs.getInt("instructorid"),rs.getString("username"),rs.getString("password"),rs.getString("email"));
			System.out.println("Instructor Id "+instructorObject.getId()+" instructor username "+instructorObject.getUsername()+" instructor Password "+instructorObject.getPassword()+" instructor email "+instructorObject.getEmail());
		}
	}
	public  void addTopic(Statement st) throws SQLException, IOException
	{
		Scanner fc=new Scanner(System.in);
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		//System.out.println("Enter Topic Id");
		//int topicid=fc.nextInt();
		
		System.out.println("Enter Topic Name");
		String topicName="";
		while(true)
		{
			topicName=bf.readLine();
			try
			{
			ExceptionHandler.containsSpecialCharacters(topicName);
			}
			catch(SpecialCharacterException e)
			{
				System.out.println(e.getMessage());
				continue;
			}
			break;
		}
		
		int moduleId=0;
		while(true)
		{
		System.out.println("Enter Module Id");
		try
		{
		moduleId=Integer.parseInt(fc.next());
		}
		catch(Exception e)
		{
			System.out.println("Enter valid number");
			continue;
		}
		break;
		}
		//ResultSet rs=st.executeQuery("select * from module");
		Topic topic=new Topic(topicName,moduleId);
		try
		{
		st.executeQuery("insert into topic values(topicseq.nextval,'"+topic.getTopicName()+"',"+topic.getModuleId()+")");
		}
		catch(Exception e)
		{
			System.out.println("Invalid module Id");
			addTopic(st);
		}
		System.out.println("Topic Added successfully");
	}
	public  void editTopic(Statement st) throws SQLException, IOException
	{
		Scanner fc=new Scanner(System.in);
		int choice=0;
		while(true)
		{
		System.out.println("Enter choices to edit");
		System.out.println("1.Topic Name\n 2.Module Id \n3.Back");
		try
		{
		choice=Integer.parseInt(fc.next());
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
			int topicId=0;
			while(true)
			{
			System.out.println("Enter to topic Id");
			try
			{
			topicId=Integer.parseInt(fc.next());
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				continue;
			}
			break;
			}
			fc.nextLine();
			//String topicName="";
			
			System.out.println("Enter Topic Name To Edit");
			BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
			String topicName="";
			while(true)
			{
				topicName=bf.readLine();
				try
				{
					ExceptionHandler.containsSpecialCharacters(topicName);;
				}
				catch(SpecialCharacterException e)
				{
					System.out.println(e.getMessage());
					continue;
				}
				break;
			}
			Topic topic=new Topic(topicId,topicName);
			try
			{
			int rows=st.executeUpdate("update topic set topicname='"+topic.getTopicName()+"' where topicid="+topic.getTopicId()+"");
			if (rows>1)
			{
				System.out.println(rows+" row have been updated successfully");
			}
			else
			{
				if(rows>0)
				{
			System.out.println(rows+" rows have been updated successfully");
				}
				else
				{
					System.out.println("Topic Id not Found.");
					editTopic(st);
				}
			}
			}
			catch(Exception e)
			{
				System.out.println("Invalid input.Enter Valid Input");
				editTopic(st);
			}
			break;
		}
		case 2:
		{
			int moduleId=0;
			while(true)
			{
			System.out.println("Enter Module Id to edit");
			try
			{
			moduleId=Integer.parseInt(fc.next());
			}
			catch(Exception e)
			{
				System.out.println("Enter valid number");
				continue;
			}
			break;
			}
			int topicId=0;
			while(true)
			{
			System.out.println("Enter Topic Id to Edit");
			try
			{
			topicId=Integer.parseInt(fc.next());
			}
			catch(Exception e)
			{
				System.out.println("Enter valid number");
				continue;
			}
			break;
			}
			Topic topic=new Topic(moduleId,topicId);
			try
			{
			int rows=st.executeUpdate("update topic set moduleid="+topic.getModuleId()+" where topicid="+topic.getTopicId()+"");
			if (rows>0)
			{
				System.out.println(rows+" row have been updated Successfully");
			}
			else
			{
			System.out.println("Invalid input.Topic Id or Module Id does not exist");
			editTopic(st);
			}
			}
			catch(Exception e)
			{
				System.out.println("Invalid input.Enter valid input");
				editTopic(st);
			}
			break;
		}
		case 3:
		{
			break;
		}
		default:
		{
			editTopic(st);
		}
		}
	}
	public  void deleteTopic(Statement st) throws SQLException
	{
		//String topicName="";
		Scanner fc=new Scanner(System.in);
		
		System.out.println("Enter Topic id to delete");
		int topicId=0;
		while(true)
		{
			try
			{
			topicId=Integer.parseInt(fc.next());
			}
			catch(Exception e)
			{
				System.out.println("Enter valid input");
				continue;
			}
			break;
		}
		
		ResultSet rs=st.executeQuery("select * from topic where topicid="+topicId+"");
		/*if(!rs.next())
		{
			deleteTopic(st);
		}*/
		while(rs.next())
		{
			Topic topic=new Topic(topicId,rs.getString("topicName"),rs.getInt("moduleid"));
			try
			{
			int rows=st.executeUpdate("delete from topic where topicid="+topic.getTopicId()+"");
			if(rows>0)
			{
			System.out.println(rows+" row(s) deleted");
			}
			else
			{
				System.out.println("Topic Id not Found");
				deleteTopic(st);
			}
			}
			catch(Exception e)
			{
				System.out.println("Topic Id not Found");
				deleteTopic(st);
			}
		}
	}
	public  void viewTopic(Statement st) throws SQLException
	{
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		ResultSet rs=st.executeQuery("select * from topic");
		ArrayList<Topic>topicList=new ArrayList<Topic>();
		while(rs.next())
		{
			Topic topic=new Topic(rs.getInt("topicid"),rs.getString("topicname"),rs.getInt("moduleid"));
			topicList.add(topic);
			System.out.println("Topic Id : "+topic.getTopicId()+" "+"Topic Name : "+topic.getTopicName()+" Module Id : "+topic.getModuleId());
		}
		Module module = new Module(0, null, 0, 0, null);
		module.setTal(topicList);
	}
	public  void viewContents(Statement st) throws SQLException
	{
		System.out.println("Topic Content");
		//viewTopic(st);
		/*System.out.println("Content Table");
		Content.viewContents(st);*/
		ResultSet rs=st.executeQuery("select * from content");
		while(rs.next())
		{
			System.out.println(" Content Id "+" "+rs.getInt("contentid")+" ; "+"Content Name"+" "+rs.getString("contentName")+" ; "+"Topic Id  "+rs.getInt("topicId"));
		}
				
	}
	public  void addContent(Statement st) throws SQLException, IOException, SpecialCharacterException
	{
		Scanner fc=new Scanner(System.in);
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter Content id (number)");
		/*int contentId=0;
		while(true)
		{
			try
			{
				contentId=Integer.parseInt(fc.next());
			}
			catch(Exception e)
			{
				System.out.println("Enter valid number");
				continue;
			}
			break;
		}*/
		String contentName="";
		while(true)
		{
			try
			{
				System.out.println("Enter Content Name");
			//exceptionHandler.containsSpecialCharacters(contentName=bf.readLine());
				contentName=bf.readLine();
			ExceptionHandler.hasValidExtension(contentName);
			}
			/*catch(specialCharacterException e)
			{
				System.out.println(e.getMessage());
				continue;
			}*/
			catch(InvalidFileException e)
			{
				System.out.println(e.getMessage());
				continue;
			}
			break;
		}
		int topicId=0;
		while(true)
		{
			try
			{
				System.out.println("Enter topic id");
			topicId=bf.read();
			}
			catch(Exception e)
			{
				System.out.println("Enter valid number");
				continue;
			}
			break;
		}
		ResultSet rs1=st.executeQuery("insert into content values(contentseq.nextval,'"+contentName+"',"+topicId+")");
		viewContents(st);;
	}
	public  void editContent(Statement st) throws IOException, SQLException
	{
		System.out.println("Topic Table");
		viewTopic(st);
		System.out.println("Content table");
		viewContents(st);
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("EditContent Name");
		String contentName="";
		while(true)
		{
			contentName=bf.readLine();
			try
			{
				ExceptionHandler.containsSpecialCharacters(contentName);
			}
			catch(SpecialCharacterException e)
			{
				System.out.println(e.getMessage());
				continue;
			}
			break;
		}
		System.out.println("Enter New Content Name");
		String newContentName="";
		while(true)
		{
			newContentName=bf.readLine();
			try
			{
			ExceptionHandler.containsSpecialCharacters(newContentName);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				continue;
			}
			break;
		}
		/*while(true)
		{
			try
			{
			System.out.println("Enter the name of the content you need to change");
			contentName=bf.readLine();
			//exceptionHandler.containsSpecialCharacters(contentName);
			}
			catch(Exception e)
			{
				System.out.println("Enter valid String");
				//System.out.println(e.getMessage());
				continue;
			}
			break;
		}*/
		//newContentName=bf.readLine();
		/*while(true)
		{
			try
			{
			System.out.println("Enter the name of the content you need to change");
			newContentName=bf.readLine();
			//exceptionHandler.containsSpecialCharacters(newContentName);
			}
			catch(Exception e)
			{
				System.out.println("Enter valid String");
				//System.out.println(e.getMessage());
				continue;
			}
			break;
		}*/
		int rows=st.executeUpdate("update content set contentname='"+newContentName+"' where contentName='"+contentName+"'");
		if(rows==0)
		{
			System.out.println("Content Not Found");
			editContent(st);
		}
		System.out.println(rows+" rows updated successfully");
	}
	public  void delete(Statement st) throws IOException, SQLException
	{
		//System.out.println("Topic Table");
		//Topic.viewTopic(st);
		System.out.println("Content Table");
		viewContents(st);
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter Name of the content to delete");
		String contentName="";
		while(true)
		{
			try
			{
				contentName=bf.readLine();
				//exceptionHandler.containsSpecialCharacters(contentName);
			}
			catch(Exception e)
			{
				System.out.println("Enter valid String");
				continue;
			}
			break;
		}
		int rows=st.executeUpdate("delete from content where contentname='"+contentName+"'");
		if(rows==0)
		{
			System.out.println("File Not Found");
			delete(st);
		}
		System.out.println(rows+" rows deleted successfully");
	}
	public void viewAssessment(Statement st) throws SQLException
	{
		ResultSet rs=st.executeQuery("select * from assessment");
		System.out.println(" Assessment Id "+" "+" Assessment Name "+" "+" Topic Id ");
		while(rs.next())
		{
			System.out.println(rs.getInt("assessmentid")+" "+rs.getString("assessmentname")+" "+rs.getInt("topicid"));

}
	}
	public  void addAssessment(Statement st) throws SQLException, IOException
	{
		Scanner fc=new Scanner(System.in);
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		//String aname="";
		
		String aname="";
		while(true)
		{
			System.out.println("Enter Assesment name");
			aname=bf.readLine();
			try
			{
			ExceptionHandler.containsSpecialCharacters(aname);
			}
			catch(SpecialCharacterException e)
			{
				System.out.println(e.getMessage());
				continue;
			}
			break;
		}
		
		int tid=0;
		while(true)
		{
		System.out.println("Enter Topic id");
		try
		{
		tid=Integer.parseInt(fc.next());
		}
		catch(Exception e)
		{
			System.out.println("Enter valid number");
			continue;
		}
		break;
		}
		Assessment assessment=new Assessment(aname,tid);
		try
		{
		st.executeQuery("insert into assessment values(assessmentseq.nextval,'"+assessment.getAssessmentName()+"',"+assessment.getTopicId()+")");
		System.out.println("Assessment inserted successfully");
		}
		catch(Exception e)
		{
			System.out.println("Enter valid Topic id");
			addAssessment(st);
		}
	}
	public  void editAssessment(Statement st) throws SQLException, IOException
	{
		System.out.println("Assessment Table");
		viewAssessment(st);
		System.out.println("Topic Id");
		viewTopic(st);
		Scanner fc=new Scanner(System.in);
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		int choice=0;
		while(true)
		{
		System.out.println("Enter\n 1.Assessment name\n 2.Topic Id\n 3.back");
		try
		{
		choice=Integer.parseInt(fc.next());
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
			int id=0;
			while(true)
			{
			System.out.println("Enter Assessment Id");
			try
			{
			id=Integer.parseInt(fc.next());
			}
			catch(Exception e)
			{
				System.out.println("Enter valid number");
				continue;
			}
			break;
			}
			fc.nextLine();
			System.out.println("Enter Assessment Name");
			String name="";
			while(true)
			{
				name=bf.readLine();
				try
				{
					ExceptionHandler.containsSpecialCharacters(name);
				}
				catch(SpecialCharacterException e)
				{
					System.out.println(e.getMessage());
					continue;
				}
				break;
			}
			ResultSet rs=st.executeQuery("select * from assessment where assessmentid="+id+"");
			if(!rs.isBeforeFirst())
			{
				System.out.println("Assessment id not found");
				editAssessment(st);
			}
			while(rs.next())
			{
				Assessment a=new Assessment(id,name,rs.getInt("topicid"));
				try
				{
				int rows=st.executeUpdate("update assessment set assessmentname='"+a.getAssessmentName()+"' where assessmentid="+a.getAssesmentid()+"");
				if(rows>0)
				{
				System.out.println(rows+" row(s) updated successfully");
				}
				else
				{
					System.out.println("Assessment id not found");
					editAssessment(st);
				}
				}
				catch(Exception e)
				{
					System.out.println("Assessment Id not Found");
					editAssessment(st);
				}
			}
			break;
		}
		case 2:
		{
			int id=0;
			while(true)
			{
			System.out.println("Enter Assessment Id");
			try
			{
			id=Integer.parseInt(fc.next());
			}
			catch(Exception e)
			{
				System.out.println("Enter valid number");
				continue;
			}
			break;
			}
			//fc.nextLine();
			int topicid=0;
			while(true)
			{
			System.out.println("Enter Topic Id");
			try
			{
			topicid=Integer.parseInt(fc.next());
			}
			catch(Exception e)
			{
				System.out.println("Enter valid number");
				continue;
			}
			break;
			}
			ResultSet rs=st.executeQuery("select * from assessment where assessmentid="+id+"");
			if(!rs.isBeforeFirst())
			{
				System.out.println("Assessment Id not found");
				editAssessment(st);
			}
			while(rs.next())
			{
				Assessment a=new Assessment(id,rs.getString("assessmentname"),topicid);
				try
				{
				int rows=st.executeUpdate("update assessment set topicid="+a.getTopicId()+" where assessmentid="+a.getAssesmentid()+"");
				if(rows>0)
				{
				System.out.println(rows+" row(s) updated successfully");
				}
				else
				{
					System.out.println("Topic id not found");
					editAssessment(st);
				}
				}
				catch(Exception e)
				{
					System.out.println("Topic Id not found");
					editAssessment(st);
				}
			}
			break;
		}
		case 3:
		{
			break;
		}
		default:
		{
			System.out.println("Invalid option");
			editAssessment(st);
		}
		}
	}
	public  void deleteAssessment(Statement st) throws SQLException
	{
		Scanner fc=new Scanner(System.in);
		int aid=0;
		while(true)
		{
		System.out.println("Enter Assessment id");
		try
		{
		aid=Integer.parseInt(fc.next());
		}
		catch(Exception e)
		{
			System.out.println("Enter valid number");
			continue;
		}
		break;
		}
		ResultSet rs=st.executeQuery("select * from assessment where assessmentid="+aid+"");
		if(!rs.isBeforeFirst())
		{
			System.out.println("Assessment id not found");
			deleteAssessment(st);
		}
		while(rs.next())
		{
			Assessment a=new Assessment(aid,rs.getString("assessmentname"),rs.getInt("topicid"));
			try
			{
			int rows=st.executeUpdate("delete from assessment where assessmentid="+a.getAssesmentid()+"");
			System.out.println(rows+" row(s) deleted successfully");
			}
			catch(Exception e)
			{
				System.out.println("Invalid Input.Enter valid input");
				deleteAssessment(st);
			}
		}
	}
	public void addQuestion(Statement st) throws SQLException
	{
		Scanner fc=new Scanner(System.in);
		System.out.println("Enter Question");
		String questions=fc.nextLine();
		System.out.println("Enter Option A");
		String optionA=fc.nextLine();
		System.out.println("Enter Option B");
		String optionB=fc.nextLine();
		System.out.println("Enter Option C");
		String optionC=fc.nextLine();
		System.out.println("Enter Option D");
		String optionD=fc.nextLine();
		System.out.println("Enter Answer");
		Character ans=fc.next().charAt(0);
		int assessmentId=0;
		while(true)
		{
		System.out.println("Enter Assessment ID");	
		try
		{
		assessmentId=Integer.parseInt(fc.next());
		}
		catch(Exception e)
		{
			System.out.println("Enter valid number");
			continue;
		}
		break;
		}
		Question question=new Question(questions,optionA,ans,assessmentId,optionB,optionC,optionD);
		st.executeQuery("insert into question values(questionseq.nextval,'"+question.getQuestion()+"','"+question.getOptionsA()+"','"+question.getAnswers()+"',"+question.getAssessmentId()+",'"+question.getOptionB()+"','"+question.getOptionC()+"','"+question.getOptionD()+"')");
		System.out.println("Question Inserted Successfully");
	}
	public void editQuestion(Statement st) throws SQLException, IOException 
	{
		Scanner fc=new Scanner(System.in);
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Edit 1.QUESTION "+"\n 2.OPTION A "+"\n 3. ANSWER "+"\n 4. ASSESSMENT ID "+"\n 5. OPTION B "+"\n 6. OPTION C "+"\n 7.OPTION D ");
		int choices=0;
		while(true)
		{
			try
			{
			choices=Integer.parseInt(fc.next());
			}
			catch(Exception e)
			{
				System.out.println("Enter valid number");
				continue;
			}
			break;
		}
		switch(choices)
		{
		case 1:
		{
			System.out.println("Enter Question id");
			
			int questionId=0;
			while(true)
			{
				try
				{
				questionId=Integer.parseInt(fc.next());
				}
				catch(Exception e)
				{
					System.out.println("Enter valid number");
					continue;
				}
				break;
			}
			fc.nextLine();
			String questions="";
			while(true)
			{
				System.out.println("Enter Question");
				try
				{
					questions=bf.readLine();
					ExceptionHandler.containsSpecialCharacters(questions);
				}
				catch(SpecialCharacterException e)
				{
					System.out.println(e.getMessage());
					continue;
				}
				break;
			}
			ResultSet rs=st.executeQuery("select * from question where questionid="+questionId+"");
			while(rs.next())
			{
				Question question=new Question(questions,rs.getString("optiona"),rs.getString("answer").charAt(0),rs.getInt("assessmentid"),rs.getString("optionb"),rs.getString("optionc"),rs.getString("optiond"));
				int rows=st.executeUpdate("update question set question='"+question.getQuestion()+"' where questionid="+question.getQuestionId()+"");
				System.out.println(rows+" rows updates");
				System.out.println("Question changed successfully");
			}
			break;
		}
		case 2:
		{
			System.out.println("Enter question id");
			int questionId=0;
			while(true)
			{
				try
				{
				questionId=Integer.parseInt(fc.next());
				}
				catch(Exception e)
				{
					System.out.println("Enter valid number");
					continue;
				}
				break;
			}
			fc.nextLine();
			System.out.println("Enter option A");
			String optionA="";
			while(true)
			{
				try
				{
					optionA=bf.readLine();
					ExceptionHandler.containsSpecialCharacters(optionA);
				}
				catch(SpecialCharacterException e)
				{
					System.out.println(e.getMessage());
					continue;
				}
				break;
			}
			ResultSet rs=st.executeQuery("select * from question where questionid="+questionId+"");
			while(rs.next())
			{
				Question q=new Question(rs.getString("question"),optionA,rs.getString("answer").charAt(0),rs.getInt("assessmentid"),rs.getString("optionb"),rs.getString("optionc"),rs.getString("optionc"));
				int rows=st.executeUpdate("update question set optionA='"+q.getOptionsA()+"' where questionid="+questionId+"");
				System.out.println(rows+" rows Updated successfully");
			}
			break;
		}
		case 3:
		{
			System.out.println("Enter question id");
			int questionId=0;
			while(true)
			{
				try
				{
				questionId=Integer.parseInt(fc.next());
				}
				catch(Exception e)
				{
					System.out.println("Enter valid number");
					continue;
				}
				break;
			}
			fc.nextLine();
			System.out.println("Enter Answer");
			Character ans="".charAt(0);
			while(true)
			{
			ans=fc.next().charAt(0);
			try
			{
			ExceptionHandler.containsNumber(ans+"");
			ExceptionHandler.containsSpecialCharacters(ans+"");
			}
			catch(ContainsNumberException e)
			{
				System.out.println(e.getMessage());
				continue;
			}
			catch(SpecialCharacterException e)
			{
				System.out.println(e.getMessage());
				continue;
			}
			break;
			}
			ResultSet rs=st.executeQuery("select * from question where questionid="+questionId+"");
			while(rs.next())
			{
				Question q=new Question(rs.getString("question"),rs.getString("optionA"),ans,rs.getInt("assessmentid"),rs.getString("optionb"),rs.getString("optionc"),rs.getString("optionc"));
				int rows=st.executeUpdate("update question set  answer='"+q.getAnswers()+"' where questionid="+questionId+"");
				System.out.println(rows+" rows Updated successfully");
			}
			break;
		}
		case 4:
		{
			System.out.println("Enter question id");
			int questionId=0;
			while(true)
			{
				try
				{
			questionId=Integer.parseInt(fc.next());
				}
				catch(Exception e)
				{
					System.out.println("Enter valid number");
					continue;
				}
			break;
			}
			fc.nextLine();
			System.out.println("Enter Assessment id");
			int assessmentId=0;
			while(true)
			{
				try
				{
			assessmentId=Integer.parseInt(fc.next());
				}
				catch(Exception e)
				{
					System.out.println("Enter valid number");
					continue;
				}
				break;
			}
			ResultSet rs=st.executeQuery("select * from question where questionid="+questionId+"");
			while(rs.next())
			{
				Question q=new Question(rs.getString("question"),rs.getString("optiona"),rs.getString("answer").charAt(0),assessmentId,rs.getString("optionb"),rs.getString("optionc"),rs.getString("optiond"));
				int rows=st.executeUpdate("update question set  assessmentid='"+q.getAssessmentId()+"' where questionid="+questionId+"");
				System.out.println(rows+" rows Updated successfully");
			}
			break;
		}
		case 5:
		{
			System.out.println("Enter question id");
			int questionId=0;
			while(true)
			{
				try
				{
				questionId=Integer.parseInt(fc.next());
				}
				catch(Exception e)
				{
					System.out.println("Enter valid number");
					continue;
				}
				break;
			}
			fc.nextLine();
			System.out.println("Enter option B");
			String optionB="";//fc.nextLine();
			while(true)
			{
				optionB=bf.readLine();
				try
				{
					ExceptionHandler.containsSpecialCharacters(optionB);
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
					continue;
				}
				break;
			}
			ResultSet rs=st.executeQuery("select * from question where questionid="+questionId+"");
			while(rs.next())
			{
				Question q=new Question(rs.getString("question"),rs.getString("optionA"),rs.getString("answer").charAt(0),rs.getInt("assessmentid"),optionB,rs.getString("optionc"),rs.getString("optiond"));
				int rows=st.executeUpdate("update question set optionB='"+q.getOptionB()+"' where questionid="+questionId+"");
				System.out.println(rows+" rows Updated successfully");
			}
			break;
		}
		case 6:
		{
			System.out.println("Enter question id");
			int questionId=0;
			while(true)
			{
				try
				{
				questionId=Integer.parseInt(fc.next());
				}
				catch(Exception e)
				{
					System.out.println("Enter valid number");
					continue;
				}
				break;
			}
			fc.nextLine();
			System.out.println("Enter option C");
			String optionC="";
			while(true)
			{
				optionC=bf.readLine();
				try
				{
					ExceptionHandler.containsSpecialCharacters(optionC);
				}
				catch(SpecialCharacterException e)
				{
					System.out.println(e.getMessage());
					continue;
				}
				break;
			}
			ResultSet rs=st.executeQuery("select * from question where questionid="+questionId+"");
			while(rs.next())
			{
				Question q=new Question(rs.getString("question"),rs.getString("optionA"),rs.getString("answer").charAt(0),rs.getInt("assessmentid"),rs.getString("optionB"),optionC,rs.getString("optiond"));
				int rows=st.executeUpdate("update question set optionC='"+q.getOptionC()+"' where questionid="+questionId+"");
				System.out.println(rows+" rows Updated successfully");
			}
			break;
		}
		case 7:
		{
			System.out.println("Enter question id");
			int questionId=0;
			while(true)
			{
				try
				{
			questionId=Integer.parseInt(fc.next());
				}
				catch(Exception e)
				{
					System.out.println("Enter valid number");
					continue;
				}
				break;
			}
			fc.nextLine();
			System.out.println("Enter option D");
			String optionD="";
			while(true)
			{
				optionD=bf.readLine();
				try
				{
				ExceptionHandler.containsSpecialCharacters(optionD);
				}
				catch(SpecialCharacterException e)
				{
					System.out.println(e.getMessage());
					continue;
				}
				break;
			}
			ResultSet rs=st.executeQuery("select * from question where questionid="+questionId+"");
			while(rs.next())
			{
				Question q=new Question(rs.getString("question"),rs.getString("optionA"),rs.getString("answer").charAt(0),rs.getInt("assessmentid"),rs.getString("optionB"),rs.getString("optionC"),optionD);
				int rows=st.executeUpdate("update question set optionD='"+q.getOptionD()+"' where questionid="+questionId+"");
				System.out.println(rows+" rows Updated successfully");
			}
			break;
		}
		default :
		{
			System.out.println("Invalid option");
			editQuestion(st);
		}
		}
	}
	public  void deleteQuestion(Statement st) throws SQLException
	{
		Scanner fc=new Scanner(System.in);
		//System.out.println("Enter question id");
		int questionId=0;
		while(true)
		{
		System.out.println("Enter question id");
		try
		{
		questionId=Integer.parseInt(fc.next());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			continue;
		}
		break;
		}
		ResultSet rs=st.executeQuery("select * from question where questionid="+questionId+"");
		while(rs.next())
		{
			Question q=new Question(rs.getString("question"),rs.getString("optionA"),rs.getString("answer").charAt(0),rs.getInt("assessmentid"),rs.getString("optionB"),rs.getString("optionC"),rs.getString("optionD"));
			int rows=st.executeUpdate("delete from question where questionid="+questionId+"");
			System.out.println(rows+" rows Deleted successfully");
		}
	}
	
}
