package src.main.java.model;

import src.main.java.expiring.ExpirationBehavior;
import src.main.java.shipping.ShippingBehavior;
import src.main.java.shipping.WeightBasedShippingBehavior;

public class Product {
    private String name;
    private double price;
    private int quantity;

    private ExpirationBehavior expirationBehavior;
    private ShippingBehavior shippingBehavior;

    public Product(String name, double price, int quantity,
                   ExpirationBehavior expirationBehavior,
                   ShippingBehavior shippingBehavior) {
        this.name=name;
        this.price = price;
        this.quantity = quantity;
        this.expirationBehavior = expirationBehavior;
        this.shippingBehavior = shippingBehavior;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void reduceQuantity(int amount) {
        if (amount > quantity) {
            throw new IllegalArgumentException("Not enough stock for: " + name);
        }
        quantity -= amount;
    }

    public boolean isShippable() {
        return shippingBehavior.isShippable();
    }

    public ShippingBehavior getShippingBehavior() {
        return shippingBehavior;
    }
    public ExpirationBehavior getExpirationBehavior() {
        return expirationBehavior;
    }
}
