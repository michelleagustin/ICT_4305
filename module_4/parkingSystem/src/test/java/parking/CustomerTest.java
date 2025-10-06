package parking;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerTest {

    private Customer customer;
    private Address address;

    @BeforeEach
    void setUp() {
        address = new Address("123 Main St", "", "Denver", "CO", "80210");
        customer = new Customer("C001", "Alice Johnson", address, "555-1234");
    }

    @Test
    void testConstructorInitializesFields() {
        assertEquals("C001", customer.getCustomerId());
        assertEquals("Alice Johnson", customer.getName());
        assertEquals("555-1234", customer.getPhoneNumber());
        assertEquals(address, customer.getAddress());
        assertTrue(customer.getCars().isEmpty());
    }

    @Test
    void testRegisterCreatesCarAndAddsToList() {
        Car car = customer.register("XYZ123", CarType.COMPACT);
        List<Car> cars = customer.getCars();

        assertEquals(1, cars.size());
        assertEquals(car, cars.get(0));
        assertEquals("XYZ123", car.getLicense());
        assertEquals(CarType.COMPACT, car.getType());
        assertEquals("C001", car.getOwnerId());
    }

    @Test
    void testRegisterAssignsPermitAndExpiration() {
        Car car = customer.register("ABC999", CarType.SUV);
        assertNotNull(car.getPermit());
        assertTrue(car.getPermit().length() > 0);
        assertEquals(LocalDate.now().plusYears(1), car.getPermitExpiration());
    }

    @Test
    void testGetCarsReturnsDefensiveCopy() {
        customer.register("XYZ123", CarType.COMPACT);
        List<Car> cars = customer.getCars();
        cars.clear(); // modify external list
        assertEquals(1, customer.getCars().size(), "Internal list should not be affected");
    }

    @Test
    void testToStringIncludesNameAndId() {
        String result = customer.toString();
        assertTrue(result.contains("C001"));
        assertTrue(result.contains("Alice Johnson"));
    }
}
