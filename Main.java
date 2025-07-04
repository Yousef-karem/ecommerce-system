import model.*;
import checkout.*;
import shipping.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        // => VALID CASE <=
        System.out.println("========== Valid Checkout ==========");
        try {
            Customer customer = new Customer("Ahmed", 1000);
            Cart cart = new Cart();

            Product cheese = new ShippableExpirableProduct("Cheese", 100, 5, LocalDate.now().plusDays(2), 0.2);
            Product biscuits = new ShippableExpirableProduct("Biscuits", 150, 3, LocalDate.now().plusDays(5), 0.7);
            Product scratchCard = new BasicProduct("ScratchCard", 50, 10);

            cart.add(cheese, 2);
            cart.add(biscuits, 1);
            cart.add(scratchCard, 1);

            CheckoutService service = new CheckoutService(new FlatRateShippingFee());
            service.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // => EMPTY CART <=
        System.out.println("\n========== Empty Cart ==========");
        try {
            Customer customer = new Customer("Hassan", 500);
            Cart emptyCart = new Cart();

            CheckoutService service = new CheckoutService(new FlatRateShippingFee());
            service.checkout(customer, emptyCart);
        } catch (Exception e) {
            System.out.println("Expected: " + e.getMessage());
        }

        // => EXPIRED PRODUCT <=
        System.out.println("\n========== Expired Product ==========");
        try {
            Customer customer = new Customer("Ahmed", 500);
            Cart cart = new Cart();

            Product expired = new ShippableExpirableProduct("Old Cheese", 120, 5, LocalDate.now().minusDays(1), 0.3);
            cart.add(expired, 1);

            CheckoutService service = new CheckoutService(new FlatRateShippingFee());
            service.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Expected: " + e.getMessage());
        }

        // => INSUFFICIENT BALANCE <=
        System.out.println("\n========== Insufficient Balance ==========");
        try {
            Customer customer = new Customer("Mohamed", 100);
            Cart cart = new Cart();

            Product tv = new ShippableProduct("TV", 500, 1, 10.0);
            cart.add(tv, 1);

            CheckoutService service = new CheckoutService(new FlatRateShippingFee());
            service.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Expected: " + e.getMessage());
        }

        // => QUANTITY EXCEEDS STOCK <=
        System.out.println("\n========== Quantity Exceeds Stock ==========");
        try {
            Customer customer = new Customer("Ramy", 1000);
            Cart cart = new Cart();

            Product scratchCard = new BasicProduct("ScratchCard", 50, 2);
            cart.add(scratchCard, 3); // Invalid

            CheckoutService service = new CheckoutService(new FlatRateShippingFee());
            service.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Expected: " + e.getMessage());
        }
    }
}
