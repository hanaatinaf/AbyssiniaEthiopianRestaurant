package com.pluralsight.Abstract;
import com.pluralsight.Enum.FoodType;
import com.pluralsight.Enum.Size;
import com.pluralsight.Interfaces.Customizable;


import java.util.*;

public abstract class Item extends Product implements Customizable {

    private FoodType type;
    private final List<Topping> toppings;
    private boolean isSpecialized;

    //  Protected constructor only subclasses (like EthiopianFoodItem) can create an Item.
    protected Item(String name, Size size, FoodType type) {
        super(name, size);
        this.type = type;
        this.toppings = new ArrayList<>();
        this.isSpecialized = false;
    }
    //  Type getters / setters
    public FoodType getType() {
        return type;
    }

    public void setType(FoodType type) {
        this.type = type;
    }

    // Specialized flag
     // Whether the item is "specialized" (e.g., stuffed crust / special option).

    public boolean isSpecialized() {
        return isSpecialized;
    }

    public void setSpecialized(boolean specialized) {
        isSpecialized = specialized;
    }

    // Toppings handling
    // Returns an unmodifiable view of the toppings list so that
     //  outside code cannot modify it directly.

    public List<Topping> getToppings() {
        return Collections.unmodifiableList(toppings);
    }

    @Override
    public void addTopping(Topping topping) {
        if (topping != null) {
            toppings.add(topping);
        }
    }
    @Override
    public void removeTopping(Topping topping) {
        toppings.remove(topping);
    }

    // Helper method for subclasses to calculate the total toppings cost based on the current size.

    protected double calculateToppingsTotal() {
        double total = 0.0;
        for (Topping topping : toppings) {
            total += topping.getPrice(getSize());
        }
        return total;
    }
    // Subclasses MUST implement their own price logic using: - base price depending on size - toppings total
    @Override
    public abstract double calculatePrice();
}
