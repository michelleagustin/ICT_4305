package parking;

import java.util.ArrayList;
import java.util.List;

public class ParkingOffice {
    private String name;
    private Address address;
    private List<Customer> customers;
    private List<Car> cars;
    private List<ParkingLot> lots;
    private List<ParkingCharge> charges;

    public ParkingOffice(String name, Address address) {
        this.name = name;
        this.address = address;
        this.customers = new ArrayList<>();
        this.cars = new ArrayList<>();
        this.lots = new ArrayList<>();
        this.charges = new ArrayList<>();
    }

    public Customer register(String name, Address address, String phone) {
        Customer customer = new Customer(name, address, phone);
        customers.add(customer);
        return customer;
    }

    public Car register(Customer c, String license, CarType type) {
        Car car = new Car(license, type);
        cars.add(car);
        c.addCar(car);
        return car;
    }

    public Customer getCustomer(String name) {
        for (Customer c : customers) {
            if (c.getName().equalsIgnoreCase(name)) return c;
        }
        return null;
    }

    public Money addCharge(ParkingCharge charge) {
        charges.add(charge);
        return charge.getAmount();
    }

    public List<ParkingLot> getLots() { return lots; }
    public List<ParkingCharge> getCharges() { return charges; }
}
