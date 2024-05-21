package com.taskmanagement.task.managements;

public class Task {

	private int taskId;
	private String taskName;
	private String description;
	private String startDate;
	private String endDate;
	private String priority;

	public Task(String taskName, String description, String startDate, String endDate, String priority) {
		super();
		this.taskName = taskName;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priority = priority;

	}

	public Task(int taskId, String taskName) {
		// TODO Auto-generated constructor stub
		this.taskId = taskId;
		this.taskName = taskName;
	}

	public Task(int taskId, String taskName, String description, String startDate, String endDate, String priority) {
		this.taskId = taskId;
		this.taskName = taskName;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priority = priority;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

}
