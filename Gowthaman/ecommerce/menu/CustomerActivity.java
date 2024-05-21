package com.ecommerce.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.ecommerce.customizedexceptions.InvalidEmailException;
import com.ecommerce.customizedexceptions.InvalidMenuChoiceException;
import com.ecommerce.customizedexceptions.InvalidMobileNumberException;
import com.ecommerce.customizedexceptions.InvalidPasswordException;
import com.ecommerce.customizedexceptions.Validation;
import com.ecommerce.users.Customer;
import com.ecommerce.users.DbmsConnection;
import com.ecommerce.users.Gender;
import com.ecommerce.users.account.Account;
import com.ecommerce.users.account.AccountStatus;
import com.ecommerce.users.account.AccountType;
import com.ungalkadai.components.Cart;
import com.ungalkadai.components.Product;
import com.ungalkadai.tester.UngalKadaiTester;

public class CustomerActivity {
	
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	static StringBuilder exitBox = new StringBuilder();
	static {
        exitBox.append("+----------------------------------+\n");
        exitBox.append("|   Are you sure to Exit?         |\n");
        exitBox.append("|  (1-yes | 0-no)                 |\n");
        exitBox.append("+----------------------------------+\n");
	}
	
	public static void showCustomerMenu(Customer customer) {
		customer.setCart(new Cart(new CartAndOrderManager().getMyCart(customer)));
		List<Product> mycart = customer.getCart().getMyCart();
		int choice = 0,confirmation = 0;
		do {
		System.out.println("+----------------------------------+");
        System.out.println("|          CUSTOMER MENU           |");
        System.out.println("+----------------------------------+");
        System.out.println("| 1. Search                        |");
        System.out.println("| 2. Cart                          |");
        System.out.println("| 3. Profile                       |");
        System.out.println("| 4. Orders                        |");
        System.out.println("| 5. Previous Menu                 |");
        System.out.println("| 6. Exit                          |");
        System.out.println("+----------------------------------+");
		System.out.println("Enter your choice");
		try {
			choice = Integer.parseInt(reader.readLine());
			choice = Validation.isOptionValid(1, 6, choice);
		} catch (NumberFormatException e) {
			System.out.println("Input should be a number.The letter or symbols are not allowed");
			continue;
		} catch (InvalidMenuChoiceException  | IOException e) {
			System.out.println(e.getMessage());
			continue;
		}
		switch(choice) {
			case 1:{
				System.out.println("You have choosen to search");
				showSearchMenu(customer);
				break;
			}
			case 2:{
				System.out.println("You are heading to cart");
				mycart.forEach((p)->System.out.println("S.no : "+((mycart.indexOf(p))+1)+" "+p+"| Quantity : "+p.getQuantity()+" |"+"\n"+"-".repeat(200)));
				break;
			}
			case 3:{
				System.out.println("You are heading to profile menu");
				showProfileMenu(customer);
				System.out.println("Page under developement........we will get back to you soon");
				break;
			}
			case 4:{
				System.out.println("You are heading to orders");
				System.out.println("Page under developement........we will get back to you soon");
				break;
			}
			case 5:{
				System.out.println("You are heading to previous menu");
				doAuthentication();
				break;
			}
			case 6:{
				System.out.println("You have choosen to exit!!!");
				System.out.println(exitBox);
				System.out.println("Enter your choice");
				try {
					confirmation = Integer.parseInt(reader.readLine());
					confirmation = Validation.isOptionValid(0, 1, confirmation);
				}catch(NumberFormatException e) {
					System.out.println("Input should be a number.The letter or symbols are not allowed");
					continue;
				}catch(Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
				if(confirmation==1) {
					System.out.println("Thanks for visiting "+customer.getAccount().getUserName()+" Have a Nice day :)");
					System.exit(0);
				}
				break;
			}
		}
		
		}while(true);
	}
	
	public static void showSearchMenu(Customer customer) {
		int choice = 0;
		int confirmation = 0;
		do {
		System.out.println("+----------------------------------+");
        System.out.println("|           SEARCH MENU            |");
        System.out.println("+----------------------------------+");
        System.out.println("| 1. Search by category            |");
        System.out.println("| 2. Search by subcategory         |");
        System.out.println("| 3. Search by productName         |");
        System.out.println("| 4. Go Back                       |");
        System.out.println("| 5. Exit                          |");
        System.out.println("+----------------------------------+");
        System.out.println("Enter your choice");
        try {
        	choice = Integer.parseInt(reader.readLine());
        	choice = Validation.isOptionValid(1, 5, choice);
        }catch (NumberFormatException e) {
			System.out.println("Input should be a number.The letter or symbols are not allowed");
			continue;
		} catch (InvalidMenuChoiceException  | IOException e) {
			System.out.println(e.getMessage());
			continue;
		}
        switch(choice) {
        case 1:{
        	SearchAndSortManager.searchByCategory(customer);
        	break;
        }
        case 2:{
        	SearchAndSortManager.searchBySubCategory(customer);
        	break;
        }
        case 3:{
        	System.out.println("Page under developement........we will get back to you soon");
			break;
        }
        case 4:{
        	System.out.println("You are heading to previous page");
        	showCustomerMenu(customer);
        	break;
        }
        case 5:{
        	System.out.println("You have choosen to exit!!!");
			System.out.println(exitBox);
			System.out.println("Enter your choice");
			try {
				confirmation = Integer.parseInt(reader.readLine());
				confirmation = Validation.isOptionValid(0, 1, confirmation);
			}catch(NumberFormatException e) {
				System.out.println("Input should be a number.The letter or symbols are not allowed");
				continue;
			}catch(Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
			if(confirmation==1) {
				System.out.println("Thanks for visiting "+customer.getAccount().getUserName()+" Have a Nice day :)");
				System.exit(0);
			}
			break;
        }
        }
		}while(true);
	}
	
//	private static void showProduct() {
//		
//	}
	
//	public static void showUsersCart() {
//		
//	}
	
	public static void doAuthentication() {
		int confirmation = 0;
		int choice = 0;
		boolean loop = true;
		//BufferedReader reader = null;
		try{
			//reader = new BufferedReader(new InputStreamReader(System.in));
		do {
		System.out.println("+--------------------------------+");
        System.out.println("|            MENU                |");
        System.out.println("+--------------------------------+");
        System.out.println("| 1. Login                       |");
        System.out.println("| 2. Registration                |");
        System.out.println("| 3. Go Back                     |");
        System.out.println("| 4. Exit                        |");
        System.out.println("+--------------------------------+");
        System.out.println("Enter your choice");
		try {
			choice = Integer.parseInt(reader.readLine());
			choice = Validation.isOptionValid(1, 4, choice);
		}catch(InvalidMenuChoiceException e) {
			System.out.println(e.getMessage());
			continue;
		}catch(NumberFormatException e) {
			System.out.println("Input should be a number.The letter or symbols are not allowed");
			continue;
		}
		switch(choice) {
		case 1:{
			loginCustomer();
			break;
		}
		case 2:{
			registerCustomer();
			break;
		}
		case 3:{
			UngalKadaiTester.startMenu();
			break;
			//break;
		}
		case 4:{
			
			System.out.println("You have choosen to exit!!!");
			System.out.println(exitBox);
			System.out.println("Enter your choice");
			try {
				confirmation = Integer.parseInt(reader.readLine());
				confirmation = Validation.isOptionValid(0, 1, confirmation);
			}catch(NumberFormatException e) {
				System.out.println("Input should be a number.The letter or symbols are not allowed");
				continue;
			}catch(Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
			if(confirmation==1) {
				System.out.println("Thanks for visiting  Have a Nice day :)");
				System.exit(0);
			}
			break;
		}
//		default:{
//			System.out.println("You have entered a invalid input");
//			doAuthentication();
//		}
		}
		}while(loop);
		}catch(NumberFormatException e) {
			System.out.println("Numbers are only allowed between(1-4)");
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				reader.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		
	}
	
	public static void loginCustomer() {
		//BufferedReader reader = null;
		boolean flag = true;
		long mobileNumber = 0L;
		int retries = 3;
		int choice = 0;
		String password = "";
		int confirmation = 0;
		try{
			//reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("You have choosen to Login as a Customer");
			do {	
				flag = true;
				do {
					System.out.println("Enter your mobileNumber");
					try {
						mobileNumber = Long.parseLong(Validation.isMobileNumberValid(reader.readLine()));
					}catch(InvalidMobileNumberException |NumberFormatException e) {
						System.out.println(e.getMessage());
						continue;
					}
					flag = false;
				}while(flag);
				flag = true;
				do {
					System.out.println("Enter your password");
					try {
						password = reader.readLine();
						password = Validation.isPasswordValid(password);
					}catch(InvalidPasswordException e) {
						System.out.println(e.getMessage());
						continue;
					}
					flag = false;
				}while(flag);
				int c_id = new Customer().loginUser(mobileNumber, password);
				if(c_id>0) {
					//System.out.println(c_id);
					showCustomerMenu(getData(c_id));
					
					//return;
				}else {
					System.out.println("provided mobile number and password not matched");
					System.out.println("Would you like to try again(0-yes|1-No)");
					try {
					confirmation = Integer.parseInt(reader.readLine());
					}catch(NumberFormatException e) {
						System.out.println("You can enter only number(0 or 1)");
						continue;
					}
					if(confirmation==1) {
						doAuthentication();
						//return;
					}else if(confirmation<0 || confirmation>1) {
						System.out.println("you have provided a invalid option try again");
						System.out.println("1.Go back\n2.Exit");
						try {
						choice = Integer.parseInt(reader.readLine());
						}catch(NumberFormatException e) {
							System.out.println("You are allowed to enter only numbers.letters or symbols are not alloweded");
						}
						if(choice==1) {
							loginCustomer();
						}else if(choice==2) {
							System.out.println("Thanks for visiting  Have a Nice day :)");
							System.exit(0);
						}else {
							System.out.println("you have provided a invalid option!");
							doAuthentication();
						}
						//confirmation=0;
					}
					retries--;
				}
			}while(confirmation==0 && retries>0);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static Customer getData(int c_id) {
		Customer customer = null;
		try {
		PreparedStatement statement = DbmsConnection.getConnection().prepareStatement("select * from customer where c_id=?");
		statement.setInt(1,c_id);
		ResultSet resultSet = statement.executeQuery();
		//Customer(String firstName, String lastName, int customerId,String address,String email,long mobileNumber)
		if(resultSet.next()) {
			customer = new Customer(
					resultSet.getString("first_name"),
					resultSet.getString("last_name"),
					c_id,
					resultSet.getString("address"),
					resultSet.getString("email_id"),
					resultSet.getLong("mobile_no"),
					new Account(resultSet.getString("username"))
					);
		}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return customer;
	}
	
//	private static Customer getMyCart(Customer customer) {
//		
//		return customer;
//	}
	
	public static void registerCustomer() {
		//BufferedReader reader = null;
		boolean flag = true;
		String password = "";
		int choice = 0;
		String userName = "";
		String firstName = "",lastName = "";
		String address = "";
		Gender gender = null;
		Long mobileNumber = 0L;
		String emailId = "";
		boolean loop=true;
		AccountType accountType;
		AccountStatus accountStatus = AccountStatus.ACTIVE;  
		System.out.println("You have choosen to register as a Customer");
		do {
			
			try{
				//reader = new BufferedReader(new InputStreamReader(System.in));
//				System.out.println("Enter your id");
//				id = Integer.parseInt(reader.readLine());
				System.out.println("Enter your username");
				userName = reader.readLine();
				do {
				System.out.println("Enter your password");
				try {
				password = reader.readLine();
				password = Validation.isPasswordValid(password);
				}catch(InvalidPasswordException e) {
					System.out.println("Invalid Input Password [password should containt atleast one small or capital letter\n password should contain atleast one number\n,password should contain atleast one special symbol\npassword length should be minimum of 8]");
					continue;
				}
				flag = false;
				}while(flag);
				flag = true;
				System.out.println("Enter your firstName");
				firstName = reader.readLine();
				System.out.println("Enter your lastName");
				lastName = reader.readLine();
				System.out.println("Enter your address");
				address = reader.readLine();
				System.out.println("Choose the gender");
				do {
				System.out.println("1.Male\t\t2.Female\t\t3.Transgender");
				try {
				choice = Integer.parseInt(reader.readLine());
				choice = Validation.isOptionValid(1, 3, choice);
				}catch(NumberFormatException  e) {
					System.out.println("You should enter only the numbers between(1-3)");
					continue;
				}catch(InvalidMenuChoiceException e) {
					System.out.println(e.getMessage());
					continue;
				}
				switch(choice) {
					case 1:{
						gender = Gender.MALE;
						break;
					}
					case 2:{
						gender = Gender.FEMALE;
						break;
					}
					case 3:{
						gender = Gender.TRANSGENDER;
						break;
					}
				}
				flag = false;
				}while(flag);
				flag = true;				
				do {
					System.out.println("Enter your mobileNumber");
					try {
						mobileNumber = Long.parseLong(Validation.isMobileNumberValid(reader.readLine()));
					}catch(InvalidMobileNumberException e) {
						System.out.println(e.getMessage());
						continue;
					}
					flag = false;
				}while(flag);
				flag = true;
				do {
					System.out.println("Enter your emailId");
					try {
						emailId = reader.readLine();
						emailId  = Validation.isEmailIdValid(emailId);
					}catch(InvalidEmailException e) {
						System.out.println(e.getMessage());
						continue;
					}
					flag = false;
				}while(flag);
				accountType = AccountType.CUSTOMER;
				Customer customer = new Customer();
				//Customer(String firstName, String lastName, String customerName,Gender gender, int customerId,Address address,String email,String mobileNumber,Account account)
				boolean status = customer.registerUser(new Customer(firstName,lastName,gender,address,emailId,mobileNumber,new Account(userName,password,accountType,accountStatus)));
				if(status) {
					registerCart(mobileNumber);
					while(true) {
						System.out.println("Would you like to continue (1-yes|0-no)");
						try {
						choice = Integer.parseInt(reader.readLine());
						}catch(NumberFormatException e) {
							System.out.println("You are allowed to enter only numbers.No letters or special characters are allowed");
							continue;
						}
						if(choice==1) {
							loginCustomer();
							//return;
						}
						else if(choice<0 || choice>1) {
							System.out.println("You have chosen a invalid option");
						}else
							doAuthentication();
					}
					
				}else {
					System.out.println("Would you like to try again (1-yes|0-no)");
					choice = Integer.parseInt(reader.readLine());
					if(choice>1 || choice<0) {
						System.out.println("You have provided a invalid input");
						doAuthentication();
					}else if(choice==0)
						return;
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}while(loop);
	}
	
	private static void registerCart(long mobileNumber) {
		int c_id = 0;
		String sql  = "select c_id from customer where mobile_no=?";
		//System.out.println("I am inside");
		try {
			//System.out.println("I am inside try 1");
		PreparedStatement statement = DbmsConnection.getConnection().prepareStatement(sql);
		statement.setLong(1, mobileNumber);
		ResultSet resultSet = statement.executeQuery();
		//System.out.println("I am inside try 2");
		if(resultSet.next()) {
			//System.out.println("I am inside if");
			c_id = resultSet.getInt("c_id");
			statement = DbmsConnection.getConnection().prepareStatement("insert into cart values(cart_sequence.nextval,?)");
			statement.setInt(1, c_id);
			int r= statement.executeUpdate();
			System.out.println(r);
			//registerOrder(c_id);
		}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	private static void showProfileMenu(Customer customer) {
		int choice = 0,confirmation = 0;
		do {
			System.out.println("┌───────────────── Profile Menu ─────────────────┐");
			System.out.println("│ 1. Show profile                                │");
			System.out.println("│ 2. Update profile                              │");
			System.out.println("│ 3. Go Back                                     │");
			System.out.println("│ 4. Exit                                        │");
			System.out.println("└────────────────────────────────────────────────┘");

		try {
			choice = Integer.parseInt(reader.readLine());
			choice = Validation.isOptionValid(1, 4, choice);
		}catch(IOException | InvalidMenuChoiceException e) {
			System.out.println(e.getMessage());
			continue;
		}catch(NumberFormatException e) {
			System.out.println("Numbers are only allowed.Letters or symbols are not allowed");
			continue;
		}
		switch(choice) {
		case 1:{
			System.out.println("You have choosen the show profile option");
			System.out.println("-".repeat(200));
			System.out.println(customer);
			System.out.println("-".repeat(200));
			break;
		}
		case 2:{
			System.out.println("You have choosen to update profile option");
			
		}
		}
		}while(true);
		
		
	}
	
//	private static void registerOrder(int c_id) {
//		String sql = "insert into "
//	}
	
	/*
	 * 23	pirat@007	1278934	pirat	lincon	san francisco	MALE	9442106465	pirat@gmail.com	CUSTOMER	ACTIVE
		22	gowtham	gowtham@17	gowthamun	G	chinnathirupathy salem-636008	MALE	8883093515	gowthag@gmail.com	CUSTOMER	BLOCKED
	 * 
	 * 
	 * */
	
	

}
