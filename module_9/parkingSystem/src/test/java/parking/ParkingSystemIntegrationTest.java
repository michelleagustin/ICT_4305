package parking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

public class ParkingSystemIntegrationTest {

    @Test
    public void testFullParkingSystemWorkflow() throws InterruptedException {
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
        // 3. Register Customers and Cars/Permits
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
        // 4. Simulate Car Entries
        // ----------------------------
        ParkingCharge entry1 = lotEntryOnly.enter(permit1, office);
        ParkingCharge entry2 = lotEntryExit.enter(permit2, office);

        assertNotNull(entry1, "Entry charge for ENTRY_ONLY lot should not be null");
        assertNull(entry2, "Entry charge for ENTRY_EXIT lot should be null until exit");

        // ----------------------------
        // 5. Simulate Exit for ENTRY_EXIT lot
        // ----------------------------
        Thread.sleep(1000); // simulate time passing
        ParkingCharge exit2 = lotEntryExit.exit(permit2, office);

        assertNotNull(exit2, "Exit charge should exist for ENTRY_EXIT lot");
        assertTrue(exit2.getAmount().getDollars() > 0, "Exit charge should be greater than $0");

        // ----------------------------
        // 6. Verify Customers
        // ----------------------------
        List<String> customerIds = office.getCustomerIds();
        assertEquals(2, customerIds.size());
        assertTrue(customerIds.contains(cust1.getCustomerId()));
        assertTrue(customerIds.contains(cust2.getCustomerId()));

        // ----------------------------
        // 7. Verify Permits
        // ----------------------------
        List<String> allPermitIds = office.getPermitIds();
        assertEquals(2, allPermitIds.size());
        assertTrue(allPermitIds.contains(permit1.getId()));
        assertTrue(allPermitIds.contains(permit2.getId()));

        List<String> cust1Permits = office.getPermitIds(cust1);
        assertEquals(1, cust1Permits.size());
        assertEquals(permit1.getId(), cust1Permits.get(0));

        // ----------------------------
        // 8. Verify Parking Charges
        // ----------------------------
        List<ParkingCharge> charges = office.getCharges();
        assertEquals(2, charges.size());
        assertEquals(entry1.getPermitId(), charges.get(0).getPermitId());
        assertEquals(exit2.getPermitId(), charges.get(1).getPermitId());

        // ----------------------------
        // 9. Verify Discounts Applied
        // ----------------------------
        long entry1Cents = Math.round(entry1.getAmount().getDollars() * 100);
        assertEquals(400, entry1Cents, "Compact car entry discount applied correctly");

        long exit2Cents = Math.round(exit2.getAmount().getDollars() * 100);
        assertTrue(exit2Cents >= 300, "SUV exit charge should be at least $3/hour");

        // ----------------------------
        // 10. Verify Lot Info
        // ----------------------------
        assertEquals(2, office.getLots().size());
        assertTrue(office.getLots().contains(lotEntryOnly), "LotA should exist in office lots");
        assertTrue(office.getLots().contains(lotEntryExit), "LotB should exist in office lots");
    }
}
