package parking;

import java.time.LocalDateTime;

/**
 * Simple record of a parking charge in a customer's account.
 */
public class ParkingTransaction {
    private final String lotId;
    private final String carLicense;
    private final double amount;
    private final LocalDateTime timestamp;
    private final String description;

    public ParkingTransaction(String lotId, String carLicense, double amount, LocalDateTime timestamp, String description) {
        this.lotId = lotId;
        this.carLicense = carLicense;
        this.amount = amount;
        this.timestamp = timestamp;
        this.description = description;
    }

    public String getLotId(){ 
        return lotId; 
    }
    public String getCarLicense(){ 
        return carLicense; 
    }
    public double getAmount(){ 
        return amount; 
    }
    public LocalDateTime getTimestamp(){ 
        return timestamp; 
    }
    public String getDescription(){ 
        return description; 
    }

    @Override
    public String toString() {
        return String.format("ParkingTransaction{lot=%s, car=%s, amount=%.2f, when=%s, desc=%s}",
                lotId, carLicense, amount, timestamp, description);
    }
}
