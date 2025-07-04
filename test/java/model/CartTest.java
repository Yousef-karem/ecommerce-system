package test.java.model;

import org.junit.jupiter.api.Test;
import src.main.java.model.BasicProduct;
import src.main.java.model.Cart;
import src.main.java.model.Product;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    @Test
    void shouldAddItemsAndCalculateSubtotal() {
        Cart cart = new Cart();
        Product p1 = new BasicProduct("ScratchCard", 50, 10);
        Product p2 = new BasicProduct("Token", 30, 10);

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
