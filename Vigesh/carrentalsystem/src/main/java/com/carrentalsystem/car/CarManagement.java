package com.carrentalsystem.car;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.carrentalsystem.exception.NumberException;
import com.carrentalsystem.exception.Validate;
import com.carrentalsystem.rental.BookRental;
import com.gotrip.CarRentalSystem.DbConnection;

import user.Admin;
import user.UserDriver1;

public class CarManagement {
	public static Connection con = DbConnection.getConnection();
	static BufferedReader scanner=new BufferedReader(new InputStreamReader(System.in));
	 static Scanner sc= new Scanner(System.in);
	  ///adding the details of car
      public boolean addcar() throws SQLException {	        
	        System.out.println("Enter the car name:");
	        String carName = sc.nextLine();
	        System.out.println("Enter the model:");
	        String model = sc.nextLine();	        
	        boolean flag=false;
	        long rentalRate=0;
	        do {
	        	try {
	        		//sc.nextLine();	        				
	        System.out.println("Enter the rental rate per Hour:");	        
	        rentalRate =Long.parseLong( scanner.readLine());
	        //sc.nextLine();
	        break;
	        	}
	        	catch(Exception e) {
	        		System.out.println("Invlid Input Enter Valid:");
	        		flag=true;
	        	}
	        }while(flag);
	       // sc.nextLine(); 
	        
//	        System.out.println("Enter the availability:");
//	        String availability = sc.nextLine();
	        flag=false;
	        int seats=0;
	        do {
	        	try {
	        System.out.println("Enter the number of seats:");
	        seats = Validate.ValidateNumber(scanner.readLine());
	        break;
	        	}catch(Exception e) {
	        		System.out.println("Enter Valid Input:");
	        		flag=true;
	        	}
	        }while(flag);        
	        //sc.nextLine();
	        flag=false;
	        int baggage=0;
	        do {
	        	try {
	        System.out.println("Enter the baggage capacity:");
	         baggage = Validate.ValidateNumber(scanner.readLine());
	         break;
	        	}catch(Exception e) {
	        		System.out.println("Enter Valid Input");
	        		flag=true;
	        	}
	        }while(flag);
	        //sc.nextLine(); 
	        String fuelType="";
	        flag=false;
	        do {
	        	try {
	        		 System.out.println("Enter the fuel type:(1.Diesel/2.Petrol/3.Electric)");
	        		 int fuelOption=Validate.ValidateNumber(scanner.readLine());;
	        		 switch(fuelOption) {
	        		 case 1:
	        			 fuelType="Diesel";
	        			 flag=false;
	        			 break;
	        		 case 2:
	        			 fuelType="Petrol";
	        			 flag=false;
	        			 break;
	        		 case 3:
	        			 fuelType="Electric";
	        			 flag=false;
	        			 break;
	        		 default:
	        			 System.out.println("Enter Valid Input:");
	        			 flag=true;
	        			 
	        		 }
	        		
	         
	        	}catch(Exception e) {
	        		System.out.println("Enter Valid Number:");
	        		flag=true;
	        		
	       
	        	}
	        }while(flag);
	        //String fuelType = sc.nextLine();
	        System.out.println("Enter the mileage: km\\l");
	        String mileage = sc.nextLine();
	        System.out.println("Enter Car Type:(Luxury/SUV/Sedan)");
	        String carType=sc.nextLine();
//		   Admin admin=new Admin();
//		   admin.carManagement(adminOption,
	       
    	  Statement st=con.createStatement();
    	  ResultSet rs = st.executeQuery("SELECT carseq.nextval FROM dual");
    	  int carid = 0; 
 	     if (rs.next()) {
 	         carid = rs.getInt(1); 
 	     }
 	     //System.out.println(carid);
 	    Car car=new Car(carid, carName, model, rentalRate,"Available", seats, baggage, fuelType, mileage,"vehicle_number","car_type");
		setCar(car);
 	    return flag;
	      
      }
      public void setCar(Car car) throws SQLException {
    	  Statement st=con.createStatement();
    	  int r= st.executeUpdate("INSERT INTO car(car_id, car_name,model,rental_rate,availability,seat,baggage,fuel_type,mileage,car_type) " +
    		      "VALUES (" +car.getCarId() + ", '" + car.getCarName() + "', '" + car.getCarModel() + "', '" + car.getRentalRate() + "', '" + 
    		    		  car.getAvailability() + "'," + car.getSeatNumber() + "," + car.getBaggage() + ", '" + car.getFuelType() + "', '" +
    		              car.getMileage()+ "','" +car.getCarType()  + "' "+")"
    		    		  );
    		      System.out.println("added successfully");
    	    	 // return true;
      }
      
      // update the details of car in database
      public void updatecar() throws SQLException, NumberException, IOException {
    	  Statement st=con.createStatement();
    	  ResultSet resultSet = st.executeQuery("SELECT CAR_ID, CAR_NAME, MODEL FROM car");
    	  System.out.println("+-------+---------------------+-------------+");
          System.out.println("| CAR_ID|     CAR_NAME        |    MODEL    |");
          System.out.println("+-------+---------------------+-------------+");
          while (resultSet.next()) {
              int carId = resultSet.getInt("CAR_ID");
              String carName = resultSet.getString("CAR_NAME");
              String model = resultSet.getString("MODEL");
              System.out.printf("| %-6d| %-20s| %-12s|\n", carId, carName, model);
          }
    	  System.out.print("Enter the CAR_ID to update: ");
    	  
    	  int carId = Validate.ValidateNumber(scanner.readLine());;
    	  resultSet = st.executeQuery("SELECT * FROM car WHERE CAR_ID = " + carId);
    	  if (resultSet.next()) {
              System.out.println("+-------+---------------------+---------------------+--------+-------------+------+------+----------+----------+");
              System.out.printf("| %-6s | %-19s | %-19s | %-6s | %-11s | %-4s | %-4s | %-8s | %-8s |\n",
                      "CAR_ID", "CAR_NAME", "MODEL", "RATE", "AVAILABILITY", "SEAT", "BAGGAGE", "FUEL_TYPE", "MILEAGE");
              System.out.println("+-------+---------------------+---------------------+--------+-------------+------+------+----------+----------+");
              System.out.printf("| %-6d | %-19s | %-19s | %-6d | %-11s | %-4d | %-4d | %-8s | %-8s |\n",
                      resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                      resultSet.getInt(4), resultSet.getString(5), resultSet.getInt(6),
                      resultSet.getInt(7), resultSet.getString(8), resultSet.getString(9));
              System.out.println("+-------+---------------------+---------------------+--------+-------------+------+------+----------+----------+");
          }else {
        	  System.out.println("Please Enter Valid Id");
        	 throw new NumberException("Enter Valid input:");
          }          
          System.out.println("Choose the option to update:");
          System.out.println("1. CAR_NAME");
          System.out.println("2. MODEL");
          System.out.println("3. RENTAL_RATE");
          System.out.println("4. AVAILABILITY");
          System.out.println("5. SEAT");
          System.out.println("6. BAGGAGE");
          System.out.println("7. FUEL_TYPE");
          System.out.println("8. MILEAGE");
          System.out.print("Enter option number: ");
          int option;
          String columnName = null;
			option = Validate.ValidateNumber(scanner.readLine());
	          switch (option) {
	              case 1:
	                  columnName = "CAR_NAME";
	                  break;
	              case 2:
	                  columnName = "MODEL";
	                  break;
	              case 3:
	                  columnName = "RENTAL_RATE";
	                  break;
	              case 4:
	                  columnName = "AVAILABILITY";
	                  break;
	              case 5:
	                  columnName = "SEAT";
	                  break;
	              case 6:
	                  columnName = "BAGGAGE";
	                  break;
	              case 7:
	                  columnName = "FUEL_TYPE";
	                  break;
	              case 8:
	                  columnName = "MILEAGE";
	                  break;
	              default:
	                  System.out.println("Invalid option.");
	                  updatecar();
	                  break;
	                 // return true;
	          }          
          System.out.println("Enter New Value to Insert:");
         String newValue=sc.nextLine();
           st=con.createStatement();
	      int r= st.executeUpdate("UPDATE car SET " + columnName + " = '" + newValue + "' WHERE CAR_ID = " + carId);
		   System.out.println("Updated Successfully");    	  
      }
      //delete the car details in car table
      public boolean deletecar() throws SQLException {
    	  Statement st=con.createStatement();
    	  ResultSet resultSet = st.executeQuery("SELECT CAR_ID, CAR_NAME, MODEL FROM car");
    	  System.out.println("+-------+---------------------+-------------+");
          System.out.println("| CAR_ID|     CAR_NAME        |    MODEL    |");
          System.out.println("+-------+---------------------+-------------+");
          while (resultSet.next()) {
              int carId = resultSet.getInt("CAR_ID");
              String carName = resultSet.getString("CAR_NAME");
              String model = resultSet.getString("MODEL");
              System.out.printf("| %-6d| %-20s| %-12s|\n", carId, carName, model);
          }
    	  System.out.println("Enter Id for delete");
		   int carId=0;
		try {
			carId = Validate.ValidateNumber(scanner.readLine());
		} catch (NumberException e) {
			System.out.println("Enter a Valid Number:");
			deletecar();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		    resultSet = st.executeQuery("SELECT * FROM car WHERE CAR_ID = " + carId);
               if (resultSet.next()) {
               System.out.println("+-------+---------------------+---------------------+--------+-------------+------+------+----------+----------+");
               System.out.printf("| %-6s | %-19s | %-19s | %-6s | %-11s | %-4s | %-4s | %-8s | %-8s |\n",
                       "CAR_ID", "CAR_NAME", "MODEL", "RATE", "AVAILABILITY", "SEAT", "CAR_TYPE", "FUEL_TYPE", "MILEAGE");
               System.out.println("+-------+---------------------+---------------------+--------+-------------+------+------+----------+----------+");
               System.out.printf("| %-6d | %-19s | %-19s | %-6d | %-11s | %-4d | %-4s | %-8s | %-8s |\n",
                       resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                       resultSet.getInt(4), resultSet.getString(5), resultSet.getInt(6),
                       resultSet.getString(11), resultSet.getString(8), resultSet.getString(9));
               System.out.println("+-------+---------------------+---------------------+--------+-------------+------+------+----------+----------+");
           } else {
               System.out.println("No row found with CAR_ID: " + carId);
               deletecar();
           }
		   System.out.println("Do You Really Want to Delete This? (Yes-1/No-2) 3.Go Back");
		   int check=sc.nextInt();
    	     switch(check) {
    	     case 1:
    	    	 ResultSet set=st.executeQuery("delete from car where car_id="+carId+"  ");
    	    	 System.out.println("Deleted Successfully");
    	    	 return true;
    	     case 2:
    	    	 deletecar();
    	    	 return false;
    	     case 3:
    	    	 UserDriver1.carManagement();
    	    	 break;    	    	 
    	     }
    	     return false;
		    // ResultSet set=st.executeQuery("delete from car where car_id="+carId+"  ");
		    // System.out.println("Deleted Successfully");
      }
      //viewing the details of car and booking
      public static void showCars() throws SQLException {
    	    String query = "SELECT car_id, car_type, car_name, model, fuel_type FROM car WHERE availability = 'Available'";
    	    PreparedStatement st = con.prepareStatement(query);
    	 //  ArrayList< Car >avlCars = new ArrayList<>();  	    
    	    ResultSet set = st.executeQuery();
    	    System.out.println("+----------+---------------------+---------------------+-------------+---------------+");
    	    System.out.println("| CARID    | CAR_TYPE            | CAR_NAME            | MODEL       | Fuel          |");
    	    System.out.println("+----------+---------------------+---------------------+-------------+---------------+");
    	    while (set.next()) {
    	        int carId = set.getInt("car_id");
    	        String carType = set.getString("car_type");
    	        String carName = set.getString("car_name");
    	        String model = set.getString("model");
    	        String fuel = set.getString("fuel_type");
    	        System.out.printf("| %-8d | %-20s | %-20s | %-10s | %-12s |\n", carId, carType, carName, model, fuel);  	        
    	    }
    	    System.out.println("Enter car Id:");
    	    int carId = 0;
			try {
				carId = Validate.ValidateNumber(scanner.readLine());
			} catch (NumberException e) {
				System.out.println("Enter a Valid Car Id:");
				showCars();
			} catch (IOException e) {
				showCars();
			};
			try {
    	   BookRental.bookCars(carId);
			}catch(SQLException e) {
				System.out.println("Enter Valid Id:");
				showCars();
			}   	    
    	}//search method for search the car by type
      public static void search() throws SQLException, NumberException, IOException {
    	  System.out.println("Search By CarType\n1.Sedan\n2.SUV\n3.Luxury");
    	  int optionType = Validate.ValidateNumber(scanner.readLine());;
    	  String key = "car_type";
    	  String value;
    	  switch (optionType) {
    	      case 1:
    	          value = "Sedan";
    	          break;
    	      case 2:
    	          value = "SUV";
    	          break;
    	      case 3:
    	          value = "Luxury";
    	          break;
    	      default:
    	    	  System.out.println("Enter Valid Input:");
    	    	  search();
    	          value = "Invalid";
    	          break;
    	  }
    	  String query = "SELECT car_id, car_type, car_name, model, fuel_type FROM car WHERE availability = 'Available' and "+key+"='"+value+"'";
  	    PreparedStatement st = con.prepareStatement(query);
  	    ResultSet set = st.executeQuery();
  	    System.out.println("+----------+---------------------+---------------------+-------------+---------------+");
  	    System.out.println("| CARID    | CAR_TYPE            | CAR_NAME            | MODEL       | Fuel          |");
  	    System.out.println("+----------+---------------------+---------------------+-------------+---------------+");
  	    while (set.next()) {
  	        int carId = set.getInt("car_id");
  	        String carType = set.getString("car_type");
  	        String carName = set.getString("car_name");
  	        String model = set.getString("model");
  	        String fuel = set.getString("fuel_type");
  	        System.out.printf("| %-8d | %-20s | %-20s | %-10s | %-12s |\n", carId, carType, carName, model, fuel);
  	    } 
      }
      // viewing the details in the collections car
      public static void showGuest() throws SQLException {
    	  List<Car>showCar =listCar();
    	  Collections.sort(showCar,new Car()); 
    	  showCar.stream().forEach(x->System.out.println(x));
//    	  for(Car car:showCar) {
//    		  System.out.println(car.getCarName());
//    	  }
      }      
      // storing the car details in collections
      public static List<Car> listCar() throws SQLException{
    	  String query="SELECT car_id,car_name,model,rental_rate,availability,seat,baggage,fuel_type,mileage,vehicle_number,car_type from car";
    	  PreparedStatement st=con.prepareStatement(query);
    	  ResultSet set=st.executeQuery();
    	  List<Car> carList=new ArrayList<Car>();
    	  while(set.next()) {
    		  long carId = set.getInt("car_id");
    	        String carName = set.getString("car_name");
    	        String model = set.getString("model");
    	        long rentalRate = set.getLong("rental_rate");
    	        String availability = set.getString("availability");
    	        int seat=set.getInt("seat");
    	        int baggage=set.getInt("baggage");
    	        String fuel=set.getString("fuel_type");
    	        String mileage=set.getString("mileage");
    	        String vehicleNo=set.getString("vehicle_number");
    	        String carType=set.getString("car_type");
    	        Car car=new Car(carId,carName,model,rentalRate, availability,seat,baggage,fuel,mileage,vehicleNo,carType);   	        
    	        carList.add(car);
//    	        this.carId = carId;
//    			this.carName = carName;
//    			this.carModel = carModel;
//    			this.rentalRate = rentalRate;
//    			this.availability = availability;
//    			this.seatNumber = seatNumber;
//    			this.baggage = baggage;
//    			this.fuelType = fuelType;
//    			this.mileage = mileage;
//    			this.vehicleNumber=vehicleNumber;
//    			this.carType=carType;    	        
   	       // return listCar;     
    	  }
		return carList;    	  
      }
      public static void view() throws SQLException {
    	  Statement st=con.createStatement();
    	  ResultSet resultSet = st.executeQuery("SELECT CAR_ID, CAR_NAME, MODEL FROM car");
    	  System.out.println("+-------+---------------------+-------------+");
          System.out.println("| CAR_ID|     CAR_NAME        |    MODEL    |");
          System.out.println("+-------+---------------------+-------------+");
          while (resultSet.next()) {
              int carId = resultSet.getInt("CAR_ID");
              String carName = resultSet.getString("CAR_NAME");
              String model = resultSet.getString("MODEL");
              System.out.printf("| %-6d| %-20s| %-12s|\n", carId, carName, model);
          }
//    	  System.out.println("Enter Id for delete");
//		   int carId=sc.nextInt();
//		    resultSet = st.executeQuery("SELECT * FROM car WHERE CAR_ID = " + carId);
//               if (resultSet.next()) {
//               System.out.println("+-------+---------------------+---------------------+--------+-------------+------+------+----------+----------+");
//               System.out.printf("| %-6s | %-19s | %-19s | %-6s | %-11s | %-4s | %-4s | %-8s | %-8s |\n",
//                       "CAR_ID", "CAR_NAME", "MODEL", "RATE", "AVAILABILITY", "SEAT", "BAGGAGE", "FUEL_TYPE", "MILEAGE");
//               System.out.println("+-------+---------------------+---------------------+--------+-------------+------+------+----------+----------+");
//               System.out.printf("| %-6d | %-19s | %-19s | %-6d | %-11s | %-4d | %-4d | %-8s | %-8s |\n",
//                       resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
//                       resultSet.getInt(4), resultSet.getString(5), resultSet.getInt(6),
//                       resultSet.getInt(7), resultSet.getString(8), resultSet.getString(9));
//               System.out.println("+-------+---------------------+---------------------+--------+-------------+------+------+----------+----------+");
           }
}
