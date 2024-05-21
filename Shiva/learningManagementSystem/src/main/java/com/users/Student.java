package com.users;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.courses.Answer;
import com.courses.Assessment;
import com.courses.Courses;
import com.courses.Module;
import com.courses.Question;
import com.courses.Topic;
import com.exceptions.ExceptionHandler;
import com.exceptions.SpecialCharacterException;
public class Student {
	private int studentid;
	private String name;
	private String username;
	private String password;
	private String email;
	private String major;
	private int courseId;
	public Student() {
	}
	public Student(int studentid, String name, String username, String password, String email, String major,
			int courseId) {
		super();
		this.studentid = studentid;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.major = major;
		this.courseId = courseId;
	}
	public Student(String name, String email, String major) {
		super();
		this.name = name;
		this.email = email;
		this.major = major;
	}
	public Student(int studentid, String name, String username, String password, String email, String major) {
		super();
		this.studentid = studentid;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.major = major;
	}
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	@Override
	public String toString() {
		return "Student [studentid=" + studentid + ", name=" + name + ", username=" + username + ", password="
				+ password + ", email=" + email + ", major=" + major + ", getStudentid()=" + getStudentid()
				+ ", getName()=" + getName() + ", getUsername()=" + getUsername() + ", getPassword()=" + getPassword()
				+ ", getEmail()=" + getEmail() + ", getMajor()=" + getMajor() + "]";
	}
	public static  void viewStudents(Statement st) throws SQLException
	{
		ResultSet rs=st.executeQuery("select * from student");
		while(rs.next())
		{
			System.out.println("Student Id "+rs.getInt("student_id")+" Student Name "+rs.getString("name")+" Student DOB "+rs.getString("DOB")+" Grade "+rs.getInt("grade")+" username "+rs.getString("username")+" password "+rs.getString("password")+" email "+rs.getString("email")+" ");
		}
	}
	public static void choices(String username,String password,Statement st) throws SQLException, IOException
	{
		Student student1=new Student();
		Scanner sc=new Scanner(System.in);
		while(true)
		{
			int flg=0;
		System.out.println("Enter\n1.Courses\n2.Log Out");
		int choices=0;
		while(true)
		{
			try
			{
		choices=Integer.parseInt(sc.next());
		ExceptionHandler.containsSpecialCharacters(choices+"");
			}
			catch(SpecialCharacterException e)
			{
				System.out.println(e.getMessage());
				continue;
			}
			catch(Exception e)
			{
				System.out.println("Enter Valid Number");
				continue;
			}
			break;
		}
		switch(choices)
		{
		case 1:
		{
			ResultSet rs1=st.executeQuery("select * from student where username='"+username+"' and password='"+password+"'");
			System.out.println("rs");
			while(rs1.next())
			{
				//System.out.println(rs1.getString("username")+" "+rs1.getString("password")+" "+rs1.getString("name"));
				Student student=new Student(rs1.getInt("STUDENT_ID"),rs1.getString("NAME"),rs1.getString("USERNAME"),rs1.getString("PASSWORD"),rs1.getString("EMAIL"),rs1.getString("MAJOR"));
				System.out.println("Student Logged in sucessfully");
				//Student student=new Student();
				Courselist(student,st);
			}
		break;
		}
		case 2:
		{
			flg++;
			break;
		}
		default:
		{
			System.out.println("Enter valid choice");
			choices(username, password, st);
		}
		
		}
		if(flg>1)
		{
			break;
		}
		break;
		}//make it a method
	}
	public static void Courselist(Student student,Statement st) throws SQLException, IOException
	{
		int flg=0;
		ResultSet rs=st.executeQuery("select * from courses");
		while(rs.next())
		{
			Courses courses=new Courses(rs.getInt("courseid"),rs.getString("coursename"),rs.getInt("instructorid"),rs.getDate("startdate").toLocalDate(),rs.getDate("enddate").toLocalDate());
			System.out.println(courses.getCourseID()+" "+courses.getCourseName());
		}
		Scanner fc=new Scanner(System.in);
		System.out.println("Enter Course id");
		int courseid=0;
		while(true)
		{
			try
			{
			courseid=Integer.parseInt(fc.next());
			ExceptionHandler.containsSpecialCharacters(courseid+"");
			}
			catch(SpecialCharacterException e)
			{
				System.out.println(e.getMessage());
				continue;
			}
			catch(Exception e)
			{
				System.out.println("Enter Valid Number");
				continue;
			}
			break;
		}
		ResultSet rs1=st.executeQuery("select * from courses");
		System.out.println("ResultSet");
		while(rs1.next())
		{
			if(courseid==(rs1.getInt("courseid")))
			{
				flg++;
				Courses courses=new Courses(rs1.getInt("courseid"),rs1.getString("coursename"),rs1.getInt("instructorid"),rs1.getDate("startdate").toLocalDate(),rs1.getDate("enddate").toLocalDate());
				System.out.println(courses);
				Student student1=new Student();
				student1.moduleList(student,courses,st);
			}
		}
		if(flg==0)
		{
			System.out.println("Invalid option");
			System.out.println(" ");
			Courselist(student,st);
		}
	}
	public  void moduleList(Student student,Courses course,Statement st) throws SQLException, IOException
	{
		int flg=0;
		System.out.println("Module lists");
		Scanner fc=new Scanner(System.in);
		int courseId=course.getCourseID();
		ResultSet rs1=st.executeQuery("select * from module where courseid="+courseId+"");
		System.out.println("Modules :");
		while(rs1.next())
		{
			Module module=new Module(rs1.getInt("moduleid"),rs1.getString("modulename"),rs1.getInt("instructorid"),rs1.getInt("courseid"));
			System.out.println(+module.getModuleId()+" "+module.getModuleName());
		}
		System.out.println(" ");
		int moduleId=0;
		while(true)
		{
			System.out.println("Enter Module Id");
			try
			{
		moduleId=Integer.parseInt(fc.next());
		ExceptionHandler.containsSpecialCharacters(moduleId+"");
			}
			catch(SpecialCharacterException e)
			{
				System.out.println(e.getMessage());
				continue;
			}
			catch(Exception e)
			{
				System.out.println("Enter Valid Number");
				continue;
			}
			break;
		}
		ResultSet rs2=st.executeQuery("select * from module where courseid="+courseId+"");
		while(rs2.next())
		{
			if(moduleId==(rs2.getInt("moduleId")))
			{
				flg++;
				Module module=new Module(rs2.getInt("moduleid"),rs2.getString("modulename"),rs2.getInt("instructorid"),rs2.getInt("courseid"));
				topicList(student,module,st);
			}
		}
		if(flg==0)
		{
			System.out.println("Invalid Module name");
			moduleList(student,course,st);
		}
	}
	public void topicList(Student student,Module module,Statement st) throws SQLException, IOException
	{
		int flg=0;
		Scanner fc=new Scanner(System.in);
		ResultSet rs=st.executeQuery("select * from topic where moduleid="+module.getModuleId()+"");
		while(rs.next())
		{
			Topic topic=new Topic(rs.getInt("topicid"),rs.getString("topicname"),rs.getInt("moduleid"));
			System.out.println(topic.getTopicId()+"   "+topic.getTopicName());
		}
		int topicId=0;
		while(true)
		{
			System.out.println("Enter Topic");
			try
			{
		topicId=Integer.parseInt(fc.next());
		ExceptionHandler.containsSpecialCharacters(topicId+"");
			}
			catch(SpecialCharacterException e)
			{
				System.out.println(e.getMessage());
				continue;
			}
			catch(Exception e)
			{
				System.out.println("Enter valid number");
				continue;
			}
			break;
		}
		ResultSet rs1=st.executeQuery("select * from topic where moduleid="+module.getModuleId()+"");
		while(rs1.next())
		{
			if(topicId==rs1.getInt("topicId"))
			{
				flg++;
				Topic topics=new Topic(rs1.getInt("topicid"),rs1.getString("topicname"),rs1.getInt("moduleid"));
				AssessmentList(student,topics,st);
			}
		}
		if(flg==0)
		{
			System.out.println("Invalid Topic List");
			topicList(student,module,st);
		}
	}
	public void AssessmentList(Student student,Topic topic,Statement st) throws SQLException, IOException
	{
		int flg=0;
		Scanner fc=new Scanner(System.in);
		ResultSet rs=st.executeQuery("select * from assessment where topicid="+topic.getTopicId()+"");
		System.out.println("Assessment Id"+"   "+"Assessments Name");
		while(rs.next())
		{
			System.out.println(rs.getInt("assessmentid")+"  "+rs.getString("assessmentname"));
		}
		int choice=0;
		while(true)
		{
		System.out.println("Enter 1.Attempt Test 2.View Score");
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
			Scanner sc=new Scanner(System.in);
		//ResultSet rs3=st.executeQuery("select * from answer where studentid="+student.getStudentid()+"");
		/*while(rs3.next())
		{
			System.out.println("Already attempted the test");
			studentChoices.Courselist(student, st);
		}*/
		int aId=0;
		while(true)
		{
			System.out.println("Enter assessment Id");
		try
		{
		aId=Integer.parseInt(sc.next());
		ExceptionHandler.containsSpecialCharacters(aId+"");
		}
		catch(SpecialCharacterException e)
		{
			System.out.println(e.getMessage());
			continue;
		}
		catch(Exception e)
		{
			System.out.println("Enter valid number");
			continue;
		}
		break;
		}
		ResultSet rs1=st.executeQuery("select * from assessment");
		//System.out.println("Enter assessment name");
		while(rs1.next())
		{
			if(rs1.getInt("assessmentid")==(aId))
			{
				Assessment a=new Assessment(rs1.getInt("assessmentid"),rs1.getString("assessmentname"),rs1.getInt("topicid"));
				flg++;
				
				questionsList(student,a,st);
			}
		}
		if(flg==0)
		{
			System.out.println("Invalid assessment name");
			AssessmentList(student,topic,st);
		}
		break;
		}
		case 2:
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter assessment Id");
			int aId=0;
			while(true)
			{
			try
			{
			aId=Integer.parseInt(sc.next());
			}
			catch(Exception e)
			{
				System.out.println("Enter valid number");
				continue;
			}
			break;
			}
			int score=0;
			int aid=0;
			ResultSet rs2=st.executeQuery("select * from assessment where assessmentid="+aId+"");
			while(rs2.next())
			{
				aid=rs2.getInt("assessmentid");
			}
			ResultSet rs1=st.executeQuery("SELECT a.ANSWERID, a.STUDENTID, a.QUESTIONID, a.STUDENTANSWER FROM answer a JOIN question q ON a.QUESTIONID = q.QUESTIONID WHERE a.STUDENTANSWER = q.ANSWER and q.assessmentid="+aid+"");
			while(rs1.next())
			{
				score++;
			}
			//System.out.println("Your Score is "+score);
			System.out.println("Your score is "+score);
			choices(student.getUsername(),student.getPassword(),st);
		}
		default:
		{
			System.out.println("Enter valid choice");
			AssessmentList(student, topic, st);
			break;
		}
		}
	}
	public  void questionsList(Student student,Assessment a,Statement st) throws SQLException, IOException
	{
		
		int flg=0;
		ResultSet rs=st.executeQuery("select * from question where ASSESSMENTID="+a.getAssesmentid()+"");
		System.out.println("1.Attempt Test 2.Cancel");
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
			/*int flg1=0;
			ResultSet rs2=st.executeQuery("select * from answer");
			while(rs2.next())
			{
				if(rs2.getInt("studentid")==student.getStudentid()&&rs2.getInt("assessmentid")==a.getAssesmentid())
				{
					flg1++;
					break;
				}
			}
			if(flg1>0)
			{
				System.out.println("You have already attempted the test");
				choices(student.getUsername(), student.getPassword(), st);
				break;
			}*/
			BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
			ArrayList<Question>questionList=new ArrayList<Question>();
		while(rs.next())
		{
			System.out.println(rs.getInt("questionid")+" "+rs.getString("question"));
			Question questions=new Question(rs.getInt("questionid"),rs.getString("question"),rs.getString("optiona"),rs.getString("answer").charAt(0),rs.getInt("assessmentid"),rs.getString("optionb"),rs.getString("optionc"),rs.getString("optiond"));
			/*System.out.println("A "+questions.getOptionsA());
			System.out.println("B "+questions.getOptionB());
			System.out.println("C "+questions.getOptionC());
			System.out.println("D "+questions.getOptionD());*/
			questionList.add(questions);
			//Answer.writeAnswer(questionList,student, st);
		}
		for(int i=0;i<questionList.size();i++)
		{
		System.out.println("Question Id "+questionList.get(i).getQuestionId()+" "+"Question Name "+questionList.get(i).getQuestion());
		System.out.println("A "+questionList.get(i).getOptionsA());
		System.out.println("B "+questionList.get(i).getOptionB());
		System.out.println("C "+questionList.get(i).getOptionC());
		System.out.println("D "+questionList.get(i).getOptionD());
		System.out.println("Enter answer");
		Character ans=(char)bf.read();
		Answer answer=new Answer(student.getStudentid(),questionList.get(i).getQuestionId(),ans);
		//if(student.getStudentid()==answer.ge)
		
		ResultSet rs1=st.executeQuery("insert into answer values(answerseq.nextval,"+answer.getStudentId()+","+answer.getQuestionId()+",'"+answer.getStudentAsnwer()+"',"+a.getAssesmentid()+")");
		}
		choices(student.getUsername(),student.getPassword(),st);
		break;
		}
		case 2:
		{
			//choices(null, null, st)
			//AssessmentList(student, null, st);
			choices(student.getUsername(),student.getPassword(),st);
			break;
		}
		}
	}
}
