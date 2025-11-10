package parking;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer {
    private String customerId; // unique ID
    private String name;
    private String phone;
    private Address address;
    private List<Car> cars;
    private List<ParkingPermit> permits;

    public Customer(String name, Address address, String phone) {
        this.customerId = java.util.UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.cars = new ArrayList<>();
        this.permits = new ArrayList<>();
    }

    public void addCar(Car car) { cars.add(car); }
    public void addPermit(ParkingPermit permit) { permits.add(permit); }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public List<Car> getCars() { return cars; }
    public List<ParkingPermit> getPermits() { return permits; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer: ").append(name).append("\n");
        sb.append("Phone: ").append(phone).append("\n");
        sb.append("Address: ").append(address).append("\n");
        sb.append("Cars:\n");
        for (Car car : cars) sb.append("  ").append(car).append("\n");
        sb.append("Permits:\n");
        for (ParkingPermit p : permits) sb.append("  ").append(p).append("\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer c = (Customer) o;
        return customerId.equals(c.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }
}
