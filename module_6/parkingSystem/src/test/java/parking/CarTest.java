package parking;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CarTest {

    @Test
    void testCarCreation() {
        Car car = new Car("ABC123", CarType.COMPACT);
        assertEquals("ABC123", car.getLicense());
        assertEquals(CarType.COMPACT, car.getType());
        assertNotNull(car.getPermitId());
    }

    @Test
    void testToString() {
        Car car = new Car("XYZ789", CarType.SUV);
        String str = car.toString();
        assertTrue(str.contains("XYZ789"));
        assertTrue(str.contains("SUV"));
    }
}
