package src.main.java.checkout;

import src.main.java.model.*;
import src.main.java.shipping.Shippable;
import src.main.java.shipping.ShippingService;

import java.util.ArrayList;
import java.util.List;

public class CheckoutService {
    private final ShippingFeeStrategy shippingFeeStrategy;

    public CheckoutService(ShippingFeeStrategy shippingFeeStrategy) {
        this.shippingFeeStrategy = shippingFeeStrategy;
    }

    public void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty.");
        }

        double subtotal = 0;
        List<Shippable> shippableItems = new ArrayList<>();

        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();

            if (product.isExpired()) {
                throw new IllegalStateException("Product expired: " + product.getName());
            }

            if (item.getQuantity() > product.getQuantity()) {
                throw new IllegalStateException("Not enough stock for: " + product.getName());
            }

            subtotal += item.getTotalPrice();

            if (product.isShippable() && product instanceof Shippable) {
                for (int i = 0; i < item.getQuantity(); i++) {
                    shippableItems.add((Shippable) product);
                }
            }
        }

        double shippingFee = shippingFeeStrategy.calculate(shippableItems);
        double total = subtotal + shippingFee;

        // Deduct stock
        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }

        customer.deductBalance(total);

        // Ship items
        if (!shippableItems.isEmpty()) {
            ShippingService.ship(shippableItems);
        }

        // Print receipt
        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            System.out.printf("%dx %-12s %.0f%n",
                    item.getQuantity(), item.getProduct().getName(), item.getTotalPrice());
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal         %.0f%n", subtotal);
        if (shippingFee > 0) {
            System.out.printf("Shipping         %.0f%n", shippingFee);
        }
        System.out.printf("Amount           %.0f%n", total);
        System.out.printf("Remaining Balance %.0f%n%n", customer.getBalance());
    }
}
