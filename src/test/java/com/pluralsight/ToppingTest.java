package com.pluralsight.Tests;

import com.pluralsight.Classes.Toppings.PremiumTopping;
import com.pluralsight.Classes.Toppings.RegularTopping;
import com.pluralsight.Enum.Size;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ToppingTest {

    @Test
    void regularTopping_priceBySize_isCorrect() {
        RegularTopping topping = new RegularTopping("Shiro");

        topping.setPrice(Size.SMALL, 1.00);
        topping.setPrice(Size.MEDIUM, 1.50);
        topping.setPrice(Size.LARGE, 2.00);

        assertEquals(1.00, topping.getPrice(Size.SMALL));
        assertEquals(1.50, topping.getPrice(Size.MEDIUM));
        assertEquals(2.00, topping.getPrice(Size.LARGE));
    }

    @Test
    void premiumTopping_hasExtraFlag() {
        PremiumTopping topping = new PremiumTopping("Doro Wat", true);

        assertTrue(topping.isExtra(), "Premium topping should allow EXTRA flag");
    }

    @Test
    void premiumTopping_priceBySize_isCorrect() {
        PremiumTopping topping = new PremiumTopping("Kitfo", false);

        topping.setPrice(Size.SMALL, 2.50);
        topping.setPrice(Size.MEDIUM, 3.50);
        topping.setPrice(Size.LARGE, 4.50);

        assertEquals(2.50, topping.getPrice(Size.SMALL));
        assertEquals(3.50, topping.getPrice(Size.MEDIUM));
        assertEquals(4.50, topping.getPrice(Size.LARGE));
    }
}
