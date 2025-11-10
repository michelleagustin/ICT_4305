package parking;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class AddressTest {

    @Test
    void testAddressCreation() {
        Address address = new Address("123 Main St", "Apt 2B", "Denver", "CO", "80204");

        assertNotNull(address);
    }

    @Test
    void testToStringWithApartment() {
        Address address = new Address("123 Main St", "Apt 2B", "Denver", "CO", "80204");
        String expected = "123 Main St, Apt 2B, Denver, CO 80204";
        assertEquals(expected, address.toString());
    }

    @Test
    void testToStringWithoutApartment() {
        Address address = new Address("456 Elm St", "", "Boulder", "CO", "80301");
        String expected = "456 Elm St, Boulder, CO 80301";
        assertEquals(expected, address.toString());
    }
}
