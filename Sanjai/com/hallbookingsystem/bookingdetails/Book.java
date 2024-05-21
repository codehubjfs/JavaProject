package com.hallbookingsystem.bookingdetails;

import com.hallbookingsystem.dbconnection.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The Book class represents a booking in the hall booking system.
 * It includes details such as booking ID, hall ID, person ID, requested time,
 * start date, end date, and booking status.
 * @author Sanjai
 * @since 10-May-2024
 */

public class Book {
    private int bookId;
    private int hallId;
    private int personId;
    private LocalDateTime requested;
    private LocalDate startDate;

    private LocalDate endDate;
    private BookingStatus bookingStatus;

    // Default constructor
    public Book(){}

    // Constructor to initialize Book with essential details
    public Book(int bookId, LocalDate startDate, LocalDate end_date,BookingStatus bookingStatus) {
        this.bookId = bookId;
        this.startDate = startDate;
        this.endDate =end_date;
        this.bookingStatus=bookingStatus;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public LocalDateTime getRequested() {
        return requested;
    }

    public void setRequested(LocalDateTime requested) {
        this.requested = requested;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
    // updateBooking method user can able to cancel the approve booking of the Admin
    public void updateBooking(int id) throws SQLException {
        try {
            String str = "Update Booking Set BOOK_STATUS= 'CONFIRMED' where book_id = ?";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(str);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //blockOtherBooking method used to cancel all the tickets how pay the first payment
    public static void blockOtherBooking(Book selectedBook) {
        String query = "SELECT book_id, start_date, end_date, book_status FROM booking WHERE hall_id = ?";
        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);
            statement.setInt(1, selectedBook.getHallId());
            ArrayList<Book> bookList = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Integer> bookIdList = new ArrayList<>();

            // Retrieve all bookings for the same hall
            while (resultSet.next()) {
                bookList.add(new Book(resultSet.getInt("book_id"), resultSet.getDate("start_date").toLocalDate(),
                        resultSet.getDate("end_date").toLocalDate(), BookingStatus.valueOf(resultSet.getString("BOOK_STATUS"))));
            }
            LocalDate startDate = selectedBook.getStartDate();
            LocalDate endDate = selectedBook.getEndDate();

            // Identify bookings that overlap with the selected booking
            for (Book book : bookList) {
                if (startDate.isBefore(book.getStartDate()) && endDate.isAfter(book.getStartDate()))
                {
                    if((book.getBookingStatus().equals(BookingStatus.PENDING)|| book.getBookingStatus()== (BookingStatus.APPROVED)))
                    {
                        bookIdList.add(book.getBookId());
                    }
                }
            }

            // Update the status of overlapping bookings to 'CANCELED'
            for (int bookId : bookIdList) {
                String blockUpdateQuery = "UPDATE booking SET book_status = 'CANCELED' WHERE book_id = ?";
                PreparedStatement blockStatement = DBConnection.getInstance().getConnection().prepareStatement(blockUpdateQuery);
                blockStatement.setInt(1, bookId);
                blockStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
