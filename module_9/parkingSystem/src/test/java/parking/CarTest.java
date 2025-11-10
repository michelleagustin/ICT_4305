package parking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarTest {

    @Test
    public void testCarCreation() {
        Car car = new Car("ABC123", CarType.COMPACT);

        assertEquals("ABC123", car.getLicense());
        assertEquals(CarType.COMPACT, car.getType());
        assertNotNull(car.toString()); // toString returns a string
        assertTrue(car.toString().contains("ABC123"));
    }

    @Test
    public void testEqualsAndHashCode() {
        Car car1 = new Car("XYZ789", CarType.SUV);
        Car car2 = new Car("XYZ789", CarType.SUV);
        Car car3 = new Car("ABC123", CarType.COMPACT);

        assertEquals(car1, car2); // same license, should be equal
        assertEquals(car1.hashCode(), car2.hashCode());

        assertNotEquals(car1, car3); // different license
        assertNotEquals(car1.hashCode(), car3.hashCode());
    }

    @Test
    public void testDifferentCarTypes() {
        Car compact = new Car("COM123", CarType.COMPACT);
        Car suv = new Car("SUV123", CarType.SUV);

        assertEquals(CarType.COMPACT, compact.getType());
        assertEquals(CarType.SUV, suv.getType());
    }
}