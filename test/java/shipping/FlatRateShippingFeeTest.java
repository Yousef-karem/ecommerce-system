package test.java.shipping;

import org.junit.jupiter.api.Test;
import src.main.java.shipping.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FlatRateShippingFeeTest {

    @Test
    void shouldReturn30WhenItemsExist() {
        ShippingBehavior item = new WeightBasedShippingBehavior("TV",0.5);
        ShippingFeeStrategy strategy = new FlatRateShippingFee();
        double fee = strategy.calculate(List.of(item));
        assertEquals(30.0, fee);
    }

    @Test
    void shouldReturnZeroWhenNoShippableItems() {
        ShippingFeeStrategy strategy = new FlatRateShippingFee();
        double fee = strategy.calculate(List.of());
        assertEquals(0.0, fee);
    }
}
