package com.issuesystem.adminmanagement;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.issuesystem.dbconnection.DBConnection;
import com.issuesystem.userdefinedxception.*;

	public class Register {
		public static int Check(Scanner sc) throws Exception {
			boolean inputFlag=true;
			int choice=0;
				while(inputFlag) {
					try {
					System.out.println("You have to add the persons in "+"\t1.Student"+"\t2.Faculty"+"\t3.Staff"+"\t4.To Back");
					System.out.println("Enter your choice:");
					 choice=Integer.parseInt(sc.next());
					if (choice != 1 && choice != 2 && choice!= 3 && choice!=4) {
		                throw new InvalidNumberException("Please enter a valid number");
		            }
					return choice;
		            
					
		        } catch (NumberFormatException e) {
		        	System.out.println("--------------------------------------------------------");
		            System.out.println("   !You have enter a character,Please enter a number!   ");
		            System.out.println("--------------------------------------------------------");
		            System.out.println();
		            
		        } catch (InvalidNumberException e) {
		        	System.out.println("--------------------------------------------------------");
		            System.out.println("!"+e.getMessage()+"! ");
		            System.out.println("--------------------------------------------------------");
		        }
		        
			 }
				return choice;	
	}
	
	public static void toRegister() throws Exception {
		
		boolean flag=true;
		Scanner sc=new Scanner(System.in);
		
		while(flag) {
			
			int choice=Check(sc);
			String username = null;
			String number=null;
			String password=null;
			String email=null;
			LocalDate date ;
			int departmentId=0;
			switch(choice) {
				case 1:
					
					boolean check=true;
					while (check) {
			            try {
			            	sc.nextLine();
			                System.out.print("Enter username: ( Username must contain at least one number) :  ");
			                username = sc.next();
			                username = userNameValidate(username);		  
			                check=false;
			                break; // If the username is valid, break the loop
			            } catch (UserNameException e) {
			                System.out.println(e.getMessage());
			            }
			        }
			        System.out.println("Username accepted: " + username);
			        System.out.println();
			        
			        
			        //Phone Number validate
			        
			         check=true;
			        while (check) {
			            try {
			            	System.out.print("Enter the phoneNumber: ( PhoneNumber must contain 10 number): ");
					        number=sc.next();
			                number=numberValidate(number);		  
			                check=false;
			                break; // If the username is valid, break the loop
			            } catch (NumberInputException e) {
			                System.out.println(e.getMessage());
			            }
			        }
			        
			        System.out.println("Phone number accepted: "+number);
			        System.out.println();
			        //password validation
			        
			        check=true;
			        while(check) {
			        	try {
			        		System.out.println("Enter the password (Password must contain atleast one uppercase and one lowercase and one special symbol and"
			        				+ "one number) :  ");
			        		password=sc.next();
			        		password=validatePassword(password);
			        		System.out.println("Password accepted: "+password);
			        		check=false;
			                break;
			        	}
			        	catch(PasswordException e) {
			        		System.out.println(e.getMessage());
			        	}
			        }
			        System.out.println();
			        //Mail id validation
			       
			        check =true;
			        while(check) {
			        	try {
			        		System.out.println("Enter the MailId: ");
			        		email=sc.next();
			        		email=validateEmailValidate(email);
			        		System.out.println("Mail id accepted: "+email);
			        		check=false;
			                break;
			        	}
			        	catch(EmailException e) {
			        		System.out.println(e.getMessage());
			        	}
			        }
			        
			        System.out.println();
			        
			        //joining date
			         date = LocalDate.now();
					
					//Section
			         check =true;
			         String section=null;
			        while(check) {
					System.out.println("Enter the section:(A or B or C) :");
					section=sc.next().toUpperCase();
					if(!section.matches("\\d+") && section.length()<=1 && (section.equalsIgnoreCase("A") ||section.equalsIgnoreCase("B")||section.equalsIgnoreCase("C"))) {
						check=false;
						break;
					}
					else {
						System.out.println("!!!!Enter valid section");
					}
					
			        }
					int year=1;
					
					String role="student";
					
					flag=true;
					while(flag) {
						try {
							System.out.println("Enter the name of the student :");
							String name=sc.next();
							validateName(name);
							System.out.println("Name accepted: "+name);
							flag=false;
							break;
						}
						catch(InvalidInputException e) {
							System.out.println(e.getMessage());
						}
					}
					
					flag=true;
					while(flag) {
						try {
							System.out.print("Enter the DepartmentId:");
							departmentId=Integer.parseInt(sc.next());
							if(departmentId==10||departmentId==20||departmentId==30||departmentId==40) {
								flag=false;
								break;
							}
							else {
								throw new InvalidNumberException("Please enter valid id");
							}
						}
						catch(NumberFormatException e){
							System.out.println("!!!!!Please enter a number");
							
						}
						catch(InvalidNumberException e) {
							System.out.println(e.getMessage());
						}
					}
					Details details=new Details(username,number, password,email,date,year,section,role,departmentId,name);
					addStudent(details);
					System.out.println();
			        break;
			        
			      
			        
			        
				case 2:
				    check=true;
					while (check) {
			            try {
			            	sc.nextLine();
			                System.out.print("Enter username: ( Username must contain at least one number) :  ");
			                username = sc.next();
			                username = userNameValidate(username);		  
			                check=false;
			                break; // If the username is valid, break the loop
			            } catch (UserNameException e) {
			                System.out.println(e.getMessage());
			            }
			        }
			        System.out.println("Username accepted: " + username);
			        System.out.println();
			        

			      
			        //password validation
			        
			        check=true;
			        while(check) {
			        	try {
			        		System.out.println("Enter the password (Password must contain atleast one uppercase and one lowercase and one special symbol and"
			        				+ "one number) :  ");
			        		password=sc.next();
			        		password=validatePassword(password);
			        		System.out.println("Password accepted: "+password);
			        		check=false;
			                break;
			        	}
			        	catch(PasswordException e) {
			        		System.out.println(e.getMessage());
			        	}
			        }
			        System.out.println();
			        
			        
			        //Mail id validation	       
			        check =true;
			        while(check) {
			        	try {
			        		System.out.println("Enter the MailId: ");
			        		email=sc.next();
			        		email=validateEmailValidate(email);
			        		System.out.println("Mail id accepted: "+email);
			        		check=false;
			                break;
			        	}
			        	catch(EmailException e) {
			        		System.out.println(e.getMessage());
			        	}
			        }
			        
			        // role
			        role="Faculty";
					
					System.out.println("Enter the name of the Faculty :");
					name=sc.next();
					System.out.println();
			        
			        //departmentid
			        departmentId=0;
			        while(flag) {
						try {
							System.out.print("Enter the DepartmentId:");
							departmentId=Integer.parseInt(sc.next());
							if(departmentId==10||departmentId==20||departmentId==30||departmentId==40) {
								flag=false;
								break;
							}
							else {
								throw new InvalidNumberException("Please enter valid id");
							}
						}
						catch(NumberFormatException e){
							System.out.println("!!!!!Please enter a number");
							
						}
						catch(InvalidNumberException e) {
							System.out.println(e.getMessage());
						}
					}
			        System.out.println();
			        
			        
			        Details facDetails=new Details(username,password,departmentId,email,name,role);
			        addFaculty(facDetails);
			        System.out.println();
			        
					break;
					
					
					
					
				case 3:
					check=true;
					while (check) {
			            try {
			            	sc.nextLine();
			                System.out.print("Enter username: ( Username must contain at least one number) :  ");
			                username = sc.next();
			                username = userNameValidate(username);		  
			                check=false;
			                break; // If the username is valid, break the loop
			            } catch (UserNameException e) {
			                System.out.println(e.getMessage());
			            }
			        }
					System.out.println();
			        System.out.println("Username accepted: " + username);
			        System.out.println();
			        

			      
			        //password validation
			        
			        check=true;
			        while(check) {
			        	try {
			        		System.out.println("Enter the password (Password must contain atleast one uppercase and one lowercase and one special symbol and"
			        				+ "one number) :  ");
			        		password=sc.next();
			        		password=validatePassword(password);
			        		System.out.println("Password accepted: "+password);
			        		check=false;
			                break;
			        	}
			        	catch(PasswordException e) {
			        		System.out.println(e.getMessage());
			        	}
			        }
			        System.out.println();
			        
			        
			        //Mail id validation	       
			        check =true;
			        while(check) {
			        	try {
			        		System.out.println("Enter the MailId: ");
			        		email=sc.next();
			        		email=validateEmailValidate(email);
			        		System.out.println("Mail id accepted: "+email);
			        		check=false;
			                break;
			        	}
			        	catch(EmailException e) {
			        		System.out.println(e.getMessage());
			        	}
			        }
			        System.out.println();
			        
			        // role
			        role="Staff";
					
					System.out.println("Enter the name of the Staff :");
					name=sc.next();
					System.out.println();
			        
 
			        Details staffDetails=new Details(username,password,email,name,role);
			        addStaff(staffDetails);
			        System.out.println();
					break;
					
				case 4:
					flag=false;
					break;
		    }

			}
		}

	
	
	
	
	
	public static String userNameValidate(String username) throws UserNameException {
	    if (!username.matches("^(?=.*[0-9])[A-Za-z][A-Za-z0-9_]{7,29}$")) {
	        char invalidChar = findInvalidCharacter(username);
	        throw new UserNameException("Invalid character '" + invalidChar + "' in username. Please enter a valid username.");
	    }
	    return username;
	}

		
		
		public static char findInvalidCharacter(String username) {
	        for (char c : username.toCharArray()) {
	            if (!Character.isLetterOrDigit(c) && c != '_') {
	                return c;
	            }
	        }
	        return 0; // Return 0 if no invalid character found
	    }
		
		
		
		public static String numberValidate(String number) throws NumberInputException {
	        if (!number.matches("\\d+") || number.length()!=10)
	        {
	            throw new NumberInputException("Input Invalid - Enter valid Input");
	        }
	        return number;
	    }
		
		
		public static String validatePassword(String Password) throws PasswordException {
	        if (Password.length() < 8) {
	            throw new PasswordException("Password length must be at least 8 characters");
	        }
	        if (!Password.matches(".*[a-z].*")) {
	            throw new PasswordException("Password must contain at least one lowercase character");
	        }
	        if (!Password.matches(".*[A-Z].*")) {
	            throw new PasswordException("Password must contain at least one uppercase character");
	        }
	        if (!Password.matches(".*[!@#$%^&()_+].*")) {
	            throw new PasswordException("Password must contain at least one special character: !@#$%^&*()_+");
	        }
	        if (!Password.matches(".*\\d.*")) {
	            throw new PasswordException("Password must contain at least one digit");
	        }
	        return Password;
	    }

		public static String validateEmailValidate(String email) throws EmailException {
			if(email.length()>=30) {
	        	throw new EmailException("Email length must be at less than 30 characters");
	        }
	        if(!email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$"))
	        {
	            throw  new EmailException("EmailAddress is invalid format - Enter Valid Email Address");
	        }
	        
	        return email;
	    }
		
		public static void validateName(String input) throws InvalidInputException {
		    if (input.length() <= 3) {
		        throw new InvalidInputException("!!!!!Input must be more than 3 characters.!!!!!");
		    }
		    if (!input.matches("[a-zA-Z ]+")) {
		        throw new InvalidInputException("!!!!!Input must contain only letters (no numbers or symbols)!!!!!");
		    }
		}
		
		//Inserting the values into the Student table
		public static void addStudent(Details details) throws SQLException {
			String GREEN = "\u001B[32m";
			 String RESET = "\u001B[0m";
			String sql="insert into student values(studid_seq.nextval,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt=DBConnection.getDBConnextion().prepareStatement(sql);
			stmt.setString(1,details.getUsername());
			stmt.setString(2,details.getPassword());
			stmt.setString(3,details.getRole());
			stmt.setInt(4,details.getDepartmentId());
			stmt.setDate(5,Date.valueOf(details.getDateOfJoining()));
			stmt.setString(6, details.getMailId());
			stmt.setString(7, details.getSection());
			stmt.setString(8, details.getName());
			stmt.setInt(9,details.getYear());
			stmt.executeUpdate();
			System.out.println("╔══════════════════════════════════════════════════════════════════════╗");
			System.out.println("║                                                                      ║");
			System.out.println("║                    "+GREEN+" Inserted Successfully "+RESET+"                           ║");
			System.out.println("║                                                                      ║");
			System.out.println("╚══════════════════════════════════════════════════════════════════════╝");	
		}
		
		
		//Inserting the values into the Faculty table
		public static void addFaculty(Details facDetails) throws SQLException {
			 String GREEN = "\u001B[32m";
			 String RESET = "\u001B[0m";
			String sql="insert into faculty values(Facid_seq.nextval,?,?,?,?,?,?)";
			PreparedStatement stmt=DBConnection.getDBConnextion().prepareStatement(sql);
			stmt.setString(1,facDetails.getUsername());
			stmt.setString(2,facDetails.getPassword());
			stmt.setInt(3,facDetails.getDepartmentId());
			stmt.setString(4, facDetails.getMailId());
			stmt.setString(5, facDetails.getName());
			stmt.setString(6,facDetails.getRole());
			stmt.executeUpdate();
			System.out.println("╔══════════════════════════════════════════════════════════════════════╗");
			System.out.println("║                                                                      ║");
			System.out.println("║                    "+GREEN+" Inserted Successfully "+RESET+"                           ║");
			System.out.println("║                                                                      ║");
			System.out.println("╚══════════════════════════════════════════════════════════════════════╝");
	
		}
		
		
		
		//Inserting the values into the Staff table
		public static void addStaff(Details staffDetails) throws SQLException {
			
			String GREEN = "\u001B[32m";
			 String RESET = "\u001B[0m";
			String sql="insert into staff values(Staffid_seq.nextval,?,?,?,?,?)";
			PreparedStatement stmt=DBConnection.getDBConnextion().prepareStatement(sql);
			stmt.setString(1,staffDetails.getUsername());
			stmt.setString(2,staffDetails.getPassword());
			stmt.setString(3, staffDetails.getMailId());
			stmt.setString(4, staffDetails.getName());
			stmt.setString(5,staffDetails.getRole());
			stmt.executeUpdate();
			System.out.println("╔══════════════════════════════════════════════════════════════════════╗");
			System.out.println("║                                                                      ║");
			System.out.println("║                    "+GREEN+" Inserted Successfully "+RESET+"                           ║");
			System.out.println("║                                                                      ║");
			System.out.println("╚══════════════════════════════════════════════════════════════════════╝");
	
		}
		
		

}
