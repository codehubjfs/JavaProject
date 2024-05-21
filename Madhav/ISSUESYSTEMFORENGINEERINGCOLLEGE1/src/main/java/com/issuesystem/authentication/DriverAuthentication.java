package com.issuesystem.authentication;
import java.util.*;

import com.issuesystem.adminmanagement.*;
import com.issuesystem.category.*;
import com.issuesystem.dbconnection.DBConnection;
import com.issuesystem.staffmanagement.*;
import com.issuesystem.subcategory.*;
import com.issuesystem.userdefinedxception.*;
import com.issuesystem.users.*;

import java.sql.*;
public class DriverAuthentication {
	public static int rollCheck(Scanner sc) {
		
		int role=0;
		boolean inputflag=true;
		while (inputflag) {
            try {
                System.out.println();
    			System.out.println("******************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************");
                System.out.println("                                                             Welcome to Issuesystem for Engineering College                                                           ");
                System.out.println("******************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************");
                System.out.println(" 1. Student                                                            ");
                System.out.println(" 2. Faculty                                                            ");
                System.out.println(" 3. Staff                                                              ");
                System.out.println(" 4. Admin                                                              ");
                System.out.println(" 5. Exit                                                               ");
                System.out.println("******************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************");
                System.out.print("Enter your choice: ");
    			role = Integer.parseInt(sc.next()); 
                
                if (role != 1 && role != 2 && role != 3 && role!=4 && role!=5) {
                    throw new InvalidNumberException("Please enter a valid number");
                }
                
                inputflag = false; 
            } catch (NumberFormatException e) {
            	System.out.println("--------------------------------------------------------");
                System.out.println("   !You have enter a character,Please enter a number!   ");
                System.out.println("--------------------------------------------------------");
                System.out.println();
                sc.nextLine(); 
            } catch (InvalidNumberException e) {
            	System.out.println("--------------------------------------------------------");
                System.out.println("!"+e.getMessage()+"! ");
                System.out.println("--------------------------------------------------------");
            }
        }
		return role;

	}
	

	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);

		//Checks the role of the users
		 int role=0;
		 role=rollCheck(sc);
		
		
		 //Getting the username and password from user
		 String username=null;
		 String password=null;
			boolean Menuflag=true;
		
				while(Menuflag) {
						
						if (role==1 ||role==2||role==3 ||role==4) {	
					    sc.nextLine();
						System.out.print("Enter the username:");
						username=sc.next().trim();
						System.out.println();
						 
						System.out.print("Enter the password:");
						password=sc.next().trim();
						System.out.println();
						}
						String GREEN = "\u001B[32m";
						  String RESET = "\u001B[0m";
						switch(role) {
						case 1:
							String authorize_Student=Students.authenticateStudent(username,password);
							if(authorize_Student.equals("Wrong")) {
								System.err.println("!!!!!!!!You have entered wrong usernmae and passowrd!!!!!!!!");
								System.out.println();
								break;
								
							}
							if(authorize_Student.equals("User not found")) {
								System.err.println("User not found!!!");
								System.out.println();
								break;
							}
							System.out.println("╔══════════════════════════════════════════════════════════════════════╗");
							System.out.println("║                                                                      ║");
							System.out.println("║                    "+GREEN+" Login Successfully "+RESET+"                              ║");
							System.out.println("║                                                                      ║");
							System.out.println("╚══════════════════════════════════════════════════════════════════════╝");
				
							System.out.println(authorize_Student);
							IssueRaise.chooseMenu(sc,username,role);
							role=rollCheck(sc);
							
							break;
						case 2:
							String authorize_Faculty=Faculty.authenticateFaculty(username, password);
							if(authorize_Faculty.equals("Wrong")) {
								System.err.println("!!!!!!!!You have entered wrong usernmae and passowrd!!!!!!!!");
								System.out.println();
								break;
								
							}
							if(authorize_Faculty.equals("User not found")) {
								System.err.println("User not found");
								System.out.println();
								break;
							}
							System.out.println("╔══════════════════════════════════════════════════════════════════════╗");
							System.out.println("║                                                                      ║");
							System.out.println("║                  "+GREEN+" Login Successfully "+RESET+"                                ║");
							System.out.println("║                                                                      ║");
							System.out.println("╚══════════════════════════════════════════════════════════════════════╝");
			
							System.out.println(authorize_Faculty);
							IssueRaise.chooseMenu(sc,username,role);
							role=rollCheck(sc);
							break;
						case 3:
							String authorize_Staff=Staff.authenticateStaff(username, password);
							if(authorize_Staff.equals("Wrong")) {
								System.err.println("!!!!!!!!You have entered wrong usernmae and passowrd!!!!!!!!");
								System.out.println();
								break;
								
							}
							if(authorize_Staff.equals("User not found")) {
								System.err.println("User not found");
								System.out.println();
								break;
							}
							System.out.println("╔══════════════════════════════════════════════════════════════════════╗");
							System.out.println("║                                                                      ║");
							System.out.println("║                  "+GREEN+" Login Successfully "+RESET+"                                ║");
							System.out.println("║                                                                      ║");
							System.out.println("╚══════════════════════════════════════════════════════════════════════╝");
							System.out.println(authorize_Staff);
							StaffManagement.chooseMenu(sc,username,role);
							role=rollCheck(sc);
							break;
						case 4:
							String authorize_Admin=Admin.authenticate_Admin(username, password);
							if(authorize_Admin.equals("Wrong")) {
								System.err.println("!!!!!!!!You have entered wrong usernmae and passowrd!!!!!!!!");
								System.out.println();
								break;
								
							}
							if(authorize_Admin.equals("User not found")) {
								System.err.println("User not found!!!");
								break;
							}
							System.out.println("╔══════════════════════════════════════════════════════════════════════╗");
							System.out.println("║                                                                      ║");
							System.out.println("║                  "+GREEN+" Login Successfully "+RESET+"                                ║");
							System.out.println("║                                                                      ║");
							System.out.println("╚══════════════════════════════════════════════════════════════════════╝");
			
							System.out.println(authorize_Admin);
							Register.toRegister();
							role=rollCheck(sc);
							break;
						case 5:
							String cyan=" \u001B[46m";
							System.out.println(""+cyan+"-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-THANKS FOR VISITING-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-"+RESET+"");
							Menuflag=false;
							break;
				
						}
					
			}
		
			
		   
		}
		
	}


