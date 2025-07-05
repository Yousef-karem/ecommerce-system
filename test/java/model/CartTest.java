package test.java.model;

import org.junit.jupiter.api.Test;
import src.main.java.expiring.NeverExpires;
import src.main.java.model.Cart;
import src.main.java.model.Product;
import src.main.java.shipping.NoShippingBehavior;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    @Test
    void shouldAddItemsAndCalculateSubtotal() {
        Cart cart = new Cart();
        Product p1 = new Product("ScratchCard", 50, 10, new NeverExpires(),new NoShippingBehavior());
        Product p2 = new Product("Token", 30, 10, new NeverExpires(),new NoShippingBehavior());

        cart.add(p1, 2);
        cart.add(p2, 1);

        assertEquals(130, cart.getSubtotal());
        assertFalse(cart.isEmpty());
    }

    @Test
    void shouldBeEmptyWhenNoItemsAdded() {
        Cart cart = new Cart();
        assertTrue(cart.isEmpty());
    }
}
