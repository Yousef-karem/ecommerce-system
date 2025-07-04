package test.java.checkout;

import src.main.java.checkout.CheckoutService;
import src.main.java.model.*;
import org.junit.jupiter.api.Test;
import src.main.java.shipping.FlatRateShippingFee;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CheckoutServiceTest {

    @Test
    void shouldSuccessfullyCheckoutWithValidItems() {
        Product cheese = new ShippableExpirableProduct("Cheese", 100, 5, LocalDate.now().plusDays(2), 0.2);
        Product card = new BasicProduct("ScratchCard", 50, 10);
        Customer customer = new Customer("John", 1000);
        Cart cart = new Cart();

        cart.add(cheese, 2);
        cart.add(card, 1);

        CheckoutService service = new CheckoutService(new FlatRateShippingFee());

        assertDoesNotThrow(() -> service.checkout(customer, cart));
        assertEquals(1000 - (200 + 50 + 30), customer.getBalance());
    }

    @Test
    void shouldThrowWhenProductIsExpired() {
        Product expired = new ShippableExpirableProduct("Milk", 80, 3, LocalDate.now().minusDays(1), 0.5);
        Customer customer = new Customer("Tom", 500);
        Cart cart = new Cart();
        cart.add(expired, 1);

        CheckoutService service = new CheckoutService(new FlatRateShippingFee());

        Exception exception = assertThrows(IllegalStateException.class, () -> service.checkout(customer, cart));
        assertEquals("Product expired: Milk", exception.getMessage());
    }

    @Test
    void shouldThrowWhenCustomerBalanceIsInsufficient() {
        Product tv = new ShippableProduct("TV", 700, 2, 10.0);
        Customer customer = new Customer("LowBalance", 100);
        Cart cart = new Cart();
        cart.add(tv, 1);

        CheckoutService service = new CheckoutService(new FlatRateShippingFee());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.checkout(customer, cart));
        assertEquals("Insufficient balance.", exception.getMessage());
    }

    @Test
    void shouldThrowWhenCartIsEmpty() {
        Customer customer = new Customer("EmptyCartUser", 500);
        Cart cart = new Cart();

        CheckoutService service = new CheckoutService(new FlatRateShippingFee());

        Exception exception = assertThrows(IllegalStateException.class, () -> service.checkout(customer, cart));
        assertEquals("Cart is empty.", exception.getMessage());
    }

    @Test
    void shouldThrowWhenQuantityExceedsAvailableStock() {
        Product p = new BasicProduct("ScratchCard", 50, 2);
        Customer customer = new Customer("OverLimitUser", 1000);
        Cart cart = new Cart();
        cart.add(p, 2);

        // Reduce actual stock to 1
        p.reduceQuantity(1);

        CheckoutService service = new CheckoutService(new FlatRateShippingFee());

        Exception exception = assertThrows(IllegalStateException.class, () -> service.checkout(customer, cart));
        assertEquals("Not enough stock for: ScratchCard", exception.getMessage());
    }
}
