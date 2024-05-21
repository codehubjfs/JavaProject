package com.courseDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.smartcliff.assessmentTool.DbmsConnection;

public class Question implements Comparable<Question> {

	private int qId;
	private String question;
	private String choice1;
	private String choice2;
	private String choice3;
	private String choice4;
	private String answer;
	private int mark;
	private int aid;

	public Question(String question, String choice1, String choice2, String choice3, String choice4, String answer,
			int mark) {
		// super(cId, cName, aId, aName, duration, totalMarks, aDate, stTime, endTime);
		// this.qId = qId;
		this.question = question;
		this.choice1 = choice1;
		this.choice2 = choice2;
		this.choice3 = choice3;
		this.choice4 = choice4;
		this.answer = answer;
		this.mark = mark;
		// this.aid=aid;
	}

	public Question(int qId, String question, String choice1, String choice2, String choice3, String choice4,
			int mark) {
		this.qId = qId;
		this.question = question;
		this.choice1 = choice1;
		this.choice2 = choice2;
		this.choice3 = choice3;
		this.choice4 = choice4;
		// this.answer = answer;
		this.mark = mark;
		// this.aid=aid;
	}

	public Question(int qId, String question, String choice1, String choice2, String choice3, String choice4,
			String answer, int mark) {
		// super(cId, cName, aId, aName, duration, totalMarks, aDate, stTime, endTime);
		this.qId = qId;
		this.question = question;
		this.choice1 = choice1;
		this.choice2 = choice2;
		this.choice3 = choice3;
		this.choice4 = choice4;
		this.answer = answer;
		this.mark = mark;
		// this.aid=aid;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public int getqId() {
		return qId;
	}

	public void setqId(int qId) {
		this.qId = qId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getChoice1() {
		return choice1;
	}

	public void setChoice1(String choice1) {
		this.choice1 = choice1;
	}

	public String getChoice2() {
		return choice2;
	}

	public void setChoice2(String choice2) {
		this.choice2 = choice2;
	}

	public String getChoice3() {
		return choice3;
	}

	public void setChoice3(String choice3) {
		this.choice3 = choice3;
	}

	public String getChoice4() {
		return choice4;
	}

	public void setChoice4(String choice4) {
		this.choice4 = choice4;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String toString() {
		String lineSeparator = "+--------------+-----------------------------------------------+\n";
		String format = "| %-12s | %-45s |\n";

		StringBuilder sb = new StringBuilder();
		sb.append(lineSeparator);
		sb.append(String.format(format, "Question ID", qId));
		sb.append(lineSeparator);
		sb.append(String.format(format, "Question", question));
		sb.append(lineSeparator);
		sb.append(String.format(format, "Option 1", choice1));
		sb.append(lineSeparator);
		sb.append(String.format(format, "Option 2", choice2));
		sb.append(lineSeparator);
		sb.append(String.format(format, "Option 3", choice3));
		sb.append(lineSeparator);
		sb.append(String.format(format, "Option 4", choice4));
		sb.append(lineSeparator);
		sb.append(String.format(format, "Answer", answer));
		sb.append(lineSeparator);
		sb.append(String.format(format, "Mark", mark));
		sb.append(lineSeparator);
		sb.append("|----------------------------------------------------------------|\n");

		return sb.toString();
	}

	@Override
	public int compareTo(Question o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
