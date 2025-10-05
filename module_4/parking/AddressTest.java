package parking;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AddressTest {

    @Test
    void testGetAddressInfoFullAddress() {
        Address address = new Address("123 Main St", "Apt 4B", "Denver", "CO", "80210");
        String result = address.getAddressInfo();

        assertTrue(result.contains("123 Main St"));
        assertTrue(result.contains("Apt 4B"));
        assertTrue(result.contains("Denver"));
        assertTrue(result.contains("CO"));
        assertTrue(result.contains("80210"));
        assertEquals("123 Main St, Apt 4B, Denver, CO 80210", result);
    }

    @Test
    void testGetAddressInfoWithoutStreet2() {
        Address address = new Address("456 Elm St", "", "Boulder", "CO", "80301");
        String result = address.getAddressInfo();

        assertEquals("456 Elm St, Boulder, CO 80301", result);
        assertFalse(result.contains(",,"));
    }

    @Test
    void testGetAddressInfoWithNullStreet2() {
        Address address = new Address("789 Oak St", null, "Aurora", "CO", "80012");
        String result = address.getAddressInfo();

        assertEquals("789 Oak St, Aurora, CO 80012", result);
    }

    @Test
    void testToStringDelegatesToGetAddressInfo() {
        Address address = new Address("101 Pine St", "", "Lakewood", "CO", "80226");
        assertEquals(address.getAddressInfo(), address.toString());
    }
}
