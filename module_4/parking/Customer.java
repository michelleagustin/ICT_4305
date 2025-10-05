package parking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Customer holds id, contact info and list of registered cars (permits).
 */
public class Customer {
    private final String customerId;
    private final String name;
    private final Address address;
    private final String phoneNumber;

    private final List<Car> cars = new ArrayList<>();

    public Customer(String customerId, String name, Address address, String phoneNumber) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getCustomerId(){ 
        return customerId; 
    }
    public String getName(){ 
        return name; 
    }
    public Address getAddress(){ 
        return address; 
    }
    public String getPhoneNumber(){ 
        return phoneNumber;
    }

    /**
     * Register a car and return the created Car object (assigns a permit id and 1-year expiration).
     */
    public Car register(String license, CarType type) {
        String permitId = UUID.randomUUID().toString();
        LocalDate expiration = LocalDate.now().plusYears(1);
        Car car = new Car(permitId, expiration, license, type, this.customerId);
        cars.add(car);
        return car;
    }

    public List<Car> getCars() {
        return new ArrayList<>(cars); // defensive copy
    }

    @Override
    public String toString() {
        return String.format("Customer{id=%s, name=%s, phone=%s}", customerId, name, phoneNumber);
    }
}
