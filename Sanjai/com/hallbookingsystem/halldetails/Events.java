package com.hallbookingsystem.halldetails;

import com.hallbookingsystem.dbconnection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Events class represents event details and provides methods to interact with event data.
 * This class includes methods for setting and getting event details as well as a method to
 * retrieve a list of events from the database.
 * @author Sanjai
 * @since 13-May-2024
 */
public class Events {
    private int eventId;
    private String eventName;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public Events() {

    }

    public void setEventName(String eventName) {
        eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    public Events(String eventName, int eventId) {
        this.eventName = eventName;
        this.eventId = eventId;
    }

    public List<String> displayEvents() throws SQLException {
        List<String> eventList = new ArrayList<String>();
        String query = "select * from event";
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);
        ResultSet set = statement.executeQuery();
        while(set.next()){
           eventName = set.getString("event_name");
            eventList.add(eventName);
        }
        return eventList;
    }
}
