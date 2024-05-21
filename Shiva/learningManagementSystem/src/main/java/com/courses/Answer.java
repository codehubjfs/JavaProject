package com.courses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.users.Student;

public class Answer {
	private int answerId;
	private int studentId;
	private int questionId;
	private Character studentAsnwer;
	public Answer(int studentId, int questionId, Character studentAsnwer) {
		super();
		this.studentId = studentId;
		this.questionId = questionId;
		this.studentAsnwer = studentAsnwer;
	}
	public Answer(int answerId, int studentId, int questionId, Character studentAsnwer) {
		super();
		this.answerId = answerId;
		this.studentId = studentId;
		this.questionId = questionId;
		this.studentAsnwer = studentAsnwer;
	}
	public int getAnswerId() {
		return answerId;
	}
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public Character getStudentAsnwer() {
		return studentAsnwer;
	}
	public void setStudentAsnwer(Character studentAsnwer) {
		this.studentAsnwer = studentAsnwer;
	}
	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", studentId=" + studentId + ", questionId=" + questionId
				+ ", studentAsnwer=" + studentAsnwer + ", getAnswerId()=" + getAnswerId() + ", getStudentId()="
				+ getStudentId() + ", getQuestionId()=" + getQuestionId() + ", getStudentAsnwer()=" + getStudentAsnwer()
				+ "]";
	}
	public static void writeAnswer(ArrayList<Question>questionList,Student student,Statement st) throws SQLException, IOException
	{
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		Scanner fc=new Scanner(System.in);
		System.out.println("Enter\n1.Attempt Test\n2.End");
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
		int flg=0;
		switch(choice)
		{
		case 1:
		{
			//System.out.println(student.getStudentid()+" "+student.getUsername()+" "+student.getPassword()+" "+student.getEmail()+" "+student.getPassword());
			
			for(int i=0;i<questionList.size();i++)
			{
				System.out.println(questionList.get(i).getQuestionId()+" "+questionList.get(i).getQuestion());
				System.out.println(questionList.get(i).getOptionsA());
				System.out.println(questionList.get(i).getOptionB());
				System.out.println(questionList.get(i).getOptionC());
				System.out.println(questionList.get(i).getOptionD());
				System.out.println("Enter Answer");
				Character ans=(char)bf.read();
				Answer answer=new Answer(student.getStudentid(),questionList.get(i).getQuestionId(),ans);
				ResultSet rs=st.executeQuery("insert into answer values(answerseq.nextval,"+answer.getStudentId()+","+answer.getQuestionId()+",'"+answer.getStudentAsnwer()+"')");
			}
			//flg++;
			break;
		}
		case 2:
		{
			//studentChoices.choices(null, null, st);
			flg++;
			break;
		}
		default :
			System.out.println("Enter valid option");
			writeAnswer(questionList, student, st);
		}
	}
}
