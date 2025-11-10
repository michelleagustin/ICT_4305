package parking;

import java.util.Objects;
import java.util.UUID;

public class Car {
    private String license;
    private CarType type;
    private String permitId;

    public Car(String license, CarType type) {
        this.license = license;
        this.type = type;
        this.permitId = UUID.randomUUID().toString();
    }

    public String getLicense() { return license; }
    public CarType getType() { return type; }
    public String getPermitId() { return permitId; }

    @Override
    public String toString() {
        return "Car [License=" + license + ", Type=" + type + ", PermitID=" + permitId + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return license.equals(car.license);
    }

    @Override
    public int hashCode() {
        return Objects.hash(license);
    }
}
