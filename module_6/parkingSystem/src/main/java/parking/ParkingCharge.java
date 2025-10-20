package parking;

import java.time.Instant;

public class ParkingCharge {
    private String permitId;
    private String lotId;
    private Instant incurred;
    private Money amount;

    public ParkingCharge(String permitId, String lotId, Money amount) {
        this.permitId = permitId;
        this.lotId = lotId;
        this.incurred = Instant.now();
        this.amount = amount;
    }

    public Money getAmount() { return amount; }
    public String getPermitId() { return permitId; }

    @Override
    public String toString() {
        return "ParkingCharge [PermitID=" + permitId + ", LotID=" + lotId +
               ", Incurred=" + incurred + ", Amount=" + amount + "]";
    }
}
