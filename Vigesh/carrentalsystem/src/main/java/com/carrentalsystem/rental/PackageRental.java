package com.carrentalsystem.rental;

import java.util.Scanner;

class CarRentalSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Assume you have a Car class with relevant attributes (car_id, model, etc.)
        // and a Booking class with booking details (booking_id, start_date, end_date, etc.).

        // Prompt user for package selection
        System.out.println("Choose a package:");
        System.out.println("1. Hourly");
        System.out.println("2. Daily");
        System.out.println("3. Weekly");
        System.out.println("4. Monthly");
        System.out.print("Enter your choice (1/2/3/4): ");
        int choice = scanner.nextInt();

        // Calculate price based on package
        double pricePerHour = 10.0; // Example hourly rate
        double pricePerDay = 50.0; // Example daily rate
        double pricePerWeek = 300.0; // Example weekly rate
        double pricePerMonth = 1000.0; // Example monthly rate

        double totalPrice = 0.0;
        switch (choice) {
            case 1:
                System.out.print("Enter hours: ");
                int hours = scanner.nextInt();
                totalPrice = hours * pricePerHour;
                break;
            case 2:
                System.out.print("Enter days: ");
                int days = scanner.nextInt();
                totalPrice = days * pricePerDay;
                break;
            case 3:
                System.out.print("Enter weeks: ");
                int weeks = scanner.nextInt();
                totalPrice = weeks * pricePerWeek;
                break;
            case 4:
                System.out.print("Enter months: ");
                int months = scanner.nextInt();
                totalPrice = months * pricePerMonth;
                break;
            default:
                System.out.println("Invalid choice!");
        }

        System.out.println("Total price: $" + totalPrice);

        // Save booking details to your database or data structure
        // ...

        scanner.close();
    }
}

