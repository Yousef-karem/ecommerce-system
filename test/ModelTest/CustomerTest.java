package src.main.java.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    @Test
    void shouldDeductBalanceCorrectly() {
        Customer customer = new Customer("Alice", 100);
        customer.deductBalance(40);
        assertEquals(60, customer.getBalance());
    }

    @Test
    void shouldThrowWhenBalanceIsInsufficient() {
        Customer customer = new Customer("Bob", 30);
        assertThrows(IllegalArgumentException.class, () -> customer.deductBalance(50));
    }
}
