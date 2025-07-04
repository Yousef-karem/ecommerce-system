package src.main.java.model;

public abstract class Product {
    String name;
    double price;
    int quantity;
    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getQuantity() {return quantity;}
    public String getName() {return name;}
    public double getPrice() {return price;}

    public void addQuantity(int quantity)
    {
        this.quantity+=quantity;
    }

    public void reduceQuantity(int amount) {
        if (amount > quantity) throw new IllegalArgumentException("Not enough stock.");
        quantity -= amount;
    }

    // to be overridden
    public abstract boolean isExpired();
    public abstract boolean isShippable();
}
