package com.userDetail;

import java.sql.Connection;
//import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.courseDetail.Assessment;
import com.courseDetail.Course;
import com.courseDetail.Question;
import com.exceptionDetails.DateValidator;
//import com.exceptionDetails.EmailException;
import com.exceptionDetails.ForeignKeyNotFoundException;
import com.exceptionDetails.InvalidOptionException;
import com.exceptionDetails.UserNameException;
import com.exceptionDetails.Validate;
import com.smartcliff.assessmentTool.App;
import com.smartcliff.assessmentTool.DbmsConnection;

public class Educator {

	private String email;
	private String password;

	private int eid;
	private String firstName;
	private String lastName;
	private String Dept;
	private String dob;
	private String gender;
	private String city;
	private String country;
	private int courseId;
	private String courseName;

	List<Course> coursesHandled = new ArrayList<>();

	static Scanner sc = new Scanner(System.in);

	public Educator(int eid, String email, String password, String efirstName, String elastName, String egender,
			String ecity, String ecountry) {
		super();
		this.email = email;
		this.password = password;
		this.eid = eid;
		this.firstName = efirstName;
		this.lastName = elastName;
		// this.dob = dob;
		this.gender = egender;
		this.city = ecity;
		this.country = ecountry;
//		this.courseId = courseId;
	}

	public Educator(String email, String password, String efirstName, String elastName, String egender, String ecity,
			String ecountry) {
		this.email = email;
		this.password = password;

		this.firstName = efirstName;
		this.lastName = elastName;
		this.gender = egender;
		this.city = ecity;
		this.country = ecountry;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getlastName() {
		return lastName;
	}

	public void setlastName(String lastName) {
		lastName = lastName;
	}

	public String getgender() {
		return gender;
	}

	public void setgender(String gender) {
		gender = gender;
	}

	public String getcity() {
		return city;
	}

	public void setcity(String city) {
		city = city;
	}

	public String getcountry() {
		return country;
	}

	public void setcountry(String country) {
		country = country;
	}

	public void addAssessment() throws SQLException, InvalidOptionException, UserNameException {

		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("Select * from course c join educatorcourse e on c.cid=e.cid where e.eid=?");
			// System.out.println(this.getEid());
			stmt.setInt(1, this.getEid());
			List<Integer> courseId = new ArrayList<>();
			ResultSet rs = stmt.executeQuery();
			System.out.println("---------------------Available Courses-----------------------");
			while (rs.next()) {
				System.out.println("Course ID  : " + rs.getInt("cid"));
				System.out.println("Course Name: " + rs.getString("cname"));
				courseId.add(rs.getInt("cid"));
			}
			int cid = 0;
			while (true) {
				System.out.println("Enter course ID");
				cid = sc.nextInt();
				if (courseId.contains(cid)) {
					break;
				} else {
					System.out.println("***Please Enter Valid Course ID***");
				}
			}
			System.out.println("Enter Assessment Name");
			sc.nextLine();
			String aName = sc.nextLine();
			while(aName.length()>20) {
				System.out.println("Assessment Name must not exceed 20 Characters");
				System.out.println("Enter Assessment Name");
				aName=sc.nextLine();
			}
			double duration = Validate.getValidDuration();
	        int totalMarks = Validate.getValidTotalMarks();
			boolean flag = true;
			String date = null;

			while (flag) {
				System.out.println("Enter Assessment Date(yyyy-MM-dd)");
				date = sc.next();

				try {
					if (Validate.DateValidater(date)) {
						flag = false;
						LocalDate aDate = LocalDate.parse(date);
						System.out.println("Valid date entered: " + aDate);
					}
				} catch (DateValidator e) {
					System.out.println(e.getMessage());
				}
			}

			    String stTime = Validate.getValidTime("Enter Start Time (HH:MM):");
		        String endTime = Validate.getValidTime("Enter End Time (HH:MM):");

			Assessment assess = new Assessment(cid, aName, duration, totalMarks, date, stTime, endTime);
			PreparedStatement stustmt = DbmsConnection.getConnection().prepareStatement(
					"INSERT INTO Assessment (aid, aname , sttime , endtime ,duration, tot_mark ,cid ,adate,eid) VALUES (assessSeq.nextVal, ?, ?, ?, ?, ?, ?,TO_DATE(?, 'YYYY-MM-DD'),?)");
			stustmt.setString(1, assess.getaName());
			stustmt.setString(2, assess.getStTime());
			stustmt.setString(3, assess.getEndTime());
			stustmt.setDouble(4, assess.getDuration());
			stustmt.setInt(5, assess.getTotalMarks());
			stustmt.setInt(6, assess.getcId());
			stustmt.setString(7, assess.getaDate());
			stustmt.setInt(8, this.getEid()); // stustmt.setString(7, country);
			System.out.println(assess.getcId());

			int rows = stustmt.executeUpdate();
			if (rows == 1) {
				System.out.println("Assessment Created!");
			}
			App.educatorFunctionalities(1, this);
		} catch (InputMismatchException i) {
			System.err.println("Please Enter Only Numbers!");
			addAssessment();
		}
	}

	public void removeAssessment() throws InvalidOptionException, UserNameException {

		int delete = 0;
		while (true) {
			try {
				System.out.println("Enter Assessment ID to remove:");
				delete = Integer.parseInt(sc.next());
				break;
			} catch (NumberFormatException nf) {
				System.err.println("Only Numbers are allowed!");
			}
		}
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("delete from Assessment where aid=?");
			stmt.setInt(1, delete);

			int rows = stmt.executeUpdate();
			if(rows==1) {
			System.out.println("Assessment Deleted");
			App.educatorFunctionalities(1, this);
			}
			else {
				System.out.println("***Assessment Not Found!***");
				App.educatorFunctionalities(1, this);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void viewAssessmentById(int aid) throws InvalidOptionException, UserNameException {
		try {
			String sql1 = "select * from assessment where aid=?";
			PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement(sql1);
			stmt.setInt(1, aid);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				System.out.println("Assessment ID  :" + rs.getInt("aid"));
				System.out.println("Assessment Name:" + rs.getString("aname"));
				System.out.println("Start Time     :" + rs.getString("sttime"));
				System.out.println("End Time       :" + rs.getString("endtime"));
				System.out.println("Duration       :" + rs.getString("duration"));
				System.out.println("Total Marks    :" + rs.getString("tot_mark"));
				System.out.println("Course ID      :" + rs.getString("cid"));
				System.out.println("Assessment Date:" + rs.getString("adate"));
				System.out.println(
						"|---------------------------------------------------------------------------------------------------------------------------------------------------|");
			} else {
				System.err.println("No Assessment found with ID: " + aid);
			}

			App.educatorFunctionalities(1, this);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void viewAssessment() throws InvalidOptionException, UserNameException {
		List<Assessment> assessments = new ArrayList<>();

		try {
			String sql1 = "select * from assessment where eid=? order by aid";
			PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement(sql1);
			stmt.setInt(1, this.getEid());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Assessment assessment = new Assessment(rs.getInt("aid"), rs.getString("aname"), rs.getString("sttime"),
						rs.getString("endtime"), rs.getDouble("duration"), rs.getInt("tot_mark"), rs.getInt("cid"),
						rs.getString("adate"), rs.getInt("eid"));
				assessments.add(assessment);
			}

			// System.out.println(getFirstName());

			if (assessments.isEmpty()) {
				System.out.println("**No Assessments were Posted!**");
				App.educatorFunctionalities(1, this);
			} else {
				assessments.stream().forEach(System.out::println);
				App.educatorFunctionalities(1, this);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editAssessment() throws InvalidOptionException, SQLException, UserNameException {

		try {
			String sql1 = "select * from assessment where eid=? order by aid";
			PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement(sql1);
			stmt.setInt(1, this.getEid());
			ResultSet rs = stmt.executeQuery();
			System.out.println("----------------------Available Assessments-------------------------");
			while (rs.next()) {
				System.out.println("Assessment ID  :" + rs.getInt("aid"));
				System.out.println("Assessment Name:" + rs.getString("aname"));
				System.out.println("Start Time     :" + rs.getString("sttime"));
				System.out.println("End Time       :" + rs.getString("endtime"));
				System.out.println("Duration       :" + rs.getDouble("duration"));
				System.out.println("Tot_mark       :" + rs.getInt("tot_mark"));
				System.out.println("CID            :" + rs.getInt("cid"));
				System.out.println("Adate          :" + rs.getString("adate"));
				System.out.println("Educator ID    :" + rs.getInt("eid"));

				System.out.println("--------------------------------------------------------");
				// assessments.add(assessment);
			}

			// System.out.println(getFirstName());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		int aId = 0;
		while (true) {
			try {
				System.out.println("Select an Assessment ID");
				aId = Integer.parseInt(sc.next());
				break;
			} catch (NumberFormatException nf) {
				System.err.println("Only Numbers are allowed!");
			}
		}
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("select * from assessment where aid=?");
			stmt.setInt(1, aId);
			int rows = stmt.executeUpdate();
			if (rows == 0) {
				new ForeignKeyNotFoundException("Assessment ID doesn't Exist!");
				App.educatorFunctionalities(1, this);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("----------------------EDIT ASSESSMENT------------------------------");
		System.out.println("1.Edit Assessment Name");
		System.out.println("2.Edit Assessment Start Time");
		System.out.println("3.Edit Assessment End Time");
		System.out.println("4.Edit Duration");
		System.out.println("5.Edit Tot_Marks");
		//System.out.println("6.Edit CID");
		System.out.println("6.Edit Assessment Date");
		System.out.println("7.Back");
		System.out.println("8.Exit");
		int opt = sc.nextInt();
		switch (opt) {
		case 1:
			editAName(aId);
			break;
		case 2:
			editStTime(aId);
			break;
		case 3:
			editEndTime(aId);
			break;
		case 4:
			editDuration(aId);
			break;
		case 5:
			editTotMarks(aId);
			break;
//		case 6:
//			editACid(aId);
//			break;
		case 6:
			editADate(aId);
			break;
		case 7:
			App.educatorFunctionalities(1, this);
			break;
		case 8:
			System.out.println("Exit Successfull!");
			System.exit(0);
		default:
			new InvalidOptionException("Please Enter Valid Option");
			editAssessment();
			break;
		}
	}

	public void editAName(int id) throws InvalidOptionException, UserNameException {
		
		String aname="";
		while(true) {

		System.out.println("Enter new Assessment Name");
		
		aname = sc.next();
		if(aname.length()>20) {
			System.out.println("Assessment Name must not exceed 20 characters");
			continue;
		}
		else {
			break;
		}
		}
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update assessment set aname=? where aid=?");
			stmt.setString(1, aname);
			stmt.setInt(2, id);
			int rows = stmt.executeUpdate();
			System.out.println(rows + "Assessment Name Updated");
			App.educatorFunctionalities(1, this);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void editStTime(int id) throws InvalidOptionException, UserNameException {

		//System.out.println("Enter new Start Time(HH:MM))");
		String sttime = Validate.getValidTime("Enter New Start Time (HH:MM):");
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update assessment set sttime=? where aid=?");
			stmt.setString(1, sttime);
			stmt.setInt(2, id);
			int rows = stmt.executeUpdate();
			if(rows==1) {
			System.out.println("Start Time Updated");
			}
			App.educatorFunctionalities(1, this);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void editEndTime(int id) throws InvalidOptionException, UserNameException {

		
		String endTime = Validate.getValidTime("Enter New End Time (HH:MM):");
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update assessment set endtime=? where aid=?");
			stmt.setString(1, endTime);
			stmt.setInt(2, id);
			int rows = stmt.executeUpdate();
			if(rows==1) {
			System.out.println("End Time Updated");
			}
			App.educatorFunctionalities(1, this);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void editDuration(int id) throws InvalidOptionException, UserNameException {

		double duration = Validate.getValidDuration();
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update assessment set duration=? where aid=?");
			stmt.setDouble(1, duration);
			stmt.setInt(2, id);
			int rows = stmt.executeUpdate();
			if(rows==1) {
			System.out.println("Duration Updated");
			}
			App.educatorFunctionalities(1, this);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void editTotMarks(int id) throws InvalidOptionException, UserNameException {

		int totMarks = Validate.getValidTotalMarks();
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update assessment set tot_mark=? where aid=?");
			stmt.setInt(1, totMarks);
			stmt.setInt(2, id);
			int rows = stmt.executeUpdate();
			System.out.println(rows + "Total Mark Updated");
			App.educatorFunctionalities(1, this);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void editACid(int id) throws InvalidOptionException, SQLException, UserNameException {

		System.out.println("Enter new COurse ID");
		String cid = sc.next();
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update assessment set cid=? where aid=?");
			stmt.setString(1, cid);
			stmt.setInt(2, id);
			int rows = stmt.executeUpdate();
			System.out.println(rows + "Course ID Updated");
			App.educatorFunctionalities(1, this);
		} catch (SQLIntegrityConstraintViolationException s) {
			System.err.println("Course ID doesn't exist!");
			App.educatorFunctionalities(1, this);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void editADate(int id) throws InvalidOptionException, UserNameException {

		System.out.println("Enter new Assessment Date(dd-MMM-yyyy)");
		String aDate = sc.next();
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update assessment set adate=? where aid=?");
			stmt.setString(1, aDate);
			stmt.setInt(2, id);
			int rows = stmt.executeUpdate();
			System.out.println(rows + "Assessment Date Updated");
			App.educatorFunctionalities(1, this);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void editQuestions() throws InvalidOptionException, SQLException, UserNameException {

		System.out.println("Enter Question ID");
		int qId = sc.nextInt();

		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("select * from question where qid=?");
			stmt.setInt(1, qId);
			int rows = stmt.executeUpdate();
			if (rows == 0) {
				new ForeignKeyNotFoundException("Question ID doesn't Exist!");
				App.educatorFunctionalities(2, this);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("----------------------EDIT QUESTIONS------------------------------");
		System.out.println("1.Edit Question");
		System.out.println("2.Edit Option 1");
		System.out.println("3.Edit option 2");
		System.out.println("4.Edit option 3");
		System.out.println("5.Edit Option 4");
		System.out.println("6.Edit Answer");
		System.out.println("7.Edit mark");
		System.out.println("8.Edit Assessment ID");
		System.out.println("9.Back");
		System.out.println("10.Exit");

		int opt = sc.nextInt();
		switch (opt) {
		case 1:
			editQuestionName(qId);
			break;
		case 2:
			editOption1(qId);
			break;
		case 3:
			editOption2(qId);
			break;
		case 4:
			editOption3(qId);
			break;
		case 5:
			editOption4(qId);
			break;
		case 6:
			editQuesAns(qId);
			break;
		case 7:
			editQuestionMark(qId);
			break;
		case 8:
			editQuestionAssessmentId(qId);
		case 9:
			editAssessment();
		case 10:
			System.out.println("Exit Successfull!");
			System.exit(0);
		default:
			new InvalidOptionException("Please Enter Valid Option");
			editQuestions();
			break;
		}
	}

	public void editQuestionName(int id) throws InvalidOptionException, UserNameException {

		System.out.println("Enter new Question");
		sc.nextLine();
		String Question = sc.nextLine();
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update question set questions=? where qid=?");
			stmt.setString(1, Question);
			stmt.setInt(2, id);
			int rows = stmt.executeUpdate();
			System.out.println(rows + "Question Updated");
			App.educatorFunctionalities(2, this);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void editOption1(int id) throws InvalidOptionException, UserNameException {

		System.out.println("Enter new Option 1");
		sc.nextLine();
		String option1 = sc.nextLine();
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update question set c1=? where qid=?");
			stmt.setString(1, option1);
			stmt.setInt(2, id);
			int rows = stmt.executeUpdate();
			System.out.println(rows + "Option Updated");
			App.educatorFunctionalities(2, this);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void editOption2(int id) throws InvalidOptionException, UserNameException {

		System.out.println("Enter new Option 2");
		sc.nextLine();
		String option2 = sc.nextLine();
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update question set c2=? where qid=?");
			stmt.setString(1, option2);
			stmt.setInt(2, id);
			int rows = stmt.executeUpdate();
			System.out.println(rows + "Option Updated");
			App.educatorFunctionalities(2, this);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void editOption3(int id) throws InvalidOptionException, UserNameException {

		System.out.println("Enter new Option 3");
		sc.nextLine();
		String option3 = sc.nextLine();
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update question set c3=? where qid=?");
			stmt.setString(1, option3);
			stmt.setInt(2, id);
			int rows = stmt.executeUpdate();
			System.out.println(rows + "Option Updated");
			App.educatorFunctionalities(2, this);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void editOption4(int id) throws InvalidOptionException, UserNameException {

		System.out.println("Enter new Option 4");
		sc.nextLine();
		String option4 = sc.nextLine();
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update question set c4=? where qid=?");
			stmt.setString(1, option4);
			stmt.setInt(2, id);
			int rows = stmt.executeUpdate();
			System.out.println(rows + "Option Updated");
			App.educatorFunctionalities(2, this);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void editQuesAns(int id) throws InvalidOptionException, UserNameException {

		System.out.println("Enter new Answer(A/B/C/D)");
		sc.nextLine();
		String ans = sc.nextLine().toUpperCase();
		if (ans.length() != 1) {
			System.err.println("Answer must be a Single Option(A/B/C/D)");

		}
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update question set answer=? where qid=?");
			stmt.setString(1, ans);
			stmt.setInt(2, id);
			int rows = stmt.executeUpdate();
			System.out.println(rows + "Answer Updated");
			App.educatorFunctionalities(2, this);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void editQuestionMark(int id) throws InvalidOptionException, UserNameException {

		System.out.println("Enter new Mark");
		int mark = sc.nextInt();
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update question set mark=? where qid=?");
			stmt.setInt(1, mark);
			stmt.setInt(2, id);
			int rows = stmt.executeUpdate();
			System.out.println(rows + "Mark Updated");
			App.educatorFunctionalities(2, this);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void editQuestionAssessmentId(int id) throws InvalidOptionException, UserNameException {

		System.out.println("Enter new Assessment ID");
		int cid = sc.nextInt();
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("update question set aid=? where qid=?");
			stmt.setInt(1, cid);
			stmt.setInt(2, id);
			int rows = stmt.executeUpdate();
//			if(rows==0) {
//				new ForeignKeyNotFoundException("");
//				editQuestionAssessmentId(id);
//			}
			if(rows==1) {
			System.out.println("Assessment ID Updated");
			App.educatorFunctionalities(2, this);
			}
			else {
				System.out.println("**Assessment ID Not Found!**");
				App.educatorFunctionalities(2, this);
			}
			
		} catch (SQLIntegrityConstraintViolationException s) {
			System.err.println("Assessment ID doesn't exist!");
			editQuestionAssessmentId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
//To Add Question to question Bank		 

	public void addQuestion() throws SQLException, InvalidOptionException, UserNameException {

		
		System.out.println("Enter Question");
		String Question = sc.nextLine();
		System.out.println("Enter Option A");
		String c1 = sc.nextLine();
		System.out.println("Enter Option B");
		String c2 = sc.nextLine();
		System.out.println("Enter Option C");
		String c3 = sc.nextLine();
		System.out.println("Enter Option D");
		String c4 = sc.nextLine();
		String answer = "";
		while(true) {
		try {
			System.out.println("Enter the corresponding answer option (A/B/C/D or a/b/c/d):");
			answer = sc.next().toUpperCase();

			if (!answer.matches("[a-dA-D]")) {
				throw new InvalidOptionException(
						"Invalid option entered. Please enter only A, B, C, or D (case insensitive).");
			}
			else {
				break;
			}

		} catch (InvalidOptionException e) {
			System.err.println(e.getMessage());
		}
		}
		System.out.println("Enter Mark");
		int mark = sc.nextInt();

		Question q = new Question(Question, c1, c2, c3, c4, answer, mark);
		PreparedStatement stustmt = DbmsConnection.getConnection().prepareStatement(
				"INSERT INTO Question (qid, questions, c1, c2, c3, c4, answer, mark) VALUES (quesSeq.nextVal, ?, ?, ?, ?, ?, ?,?)");
		stustmt.setString(1, q.getQuestion());
		stustmt.setString(2, q.getChoice1());
		stustmt.setString(3, q.getChoice2());
		stustmt.setString(4, q.getChoice3());
		stustmt.setString(5, q.getChoice4());
		stustmt.setString(6, q.getAnswer());
		stustmt.setInt(7, q.getMark());

		int rows = stustmt.executeUpdate();
		if (rows == 1) {
			System.out.println("Question Created!");
		}

		App.educatorFunctionalities(2, this);
	}

	public void removeQuestion() throws InvalidOptionException, UserNameException {

		System.out.println("Enter Question ID to remove:");
		int delete = sc.nextInt();
		try {
			PreparedStatement stmt = DbmsConnection.getConnection()
					.prepareStatement("delete from question where qid=?");
			stmt.setInt(1, delete);

			int rows = stmt.executeUpdate();
			System.out.println(rows + "Question Deleted");
			App.educatorFunctionalities(2, this);
			;
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void viewQuestions() throws InvalidOptionException, UserNameException {
		List<Question> questions = new ArrayList<>();
		Scanner sc = new Scanner(System.in);

		try {

			String sql1 = "select * from question order by qid";
			PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement(sql1);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Question question = new Question(rs.getInt("qid"), rs.getString("questions"), rs.getString("c1"),
						rs.getString("c2"), rs.getString("c3"), rs.getString("c4"), rs.getString("answer"),
						rs.getInt("mark")

				);
				questions.add(question);
			}

			if (questions.isEmpty()) {
				System.err.println("No Questions were Inserted Yet!");
				App.educatorFunctionalities(2, this);
				return;
			}

			// Print questions using streams
			questions.stream().forEach(System.out::println);

			App.educatorFunctionalities(2, this);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getEfirstName() {
		return firstName;
	}

	public void setEfirstName(String efirstName) {
		this.firstName = efirstName;
	}

	public String getelastName() {
		return lastName;
	}

	public void setelastName(String elastName) {
		this.lastName = elastName;
	}

	public String geteDept() {
		return Dept;
	}

	public void seteDept(String eDept) {
		this.Dept = eDept;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getegender() {
		return gender;
	}

	public void setegender(String egender) {
		this.gender = egender;
	}

	public String getecity() {
		return city;
	}

	public void setecity(String ecity) {
		this.city = ecity;
	}

	public String getecountry() {
		return country;
	}

	public void setecountry(String ecountry) {
		this.country = ecountry;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public List<Course> getCoursesHandled() {
		return coursesHandled;
	}

	public void setCoursesHandled(List<Course> coursesHandled) {
		this.coursesHandled = coursesHandled;
	}

	public void postAssessment() {

	}

	public void viewPerformance() throws InvalidOptionException, UserNameException {

		try {
			System.out.println("Enter Assessment ID");
			int aId = sc.nextInt();
			if (checkEducatorAccess(aId)) {
				String sql1 = "select * from studentmark m join student s on m.sid=s.sid where aid=?";
				PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement(sql1);
				stmt.setInt(1, aId);
				// stmt.setInt(2,s.getSid());
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {

					System.out.println("Assessment ID  :" + rs.getInt("aid"));
					System.out.println("Student ID     :" + rs.getInt("sid"));
					System.out.println("Student Name   :" + rs.getString("fname") + " " + rs.getString("lname"));
					System.out.println("Total Marks    :" + rs.getString("tot_mark"));
					System.out.println(
							"|---------------------------------------------------------------------------------------------------------------------------------------------------|");
				}
				int rows = stmt.executeUpdate();
				if (rows == 0) {
					System.out.println("No Responses Yet!");
				}
				App.educatorFunctionalities(2, this);
			} else {
				System.out.println("No such Assessments were Found!");
				App.educatorFunctionalities(2, this);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean checkEducatorAccess(int aId) throws InvalidOptionException, UserNameException {
		try {

			String sql1 = "select * from assessment where eid=?";
			PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement(sql1);
			stmt.setInt(1, this.getEid());
			// stmt.setInt(2,s.getSid());
			// ResultSet rs = stmt.executeQuery();

//			while (rs.next()) {
//
//				System.out.println("Assessment ID  :" + rs.getInt("aid"));
//				System.out.println("Student ID     :" + rs.getInt("sid"));
//				System.out.println("Student Name   :" + rs.getString("fname") +" "+ rs.getString("lname"));
//				System.out.println("Total Marks    :" + rs.getString("tot_mark"));
//				System.out.println(
//						"|---------------------------------------------------------------------------------------------------------------------------------------------------|");
//			}
			int rows = stmt.executeUpdate();
			if (rows != 0) {
				return true;

			} else {
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public String toString() {
		String format = "| %-10d | %-15s | %-15s | %-30s | %-15s | %-6s | %-15s | %-15s |%n";
		String lineSeparator = "+------------+-----------------+-----------------+--------------------------------+-----------------+--------+-----------------+-----------------+%n";

		StringBuilder sb = new StringBuilder();
		sb.append(String.format(format, eid, firstName, lastName, email, password, gender, city, country));
		sb.append(String.format(lineSeparator));

		return sb.toString();
	}

	public void assignQuestion(Assessment a) throws SQLException, InvalidOptionException, UserNameException {

		System.out.println("1.Add New Question");
		System.out.println("2.Assign existing Question");
		System.out.println("3.Back");

		int choice = sc.nextInt();

		switch (choice) {
		case 1:
			sc.nextLine();
			System.out.println("Enter Question");

			String Question = sc.nextLine();
			System.out.println("Enter Option A");
			String c1 = sc.nextLine();
			// sc.nextLine();
			System.out.println("Enter Option B");
			String c2 = sc.nextLine();
			// sc.nextLine();
			System.out.println("Enter Option C");
			String c3 = sc.nextLine();
			// sc.nextLine();
			System.out.println("Enter Option D");
			String c4 = sc.nextLine();
			// sc.nextLine();
			System.out.println("Enter the  corresponding answer option(A/B/C/D)");
			String answer = sc.next().toUpperCase();
			System.out.println("Enter Mark");
			int mark = sc.nextInt();

			Question q = new Question(Question, c1, c2, c3, c4, answer, mark);
			PreparedStatement stustmt = DbmsConnection.getConnection().prepareStatement(
					"INSERT INTO Question (qid, questions, c1, c2, c3, c4, answer, mark) VALUES (quesSeq.nextVal, ?, ?, ?, ?, ?, ?,?)");
			stustmt.setString(1, q.getQuestion());
			stustmt.setString(2, q.getChoice1());
			stustmt.setString(3, q.getChoice2());
			stustmt.setString(4, q.getChoice3());
			stustmt.setString(5, q.getChoice4());
			stustmt.setString(6, q.getAnswer());
			stustmt.setInt(7, q.getMark());
			// stustmt.setInt(8, q.getAid());

			int rows = stustmt.executeUpdate();
			PreparedStatement qidStmt = DbmsConnection.getConnection()
					.prepareStatement("select qid from question where questions=? and answer=?");
			qidStmt.setString(1, q.getQuestion());
			qidStmt.setString(2, q.getAnswer());
			ResultSet rs = qidStmt.executeQuery();
			int qId = 0;
			while (rs.next()) {
				qId = rs.getInt("qId");
			}
			if (rows == 1) {
				PreparedStatement stmt = DbmsConnection.getConnection()
						.prepareStatement("INSERT INTO questionassessment (aid, qid) VALUES (?,?)");
				stmt.setInt(1, a.getaId());
				stmt.setInt(2, qId);
				int rows1 = stmt.executeUpdate();
				if (rows1 == 1) {
					System.out.println("Question Created and Assigned to Assessment " + a.getaId());
					assignQuestion(a);
				}
			}

			break;

		case 2:
			System.out.println("Enter Question ID to Assign");
			int assignQid = 0;
			try {
				assignQid = Integer.parseInt(sc.next());
			} catch (NumberFormatException nf) {
				System.err.println("Only Numbers are allowed!");
				assignQuestion(a);
			}
			Question qObj = checkQuestion(assignQid);
			if (qObj != null) {
				PreparedStatement assignStmt = DbmsConnection.getConnection()
						.prepareStatement("INSERT INTO questionassessment (aid, qid) VALUES (?,?)");
				assignStmt.setInt(1, a.getaId());
				assignStmt.setInt(2, assignQid);
				int assignRows = assignStmt.executeUpdate();

				if (assignRows == 1) {
					System.out.println("Question assigned to Assessment " + a.getaId());
					assignQuestion(a);
				}

			}

		case 3:
			App.educatorFunctionalities(2, this);
		}

	}

	public static Assessment checkAssessment(int aid) {
		String sql = "SELECT * FROM assessment WHERE aid = ?";
		try (Connection connection = DbmsConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, aid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int aId = rs.getInt("aid");
				String aName = rs.getString("aname");
				String stTime = rs.getString("sttime");
				String endTime = rs.getString("endtime");
				double duration = rs.getDouble("duration");
				int totalMarks = rs.getInt("tot_mark");
				int cId = rs.getInt("cid");
				String aDate = rs.getString("adate");
				int eId = rs.getInt("eid");
				Assessment a = new Assessment(aid, aName, stTime, endTime, duration, totalMarks, cId, aDate, eId);
				return a;
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return null;
	}

	public Question checkQuestion(int qid) {
		String sql = "SELECT * FROM question WHERE qid = ?";
		try (Connection connection = DbmsConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, qid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Question q = new Question(rs.getInt("qid"), rs.getString("questions"), rs.getString("c1"),
						rs.getString("c2"), rs.getString("c3"), rs.getString("c4"), rs.getString("answer"),
						rs.getInt("mark")
				// rs.getInt("aid")
				);
				return q;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return null;
	}

	public void viewAssessmentQuestions(Assessment a) throws InvalidOptionException, UserNameException {
		List<Question> questions = new ArrayList<>();
		String assessQuesQuery = "select * from question q join questionassessment a on q.qid=a.qid where a.aid=? order by q.qid";
		try (Connection connection = DbmsConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(assessQuesQuery)) {
			stmt.setInt(1, a.getaId());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Question question = new Question(rs.getInt("qid"), rs.getString("questions"), rs.getString("c1"),
						rs.getString("c2"), rs.getString("c3"), rs.getString("c4"), rs.getString("answer"),
						rs.getInt("mark")

				);
				questions.add(question);
			}

			if (questions.isEmpty()) {
				System.err.println("No Questions were Inserted Yet!");
				App.educatorFunctionalities(2, this);
				return;
			}
			questions.stream().forEach(System.out::println);

			App.educatorFunctionalities(2, this);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
