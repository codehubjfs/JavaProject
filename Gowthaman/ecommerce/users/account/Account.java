package com.ecommerce.users.account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import com.ecommerce.users.DbmsConnection;

public class Account {
	private String userName;
	private String password;
	private AccountType accountType;
	private AccountStatus accountStatus;
	
	public Account() {
		
	}
	
	public Account(String userName,String password,AccountType accountType,AccountStatus accountStatus) {
		this.userName = userName;
		this.password = password;
		this.accountType = accountType;
		this.accountStatus = accountStatus;
	}
	
	public Account(String userName,String password,AccountType accountType) {
		this.userName = userName;
		this.password = password;
		this.accountType = accountType;
	}
	
	public Account(String userName) {
		this.userName = userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	
	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public AccountType getAccountType() {
		return accountType;
	}
	
	public AccountStatus getAccountStatus() {
		return accountStatus;
	}
	
	@Override
	public String toString() {
		return userName+" "+" "+accountType+" "+accountStatus+" ***********";
	}
	
//	public static void loginUser(String username, String password,String table){
//		try {
//			PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement("select * from "+table+" where username=? and password=?");
//			stmt.setString(1, username);
//			stmt.setString(2, password);
//			ResultSet rs = stmt.executeQuery();
//			if(rs.next()) {
//				System.out.println("Welcome "+rs.getString("username")+" to ungal kadai");
//			}
//			
//		}catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
//		
//	} 
	
}
