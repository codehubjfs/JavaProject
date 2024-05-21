package user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

import com.carrentalsystem.car.CarManagement;
import com.carrentalsystem.exception.InvalidEmailException;
import com.carrentalsystem.exception.NumberException;
import com.carrentalsystem.exception.PasswordException;
import com.carrentalsystem.exception.UsernameException;
import com.carrentalsystem.exception.Validate;
import com.carrentalsystem.rental.RentalPackageManagement;

public class UserDriver1 {
	static BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));
	//this method is the index of the application
     public static void mainMenu() {
    	 
    	 boolean flag=true;
 		while(flag) {
 			System.out.println("=".repeat(200));
 			System.out.println(" ".repeat(60)+"WELCOME TO கோTRIP");
 			System.out.println("=".repeat(200));
 			System.out.println("1.ADMIN\n2.USER\n3.GUEST\n4.REGISTER\n5.EXIT");
 			System.out.println(".".repeat(200));
 			Account account=new Account();
 			try {
				int option=Validate.ValidateNumber(sc.readLine());
				switch(option) {
				case 1:
					Admin();
					break;
				case 2:
					User();
					break;
				case 3:
					guest();
					break;
				case 4:
					
					account.reg();
					break;
				case 5:
					System.out.println("Thank You For Visiting");
					System.exit(0);
				default:
					System.out.println("Enter a Valid Number");
					mainMenu();
					break;
				}
				
	
			} catch (NumberException e) {
				System.out.println(e.getMessage());
			}catch (IOException e) {
				e.printStackTrace();
			}catch(PasswordException e) {
				System.out.println(e.getMessage());
				mainMenu();
			} catch (SQLException e) {
				
			} catch (UsernameException e) {
				System.out.println(e.getMessage());
				mainMenu();
			}
 		}
     }
     //this method has the functionalities of guest
     private static void guest() {
    	 int option;
		try {
			System.out.println("1.VIEW CARS\n2.SEARCH CARS\n3.VIEW RENTAL PACKAGES\n4.GO Back");
			option = Validate.ValidateNumber(sc.readLine());
			switch(option) {
			case 1:
				CarManagement.showGuest();
				guest();
				break;
			case 2:
				CarManagement.search();
				guest();
				break;
			case 3:
				RentalPackageManagement rent= new RentalPackageManagement();
				rent.viewrental();
				guest();
				break;
			case 4:
				mainMenu();
				break;
//				System.out.println("Thank You For Visiting");
//				System.exit(0);
			default:
				System.out.println("Enter a Valid Number");
				guest();
				break;
			}
		} catch (NumberException e) {
			System.out.println("Enter a Valid Number:");
			guest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			guest();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			}
     
	//it has the functinalities of customer
	private static void User() {
		System.out.println("USER\n1.Login\n2.Go Back\n3.Exit");
		boolean flag=false;
		do
		{
		try {
			int option=Validate.ValidateNumber(sc.readLine());
			Account account=new Account();
			switch(option) {
			case 1:
				 try {
					if(account.login("Customer")) {
						userOperation();
					}    
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} break;
			case 2:
				mainMenu();
				break;
			case 3:
				System.out.println("Thank You For Visiting");
				System.exit(0);
				break;
			default:
				System.out.println("Enter a Valid Number");
				User();
				break;
		}
		}catch (NumberException e) {
			
			System.out.println(e.getMessage());
			flag = true;
			}
			 catch (IOException e) {
					flag = true;
			System.out.println(e.getMessage());
			 }
		}while(flag);
		
		
	}
	//it has the customers functionalities
	private static void userOperation() {
		 System.out.println("MENU\n1.BOOK CAR\n2.SEARCH\n3.View Cars\n4.VIEW RENTAL PACKAGES \n5.BOOK RENTAL PACKAGES\n6.HISTORY\n7.GO BACK");
		 int userOption;
		try {
			userOption = Validate.ValidateNumber(sc.readLine());
			 RentalPackageManagement rent= new RentalPackageManagement();
			 switch(userOption) {
			 case 1:
				try {
					CarManagement.showCars();
					userOperation();
				} catch (SQLException e){
					System.out.println("Enter Valid Details:");
				}catch(DateTimeParseException e) {
					System.out.println("Enter valid details:");
					userOperation();
					
				}
			 break;
			 case 2:
				 try {
					CarManagement.search();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				userOperation();
				 break;
			 case 3:
				 try {
					CarManagement.view();
					userOperation();
				}
				 catch (SQLException e) {
					
				}
				 break;
			 case 4:
				 RentalPackageManagement rental= new RentalPackageManagement();
				try {
					rent.viewrental();
				} catch (SQLException e) {
					System.out.println("Enter Valid Details:");
					userOperation();
				}
				userOperation();
			 case 5:
				 RentalPackageManagement bookRental= new RentalPackageManagement();
				 try {
					bookRental.bookrental();
				} catch (SQLException e) {
					System.out.println("Enter Valid Input: " +e.getMessage());
					userOperation();
				}catch(Exception e) {
					System.out.println("Enter Valid details:");
					userOperation();
				}
			 case 6:
				 try {
					 System.out.println("Booking Details:");
				 rent.viewBooking();
				 System.out.println("viewed");
				 userOperation();
				 }catch(Exception e) {
					 System.out.println("Not Booked");
					 userOperation();
				 }
				 break;
			 case 7:
				 User();
				 break;
			 default:
				 System.out.println("Enter Valid Option:");
				 userOperation();
				 break;
			 }
			 
				 
		} catch (NumberException e) {
			System.out.println(e.getMessage());
			userOperation();
		} catch (IOException e) {
			
		}
		
	}
	//this method has the admin login
	private static void Admin() {
//		boolean flag=true;
//		while(flag) {
		System.out.println("1.Login\n2.Go Back\n3.Exit");
		
		try {
			int option=Validate.ValidateNumber(sc.readLine());
			Account account=new Account();
			switch(option) {
			case 1:{
				try {
					 //=account.login("Admin");
					if(account.login("Admin")) {
						adminManagement();
					}
				} catch (SQLException e) {
					System.out.println("Please Valid Input:");
				}
				break;
				
			}
			case 2:{
				mainMenu();
				break;
			}case 3:{
				System.out.println("Thank You For Visiting");
				System.exit(0);
			}default:
				System.out.println("Enter a Valid Number");
				Admin();
				break;
				
			}
			
			
			
		
		} catch (NumberException e) {
			System.out.println(e.getMessage());
			Admin();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}
	//admin main functionalities are in this method
	private static void adminManagement() {
		System.out.println("MENU\n1.CAR MANAGEMENT\n2.RENTAL PACKAGE MANAGEMENT\n3.USER MANAGEMENT\n4.REPORT\n5.Go Back\n6.Exit");
		int carOption;
		
			try {
				carOption = Validate.ValidateNumber(sc.readLine());
				switch(carOption) {
				case 1:
				
			     carManagement();
					
				
				break;
				case 2:
				packageManagement();
				break;
				case 3:
				userManagement();
				break;
				case 4:
				break;
				case 5:
				Admin();
				break;
				case 6:
					System.out.println("Thank You For Visiting");
				System.exit(0);
				break;
				default:
					System.out.println("Enter Valid Option");
					adminManagement();
				}
			}catch (NumberException e) {
				System.out.println(e.getMessage());
				adminManagement();
			} catch (IOException e) {
				adminManagement();
			}
		
		
		
	    
		
		
	}
    //Modifying the user is functionality in this method
	private static void userManagement() {
		System.out.println("1.Modify user\n2.Go back\n3.Exit");
		int userOption;
		
		try {
			userOption = Validate.ValidateNumber(sc.readLine());
			UserManagement user=new UserManagement();
			switch(userOption) {
			case 1:	
				try {
					user.manageuser();
					userManagement();
				} catch (SQLException e) {
					System.out.println("Enter Valid Id:");
					try {
						user.manageuser();
						userManagement();
					} catch (SQLException e1) {
						
					}
					
				}catch(NumberException e) {
					System.out.println("Enter Valid Input:");
					try {
						user.manageuser();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
					}
				}
			break;
			case 2:
			userManagement();
			break;
			case 3:
				System.out.println("Thank You For Visiting");
			System.exit(0);
			break;
			default:
				System.out.println("Enter valid Number");
				userManagement();
				break;
			}
		} catch (NumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
	//all car functionalities are in this method
	public static void carManagement() {
		
		int carOption;		
		try {
			System.out.println("1.View car\n2.AddCar\n3.Delete Car\n4.Update Car\n5.Go Back\n6.Exit");
			carOption = Validate.ValidateNumber(sc.readLine());
			CarManagement carmanage=new CarManagement();
			switch(carOption) {
			case 1:
				
				try {
					carmanage.showGuest();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				carManagement();
				break;
			case 2:
				try {
					carmanage.addcar();
					carManagement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case 3:
				try {
					if(carmanage.deletecar())
					carManagement();
					else
					carmanage.deletecar();
				} catch(SQLException e){
					System.out.println("Enter valid Id:");
					try {
						carmanage.deletecar();
					} catch (SQLException e1) {
						System.out.println("Enter valid input:");
						//carmanage.deletecar();
					}
				}
				break;
			case 4:
				try {
					carmanage.updatecar();
					carManagement();
					break;
				} catch (NumberException e) {
					System.out.println("Enter Valid Number:");
					
					try {
						carmanage.updatecar();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						
					}
				}catch(SQLException e) {
					System.out.println("Give Valid Input");
					try {
						carmanage.updatecar();
					} catch (SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
					}
				}
				
				break;
			case 5:
				adminManagement();
				break;
			case 6:
				System.out.println("Thank You For Visiting");
				System.exit(0);
				break;
			default:
				System.out.println("Enter a valid Number:");
				carManagement();
				break;
				
			}
		} 
		
		catch (NumberException e) {
			System.out.println(e.getMessage());
			carManagement();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	//all rental packages functionalities are in the method
	private static void packageManagement() {
		System.out.println("1.AddRent\n2.Delete Rent\n3.Update Rent\n4.View Rent\n5.Go Back\n6.Exit");
		int rentOption;
		try {
			rentOption = Validate.ValidateNumber(sc.readLine());
			RentalPackageManagement rentmanage=new RentalPackageManagement();
			switch(rentOption) {
			case 1:
				try {
					rentmanage.addrental();
					packageManagement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case 2:
				try {
					rentmanage.deleterental();
				} catch (SQLException e1) {
					System.out.println("Enter Valid Id:");
					try {
						rentmanage.deleterental();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
				}catch(NumberException e) {
					System.out.println("Enter a Number");
					try {
						rentmanage.deleterental();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				packageManagement();
				break;
			case 3:
				try {
					rentmanage.updaterental();
					packageManagement();
				} catch (Exception e) {
					try {
						System.out.println("Enter Valid Input:");
						try {
							rentmanage.updaterental();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							System.out.println("Enter  Valid Input:");
						}
					}catch(Exception val) {
						
					}
				}
				break;
			case 4:
				try {
					rentmanage.viewrental();
				} catch (SQLException e) {
					System.out.println("Enter Valid Id");
					
				}
				packageManagement();
				break;
			case 5:
				adminManagement();
				break;
			case 6:
				System.out.println("Thank You For Visiting");
				System.exit(0);
				break;
			default:
				System.out.println("Enter a valid Number:");
				packageManagement();
				break;
				
			}
		} catch (NumberException e) {
			System.out.println(e.getMessage());
			packageManagement();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	//calling main method to run the application
	public static void main(String[] args) {
		mainMenu();

	}

}
