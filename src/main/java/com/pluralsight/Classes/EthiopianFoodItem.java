package com.pluralsight.Classes;

import com.pluralsight.Abstract.*;
import com.pluralsight.Classes.Toppings.PremiumTopping;
import com.pluralsight.Classes.Toppings.RegularTopping;
import com.pluralsight.Enum.FoodType;
import com.pluralsight.Enum.Size;

public class EthiopianFoodItem extends Item {
    /* Public constructor used by your UI when the customer builds their own plate step-by-step.
     By default, we set the "name" to the FoodType display name. */
    public EthiopianFoodItem(FoodType type, Size size) {
        super(type.getDisplayName(), size, type);
    }

    // Calculates the total price of this Ethiopian plate:
    // 1) base price by size 2) + toppings total 3) + specialized extra (if isSpecialized == true)


    @Override
    public double calculatePrice() {
        double basePrice;

        // 1) Base price by food type (you can adjust these numbers)
        switch (getType()) {
            case INJERA_COMBO:
                basePrice = 19.00;
                break;
            case TIBS_PLATE:
                basePrice = 18.00;
                break;
            case KITFO_PLATE:
                basePrice = 20.88;
                break;
            case BEYAYNETU:
                basePrice = 25.99;
                break;
            default:
                basePrice = 9.00;
                break;
        }
        // 2) Apply size multiplier (from your Size enum)
        double sizeMultiplier = getSize().getPriceMultiplier();
        double price = basePrice * sizeMultiplier;

        // 3) Add special customization charge
        if (isSpecialized()) {
            // Example: add 2.00 times the size multiplier
            double specialCharge = 2.00 * sizeMultiplier;
            price += specialCharge;
        }
        // 4) Add toppings cost (regular + premium)
        if (getToppings() != null && !getToppings().isEmpty()) {
            for (Topping t : getToppings()) {
                price += t.getPrice(getSize());
            } } return price;
    }

    public static EthiopianFoodItem createBeyaynetu(Size size) {
        EthiopianFoodItem item =
                new EthiopianFoodItem(FoodType.BEYAYNETU, size);

        // Example default toppings for Beyaynetu – adjust as you like.
        item.addTopping(new RegularTopping("Lentils (Misir)"));
        item.addTopping(new RegularTopping("Shiro"));
        item.addTopping(new RegularTopping("Gomen"));
        item.addTopping(new RegularTopping("Salata"));

        // Usually Beyaynetu is already "special", but we can leave false.
        item.setSpecialized(false);

        return item;
    }
    public static EthiopianFoodItem createTibsPlate(Size size) {
        EthiopianFoodItem item =
                new EthiopianFoodItem(FoodType.TIBS_PLATE, size);

        item.addTopping(new PremiumTopping("Beef Tibs", false));
        item.addTopping(new RegularTopping("Onions & Peppers"));

        return item;
    }
    public static EthiopianFoodItem createKitfoPlate(Size size) {
        EthiopianFoodItem item =
                new EthiopianFoodItem(FoodType.KITFO_PLATE, size);

        item.addTopping(new PremiumTopping("Kitfo", false));
        item.addTopping(new RegularTopping("Gomen"));
        item.addTopping(new RegularTopping("Ayib"));

        // Maybe Kitfo plate has a special preparation
        item.setSpecialized(true);

        return item;
    }
    public static EthiopianFoodItem createInjeraCombo(Size size) {
        EthiopianFoodItem item =
                new EthiopianFoodItem(FoodType.INJERA_COMBO, size);

        item.addTopping(new RegularTopping("Salata"));
        item.addTopping(new RegularTopping("Gomen"));
        item.addTopping(new PremiumTopping("Doro Wat", false));

        return item;
    }
}