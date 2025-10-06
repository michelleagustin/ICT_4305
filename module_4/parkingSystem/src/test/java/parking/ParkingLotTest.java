package parking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {

    private ParkingLot lot;
    private Car car1;
    private Car car2;
    private Address address;

    @BeforeEach
    void setUp() {
        address = new Address("123 Main St", "", "Springfield", "IL", "62701");
        lot = new ParkingLot("LotA", address, 2); // capacity 2

        car1 = new Car("PERMIT1", LocalDate.of(2025, 12, 31), "ABC123", CarType.COMPACT, "OWNER1");
        car2 = new Car("PERMIT2", LocalDate.of(2025, 6, 30), "XYZ789", CarType.SUV, "OWNER2");
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("LotA", lot.getLotId());
        assertEquals(address, lot.getAddress());
        assertEquals(2, lot.getCapacity());
    }

    @Test
    void testEntryAddsCar() {
        lot.entry(car1);
        assertTrue(lot.isParked(car1));
        Map<String, Instant> parked = lot.getParkedCars();
        assertEquals(1, parked.size());
        assertTrue(parked.containsKey(car1.getLicense()));
    }

    @Test
    void testEntryDuplicateCarThrows() {
        lot.entry(car1);
        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> lot.entry(car1));
        assertTrue(ex.getMessage().contains("already parked"));
    }

    @Test
    void testEntryFullLotThrows() {
        lot.entry(car1);
        lot.entry(car2);
        Car car3 = new Car("PERMIT3", LocalDate.of(2025, 1, 31), "LMN456", CarType.COMPACT, "OWNER3");
        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> lot.entry(car3));
        assertTrue(ex.getMessage().contains("full"));
    }

    @Test
    void testGetParkedCarsUnmodifiable() {
        lot.entry(car1);
        Map<String, Instant> parked = lot.getParkedCars();
        assertThrows(UnsupportedOperationException.class, () -> parked.put("FAKE", Instant.now()));
    }

    @Test
    void testToStringContainsInfo() {
        lot.entry(car1);
        String desc = lot.toString();
        assertTrue(desc.contains("LotA"));
        assertTrue(desc.contains("occupied=1"));
        assertTrue(desc.contains(address.toString()));
    }
}
