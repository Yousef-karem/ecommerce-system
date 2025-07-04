package test.java.model;

import org.junit.jupiter.api.Test;
import src.main.java.model.Customer;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    @Test
    void shouldDeductBalanceCorrectly() {
        Customer customer = new Customer("Ahmed", 100);
        customer.deductBalance(40);
        assertEquals(60, customer.getBalance());
    }

    @Test
    void shouldThrowWhenBalanceIsInsufficient() {
        Customer customer = new Customer("Hassan", 30);
        assertThrows(IllegalArgumentException.class, () -> customer.deductBalance(50));
    }
}
