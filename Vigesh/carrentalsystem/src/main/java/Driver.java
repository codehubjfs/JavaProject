import java.sql.SQLException;
import java.util.Scanner;

import user.Account;
import user.Admin;
import user.Customer;

public class Driver {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner(System.in);
		boolean flag=true;
		while(flag) {
			System.out.println("WELCOME TO கோTRIP");
			System.out.println("1.ADMIN\n2.USER\n3.GUEST\n4.REGISTER\n5.EXIT");
			int option=sc.nextInt();
			Account a=new Account();
		   switch(option) {
		   
		   case 1:
//		       if(a.login("Admin")) {
//		    	   System.out.println("MENU\n1.CAR MANAGEMENT\n2.RENTAL PACKAGE MANAGEMENT\n3.USER MANAGEMENT\n4.REPORT");
//		    	  
//		       }
		       break;
		   case 2:
			   System.out.println("Enter CustomerName");
//			   sc.nextLine();
//			   username=sc.nextLine();
//			   System.out.println("Enter Password:");
//			   password=sc.nextLine();
//		       if(a.login("Customer")) {
//		    	   System.out.println("MENU\n1.SEARCH\n2.BOOKING\n3.VIEW\n4.EDIT BOOKING");
//		    	   int userOption=sc.nextInt();
//		       }
		       break;
		   case 3:
			   System.out.println("SEARCH \n VIEW");
			   
		   case 4:
			  // System.out.println("1.ADMIN \n 2.USER");
			   sc.nextLine();
			   
				   Customer c=new Customer();
				   System.out.println("Enter Id:");
				   sc.nextLine(); 
				   int userId = sc.nextInt();
				   sc.nextLine(); 
				   System.out.println("Enter First Name:");
				   String firstName = sc.nextLine();
				   System.out.println("Enter Last Name:");
				   String lastName = sc.nextLine();
				   System.out.println("Enter Email:");
				   String email = sc.nextLine();
				   System.out.println("Enter Gender:");
				   String gender = sc.nextLine();
				   System.out.println("Enter Phone Number:");
				   long phoneNumber = sc.nextLong();
				   sc.nextLine(); 
				   System.out.println("Enter Password:");
				   String password1 = sc.nextLine();
				   System.out.println("Enter Licence Number:");
				   String licence = sc.nextLine();
				   System.out.println("Enter User Name:");
				   String userName = sc.nextLine();
				  // c.reg(userId, firstName, lastName, email, gender, phoneNumber, password1, licence, userName);
                   System.out.println("REGISTERED SUCCESSFULLY");
			   break;
		       default:
		    	   flag=false;
		    	   break;
		}
		   
		   
		   
		   
		   
		}
	}

}
