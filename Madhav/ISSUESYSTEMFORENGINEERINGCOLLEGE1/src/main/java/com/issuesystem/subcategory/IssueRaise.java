package com.issuesystem.subcategory;

import java.sql.SQLException;
import java.util.Scanner;

import com.issuesystem.category.Category;
import com.issuesystem.userdefinedxception.EmailException;
import com.issuesystem.userdefinedxception.InvalidInputException;
import com.issuesystem.userdefinedxception.InvalidNumberException;
import com.issuesystem.users.Profile;
import com.issuesytem.issue.ViewIssue;

public class IssueRaise {
	
	public static void chooseMenu(Scanner sc,String username,int role) throws SQLException, EmailException, InvalidInputException {
		
		boolean menuFlag=true;
		
		while(menuFlag) {
			System.out.println("1.To Raise Issue "+"\n2.To View Issue"+"\n3.Edit profile"+"\n4.Back");
			System.out.print("Enter your choice: ");
			
			try {
				int category=Integer.parseInt(sc.next());
				if(category!=1 && category!=2 && category!=3 && category!=4) {
				   throw new InvalidNumberException("You have entered invalid number");
				
				}
				else {		
					switch(category){
					case 1:
						System.out.println("-----------------------");
						System.out.println("|  Choose a category  |");
						System.out.println("-----------------------");
						Category.chooseCategory(sc,username);
						
						break;
					case 2:
						System.out.println("-----------------------");
						System.out.println("|  Issue raised by you  |");
						System.out.println("-----------------------");
						ViewIssue.toViewIssue(sc, username);
						break;
					case 3:
						System.out.println("-----------------------");
						System.out.println("|    Edit Profile     |");
						System.out.println("-----------------------");
						Profile.editProfile(sc,username,role);
						break;
					case 4:
						menuFlag=false;
						break;
				
					}
				}
		
			
		}
			catch (NumberFormatException e) {
				System.out.println("--------------------------------------------------------");
                System.out.println("   !You have enter a character,Please enter a number!  ");
                System.out.println("--------------------------------------------------------");
                System.out.println();
                sc.nextLine(); 
			}
			catch (InvalidNumberException e) {
				System.out.println("--------------------------------------------------------");
                System.out.println("!"+e.getMessage()+"! ");
                System.out.println("--------------------------------------------------------");
            }
	
		}
		
		
		
		
	}

}

