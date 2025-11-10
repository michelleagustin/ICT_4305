package parking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;

public class ParkingOffice {
    private String name;
    private Address address;
    private List<Customer> customers;
    private List<ParkingPermit> permits;
    private List<ParkingLot> lots;
    private List<ParkingCharge> charges;

    public ParkingOffice(String name, Address address) {
        this.name = name;
        this.address = address;
        this.customers = new ArrayList<>();
        this.permits = new ArrayList<>();
        this.lots = new ArrayList<>();
        this.charges = new ArrayList<>();
    }

    // ----------------------------
    // Register a new customer
    // ----------------------------
    public Customer register(String name, Address address, String phone) {
        Customer customer = new Customer(name, address, phone);
        customers.add(customer);
        return customer;
    }

    // ----------------------------
    // Register a new permit for a car
    // ----------------------------
    public ParkingPermit register(Customer customer, Car car, LocalDate expiration) {
        ParkingPermit permit = new ParkingPermit(car, expiration);
        permits.add(permit);
        customer.addPermit(permit);
        return permit;
    }

    // ----------------------------
    // Retrieve customer by name
    // ----------------------------
    public Customer getCustomer(String name) {
        return customers.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }

    // ----------------------------
    // Permit validation
    // ----------------------------
    public boolean validatePermit(String permitId, String lotId) {
        return findCustomerByPermit(permitId) != null;
    }

    public Customer findCustomerByPermit(String permitId) {
        for (Customer c : customers) {
            for (ParkingPermit p : c.getPermits()) {
                if (permitId.equals(p.getId())) return c;
            }
        }
        return null;
    }

    // ----------------------------
    // Add parking charge
    // ----------------------------
    public Money addCharge(ParkingCharge charge) {
        charges.add(charge);
        return charge.getAmount();
    }

    // ----------------------------
    // Accessor methods
    // ----------------------------
    public List<ParkingLot> getLots() { return lots; }
    public List<ParkingCharge> getCharges() { return charges; }

    public void addLot(ParkingLot lot) {
        if (lot != null) lots.add(lot);
    }

    public List<String> getCustomerIds() {
        return customers.stream().map(Customer::getCustomerId).collect(Collectors.toList());
    }

    public List<String> getPermitIds() {
        return permits.stream().map(ParkingPermit::getId).collect(Collectors.toList());
    }

    public List<String> getPermitIds(Customer customer) {
        if (customer == null) return new ArrayList<>();
        return customer.getPermits().stream().map(ParkingPermit::getId).collect(Collectors.toList());
    }
}
