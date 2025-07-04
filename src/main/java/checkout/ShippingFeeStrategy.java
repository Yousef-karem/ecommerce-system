package src.main.java.checkout;

import src.main.java.shipping.Shippable;
import java.util.List;

public interface ShippingFeeStrategy {
    double calculate(List<Shippable> items);
}
