package com.courses;
import java.util.*;
import java.util.stream.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import javax.xml.transform.stream.StreamSource;
public class Courses implements Comparable<Courses>{
	private int courseID;
	private String CourseName;
	private int instructorid;
	private LocalDate StartDate;
	private LocalDate EndDate;
	//private ArrayList<Courses> cal=new ArrayList<Courses>();
	private ArrayList<Module>moduleList=new ArrayList<Module>();
	public Courses(int courseID, String courseName, int instructorid, LocalDate startDate, LocalDate endDate,
			ArrayList<Module> al) {
		super();
		this.courseID = courseID;
		CourseName = courseName;
		this.instructorid = instructorid;
		StartDate = startDate;
		EndDate = endDate;
		this.moduleList = al;
	}

	public Courses(String courseName, int instructorid, LocalDate startDate, LocalDate endDate) {
		super();
		CourseName = courseName;
		this.instructorid = instructorid;
		StartDate = startDate;
		EndDate = endDate;
	}
	
	public Courses(int courseID, String courseName, int instructorid, LocalDate startDate, LocalDate endDate) {
		super();
		this.courseID = courseID;
		CourseName = courseName;
		this.instructorid = instructorid;
		StartDate = startDate;
		EndDate = endDate;
	}
	public Courses()
	{
		
	}
	
	/*public ArrayList<Courses> getCal() {
		return cal;
	}*/

	/*public void setCal(ArrayList<Courses> cal) {
		this.cal = cal;
	}*/

	public ArrayList<Module> getModuleList() {
		return moduleList;
	}

	public void setModuleList(ArrayList<Module> al) {
		this.moduleList = al;
	}
	public void pushList(Module module) {
		moduleList.add(module);
	}
	/*public void pushCourseList(Courses course)
	{
		cal.add(course);
	}*/
	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public String getCourseName() {
		return CourseName;
	}
	public void setCourseName(String courseName) {
		CourseName = courseName;
	}
	public int getInstructorid() {
		return instructorid;
	}
	public void setInstructorid(int instructorid) {
		this.instructorid = instructorid;
	}
	public LocalDate getStartDate() {
		return StartDate;
	}
	public void setStartDate(LocalDate startDate) {
		StartDate = startDate;
	}
	public LocalDate getEndDate() {
		return EndDate;
	}
	public void setEndDate(LocalDate endDate) {
		EndDate = endDate;
	}
	@Override
	public String toString() {
		return "Courses [ CourseName=" + CourseName + ", instructorid=" + instructorid
				+ ", StartDate=" + StartDate + ", EndDate=" + EndDate + ", getCourseName()=" + getCourseName() + ", getInstructorid()=" + getInstructorid()
				+ ", getStartDate()=" + getStartDate() + ", getEndDate()=" + getEndDate() + "]";
	}
	
	@Override
	public int compareTo(Courses o) {
		// TODO Auto-generated method stub
		return Integer.compare(o.courseID, this.courseID);
	}

}
