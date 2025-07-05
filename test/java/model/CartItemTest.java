package test.java.model;

import org.junit.jupiter.api.Test;
import src.main.java.expiring.NeverExpires;
import src.main.java.model.CartItem;
import src.main.java.model.Product;
import src.main.java.shipping.NoShippingBehavior;

import static org.junit.jupiter.api.Assertions.*;

public class CartItemTest {

    @Test
    void shouldCreateCartItemAndCalculateTotalPrice() {
        Product product = new Product("ScratchCard", 50, 10, new NeverExpires(),new NoShippingBehavior());
        CartItem item = new CartItem(product, 2);
        assertEquals(100, item.getTotalPrice());
    }

    @Test
    void shouldThrowWhenQuantityExceedsStock() {
        Product product = new Product("ScratchCard", 50, 2, new NeverExpires(),new NoShippingBehavior());
        assertThrows(IllegalArgumentException.class, () -> new CartItem(product, 5));
    }
}
