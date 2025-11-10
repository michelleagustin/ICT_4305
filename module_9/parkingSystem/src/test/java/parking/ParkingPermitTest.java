package parking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class ParkingPermitTest {

    @Test
    public void testPermitCreation() {
        Car car = new Car("XYZ789", CarType.SUV);
        ParkingPermit permit = new ParkingPermit(car, LocalDate.now().plusYears(1));

        assertNotNull(permit.getId());
        assertEquals(car, permit.getCar());
        assertTrue(permit.getExpirationDate().isAfter(LocalDate.now()));
    }

    @Test
    public void testEqualsAndHashCode() {
        Car car = new Car("XYZ789", CarType.SUV);
        ParkingPermit p1 = new ParkingPermit(car, LocalDate.now());
        ParkingPermit p2 = new ParkingPermit(car, LocalDate.now());

        assertNotEquals(p1, p2); // different UUIDs
        assertNotEquals(p1.hashCode(), p2.hashCode());
    }
}
