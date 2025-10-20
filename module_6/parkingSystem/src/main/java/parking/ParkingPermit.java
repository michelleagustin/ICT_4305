package parking;

import java.time.LocalDate;

public class ParkingPermit {
    private String id;
    private Car car;
    private LocalDate expirationDate;

    public ParkingPermit(String id, Car car, LocalDate expirationDate) {
        this.id = id;
        this.car = car;
        this.expirationDate = expirationDate;
    }

    public String getId() {
        return id;
    }

    public Car getCar() {
        return car;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    @Override
    public String toString() {
        return "Permit [id=" + id + ", car=" + car + ", expires=" + expirationDate + "]";
    }
}
