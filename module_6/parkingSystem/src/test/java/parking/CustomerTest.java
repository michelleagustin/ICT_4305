package parking;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CustomerTest {

    @Test
    void testAddCar() {
        Address address = new Address("123 Main St", "", "City", "ST", "12345");
        Customer customer = new Customer("John Doe", address, "555-1234");
        Car car = new Car("ABC123", CarType.COMPACT);

        customer.addCar(car);
        assertEquals(1, customer.getCars().size());
        assertEquals(car, customer.getCars().get(0));
    }

    @Test
    void testToString() {
        Address address = new Address("123 Main St", "", "City", "ST", "12345");
        Customer customer = new Customer("John Doe", address, "555-1234");
        Car car = new Car("ABC123", CarType.COMPACT);
        customer.addCar(car);

        String output = customer.toString();
        assertTrue(output.contains("John Doe"));
        assertTrue(output.contains("ABC123"));
    }
}
