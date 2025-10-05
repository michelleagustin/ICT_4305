package parking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the ParkingLot class.
 */
public class ParkingLotTest {

    private ParkingLot lot;
    private Car testCar;

    @BeforeEach
    void setUp() {
        Address address = TestDataFactory.createAddress();
        lot = new ParkingLot("LOT-001", address, 2);
        testCar = TestDataFactory.createDefaultCar();
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("LOT-001", lot.getLotId());
        assertEquals(2, lot.getCapacity());
        assertNotNull(lot.getAddress());
    }

    @Test
    void testEntryAddsCarSuccessfully() {
        lot.entry(testCar);
        assertTrue(lot.isParked(testCar));

        Map<String, Instant> parkedCars = lot.getParkedCars();
        assertEquals(1, parkedCars.size());
        assertTrue(parkedCars.containsKey(testCar.getLicense()));
    }

    @Test
    void testDuplicateEntryThrowsException() {
        lot.entry(testCar);
        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> lot.entry(testCar));
        assertTrue(ex.getMessage().contains("already parked"));
    }

    @Test
    void testCapacityFullThrowsException() {
        Car car1 = TestDataFactory.createCar("CAR1", CarType.COMPACT, "CUST1");
        Car car2 = TestDataFactory.createCar("CAR2", CarType.SUV, "CUST2");
        Car car3 = TestDataFactory.createCar("CAR3", CarType.COMPACT, "CUST3");

        lot.entry(car1);
        lot.entry(car2);

        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> lot.entry(car3));
        assertTrue(ex.getMessage().contains("full"));
    }

    @Test
    void testGetParkedCarsIsUnmodifiable() {
        lot.entry(testCar);
        Map<String, Instant> parkedCars = lot.getParkedCars();
        assertThrows(UnsupportedOperationException.class, () -> parkedCars.clear());
    }

    @Test
    void testToStringIncludesLotIdAndCapacity() {
        String info = lot.toString();
        assertTrue(info.contains("LOT-001"));
        assertTrue(info.contains("capacity=2"));
    }

    @Test
    void testInvalidCapacityThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new ParkingLot("BAD", TestDataFactory.createAddress(), -1));
    }
}
