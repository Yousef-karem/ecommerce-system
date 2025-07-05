package src.main.java.shipping;

public class WeightBasedShippingBehavior implements ShippingBehavior {
    private double weight;
    String name;
    public WeightBasedShippingBehavior(String name , double weight)
    {
        this.weight=weight;
        this.name=name;
    }
    @Override
    public boolean isShippable() {
        return true;
    }

    public double getWeight()
    {
        return weight;
    }

    @Override
    public String getName() {
        return name;
    }

}
