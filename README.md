# E-Commerce System

This is a Java-based Object-Oriented Programming (OOP) project built for the Fawry Rise Journey Full Stack Internship Challenge.

It simulates a minimal e-commerce system with cart management, checkout processing, expirable products, and shipping support.

---

## Features

- Generic `Product` class composed with:
  - `ShippingBehavior` (e.g., weight-based or no shipping)
  - `ExpirationBehavior` (e.g., date-based, never expires)
- Supports shipping items via `ShippingService` using behavior-driven logic
- Handles expirable and non-expirable products dynamically
- Customers with balance deduction logic
- Cart & checkout flow with:
  - Quantity checks
  - Expiration checks
  - Balance validation
  - Shipping fee via **Strategy Pattern**
- Robust handling of edge cases:
  - Empty cart
  - Insufficient balance
  - Expired products
  - Exceeding available stock

---

##  Technologies Used

- Java 17+
- JUnit 5 for testing
- OOP principles:
  - **Composition** over inheritance
  - **Interface Segregation** for specialized behaviors
  - **Strategy Pattern** for shipping fees

---
## UML Class Diagram

[UML Diagram](UML.png)
 
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

