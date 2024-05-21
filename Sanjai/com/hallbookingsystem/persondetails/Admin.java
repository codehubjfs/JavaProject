package com.hallbookingsystem.persondetails;

import com.hallbookingsystem.dbconnection.DBConnection;
import com.hallbookingsystem.halldetails.Events;
import com.hallbookingsystem.halldetails.Hall;
import com.hallbookingsystem.halldetails.Seats;
import com.hallbookingsystem.customexception.IntegerException;
import com.hallbookingsystem.customexception.NumberInputException;
import com.hallbookingsystem.customexception.Validate;
import com.hallbookingsystem.primarykey.PrimaryKeyProvider;
import com.hallbookingsystem.searchdetails.HallDirectory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class Admin extends Person {
    public Admin() {
        super();
    }

    public Admin(Person p) {

    }

    static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

    public Admin(int users, String name, Account account, Gender gender, String email, String phoneNumber, Address address) {
        super(users, name, gender, email, phoneNumber, address);
    }


    public void showCustomerDetails() {
        try {
            String customerDetailsQuery = "Select * from users where ACCOUNT_TYPE = 'CUSTOMER' ";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(customerDetailsQuery);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Customer> customerList = new ArrayList<>();
            Customer customer;
            while (resultSet.next()) {
                String address = resultSet.getString("address");
                String[] add = address.split(",");
                Address addressObj = new Address(add[0], add[1], add[2], add[3], add[4]);
                customer = new Customer(resultSet.getString("name"), Gender.valueOf(resultSet.getString("gender")),
                        resultSet.getString("email_ID"), resultSet.getString("Phone_Number"), addressObj);
                customerList.add(customer);
            }
            Collections.sort(customerList);
            System.out.println("+-----------------------------------------------------------------------------------------------------+");
            System.out.printf("| %-18s | %-10s | %-25s | %-15s | %-50s |%n", "Name", "Gender", "Email ID", "Phone Number", "Address");
            System.out.println("+----------------------------------------------------------------------------------------------------+");
            for (Customer customers : customerList) {
                System.out.printf("| %-18s | %-10s | %-25s | %-15s | %-50s |%n",
                        customers.getName(), customers.getGender(), customers.getEmail(),
                        customers.getMobileNumber(), customers.getAddress());
            }
            System.out.println("+-----------------------------------------------------------------------------------------------------+");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    // addHall method represent to add new hall to the system
    public Hall addHall(Hall hall) {
        boolean mainFlag = true; // Set initial flag value to true to enter the loop
        do {
            try {
                String hallName = null;

                do {
                    System.out.println("Enter the Hall Name (at most 30 ):");
                    hallName = sc.readLine();
                    String finalHallName = hallName;
                    if (new HallDirectory().listHall().stream().filter(x -> x.getHallName().equalsIgnoreCase(finalHallName)).equals(hall)) {

                    } else if (hallName.length() < 30) {
                        break;
                    } else {
                        System.out.println("Hall Name should be under 30 characters");
                    }
                } while (true);

                boolean isAcHall = false;
                while (true) {
                    System.out.println("Is it an AC Hall? (yes/no): ");
                    String input = sc.readLine().toLowerCase();
                    if (input.equals("yes")) {
                        isAcHall = true;
                        break;
                    } else if (input.equals("no")) {
                        isAcHall = false;
                        break;
                    } else {
                        System.out.println("Invalid Input! Please enter 'yes' or 'no'.");
                    }
                }
                System.out.println("Enter the Capacity of hall :");
                boolean flag = false;
                int hallCapacity = 0;
                do {
                    try {
                        hallCapacity = Validate.validInteger(sc.readLine());
                    } catch (IntegerException e) {
                        flag = true;
                        System.out.println(e.getMessage());
                    }
                } while (flag);
                float prices = 0.0f;
                do {
                    try {
                        System.out.print("Enter the Price per Day: ");
                        prices = Float.parseFloat(sc.readLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println(e.getMessage());
                    }
                } while (true);


                // Displaying available events
                List<String> eventName = new Events().displayEvents();
                int i = 0;
                for (String set : eventName) {
                    System.out.println((++i) + ". " + set);
                }

                // Adding events to the hall
                System.out.println("Enter the Events can done in the Hall (Enter 'S' to stop adding): ");
                LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
                LinkedHashSet<Events> events = new LinkedHashSet<>();
                while (true) {
                    String input = sc.readLine();
                    if ("S".equalsIgnoreCase(input)) {
                        break;
                    } else if (!input.matches("\\d+")) {
                        System.out.println("Invalid Input! Please enter a number or 'S' to stop adding.");
                        continue;
                    }
                    int eventNumber = Integer.parseInt(input);
                    if (eventNumber < 1 || eventNumber > eventName.size()) {
                        System.out.println("Invalid Event Number! Please enter a valid event number.");
                        continue;
                    }
                    linkedHashSet.add(eventNumber);
                    events.add(new Events(eventName.get(eventNumber - 1), eventNumber));
                }
                // Displaying available seating arrangements
                List<String> arrangementList = new Seats().arrangementList();
                i = 0;
                for (String list : arrangementList) {
                    System.out.println((++i) + ". " + list);
                }

                // Adding seating arrangements to the hall
                System.out.println("Enter the Arrangements to be added (Enter 'S' to stop adding): ");
                LinkedHashSet<Integer> linkedHashSetSeat = new LinkedHashSet<>();
                List<Integer> capacity = new ArrayList<>();
                System.out.println("Seats   Capacity");
                LinkedHashSet<Seats> seats = new LinkedHashSet<>();// Linked Hash set to store the Seats object
                while (true) {
                    System.out.println("Enter seat type number (or 'S' to stop adding):");
                    String input = sc.readLine().trim();
                    if ("S".equalsIgnoreCase(input)) {
                        break;
                    } else if (!input.matches("\\d+")) {
                        System.out.println("Invalid Input! Please enter a number or 'S' to stop adding.");
                        continue;
                    }
                    int arrangementNumber = Validate.validInteger(input);
                    if (arrangementNumber < 1 || arrangementNumber > arrangementList.size()) {
                        System.out.println("Invalid Arrangement Number! Please enter a valid arrangement number.");
                        continue;
                    }
                    flag = false;
                    do {
                        try {
                            System.out.println("Enter seat capacity for arrangement " + arrangementNumber + ":");
                            String capacityInput = sc.readLine().trim();
                            int capacityValue = Validate.validInteger(capacityInput);
                            if (capacityValue < 0) {
                                flag = true;
                                System.out.println("Negative input not allowed");
                            } else {
                                if (capacityValue < hallCapacity) {
                                    capacity.add(capacityValue);
                                    linkedHashSetSeat.add(arrangementNumber);
                                    seats.add(new Seats(arrangementNumber, arrangementList.get(arrangementNumber - 1), capacityValue));
                                    flag = false;// Reset flag since input was successful
                                } else {
                                    System.out.println("Invalid input - Enter the hall seat capacity is less than hall capacity ");
                                    flag = true;
                                }
                            }
                        } catch (IntegerException e) {
                            flag = true;
                            e.printStackTrace(); // Or handle the customexception according to your needs
                        }
                    } while (flag);
                }

                int hallKey = PrimaryKeyProvider.primaryKey("halls");
                hall = new Hall(hallKey, hallName, prices, isAcHall, hallCapacity, seats, events);
                String query = "INSERT INTO halls VALUES (?, ?, ?, ?,'A',?)";
                PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);
                statement.setInt(1, hallKey);
                statement.setString(2, hall.getHallName());
                statement.setFloat(3, hall.getPrice());
                statement.setString(4, hall.getIsAcHall() ? "Ac" : "NonAc");
                statement.setInt(5, hall.getCapacity());
                statement.executeUpdate();

                // Inserting seating arrangements into the database
                i = 0;
                for (Seats arrangementNumber : seats) {
                    String insertQuery = "INSERT INTO Hall_Seating  (hall_id, arrangement_id,capacity) VALUES (?, ?,?)";
                    PreparedStatement insertStatement = DBConnection.getInstance().getConnection().prepareStatement(insertQuery);
                    insertStatement.setInt(1, hallKey);
                    insertStatement.setInt(2, arrangementNumber.getSeatId());
                    insertStatement.setInt(3, arrangementNumber.getCapacity());
                    insertStatement.executeUpdate();
                }

                // Inserting booked events into the database
                for (Events eventNumber : events) {//the method used to insert the event
                    String insertQuery = "INSERT INTO book_event (hall_id, event_id) VALUES (?, ?)";
                    PreparedStatement insertStatement = DBConnection.getInstance().getConnection().prepareStatement(insertQuery);
                    insertStatement.setInt(1, hallKey);
                    insertStatement.setInt(2, eventNumber.getEventId());

                    insertStatement.executeUpdate();
                }

                System.out.println("Hall added successfully!");

                // Asking user if they want to add another hall
                System.out.println("Do you want to add another hall? (yes/no): ");
                String input = sc.readLine().toLowerCase();


                if (!input.equalsIgnoreCase("yes")) {
                    mainFlag = false; // Set mainFlag to false to exit the loop
                }

            } catch (IOException | SQLException | IntegerException e) {
                e.printStackTrace();
            }
        } while (mainFlag); // Loop will continue until mainFlag is true
        return hall;
    }
    // removeHall method used to remove the hall from the
    public void removeHall() {
        boolean exitFlag = false;
        do{
            HallDirectory hallDirectory = new HallDirectory();
            List<Hall> hallList =hallDirectory.listHall();
            System.out.println("+----+----------------------+");
            System.out.printf("| %-2s | %-20s|%n","SNO","HallName");
            System.out.println("+----+----------------------+");
            int i = 0;
            LinkedHashMap<Integer,Hall> mapHall= new LinkedHashMap<>();
            for(Hall hallDetails:hallList){
                if(hallDetails.isAvail()){
                    System.out.printf("| %-2d | %-20s |%n",(++i),hallDetails.getHallName());
                    mapHall.put(i,hallDetails);
                }
            }
            System.out.println("+----+--------------------+");
            do{
                try{
                    System.out.println("Enter the hall SNO to select the hall");
                    int option = Validate.validateOption(sc.readLine());   // getting input in the format of string to matches with exit
                        if (option > 0 && option <= mapHall.size()) {
                            int hallId = mapHall.get(option).getHallId();
                            String query = "Update halls set hallAvail = 'N' where hall_ID = ?";
                            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);
                            statement.setInt(1, hallId);
                            if (statement.executeUpdate() > 0) {
                                System.out.println(" Hall Removed successfully ");
                                break;
                            } else {
                                System.out.println(" Hall not removed ");
                            }

                    } else{
                            System.out.println("Enter the valid input 1 - "+mapHall.size());
                        }
                }catch (IOException | SQLException | NumberInputException e){
                    System.out.println(e.getMessage());
                }
            }while(true);

            do{
                System.out.println("1.Continue remove \n2.Exit");
                try {
                    byte option = Validate.validateOption(sc.readLine().trim());
                    if(option==1){
                        exitFlag = true;
                        break;
                    } else if (option==2) {
                        exitFlag = false;
                        break;
                    }
                    else{
                        System.out.println("Enter the valid intput");
                    }
                } catch (IOException | NumberInputException e) {
                    System.out.println(e.getMessage());;
                }

            }while (true);

        }while(exitFlag);

    }

    public void updatePrice(Hall hall) throws IOException {
        boolean exitFlag = false;
        do {
            String hallName = "";
            try {
                do {
                    System.out.println("Enter the Hall Name to be update :");
                    hallName = sc.readLine();
                    if (hallName.matches("[\\d+]")) {
                        System.out.println("Invalid Input - Enter at least one Character");
                        continue;
                    } else {
                        break;
                    }
                } while (true);


                boolean flag = false;
                float updatePrice = 0.0f;
                do {
                    System.out.println("Enter the Price to be Update :");
                    try {
                        updatePrice = Validate.validInteger(sc.readLine());
                    } catch (IntegerException | IOException e) {
                        System.out.println("Enter the Valid Price");
                        System.out.println(e.getMessage());
                    }
                } while (flag);

                hall = new Hall();
                hall.setHallName(hallName);
                hall.setPrice(updatePrice);
                String query = "Update halls  set HALL_PRICE=? where hall_name = ?";// Query to Update the
                PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);
                statement.setFloat(1, hall.getPrice());
                statement.setString(2, hall.getHallName());
                if (statement.executeUpdate() > 0) {
                    System.out.println("Price Updated Successfully  to " + hall.getHallName());
                } else {
                    System.out.println("Hall Not Existed");
                }
                flag = false;
                byte option = -1;
                do {
                    try {
                        System.out.println("1.Continue Update \n2.Back");
                        option = Validate.validateOption(sc.readLine());
                        if (option == 1) {
                            exitFlag = true;
                        } else if (option == 2) {
                            exitFlag = false;
                        } else {
                            System.out.println("Invalid ");
                        }
                    } catch (NumberInputException e) {
                        flag = true;
                        System.out.println(option);
                    }
                } while (flag);

            } catch (IOException | SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        while (exitFlag);
    }

    public void updateCapacity(Hall hall) {
        boolean exitFlag = false;
        do{
            hall = new Hall();
            HallDirectory hallDirectory = new HallDirectory();
            List<Hall> hallList =hallDirectory.listHall();
            System.out.println("+----+----------------------+------------+");
            System.out.printf("| %-2s | %-20s| %-10s |%n","SNO","Hall Name","Hall Capacity");
            System.out.println("+----+----------------------+------------+");
            int i = 0;
            LinkedHashMap<Integer,Hall> mapHall= new LinkedHashMap<>();
            for(Hall hallDetails:hallList){
                if(hallDetails.isAvail()){
                    System.out.printf("| %-2d | %-20s |%-10d |%n",(++i),hallDetails.getHallName(),hallDetails.getCapacity());
                    mapHall.put(i,hallDetails);
                }
            }
            System.out.println("+----+----------------------+------------+");
            do{
                try{
                    System.out.println("Enter the hall SNO to select the hall");
                    int option = Validate.validateOption(sc.readLine());   // getting input in the format of string to matches with exit
                    if (option > 0 && option <= mapHall.size()) {
                        System.out.println("Enter the Maximum Capacity to be Update :");
                        boolean flag = false;
                        int updateCapacity = 0;
                        do {
                            try {
                                updateCapacity = Validate.validInteger(sc.readLine());
                            } catch (IntegerException | IOException e) {
                                flag = true;
                                System.out.println(e.getMessage());
                            }
                        } while (flag);

                        hall.setHallId( mapHall.get(option).getHallId());
                        hall.setCapacity(updateCapacity);
                        String query = "Update halls  set HALLCAPACITY=? where hall_ID = ?";
                        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);
                        statement.setInt(1, hall.getCapacity());
                        statement.setInt(2, hall.getHallId());
                        if (statement.executeUpdate() > 0) {
                            System.out.println("Capacity Updated Successfully  to " + mapHall.get(option).getHallName());
                        }

                    } else{
                        System.out.println("Enter the valid input 1 - "+mapHall.size());
                    }
                    do{
                        System.out.println("1.Continue update \n2.Exit");
                        try {
                            byte exitOption = Validate.validateOption(sc.readLine().trim());
                            if(exitOption==1){
                                exitFlag = true;
                                break;
                            } else if (exitOption==2) {
                                exitFlag = false;
                                break;
                            }
                            else{
                                System.out.println("Enter the valid intput");
                            }
                        } catch (IOException | NumberInputException e) {
                            System.out.println(e.getMessage());;
                        }
                    }while (true);
                    break;
                }catch (IOException | SQLException | NumberInputException e){
                    System.out.println(e.getMessage());
                }
            }while(true);
        }while(exitFlag);

    }


    public void manageUserBooking() {
        boolean exitFlag = false;
        int i = 0;
        do {
            try {
                String query = "SELECT " +
                        "u.name, " +
                        "b.requested_time, " +
                        "b.book_id, " + // Corrected column name
                        "b.start_date, " +
                        "b.end_date, " +
                        "h.hall_name, " +
                        "e.event_name, " +
                        "b.BOOK_STATUS, " +
                        "Trunc(b.end_date - b.start_date)*h.hall_price as total_price," +
                        "NVL(p.price,0.0) as paid_Rupees," +
                        "NVL(p.paid_status,'Un Paid') AS payment_status " +
                        "FROM " +
                        "booking b " +
                        "JOIN " +
                        "users u ON b.user_id = u.user_id " +
                        "JOIN " +
                        "halls h ON b.hall_id = h.hall_id " +
                        "JOIN " +
                        "event e ON b.event_id = e.event_id " +
                        "JOIN " +
                        "Seating_Arrangement a ON b.arrangement_id = a.arrangement_id " +
                        "LEFT JOIN " +
                        "PAYMENT p ON b.book_id = p.book_id " +
                        "WHERE b.start_Date > sysdate " +
                        "ORDER BY b.requested_time ";

                PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);
                ResultSet set = statement.executeQuery();
                HashMap<Integer, Integer> map = new HashMap<>();
                System.out.println("+-----+---------------------+-----------------------------+------------------+-----------------------------+-------------------------+---------------------+-------------------+-----------------------------+");
                System.out.println("| Sno |      User Name      |       Requested Time       |    Start Date    |          End Date          |       Hall Name        |     Event Name     |  Booking Status   |   Hall Price   | Payment Status |");
                System.out.println("+-----+---------------------+-----------------------------+------------------+-----------------------------+-------------------------+---------------------+-------------------+-----------------------------+");

                while (set.next()) {
                    i++;
                    Timestamp requiredTamp = set.getTimestamp("REQUESTED_TIME"); // Make the Date format in the(dd/MM/yyyy HH:mm:ss)
                    LocalDateTime requiredTime = requiredTamp.toLocalDateTime();
                    DateTimeFormatter requireDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    String requireDateTime =  requiredTime.format(requireDateFormatter);

                    Date startDate = set.getDate("START_DATE");// Make the Start Date format in (dd/MM/yyyy)
                    LocalDate localStartDate = startDate.toLocalDate();
                    DateTimeFormatter startDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String startDateString = localStartDate.format(startDateFormatter);

                    Date endDate = set.getDate("end_Date");// Make the End Date format in (dd/MM/yyyy)
                    LocalDate localEndDate = endDate.toLocalDate();
                    DateTimeFormatter endDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String endDateString = localEndDate.format(endDateFormatter);

                    String price = set.getString("total_price");
                    System.out.printf("| %-3d | %-19s | %-27s | %-16s | %-26s | %-22s | %-18s | %-17s | %-14s | %13s |\n",
                            i,
                            set.getString("NAME"),
                            requireDateTime,
                            startDateString,
                            endDateString,
                            set.getString("HALL_NAME"),
                            set.getString("EVENT_NAME"),
                            set.getString("BOOK_STATUS"),
                            set.getInt("total_price"),
                            set.getString("payment_status")
                    );
                    System.out.println("+-----+---------------------+-----------------------------+------------------+-----------------------------+-------------------------+---------------------+-------------------+----------------+");
                    map.put(i, set.getInt("book_id"));
                }
                boolean flag = false;
                int bookId = -1;
                do {
                    if (i > 0) {
                        try {
                            System.out.println("Select the user by SNO :");
                            int choice = Validate.validInteger(sc.readLine());
                            if (choice > 0 && choice <= map.size() && map.containsKey(choice)) {
                                boolean optionFlag = false;
                                do {
                                    try {
                                        System.out.println("1.Approve 2.Canceled 3.Back");
                                        byte option = Validate.validateOption(sc.readLine());
                                        if (option == 1) {
                                            bookId = map.get(choice);
                                            String queryUpdate = "UPDATE booking SET book_status = 'APPROVED' WHERE book_id = ? AND book_status = 'PENDING'";
                                            PreparedStatement updateStatement = DBConnection.getInstance().getConnection().prepareStatement(queryUpdate);
                                            updateStatement.setInt(1, bookId);
                                            int rowsAffected = updateStatement.executeUpdate();
                                            if (rowsAffected > 0) {
                                                System.out.println("Booking with has been approved.");
                                            } else {
                                                System.out.println("No rows were updated. Either the booking ID was not found or the booking status was not 'Pending'.");
                                            }
                                        } else if (option == 2) {
                                            bookId = map.get(choice);
                                            String queryUpdate = "UPDATE booking SET book_status = 'CANCELED' WHERE book_id = ? AND book_status = 'PENDING' OR book_status = 'APPROVED'";
                                            PreparedStatement updateStatement = DBConnection.getInstance().getConnection().prepareStatement(queryUpdate);
                                            updateStatement.setInt(1, bookId);
                                            int rowsAffected = updateStatement.executeUpdate();
                                            if (rowsAffected > 0) {
                                                System.out.println("Booking with ID " + bookId + " has been Canceled.");
                                            } else {
                                                System.out.println("No rows were updated. Either the booking ID was not found or the booking status was not 'Pending'.");
                                            }
                                        } else if (option == 3) {
                                            return;
                                        }
                                    } catch (NumberInputException | SQLException | IOException e) {
                                        optionFlag = true;
                                    }
                                } while (optionFlag);
                            }
                        } catch (IOException e) {
                            flag = true;
                            System.out.println(e.getMessage());
                        }
                        boolean continueFlag = false;
                        do {
                            try {
                                System.out.println("1.Continue Approvals \n2.Show Details \n3.Exit");
                                byte option = Validate.validateOption(sc.readLine());
                                if (option == 1) {
                                    flag = true;
                                } else if (option == 2) {
                                    manageUserBooking();
                                } else if (option == 3) {
                                    flag = false;
                                }
                            } catch (NumberInputException e) {
                                continueFlag = true;
                                System.out.println(e.getMessage());
                            }
                        } while (continueFlag);
                    }
                } while (flag);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (exitFlag);
    }

    //blockCustomer method to implement the user to block their account
    public void blockCustomer() {
        boolean flag = false;
        do {
            try {
                System.out.println("Enter the MailAddress to block account:");
                String emailId = sc.readLine();
                String blockQuery = "Update users set account_status  = 'BLOCKED' where email_Id = ? AND account_type ='ADMIN'";
                PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(blockQuery);
                statement.setString(1, emailId);
                if (statement.executeUpdate() > 0) {
                    System.out.println(emailId + "User is Blocked ");
                } else {
                    System.out.println(emailId + "User Couldn't Block Or User Not Existed");
                }

                do {
                    try {
                        System.out.println("1. Continue\n 2.Back");
                        byte backOption = Validate.validateOption(sc.readLine());
                        if (backOption == 1) {
                            flag = true;
                        } else if (backOption == 2) {
                            break;
                        }
                        else{
                            System.out.println("Invalid Input - Enter a valid input ");
                        }
                    } catch (NumberInputException e) {
                        System.out.println(e.getMessage());
                    }
                } while (true);

            } catch (SQLException | IOException e) {
                flag = true;
                System.out.println(e.getMessage());
            }
        } while (flag);
    }

    public void activeCustomer() {
        boolean flag = false;
        do {
            try {
                System.out.println("Enter the MailAddress to block account:");
                String emailId = sc.readLine();
                String blockQuery = "Update users set account_status  = 'ACTIVE' where email_Id = ? AND account_type ='ADMIN'";
                PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(blockQuery);
                statement.setString(1, emailId);
                if (statement.executeUpdate() > 0) {
                    System.out.println(emailId + "User is Blocked ");
                } else {
                    System.out.println(emailId + "User Couldn't Block Or User Not Existed");
                }

                boolean optionFlag = false;
                do {
                    try {
                        System.out.println("1. Continue\n2. Back");
                        byte backOption = Validate.validateOption(sc.readLine());
                        if (backOption == 1) {
                            flag = true;
                        } else if (backOption == 2) {
                            optionFlag = false;
                        } else {
                            System.out.println("Enter the valid Input");
                        }
                    } catch (NumberInputException e) {
                        optionFlag = true;
                        System.out.println(e.getMessage());
                    }
                } while (true);

            } catch (SQLException | IOException e) {
                flag = true;
                System.out.println(e.getMessage());
            }
        } while (flag);
    }

    public void refundRequest() {
        try {
            String query = "SELECT p.payment_id,b.start_date, h.hall_name, (b.end_date - b.start_date) AS Days, " +
                    "h.hall_price * (b.end_date - b.start_date) AS Total_Price, " +
                    "(h.hall_price * (b.end_date - b.start_date) - " +
                    "(SELECT MAX(price) FROM payment p JOIN booking b ON p.book_id = b.book_id)) AS paid," +
                    "p.paid_status " +
                    "FROM payment p " +
                    "JOIN booking b ON p.book_id = b.book_id " +
                    "JOIN halls h ON b.hall_id = h.hall_id " +
                    "where start_DATE>sysdate AND p.paid_status = 'ADVANCED' OR p.paid_status ='REFUND REQUEST'";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);
            ResultSet set = statement.executeQuery();
            System.out.println("+---------------------+------------------+------+-------------+-------------+-----------------------+");
            System.out.println("|  SNO   |Start Date           |     Hall Name    | Days | Total Price | Paid |   Payment Status    |");
            System.out.println("+---------------------+------------------+------+-------------+-------------+-----------------------+");
            boolean isPayFound = false;
            int paymentID = 0;
            int i = 0;
            String status = "";
            LinkedHashMap<Integer, Integer> snoMap = new LinkedHashMap<>();
            LinkedHashMap<Integer, String> statusMap = new LinkedHashMap<>();
            while (set.next()) {
                isPayFound = true;

                Date startDate = set.getDate("START_DATE");// Make the Start Date format in (dd/MM/yyyy)
                LocalDate localStartDate = startDate.toLocalDate();
                DateTimeFormatter startDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String startDateString = localStartDate.format(startDateFormatter);

                System.out.printf("| %-5d | %-19s | %-16s | %-4d | %-11.2f | %-7.2f | %-15s |\n",
                        ++i,
                        startDateString,
                        set.getString("hall_name"),
                        set.getInt("Days"),
                        set.getDouble("Total_Price"),
                        set.getFloat("paid"),
                        set.getString("paid_Status"));
                paymentID = set.getInt("payment_id");
                status = set.getString("paid_status");
                statusMap.put(i, status);
                snoMap.put(i, paymentID);
            }
            if (!isPayFound) {
                System.out.println("|                                      No payment is required at this time                          |");
            }
            System.out.println("+---------------------+------------------+------+-------------+-------------+-----------------------+");
            boolean flag = false;
            do {
                try {
                    System.out.println("1.Grant Request   \n2.Back");
                    byte option = Validate.validateOption(sc.readLine());
                    int sno = -1;
                    if (statusMap.size() > 0 && option == 1 && !statusMap.get((int) option).equals("REFUNDED")) {
                        boolean snoFlag = false;
                        do {
                            try {
                                System.out.println("Select number to accept request :");
                                sno = Validate.validInteger(sc.readLine());
                            } catch (IntegerException e) {
                                System.out.println(e.getMessage());
                            }

                        } while (snoFlag);

                        String paymentQuery = "UPDATE payment SET PAID_STATUS = 'REFUNDED',PRICE = null WHERE payment_id = ?";
                        PreparedStatement paymentStatement = DBConnection.getInstance().getConnection().prepareStatement(paymentQuery);
                        paymentStatement.setInt(1, snoMap.get(sno));
                        if (paymentStatement.executeUpdate() > 0) {
                            System.out.println("Refunded Successfully");
                        }
                    } else if (option == 1 && statusMap.get((int) option).equals("REFUNDED")) {
                        System.out.println("Request Already Sent");
                    } else if (option == 2) {
                        flag = false;
                    } else {
                        System.out.println("Enter the valid Statement");
                    }

                } catch (NumberInputException e) {
                    flag = true;
                    System.out.println(e.getMessage());
                }
            } while (flag);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void orderByHallTotalBooking() {
        boolean flag = false;
        do {
            try {
                String query = "SELECT h.hall_name, h.hall_price AS price, h.is_Ac, COUNT(b.book_id) AS total_orders " +
                        "FROM halls h " +
                        "LEFT JOIN booking b ON h.hall_id = b.hall_id " +
                        "GROUP BY h.hall_id, h.hall_name, h.hall_price, h.is_Ac " +
                        "ORDER BY total_orders DESC";

                PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);

                ResultSet resultSet = statement.executeQuery();

                System.out.println("+----------------------+----------------------+-----------------+-----------------+");
                System.out.println("|       Hall Name      |         Price        |      AC Status  |   Total Orders  |");
                System.out.println("+----------------------+----------------------+-----------------+-----------------+");

                while (resultSet.next()) {
                    String hallName = resultSet.getString("hall_name");
                    double price = resultSet.getDouble("price");
                    String isAc = resultSet.getString("is_Ac");
                    int totalOrders = resultSet.getInt("total_orders");


                    // Print the row values within the box
                    System.out.printf("| %-20s | %-20.2f | %-15s | %-15d |\n", hallName, price, isAc, totalOrders);

                    // Print the box footer

                }
                System.out.println("+----------------------+----------------------+-----------------+-----------------+");
                boolean exitflag = false;
                do {
                    try {
                        System.out.println("Enter E to exit");
                        if (sc.readLine().equalsIgnoreCase("E")) {
                            exitflag = false;
                            flag = false;
                        } else {
                            System.out.println("Enter the valid Input ");
                        }
                    } catch (IOException e) {
                        System.out.println(e);
                    }

                } while (exitflag);

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } while (flag);
    }

    public void orderByHighPayUser() {
        boolean flag = false;
        do {
            try {
                String query = "SELECT u.name AS user_name, " +
                        "       SUM(p.price) AS total_amount_spend, " +
                        "       u.email_id AS email, " +
                        "       u.phone_number AS phone_number " +
                        "FROM users u " +
                        "JOIN booking b ON u.user_id = b.user_id " +
                        "JOIN PAYMENT p ON b.book_id = p.book_id " +
                        "GROUP BY u.user_id, u.name, u.email_id, u.phone_number " +
                        "ORDER BY total_amount_spend DESC";

                PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();
                System.out.println("+------------+--------------+-----------------+----------------+");
                System.out.printf("| %-10s | %-12s | %-15s | %-14s |\n", "User Name", "Total Amount", "Email", "Phone Number");
                System.out.println("+------------+--------------+-----------------+----------------+");

                while (resultSet.next()) {
                    String userName = resultSet.getString("user_name");
                    double totalAmountSpend = resultSet.getDouble("total_amount_spend");
                    String email = resultSet.getString("email");
                    String phoneNumber = resultSet.getString("phone_number");

                    System.out.printf("| %-10s | %-12.2f | %-15s | %-14s |\n",
                            userName, totalAmountSpend, email, phoneNumber);

                }
                System.out.println("+------------+--------------+-----------------+----------------+");
                boolean exitflag = false;
                do {
                    try {
                        System.out.println("Enter E to exit");
                        if (sc.readLine().equalsIgnoreCase("E")) {
                            exitflag = false;
                            flag = false;
                        } else {
                            System.out.println("Enter the valid Input ");
                        }
                    } catch (IOException e) {
                        System.out.println(e);
                    }

                } while (exitflag);

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } while (flag);
    }

    public void highlyBookedEvent() {
        boolean flag = false;
        do {
            try {
                String query = "SELECT e.event_name, COUNT(b.event_id) AS total_bookings " +
                        "FROM event e " +
                        "JOIN booking b ON e.event_id = b.event_id " +
                        "GROUP BY e.event_name " +
                        "ORDER BY total_bookings DESC";
                PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();

                // Print the results
                System.out.println("+-------------------+-----------------+");
                System.out.println("|     Event Name    | Total Bookings  |");
                System.out.println("+-------------------+-----------------+");
                while (resultSet.next()) {
                    String eventName = resultSet.getString("event_name");
                    int totalBookings = resultSet.getInt("total_bookings");
                    System.out.printf("| %-17s | %-15d |\n", eventName, totalBookings);
                }
                System.out.println("+-------------------+-----------------+");
                boolean exitflag = false;
                do {
                    try {
                        System.out.println("Enter E to exit");
                        if (sc.readLine().equalsIgnoreCase("E")) {
                            exitflag = false;
                            flag = false;
                        } else {
                            System.out.println("Enter the valid Input ");
                        }
                    } catch (IOException e) {
                        System.out.println(e);
                    }

                } while (exitflag);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } while (flag);

    }

    public void highlyBookedArrangementType() {
        boolean flag = false;
        do {


            try {
                String query = "SELECT sa.arrangement_type, COUNT(b.arrangement_id) AS total_bookings " +
                        "FROM Seating_Arrangement sa " +
                        "LEFT JOIN Hall_Seating hs ON sa.arrangement_id = hs.arrangement_id " +
                        "LEFT JOIN booking b ON hs.hall_id = b.hall_id AND hs.arrangement_id = b.arrangement_id " +
                        "GROUP BY sa.arrangement_type ORDER BY total_bookings DESC";
                PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();

                // Print the results
                System.out.println("+----------------------+-----------------+");
                System.out.println("|   Arrangement Type   | Total Bookings  |");
                System.out.println("+----------------------+-----------------+");
                while (resultSet.next()) {
                    String arrangementType = resultSet.getString("arrangement_type");
                    int totalBookings = resultSet.getInt("total_bookings");
                    System.out.printf("| %-20s | %-15d |\n", arrangementType, totalBookings);
                }
                System.out.println("+----------------------+-----------------+");
                boolean exitflag = false;
                do {
                    try {
                        System.out.println("Enter E to exit");
                        if (sc.readLine().equalsIgnoreCase("E")) {
                            exitflag = false;
                            flag = false;
                        } else {
                            System.out.println("Enter the valid Input ");
                        }
                    } catch (IOException e) {
                        System.out.println(e);
                    }

                } while (exitflag);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } while (flag);
    }

    public void manageEvents() {
        try {
            System.out.println("+------------------------------------+");
            System.out.println("| 1. Add event to Hall              |");
            System.out.println("| 2. Remove Event from Hall         |");
            System.out.println("| 3. Back                           |");
            System.out.println("| 4. Exit                           |");
            System.out.println("+------------------------------------+");

            int option = Validate.validateOption(sc.readLine());
            switch (option) {
                case 1:
                    addEvents();
                    break;
                case 2:
                    removeEvents();
                    break;
                default:
                    System.out.println("Enter the valid Option");
            }
        } catch (NumberInputException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeEvents() {
        boolean flag = false;
        do {
            try {
                System.out.println("Enter the hall name:");
                String hallName = sc.readLine();
                int hallId = -1;
                boolean isExisted = false;
                for (Hall list : new HallDirectory().listHall()) {
                    if (list.getHallName().equalsIgnoreCase(hallName)) {
                        isExisted = true;
                        hallId = list.getHallId();
                    }
                }
                if (!isExisted) {
                    System.out.println("Hall not existed");
                    continue;
                }
                String query = "SELECT e.event_Id, e.event_name FROM event e WHERE e.event_id IN " +
                        "(SELECT be.event_id FROM book_event be WHERE be.hall_id = ?)";
                PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);
                statement.setInt(1, hallId);
                ResultSet set = statement.executeQuery();
                LinkedHashMap<Integer, Integer> eventMap = new LinkedHashMap<>();
                System.out.println("+------------+-----------------+");
                System.out.println("| Event NO   |  Event Type      |");
                System.out.println("+------------+-----------------+");

                int i = 0;
                while (set.next()) {
                    int eventNo = i + 1;
                    String eventType = set.getString("event_name");
                    System.out.printf("| %-10d | %-15s |\n", eventNo, eventType);
                    eventMap.put(eventNo, set.getInt("event_id"));
                    i++;
                }
                System.out.println("+------------+-----------------+");
                boolean optionFlag = false;
                do {
                    int event_no = -1;
                    try {
                        System.out.print("Enter the event number to remove (1 to " + eventMap.size() + "): ");
                        int option = Validate.validInteger(sc.readLine());
                        if (option < 0 && eventMap.size() < option) {
                            System.out.println("Enter the input from 1 to " + eventMap.size());
                            continue;
                        } else {
                            event_no = eventMap.get(option);
                        }
                    } catch (IntegerException e) {
                        optionFlag = true;
                        System.out.println(e.getMessage());
                    }
                    String deleteQuery = "DELETE FROM BOOK_EVENT WHERE hall_id = ? AND event_id = ?";
                    PreparedStatement deleteStatement = DBConnection.getInstance().getConnection().prepareStatement(deleteQuery);
                    deleteStatement.setInt(1, hallId);
                    deleteStatement.setInt(2, event_no);
                    if (deleteStatement.executeUpdate() > 0) {
                        System.out.println("Event removed successfully");
                    }
                    boolean updateContinueFlag = false;
                    do {
                        System.out.println("Do you want to remove another event? (yes/no):");
                        String input = sc.readLine().toLowerCase();
                        if ("yes".equals(input)) {
                            optionFlag = true;
                            updateContinueFlag = false;
                        } else if ("no".equals(input)) {
                            optionFlag = false;
                            updateContinueFlag = false;
                        } else {

                            updateContinueFlag = true;
                            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                        }
                    } while (updateContinueFlag);
                } while (optionFlag);
            } catch (IOException | SQLException e) {
                flag = true;
                System.out.println(e.getMessage());
            }
        } while (flag);
    }


    public void addEvents() {
        boolean flag = false;
        do {
            try {
                System.out.println("Enter the hall name :");
                String hallName = sc.readLine();
                int hallId = -1;
                int hallCapacity = -1;
                boolean isExisted = false;
                for (Hall list : new HallDirectory().listHall()) {
                    if (list.getHallName().equalsIgnoreCase(hallName)) {
                        isExisted = true;
                        hallId = list.getHallId();
                        hallCapacity = list.getCapacity();
                    }
                }
                if (!isExisted) {
                    System.out.println("Hall not existed");
                    continue;
                }
                String query = "SELECT e.event_Id,e.event_name FROM event e WHERE e.event_id NOT IN ( SELECT be.event_id" +
                        "  FROM book_event be WHERE be.hall_id = ?)";
                PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);
                statement.setInt(1, hallId);
                ResultSet set = statement.executeQuery();
                LinkedHashMap<Integer, Integer> eventMap = new LinkedHashMap<>();
                System.out.println("+------------+-----------------+");
                System.out.println("| Event NO   |  Event Type      |");
                System.out.println("+------------+-----------------+");

                int i = 0;
                while (set.next()) {
                    int eventNo = i + 1;
                    String eventType = set.getString("event_name");
                    System.out.printf("| %-10d | %-15s |\n", eventNo, eventType);
                    eventMap.put(eventNo, set.getInt("event_id"));
                    i++;
                }

                System.out.println("+------------+-----------------+");
                boolean optionFlag = false;
                do {
                    int event_no = -1;
                    try {
                        System.out.print("Enter the event number to add 1 to " + eventMap.size());
                        int option = Validate.validInteger(sc.readLine());
                        if (option < 0 && eventMap.size() < option) {
                            System.out.println("Enter the input from 1 to " + eventMap.size());
                            continue;
                        } else {
                            event_no = eventMap.get(option);
                        }
                    } catch (IntegerException e) {
                        optionFlag = true;
                        System.out.println(e.getMessage());
                    }

                    String insertQuery = "INSERT INTO BOOK_EVENT (HALL_ID, EVENT_ID) VALUES (?, ?)";
                    PreparedStatement insertStatement = DBConnection.getInstance().getConnection().prepareStatement(insertQuery);
                    insertStatement.setInt(1, hallId);
                    insertStatement.setInt(2, event_no);
                    if (insertStatement.executeUpdate() > 0) {
                        System.out.println("Added successfully");
                    }
                    boolean updateContinueFlag = false;
                    do {
                        System.out.println("Do you want to add another arrangement type? (yes/no):");
                        String input = sc.readLine().toLowerCase();
                        if ("yes".equals(input)) {
                            optionFlag = true;
                            updateContinueFlag = false;
                        } else if ("no".equals(input)) {
                            optionFlag = false;
                            updateContinueFlag = false;
                        } else {
                            updateContinueFlag = true;
                            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                        }
                    } while (updateContinueFlag);
                } while (optionFlag);
            } catch (IOException | SQLException e) {
                flag = true;
                System.out.println(e.getMessage());
            }
        } while (flag);
    }

    public void manageSeats() {
        try {
            System.out.println("+-----------------------------------+");
            System.out.println("| 1. Update Existing Seat           |");
            System.out.println("| 2. Add New Type Seats             |");
            System.out.println("| 3. Back                           |");
            System.out.println("| 4. Exit                           |");
            System.out.println("+-----------------------------------+");
            int option = Validate.validateOption(sc.readLine());
            switch (option) {
                case 1:
                    UpdateExistingSeats();
                    break;
                case 2:
                    addNewTypeSeats();
                    break;
                case 3:
                    System.out.println("Back");
                    break;
                case 4:
                    System.exit(0);
                    break;
            }
        } catch (NumberInputException | IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void UpdateExistingSeats() throws IOException {
        boolean flag = false;
        do {
            try {
                System.out.println("Enter the hall name :");
                String hallName = sc.readLine();
                int hallId = -1;
                int hallCapacity = -1;
                boolean isExisted = false;
                for (Hall list : new HallDirectory().listHall()) {
                    if (list.getHallName().equalsIgnoreCase(hallName)) {
                        isExisted = true;
                        hallId = list.getHallId();
                        hallCapacity = list.getCapacity();
                    }
                }
                System.out.println("Hall Total Capacity " + hallCapacity);
                if (!isExisted) {
                    continue;
                }
                String query = "SELECT s.arrangement_id, s.arrangement_type, hs.capacity " +
                        "FROM Seating_Arrangement s " +
                        "JOIN Hall_Seating hs ON hs.arrangement_id = s.arrangement_id Where hs.hall_id = ?";
                PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);
                statement.setInt(1, hallId);
                ResultSet set = statement.executeQuery();
                System.out.println("+-------+-------------------------+---------------+");
                System.out.println("|  SNO  |Arrangement Type         | Seat Capacity |");
                System.out.println("+---------------------------------+---------------+");
                int i = 0;
                LinkedHashMap<Integer, Integer> mapArrangement = new LinkedHashMap<>();
                while (set.next()) {
                    ++i;
                    String arrangementType = set.getString("arrangement_type");
                    int capacity = set.getInt("capacity");
                    int arrangementID = set.getInt("arrangement_id");
                    mapArrangement.put(i, set.getInt("arrangement_id"));
                    System.out.printf("| %-4s | %-24s | %-13d |\n", i, arrangementType, capacity);
                }

                System.out.println("+---------------------------------+---------------+");
                int arrangementId = -1;
                boolean updateFlag = false;
                int sno = -1;
                do {
                    boolean selectFlag = false;
                    do {
                        try {
                            System.out.println("Enter the SNO to select seat : ");
                            sno = Validate.validInteger(sc.readLine());
                            if (sno > mapArrangement.size() && sno < 0) {
                                System.out.println("Enter the value between 1 to " + mapArrangement.size());
                                updateFlag = false;
                            }
                        } catch (IntegerException e) {
                            selectFlag = false;
                            System.out.println(e.getMessage());
                        }
                    } while (selectFlag);
                    int currentCapacity = 0;
                    boolean capacityFlag = false;
                    /*The Loop get executed until the current seat capacity is less than hall capacity */
                    do {
                        try {
                            System.out.println("Enter the capacity under " + (hallCapacity - 20));
                            currentCapacity = Validate.validInteger(sc.readLine());
                            if (currentCapacity > (hallCapacity - 20)) {
                                capacityFlag = true;
                            }
                        } catch (IntegerException e) {
                            capacityFlag = true;
                            System.out.println(e.getMessage());
                        }
                    } while (capacityFlag);
                    System.out.println(currentCapacity + " " + hallId + " " + sno);
                    String updateQuery = "UPDATE Hall_seating SET capacity = ? where HALL_ID = ? AND  ARRANGEMENT_ID = ?";
                    PreparedStatement updateStatement = DBConnection.getInstance().getConnection().prepareStatement(updateQuery);
                    updateStatement.setInt(1, currentCapacity);
                    updateStatement.setInt(2, hallId);
                    updateStatement.setInt(3, mapArrangement.get(sno));
                    if (updateStatement.executeUpdate() > 0) {
                        System.out.println(" Updated Successfully ");
                    }
                    boolean updateContinueFlag = false;
                    do {
                        System.out.println("Do you want to add another arrangement type? (yes/no):");
                        String input = sc.readLine().toLowerCase();
                        if ("yes".equals(input)) {
                            updateFlag = true;
                            updateContinueFlag = false;
                        } else if ("no".equals(input)) {
                            updateFlag = false;
                            updateContinueFlag = false;
                        } else {
                            updateContinueFlag = true;
                            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                        }
                    } while (updateContinueFlag);
                } while (updateFlag);
            } catch (SQLException e) {
                flag = true;
                System.out.println(e.getMessage());
            }
        } while (flag);
    }

    public void addNewTypeSeats() throws IOException {
        boolean flag = false;
        do {
            try {
                System.out.println("Enter the hall name:");
                String hallName = sc.readLine();
                int hallId = -1;
                int hallCapacity = -1;
                boolean isExisted = false;
                for (Hall list : new HallDirectory().listHall()) {
                    if (list.getHallName().equalsIgnoreCase(hallName)) {
                        isExisted = true;
                        hallId = list.getHallId();
                        hallCapacity = list.getCapacity();
                        break;
                    }
                }

                if (!isExisted) {
                    System.out.println("Hall does not exist. Please try again.");
                    continue;
                }

                System.out.println("Hall Total Capacity: " + hallCapacity);

                // Fetch available arrangement types not associated with the hall
                String query = "SELECT arrangement_id, arrangement_type FROM Seating_Arrangement " +
                        "WHERE arrangement_id NOT IN (SELECT arrangement_id FROM Hall_Seating WHERE hall_id = ?)";
                PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);
                statement.setInt(1, hallId);
                ResultSet set = statement.executeQuery();

                // Display available arrangement types
                LinkedHashMap<Integer, String> availableArrangements = new LinkedHashMap<>();
                int i = 1;
                System.out.println("+------+-------------------------+");
                System.out.println("| SNO  | Arrangement Type        |");
                System.out.println("+------+-------------------------+");

                while (set.next()) {
                    int arrangementId = set.getInt("arrangement_id");
                    String arrangementType = set.getString("arrangement_type");
                    availableArrangements.put(i, arrangementType);
                    System.out.printf("| %-4d | %-23s |\n", i, arrangementType);
                    i++;
                }
                System.out.println("+------+-------------------------+");

                if (availableArrangements.isEmpty()) {
                    System.out.println("No new arrangement types are available to add.");
                    return;
                }

                boolean addMore = true;
                while (addMore) {
                    int arrangementId = -1;
                    int sno = -1;

                    // Select an arrangement type to add
                    boolean selectFlag = false;
                    do {
                        try {
                            System.out.println("Enter the SNO to select an arrangement type:");
                            sno = Validate.validInteger(sc.readLine());
                            if (sno < 1 || sno > availableArrangements.size()) {
                                System.out.println("Enter a valid SNO between 1 and " + availableArrangements.size());
                                selectFlag = true;
                            } else {
                                arrangementId = new ArrayList<>(availableArrangements.keySet()).get(sno - 1);
                                selectFlag = false;
                            }
                        } catch (IntegerException e) {
                            selectFlag = true;
                            System.out.println(e.getMessage());
                        }
                    } while (selectFlag);

                    // Input the capacity for the selected arrangement type
                    int currentCapacity = 0;
                    boolean capacityFlag = false;
                    do {
                        try {
                            System.out.println("Enter the capacity (must be less than " + hallCapacity + "):");
                            currentCapacity = Validate.validInteger(sc.readLine());
                            if (currentCapacity >= hallCapacity) {
                                System.out.println("Capacity must be less than the hall capacity.");
                                capacityFlag = true;
                            } else {
                                capacityFlag = false;
                            }
                        } catch (IntegerException e) {
                            capacityFlag = true;
                            System.out.println(e.getMessage());
                        }
                    } while (capacityFlag);

                    // Insert the new arrangement type and its capacity into Hall_Seating
                    String insertQuery = "INSERT INTO Hall_Seating (hall_id, arrangement_id, capacity) VALUES (?, ?, ?)";
                    PreparedStatement insertStatement = DBConnection.getInstance().getConnection().prepareStatement(insertQuery);
                    insertStatement.setInt(1, hallId);
                    insertStatement.setInt(2, arrangementId);
                    insertStatement.setInt(3, currentCapacity);

                    if (insertStatement.executeUpdate() > 0) {
                        System.out.println("Arrangement type added successfully!");
                    }

                    // Ask if the user wants to add another arrangement type
                    boolean updateContinueFlag = false;
                    do {
                        System.out.println("Do you want to add another arrangement type? (yes/no):");
                        String input = sc.readLine().toLowerCase();
                        if ("yes".equals(input)) {
                            addMore = true;
                            updateContinueFlag = false;
                        } else if ("no".equals(input)) {
                            addMore = false;
                            updateContinueFlag = false;
                        } else {
                            updateContinueFlag = true;
                            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                        }
                    } while (updateContinueFlag);
                }

            } catch (SQLException e) {
                flag = true;
                System.out.println(e.getMessage());
            }
        } while (flag);
    }
    public List<Person> UserList(){
        try{
            List<Person> personList = new ArrayList<>();
            String personListQuery = "Select * from users";
            PreparedStatement personListStatement = DBConnection.getInstance().getConnection().prepareStatement(personListQuery);
            ResultSet resultSet = personListStatement.executeQuery();
            while (resultSet.next()){
                
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}