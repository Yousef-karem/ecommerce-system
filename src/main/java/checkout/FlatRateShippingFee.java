package src.main.java.checkout;

import src.main.java.shipping.Shippable;
import java.util.List;

public class FlatRateShippingFee implements ShippingFeeStrategy {
    private static final double FLAT_RATE = 30.0;

    @Override
    public double calculate(List<Shippable> items) {
        return items.isEmpty() ? 0.0 : FLAT_RATE;
    }
}
