package parking;

public class Money {
    private long cents;

    public Money(long cents) {
        this.cents = cents;
    }

    public double getDollars() {
        return cents / 100.0;
    }

    public Money add(Money other) {
        return new Money(this.cents + other.cents);
    }

    @Override
    public String toString() {
        return "$" + getDollars();
    }
}
