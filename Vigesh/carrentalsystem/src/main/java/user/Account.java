package user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;

import com.carrentalsystem.exception.InvalidEmailException;
import com.carrentalsystem.exception.InvalidPhoneNumberException;
import com.carrentalsystem.exception.PasswordException;
import com.carrentalsystem.exception.UsernameException;
import com.carrentalsystem.exception.Validate;
import com.gotrip.CarRentalSystem.DbConnection;

//Account has username and password for authentication

public class Account {
      private String userName;
      private String password;
      private String accountType;
      
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public Account() {
		
	}
	
	public Account(String userName, String password,  String accountType) {
		super();
		this.userName = userName;
		this.password = password;
		this.accountType = accountType;
	}
	Connection con = DbConnection.getConnection();
	BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	//login the user or admin to the application
      public boolean login(String accountType) throws SQLException, IOException {

    	   System.out.println("Enter UserName:");
		   String userName=sc.readLine();
		  // System.out.println(userName);
		   System.out.println("Enter Password:");
		   String password=sc.readLine();
		  // System.out.println(password);
    	  
    	  if(accountType.equals("Admin")) {
    		  	 PreparedStatement st=con.prepareStatement("select admin_id,username,password from admin where username= ? ");
    		  	 st.setString(1,userName);
			     ResultSet set=st.executeQuery();
			    
			     if(set.next())
			     {
			    	 String checkName=set.getString("username");
			    	 String checkPassword=set.getString("password");
			    	// System.out.println(checkName+" "+checkPassword);
			    	 if(checkName.equals(userName)&& checkPassword.equals(password)) {
			    		 System.out.println("WELCOME ADMIN "+userName);
			    		 return true;
			    		// return set.getInt("user_id");
			    	 }else {
			    		 System.out.println("PASSWORD WRONG");
			    	 }
			     }
			     else {
			    	 System.out.println("INVALID ADMIN NAME AND PASSWORD ");
			     }
			    
      }else if(accountType.equals("Customer")) {
    	  
		  	 PreparedStatement st=con.prepareStatement("select username,password from users where username='"+userName+"' ");
		     ResultSet set=st.executeQuery();
		     if(set.next())
		     {
		    	 String checkName=set.getString("username");
		    	 String checkPassword=set.getString("password");
		    	
		    	 
		    	 if(checkName.equals(userName)&& checkPassword.equals(password)) {
		    		 LoggedInUser loggedInUser = LoggedInUser.getInstance(userName);
		    		 System.out.println("WELCOME USER "+userName);
		    		return true;
		    	 }else {
		    		 System.out.println("INVALID PASSWORD");
		    	 }
		     }else {
		    	 System.out.println("INVALID USERNAME AND PASSWORD");
		     }
		    
}
		//return false;
		//return 0;
		return false;
    	  
      }
      //registering the user by their inputs
      public void reg() throws SQLException, IOException, PasswordException, UsernameException {
		   
		   System.out.println("Enter First Name:");
		   String firstName = sc.readLine();
		   System.out.println("Enter Last Name:");
		   String lastName = sc.readLine();
		   
		   String email = null;
		   boolean flag=false;
	        do {
	        	try {
	        		System.out.println("Enter Email:");
			email = Validate.validateEmail(sc.readLine());
			break;
	        	}catch(InvalidEmailException e) {
	        		System.out.println(e.getMessage());
	        		flag=true;
	        	}
	        	
	        }while(flag);
		   
		   System.out.println("Enter Gender:(MALE/FEMALE/OTHERS)");
		   String gender = sc.readLine();
		   long phoneNumber=0;
		   flag=false;
		   do {
		   System.out.println("Enter Phone Number:");
		   try {
		    phoneNumber = Validate.validatePhoneNumber(sc.readLine());
		   
		   
		   break;
		   }catch(InvalidPhoneNumberException e) {
			   System.out.println(e.getMessage());
			   flag=true;
		   }
		   }while(flag);
		   flag=false;
		   String passwordVal = null;
		   do {
		   System.out.println("Enter Password:");
		   try {
		    passwordVal =Validate.validatePassword(sc.readLine());
		    break;
		   }catch(PasswordException e) {
			   System.out.println(e.getMessage());
			   flag=true;
		   }
		   }while(flag);
		   System.out.println("Enter Licence Number:");
		   String licence = sc.readLine();
		   
		   flag=false;
		   String userName=null;
		   do {
			   System.out.println("Enter User Name:");
			   try {
		    userName =Validate.validateUsername( sc.readLine());
		     break;
			   }catch(UsernameException e) {
				   System.out.println(e.getMessage());
				   flag=true;
			   }
		   }while(flag);
		     Statement st=con.createStatement();
	    	  ResultSet rs = st.executeQuery("SELECT userseq.nextval FROM dual");
	    	  int userid = 0; 
	 	     if (rs.next()) {
	 	         userid = rs.getInt(1); 
	 	     }
		   Customer customer=new Customer( firstName, lastName, email, gender, phoneNumber, passwordVal,"Active",userid, licence, userName);
    	   st=con.createStatement();
	      String Active="Active";
	      int r= st.executeUpdate("INSERT INTO users(user_id, first_name, last_name, email, gender, phone_number, password, account_status,license_id, username) " +
	                "VALUES (" + userid+ ", '" + customer.getFirstName() + "', '" + customer.getLastName() + 
	                "', '" + customer.getEmail() + "', '" + customer.getGender() + "', " + customer.getPhoneNumber() + ", '" +
	                customer.getPassword() + "', '" + Active + "', '" + customer.getLicenseNumber()+ "','" +customer.getUserName() + "')");
	      System.out.println("REGISTERED SUCCESSFULLY");
      }
      
      
      
}
