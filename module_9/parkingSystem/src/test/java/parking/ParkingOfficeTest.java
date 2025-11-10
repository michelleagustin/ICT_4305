package parking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

public class ParkingOfficeTest {

    @Test
    public void testRegisterCustomerAndPermit() {
        ParkingOffice office = new ParkingOffice("Office", new Address("Addr", "", "City", "ST", "00000"));
        Customer cust = office.register("Alice", new Address("Addr", "", "City", "ST", "00000"), "555-1111");
        Car car = new Car("ABC123", CarType.COMPACT);
        ParkingPermit permit = office.register(cust, car, LocalDate.now().plusYears(1));

        assertEquals(1, office.getPermitIds().size());
        assertEquals(permit.getId(), office.getPermitIds(cust).get(0));
        assertEquals(cust, office.findCustomerByPermit(permit.getId()));
    }

    @Test
    public void testGetCustomerIds() {
        ParkingOffice office = new ParkingOffice("Office", new Address("Addr", "", "City", "ST", "00000"));
        Customer c1 = office.register("Alice", new Address("Addr", "", "City", "ST", "00000"), "555-1111");
        Customer c2 = office.register("Bob", new Address("Addr", "", "City", "ST", "00000"), "555-2222");

        List<String> ids = office.getCustomerIds();
        assertEquals(2, ids.size());
        assertTrue(ids.contains(c1.getCustomerId()));
        assertTrue(ids.contains(c2.getCustomerId()));
    }
}
