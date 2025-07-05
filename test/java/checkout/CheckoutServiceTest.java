package test.java.checkout;

import src.main.java.checkout.CheckoutService;
import src.main.java.expiring.ExpiresOnDate;
import src.main.java.expiring.NeverExpires;
import src.main.java.model.*;
import org.junit.jupiter.api.Test;
import src.main.java.shipping.FlatRateShippingFee;
import src.main.java.shipping.NoShippingBehavior;
import src.main.java.shipping.WeightBasedShippingBehavior;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CheckoutServiceTest {

    @Test
    void shouldSuccessfullyCheckoutWithValidItems() {
        Product cheese = new Product("Cheese", 100, 5
                ,new ExpiresOnDate(LocalDate.now().plusDays(2))
                ,new WeightBasedShippingBehavior("Cheese",0.2));
        Product card = new Product("ScratchCard", 50, 10
                ,new NeverExpires()
                ,new NoShippingBehavior());
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
        Product expired = new Product("Milk", 80, 3
                , new ExpiresOnDate(LocalDate.now().minusDays(1))
                ,new WeightBasedShippingBehavior("Milk",0.5));
        Customer customer = new Customer("Tom", 500);
        Cart cart = new Cart();
        cart.add(expired, 1);

        CheckoutService service = new CheckoutService(new FlatRateShippingFee());

        Exception exception = assertThrows(IllegalStateException.class, () -> service.checkout(customer, cart));
        assertEquals("Product expired: Milk", exception.getMessage());
    }

    @Test
    void shouldThrowWhenCustomerBalanceIsInsufficient() {
        Product tv = new Product("TV", 700, 2
                ,new NeverExpires()
                ,new WeightBasedShippingBehavior( "TV",10.0));
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
        Product p = new Product("ScratchCard", 50, 2,
                new NeverExpires(),
                new NoShippingBehavior());
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
