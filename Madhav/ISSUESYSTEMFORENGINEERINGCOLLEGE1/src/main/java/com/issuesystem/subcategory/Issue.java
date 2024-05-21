package com.issuesystem.subcategory;

import java.time.LocalDate;

public class Issue {
	
	private String issue_title;
	private String description;
	private String issueOn;
	private String priority;
	private LocalDate issueDate;
	private LocalDate issue_raise_date;
	public Issue(String issue_title, String description, String issueOn,LocalDate issue_raise_date, String priority, LocalDate issueDate) {
		super();
		this.issue_title = issue_title;
		this.description = description;
		this.issueOn = issueOn;
		this.priority = priority;
		this.issueDate = issueDate;
		this.issue_raise_date = issue_raise_date;
	}
	public String getIssue_title() {
		return issue_title;
	}
	public void setIssue_title(String issue_title) {
		this.issue_title = issue_title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIssueOn() {
		return issueOn;
	}
	public void setIssueOn(String issueOn) {
		this.issueOn = issueOn;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public LocalDate getIssue_date() {
		return issueDate;
	}
	public void setIssue_date(LocalDate issueDate) {
		this.issueDate = issueDate;
	}
	public LocalDate getIssue_raise_date() {
		return issue_raise_date;
	}
	public void setIssue_raise_date(LocalDate issue_raise_date) {
		this.issue_raise_date = issue_raise_date;
	}


}
