package com.smartcliff.TaskManagementSystem;

import java.sql.SQLException;
import java.util.Scanner;

import com.taskmanagement.authentication.UserAuthentication;
import com.taskmanagement.exception.InvalidNumberException;
import com.taskmanagement.role.Admin;
import com.taskmanagement.role.Employee;
import com.taskmanagement.role.Manager;

public class App {

    // Method to get the role from the user
    public static int getRole(Scanner sc) {
        while (true) {
            try {
                System.out.println("*".repeat(200));
                System.out.println("Press 1 to login as Employee...");
                System.out.println("Press 2 to login as Manager...");
                System.out.println("Press 3 to login as Admin...");
                System.out.println("Press 4 to exit..");
                System.out.println("*".repeat(200));

                System.out.println("Enter your choice: ");
                int role = Integer.parseInt(sc.next());

                if (role != 1 && role != 2 && role != 3 && role != 4) {
                    throw new InvalidNumberException("Please enter a valid number");
                }

                return role;
            } catch (NumberFormatException e) {
                System.out.println("You have entered a character!!!... Please enter a number!");
                System.out.println();
            } catch (InvalidNumberException e) {
                System.out.println("!" + e.getMessage() + "!");
            }
        }
    }

    // Main method
    public static void main(String args[]) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("*".repeat(200));
        System.out.println("---------Welcome--------");

        int role;

        while (true) {
            role = getRole(sc); // Get the role from the user
            if (role == 4) { // Exit if the user chooses to
                System.out.println("Thank you...");
                System.out.println("----------Exit----------");
                break;
            }

            UserAuthentication log = new UserAuthentication(); // Create an instance of UserAuthentication

            switch (role) {
                case 1: // Employee login
                    boolean empLoginSuccess = false;
                    String mail = "";
                    do {
                        System.out.println("Enter the Employee Email: ");
                        mail = sc.next().trim();

                        System.out.println("Enter the password: ");
                        String password = sc.next().trim();

                        empLoginSuccess = log.loginemp(mail, password); // Authenticate employee
                    } while (!empLoginSuccess);
                    Employee.empuser(sc, mail); // Direct to employee interface
                    break;

                case 2: // Manager login
                    boolean manLoginSuccess = false;
                    String email = "";
                    int empid = 0;
                    do {
                        System.out.println("Enter the Manager Email: ");
                        email = sc.next().trim();

                        System.out.println("Enter the password: ");
                        String password1 = sc.next().trim();

                        manLoginSuccess = log.loginman(email, password1); // Authenticate manager
                        empid = UserAuthentication.manager(email); // Get manager ID
                    } while (!manLoginSuccess);
                    Manager.maguser(sc, email, empid); // Direct to manager interface
                    break;

                case 3: // Admin login
                    boolean adminLoginSuccess = false;
                    do {
                        System.out.println("Enter the admin Email: ");
                        String username2 = sc.next().trim();
                        System.out.println("Enter the password");
                        String password2 = sc.next().trim();

                        adminLoginSuccess = log.loginadmin(username2, password2); // Authenticate admin
                    } while (!adminLoginSuccess);
                    Admin.adminuser(sc); // Direct to admin interface
                    break;

                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }
    }
}
