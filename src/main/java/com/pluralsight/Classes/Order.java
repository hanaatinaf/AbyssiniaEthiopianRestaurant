package com.pluralsight.Classes;

import com.pluralsight.Abstract.Product;
import com.pluralsight.Classes.EthiopianFoodItem;
import com.pluralsight.Abstract.Topping;
import com.pluralsight.Classes.Toppings.PremiumTopping;
import com.pluralsight.Enum.Size;
import com.pluralsight.Classes.Drink.Drink;





import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;




/**
 * Represents a customer's order.
 * An order contains multiple products (main dishes, drinks, sides),
 * knows when it was created, and can generate a receipt string.
 */
public class Order {

    private String id;
    private LocalDateTime dateTime;
    private final List<Product>  products;
    private boolean isCompleted;

    private static final DateTimeFormatter ID_FORMAT =
            DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");


    public Order() {
        this.dateTime = LocalDateTime.now();
        this.id = dateTime.format(ID_FORMAT); // example: 20251112-153045
        this.products = new ArrayList<>();
        this.isCompleted = false;
    }

    // --- Basic getters ---

    public String getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public boolean isCompleted() {
        return isCompleted;
    }


    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    // --- Order operations ---


    public void addProduct(Product product) {
        if (product != null && !isCompleted) {
            products.add(product);
        }
    }

    /**
     * Removes a product from the order.
     */
    public void removeProduct(Product product) {
        if (!isCompleted) {
            products.remove(product);
        }
    }



    public double calculateTotal() {
        // Uses Java Streams to sum up product prices
        return products.stream()
                .mapToDouble(Product::calculatePrice)
                .sum();
    }

    public void completeOrder() {
        this.isCompleted = true;

    }


    public String generateReceipt() {
        StringBuilder sb = new StringBuilder();
        // Format the date/time nicely: 2025-11-12 07:28 PM
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        String formattedDateTime = dateTime.format(formatter);

        // Header
        sb.append("=====  Ethiopian Restaurant Receipt =====\n");
        sb.append("Order ID : ").append(id).append("\n");
        sb.append("Date/Time: ").append(formattedDateTime).append("\n");
        sb.append("---------------------------------------\n");

        double orderTotal = 0.0;

        // Line items
        for (Product product : products) {
            double linePrice = product.calculatePrice();

            String lineName;

            // If it's a Drink, show the drink flavor/type instead of just "Drink"
            if (product instanceof Drink) {
                Drink drink = (Drink) product;
                // Just the flavor, like "Layered Mixed Juice"
                lineName = drink.getFlavor();
                // If you prefer: lineName = "Drink - " + drink.getFlavor();
            } else {
                // For food and sides, keep using the product name
                lineName = product.getName();
            }

            // Add size if available
            if (product.getSize() != null) {
                lineName += " (" + product.getSize().getDisplayName() + ")";
            }

            sb.append(String.format("%-30s %6.2f%n", lineName, linePrice));
        }

        sb.append("=======================================\n");
        sb.append(String.format("TOTAL:%34.2f%n", calculateTotal()));
        sb.append("=======================================\n");


        return sb.toString();
    }

}
