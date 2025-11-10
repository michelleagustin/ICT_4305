package parking;

public class Money {
    private final long cents;

    public Money(long cents) {
        this.cents = cents;
    }

    public long getCents() { return cents; }
    public double getDollars() { return cents / 100.0; }

    public Money add(Money other) {
        return new Money(this.cents + other.cents);
    }

    public Money multiply(double factor) {
        long result = Math.round(this.cents * factor);
        return new Money(result);
    }

    @Override
    public String toString() {
        return String.format("$%.2f", getDollars());
    }
}
