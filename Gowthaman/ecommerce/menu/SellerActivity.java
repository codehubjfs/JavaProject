package com.ecommerce.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.ecommerce.customizedexceptions.InvalidAadharNumberException;
import com.ecommerce.customizedexceptions.InvalidEmailException;
import com.ecommerce.customizedexceptions.InvalidMenuChoiceException;
import com.ecommerce.customizedexceptions.InvalidMobileNumberException;
import com.ecommerce.customizedexceptions.InvalidPasswordException;
import com.ecommerce.customizedexceptions.InvalidSubCategoryException;
import com.ecommerce.customizedexceptions.Validation;
import com.ecommerce.users.Customer;
import com.ecommerce.users.DbmsConnection;
import com.ecommerce.users.Gender;
import com.ecommerce.users.Vendor;
import com.ecommerce.users.account.Account;
import com.ecommerce.users.account.AccountStatus;
import com.ecommerce.users.account.AccountType;
import com.ungalkadai.components.Category;
import com.ungalkadai.components.Product;
import com.ungalkadai.components.ProductStatus;
import com.ungalkadai.components.SubCategory;
import com.ungalkadai.components.VerificationStatus;
import com.ungalkadai.tester.UngalKadaiTester;

public class SellerActivity {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
	static StringBuilder exitBox = new StringBuilder();
	
	static {
        exitBox.append("+----------------------------------+\n");
        exitBox.append("|   Are you sure to Exit?         |\n");
        exitBox.append("|  (1-yes | 0-no)                 |\n");
        exitBox.append("+----------------------------------+\n");
	}
	
	//static Scanner scanner = new Scanner(System.in);
	
	public static void showSellerMenu(Vendor vendor) {
		int choice = 0,confirmation=0;
		
		do {
			System.out.println("+-----------------------------+");
	        System.out.println("|        SELLER MENU          |");
	        System.out.println("+-----------------------------+");
	        System.out.println("| 1. Manage Products          |");
	        System.out.println("| 2. Manage Orders            |");
	        System.out.println("| 3. Go Back                  |");
	        System.out.println("| 4. Exit                     |");
	        System.out.println("+-----------------------------+");
	        System.out.println("Enter your option");
		try {
			choice = Integer.parseInt(reader.readLine());
			choice = Validation.isOptionValid(1, 4, choice);
			}catch(IOException | InvalidMenuChoiceException e) {
				System.out.println(e.getMessage());
				continue;
			}catch(NumberFormatException e) {
				System.out.println("Input should be a number.The letter or symbols are not allowed");
				continue;
			}
		switch(choice) {
			case 1:{
				System.out.println("You have chosen to manage the products");
				manageProductMenu(vendor);
				break;
			}
			case 2:{
				System.out.println("You have chosen to manage the orders");
				break;
			}
			case 3:{
				System.out.println("You are heading to your previous menu");
				doSellerAuthentication();
				break;
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
					System.out.println("Thanks for visiting "+vendor.getAccount().getUserName()+" Have a Nice day:)");
					System.exit(0);
				}
				break;
			}
		}
		}while(true);
	}
	
	private static void manageProductMenu(Vendor vendor) {
		int choice=0,confirmation=0;
		do {
			System.out.println("+-------------------------------+");
	        System.out.println("|       MANAGE PRODUCT          |");
	        System.out.println("+-------------------------------+");
	        System.out.println("| 1. Add Product                |");
	        System.out.println("| 2. Update Product             |");
	        System.out.println("| 3. Go Back                    |");
	        System.out.println("| 4. Exit                       |");
	        System.out.println("+-------------------------------+");
	        System.out.println("Enter your option ");
		try {
			choice = Integer.parseInt(reader.readLine());
			choice = Validation.isOptionValid(1, 4, choice);
			}catch(IOException | InvalidMenuChoiceException e) {
				System.out.println(e.getMessage());
				continue;
			}catch(NumberFormatException e) {
				System.out.println("Input should be a number.The letter or symbols are not allowed");
				continue;
			}
		switch(choice) {
			case 1:{
				System.out.println("You have choosen to Add product");
				addProduct(vendor);
				break;
			}
			case 2:{
				System.out.println("You have choosen to update product");
				updateProduct(vendor);
				break;
			}
			case 3:{
				System.out.println("You have choosen to go back...");
				showSellerMenu(vendor);
				break;
			}
			case 4:{
				System.out.println("You have choosen to exit!!!");
				System.out.println(exitBox);
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
					System.out.println("Thanks for visiting "+vendor.getAccount().getUserName()+" Have a Nice day:)");
					System.exit(0);
				}
				break;
			}
		}
		}while(true);
		
	}
	
	public static void doSellerAuthentication() {
		int confirmation=0;
		//BufferedReader reader = null;
		try{
		//reader = new BufferedReader(new InputStreamReader(System.in));
		do {
		int choice = 0;
		System.out.println("+-----------------------------+");
        System.out.println("|            SELLER           |");
        System.out.println("+-----------------------------+");
        System.out.println("| 1. Login                    |");
        System.out.println("| 2. Registration             |");
        System.out.println("| 3. Go Back                  |");
        System.out.println("| 4. Exit                     |");
        System.out.println("+-----------------------------+");
        System.out.println("Enter your option ");
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
			loginSeller();
			break;
		}
		case 2:{
			registerSeller();
			break;
		}
		case 3:{
			UngalKadaiTester.startMenu();
			break;
		}
		case 4:{
			System.out.println("You have choosen to exit!!!");
			System.out.println(exitBox);
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
				System.out.println("Thanks for visiting  Have a Nice day:)");
				System.exit(0);
			}
			break;
		}
		}
		}while(true);
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
	
	public static void loginSeller() {
		long mobileNumber = 0L;
		boolean flag = true;
		int retries = 3;
		String password = "";
		int confirmation = 0;
		try{
			System.out.println("You have choosen to Login as a Seller");
			do {
				flag = true;
				do {
				System.out.println("Enter your mobile number");
				try {
				mobileNumber = Long.parseLong(Validation.isMobileNumberValid(reader.readLine()));
				}catch(NumberFormatException e) {
					System.out.println("You should only numbers letters or special symbols are not allowed");
					continue;
				}catch(InvalidMobileNumberException e) {
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
				int v_id = new Vendor().loginUser(mobileNumber, password);
				if(v_id>0) {
					//System.out.println(v_id);
					showSellerMenu(getData(v_id));
				}else {
					System.out.println("provided mobile number and password not matched");
					System.out.println("Would you like to try again(0-yes|1-No)");
					try {
					confirmation = Integer.parseInt(reader.readLine());
					}catch(NumberFormatException e) {
						System.out.println("Only numbers are allowed.letters and symbols are not allowed");
					}
					if(confirmation==1) {
						UngalKadaiTester.startMenu();
						return;
					}else if(confirmation<0 || confirmation>1) {
						System.out.println("you have provided a invalid option try again");
					}
					retries--;
				}
			}while(confirmation==0 && retries>0);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void registerSeller() {
		//BufferedReader reader = null;
		boolean flag = true;
		String password = "";
		int choice = 0;
		String registerNumber = "";
		Long aadharNumber = 0L;
		String userName = "";
		String address = "";
		Long mobileNumber = 0L;
		String emailId = "";
		boolean loop=true;
		AccountType accountType;
		AccountStatus accountStatus = AccountStatus.ACTIVE;  
		System.out.println("You have choosen to register as a Seller");
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
						System.out.println(e.getMessage());
						continue;
					}
					flag = false;
				}while(flag);
				flag = true;
				System.out.println("Enter your address");
				address = reader.readLine();
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
				System.out.println("Enter your registerNumber");
				registerNumber = reader.readLine();
				do {
				System.out.println("Enter your aadharNumber");
				try {
				aadharNumber = Long.parseLong(Validation.isAadharNumberValid(reader.readLine()));
				}catch(InvalidAadharNumberException e) {
					System.out.println(e.getMessage());
					continue;
				}
				flag = false;
				}while(flag);
				flag = true;
//				if(mobileNumber.toString().length()!=10) {
//					System.out.println("Provided mobile Number is not a valid");
//					continue;
//				}
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
				accountType = AccountType.SELLER;
				Vendor vendor = new Vendor();
				//Customer(String firstName, String lastName, String customerName,Gender gender, int customerId,Address address,String email,String mobileNumber,Account account)
				boolean status = vendor.registerUser(new Vendor(registerNumber,new Account(userName,password,accountType,accountStatus),aadharNumber,emailId,mobileNumber,address));
				if(status) {
					do {
					System.out.println("Would you like to continue (1-yes|0-no)");
					try {
					choice = Integer.parseInt(reader.readLine());
					}catch(NumberFormatException e) {
						System.out.println("You should enter only number(0 or 1)");
					}
					if(choice==1) {
						loginSeller();
					}
					else if(choice<0 || choice>1) {
						System.out.println("You have chosen a invalid option");
					}else {
						doSellerAuthentication();
					}
					}while(true);
					//return;
				}else {
					System.out.println("Would you like to try again (1-yes|0-no)");
					try {
					choice = Integer.parseInt(reader.readLine());
					}catch(NumberFormatException e) {
						System.out.println("Numbers are only allowed.you should not enter letter or symbols");
						doSellerAuthentication();
					}
					if(choice>1 || choice<0) {
						System.out.println("You have provided a invalid input");
						doSellerAuthentication();
					}else if(choice==0)
						doSellerAuthentication();
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
			}
			
		}while(true);
	}
	////Product(double productPrice,String productName, String brand, int quantity, String subtitle, String description, String warranty,
	//Vendor vendor, SubCategory subCategrory, ProductStatus productStatus)
	private static void addProduct(Vendor vendor) {
		boolean flag = true;
		int warrantyCheck = 0;
		int duration = 0;
		int warrantyTime = 0;
		Map<Integer,SubCategory> subCategories = getAllSubCategoryMap();
		double productPrice = 0;
		String productName = "";
		String brand = "";
		int quantity = 0;
		String subTitle = "";
		String description = "";
		String warranty = "";
		int subCategoryid = 0;
		SubCategory subCategory = null;
		
		ProductStatus productStatus = ProductStatus.IN_STOCK;
		do {
			try {
				System.out.println("Enter the product name");
				productName = reader.readLine();
				System.out.println("Enter the product subTitle");
				subTitle = reader.readLine();
				do {
					subCategories.forEach((k,v)->System.out.println(k+" "+v));
					System.out.println("Choose the product subcategory");
					try {
						subCategoryid = Integer.parseInt(reader.readLine());
						subCategoryid = Validation.isSubCategoryValid(subCategoryid, subCategories);
					}catch(NumberFormatException e) {
						System.out.println("Numbers are only allowed.letters and symbols are not allowed");
						continue;
					}catch(InvalidSubCategoryException e) {
						System.out.println(e.getMessage());
						continue;
					}
					flag = false;
				}while(flag);
				flag = true;
				subCategory = subCategories.get(subCategoryid);
				System.out.println(subCategory);
				System.out.println("enter the product description");
				description = reader.readLine();
				System.out.println("Enter the product brand");
				brand = reader.readLine();
				//flag = true;
				do {
				System.out.println("Enter the quantity");
				try {
				quantity = Integer.parseInt(reader.readLine());
				}catch(NumberFormatException e) {
					System.out.println(e.getMessage());
					continue;
				}
				}while(flag);
				flag = true;
				do {
					System.out.println("Does this product has warranty(1-Yes|0-No)");
					try {
					warrantyCheck = Integer.parseInt(reader.readLine());
					warrantyCheck = Validation.isOptionValid(0, 1, warrantyCheck);
					}catch(NumberFormatException e) {
						System.out.println("Numbers are only allowed.letters and symbols are not allowed");
						continue;
					}catch(InvalidMenuChoiceException e) {
						System.out.println(e.getMessage());
						continue;
					}
					flag = false;
				}while(flag);
				flag = true;
				if(warrantyCheck==1) {
					do {
						System.out.println("Choose the warranty duration type(1-Months|2-year)");
						try {
							duration = Integer.parseInt(reader.readLine());
							duration = Validation.isOptionValid(1, 2, duration);
						}catch(NumberFormatException e) {
							System.out.println("Numbers are only allowed.letters and symbols are not allowed");
							continue;
						}catch(InvalidMenuChoiceException e) {
							System.out.println(e.getMessage());
							continue;
						}
						if(duration == 1) {
							try {
								System.out.println("Enter the no of months for warranty(1-12)");
								warrantyTime = Integer.parseInt(reader.readLine());
								warrantyTime = Validation.isOptionValid(1, 12, warrantyTime);
							}catch(NumberFormatException e) {
								System.out.println("Numbers are only allowed.letters and symbols are not allowed");
								continue;
							}catch(InvalidMenuChoiceException e) {
								System.out.println(e.getMessage());
								continue;
							}
							warranty = warrantyTime+" MONTHS";
						}else {
							try {
							System.out.println("Enter the no of years for warranty(1-3)");
							warrantyTime = Integer.parseInt(reader.readLine());
							warrantyTime = Validation.isOptionValid(1, 3, warrantyTime);
						}catch(NumberFormatException e) {
							System.out.println("Numbers are only allowed.letters and symbols are not allowed");
							continue;
						}catch(InvalidMenuChoiceException e) {
							System.out.println(e.getMessage());
							continue;
						}
							warranty = warrantyTime+" YEARS";
						}
						flag = false;
					}while(flag);
				}else {
					warranty = "NOT AVAILABLE";
				}
				System.out.println("Enter the product price");
				try {
					productPrice = Double.parseDouble(reader.readLine());
				}catch(NumberFormatException e) {
					System.out.println(e.getMessage());
				}
				//Product(double productPrice,String productName, String brand, int quantity, String subtitle, String description, String warranty,
				//Vendor vendor, SubCategory subCategrory, ProductStatus productStatus)
				flag = vendor.addProduct(new Product(productPrice,productName,brand,quantity,
						subTitle,description,warranty,vendor,subCategory,productStatus,VerificationStatus.VERIFIED));
				if(flag) {
					manageProductMenu(vendor);
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}while(true);
		
	}
	
	public static void updateProduct(Vendor vendor) {
		int choice = 0,confirmation = 0;
		boolean flag = true;
		Map<Integer,Product> products = getAllProductMap(vendor);
		try {
			do {
				System.out.println("The product of Seller "+vendor.getAccount().getUserName()+" are");
				products.forEach((k,v)->System.out.println("S.no : "+k+" "+v+"| Quantity : "+v.getQuantity()+" |"));
				System.out.println("Enter your choice : ");
				try {
					choice = Integer.parseInt(reader.readLine());
					if(!products.containsKey(choice)) {
						System.out.println("Given product s.no is not available");
						continue;
					}
				}catch(NumberFormatException e) {
					System.out.println("Numbers are only allowed.Letters or symbols are not allowed");
					continue;
				}
				flag = false;
			}while(flag);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		//System.out.println("You ")
	}
	
	public static Vendor getData(int v_id) {
		Vendor vendor = null;
		try {
		PreparedStatement statement = DbmsConnection.getConnection().prepareStatement("select * from vendor where v_id=?");
		statement.setInt(1,v_id);
		ResultSet resultSet = statement.executeQuery();
		//Vendor(int vendorId,String registeredNumber,Account account,long aadharNumber,String email,long mobileNumber,String address) 
		if(resultSet.next()) {
			vendor = new Vendor(
					v_id,
					resultSet.getString("reg_no"),
					new Account(resultSet.getString("username")),
					resultSet.getLong("aadhar_no"),
					resultSet.getString("email"),
					resultSet.getLong("mobile_no"),
					resultSet.getString("address")
					);
		}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return vendor;
	}
	
	public static Map<Integer,SubCategory> getAllSubCategoryMap(){
		Map<Integer,SubCategory> categoryList = new HashMap<>();
		int i = 1;
		String sql  = "select s.subcategory_id,s.subcategory_name,c.category_name,c.category_id from subcategory s,category c where s.category_id=c.category_id";
		try {
			Statement statement = DbmsConnection.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				categoryList.put(i,new SubCategory(
						resultSet.getInt("subcategory_id"),
						resultSet.getString("subcategory_name"),
						new Category(
								resultSet.getInt("category_id"),
								resultSet.getString("category_name")
								)
						));
				i++;
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return categoryList;
	}
	
	public static Map<Integer,Product> getAllProductMap(Vendor vendor) {
		String sql = "select p.p_id,p.p_description,p.warranty,p.p_name,p.p_quantity,p.p_subtitle,p.p_price,p.p_brand from product p JOIN vendor v ON p.v_id = v.v_id WHERE p.v_id = ?";
		Map<Integer,Product> products = new HashMap<>();
		int i=1;
		try {
			PreparedStatement statement = DbmsConnection.getConnection().prepareStatement(sql);
			statement.setInt(1,vendor.getVendorId());
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				//System.out.println("Inside the while statement");
				products.put(i, new Product(
						resultSet.getString("p_name"),
						resultSet.getInt("p_id"),
						resultSet.getDouble("p_price"),
						resultSet.getString("p_brand"),
						resultSet.getInt("p_quantity"),
						resultSet.getString("p_subtitle"),
						resultSet.getString("p_description"),
						resultSet.getString("warranty")));
				i++;
			}
		}catch(SQLException e) {
			//System.out.println(products);
			System.out.println(e.getMessage());
		}
		return products;
	}

}
