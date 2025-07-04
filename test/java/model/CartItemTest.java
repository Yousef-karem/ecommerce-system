package test.java.model;

import org.junit.jupiter.api.Test;
import src.main.java.model.BasicProduct;
import src.main.java.model.CartItem;
import src.main.java.model.Product;

import static org.junit.jupiter.api.Assertions.*;

public class CartItemTest {

    @Test
    void shouldCreateCartItemAndCalculateTotalPrice() {
        Product product = new BasicProduct("ScratchCard", 50, 10);
        CartItem item = new CartItem(product, 2);
        assertEquals(100, item.getTotalPrice());
    }

    @Test
    void shouldThrowWhenQuantityExceedsStock() {
        Product product = new BasicProduct("ScratchCard", 50, 2);
        assertThrows(IllegalArgumentException.class, () -> new CartItem(product, 5));
    }
}
