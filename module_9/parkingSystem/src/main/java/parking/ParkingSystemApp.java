package parking;

import java.time.LocalDate;

public class ParkingSystemApp {
    public static void main(String[] args) {
        // ----------------------------
        // 1. Create Parking Office
        // ----------------------------
        Address officeAddress = new Address("123 University Ave", "", "Denver", "CO", "80210");
        ParkingOffice office = new ParkingOffice("University Parking Office", officeAddress);

        // ----------------------------
        // 2. Create Parking Lots
        // ----------------------------
        ParkingLot lotEntryOnly = new ParkingLot("LotA", ParkingLot.ScanType.ENTRY_ONLY, new Money(500)); // $5 flat
        ParkingLot lotEntryExit = new ParkingLot("LotB", ParkingLot.ScanType.ENTRY_EXIT, new Money(300)); // $3/hour
        office.addLot(lotEntryOnly);
        office.addLot(lotEntryExit);

        // ----------------------------
        // 3. Register Customers, Cars, and Permits
        // ----------------------------
        Address addr1 = new Address("789 Elm St", "Apt 2B", "Denver", "CO", "80204");
        Customer cust1 = office.register("Stella Moore", addr1, "555-1234");
        Car car1 = new Car("ABC123", CarType.COMPACT);
        ParkingPermit permit1 = office.register(cust1, car1, LocalDate.now().plusYears(1));

        Address addr2 = new Address("456 Maple Ave", "", "Denver", "CO", "80205");
        Customer cust2 = office.register("James Lee", addr2, "555-5678");
        Car car2 = new Car("XYZ789", CarType.SUV);
        ParkingPermit permit2 = office.register(cust2, car2, LocalDate.now().plusYears(1));

        // ----------------------------
        // 4. Simulate Parking Entry
        // ----------------------------
        System.out.println("=== Car Entries ===");
        ParkingCharge entry1 = lotEntryOnly.enter(permit1, office);  // ENTRY_ONLY lot
        ParkingCharge entry2 = lotEntryExit.enter(permit2, office);  // ENTRY_EXIT lot

        if (entry1 != null) System.out.println("Entry Charge (LotA): " + entry1);
        if (entry2 != null) System.out.println("Entry Charge (LotB): " + entry2);
        else System.out.println("Car2 entered LotB; charge will be calculated on exit.");

        // ----------------------------
        // 5. Simulate time passing and exit
        // ----------------------------
        try { Thread.sleep(2000); } catch (InterruptedException e) { }

        ParkingCharge exit2 = lotEntryExit.exit(permit2, office);
        if (exit2 != null) System.out.println("Exit Charge (LotB): " + exit2);

        // ----------------------------
        // 6. Print summaries
        // ----------------------------
        System.out.println("\n=== Customers ===");
        System.out.println(cust1);
        System.out.println(cust2);

        System.out.println("=== Parking Lots ===");
        for (ParkingLot lot : office.getLots()) {
            System.out.println(lot);
        }

        System.out.println("\n=== All Charges ===");
        for (ParkingCharge pc : office.getCharges()) {
            System.out.println(pc);
        }

        // ----------------------------
        // 7. Optional: List of IDs
        // ----------------------------
        System.out.println("\nCustomer IDs: " + office.getCustomerIds());
        System.out.println("Permit IDs: " + office.getPermitIds());
        System.out.println("Permit IDs for " + cust1.getName() + ": " + office.getPermitIds(cust1));
    }
}
