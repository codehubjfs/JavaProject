package com.courses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Question {
	private int questionId;
	private String question;
	private String optionsA;
	private Character answers;
	private int assessmentId;
	private String optionB;
	private String optionC;
	private String optionD;
	public Question(String question, String optionsA, Character answers, int assessmentId, String optionB,
			String optionC, String optionD) {
		super();
		this.question = question;
		this.optionsA = optionsA;
		this.answers = answers;
		this.assessmentId = assessmentId;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
	}
	
	public Question(int questionId, String question, String optionsA, Character answers, int assessmentId,
			String optionB, String optionC, String optionD) {
		super();
		this.questionId = questionId;
		this.question = question;
		this.optionsA = optionsA;
		this.answers = answers;
		this.assessmentId = assessmentId;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
	}






	@Override
	public String toString() {
		return "Question [(question=" + question + "), (optionsA=" + optionsA + ")(, optionB=" + optionB + "),( optionC="
				+ optionC + ")(, optionD=" + optionD + ")(, answers=" + answers + ")(, assessmentId=" + assessmentId
				+ ")(, getQuestionId()=" + getQuestionId() + ")(, getQuestion()=" + getQuestion() + ")(, getOptionsA()="
				+ getOptionsA() + ", getOptionB()=" + getOptionB() + ", getOptionC()=" + getOptionC()
				+ ", getOptionD()=" + getOptionD() + ", getAnswers()=" + getAnswers() + ", getAssessmentId()="
				+ getAssessmentId() + "]";
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOptionsA() {
		return optionsA;
	}

	public void setOptionsA(String optionsA) {
		this.optionsA = optionsA;
	}

	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}

	public Character getAnswers() {
		return answers;
	}

	public void setAnswers(Character answers) {
		this.answers = answers;
	}

	public int getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(int assessmentId) {
		this.assessmentId = assessmentId;
	}

}
