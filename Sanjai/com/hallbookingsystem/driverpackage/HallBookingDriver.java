package com.hallbookingsystem.driverpackage;

import com.hallbookingsystem.bookingdetails.Book;
import com.hallbookingsystem.customexception.IntegerException;
import com.hallbookingsystem.customexception.NumberInputException;
import com.hallbookingsystem.customexception.Validate;
import com.hallbookingsystem.dbconnection.DBConnection;
import com.hallbookingsystem.halldetails.Hall;
import com.hallbookingsystem.persondetails.*;
import com.hallbookingsystem.searchdetails.HallDirectory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HallBookingDriver {
    static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
//mainMenu method has case with very first menu
    public static void mainMenu() {
        do {
            System.out.println("------Welcome-------");
            System.out.println("Main menu");
            System.out.println("+-----------------+");
            System.out.println("| 1. Admin        |");
            System.out.println("| 2. Customer     |");
            System.out.println("| 3. Exit         |");
            System.out.println("+-----------------+");
            System.out.print("Enter the option ");
            try {
                byte option = Validate.validateOption(sc.readLine());
                switch (option) {
                    case 1:
                        adminMainMenu();
                        break;
                    case 2:
                        customerMainMenu();
                        break;
                    case 3:
                        System.out.println("Thank you for visiting..!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid Option - Enter Valid Option ");
                        break;
                }
                cancelOverDue();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }
/*cancelTheOverDue Method used to cancel the Approve if the date is exceeded*/
    private static void cancelOverDue() {
        try{
            String cancelQuery = "Update Booking set Book_status='CANCELED' where  start_date>sysdate AND Book_status ='APPROVED' ";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(cancelQuery);
            statement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        mainMenu();
    }

    private static void customerMainMenu() {// Customer Login options
        do{
            try {
                System.out.println("+--------------------+");
                System.out.println("|  1.   Login        |");
                System.out.println("|  2.   Register     |");
                System.out.println("|  3.   Go Back      |");
                System.out.println("|  4.   Exit         |");
                System.out.println("+--------------------+");
                System.out.print("Enter the option ");
                byte option = Validate.validateOption(sc.readLine());
                Authentication account = new Authentication();
                Customer customer;
                switch (option) {
                    case 1:{
                         customer=account.login(AccountType.CUSTOMER);
                        if(customer!=null){
                            customerMenu(customer);
                        }else{
                            continue;
                        }
                        break;
                    }

                    case 2: {
                        customer = account.register(AccountType.CUSTOMER);
                        if (customer!=null) {
                            customerMenu(customer);
                        } else {
                            continue;
                        }
                        break;
                    }
                    case 3:
                        mainMenu();
                        break;
                    case 4:
                        System.out.println("Thank you for visiting..!");
                        System.exit(0);
                        break;
                }
            } catch (IOException | NumberInputException e) {
                System.out.println(e.getMessage());
            }
        }while (true);
    }

    public static void customerMenu( Customer customer) {// Customer's various Menu option
        do {
            try {
                System.out.println("+------------------------+");
                System.out.println("| 1. Search Hall        |");
                System.out.println("| 2. Book Hall          |");
                System.out.println("| 3. WatchList          |");
                System.out.println("| 4. Our Suggestion     |");
                System.out.println("| 5. PaymentDetails     |");
                System.out.println("| 6. Profile Management |");
                System.out.println("| 7. Back               |");
                System.out.println("| 8. Exit               |");
                System.out.println("+-----------------------+");
                System.out.print("Enter the option ");
                byte option = Validate.validateOption(sc.readLine());
                Book book = null;
                switch (option) {
                    case 1:
                        searchHall(customer);
                    case 2:
                        customer.bookingHall(book);
                        break;
                    case 3:
                        watchListMenu(customer);
                        break;
                    case 4:
                        new HallDirectory().suggestedHalls(customer);
                        break;
                    case 5:
                        paymentDetails(customer);
                        break;
                    case 6:
                        customerProfileManagementMenu(customer);
                    case 7:
                        customerMainMenu();
                        break;
                    case 8:
                        System.out.println("Thank you for visiting..!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid Option - Enter Valid Option ");
                        break;
                }
            } catch (IOException | NumberInputException e) {
                System.out.println(e.getMessage());
            }
        }while (true);
    }

    private static void customerProfileManagementMenu(Customer customer) {// Customer Management options
            while(true){
                try {
                    System.out.println("+-------------------------+");
                    System.out.println("| 1. Change Password      |");
                    System.out.println("| 2. Change Email Address |");
                    System.out.println("| 3. Back                 |");
                    System.out.println("| 4. Exit                 |");
                    System.out.println("+-------------------------+");
                    System.out.print("Enter the option ");
                    byte option = Validate.validateOption(sc.readLine());
                    Person person = new Person();
                    switch (option) {
                        case 1:
                            person.changePassword(customer);
                            break;
                        case 2:
                            person.changeMailAddress(customer);
                            break;
                        case 3:
                            customerMenu(customer);
                            break;
                        case 4:
                            System.out.println("ThankYou for visiting..!");
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid Option - Enter Valid Option ");
                            break;
                    }

                } catch (NumberInputException | IOException e) {
                    System.out.println(e.getMessage());
                }
            }
    }

    private static void watchListMenu(Customer customer) {// watchlistMenu used to show
        customer.watchList();
    }
    private static void paymentDetails(Customer customer) {// paymentDetails method shows the pay
        do {
            try {
                System.out.println("+---------------------+");
                System.out.println("| 1. Pay              |");
                System.out.println("| 2. Payment History  |");
                System.out.println("| 3. Refund Request   |");
                System.out.println("| 4. Back             |");
                System.out.println("| 5. Exit             |");
                System.out.println("+---------------------+");
                System.out.print("Enter the option ");
                int option = Validate.validInteger(sc.readLine());
                switch (option) {
                    case 1:
                        customer.pay(customer);
                        break;
                    case 2:
                        customer.paymentHistory(customer);
                        break;
                    case 3:
                        customer.refund(customer);
                        break;
                    case 4:
                        customerMenu(customer);
                        break;
                    case 5:
                        System.out.println("Thank you for visiting..!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (IOException | NumberFormatException | IntegerException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    private static void searchHall(Customer customer) {// Searched on the user
        do{
            try{
                System.out.println("+----------------------------+");
                System.out.println("| 1. Search By Name          |");
                System.out.println("| 2. Search By Price         |");
                System.out.println("| 3. Search By Capacity      |");
                System.out.println("| 4. Sort By Price           |");
                System.out.println("| 5. Sort By Capacity        |");
                System.out.println("| 6. Sort By Name            |");
                System.out.println("| 7. Back                    |");
                System.out.println("| 8. Exit                    |");
                System.out.println("+----------------------------+");
                System.out.print("Enter the option ");
                int option = Validate.validateOption(sc.readLine());
                HallDirectory directory = new HallDirectory();
                switch (option){
                    case 1:
                        directory.searchByName(customer);
                        break;
                    case 2:
                       directory.searchByPrice(customer);
                        break;
                    case 3:
                        directory.searchByCapacity(customer);
                        break;
                    case 4:
                        HallDirectory.shortByPrice(customer);
                        break;
                    case 5:
                        HallDirectory.shortByCapacity(customer);
                        break;
                    case 6:
                        HallDirectory.shortByName(customer);
                        break;
                    case 7:
                        customerMenu(customer);
                        break;
                    case 8:
                        System.out.println("Thanks for visiting..!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid Option - Enter Valid Option ");
                        break;
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }while (true);
    }
    public static void adminMainMenu() {
        do {
            try {
                System.out.println("+------------+");
                System.out.println("| 1. Login   |");
                System.out.println("| 2. Back    |");
                System.out.println("| 3. Exit    |");
                System.out.println("+------------+");
                System.out.print("Enter the option ");
                byte option = Validate.validateOption(sc.readLine());
                switch (option) {
                    case 1:
                        Authentication account = new Authentication();
                        Admin admin = account.login(AccountType.ADMIN);
                        if( admin != null){
                            adminMenu(admin);
                        }
                        else{
                            continue;
                        }
                        break;
                    case 2:
                        mainMenu();
                        break;
                    case 3:
                        System.out.println("Thanks for visiting..!");
                        System.exit(0);
                        break;
                    default: {
                        System.out.println("Invalid Option - Enter Valid Option ");
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }while (true);

    }
    private static void adminMenu(Admin admin) {
        do{
            try{
                System.out.println("+------------------------------------+");
                System.out.println("| 1. Profile Management              |");
                System.out.println("| 2. Booking Management              |");
                System.out.println("| 3. Hall Management                 |");
                System.out.println("| 4. Customer Management             |");
                System.out.println("| 5. Reports                         |");
                System.out.println("| 6. Back                            |");
                System.out.println("| 7. Exit                            |");
                System.out.println("+------------------------------------+");
                System.out.print("Enter the option ");
                byte option = Validate.validateOption(sc.readLine());
                switch (option){
                    case 1:
                        profileManagementMenu(admin);
                        break;
                    case 2:
                        bookingManagement(admin);
                        break;
                    case 3:
                        hallManagementMenu(admin);
                        break;
                    case 4:
                        customerManagement(admin);
                        break;
                    case 5:
                        reports(admin);
                        break;
                    case 6:
                        adminMainMenu();
                        break;
                    case 7:
                        System.out.println("Thank you for visiting..!");
                        DBConnection.getInstance().getConnection().close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid Option - Enter Valid Option ");
                }
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }while (true);

    }

    private static void profileManagementMenu(Admin admin) {
        while(true){
            try {
                System.out.println("+-------------------------+");
                System.out.println("| 1. Change Password      |");
                System.out.println("| 2. Change Email Address |");
                System.out.println("| 3. Back                 |");
                System.out.println("| 4. Exit                 |");
                System.out.println("+-------------------------+");
                System.out.print("Enter the option ");
                byte option = Validate.validateOption(sc.readLine());
                Person person = new Person();
                switch (option) {
                    case 1:
                        person.changePassword(admin);
                        break;
                    case 2:
                        person.changeMailAddress(admin);
                        break;
                    case 3:
                        adminMenu(admin);
                        break;
                    case 4:
                        System.out.println("Thank you for visiting..!");
                        DBConnection.getInstance().getConnection().close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid Option - Enter Valid Option ");
                        break;
                }

            } catch (NumberInputException | IOException | SQLException e) {
                System.out.println(e.getMessage());
            }

        }
    }
    private static void bookingManagement(Admin admin) {

        do{
            try{
                System.out.println("+-------------------------+");
                System.out.println("| 1. Manage Bookings      |");
                System.out.println("| 2. Refund Requests      |");
                System.out.println("| 3. Back                 |");
                System.out.println("| 4. Exit                 |");
                System.out.println("+-------------------------+");
                byte option = Validate.validateOption(sc.readLine());
                switch (option){
                    case 1:
                        admin.manageUserBooking();
                        break;
                    case 2:
                        admin.refundRequest();
                        break;
                    case 3:
                        adminMenu(admin);
                        break;
                    case 4:
                        System.out.println("Thank you for visiting..!");
                        DBConnection.getInstance().getConnection().close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid Input-Enter Valid Input");
                        break;
                }
            } catch (NumberInputException | IOException | SQLException e) {
                System.out.println(e.getMessage());
            }
        }while(true);
    }

    private static void reports(Admin admin) {
        do{
            try{
                System.out.println("+------------------------------------+");
                System.out.println("|           Report Options           |");
                System.out.println("+------------------------------------+");
                System.out.println("|  1. Order by Total Hall Booking    |");
                System.out.println("|  2. Order by high pay User         |");
                System.out.println("|  3. Highly booked Events           |");
                System.out.println("|  4. Highly Booked type             |");
                System.out.println("|  5. Back                           |");
                System.out.println("|  6. Exit                           |");
                System.out.println("+------------------------------------+");
                System.out.print("Enter your choice: ");
                byte option = Validate.validateOption(sc.readLine());

                switch (option) {
                    case 1:
                        admin.orderByHallTotalBooking();
                        break;
                    case 2:
                        admin.orderByHighPayUser();
                        break;
                    case 3:
                        admin.highlyBookedEvent();
                        break;
                    case 4:
                        admin.highlyBookedArrangementType();
                        break;
                    case 5:
                        adminMenu(admin);
                        break;
                    case 6:
                        System.out.println("Thank you for visiting..!");
                        DBConnection.getInstance().getConnection().close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            }catch (NumberInputException | IOException | SQLException e) {
                System.out.println(e.getMessage());
            }
        }while(true);

    }

    private static void customerManagement(Admin admin) {
        do {
            try {
                System.out.println("+-----------------------------------+");
                System.out.println("| 1. Show Customer Details         |");
                System.out.println("| 2. Manage Account                |");
                System.out.println("| 3. Back                          |");
                System.out.println("| 4. Exit                          |");
                System.out.println("+-----------------------------------+");
                System.out.print("Enter the option ");
                byte option = Validate.validateOption(sc.readLine());
                switch (option) {
                    case 1:
                       admin.showCustomerDetails();
                        break;
                    case 2:
                        manageCustomerAccount(admin);
                        break;
                    case 3:
                        adminMenu(admin);
                        break;
                    case 4:
                        System.out.println("Thank you for visiting..!");
                        DBConnection.getInstance().getConnection().close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Enter Valid input");
                        break;
                }
            } catch (NumberInputException | IOException | SQLException e) {

                System.out.println(e.getMessage());
            }
        } while (true);
    }
    private static void hallManagementMenu(Admin admin) {
        do{
            System.out.println("+------------------------------------+");
            System.out.println("| 1. Show All Halls                  |");
            System.out.println("| 2. Add Hall                        |");
            System.out.println("| 3. Update Hall                     |");
            System.out.println("| 4. Remove Hall                     |");
            System.out.println("| 5. Manage Seats                    |");
            System.out.println("| 6. Manage Event                    |");
            System.out.println("| 7. Back                            |");
            System.out.println("| 8. Exit                            |");
            System.out.println("+------------------------------------+");
            System.out.print("Enter the option ");
            Person person = new Person();
            try{
                Hall hall = null;
                byte option = Validate.validateOption(sc.readLine());
                switch (option){
                    case 1:
                        person.showAllHall();
                        break;
                    case 2:
                        admin.addHall(hall);
                        break;
                    case 3:
                        updateHall(admin,hall);
                        break;
                    case 4:
                        admin.removeHall();
                        break;
                    case 5:
                        admin.manageSeats();
                        break;
                    case 6:
                        admin.manageEvents();
                        break;
                    case 7:
                        adminMenu(admin);
                        break;
                    case 8:
                        System.out.println("Thank you for visiting..!");
                        DBConnection.getInstance().getConnection().close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid Input - Enter Valid Input ");
                        break;
                }
            }
            catch (SQLException | NumberInputException |IOException e){
                System.out.println(e.getMessage());
            }
        }while (true);
    }

    private static void updateHall(Admin admin,Hall hall) {
        do {
            System.out.println("+------------------------------------+");
            System.out.println("| 1. Update Price of the Hall       |");
            System.out.println("| 2. Update Capacity of Hall        |");
            System.out.println("| 3. Back                           |");
            System.out.println("| 4. Exit                           |");
            System.out.println("+------------------------------------+");
            System.out.print("Enter the option ");
            try{

                byte option = Validate.validateOption(sc.readLine());
                switch (option) {
                    case 1:
                        admin.updatePrice(hall);
                        break;
                    case 2:
                        admin.updateCapacity(hall);
                        break;
                    case 3:
                        hallManagementMenu(admin);
                        break;
                    case 4:
                        System.out.println("Thank you for visiting..!");
                        DBConnection.getInstance().getConnection().close();
                        System.exit(0);
                        break;
                    default: {
                        System.out.println("Invalid Input - Enter Valid Input ");
                        break;
                    }
                }
            } catch (NumberInputException | SQLException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
        while (true);
    }
    public static void manageCustomerAccount(Admin admin) {
        do{
            try {
                System.out.println("+--------------------------------+");
                System.out.println("|        Menu Selection          |");
                System.out.println("+--------------------------------+");
                System.out.println("|  1. Block Customer             |");
                System.out.println("|  2. Activate Customer          |");
                System.out.println("|  3. Back                       |");
                System.out.println("|  4. Exit                       |");
                System.out.println("+--------------------------------+");
                System.out.print("Enter the option ");
                byte option = Validate.validateOption(sc.readLine());
                switch (option) {
                    case 1:
                        admin.blockCustomer();
                        break;
                    case 2:
                        admin.activeCustomer();
                        break;
                    case 3:
                        customerManagement(admin);
                        break;
                    case 4:
                        DBConnection.getInstance().getConnection().close();
                        System.out.println("Thank you for visiting..!");
                        System.exit(0);
                        break;
                }
            } catch (NumberInputException | IOException |SQLException e) {
                System.out.println(e.getMessage());
            }
        }while (true);
    }
}
