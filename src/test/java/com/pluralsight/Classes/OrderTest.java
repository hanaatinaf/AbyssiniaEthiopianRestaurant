package com.pluralsight.Classes;

import com.pluralsight.Classes.Drink.Drink;
import com.pluralsight.Classes.Sides.Side;
import com.pluralsight.Enum.Size;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    void calculateTotal_sumsAllProductPrices() {
        Order order = new Order();

        Drink drink = new Drink("Drink", Size.SMALL, "Spiced Black Tea");
        Side side = new Side("Salata", Size.SMALL);

        order.addProduct(drink);
        order.addProduct(side);

        double expected = drink.calculatePrice() + side.calculatePrice();
        double actual = order.calculateTotal();

        assertEquals(expected, actual, 0.001);
    }

    @Test
    void addProduct_afterOrderCompleted_doesNotChangeProducts() {
        Order order = new Order();

        Drink drink = new Drink("Drink", Size.SMALL, "Mango Juice");
        order.addProduct(drink);

        int beforeComplete = order.getProducts().size();

        order.completeOrder();

        // Try to add another product after completion
        Side side = new Side("Extra Injera", Size.SMALL);
        order.addProduct(side);

        int afterComplete = order.getProducts().size();

        assertEquals(beforeComplete, afterComplete,
                "No new products should be added after order is completed");
    }

    @Test
    void generateReceipt_containsOrderIdAndTotal() {
        Order order = new Order();
        Drink drink = new Drink("Drink", Size.MEDIUM, "Spris");
        order.addProduct(drink);

        String receipt = order.generateReceipt();

        assertNotNull(receipt);
        assertTrue(receipt.contains("Order ID"),
                "Receipt should contain 'Order ID'");
        assertTrue(receipt.contains("TOTAL"),
                "Receipt should contain 'TOTAL'");
        assertTrue(receipt.contains(drink.getName()),
                "Receipt should contain product name");
    }
}
