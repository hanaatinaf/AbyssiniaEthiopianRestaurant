package com.pluralsight;


import com.pluralsight.Classes.EthiopianFoodItem;
import com.pluralsight.Classes.FoodMenu;
import com.pluralsight.Classes.Order;
import com.pluralsight.Classes.Toppings.RegularTopping;
import com.pluralsight.Classes.Toppings.ToppingMenu;
import com.pluralsight.Enum.FoodType;
import com.pluralsight.Enum.Size;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodMenuLogicTest {

    @Test
    void createCustomPlate_itemIsSpecialized_andHasToppings() {
        // We will call EthiopianFoodItem directly instead of prompting scanner
        EthiopianFoodItem item = new EthiopianFoodItem(FoodType.INJERA_COMBO, Size.MEDIUM);

        // Custom plate always specialized
        item.setSpecialized(true);

        // Add toppings
        RegularTopping t1 = new RegularTopping("Gomen");
        t1.setPrice(Size.MEDIUM, 1.50);

        item.addTopping(t1);

        // Validate
        assertTrue(item.isSpecialized(), "Custom plate must be special");
        assertEquals(1, item.getToppings().size());
        assertEquals("Gomen", item.getToppings().get(0).getName());
    }

    @Test
    void addCustomPlateToOrder_increasesOrderTotal() {
        Order order = new Order();

        EthiopianFoodItem item = new EthiopianFoodItem(FoodType.TIBS_PLATE, Size.LARGE);
        item.setSpecialized(true);

        RegularTopping reg = new RegularTopping("Onions");
        reg.setPrice(Size.LARGE, 2.00);

        item.addTopping(reg);

        double before = order.calculateTotal();

        order.addProduct(item);

        double after = order.calculateTotal();

        assertTrue(after > before, "Order total should increase after adding item");
    }
}
