package src.main.java.shipping;

public interface ShippingBehavior {
    public double getWeight();
    public String getName();
    boolean isShippable();
}