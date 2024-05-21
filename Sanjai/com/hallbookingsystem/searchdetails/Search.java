package com.hallbookingsystem.searchdetails;

import com.hallbookingsystem.persondetails.Customer;

public interface Search {
    public void searchByName(Customer customer);
    public void searchByPrice(Customer customer);
    public void searchByCapacity(Customer customer);
}
