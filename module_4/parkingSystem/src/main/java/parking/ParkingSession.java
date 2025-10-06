package parking;

import java.time.LocalDateTime;

/**
 * Tracks an active parking session (entry time and car).
 */
public class ParkingSession {
    private final Car car;
    private final LocalDateTime entryTime;

    public ParkingSession(Car car, LocalDateTime entryTime) {
        this.car = car;
        this.entryTime = entryTime;
    }

    public Car getCar(){ 
        return car; 
    }

    public LocalDateTime getEntryTime(){ 
        return entryTime; 
    }
}
