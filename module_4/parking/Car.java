package parking;

import java.time.LocalDate;
import java.util.Objects;

public class Car {
    private final String permit;
    private final LocalDate permitExpiration;
    private final String license;
    private final CarType type;
    private final String ownerId; // customerId

    public Car(String permit, LocalDate permitExpiration, String license, CarType type, String ownerId) {
        this.permit = permit;
        this.permitExpiration = permitExpiration;
        this.license = license;
        this.type = type;
        this.ownerId = ownerId;
    }

    public String getPermit(){ 
        return permit; 
    }
    public LocalDate getPermitExpiration(){ 
        return permitExpiration; 
    }
    public String getLicense(){ 
        return license; 
    }
    public CarType getType(){ 
        return type; 
    }
    public String getOwnerId(){ 
        return ownerId; 
    }

    @Override
    public String toString() {
        return String.format("Car{license=%s, type=%s, permit=%s, expires=%s, owner=%s}",
                license, type, permit, permitExpiration, ownerId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) 
            return false;
            
        Car car = (Car) o;
        return Objects.equals(license, car.license);
    }

    @Override
    public int hashCode() {
        return Objects.hash(license);
    }
}
