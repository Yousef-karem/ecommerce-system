# E-Commerce System

This is a Java-based Object-Oriented Programming (OOP) project built for the [Fawry Rise Journey Full Stack Internship Challenge](https://fawry-internship.notion.site/22573781f94380039bd8e91e04dbef83?pvs=105).

It simulates a minimal e-commerce system with cart management, checkout processing, expirable products, and shipping support.

---

## Features

-  Add products to cart with quantity validation
-  Support for **shippable** and **non-shippable** products
-  Handle **expirable** and **non-expirable** products
-  Customer checkout with balance deduction and validation
-  Receipt generation:
  - Subtotal
  - Shipping Fee
  - Total
  - Remaining Balance
-  Shipping service with shipment notice and total package weight
-  Full handling of edge cases:
  - Empty cart
  - Insufficient balance
  - Expired products
  - Exceeding available stock

---

##  Technologies Used

- Java 17+
- JUnit 5 (for testing)
- OOP principles:
  - Abstraction
  - Inheritance
  - Encapsulation
  - Polymorphism
- Design Pattern:
  - **Strategy Pattern** (used for shipping fee calculation)

---

##  Project Structure
src/
├── main/
│ ├── model/ # Product, Customer, Cart, CartItem
│ ├── shipping/ # Shippable interface and ShippingService
│ ├── checkout/ # CheckoutService and ShippingFeeStrategy
│ └── Main.java # Entry point with demo scenarios
└── test/
├── model/ # JUnit tests for model classes
├── checkout/ # Tests for checkout logic and strategy
└── shipping/ # Tests for shipping fee strategies  
## Example Output

** Shipment notice **
2x Cheese
1x Biscuits
400g
400g
700g
Total package weight 1.5kg

** Checkout receipt **
2x Cheese 200
1x Biscuits 150
1x ScratchCard 50
Subtotal 400
Shipping 30
Amount 430
Remaining Balance 570

## Running Tests

Make sure you have JUnit 5 configured.
