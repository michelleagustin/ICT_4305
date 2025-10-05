package parking;

import java.time.Instant;
import java.util.*;

/**
 * Simple ParkingLot that tracks parked cars (by license) and enforces capacity.
 * - entry(Car) records a timestamp and stores the car if capacity allows.
 * - toString() returns a compact summary.
 *
 * Note: assignment required only `entry(Car): void` and basic attributes — this class adds a minimal timestamp
 * map so the lot can be extended later to compute durations or charges.
 */
public class ParkingLot {
    private final String lotId;
    private final Address address;
    private final int capacity;

    // tracks parked cars by license -> entry instant
    private final Map<String, Instant> parkedCars = new LinkedHashMap<>();

    public ParkingLot(String lotId, Address address, int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("capacity must be >= 0");
        this.lotId = lotId;
        this.address = address;
        this.capacity = capacity;
    }

    public String getLotId() { return lotId; }
    public Address getAddress() { return address; }
    public int getCapacity() { return capacity; }

    /**
     * Simulate an entry scan: add the car if space available and not already parked here.
     * Records the entry timestamp (Instant.now()) so you can extend to compute durations later.
     * Throws IllegalStateException if capacity full or car already parked.
     */
    public void entry(Car car) {
        if (parkedCars.containsKey(car.getLicense())) {
            throw new IllegalStateException("Car already parked in this lot: " + car.getLicense());
        }
        if (parkedCars.size() >= capacity) {
            throw new IllegalStateException("Parking lot is full: " + lotId);
        }
        parkedCars.put(car.getLicense(), Instant.now());
    }

    /**
     * Optional helper to check if a car is currently parked here.
     */
    public boolean isParked(Car car) {
        return parkedCars.containsKey(car.getLicense());
    }

    /**
     * Returns an unmodifiable view of currently parked licenses and their entry time.
     */
    public Map<String, Instant> getParkedCars() {
        return Collections.unmodifiableMap(parkedCars);
    }

    @Override
    public String toString() {
        return String.format("ParkingLot{id=%s, address=%s, capacity=%d, occupied=%d}",
                lotId, address, capacity, parkedCars.size());
    }
}
