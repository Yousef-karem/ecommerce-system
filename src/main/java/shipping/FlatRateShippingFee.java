package src.main.java.shipping;

import java.util.List;

public class FlatRateShippingFee implements ShippingFeeStrategy {
    private static final double FLAT_RATE = 30.0;

    @Override
    public double calculate(List<ShippingBehavior> items) {
        return items.isEmpty() ? 0.0 : FLAT_RATE;
    }
}
