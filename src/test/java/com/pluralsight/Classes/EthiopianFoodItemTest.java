package com.pluralsight.Classes;

import com.pluralsight.Classes.Toppings.RegularTopping;
import com.pluralsight.Enum.FoodType;
import com.pluralsight.Enum.Size;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EthiopianFoodItemTest {

    @Test
    void calculatePrice_regularSmallTibs_noToppings_notSpecial() {
        // Arrange
        EthiopianFoodItem item = new EthiopianFoodItem(FoodType.TIBS_PLATE, Size.SMALL);
        item.setSpecialized(false); // just to be explicit

        // Base for TIBS_PLATE = 18.00, SMALL multiplier = 1.0
        double expected = 18.00 * 1.0;

        // Act
        double actual = item.calculatePrice();

        // Assert
        assertEquals(expected, actual, 0.001);
    }

    @Test
    void calculatePrice_largeInjera_specialized_withToppings() {
        // Arrange
        EthiopianFoodItem item = new EthiopianFoodItem(FoodType.INJERA_COMBO, Size.LARGE);

        // Make it a special/custom plate
        item.setSpecialized(true);

        // Add a topping with explicit prices
        RegularTopping lentils = new RegularTopping("Lentils (Misir)");
        lentils.setPrice(Size.SMALL, 1.00);
        lentils.setPrice(Size.MEDIUM, 1.50);
        lentils.setPrice(Size.LARGE, 2.00);

        item.addTopping(lentils);

        // Base price for INJERA_COMBO = 19.00
        // LARGE multiplier = 2.0
        double base = 19.00 * 2.0;     // 38.00

        // Special charge = 2.00 * size multiplier (2.0) = 4.00
        double special = 2.00 * 2.0;   // 4.00

        // Toppings total (only one topping, large price = 2.00)
        double toppings = 2.00;

        double expected = base + special + toppings; // 44.00

        // Act
        double actual = item.calculatePrice();

        // Assert
        assertEquals(expected, actual, 0.001);
    }

    @Test
    void calculatePrice_mediumBeyaynetu_withMultipleToppings() {
        EthiopianFoodItem item = new EthiopianFoodItem(FoodType.BEYAYNETU, Size.MEDIUM);
        item.setSpecialized(false);

        // First topping
        RegularTopping shiro = new RegularTopping("Shiro");
        shiro.setPrice(Size.MEDIUM, 1.25);

        // Second topping
        RegularTopping gomen = new RegularTopping("Gomen");
        gomen.setPrice(Size.MEDIUM, 1.75);

        item.addTopping(shiro);
        item.addTopping(gomen);

        double base = 25.99 * 1.5; // BEYAYNETU base * MEDIUM multiplier

        double toppings = 1.25 + 1.75;

        double expected = base + toppings;

        double actual = item.calculatePrice();

        assertEquals(expected, actual, 0.001);
    }
}
