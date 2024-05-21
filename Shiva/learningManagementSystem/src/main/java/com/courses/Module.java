package com.courses;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
public class Module {
	private int moduleId;
	private String moduleName;
	private int instructorId;
	private int courseId;
	private ArrayList<Topic> tal=new ArrayList<Topic>();
	public Module()
	{
		
	}
	public Module(int moduleId, String moduleName, int instructorId, int courseId, ArrayList<Topic> tal) {
		super();
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.instructorId = instructorId;
		this.courseId = courseId;
		this.tal = tal;
	}

	public Module(int moduleId, String moduleName, int instructorId, int courseId) {
		super();
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.instructorId = instructorId;
		this.courseId = courseId;
	}
	
	public Module(String moduleName, int instructorId, int courseId) {
		super();
		this.moduleName = moduleName;
		this.instructorId = instructorId;
		this.courseId = courseId;
	}
	public void addTopicList(Topic topic)
	{
		tal.add(topic);
	}

	public ArrayList<Topic> getTal() {
		return tal;
	}

	public void setTal(ArrayList<Topic> tal) {
		this.tal = tal;
	}

	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public int getInstructorId() {
		return instructorId;
	}
	public void setInstructorId(int instructorId) {
		this.instructorId = instructorId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	@Override
	public String toString() {
		return "Module [moduleId=" + moduleId + ", moduleName=" + moduleName + ", instructorId=" + instructorId
				+ ", courseId=" + courseId + ", getModuleId()=" + getModuleId() + ", getModuleName()=" + getModuleName()
				+ ", getInstructorId()=" + getInstructorId() + ", getCourseId()=" + getCourseId() + "]";
	}
	public static void moduleManagement(Statement st)
	{
		
	}
	
}
