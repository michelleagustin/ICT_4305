package parking;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ParkingLotTest {

    @Test
    void testEntryExitCharges() {
        ParkingOffice office = new ParkingOffice("Office", new Address("Addr", "", "City", "ST", "12345"));
        Customer customer = office.register("John Doe", new Address("Addr", "", "City", "ST", "12345"), "555-1234");
        Car car = office.register(customer, "ABC123", CarType.COMPACT);

        ParkingLot lot = new ParkingLot("LotB", ParkingLot.ScanType.ENTRY_EXIT, new Money(300));

        // Enter
        ParkingCharge entry = lot.enter(car, office);
        assertNull(entry); // ENTRY_EXIT charges on exit

        // Exit
        ParkingCharge exit = lot.exit(car, office);
        assertNotNull(exit);
        assertEquals(car.getPermitId(), exit.getPermitId());
        assertTrue(exit.getAmount().getDollars() > 0);
    }

    @Test
    void testEntryOnlyCharge() {
        ParkingOffice office = new ParkingOffice("Office", new Address("Addr", "", "City", "ST", "12345"));
        Customer customer = office.register("Jane Doe", new Address("Addr", "", "City", "ST", "12345"), "555-5678");
        Car car = office.register(customer, "XYZ789", CarType.SUV);

        ParkingLot lot = new ParkingLot("LotA", ParkingLot.ScanType.ENTRY_ONLY, new Money(500));

        ParkingCharge charge = lot.enter(car, office);
        assertNotNull(charge);
        assertEquals(car.getPermitId(), charge.getPermitId());
    }
}
