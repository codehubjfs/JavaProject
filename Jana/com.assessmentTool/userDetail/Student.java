package com.userDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.courseDetail.Assessment;
import com.courseDetail.Course;
import com.courseDetail.Question;
import com.exceptionDetails.EmailException;
import com.exceptionDetails.InvalidOptionException;
import com.exceptionDetails.UserNameException;
import com.exceptionDetails.Validate;
import com.smartcliff.assessmentTool.App;
import com.smartcliff.assessmentTool.DbmsConnection;

public class Student {

	private String email;
	private String password;
	private int sid;
	private String sfirstName;
	private String slastName;
	private String dob;
	private String sGender;
	private String sCity;
	private String sCountry;
	private int courseId;

	static Scanner sc = new Scanner(System.in);
	List<Course> registerdCourses = new ArrayList<>();

	public Student(int sid, String email, String password, String sfirstName, String slastName, String sGender,
			String sCity, String sCountry) {
		super();
		this.email = email;
		this.password = password;
		this.sfirstName = sfirstName;
		this.slastName = slastName;
		this.sGender = sGender;
		this.sCity = sCity;
		this.sCountry = sCountry;
		// this.courseId = courseId;
		this.sid = sid;
	}

	public Student(String email, String password, String sfirstName, String slastName, String sGender, String sCity,
			String sCountry) {
		this.email = email;
		this.password = password;
		this.sfirstName = sfirstName;
		this.slastName = slastName;
		this.sGender = sGender;
		this.sCity = sCity;
		this.sCountry = sCountry;
	}

	public static void takeAssessment(Assessment a, Student s)
			throws InvalidOptionException, SQLException, UserNameException {

		String checkQuery = "SELECT * FROM studentmark WHERE aid=? AND sid=?";

		try (Connection connection = DbmsConnection.getConnection();
				PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {

			checkStmt.setInt(1, a.getaId());
			checkStmt.setInt(2, s.getSid());
			ResultSet rs1 = checkStmt.executeQuery();

			if (rs1.next()) {
				System.err.println("Assessment already taken!");
				App.studentFunctionalities(s);
				return;
			}

			String sql1 = "SELECT * FROM question q join questionassessment a on q.qid=a.qid WHERE aid=? ORDER BY q.qid";
			try (PreparedStatement stmt = connection.prepareStatement(sql1)) {
				stmt.setInt(1, a.getaId());
				ResultSet rs = stmt.executeQuery();

				List<Question> questions = new ArrayList<>();
				while (rs.next()) {
					int qid = rs.getInt("qid");
					String questionText = rs.getString("questions");
					String optionA = rs.getString("c1");
					String optionB = rs.getString("c2");
					String optionC = rs.getString("c3");
					String optionD = rs.getString("c4");
					int mark = rs.getInt("mark");

					questions.add(new Question(qid, questionText, optionA, optionB, optionC, optionD, mark));
				}

				for (Question question : questions) {
					System.out.println("Question ID: " + question.getqId());
					System.out.println("Question   : " + question.getQuestion());
					System.out.println("Option A   : " + question.getChoice1());
					System.out.println("Option B   : " + question.getChoice2());
					System.out.println("Option C   : " + question.getChoice3());
					System.out.println("Option D   : " + question.getChoice4());
					System.out.println("Mark       : " + question.getMark());
					System.out.println("Please give your answer (A/B/C/D)");
					String answer = "";
					boolean flag = true;

					while (flag) {

						System.out.println("Enter the corresponding answer option (A/B/C/D or a/b/c/d):");
						answer = sc.next().toUpperCase();
						try {
							Validate.validateAnswer(answer);
							flag = false;
						} catch (InvalidOptionException e) {

							System.out.println(e.getMessage());

						}
					}

					String insertAnswerSql = "INSERT INTO StudentAnswer (ansid, sid, qid, studanswer) VALUES (ansseq.nextval, ?, ?, ?)";
					try (PreparedStatement insertAnswerStmt = connection.prepareStatement(insertAnswerSql)) {
						insertAnswerStmt.setInt(1, s.getSid());
						insertAnswerStmt.setInt(2, question.getqId());
						insertAnswerStmt.setString(3, answer);
						int rows = insertAnswerStmt.executeUpdate();
						if (rows == 1) {
							System.out.println("Answer saved!");
						}
					}
				}

				calculateAndStoreTotalMarks(s.getSid(), a.getaId());
				App.studentFunctionalities(s);
			}
		}
	}

//	public static void takeAssessment(int aId,Student s) throws InvalidOptionException, SQLException, UserNameException {
//		
//		String checkQuery = "select * from studentmark where aid=? and sid=?";
//		PreparedStatement checkstmt = DbmsConnection.getConnection().prepareStatement(checkQuery);
//		checkstmt.setInt(1, aId);
//		checkstmt.setInt(2, s.getSid());
//		ResultSet rs1 = checkstmt.executeQuery();
//		
//		int count=0;
//		while(rs1.next()) {
//			System.err.println("Assessment already Taken!");
//			App.studentFunctionalities(s);
//		}
//		
//		
//		
//		try {
//			String sql1 = "select * from question where aid=? order by qid";
//			PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement(sql1);
//			stmt.setInt(1, aId);
//			ResultSet rs = stmt.executeQuery();
//			
//			
//
//			while (rs.next()) {
//				int qid = rs.getInt("qid");
//				
//
//				System.out.println("Question ID  :" + qid);
//				System.out.println("Question:" + rs.getString("questions"));
//				System.out.println("Option A:" + rs.getString("c1"));
//				System.out.println("Option B:" + rs.getString("c2"));
//				System.out.println("Option C:" + rs.getString("c3"));
//				System.out.println("Option D:" + rs.getString("c4"));
//				System.out.println("Mark    :" + rs.getString("mark"));
//				
//				System.out.println("Please Give Your Answer(A/B/C/D)");
//				String ans = sc.next().toUpperCase();
//				String insertAnswerSql = "INSERT INTO StudentAnswer (ansid, sid, qid, studanswer) VALUES (ansseq.nextval, ?, ?, ?)";
//	            PreparedStatement insertAnswerStmt = DbmsConnection.getConnection().prepareStatement(insertAnswerSql);
//	            insertAnswerStmt.setInt(1, s.getSid());
//	            insertAnswerStmt.setInt(2, qid);
//	            insertAnswerStmt.setString(3, ans);
//	            int rows=insertAnswerStmt.executeUpdate();
//	            if(rows==1) {
//	            	System.out.println("Answer Saved!");
//	            }
//				System.out.println(
//						"|---------------------------------------------------------------------------------------------------------------------------------------------------|");
//			}
//			
//			calculateAndStoreTotalMarks(s.getSid(),aId);
//			
//			App.studentFunctionalities(s);
//			 
//			// System.out.println(rows + " selected");
//			//app.educatorFunctionalities();
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public static void calculateAndStoreTotalMarks(int studentId, int assessmentId) throws SQLException {

		String retrieveQuestionsQuery = "SELECT * FROM question q join questionassessment a on q.qid=a.qid WHERE a.aid=? order by q.qid";

		String retrieveStudentAnswersQuery = "SELECT QID, studAnswer " + "FROM StudentAnswer " + "WHERE SID = ? "
				+ "AND QID IN ( " + "    SELECT q.QID " + "    FROM Question q "
				+ "    JOIN questionassessment a ON q.QID = a.QID " + "    WHERE a.AID = ? " + ") " + "ORDER BY QID";

		int totalMarks = calculateTotalMarks(retrieveQuestionsQuery, retrieveStudentAnswersQuery, studentId,
				assessmentId);

		storeTotalMarks(studentId, assessmentId, totalMarks);
	}

	private static int calculateTotalMarks(String retrieveQuestionsQuery, String retrieveStudentAnswersQuery,
			int studentId, int assessmentId) {
		int totalMarks = 0;

		try (Connection connection = DbmsConnection.getConnection();
				PreparedStatement retrieveQuestionsStmt = connection.prepareStatement(retrieveQuestionsQuery);
				PreparedStatement retrieveStudentAnswersStmt = connection
						.prepareStatement(retrieveStudentAnswersQuery)) {

			retrieveQuestionsStmt.setInt(1, assessmentId);

			ResultSet questionsResult = retrieveQuestionsStmt.executeQuery();

			retrieveStudentAnswersStmt.setInt(1, studentId);
			retrieveStudentAnswersStmt.setInt(2, assessmentId);

			ResultSet studentAnswersResult = retrieveStudentAnswersStmt.executeQuery();

			while (questionsResult.next() && studentAnswersResult.next()) {
				String correctAnswer = questionsResult.getString("Answer");
				String studentAnswer = studentAnswersResult.getString("studAnswer");
				System.out.println();
				if (correctAnswer != null && correctAnswer.equals(studentAnswer)) {
					totalMarks += questionsResult.getInt("Mark");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return totalMarks;
	}

	public static void storeTotalMarks(int studentId, int assessmentId, int totalMarks) throws SQLException {

		String insertMarkQuery = "INSERT INTO Studentmark (sid, aid, tot_mark) VALUES (?, ?, ?)";

		PreparedStatement insertMarkStmt = DbmsConnection.getConnection().prepareStatement(insertMarkQuery);
		insertMarkStmt.setInt(1, studentId);
		insertMarkStmt.setInt(2, assessmentId);
		insertMarkStmt.setInt(3, totalMarks);

		int rows = insertMarkStmt.executeUpdate();
		if (rows == 1) {
			System.out.println("Marks Evaluated!");
		}

	}

	public static void ViewMarks(Student s, Assessment a) throws InvalidOptionException, UserNameException {
		try {

			String sql1 = "select * from studentmark where aid=? and sid=?";
			PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement(sql1);
			stmt.setInt(1, a.getaId());
			stmt.setInt(2, s.getSid());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				System.out.println("Assessment ID  :" + rs.getInt("aid"));
				System.out.println("Total Marks    :" + rs.getString("tot_mark"));
				System.out.println(
						"|---------------------------------------------------------------------------------------------------------------------------------------------------|");
			}
			int rows = stmt.executeUpdate();
			if (rows == 0) {
				System.out.println("Assessment Not yet Taken!");
			}
			App.studentFunctionalities(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getSfirstName() {
		return sfirstName;
	}

	public void setSfirstName(String sfirstName) {
		this.sfirstName = sfirstName;
	}

	public String getSlastName() {
		return slastName;
	}

	public void setSlastName(String slastName) {
		this.slastName = slastName;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
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

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getsGender() {
		return sGender;
	}

	public void setsGender(String sGender) {
		this.sGender = sGender;
	}

	public String getsCity() {
		return sCity;
	}

	public void setsCity(String sCity) {
		this.sCity = sCity;
	}

	public String getsCountry() {
		return sCountry;
	}

	public void setsCountry(String sCountry) {
		this.sCountry = sCountry;
	}

	public String toString() {
		String format = "| %-10s | %-15s | %-15s | %-30s | %-15s | %-10s | %-15s | %-15s |\n";
		String lineSeparator = "+------------+-----------------+-----------------+--------------------------------+-----------------+------------+-----------------+-----------------+";

		StringBuilder sb = new StringBuilder();
		sb.append(String.format(format, sid, sfirstName, slastName, email, password, sGender, sCity, sCountry));
		sb.append(lineSeparator);

		return sb.toString();
	}

	public static void showAssessmentDetails(Student s) throws InvalidOptionException, UserNameException {
		try {
			String sql1 = "select * from assessment";
			PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();
			// rs.getDate("adate");

			while (rs.next()) {

				System.out.println("Assessment ID  :" + rs.getInt("aid"));
				System.out.println("Assessment Name:" + rs.getString("aname"));
				System.out.println("Start Time     :" + rs.getString("sttime"));
				System.out.println("EndTime        :" + rs.getString("endtime"));
				System.out.println("Duration       :" + rs.getString("duration"));
				System.out.println("Tot_mark       :" + rs.getString("Tot_mark"));
				System.out.println("CID            :" + rs.getString("cid"));
				System.out.println("Adate          :" + rs.getString("adate"));
				System.out.println(
						"|---------------------------------------------------------------------------------------------------------------------------------------------------|");
			}
			int rows = stmt.executeUpdate();
			if (rows == 0) {
				System.err.println("No Assessments were Posted!");
				App.studentFunctionalities(s);
			}
			System.out.println(rows + "Records found!");
			App.studentFunctionalities(s);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
