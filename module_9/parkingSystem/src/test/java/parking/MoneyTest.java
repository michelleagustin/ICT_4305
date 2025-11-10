package parking;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    void testGetDollars() {
        Money money = new Money(250); // $2.50
        assertEquals(2.5, money.getDollars());
    }

    @Test
    void testAdd() {
        Money m1 = new Money(100);
        Money m2 = new Money(200);
        Money sum = m1.add(m2);
        assertEquals(3.0, sum.getDollars());
    }

    @Test
    void testToString() {
        Money money = new Money(150);
        assertEquals("$1.50", money.toString());
    }
}
