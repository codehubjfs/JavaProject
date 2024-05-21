package com.ecommerce.users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecommerce.users.account.Account;
import com.ecommerce.users.account.AccountStatus;
import com.ecommerce.users.authentication.Authentication;
import com.ungalkadai.components.Product;
import com.ungalkadai.components.ProductStatus;
import com.ungalkadai.components.SubCategory;
import com.ungalkadai.tester.UngalKadaiTester;

public class Vendor extends Person implements Authentication{
	private int vendorId;
	private String registeredNumber;
	private Account account;
	private long aadharNumber;
	
	public Vendor() {
		super();
	}
	
	public Vendor(int vendorId,String registeredNumber,Account account,long aadharNumber,String email,long mobileNumber,String address) {
		super(address,email,mobileNumber);
		this.vendorId = vendorId;
		this.registeredNumber = registeredNumber;
		this.account = account;
		this.aadharNumber = aadharNumber;
	}
	
	public Vendor(String registeredNumber,Account account,long aadharNumber,String email,long mobileNumber,String address) {
		super(address,email,mobileNumber);
		this.registeredNumber = registeredNumber;
		this.account = account;
		this.aadharNumber = aadharNumber;
	}
	
	
	
	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

//	public String getVendorName() {
//		return vendorName;
//	}

//	public void setVendorName(String vendorName) {
//		this.vendorName = vendorName;
//	}

	public String getRegisteredNumber() {
		return registeredNumber;
	}

	public void setRegisteredNumber(String registeredNumber) {
		this.registeredNumber = registeredNumber;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public long getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(long aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	
	

	@Override
	public String toString() {
		super.toString();
		return "Vendor [vendorId=" + vendorId + ", registeredNumber=" + registeredNumber + ", account=" + account
				+ ", aadharNumber=" + aadharNumber + "]"+" ";
	}

	//Product(double productPrice,String productName, String brand, int quantity, String subtitle, String description, String warranty,
	//Vendor vendor, SubCategory subCategrory, ProductStatus productStatus)
	public boolean addProduct(Product product) {
		String sql = "insert into product values(product_sequence.nextval,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
		PreparedStatement statement = DbmsConnection.getConnection().prepareStatement(sql);
		statement.setString(1, product.getProductName());
		statement.setString(2, product.getBrand());
		statement.setInt(3, product.getQuantity());
		statement.setString(4, product.getSubtitle());
		statement.setString(5, product.getDescription());
		statement.setString(6, product.getWarranty());
		statement.setInt(7, product.getSubCategrory().getSubCategoryId());
		statement.setInt(8, product.getSubCategrory().getCategory().getCategoryId());
		statement.setString(9, product.getProductStatus().toString());
		statement.setInt(10, product.getVendor().getVendorId());
		statement.setDouble(11, product.getProductPrice());
		statement.setString(12, product.getVerificationStatus().toString());
		int rowsAffected = statement.executeUpdate();
		if(rowsAffected>0) {
			System.out.println("Product has been added sucessfully");
			return true;
		}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public int loginUser(long mobileNumber, String password) {
		String sql = "select * from vendor where mobile_no=? and password=?";
		try {
			PreparedStatement statement = DbmsConnection.getConnection().prepareStatement(sql);
			statement.setLong(1, mobileNumber);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				if(rs.getString("account_status").equals(AccountStatus.BLOCKED.toString())) {
					System.out.println("!".repeat(200));
					System.out.println("Your account has been blocked by Admin");
					System.out.println("!".repeat(200));
					UngalKadaiTester.startMenu();
				}else {
				System.out.println("#".repeat(200));
				System.out.println("Welcome Vendor "+rs.getString("username")+" to my Ecommerce Store");
				System.out.println("#".repeat(200));
				return rs.getInt("v_id");
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return -1;
		
	}

	@Override
	public <T extends Person> boolean registerUser(T user) {
			// TODO Auto-generated method stub
			Vendor vendor = (Vendor) user;
			
//			System.out.println(vendor.getAccount().getUserName());
//			System.out.println(vendor.getAccount().getPassword());
//			System.out.println(vendor.getRegisteredNumber());
//			System.out.println(vendor.getAddress());
//			System.out.println(vendor.getMobileNumber());
//			
//			System.out.println(vendor.getEmail());
//			System.out.println(vendor.getAccount().getAccountType().toString());
//			System.out.println(vendor.getAccount().getAccountStatus().toString());
			
			String sql = "insert into vendor values(vendor_sequence.nextval,?,?,?,?,?,?,?,?,?)";
	      try{
	          PreparedStatement statement = DbmsConnection.getConnection().prepareStatement(sql);
	          //statement.setInt(1,vendor.getVendorId());
	          statement.setString(1,vendor.getAccount().getUserName());
	          statement.setString(2,vendor.getRegisteredNumber());
	          statement.setString(3, vendor.getAddress());
	          statement.setLong(4,vendor.getMobileNumber());
	          statement.setString(5,vendor.getAccount().getPassword());
	          statement.setLong(6,vendor.getAadharNumber());
	          statement.setString(7,vendor.getEmail());
	          statement.setString(8, vendor.getAccount().getAccountType().toString());
	          statement.setString(9, vendor.getAccount().getAccountStatus().toString());
	          int rowsAffected = statement.executeUpdate();
	          if(rowsAffected>=1) {
	        	  System.out.println("Registered as Vendor Successfully!!");
	        	  System.out.println("Welcome "+vendor.getAccount().getUserName()+" to my ecommerce platform");
	        	  return true;
	          }
	      }catch (Exception e){
	          System.out.println(e.getMessage());
	      }
	      return false;
			
		}
		
	public void updateProductQuantity(Product product,int quantity) {
		String sql = "update product set quantity=quantity+?";
		try {
			PreparedStatement statement = DbmsConnection.getConnection().prepareStatement(sql);
			statement.setInt(1, quantity);
			int rowsAffected = statement.executeUpdate();
			if(rowsAffected>0) {
				System.out.println("Quantity has been updated sucessfully");
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updateProductPrice(Product p,double price) {
		String sql = "update product set p_price=?";
		try {
			PreparedStatement statement = DbmsConnection.getConnection().prepareStatement(sql);
			statement.setDouble(1, price);
			int rowsAffected = statement.executeUpdate();
			if(rowsAffected>0) {
				System.out.println("Price has been updated sucessfully");
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updateProductBrand(Product p,String brand) {
		String sql = "update product set brand=?";
		try {
			PreparedStatement statement = DbmsConnection.getConnection().prepareStatement(sql);
			statement.setString(1, brand);
			int rowsAffected = statement.executeUpdate();
			if(rowsAffected>0) {
				System.out.println("Brand has been updated sucessfully");
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updateProductName(Product p,String productName) {
		String sql = "update product set p_name=?";
		try {
			PreparedStatement statement = DbmsConnection.getConnection().prepareStatement(sql);
			statement.setString(1, productName);
			int rowsAffected = statement.executeUpdate();
			if(rowsAffected>0) {
				System.out.println("Name has been updated sucessfully");
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updateProductSubtitle(Product p,String subTitle) {
		String sql = "update product set p_subtitle=?";
		try {
			PreparedStatement statement = DbmsConnection.getConnection().prepareStatement(sql);
			statement.setString(1, subTitle);
			int rowsAffected = statement.executeUpdate();
			if(rowsAffected>0) {
				System.out.println("Subtitle has been updated sucessfully");
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updateProductDescription(Product p,String description) {
		String sql = "update product set p_description=?";
		try {
			PreparedStatement statement = DbmsConnection.getConnection().prepareStatement(sql);
			statement.setString(1, description);
			int rowsAffected = statement.executeUpdate();
			if(rowsAffected>0) {
				System.out.println("Description has been updated sucessfully");
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
		
	
	
}
