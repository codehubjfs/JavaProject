package com.issuesystem.subcategory;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.issuesystem.dbconnection.DBConnection;
import com.issuesystem.userdefinedxception.*;
import com.issuesytem.issue.IssueInsert;

public class Academic {
	
	//ACADEMIC INPUT CHECK
	public static int checkInput(Scanner sc) {
		boolean inputflag=true;
		int input=0;
		while(inputflag) {
		try {
			System.out.println("1.Enter the details of the issue"+"\t2.goback");
			System.out.print("choose your choice:");
			
			 input=Integer.parseInt(sc.next());
			if(input!=1 &&input!=2) {
				throw new InvalidNumberException("You have entered invalid number");
			}
			inputflag=false;
			break;
		}
		
		catch (NumberFormatException e) {
			System.out.println("-------------------------------------------------------");
            System.out.println("   !You have enter a character,Please enter a number!  ");
            System.out.println("-------------------------------------------------------");
            System.out.println();
            sc.nextLine(); 
		}
		catch (InvalidNumberException e) {
			System.out.println("-------------------------------------------------------");
            System.out.println("!"+e.getMessage()+"! ");
            System.out.println("-------------------------------------------------------");
        }
		}
	
		return input;
	}
	
	
   
		
// GET ACADEMIC DETAILS
public static void academicdetails(int key,String username,Scanner sc) throws SQLException, DateValidatorException, InvalidInputException	{	
		boolean academicDetailsFlag=true;
		while(academicDetailsFlag) {
	
			int input=checkInput(sc);
			int academic=0;
			String description=null;
			LocalDate issue_raise_date ;
			DateTimeFormatter formatter;
			String priority=null;
			boolean dateFlag=true;
			String issue_title=null;
			String issueOn=null;
			
			switch(input) {
			case 1:
				
				//title of the issue
				sc.nextLine(); 
				while (dateFlag) {
		            try {
		            	System.out.println("Issuse title:");
						issue_title=sc.nextLine();
						validateTitle(issue_title);
						dateFlag=false;
		            	break;
		            } catch (InvalidInputException e) {
		                System.out.println(e.getMessage());
		            }
		        }
				System.out.println();
				//Description of your issue
				dateFlag=true;
				while (dateFlag) {
		            try {
		            	
		            	System.out.print("Enter the description:");
						description=sc.nextLine();
						validateInput(description);
						dateFlag=false;
		            	break;
		            } catch (InvalidInputException e) {
		                System.out.println(e.getMessage());
		            }
		        }
				System.out.println();
				
				//Issue on which particular location
				dateFlag=true;
				while (dateFlag) {
		            try {
		            	System.out.print("Issue on");
						System.out.print("like ("+"\t1.portion not uploades"+"\t2.exam schedule):");
						issueOn=sc.nextLine();
						validateInput(issueOn);
						dateFlag=false;
						break;
		            } catch (InvalidInputException e) {
		                System.out.println(e.getMessage());
		            }
		        }
				
				System.out.println();
				//Issue raised date
				issue_raise_date = LocalDate.now();
				
				
				
				dateFlag=true;
				while (dateFlag) {
		            
					    int choice=0;
		            	System.out.println("Set the priority:" +"\t1.low"+"\t2.medium"+"\t3.high");
		            	try {
		            		System.out.print("Enter your Choice:");
		            		   choice=Integer.parseInt(sc.next());
		            		   if (choice!=1 && choice!=2 && choice!=3) {
		                           throw new InvalidNumberException("Please enter a valid number");
		                       }
		            		
		            	}
		            	catch (NumberFormatException e) {
		                	System.out.println("--------------------------------------------------------");
		                    System.out.println("   !You have enter a character,Please enter a number!   ");
		                    System.out.println("--------------------------------------------------------");
		                    System.out.println();
		                    sc.nextLine(); 
		                }
		            	catch (InvalidNumberException e) {
		                	System.out.println("--------------------------------------------------------");
		                    System.out.println("!"+e.getMessage()+"! ");
		                    System.out.println("--------------------------------------------------------");
		                }
		            	
		            	if(choice==1) {
		            		priority="Low";
		            		dateFlag=false;
			            	break;
		            	}
		            	if(choice==2) {
		            		priority="Medium";
		            		dateFlag=false;
			            	break;
		            	}
		            	if(choice==3) {
		            		priority="High";
		            		dateFlag=false;
			            	break;
		            	}
		            	
		            	
		            
				}
				
				System.out.println();
				
				 //Issue happen date
				 dateFlag=true;
				 String date=null;
				 LocalDate issueDate = null;
				 while (dateFlag) {
			            try {
			            	sc.nextLine();
			                System.out.print("Enter the issueDate(YYYY-MM-DD) : ");
			                date = sc.next();
			                date = DateValidater(date);
			                issueDate=LocalDate.parse(date);
			                if (issueDate.isBefore(LocalDate.now().minusMonths(3))) {
                                System.out.println("We could not take the issue before 3 months!!!");
                                continue;
                            }
			                else {
			                dateFlag=false;
			                break;
			                }
			            } catch (DateValidatorException e) {
			                System.out.println(e.getMessage());
			            }
			        }
				 	
			        System.out.println();
			        
			        
			        
					Issue obj=new Issue(issue_title, description, issueOn,issue_raise_date,priority, issueDate);
					IssueInsert.details(username,key,obj);
					break;
			case 2:
				academicDetailsFlag=false;
				break;

				}
			}
		}






		//Date Exception

		public static String DateValidater(String issue_date) throws DateValidatorException {
		    if(!issue_date.matches("^(?:19|20)\\d\\d-(?:0[1-9]|1[012])-(?:0[1-9]|[12]\\d|3[01])$"))
		    {
		        throw new DateValidatorException("Invalid date format");
		    }
		    return issue_date;
		}
		
		
		
		
		//User Exception 
		
		public static void validateInput(String input) throws InvalidInputException {
	        if (input.length() <= 5) {
	            throw new InvalidInputException("!!!!!Input must be more than 5 characters.!!!!!");
	        }
	        if (!input.matches(".*[a-zA-Z]+.*")) {
	            throw new InvalidInputException("!!!!!Input must contain at least one letter!!!!!");
	        }
	        if (input.matches("^[^a-zA-Z]*$")) {
	            throw new InvalidInputException("!!!!! Input must contain more than just special characters, dots, or numbers !!!!!");
	        }
	    }
		
		
		//title Exception
		
		public static void validateTitle(String input) throws InvalidInputException {
		    if (input.length() <= 3) {
		        throw new InvalidInputException("!!!!!Input must be more than 3 characters.!!!!!");
		    }
		    if (!input.matches("[a-zA-Z]+")) {
		        throw new InvalidInputException("!!!!!Input must contain only letters (no numbers or symbols or no spaces)!!!!!");
		    }
		}
		
}


	
	
	



