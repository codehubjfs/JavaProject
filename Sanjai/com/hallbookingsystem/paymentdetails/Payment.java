package com.hallbookingsystem.paymentdetails;

import com.hallbookingsystem.bookingdetails.Book;

import java.io.IOException;
import java.sql.SQLException;

public interface Payment {
        boolean pay(Book book) throws SQLException, IOException;

}
