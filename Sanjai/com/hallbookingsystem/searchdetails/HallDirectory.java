package com.hallbookingsystem.searchdetails;
import com.hallbookingsystem.bookingdetails.Book;
import com.hallbookingsystem.dbconnection.DBConnection;
import com.hallbookingsystem.halldetails.Hall;
import com.hallbookingsystem.customexception.IntegerException;
import com.hallbookingsystem.customexception.Validate;
import com.hallbookingsystem.persondetails.Customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

class SortByPrice implements Comparator<Hall> {

    @Override
    public int compare(Hall hall1, Hall hall2) {
        return Float.compare(hall1.getPrice(),hall2.getPrice());
    }

}
class  SortByName implements Comparator<Hall>{
    @Override
    public int compare(Hall hall1, Hall hall2) {
        return hall1.getHallName().compareTo(hall2.getHallName());
    }
}

public class HallDirectory implements Search{
    static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
    @Override
    public void searchByName(Customer customer) {
        try{
            System.out.println("Enter the Name of Hall :" );
            String name = sc.readLine();
            List<Hall> list = listHall();
            System.out.println("+-------------------------------------------------------------------+");
            System.out.printf("│ %-20s │ %-16s │ %-10s │ %-10s │%n", "Hall Name", "Price per Day", "Capacity", "AC");
            System.out.println("+-------------------------------------------------------------------+");
            list.stream().filter(x->x.getHallName().equalsIgnoreCase(name) && x.isAvail())
                    .forEach(hall ->System.out.printf("| %-20s | %-16.2f | %-10d | %-10s |%n", hall.getHallName(), hall.getPrice(), hall.getCapacity(), hall.getIsAcHall() ? "Yes" : "No"));
            System.out.println("+-------------------------------------------------------------------+");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        boolean optionFlag = false ;
        do{
            try{
                System.out.println("1. Book\n 2.Back  ");
                String option = sc.readLine();
                if (option.matches("1")){
                    Book book = new Book();
                    customer.bookingHall(book);
                }
                else if(option.matches("2")){
                    optionFlag = false;
                    return;
                }
                else{
                    System.out.println("Enter valid Input");
                }
            } catch (IOException e) {
                optionFlag = true;
                System.out.println(e.getMessage());
            }
        }while (optionFlag);
    }

    @Override
    public void searchByPrice(Customer customer) {
        System.out.println("Enter the Max Budget per day :");
        float maxPrice =0.0f;
        boolean flag = false;
        do{
            try{
                maxPrice = Validate.validInteger(sc.readLine());
            }
            catch (IOException | IntegerException e) {
                flag =true;
                System.out.println(e.getMessage());
            }
        }while (flag);

        float finalMaxPrice = maxPrice;
        List<Hall> filteredHalls = listHall().stream()
                .filter(hall ->hall.getPrice() <= finalMaxPrice && hall.isAcHall())
                .collect(Collectors.toList());
        System.out.println("+-------------------------------------------------------------------+");
        System.out.printf("│ %-20s │ %-16s │ %-10s │ %-10s │%n", "Hall Name", "Price per Day", "Capacity", "AC");
        System.out.println("+-------------------------------------------------------------------+");
        filteredHalls.forEach(hall ->System.out.printf("| %-20s | %-16.2f | %-10d | %-10s |%n", hall.getHallName(), hall.getPrice(), hall.getCapacity(), hall.getIsAcHall() ? "Yes" : "No"));
        System.out.println("+-------------------------------------------------------------------+");
        System.out.print(" Enter 1. Back ");
        boolean optionFlag = false ;
        do{
            try{
                System.out.println("1. Book\n 2.Back  ");
                String option = sc.readLine();
                if (option.matches("1")){
                    Book book = new Book();
                    customer.bookingHall(book);
                }
                else if(option.matches("2")){
                    optionFlag = false;
                    return;
                }
                else{
                    System.out.println("Enter valid Input");
                }
            } catch (IOException e) {
                optionFlag = true;
                System.out.println(e.getMessage());
            }
        }while (optionFlag);

    }

    @Override
    public void searchByCapacity(Customer customer){
        System.out.println("Enter the Min Capacity :" );
        boolean flag = false;
        int minCapacity =0;
        do{
            try{
                minCapacity = Validate.validInteger(sc.readLine());
            }
            catch (IOException | IntegerException e) {
                flag =true;
                System.out.println(e.getMessage());
            }
        }while (flag);

        System.out.println("Enter the Max Capacity :");
        int maxCapacity =0;
            do{
            try{
                maxCapacity = Validate.validInteger(sc.readLine());
            }
            catch (IOException | IntegerException e) {
                flag =true;
                System.out.println(e.getMessage());
            }
        }while (flag);


        float finalMinCapacity = minCapacity;
        float finalMaxCapacity = maxCapacity;
        List<Hall> filteredHalls = listHall().stream()
                .filter(hall -> hall.getCapacity() >= finalMinCapacity && hall.getCapacity() <= finalMaxCapacity && hall.isAcHall())
                .collect(Collectors.toList());
        System.out.println("+-------------------------------------------------------------------+");
        System.out.printf("│ %-20s │ %-16s │ %-10s │ %-10s │%n", "Hall Name", "Price per Day", "Capacity", "AC");
        System.out.println("+-------------------------------------------------------------------+");
            filteredHalls.stream().filter(Hall::isAvail).forEach(hall -> System.out.printf("| %-20s | %-16.2f | %-10d | %-10s |%n", hall.getHallName(), hall.getPrice(), hall.getCapacity(), hall.getIsAcHall() ? "Yes" : "No"));
            System.out.println("+---------------------------------------------------------------------------------------+");
        boolean optionFlag = false ;
        do{
            try{
                System.out.println("Enter 1. to Back ");
                String option = sc.readLine();
                if(!option.matches("1")){
                    optionFlag = true;
                    System.out.println("Enter valid Input");
                }
            } catch (IOException e) {
                optionFlag = true;
                System.out.println(e.getMessage());
            }
        }while (optionFlag);

    }
    public void suggestedHalls(Customer customer) {
        List<Hall> halls = listHall();
        System.out.println("+-------------------------------------------------------------------+");
        System.out.println("|                      Sorted By Suggested Halls                    |");
        System.out.println("+-------------------------------------------------------------------+");
        System.out.printf("│ %-20s │ %-16s │ %-10s │ %-10s │%n", "Hall Name", "Price per Day", "Capacity", "AC");
        System.out.println("+-------------------------------------------------------------------+");
        halls.stream().filter(Hall::isAvail)
                .sorted(Comparator.comparingDouble(hall -> hall.getPrice() / hall.getCapacity()))
                .forEach(hall -> System.out.printf("| %-20s | %-16.2f | %-10d | %-10s |%n", hall.getHallName(), hall.getPrice(), hall.getCapacity(), hall.getIsAcHall() ? "Yes" : "No"));
        System.out.println("+-------------------------------------------------------------------+");
        boolean optionFlag = false ;
        do{
            try{
                System.out.println("Enter 1. to Back ");
                String option = sc.readLine();
                if(!option.matches("1")){
                    optionFlag = true;
                    System.out.println("Enter valid Input");
                }
            } catch (IOException e) {
                optionFlag = true;
                System.out.println(e.getMessage());
            }
        }while (optionFlag);

    }

    public   List<Hall> listHall()  {
        try{
            String query = "Select hall_id,hall_name,HALL_PRICE,IS_AC,HALLCAPACITY,HALLAVAIL from halls ";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);
            ResultSet set = statement.executeQuery();
            List<Hall> list= new ArrayList<>();
            while (set.next()){
                list.add(new Hall(set.getInt("hall_id"),set.getString("hall_name"),
                        set.getFloat("HALL_PRICE"),set.getString("IS_AC").equalsIgnoreCase("Yes")?true:false,set.getInt("HALLCAPACITY"),
                        set.getString("HALLAVAIL").equalsIgnoreCase("A")?true:false));
            }
            return list;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public  static void shortByPrice(Customer customer){
        System.out.println("+----------------------------------------------------+");
        System.out.println("|                Sorted By Price                    |");
        System.out.println("+----------------------------------------------------+");
        System.out.printf("| %-20s | %-8s | %-6s | %-4s |%n", "Hall Name", "Price", "Capacity", "AC");
        System.out.println("+----------------------------------------------------+");
        HallDirectory  hallDirectory = new HallDirectory();
        List<Hall> halls = hallDirectory.listHall();
        halls.sort(new SortByPrice());
        for (Hall list : halls) {
            System.out.printf("| %-20s | %-8.2f | %-6d | %-4s | %-10s |%n", list.getHallName(), list.getPrice(), list.getCapacity(), list.getIsAcHall() ? "Yes" : "No");
        }
        System.out.println("+----------------------------------------------------+");
        boolean optionFlag = false ;
        do{
            try{
                System.out.println("Enter 1. Back ");
                String option = sc.readLine();
                if(!option.matches("1")){
                    optionFlag = true;
                    System.out.println("Enter valid Input");
                }
            } catch (IOException e) {
                optionFlag = true;
                System.out.println(e.getMessage());
            }
        }while (optionFlag);

    }
        public static void shortByCapacity(Customer customer){

        HallDirectory  hallDirectory = new HallDirectory();
        List<Hall> halls = hallDirectory.listHall();
        Collections.sort(halls);
            System.out.println("+----------------------------------------------------+");
            System.out.println("|                Sorted By Capacity                  |");
            System.out.println("+----------------------------------------------------+");
            System.out.printf("| %-20s | %-8s | %-6s | %-4s | %-10s |%n", "Hall Name", "Price", "Capacity", "AC");
            System.out.println("+----------------------------------------------------+");
            for (Hall list : halls) {
                System.out.printf("| %-20s | %-8.2f | %-6d | %-4s | %-10s |%n", list.getHallName(), list.getPrice(), list.getCapacity(), list.getIsAcHall() ? "Yes" : "No");
            }
            System.out.println("+----------------------------------------------------+");
            boolean optionFlag = false ;
            do{
                try{
                    System.out.println("Enter 1. Book Hall \n2.Back ");
                    String option = sc.readLine();
                    if(option.matches("1")){

                    }
                    if(!option.matches("1")){
                        optionFlag = true;
                        System.out.println("Enter valid Input");
                    }
                } catch (IOException e) {
                    optionFlag = true;
                    System.out.println(e.getMessage());
                }
            }while (optionFlag);

        }
        public static void shortByName(Customer customer){
            HallDirectory  hallDirectory = new HallDirectory();
            List<Hall> halls = hallDirectory.listHall();
            halls.sort(new SortByName());
            System.out.println("+----------------------------------------------------+");
            System.out.println("|                Sorted By Name                      |");
            System.out.println("+----------------------------------------------------+");
            System.out.printf("| %-20s | %-8s | %-6s | %-4s | %-10s |%n", "Hall Name", "Price", "Capacity", "AC");
            System.out.println("+----------------------------------------------------+");
            for (Hall list : halls) {
                System.out.printf("| %-20s | %-8.2f | %-6d | %-4s | %-10s |%n", list.getHallName(), list.getPrice(), list.getCapacity(), list.getIsAcHall() ? "Yes" : "No");
            }
            System.out.println("+----------------------------------------------------+");
            boolean optionFlag = false ;
            do{
                try{
                    System.out.println("Enter 1. Back ");
                    String option = sc.readLine();
                    if(!option.matches("1")){
                        optionFlag = true;
                        System.out.println("Enter valid Input");
                    }
                } catch (IOException e) {
                    optionFlag = true;
                    System.out.println(e.getMessage());
                }
            }while (optionFlag);
        }
}
