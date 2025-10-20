package parking;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

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

    public ParkingCharge enter(Car car, ParkingOffice office) {
        Money charge = entryFee;
        if (car.getType() == CarType.COMPACT) {
            charge = new Money(Math.round(charge.getDollars() * 0.8 * 100));
        }

        if (scanType == ScanType.ENTRY_ONLY) {
            ParkingCharge pc = new ParkingCharge(car.getPermitId(), lotId, charge);
            office.addCharge(pc);
            return pc;
        } else {
            entries.put(car.getPermitId(), Instant.now());
            return null;
        }
    }

    public ParkingCharge exit(Car car, ParkingOffice office) {
        if (scanType == ScanType.ENTRY_EXIT && entries.containsKey(car.getPermitId())) {
            Instant entry = entries.remove(car.getPermitId());
            long hours = java.time.Duration.between(entry, Instant.now()).toHours() + 1;
            long totalCents = Math.round(hours * entryFee.getDollars() * 100);
            if (car.getType() == CarType.COMPACT) totalCents = Math.round(totalCents * 0.8);
            Money charge = new Money(totalCents);
            ParkingCharge pc = new ParkingCharge(car.getPermitId(), lotId, charge);
            office.addCharge(pc);
            return pc;
        }
        return null;
    }

    @Override
    public String toString() {
        return "ParkingLot [LotID=" + lotId + ", ScanType=" + scanType + ", EntryFee=" + entryFee + "]";
    }
}
