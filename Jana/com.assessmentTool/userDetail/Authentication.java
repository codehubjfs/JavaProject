package com.userDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.smartcliff.assessmentTool.DbmsConnection;

public class Authentication {

	private String rEmail;
	private String rPassword;
	private String rname;
	private String rLastName;
	private String rGender;
	private String rCity;
	private String rCountry;
	private int rCid;
	private int sid;
	private int rEid;

	public Educator validateEducatorLogin(String email, String password) {

		try {
			Connection con = DbmsConnection.getConnection();
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("select * from educator where email='" + email + "'");
			if (rs.next()) {
				rEmail = rs.getString("email");
				rPassword = rs.getString("password");
				rname = rs.getString("fname");
				rLastName = rs.getString("lname");
				rGender = rs.getString("gender");
				rCity = rs.getString("city");
				rCountry = rs.getString("country");
				rEid = rs.getInt("eid");
				Educator e = new Educator(rEid, email, password, rname, rLastName, rGender, rCity, rCountry);
				if (rEmail.equals(email) && rPassword.equals(password)) {
					return e;
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getrEmail() {
		return rEmail;
	}

	public void setrEmail(String rEmail) {
		this.rEmail = rEmail;
	}

	public String getrPassword() {
		return rPassword;
	}

	public void setrPassword(String rPassword) {
		this.rPassword = rPassword;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public Student validateStudentLogin(String email, String password) {

		try {
			Connection con = DbmsConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from student where email='" + email + "'");

			if (rs.next()) {
				rEmail = rs.getString("email");

				rPassword = rs.getString("password");
				rname = rs.getString("fname");
				rLastName = rs.getString("lname");
				rGender = rs.getString("gender");
				rCity = rs.getString("city");
				rCountry = rs.getString("country");
				sid = rs.getInt("sid");
				Student s = new Student(sid, email, password, rname, rLastName, rGender, rCity, rCountry);

				if (rEmail.equals(email) && rPassword.equals(password)) {
					return s;
				}
				// return false;

			}
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
