package com.jobportal.users;

public class Admin extends User {
	

	private int adminID;
	private String name;

	


	
	public Admin(String username, String email, String password, UserType userType) {
		super(username, email, password, userType);
		// TODO Auto-generated constructor stub
	}

	public int getAdminID() {
		return adminID;
	}

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	public Admin(int adminId2, String username, String password, String email) {
		// TODO Auto-generated constructor stub
	}

	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
