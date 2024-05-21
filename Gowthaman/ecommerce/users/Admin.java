package com.ecommerce.users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecommerce.users.account.Account;
import com.ecommerce.users.account.AccountStatus;
import com.ungalkadai.components.Category;
import com.ungalkadai.components.SubCategory;

public class Admin{
	private int adminId;
	private String userName;
	private String password;
	
	public Admin() {
		
	}
	
	public Admin(int adminId,String userName,String password) {
		this.adminId = adminId;
		this.userName = userName;
		this.password = password;
	}
	
	public Admin(String userName,String password) {
		//this.adminId = adminId;
		this.userName = userName;
		this.password = password;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int getAdminId() {
		return adminId;
	}
	
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	
	public String getPassowrd() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean createAnotherAdmin(Admin admin) {
		String sql = "insert into admin values(admin_sequence.nextval,?,?)";
		try {
			PreparedStatement statement = DbmsConnection.getConnection().prepareStatement(sql);
			statement.setString(1, admin.getPassowrd());
			statement.setString(2, admin.getUserName());
			int rowsAffected = statement.executeUpdate();
			if(rowsAffected>0) {
				return true;
			}else {
				System.out.println("Error occured while trying to insert");
				return false;
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public void blockUser(int userId,int userType) {
		String sql = "";
		if(userType==1) {
			sql = "update customer set account_status=? where c_id=?";
		}else if(userType==2){
			sql = "update vendor set account_status=? where v_id=?";
		}
		try {
			PreparedStatement statement = DbmsConnection.getConnection().prepareStatement(sql);
			statement.setString(1, AccountStatus.BLOCKED.toString());
			statement.setInt(2, userId);
			int rowsAffected = statement.executeUpdate();
			if(rowsAffected>0) {
				System.out.println("The user Account has been blocked sucessfully");
			}else {
				System.out.println("There is no such user exist with id "+userId);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		
	}
	
	public void activateUser(int userId,int userType) {
		String sql = "";
		if(userType==1) {
			sql = "update customer set account_status=? where c_id=?";
		}else if(userType==2){
			sql = "update vendor set account_status=? where v_id=?";
		}
		try {
			PreparedStatement statement = DbmsConnection.getConnection().prepareStatement(sql);
			statement.setString(1, AccountStatus.ACTIVE.toString());
			statement.setInt(2, userId);
			int rowsAffected = statement.executeUpdate();
			if(rowsAffected>0) {
				System.out.println("The user Account has been activated sucessfully");
			}else {
				System.out.println("There is no blocked such user exist with id "+userId);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
	}
	
//	public void blockSeller(Vendor seller) {
//		
//	}
	
	public boolean loginAdmin(Admin admin) {
		String sql = "select * from admin where username=? and password=?";
		try {
			PreparedStatement statement = DbmsConnection.getConnection().prepareStatement(sql);
			statement.setString(1, admin.getUserName());
			statement.setString(2, admin.getPassowrd());
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				System.out.println("#".repeat(200));
				System.out.println("Welcome Admin "+resultSet.getString("username")+" to my Ecommerce Store");
				System.out.println("#".repeat(200));
				return true;
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public void addCategory(Category categrory) {
		String sql = "insert into category values(category_sequence.nextval,?)";
		try {
		PreparedStatement statement = DbmsConnection.getConnection().prepareStatement(sql);
		statement.setString(1, categrory.getCategoryName());
		int rowsAffected = statement.executeUpdate();
		if(rowsAffected>0) {
			System.out.println("The New category has been introduced successfully");
		}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void addSubCategory(SubCategory subCategory) {
		String sql = "INSERT into subcategory values(subcategrory_sequence.nextval,?,?)";
		try {
			PreparedStatement statement = DbmsConnection.getConnection().prepareStatement(sql);
			statement.setInt(1,subCategory.getCategory().getCategoryId());
			statement.setString(2, subCategory.getSubCategoryName());
			int rowsAffected = statement.executeUpdate();
			if(rowsAffected>0) {
				System.out.println("The New Subcategory has been introduced Sucessfully");
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	

}
