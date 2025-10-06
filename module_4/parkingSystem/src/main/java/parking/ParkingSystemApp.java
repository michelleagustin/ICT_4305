package parking;

import java.time.LocalDate;

public class ParkingSystemApp {
    public static void main(String[] args) {
        // Create an address for the parking lot
        Address lotAddress = new Address(
                "123 University Ave", "", "Denver", "CO", "80210");

        // Create a parking lot with capacity 2
        ParkingLot lot = new ParkingLot("LotA", lotAddress, 2);

        // Create a customer and their address
        Address customerAddress = new Address(
                "789 Elm St", "Apt 2B", "Denver", "CO", "80204");

        Customer customer = new Customer(
                "CUST001", "Stella Moore", customerAddress, "555-1234");

        // Register a car for the customer
        Car car1 = customer.register("ABC123", CarType.COMPACT);

        // Simulate entry and exit
        lot.entry(car1);

        // Print summary
        System.out.println(customer);
        System.out.println(car1);
        System.out.println(lot);
    }
}
