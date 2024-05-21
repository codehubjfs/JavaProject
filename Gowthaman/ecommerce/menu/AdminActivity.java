package com.ecommerce.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ecommerce.customizedexceptions.InvalidMenuChoiceException;
import com.ecommerce.customizedexceptions.InvalidPasswordException;
import com.ecommerce.customizedexceptions.Validation;
import com.ecommerce.users.Admin;
import com.ecommerce.users.Customer;
import com.ecommerce.users.DbmsConnection;
import com.ecommerce.users.account.AccountStatus;
import com.ungalkadai.components.Category;
import com.ungalkadai.components.SubCategory;
import com.ungalkadai.tester.UngalKadaiTester;

public class AdminActivity {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	static StringBuilder exitBox = new StringBuilder();
	
	static {
        exitBox.append("+----------------------------------+\n");
        exitBox.append("|   Are you sure to Exit?         |\n");
        exitBox.append("|  (1-yes | 0-no)                 |\n");
        exitBox.append("+----------------------------------+\n");
	}
	
	public static void showAdminMenu() {
		int choice = 0;
		int confirmation = 0;
		try{
		do { 
			System.out.println("+---------------------------------+");
	        System.out.println("|           ADMIN MENU            |");
	        System.out.println("+---------------------------------+");
	        System.out.println("| 1. Add another Admin            |");
	        System.out.println("| 2. User Management              |");
	        System.out.println("| 3. Go Back                      |");
	        System.out.println("| 4. Manage products              |");
	        System.out.println("| 5. Exit                         |");
	        System.out.println("+---------------------------------+");
	        System.out.println("Enter your choice");
			try {
			choice = Integer.parseInt(reader.readLine());
			choice = Validation.isOptionValid(1, 5, choice);
			}catch(NumberFormatException e) {
				System.out.println("You should provide only number(1-5)");
				continue;
			}catch(InvalidMenuChoiceException e) {
				System.out.println(e.getMessage());
			}
			switch(choice) {
			case 1:{
				addAdmin();
				//continue;
				break;
			}
			case 2:{
				showUserManagementMenu();
				break;
			}
			case 3:{
				doAdminAuthentication();
				break;
			}
			case 4:{
				
				break;
			}
			case 5:{
				System.out.println(exitBox);
				try {
					confirmation = Integer.parseInt(reader.readLine());
					confirmation = Validation.isOptionValid(0, 1, confirmation);
				}catch(NumberFormatException e) {
					System.out.println("You are allowed to enter only numbers(1 or 0)");
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
			}
		}while(true);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
	public static void doAdminAuthentication() {
		int choice = 0,	confirmation =0;
		try{
		do {
			System.out.println("+-----------------------------+");
	        System.out.println("|           ADMIN             |");
	        System.out.println("+-----------------------------+");
	        System.out.println("| 1. Login                    |");
	        System.out.println("| 2. Go Back                  |");
	        System.out.println("| 3. Exit                     |");
	        System.out.println("+-----------------------------+");
	        System.out.println("Enter your choice");
		try {
		choice = Integer.parseInt(reader.readLine());
		choice = Validation.isOptionValid(1, 2, choice);
		}catch(NumberFormatException e) {
			System.out.println("Option should be in number and between(1-3)");
			continue;
		}catch(InvalidMenuChoiceException e) {
			System.out.println(e.getMessage());
			continue;
		}
		switch(choice) {
		case 1:{
			loginAdmin();
			break;
		}
		case 2:{
			UngalKadaiTester.startMenu();
			break;
		}
		case 3:{
			System.out.println(exitBox);
			try {
				confirmation = Integer.parseInt(reader.readLine());
				confirmation = Validation.isOptionValid(0, 1, confirmation);
			}catch(NumberFormatException e) {
				System.out.println("You are allowed to enter only numbers(1 or 0)");
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
		}
		}while(true);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void showUserManagementMenu() {
		int choice = 0;
		int confirmation = 0;
		try {
			do {
				System.out.println("+--------------------------------+");
		        System.out.println("|       USER MANAGEMENT          |");
		        System.out.println("+--------------------------------+");
		        System.out.println("| 1. Block Customer              |");
		        System.out.println("| 2. Activate Customer           |");
		        System.out.println("| 3. Block Seller                |");
		        System.out.println("| 4. Activate Seller             |");
		        System.out.println("| 5. Go Back                     |");
		        System.out.println("| 6. Exit                        |");
		        System.out.println("+--------------------------------+");
				System.out.println("Enter your choice : ");
				try {
					choice = Integer.parseInt(reader.readLine());
					choice = Validation.isOptionValid(1, 6, choice);
				}catch(NumberFormatException e) {
					System.out.println("Option should be in number and between(1-6)");
					continue;
				}catch(InvalidMenuChoiceException e) {
					System.out.println(e.getMessage());
					continue;
				}
				switch(choice) {
				case 1:{
					System.out.println("You have choosen to Block a customer Account");
					restrictCustomer();
					break;
				}
				case 2:{
					System.out.println("You have choosen to activate a customer Account");
					activateCustomer();
					break;
				}
				case 3:{
					System.out.println("You have choosen to Block a Seller Account");
					restrictSeller();
					break;
				}
				case 4:{
					System.out.println("You have choosen to Activate Account");
					activateSeller();
					break;
				}
				case 5:{
					System.out.println("You are heading to previous menu");
					showAdminMenu();
					break;
				}
				case 6:{
					System.out.println("You have choosen to exit!!!");
					System.out.println(exitBox);
					try {
						confirmation = Integer.parseInt(reader.readLine());
						confirmation = Validation.isOptionValid(0, 1, confirmation);
					}catch(NumberFormatException e) {
						System.out.println("You are allowed to enter only numbers(1 or 0)");
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
				
				}
			}while(true);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
		public static void showProductManagementMenu() {
			int choice = 0,confirmation=0;
			try {
			do {
			System.out.println("+--------------------------------+");
	        System.out.println("|       PRODUCT MANAGEMENT       |");
	        System.out.println("+--------------------------------+");
	        System.out.println("| 1. Add a Category              |");
	        System.out.println("| 2. Add a Subcategory           |");
	        System.out.println("| 3. Go Back                     |");
	        System.out.println("| 4. Exit                        |");
	        System.out.println("+--------------------------------+");
	        System.out.println("Enter your choice");
			try {
			choice = Integer.parseInt(reader.readLine());
			choice = Validation.isOptionValid(1, 4, choice);
			}catch(NumberFormatException e) {
				System.out.println("You are allowed to enter only numbers between(1-4)");
				continue;
			}catch(InvalidMenuChoiceException e) {
				System.out.println(e.getMessage());
				continue;
			}
			switch(choice) {
				case 1:{
					System.out.println("You have choosen to Add a category");
					addNewCategory();
					break;
				}
				case 2:{
					System.out.println("You have choosen to Add a SubCategory");
					addNewSubCategory();
					break;
				}
				case 3:{
					System.out.println("You have choosen to Go Back");
					showUserManagementMenu();
					break;
				}
				case 4:{
					System.out.println("You have choosen to exit!!!");
					System.out.println(exitBox);
					try {
						confirmation = Integer.parseInt(reader.readLine());
						confirmation = Validation.isOptionValid(0, 1, confirmation);
					}catch(NumberFormatException e) {
						System.out.println("You are allowed to enter only numbers(1 or 0)");
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
			}
			}while(true);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
	
	public static void addAdmin() {
		String adminName = "";
		String password = "";
		int confirmation = 0;
		try{
			System.out.println("Do you really need to add another admin!!");
			System.out.println("+-----------------------------+");
	        System.out.println("|         CONFIRMATION        |");
	        System.out.println("+-----------------------------+");
	        System.out.println("| Enter (0-NO | 1-YES)        |");
	        System.out.println("+-----------------------------+");
	        System.out.println("Enter your choice");
			try {
			confirmation = Validation.isOptionValid(0, 1,Integer.parseInt(reader.readLine()));
			}catch(NumberFormatException e) {
				System.out.println("Input should be a number.The letter or symbols are not allowed");
				showAdminMenu();
			}catch(InvalidMenuChoiceException e) {
				System.out.println(e.getMessage());
				showAdminMenu();
			}
			if(confirmation==0) {
				System.out.println("You are heading to your menu");
				return;
			}else {
				System.out.println("Enter the username");
				adminName = reader.readLine();
				System.out.println("Enter your password");
				password = reader.readLine();
				boolean flag = new Admin().createAnotherAdmin(new Admin(adminName,password));
				if(flag) {
					System.out.println("New admin has been created successfully");
				}else {
					System.out.println("Some error occured unable to add another admin");
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void restrictCustomer() {
		try {
			ResultSet resultSet = getAllUsers("customer",AccountStatus.ACTIVE);
			while(resultSet.next()) {
				System.out.println(""+resultSet.getInt("c_id")+" "+resultSet.getString("username")+" "+resultSet.getString("account_status")+"");
			}
			System.out.println("Enter the c_id of the customer");
			try {
			int c_id = Integer.parseInt(reader.readLine());
			new Admin().blockUser(c_id,1);
			}catch(NumberFormatException e) {
				System.out.println("You should enter only numbers");
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void activateCustomer() {
		try {
			ResultSet resultSet = getAllUsers("customer",AccountStatus.BLOCKED);
			while(resultSet.next()) {
				System.out.println(""+resultSet.getInt("c_id")+" "+resultSet.getString("username")+" "+resultSet.getString("account_status")+"");
			}
			System.out.println("Enter the c_id of the customer");
			try {
			int c_id = Integer.parseInt(reader.readLine());
			new Admin().activateUser(c_id,1);
			}catch(NumberFormatException e) {
				System.out.println("You should enter only numbers");
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}catch(IOException e ) {
			System.out.println(e.getMessage());
		}
	}
	
	private static ResultSet getAllUsers(String userType,AccountStatus accountStatus) throws SQLException {
		String sql = "select * from "+userType+" where account_status='"+accountStatus.toString()+"'";
		PreparedStatement statement = DbmsConnection.getConnection().prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		return resultSet;
	}
	
	public static void restrictSeller() {
		try {
			ResultSet resultSet = getAllUsers("vendor",AccountStatus.ACTIVE);
			while(resultSet.next()) {
				System.out.println(""+resultSet.getInt("v_id")+" "+resultSet.getString("username")+" "+resultSet.getString("account_status")+"");
			}
			System.out.println("Enter the v_id of the Seller");
			try {
			int v_id = Integer.parseInt(reader.readLine());
			new Admin().blockUser(v_id,2);
			}catch(NumberFormatException e) {
				System.out.println("You should enter only numbers");
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}catch(IOException e ) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void activateSeller() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(System.in));
			ResultSet resultSet = getAllUsers("vendor",AccountStatus.BLOCKED);
			while(resultSet.next()) {
				System.out.println(""+resultSet.getInt("v_id")+" "+resultSet.getString("username")+" "+resultSet.getString("account_status")+"");
			}
			System.out.println("Enter the v_id of the Seller");
			try {
			int v_id = Integer.parseInt(reader.readLine());
			new Admin().activateUser(v_id,2);
			}catch(NumberFormatException e) {
				System.out.println("You should enter only numbers");
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}catch(IOException e ) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void loginAdmin() {
		boolean flag = true;
		int retries = 3;
		String userName = "";
		String password = "";
		int confirmation = 0;
		BufferedReader reader = null;
		try{
			reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("You have choosen to Login as a Admin");
			do {
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
				boolean valid = new Admin().loginAdmin(new Admin(userName, password));
				if(valid) {
					showAdminMenu();
					//return;
				}else {
					retries--;
					if(retries==0)
						break;
					System.out.println("provided username and password not matched");
					System.out.println("There is more "+retries+" retry");
					System.out.println("Would you like to try again(0-yes|1-No)");
					try {
					confirmation = Integer.parseInt(reader.readLine());
					}catch(NumberFormatException e) {
						System.out.println(e.getMessage());
						continue;
					}
					if(confirmation==1) {
						UngalKadaiTester.startMenu();
						///return;
					}else if(confirmation<0 || confirmation>1) {
						System.out.println("you have provided a invalid option try again");
						confirmation=0;
					}
					
				}
			}while(confirmation==0 && retries>0);
			doAdminAuthentication();
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
		
	}
	
	public static void addNewCategory() {
		String categoryName = "";
		boolean flag = true;
		do {
		System.out.println("Enter the new category name you need to add");
		try {
		categoryName = reader.readLine();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			continue;
		}
		Category category = new Category();
		category.setCategoryName(categoryName);
		new Admin().addCategory(category);
		flag = false;
		}while(flag);
	}
	
	public static void addNewSubCategory() {
		boolean flag = true;
		int choice = 0;
		String subCategoryName = "";
		int confirmation = 0;
		SubCategory subCategory = new SubCategory();
		List<Category> categories = getAllCategory();
		do {
			categories.forEach((c)->System.out.println((categories.indexOf(c)+1)+" "+c));
			System.out.println("Enter the category in which the subCategory comes");
			try {
				choice = Integer.parseInt(reader.readLine());
				choice = Validation.isOptionValid(1, categories.size(), choice);
			}catch(NumberFormatException e) {
				System.out.println("Numbers are only allowed.Letters or symbols are not allowed");
				continue;
			}catch(InvalidMenuChoiceException | IOException e) {
				System.out.println(e.getMessage());
				continue;
			}
			
			//new Admin().addSubCategory(new SubCategory());
			flag = false;
		}while(flag);
		subCategory.setCategory(categories.get(choice));
		System.out.println("Enter the New Sub Category Name");
		try {
			subCategoryName = reader.readLine();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		subCategory.setSubCategoryName(subCategoryName);
		new Admin().addSubCategory(subCategory);
	}
	
	static List<Category> getAllCategory(){
		List<Category> categoryList = new ArrayList<>();
		String sql  = "select * from category";
		try {
			Statement statement = DbmsConnection.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				categoryList.add(new Category(
						resultSet.getInt("category_id"),
						resultSet.getString("category_name")
						));
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return categoryList;
	}
}
