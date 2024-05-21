package com.hallbookingsystem.paymentdetails;

import com.hallbookingsystem.customexception.IntegerException;
import com.hallbookingsystem.customexception.Validate;
import com.hallbookingsystem.bookingdetails.Book;
import com.hallbookingsystem.dbconnection.DBConnection;
import com.hallbookingsystem.primarykey.PrimaryKeyProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * UPI class implements the `Payment` interface and represents UPI payment functionality.
 * It provides a `pay` method to process UPI payments for booking a hall.
 * @author Sanjai
 * @since 14-May-2024
 */
public class UPI implements Payment {

    private String upiId;

    public String getUpiId() {
        return upiId;
    }

    static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

    public boolean pay(Book book) {
        try {
            String query = "select hall_price*(End_date-start_date) as Price from booking b join halls h on b.hall_id = h.hall_id where book_id = ?";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);
            statement.setInt(1, book.getBookId());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            float totalPrice = resultSet.getFloat("Price");
            System.out.println("Total Price is " + totalPrice);
            System.out.println("Pay minimum amount " + (totalPrice / 2) + " to confirm Booking");
            float advanceAmount;
            boolean flag = false;
            do {
                try {
                    System.out.println("Enter the amount :");
                    advanceAmount = Validate.validInteger(sc.readLine());
                    if ((advanceAmount < totalPrice/2) || advanceAmount > totalPrice) {
                        System.out.println("Enter a valid amount (between "+ (totalPrice / 2)+" and " + totalPrice+ ")");
                        flag = true;
                    } else {
                        payAmount(advanceAmount, book.getBookId());
                        flag = false;
                    }
                } catch (IOException | NumberFormatException e) {
                    flag = true;
                    System.out.println(e.getMessage());
                } catch (IntegerException e) {
                    flag = true;
                    System.out.println("Invalid Amount");
                }
            } while (flag);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    /*payAmount method used to pay the amount int user*/
    private void payAmount(float amount, int id) {
        try {
            String query = "INSERT INTO payment VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);
            statement.setInt(1, PrimaryKeyProvider.primaryKey("Payment"));
            statement.setInt(2, id);
            statement.setTimestamp(3, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            statement.setString(4, "ADVANCED");
            statement.setFloat(5, amount);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Payment successful.");
                new Book().updateBooking(id);
            } else {
                System.out.println("Failed to process payment.");
            }
        } catch (SQLException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}
