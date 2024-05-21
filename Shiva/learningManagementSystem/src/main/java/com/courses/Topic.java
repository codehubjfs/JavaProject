package com.courses;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.courses.Module;
public class Topic {
	private int topicId;
	private String topicName;
	private int moduleId;
	ArrayList<Assessment>assessmentList=new ArrayList<Assessment>();
	
	public Topic(int topicId, String topicName, int moduleId, ArrayList<Assessment> assessmentList) {
		super();
		this.topicId = topicId;
		this.topicName = topicName;
		this.moduleId = moduleId;
		this.assessmentList = assessmentList;
	}

	public Topic(int topicId, String topicName, int moduleId) {
		super();
		this.topicId = topicId;
		this.topicName = topicName;
		this.moduleId = moduleId;
	}
	
	public Topic(String topicName, int moduleId) {
		super();
		this.topicName = topicName;
		this.moduleId = moduleId;
	}

	public Topic(int topicId, String topicName) {
		// TODO Auto-generated constructor stub
		this.topicName = topicName;
		this.topicId = topicId;
	}

	public Topic(int moduleId, int topicId) {
		// TODO Auto-generated constructor stub
		this.topicId = topicId;
		this.moduleId = moduleId;
	}
	
	public ArrayList<Assessment> getAssessmentList() {
		return assessmentList;
	}
	
	public void setAssessmentList(ArrayList<Assessment> assessmentList) {
		this.assessmentList = assessmentList;
	}
	public void addAssessmentList(Assessment assessment)
	{
		assessmentList.add(assessment);
	}
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	@Override
	public String toString() {
		return "Topic [topicId=" + topicId + ", topicName=" + topicName + ", getTopicId()=" + getTopicId()
				+ ", getTopicName()=" + getTopicName() + "]";
	}
	
	
}
