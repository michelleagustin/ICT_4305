package parking;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ParkingLot {
    public enum ScanType { ENTRY_ONLY, ENTRY_EXIT }

    private String lotId;
    private ScanType scanType;
    private Money entryFee;
    private Map<String, Instant> entries;

    public ParkingLot(String lotId, ScanType scanType, Money entryFee) {
        this.lotId = lotId;
        this.scanType = scanType;
        this.entryFee = entryFee;
        this.entries = new HashMap<>();
    }

    public ParkingCharge enter(ParkingPermit permit, ParkingOffice office) {
        Money charge = entryFee;
        if (permit.getCar().getType() == CarType.COMPACT) {
            charge = new Money(Math.round(charge.getDollars() * 0.8 * 100));
        }

        if (scanType == ScanType.ENTRY_ONLY) {
            ParkingCharge pc = new ParkingCharge(permit.getId(), lotId, charge);
            office.addCharge(pc);
            return pc;
        } else {
            entries.put(permit.getId(), Instant.now());
            return null;
        }
    }

    public ParkingCharge exit(ParkingPermit permit, ParkingOffice office) {
        if (scanType == ScanType.ENTRY_EXIT && entries.containsKey(permit.getId())) {
            Instant entry = entries.remove(permit.getId());
            long hours = java.time.Duration.between(entry, Instant.now()).toHours() + 1;
            long totalCents = Math.round(hours * entryFee.getDollars() * 100);
            if (permit.getCar().getType() == CarType.COMPACT) totalCents = Math.round(totalCents * 0.8);
            Money charge = new Money(totalCents);
            ParkingCharge pc = new ParkingCharge(permit.getId(), lotId, charge);
            office.addCharge(pc);
            return pc;
        }
        return null;
    }

    @Override
    public String toString() {
        return "ParkingLot [LotID=" + lotId + ", ScanType=" + scanType + ", EntryFee=" + entryFee + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParkingLot)) return false;
        ParkingLot lot = (ParkingLot) o;
        return lotId.equals(lot.lotId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotId);
    }
}
