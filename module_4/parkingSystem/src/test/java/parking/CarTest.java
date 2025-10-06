package parking;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    void constructorAndGettersWork() {
        LocalDate exp = LocalDate.now().plusYears(1);
        Car car = new Car("PERMIT-1", exp, "ABC123", CarType.COMPACT, "CUST1");

        assertEquals("PERMIT-1", car.getPermit());
        assertEquals(exp, car.getPermitExpiration());
        assertEquals("ABC123", car.getLicense());
        assertEquals(CarType.COMPACT, car.getType());
        assertEquals("CUST1", car.getOwnerId());
    }

    @Test
    void toStringContainsKeyFields() {
        LocalDate exp = LocalDate.of(2026, 1, 1);
        Car car = new Car("P-2", exp, "XYZ999", CarType.SUV, "C002");

        String s = car.toString();
        assertTrue(s.contains("XYZ999"));
        assertTrue(s.contains("SUV"));
        assertTrue(s.contains("P-2"));
        assertTrue(s.contains("2026"));
        assertTrue(s.contains("C002"));
    }

    @Test
    void equalsAndHashCodeBasedOnLicense() {
        LocalDate exp1 = LocalDate.now().plusMonths(6);
        LocalDate exp2 = LocalDate.now().plusYears(2);

        // same license, different other fields -> should be equal
        Car a = new Car("PA", exp1, "SAME1", CarType.COMPACT, "OwnerA");
        Car b = new Car("PB", exp2, "SAME1", CarType.SUV, "OwnerB");

        assertEquals(a, b, "Cars with same license should be equal");
        assertEquals(a.hashCode(), b.hashCode(), "Hash codes should match for equal objects");
    }

    @Test
    void notEqualWhenLicenseDiffers() {
        LocalDate exp = LocalDate.now().plusYears(1);
        Car a = new Car("P1", exp, "L1", CarType.COMPACT, "O1");
        Car b = new Car("P2", exp, "L2", CarType.COMPACT, "O1");

        assertNotEquals(a, b);
    }

    @Test
    void equalsNullAndDifferentClass() {
        LocalDate exp = LocalDate.now().plusYears(1);
        Car car = new Car("PX", exp, "L100", CarType.SUV, "O100");

        assertNotEquals(null, car);
        assertNotEquals(car, "some string");
    }

    @Test
    void hashCodeIsStableAcrossCalls() {
        LocalDate exp = LocalDate.now().plusYears(1);
        Car car = new Car("PZ", exp, "LZ", CarType.COMPACT, "OZ");

        int h1 = car.hashCode();
        int h2 = car.hashCode();
        assertEquals(h1, h2);
    }
}
