package com.issuesystem.users;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.issuesystem.category.Category;
import com.issuesystem.dbconnection.DBConnection;
import com.issuesystem.userdefinedxception.EmailException;
import com.issuesystem.userdefinedxception.InvalidInputException;
import com.issuesystem.userdefinedxception.InvalidNumberException;
import com.issuesytem.issue.ViewIssue;

public class Profile {
	
	public static void editProfile(Scanner sc,String username,int role) throws EmailException, SQLException, InvalidInputException {
		boolean flag=true;
		while(flag) {
			System.out.println("1.To edit email "+"\n2.To edit name"+"\n3.Back");
			System.out.print("Enter your choice: ");
			
			try {
				int choice=Integer.parseInt(sc.next());
				if(choice!=1 && choice!=2 && choice!=3) {
				   throw new InvalidNumberException("You have entered invalid number");
				
				}
				else {		
					switch(choice){
					case 1:
						editMail(sc,username,role);
						
						break;
					case 2:
						editName(sc,username,role);
						
						break;
						
					case 3:
						flag=false;
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
	
	
	
	//Edit the mail id for student,faculty,staff
	public static void editMail(Scanner sc,String username,int role) throws  SQLException {
		
		String GREEN = "\u001B[32m";
		String RESET = "\u001B[0m";
		if(role==1) {
			String mail=null;
			boolean flag=true;
			while(flag) {
				try {
					sc.nextLine();
					System.out.println("Enter the new mailid : ");
					mail=sc.nextLine();
					mail=validateEmailValidate(mail);
					
					flag=false;
					break;
				}
				catch(EmailException e) {
					System.out.println(e.getMessage());
				}
			}
			
			flag=true;
			if(flag) {
			String sql="update student set mail_id=? where username=?";
			PreparedStatement stmt=DBConnection.getDBConnextion().prepareStatement(sql);
			stmt.setString(1, mail);
			stmt.setString(2,username);
			stmt.executeUpdate();
			System.out.println();
			System.out.println("╔══════════════════════════════════════════════════════════════════════════════════╗");
			System.out.println("║                                                                                  ║");
			System.out.println("║                   "+GREEN+" Email was Successfully updated "+RESET+"                               ║");
			System.out.println("║                                                                                  ║");
			System.out.println("╚══════════════════════════════════════════════════════════════════════════════════╝");
			System.out.println();
			}
					
		}
		
		if(role==2) {
			String mail=null;
			boolean flag=true;
			while(flag) {
				try {
					sc.nextLine();
					System.out.println("Enter the new mailid : ");
					mail=sc.nextLine();
					mail=validateEmailValidate(mail);
					
					flag=false;
					break;
				}
				catch(EmailException e) {
					System.out.println(e.getMessage());
				}
			}
			
			flag=true;
			if(flag) {
			String sql="update faculty set mail_id=? where username=?";
			PreparedStatement stmt=DBConnection.getDBConnextion().prepareStatement(sql);
			stmt.setString(1, mail);
			stmt.setString(2,username);
			stmt.executeUpdate();
			System.out.println();
			System.out.println("╔══════════════════════════════════════════════════════════════════════════════════╗");
			System.out.println("║                                                                                  ║");
			System.out.println("║                   "+GREEN+" Email was Successfully updated "+RESET+"                               ║");
			System.out.println("║                                                                                  ║");
			System.out.println("╚══════════════════════════════════════════════════════════════════════════════════╝");
			System.out.println();
			
			}
					
		}
		
		if(role==3) {
			String mail=null;
			boolean flag=true;
			while(flag) {
				try {
					sc.nextLine();
					System.out.println("Enter the new mailid : ");
					mail=sc.nextLine();
					mail=validateEmailValidate(mail);
					
					flag=false;
					break;
				}
				catch(EmailException e) {
					System.out.println(e.getMessage());
				}
			}
			
			flag=true;
			if(flag) {
			String sql="update staff set mail_id=? where username=?";
			PreparedStatement stmt=DBConnection.getDBConnextion().prepareStatement(sql);
			stmt.setString(1, mail);
			stmt.setString(2,username);
			stmt.executeUpdate();
			System.out.println("╔══════════════════════════════════════════════════════════════════════════════════╗");
			System.out.println("║                                                                                  ║");
			System.out.println("║                   "+GREEN+" Email was Successfully updated "+RESET+"                               ║");
			System.out.println("║                                                                                  ║");
			System.out.println("╚══════════════════════════════════════════════════════════════════════════════════╝");
			System.out.println();
			
			}
					
		}
	}

		
	

	
		//Edit the name for Student,Faculty and staff
		
		public static void editName(Scanner sc,String username,int role) throws SQLException, InvalidInputException {
			 String GREEN = "\u001B[32m";
			 String RESET = "\u001B[0m";
			  
			//Student
			if(role==1) {
				String name=null;
				boolean flag=true;
				while(flag) {
					try {
						System.out.println("Enter the new name(minimum 3 character) : ");
						name=sc.nextLine();
						validateInput(name);
						
						flag=false;
						break;
					}
					catch(InvalidInputException e) {
						System.out.println(e.getMessage());
					}
				}
					flag=true;
					if(flag) {
					String sql="update student set name=? where username=?";
					PreparedStatement stmt=DBConnection.getDBConnextion().prepareStatement(sql);
					stmt.setString(1, name);
					stmt.setString(2,username);
					stmt.executeUpdate();
					System.out.println("╔══════════════════════════════════════════════════════════════════════════════════╗");
					System.out.println("║                                                                                  ║");
					System.out.println("║                    "+GREEN+" Name was Successfully updated "+RESET+"                               ║");
					System.out.println("║                                                                                  ║");
					System.out.println("╚══════════════════════════════════════════════════════════════════════════════════╝");
				}	
			}
			
			//Faculty
			if(role==2) {
				String name=null;
				boolean flag=true;
				while(flag) {
					try {
						System.out.println("Enter the new name(minimum 3 character) : ");
						name=sc.nextLine();
						validateInput(name);
						
						flag=false;
						break;
					}
					catch(InvalidInputException e) {
						System.out.println(e.getMessage());
					}
				}
					flag=true;
					if(flag) {
					String sql="update faculty set name=? where username=?";
					PreparedStatement stmt=DBConnection.getDBConnextion().prepareStatement(sql);
					stmt.setString(1, name);
					stmt.setString(2,username);
					stmt.executeUpdate();
					System.out.println("Name was Successfully updated");
					System.out.println("╔══════════════════════════════════════════════════════════════════════════════════╗");
					System.out.println("║                                                                                  ║");
					System.out.println("║                    "+GREEN+" Name was Successfully updated "+RESET+"                               ║");
					System.out.println("║                                                                                  ║");
					System.out.println("╚══════════════════════════════════════════════════════════════════════════════════╝");
				}	
			}
			
			
			//Staff
			if(role==3) {
				String name=null;
				boolean flag=true;
				while(flag) {
					try {
						System.out.println("Enter the new name(minimum 3 character) : ");
						name=sc.nextLine();
						validateInput(name);
						
						flag=false;
						break;
					}
					catch(InvalidInputException e) {
						System.out.println(e.getMessage());
					}
				}
					flag=true;
					if(flag) {
					String sql="update staff set name=? where username=?";
					PreparedStatement stmt=DBConnection.getDBConnextion().prepareStatement(sql);
					stmt.setString(1, name);
					stmt.setString(2,username);
					stmt.executeUpdate();
					System.out.println("╔══════════════════════════════════════════════════════════════════════════════════╗");
					System.out.println("║                                                                                  ║");
					System.out.println("║                    "+GREEN+" Name was Successfully updated "+RESET+"                               ║");
					System.out.println("║                                                                                  ║");
					System.out.println("╚══════════════════════════════════════════════════════════════════════════════════╝");
				}	
			}
		}
	
	
	
	
	
	
	
	
		public static String validateEmailValidate(String email) throws EmailException {
	        if(!email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$"))
	        {
	            throw  new EmailException("EmailAddress is invalid format - Enter Valid Email Address");
	        }
	        return email;
	    }
		
		public static void validateInput(String input) throws InvalidInputException {
		    if (input.length() <= 3) {
		        throw new InvalidInputException("!!!!!Input must be more than 3 characters.!!!!!");
		    }
		    if (!input.matches("[a-zA-Z]+")) {
		        throw new InvalidInputException("!!!!!Input must contain only letters (no numbers or symbols)!!!!!");
		    }
		}

}
