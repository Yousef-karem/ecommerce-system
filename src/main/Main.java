package src.main;

import src.main.java.expiring.ExpiresOnDate;
import src.main.java.expiring.NeverExpires;
import src.main.java.model.*;
import src.main.java.checkout.*;
import src.main.java.shipping.FlatRateShippingFee;
import src.main.java.shipping.NoShippingBehavior;
import src.main.java.shipping.WeightBasedShippingBehavior;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        // => VALID CASE <=
        System.out.println("========== Valid Checkout ==========");
        try {
            Customer customer = new Customer("Ahmed", 1000);
            Cart cart = new Cart();

            // create ShippableExpirableProduct
            Product cheese = new Product("Cheese", 100, 5,
                    new ExpiresOnDate( LocalDate.now().plusDays(2)),
                    new WeightBasedShippingBehavior("Cheese",0.2));
            Product biscuits = new Product("Biscuits", 150, 3,
                    new ExpiresOnDate(LocalDate.now().plusDays(5)),
                    new WeightBasedShippingBehavior("Biscuits",0.7));

            Product scratchCard = new Product("ScratchCard", 50, 10,new NeverExpires(),new NoShippingBehavior());

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

            Product expired = new Product("Old Cheese", 120, 5,
                    new ExpiresOnDate( LocalDate.now().minusDays(1)),
                    new WeightBasedShippingBehavior("Old Cheese",0.3));
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

            Product tv = new Product("TV", 500, 1,
                    new NeverExpires(),
                    new WeightBasedShippingBehavior("TV",10.0) );
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

            Product scratchCard = new Product("ScratchCard", 50, 2,
                    new NeverExpires(),
                    new WeightBasedShippingBehavior("ScratchCard",10.0));
            cart.add(scratchCard, 3); // Invalid

            CheckoutService service = new CheckoutService(new FlatRateShippingFee());
            service.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Expected: " + e.getMessage());
        }
    }
}
