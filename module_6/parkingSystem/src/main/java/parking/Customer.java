package parking;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private String phone;
    private Address address;
    private List<Car> cars;

    public Customer(String name, Address address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.cars = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public String getName() {
        return name;
    }

    public List<Car> getCars() {
        return cars;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer: ").append(name).append("\n");
        sb.append("Phone: ").append(phone).append("\n");
        sb.append("Address: ").append(address).append("\n");
        sb.append("Cars:\n");
        for (Car car : cars) {
            sb.append("  ").append(car).append("\n");
        }
        return sb.toString();
    }
}
