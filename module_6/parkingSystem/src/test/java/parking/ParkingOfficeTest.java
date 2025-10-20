package parking;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ParkingOfficeTest {

    @Test
    void testRegisterCustomerAndCar() {
        ParkingOffice office = new ParkingOffice("Office", new Address("Addr", "", "City", "ST", "12345"));
        Customer customer = office.register("Alice", new Address("Addr", "", "City", "ST", "12345"), "555-0000");

        Car car = office.register(customer, "AAA111", CarType.SUV);

        assertEquals(customer, office.getCustomer("Alice"));
        assertEquals(1, customer.getCars().size());
        assertEquals(car, customer.getCars().get(0));
    }

    @Test
    void testAddCharge() {
        ParkingOffice office = new ParkingOffice("Office", new Address("Addr", "", "City", "ST", "12345"));
        ParkingCharge charge = new ParkingCharge("PERMIT1", "LotA", new Money(500));

        Money money = office.addCharge(charge);
        assertEquals(5.0, money.getDollars());
        assertTrue(office.getCharges().contains(charge));
    }
}
