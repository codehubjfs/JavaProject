package com.gotrip.CarRentalSystem;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.carrentalsystem.car.Car;
import com.carrentalsystem.car.CarManagement;
import com.carrentalsystem.exception.NumberException;
import com.carrentalsystem.exception.PasswordException;
import com.carrentalsystem.exception.UsernameException;
import com.carrentalsystem.exception.Validate;

import user.Account;
import user.Admin;
import user.Customer;
public class App 
{
    public static void main( String[] args ) 
    {
    	Scanner sc= new Scanner(System.in);
		boolean flag=true;
		while(flag) {
			System.out.println("=".repeat(200));
			System.out.println(" ".repeat(60)+"WELCOME TO கோTRIP");
			System.out.println("=".repeat(200));
			System.out.println("1.ADMIN\n2.USER\n3.GUEST\n4.REGISTER\n5.EXIT");
			System.out.println(".".repeat(200));
			try {
			int option=Validate.ValidateNumber(sc.nextLine());		
			Account a=new Account();
		   switch(option) {
		   case 1:
		   {
				try {
					if(a.login("Admin")) {
						   System.out.println("MENU\n1.CAR MANAGEMENT\n2.RENTAL PACKAGE MANAGEMENT\n3.USER MANAGEMENT\n4.REPORT");
						   int adminOption=Validate.ValidateNumber(sc.nextLine());
						   if(adminOption==1) {
							   System.out.println("Menu\n1.Add Car\n2.Delete Car\n3.Update Car");
							    int carOption=Validate.ValidateNumber(sc.nextLine());
							   CarManagement add=new CarManagement();
							   if(carOption==1)
							   add.addcar();
							   else if(carOption==2) {
								   add.deletecar();
							   }
						   }
					   }
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (PasswordException e) {
					e.printStackTrace();
				}
		   }
		       break;
		   case 2:{
			  
		       try {
				if(a.login("Customer")) {
					   System.out.println("MENU\n1.SEARCH\n2.BOOKING\n3.VIEW\n4.EDIT BOOKING");
					   int userOption=Validate.ValidateNumber(sc.nextLine());
				   }
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}catch(PasswordException e) {
				System.out.println(e.getMessage());
			}
		       break;
		   }
		   case 3:{
			   System.out.println("SEARCH \n VIEW");
			   break;
		   }
		   case 4:   {
			try {
				a.reg();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (PasswordException e) {
				System.out.println(e.getMessage());
			} catch (UsernameException e) {
				e.printStackTrace();
			}
			   break;
		   }
		       default:{
		    	   flag=false;
		    	   break;
		   }
		}
		  }catch(NumberException e) {
				System.out.println(e.getMessage());
			}
		}		
}
}
