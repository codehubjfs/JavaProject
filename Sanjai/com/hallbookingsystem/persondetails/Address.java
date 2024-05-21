package com.hallbookingsystem.persondetails;
/**
 * Address class represents an address with various details such as door number,
 * street name, town, city, and postal code.
 * @author Sanjai
 * @since 13-May-2024
 */
public class Address {
    private String doorNumber;
    private String streetName;
    private String town;
    private String city;
    private String code;

    public Address(String doorNumber, String streetName, String town, String city, String code) {
        this.doorNumber = doorNumber;
        this.streetName = streetName;
        this.town = town;
        this.city = city;
        this.code = code;
    }
    public Address(String city){
        this.city = city;
    }

    @Override
    public String toString() {
        if(doorNumber!=null){
            return doorNumber +","+ streetName  + ", " + town  + ", " + city  + ", " + code ;
        }
        else {
            return city;
        }

    }
}
