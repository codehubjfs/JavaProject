package com.hallbookingsystem.persondetails;
import com.hallbookingsystem.customexception.*;
import com.hallbookingsystem.dbconnection.DBConnection;
import com.hallbookingsystem.primarykey.PrimaryKeyProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Authentication {
    public Authentication(){}
    static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

    /*This collectAccountDetails used to collect the account details of the users*/
    public static HashMap<String, Account> collectAccountDetails() {
        try {
            HashMap<String, Account> accountList = new HashMap<>();
            String query = "SELECT username, password, account_status, account_type FROM HALLBOOKING.users";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                String username = set.getString("username");
                String password = set.getString("password");
                AccountStatus status = AccountStatus.valueOf(set.getString("account_status"));
                AccountType type = AccountType.valueOf(set.getString("account_type").toUpperCase());
                accountList.put(username, new Account(username, password, status, type));
            }
            return accountList;
        } catch (Exception e) {
            System.out.println(e.getMessage());;
            return new HashMap<>();
        }
    }

    /*This collectPersonList used to collect the account details of the users*/
    public static List<Person> collectPersonList(){
        try{
            List<Person> personList = new ArrayList<>();
            String query = "Select user_id,name,gender,email_ID,Phone_Number, address from users";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while (set.next()){
                String address = set.getString("address");
                String [] add = address.split(",");
                Address addressObj = new Address(add[0],add[1],add[2],add[3],add[4]);
                Person person = new Person(set.getInt("user_id"),set.getString("name"), Gender.valueOf(set.getString("gender")),
                        set.getString("email_ID"), set.getString("Phone_Number"), addressObj);
                personList.add(person);
            }
            return personList;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());;
        }
        return new ArrayList<>();
    }


    /*login method used to check the matching username and password*/
    public  <T extends Person>  T login(AccountType accountType) {
        try {
            boolean attempt= false;
            HashMap<String, Account> accounts = collectAccountDetails();
            boolean isContains = false;
            int count = 0;
            do{
                if(attempt){
                    System.out.println("Want to continue (Y/N)");
                    String continueOption = sc.readLine().trim();
                    if(continueOption.equalsIgnoreCase("N")){
                        return null;
                    }
                    else if (!continueOption.equalsIgnoreCase("Y")) {
                        System.out.println("Enter the Valid Option");
                        continue;
                    }
                }
                System.out.print("Username  ");
                String username = sc.readLine().trim();
                System.out.print("Password  ");
                String password = sc.readLine().trim();

                if (accounts.containsKey(username)) {// check the username is occur in the map
                    Account account = accounts.get(username);
                    if (account.getPassword().equals(password) ) {// check the password from the account object
                        if(account.getStatus().equals(AccountStatus.BLOCKED)){// check wheather the account is ACTIVE or BLOCKED
                            System.out.println("Your account has been blocked. Please contact support for further assistance.");
                        }
                        else if(!accounts.get(username).getType().equals(accountType)){
                            System.out.println(" Invalid UserName or Password ");
                            count++;
                            isContains = false;
                        }
                        else {
                            System.out.println("Login successfully");
                        }
                        String query = "SELECT user_id, name, gender, email_id, phone_number, address, account_type,'ACTIVE'  FROM users WHERE username= ?";
                        try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query)) {
                            statement.setString(1, username);
                            try (ResultSet set = statement.executeQuery()) {
                                if (set.next()) {
                                    isContains = true;
                                    Admin admin = new Admin();
                                    Customer customer = new Customer();
                                    if(accountType.equals(AccountType.CUSTOMER)){
                                        customer.setUserId(set.getInt("user_id"));
                                        customer.setName(set.getString("name"));
                                        customer.setGender(Gender.valueOf(set.getString("Gender").toUpperCase()));
                                        customer.setEmail(set.getString("email_id"));
                                        customer.setMobileNumber(set.getString("phone_number"));
                                        String address = set.getString("address");
                                        String [] add = address.split(",");
                                        Address addressObj= null;
                                        if(add.length==4){
                                            addressObj = new Address(add[0],add[1],add[2],add[3],add[4]);
                                        }
                                        else{
                                            addressObj = new Address(add[0]);
                                        }
                                        Account accountCustomer = new Account(username,password,AccountStatus.ACTIVE,AccountType.CUSTOMER);
                                        customer.setAddress(addressObj);
                                        customer.setAccount(accountCustomer);
                                        return (T) customer;
                                    }
                                    else{
                                        admin.setUserId(set.getInt("user_id"));
                                        admin.setName(set.getString("name"));
                                        admin.setGender(Gender.valueOf(set.getString("GENDER")));
                                        admin.setEmail(set.getString("email_id"));
                                        admin.setMobileNumber(set.getString("phone_number"));
                                        String address = set.getString("address");
                                        String [] add = address.split(",");
                                        Address addressObj= null;
                                        if(add.length==4){
                                            addressObj = new Address(add[0],add[1],add[2],add[3],add[4]);
                                        }
                                        else{
                                            addressObj = new Address(add[0]);
                                        }
                                        Account accountAdmin = new Account(username,password,AccountStatus.ACTIVE,AccountType.ADMIN);
                                        admin.setAddress(addressObj);
                                        admin.setAccount(accountAdmin);
                                        return (T) admin;
                                    }
                                }
                            }
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                    else {
                        count++;
                        attempt = true;
                        System.out.println(" Invalid UserName or Password ");
                    }
                }else {
                    count++;
                    attempt = true;
                    isContains = false;
                    System.out.println(" Invalid UserName or Password ");
                }
                if(count==3){

                    System.out.println("You have exceeded the maximum number of attempts. Exiting program.");
                    System.exit(0);
                }
            }
            while (!isContains);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    /*register method used to new register*/
    public <T extends Person >T register(AccountType accountType){
        String name = "";
        try{
            boolean flag = false;
            do{
                try{
                    System.out.print("Enter Your Full Name   ");
                    name = Validate.validateName(sc.readLine().trim());
                    break;
                }catch (FullNameException e){
                    flag = true;
                    System.out.println(e.getMessage());
                }
            }while (flag);
            flag = false;
            Gender gender = null;
            do{
                System.out.print("Enter Your Gender(MALE/FEMALE/TRANSGENDER)   ");
                String identity = sc.readLine().toUpperCase().trim();
                if("MALE".equalsIgnoreCase(identity)||"FEMALE".equalsIgnoreCase(identity)||"TRANSGENDER".equalsIgnoreCase(identity)){
                    gender = Gender.valueOf(identity);
                    break;
                }
                else{
                    flag = true;
                    System.out.println("Enter Valid Gender");
                }
            }while (flag);

            boolean emailIsExisted = false;
            String email="";
            do{
                {
                    try{
                        System.out.print("Enter Your Email id   ");
                        email = Validate.validateEmail(sc.readLine().trim());
                        String finalEmail = email;
                        if (collectPersonList().stream().filter(person -> person.getEmail().equals(finalEmail)).count()!=0){
                            emailIsExisted = true;
                            System.out.print("Entered Mail ID is already Existed");
                        }
                        else{
                            emailIsExisted =false;
                        }
                    }
                    catch (EmailException e){
                        emailIsExisted = true;
                        System.out.println(e.getMessage());
                    }
                }
            }
            while(emailIsExisted);
            boolean phoneNumberIsExisted = false;
            String phoneNumber = "";
            do {
                try
                {
                    System.out.print("Enter Your Phone Number   ");
                    phoneNumber = Validate.validateNumber(sc.readLine().trim());
                    String finalPhoneNumber = phoneNumber;
                    if (collectPersonList().stream().filter(person -> person.getMobileNumber().equals(finalPhoneNumber)).count()!=0) {
                        System.out.println("Entered Phone Number is already Existed");
                        phoneNumberIsExisted = true;
                    }
                    else {
                        phoneNumberIsExisted =false;
                    }
                }
                catch (PhoneNumberException ex){
                    phoneNumberIsExisted=true;
                    System.out.println(ex.getMessage());
                }

            } while (phoneNumberIsExisted);
            String doorNumber = null;
            String city = null;
            String townName = null;
            String pincode = null;
            String streetName =null;
            boolean isAddressing = true;
            System.out.print("Enter Your Door Number (optional)");// Entering the door number is optional where door number =null;
            doorNumber=sc.readLine();
            if(doorNumber!=null){
                isAddressing =true;
            }
            else{
                System.out.println("Not entered door");
                isAddressing = false;
            }
            if(!isAddressing) {
                System.out.print("Enter Your Street Name   ");
                streetName = sc.readLine().trim();
                boolean townFlag = false;
                do {
                    try {
                        System.out.print("Enter Your Town Name ");
                        townName = Validate.validateName(sc.readLine().trim());
                        break;
                    } catch (FullNameException | IOException e) {
                        townFlag = true;
                        System.out.println(e.getMessage());
                    }
                } while (townFlag);

                boolean cityFlag = false;
                do {
                    try {
                        System.out.print("Enter Your City  ");
                        city = Validate.validateName(sc.readLine().trim());
                        break;
                    } catch (FullNameException | IOException e) {
                        cityFlag = true;
                        System.out.println(e.getMessage());
                    }
                } while (cityFlag);

                boolean pincodePattern = false;
                do {
                    System.out.print("Enter PinCode numbers with space (654 123) ");
                    try {
                        pincode = Validate.validPincode(sc.readLine().trim());
                        break;
                    } catch (PinCodeException e) {
                        pincodePattern = true;
                        System.out.println(e.getMessage());
                    }
                } while (pincodePattern);
            }
            else{
                boolean cityFlag = false;
                do {
                    try {
                        System.out.print("Enter Your City  ");
                        city = Validate.validateName(sc.readLine().trim());
                        break;
                    } catch (FullNameException | IOException e) {
                        cityFlag = true;
                        System.out.println(e.getMessage());
                    }
                } while (cityFlag);
            }


            boolean usernameIsExisted = false;
            String username = null;
            do{
                try{
                    System.out.println("UserName  has contains alphanumeric with underscore(_) minimum 8 character and maximum 27 characters ");
                    System.out.print("Enter your User Name  ");
                    username = Validate.validateUserName(sc.readLine().trim());
                    String finalUsername = username;
                    if(collectAccountDetails().values().stream().filter(x->x.getUsername().equalsIgnoreCase(finalUsername)).count()!=0){
                        System.out.print("Entered username is already Existed");
                        usernameIsExisted = true;
                    }
                    else{
                        break;
                    }
                }
                catch (UserNameException e){
                    usernameIsExisted = true;
                    System.out.println(e.getMessage());
                }
            }while (usernameIsExisted);
            String password = "";
            boolean isPatternMatch = false;
            do{
                System.out.print("Password contains minimum eight characters, at least one letter, one number and one special character");
                System.out.print("\nEnter the password with :");
                try {
                    password = Validate.validatePassword(sc.readLine().trim());
                    break;
                }
                catch (PasswordException ex){
                    System.out.println(ex.getMessage());
                    isPatternMatch = true;
                }
            }while (isPatternMatch);
            Account account = null;
            Address address = null;
            if(!isAddressing){
                address = new Address(doorNumber,streetName,townName,city,pincode);
            }
            else{
                address = new Address(city);
            }


            Admin admin = null;
            Customer customer =null;
            if(accountType.equals(AccountType.ADMIN)){
                account = new Account(username,password,AccountStatus.ACTIVE,AccountType.ADMIN);
                admin = new Admin(PrimaryKeyProvider.primaryKey("users"),name,account,gender,email,phoneNumber,address);
                String query  = "Insert INTO users values (?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);
                statement.setInt(1,admin.getUserId());
                statement.setString(2,admin.getName());
                statement.setString(3,admin.getGender().toString());
                statement.setString(4,admin.getEmail());
                statement.setString(5,admin.getMobileNumber());
                statement.setString(6,address.toString());
                statement.setString(7,account.getType().toString());
                statement.setString(8,account.getStatus().toString());
                statement.setString(9,account.getUsername());
                statement.setString(10,account.getPassword());
                if(statement.executeUpdate()>0){
                    System.out.println("Registered Successfully");
                    return (T)admin;
                }
            }
            else if(accountType.equals(AccountType.CUSTOMER)){
                account = new Account(username,password,AccountStatus.ACTIVE,AccountType.CUSTOMER);
                customer = new Customer(PrimaryKeyProvider.primaryKey("users"),name,account,gender,email,phoneNumber,address);
                String query  = "Insert INTO users values (?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);
                statement.setInt(1,customer.getUserId());
                statement.setString(2,customer.getName());
                statement.setString(3,customer.getGender().toString());
                statement.setString(4,customer.getEmail());
                statement.setString(5,customer.getMobileNumber());
                statement.setString(6,address.toString());
                statement.setString(7,account.getType().toString());
                statement.setString(8,account.getStatus().toString());
                statement.setString(9,account.getUsername());
                statement.setString(10,account.getPassword());
                if(statement.executeUpdate()>0){
                    System.out.println("Registered Successfully");
                    return (T) customer;
                }
            }
        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
