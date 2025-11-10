package parking;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ParkingChargeTest {

    @Test
    void testParkingChargeCreation() {
        ParkingCharge charge = new ParkingCharge("PERMIT123", "LotA", new Money(500));
        assertEquals("PERMIT123", charge.getPermitId());
        assertEquals(new Money(500).getDollars(), charge.getAmount().getDollars());
        assertNotNull(charge.toString());
    }
}
