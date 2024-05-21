package com.hallbookingsystem.persondetails;

import com.hallbookingsystem.dbconnection.DBConnection;
import com.hallbookingsystem.halldetails.Hall;
import com.hallbookingsystem.bookingdetails.Book;
import com.hallbookingsystem.bookingdetails.BookingStatus;
import com.hallbookingsystem.customexception.*;
import com.hallbookingsystem.primarykey.PrimaryKeyProvider;
import com.hallbookingsystem.searchdetails.HallDirectory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/*
* Person class implements the attributes and the methods which are related to the class
* @author Sanjai P
* @since 14-May-2024
* */


public class Person {
    private int userId;
    private String name;
    private Account account; //Aggregation or composition
    private Gender gender;
    private String email;
    private String mobileNumber;
    private Address address;
    // Person Constructor to initialize the filed members
    public Person(int userId, String name, Account account, Gender gender, String email, String mobileNumber, Address address) {
        this.userId= userId;
        this.name = name;
        this.account = account;
        this.gender = gender;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.address = address;
    }

    public Person(int userId, String name, Gender gender, String email, String mobileNumber, Address address) {
        this.userId = userId;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.address = address;
    }
    public Person(String name, Gender gender, String email, String mobileNumber, Address address) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.address = address;
    }


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int user_id) {
        this.userId = user_id;
    }
    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

    public void showAllHall() {
                List<Hall> list = new HallDirectory().listHall();
                System.out.println("+-------------------------------------------------------------------+");
                System.out.println("|                          Hall Details                             |");
                System.out.println("+-------------------------------------------------------------------+");
                System.out.printf("│  %-20s │ %-16s │ %-10s │ %-10s │%n","Hall Name","Price per Day","Capacity","AC");
                System.out.println("+-------------------------------------------------------------------+");
                list.stream().filter(Hall::isAvail).forEach(hall -> System.out.printf("│ %-20s │ %-16.2f │ %-10d │ %-10s  │%n", hall.getHallName(), hall.getPrice(),hall.getCapacity(), hall.getIsAcHall() ? "Yes" : "No"));
                System.out.println("+-------------------------------------------------------------------+");

    }
    public <T extends Person> void changePassword(T person) {
        boolean exitFlag = true;
        byte count = 0; // Moved count outside the loop to count attempts
        do {
            try {
                System.out.println("Enter the old user Password :");
                String oldPassword = sc.readLine();
                if (oldPassword.equals(person.getAccount().getPassword())) {
                    boolean passwordFlag = false;
                    String newPassword = "";
                    do {
                        try {
                            System.out.print("Enter the new password :");
                            newPassword = Validate.validatePassword(sc.readLine());
                            if (oldPassword.equals(newPassword)) {
                                passwordFlag = true;
                                System.out.println("New password should not be the same as the old password.");
                            } else {
                                String updateQuery = "UPDATE users SET password = ? WHERE user_id = ?";
                                PreparedStatement updateStatement = DBConnection.getInstance().getConnection().prepareStatement(updateQuery);
                                updateStatement.setString(1, newPassword);
                                updateStatement.setInt(2, person.getUserId());
                                int rowsAffected = updateStatement.executeUpdate();
                                if (rowsAffected > 0) {
                                    System.out.println("Password updated successfully.");
                                    return;
                                } else {
                                    System.out.println("Failed to update password.");
                                }
                            }
                        } catch (PasswordException e) {
                            passwordFlag = true;
                            System.out.println(e.getMessage());
                        }
                    } while (passwordFlag);

                } else {
                    count++;
                    System.out.println("Old password does not match.");
                }
                if (count == 3) {
                    DBConnection.getInstance().getConnection().close();
                    System.out.println("Password attempt limit exceeded.");
                    System.exit(0);
                }
            } catch (SQLException | IOException ex) {
                System.out.println(ex.getMessage());
            }
            boolean optionFlag = false;
            do {
                try {
                    System.out.println("1. To Continue\n2. Back");
                    byte option = Validate.validateOption(sc.readLine());
                    if (option == 1) {
                        exitFlag = true;
                    } else if (option == 2) {
                        exitFlag = false;
                        break;
                    } else {
                        optionFlag = true;
                        System.out.println("Enter valid input");
                    }
                } catch (NumberInputException | IOException e) {
                    optionFlag = true;
                    System.out.println(e.getMessage());
                }
            } while (optionFlag);
        } while (exitFlag);
    }



    public  <T extends  Person> void changeMailAddress(T user) {
            boolean exitFlag = false;
            do {
                try {
                    System.out.println("Enter the new email address:");
                    String newEmail = sc.readLine();
                    if(newEmail.equals(user.getEmail())){
                        System.out.println("Error: The current email ID and the new email ID are the same.");
                        continue;
                    }

                    String updateQuery = "UPDATE users SET email_id = ? WHERE user_id = ?";
                    PreparedStatement updateStatement =DBConnection.getInstance().getConnection().prepareStatement(updateQuery);
                    updateStatement.setString(1, newEmail);
                    updateStatement.setInt(2,user.getUserId());

                    int rowsAffected = updateStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Email address updated successfully.");
                    } else {
                        System.out.println("Failed to update email address.");
                    }

                    System.out.println("1. To Continue\n2. Back");
                    boolean optionFlag = false;
                    do {
                        try {
                            byte option = Validate.validateOption(sc.readLine());
                            if (option == 1) {
                                exitFlag = true;
                            } else if (option == 2) {
                                exitFlag = false;
                            }
                        } catch (NumberInputException e) {
                            optionFlag = true;
                            System.out.println(e.getMessage());
                        }
                    } while (optionFlag);
                } catch (IOException | SQLException e) {
                    System.out.println(e.getMessage());
                }
            } while (exitFlag);

    }

}
