package parking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class ParkingLotTest {

    @Test
    public void testEntryOnlyLot() {
        ParkingLot lot = new ParkingLot("LotA", ParkingLot.ScanType.ENTRY_ONLY, new Money(500));
        ParkingOffice office = new ParkingOffice("Office", new Address("Addr", "", "City", "ST", "00000"));
        Customer cust = office.register("Alice", new Address("Addr", "", "City", "ST", "00000"), "555-1111");
        Car car = new Car("ABC123", CarType.COMPACT);
        ParkingPermit permit = office.register(cust, car, LocalDate.now().plusYears(1));

        ParkingCharge charge = lot.enter(permit, office);

        assertNotNull(charge);
        assertEquals(permit.getId(), charge.getPermitId());
        assertEquals(400, charge.getAmount().getDollars() * 100); // 20% discount for COMPACT
    }

    @Test
    public void testEntryExitLot() throws InterruptedException {
        ParkingLot lot = new ParkingLot("LotB", ParkingLot.ScanType.ENTRY_EXIT, new Money(300));
        ParkingOffice office = new ParkingOffice("Office", new Address("Addr", "", "City", "ST", "00000"));
        Customer cust = office.register("Bob", new Address("Addr", "", "City", "ST", "00000"), "555-2222");
        Car car = new Car("XYZ789", CarType.SUV);
        ParkingPermit permit = office.register(cust, car, LocalDate.now().plusYears(1));

        lot.enter(permit, office);
        Thread.sleep(1000); // simulate time passing
        ParkingCharge exitCharge = lot.exit(permit, office);

        assertNotNull(exitCharge);
        assertEquals(permit.getId(), exitCharge.getPermitId());
        assertTrue(exitCharge.getAmount().getDollars() > 0);
    }
}
