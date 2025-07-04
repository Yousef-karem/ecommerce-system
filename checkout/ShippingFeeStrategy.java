package checkout;

import shipping.Shippable;
import java.util.List;

public interface ShippingFeeStrategy {
    double calculate(List<Shippable> items);
}
