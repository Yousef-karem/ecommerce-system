package src.main.java.shipping;

public class NoShippingBehavior implements ShippingBehavior{
    public boolean isShippable()
    {
        return false;
    }

    @Override
    public double getWeight() {
        return 0;
    }
    @Override
    public String getName()
    {
        return "";
    }
}
