package com.hallbookingsystem.halldetails;

import com.hallbookingsystem.dbconnection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Seats class represents a seat within a hall in the hall booking system.
 * A seat has properties like seat ID, arrangement type (e.g., theater, classroom),
 * and capacity (number of people it can accommodate).  It also provides a method
 * to retrieve a list of available seating arrangements from the database.
 * @author Sanjai
 * @since  14-May-2024
 */
public class Seats {
    private int seatId;

    private String arrangementType;
    private int capacity;

    // Constructor with all parameters
    public Seats(int seatId,String arrangementType, int capacity) {
        this.seatId = seatId;
        this.arrangementType = arrangementType;
        this.capacity = capacity;
    }
    // Empty constructor (for potential object initialization)
    public Seats() {
    }
    // Getters and setters for all member variables
    public String getArrangementType() {
        return arrangementType;
    }

    public void setArrangementType(String arrangementType) {
        this.arrangementType = arrangementType;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


     /* arrangementList methods to retrieves a list of available seating arrangements from the database table "Seating_Arrangement".*/
    public  List<String> arrangementList() throws SQLException {
        String query = "Select * from Seating_Arrangement ";
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);
        ResultSet set = statement.executeQuery();
        List<String> seatOrderList = new ArrayList<>();
        while(set.next()){
            arrangementType=set.getString("arrangement_type");
            seatOrderList.add(arrangementType);
        }
        return seatOrderList;
    }
}
