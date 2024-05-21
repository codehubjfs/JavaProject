package com.courses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Assessment {
	private int assesmentid;
	private String assessmentName;
	private int topicId;
	private ArrayList<Question> qList=new ArrayList<Question>();
	public Assessment(String assessmentName, int topicId) {
		super();
		this.assessmentName = assessmentName;
		this.topicId = topicId;
	}
	public Assessment(int assesmentid, String assessmentName, int topicId) {
		super();
		this.assesmentid = assesmentid;
		this.assessmentName = assessmentName;
		this.topicId = topicId;
	}
	
	public ArrayList<Question> getQList() {
		return qList;
	}
	public void setQList(ArrayList<Question> qList) {
		qList = qList;
	}
	public void addqList(Question question)
	{
		qList.add(question);
	}
	public int getAssesmentid() {
		return assesmentid;
	}
	public void setAssesmentid(int assesmentid) {
		this.assesmentid = assesmentid;
	}
	public String getAssessmentName() {
		return assessmentName;
	}
	public void setAssessmentName(String assessmentName) {
		this.assessmentName = assessmentName;
	}
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	@Override
	public String toString() {
		return "Assessment [assesmentid=" + assesmentid + ", assessmentName=" + assessmentName + ", topicId=" + topicId
				+ ", getAssesmentid()=" + getAssesmentid() + ", getAssessmentName()=" + getAssessmentName()
				+ ", getTopicId()=" + getTopicId() + "]";
	}
	public static void addAssessment(Statement st) throws SQLException
	{
		Scanner fc=new Scanner(System.in);
		//String aname="";
		System.out.println("Enter Assesment name");
		
		String aname=fc.nextLine();
		
		
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
			System.out.println(e.getMessage());
			continue;
		}
		break;
		}
		Assessment assessment=new Assessment(aname,tid);
		st.executeQuery("insert into assessment values(assessmentseq.nextval,'"+assessment.getAssessmentName()+"',"+assessment.getTopicId()+")");
		System.out.println("Assessment inserted successfully");
	}
	public static void editAssessment(Statement st) throws SQLException
	{
		Scanner fc=new Scanner(System.in);
		int choice=0;
		while(true)
		{
		System.out.println("Enter\n 1.Assessment name\n 2.Topic Id");
		try
		{
		choice=Integer.parseInt(fc.next());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
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
				System.out.println(e.getMessage());
				continue;
			}
			break;
			}
			fc.nextLine();
			System.out.println("Enter Assessment Name");
			String name=fc.nextLine();
			ResultSet rs=st.executeQuery("select * from assessment where assessmentid="+id+"");
			while(rs.next())
			{
				Assessment a=new Assessment(id,name,rs.getInt("topicid"));
				int rows=st.executeUpdate("update assessment set assessmentname='"+a.getAssessmentName()+"' where assessmentid="+a.getAssesmentid()+"");
				System.out.println(rows+" row(s) updated successfully");
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
			id=fc.nextInt();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
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
			topicid=fc.nextInt();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				continue;
			}
			break;
			}
			ResultSet rs=st.executeQuery("select * from assessment where assessmentid="+id+"");
			while(rs.next())
			{
				Assessment a=new Assessment(id,rs.getString("assessmentname"),topicid);
				int rows=st.executeUpdate("update assessment set topicid="+a.getTopicId()+" where assessmentid="+a.getAssesmentid()+"");
				System.out.println(rows+" row(s) updated successfully");
			}
			break;
		}
		default:
		{
			System.out.println("Invalid option");
			editAssessment(st);
		}
		}
	}
	public static void deleteAssessment(Statement st) throws SQLException
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
			System.out.println(e.getMessage());
			continue;
		}
		break;
		}
		ResultSet rs=st.executeQuery("select * from assessment where assessmentid="+aid+"");
		while(rs.next())
		{
			Assessment a=new Assessment(aid,rs.getString("assessmentname"),rs.getInt("topicid"));
			int rows=st.executeUpdate("delete from assessment where assessmentid="+a.getAssesmentid()+"");
			System.out.println(rows+" row(s) deleted successfully");
		}
	}
}
