package com.courseDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.smartcliff.assessmentTool.DbmsConnection;

public class Assessment {
	private int aId;
	private String aName;
	private double duration;
	private int totalMarks;
	private String aDate;
	private String stTime;
	private String endTime;
	private int cId;
	private int eId;

	List<Question> questions = new ArrayList<>();

	// Constructor for creating an assessment without aId
	public Assessment(int cId, String aName, double duration, int totalMarks, String aDate, String stTime,
			String endTime) {
		// super(cId);
		this.cId = cId;
		this.aName = aName;
		this.duration = duration;
		this.totalMarks = totalMarks;
		this.aDate = aDate;
		this.stTime = stTime;
		this.endTime = endTime;
	}

	// Constructor for creating an assessment with aId
	public Assessment(int aId, String aName, String stTime, String endTime, double duration, int totalMarks, int cId,
			String aDate, int eId) {
		// super(cId);

		this.aId = aId;
		this.aName = aName;
		this.duration = duration;
		this.totalMarks = totalMarks;
		this.aDate = aDate;
		this.stTime = stTime;
		this.endTime = endTime;
		this.eId = eId;
		this.cId=cId;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public int geteId() {
		return eId;
	}

	public void seteId(int eId) {
		this.eId = eId;
	}

	public int getaId() {
		return aId;
	}

	public void setaId(int aId) {
		this.aId = aId;
	}

	public String getaName() {
		return aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

	public String getaDate() {
		return aDate;
	}

	public void setaDate(String aDate) {
		this.aDate = aDate;
	}

	public String getStTime() {
		return stTime;
	}

	public void setStTime(String stTime) {
		this.stTime = stTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public String toString() {
		return "Assessment ID  :" + aId + "\n" + "Assessment Name:" + aName + "\n" + "Start Time     :" + stTime + "\n"
				+ "EndTime        :" + endTime + "\n" + "Duration       :" + duration + "\n" + "Tot_mark       :"
				+ totalMarks + "\n" + "CID            :" + cId + "\n" + "Adate          :" + aDate + "\n"
				+ "|---------------------------------------------------------------------------------------------------------------------------------------------------|";
	}

}
