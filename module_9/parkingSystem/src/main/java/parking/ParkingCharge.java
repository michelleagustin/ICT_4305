package parking;

import java.time.Instant;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParkingCharge)) return false;
        ParkingCharge that = (ParkingCharge) o;
        return Objects.equals(permitId, that.permitId) &&
               Objects.equals(lotId, that.lotId) &&
               Objects.equals(incurred, that.incurred);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permitId, lotId, incurred);
    }
}
