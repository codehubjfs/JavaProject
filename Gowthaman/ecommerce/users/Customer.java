package com.ecommerce.users;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ecommerce.menu.CartAndOrderManager;
import com.ecommerce.users.account.Account;
import com.ecommerce.users.account.AccountStatus;
import com.ecommerce.users.authentication.Authentication;
import com.ungalkadai.components.Cart;
import com.ungalkadai.components.Order;
import com.ungalkadai.components.Orders;
import com.ungalkadai.components.Product;
import com.ungalkadai.tester.UngalKadaiTester;

public class Customer extends Person implements Authentication{
	private int customerId;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Cart myCart;
    private Orders myOrders;
    private Account account;
    //private CustomerType customerType;

    public Customer() {

    }
    
    public Customer(String firstName, String lastName, int customerId,String address,String email,long mobileNumber,Account account) {
        super(address,email,mobileNumber);
        this.firstName = firstName;
        this.lastName = lastName;
        //this.age = age;
        //this.customerName = customerName;
        this.customerId = customerId;
        this.myCart = new Cart();
        this.myOrders = new Orders();
        this.account = account;
       // this.gender = gender;
        //this.customerType = customerType;
    }

    public Customer(String firstName, String lastName,Gender gender, int customerId,String address,String email,long mobileNumber,Account account) {
        super(address,email,mobileNumber);
        this.firstName = firstName;
        this.lastName = lastName;
        //this.age = age;
        //this.customerName = customerName;
        this.customerId = customerId;
        this.account = account;
        this.gender = gender;
        //this.customerType = customerType;
    }
    
    public Customer(String firstName, String lastName,Gender gender,String address,String email,long mobileNumber,Account account) {
        super(address,email,mobileNumber);
        this.firstName = firstName;
        this.lastName = lastName;
        //this.age = age;
        //this.customerName = customerName;
        this.account = account;
        this.gender = gender;
        //this.customerType = customerType;
    }


    public int getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

//    public byte getAge() {
//        return age;
//    }

//    public String getCustomerName() {
//        return customerName;
//    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setCart(Cart myCart) {
    	this.myCart = myCart;
    }
    
    public Cart getCart() {
    	return myCart;
    }
    
    public void setOrders(Orders myOrders) {
    	this.myOrders = myOrders;
    }
    
    public Orders getOrders() {
    	return myOrders;
    }

//    public void setAge(byte age) {
//        this.age = age;
//    }

//    public void setCustomerName(String customerName) {
//        this.customerName = customerName;
//    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
    @Override
    public String toString() {
    	return "First Name : "+firstName+"\nLast Name : "+lastName+"\n"+super.toString();
    }

	@Override
	public int loginUser(long mobileNumber, String password){
		try {
			String sql = "select * from customer where mobile_no=? and password=?";
			PreparedStatement stmt = DbmsConnection.getConnection().prepareStatement(sql);
			stmt.setLong(1, mobileNumber);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("account_status").equals(AccountStatus.BLOCKED.toString())) {
					System.out.println("!".repeat(200));
					System.out.println("Your account has been blocked by Admin");
					System.out.println("!".repeat(200));
					UngalKadaiTester.startMenu();
				}else {
					System.out.println("#".repeat(200));
					System.out.println("Welcome Customer "+rs.getString("username")+" to my Ecommerce Store");
					System.out.println("#".repeat(200));
				return rs.getInt("c_id");
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
		Customer customer = (Customer) user;
		//System.out.println(customer.getCustomerId());
//		System.out.println(customer.getAccount().getUserName());
//		System.out.println(customer.getAccount().getPassword());
//		System.out.println(customer.getFirstName());
//		System.out.println(customer.getLastName());
//		System.out.println(customer.getAddress());
//		System.out.println(customer.getGender().toString());
//		System.out.println(customer.getMobileNumber());
//		System.out.println(customer.getEmail());
//		System.out.println(customer.getAccount().getAccountType().toString());
//		System.out.println(customer.getAccount().getAccountStatus().toString());
		
		String sql = "insert into customer values(customer_sequence.nextval,?,?,?,?,?,?,?,?,?,?)";
      try{
    	  System.out.println("I am inside try");
          PreparedStatement statement = DbmsConnection.getConnection().prepareStatement(sql);
          //statement.setInt(1,customer.getCustomerId());
          statement.setString(1,customer.getAccount().getUserName());
          statement.setString(2,customer.getAccount().getPassword());
          statement.setString(3, customer.getFirstName());
          statement.setString(4,customer.getLastName());
          statement.setString(5,customer.getAddress());
          statement.setString(6,customer.getGender().toString());
          statement.setLong(7,customer.getMobileNumber());
          statement.setString(8,customer.getEmail());
          statement.setString(9,customer.getAccount().getAccountType().toString());
          statement.setString(10,customer.getAccount().getAccountStatus().toString());
          int rowsAffected = statement.executeUpdate();
          //System.out.println("After the executeUpdate query");
          if(rowsAffected>=1) {
        	  System.out.println("Registered as Customer Successfully!!");
        	  System.out.println("Welcome "+customer.getFirstName()+ " to my ecommerce platform");
        	  return true;
          }else {
        	  System.out.println("There is some error occured");
          }
      }catch (Exception e){
          System.out.println(e.getMessage());
      }
      return false;
		
	}
	
	public boolean addProductToCart(Product p) {
		String sql = "insert into cart_detail values(?,?,?)";
		try {
		PreparedStatement statement = DbmsConnection.getConnection().prepareStatement(sql);
		statement.setInt(1, this.getCart().getCart_id());
		statement.setInt(2, p.getProductId());
		statement.setInt(3,p.getQuantity());
		int rowsAffected = statement.executeUpdate();
		if(rowsAffected>0) {
			System.out.println("Item has been inserted into cart sucessfully");
			return true;
			//CartAndOrderManager.updateCartTable(p);
		}else {
			System.out.println("Some error occured");
		}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
		
	}
	
	public boolean makeOrder(Order order) {
		String sql = "insert into orders values(?,?,?,?)";
		try {
			PreparedStatement statement = DbmsConnection.getConnection().prepareStatement(sql);
			statement.setInt(1, order.getOrderId());
			statement.setInt(2, order.getCustomer().getCustomerId());
			statement.setDate(3,Date.valueOf(order.getOrderDate()));
			statement.setString(4, order.getAddress());
			int rowsAffected = statement.executeUpdate();
			if(rowsAffected>0)
			{
				System.out.println("Your Order has been placed sucessfully");
				return true;
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			
		}
		return false;
	}
	
	public void update

	
	
	
	
	
	
}
