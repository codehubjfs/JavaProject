package com.issuesystem.category;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.issuesystem.dbconnection.*;
import com.issuesystem.subcategory.*;
import com.issuesystem.userdefinedxception.InvalidNumberException;
public class Category {
	
	
	//CHECKING CATEGORY INPUT
	public static int categoryCheck(int choice,Scanner sc) {
		
		boolean inputflag=true;
		while(inputflag) {
		
		try {
			System.out.print("Enter your choice:");
			choice=Integer.parseInt(sc.next());
			if(choice!=1 &&choice!=2 &&choice!=3&&choice!=4) {
				throw new InvalidNumberException("You have entered invalid number");
			}
			inputflag=false;
		}
		
		catch (NumberFormatException e) {
			System.out.println("---------------------------------------------------------");
            System.out.println("   !You have enter a character,Please enter a number!  ");
            System.out.println("---------------------------------------------------------");
            System.out.println();
            sc.nextLine(); 
		}
		catch (InvalidNumberException e) {
			System.out.println("--------------------------------------------------------");
            System.out.println("!"+e.getMessage()+"! ");
            System.out.println("--------------------------------------------------------");
        }
		}
		return choice;
		
	}
	
	//DISPLAYING THE CATEGORY NAME FROM TABLE
	
	public static void state() throws SQLException{
		String sql="select category_id,category_name from category";
		PreparedStatement stmt = DBConnection.getDBConnextion().prepareStatement(sql);
		ResultSet result = stmt.executeQuery();
		int i=1;
		while(result.next()) {
			int stored_categoryId=result.getInt("category_id");
			String stored_categoryName=result.getString("category_name");
			
			System.out.println(i+". "+stored_categoryName);
			i++;
		}
	}

	
	//CHOOSING THE CATEGORY
	public static void chooseCategory(Scanner sc,String username) throws SQLException {

		boolean categoryFlag=true;
		while(categoryFlag) {
			
				state();
			try {
				int key=0;
				System.out.println("4. Back");
				int choice=0;
				choice=categoryCheck(choice,sc);
				
				
				
				if(choice==1 ||choice==2||choice==3 ||choice==4) {
					switch(choice) {
					case 1:
						key=5001;
						System.out.println("------------------------");
						System.out.println("|      Academic        |");
						System.out.println("------------------------");
						Academic.academicdetails(key,username,sc);
						System.out.println("-----------------");
						break;
					case 2:
						
						key=5002;
						System.out.println("------------------------");
						System.out.println("|      Infrastructure  |");
						System.out.println("------------------------");
						Infrastructure.infrastructuredetails(key,username,sc);
						System.out.println("-----------------");
						break;
					case 3:
						key=5003;
						System.out.println("------------------------");
						System.out.println("|      Adminstrative   |");
						System.out.println("------------------------");
						Adminstrative.adminstractivedetails(key,username,sc);
						System.out.println("-----------------");
						break;
					case 4:
						categoryFlag=false;
						break;
					
					
					}
				}
	
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
		
		
		
	}

}
