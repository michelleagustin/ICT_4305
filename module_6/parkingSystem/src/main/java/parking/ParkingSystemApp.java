package parking;

import java.time.Instant;

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

        office.getLots().add(lotEntryOnly);
        office.getLots().add(lotEntryExit);

        // ----------------------------
        // 3. Register Customers and Cars
        // ----------------------------
        Address addr1 = new Address("789 Elm St", "Apt 2B", "Denver", "CO", "80204");
        Customer cust1 = office.register("Stella Moore", addr1, "555-1234");
        Car car1 = office.register(cust1, "ABC123", CarType.COMPACT);

        Address addr2 = new Address("456 Maple Ave", "", "Denver", "CO", "80205");
        Customer cust2 = office.register("James Lee", addr2, "555-5678");
        Car car2 = office.register(cust2, "XYZ789", CarType.SUV);

        // ----------------------------
        // 4. Simulate Parking
        // ----------------------------
        System.out.println("=== Car Entries ===");
        ParkingCharge entry1 = lotEntryOnly.enter(car1, office);  // ENTRY_ONLY lot
        ParkingCharge entry2 = lotEntryExit.enter(car2, office);  // ENTRY_EXIT lot

        if (entry1 != null) System.out.println("Entry Charge (LotA): " + entry1);
        if (entry2 != null) System.out.println("Entry Charge (LotB): " + entry2);
        else System.out.println("Car2 entered LotB; charge will be on exit.");

        // Simulate some time passing for LotB (ENTRY_EXIT)
        try { Thread.sleep(2000); } catch (InterruptedException e) { }

        ParkingCharge exit2 = lotEntryExit.exit(car2, office);  // exit for car2
        if (exit2 != null) System.out.println("Exit Charge (LotB): " + exit2);

        // ----------------------------
        // 5. Print Summaries
        // ----------------------------
        System.out.println("\n=== Customers ===");
        System.out.println(cust1);
        System.out.println(cust2);

        System.out.println("=== Parking Lots ===");
        System.out.println(lotEntryOnly);
        System.out.println(lotEntryExit);

        System.out.println("\n=== All Charges ===");
        for (ParkingCharge pc : office.getCharges()) {
            System.out.println(pc);
        }
    }
}
