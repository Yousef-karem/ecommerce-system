package src.main.java.shipping;

import java.util.List;

public interface ShippingFeeStrategy {
    double calculate(List<Shippable> items);
}
