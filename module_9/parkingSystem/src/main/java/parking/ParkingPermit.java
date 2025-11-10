package parking;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class ParkingPermit {
    private String id;
    private Car car;
    private LocalDate expirationDate;

    public ParkingPermit(Car car, LocalDate expirationDate) {
        this.id = UUID.randomUUID().toString();
        this.car = car;
        this.expirationDate = expirationDate;
    }

    public String getId() { return id; }
    public Car getCar() { return car; }
    public LocalDate getExpirationDate() { return expirationDate; }

    @Override
    public String toString() {
        return "Permit [id=" + id + ", car=" + car + ", expires=" + expirationDate + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParkingPermit)) return false;
        ParkingPermit p = (ParkingPermit) o;
        return id.equals(p.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
