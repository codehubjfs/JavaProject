package com.courses;

public class Assignment {
	private int assignmentId;
	private String assignmentName;
	public Assignment(int assignmentId, String assignmentName) {
		super();
		this.assignmentId = assignmentId;
		this.assignmentName = assignmentName;
	}
	public int getAssignmentId() {
		return assignmentId;
	}
	public void setAssignmentId(int assignmentId) {
		this.assignmentId = assignmentId;
	}
	public String getAssignmentName() {
		return assignmentName;
	}
	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}
	@Override
	public String toString() {
		return "Assignment [assignmentId=" + assignmentId + ", assignmentName=" + assignmentName
				+ ", getAssignmentId()=" + getAssignmentId() + ", getAssignmentName()=" + getAssignmentName() + "]";
	}
	
}
