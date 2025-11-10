package parking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class CustomerTest {

    @Test
    public void testAddPermit() {
        Address addr = new Address("123 Main St", "", "Denver", "CO", "80210");
        Customer customer = new Customer("Alice", addr, "555-1111");
        Car car = new Car("ABC123", CarType.COMPACT);
        ParkingPermit permit = new ParkingPermit(car, null);

        customer.addPermit(permit);

        List<ParkingPermit> permits = customer.getPermits();
        assertEquals(1, permits.size());
        assertEquals(permit, permits.get(0));
    }

    @Test
    public void testEqualsAndHashCode() {
        Address addr = new Address("123 Main St", "", "Denver", "CO", "80210");
        Customer customer1 = new Customer("Bob", addr, "555-2222");
        Customer customer2 = new Customer("Bob", addr, "555-2222");

        assertNotEquals(customer1, customer2); // Different IDs
        assertNotEquals(customer1.hashCode(), customer2.hashCode());
    }
}
