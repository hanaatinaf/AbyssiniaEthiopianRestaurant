package com.plursalsight.Abstract;

import com.plursalsight.Enum.ItemType;

import java.util.List;

public abstract  class Item {
    private ItemType  type;
    private List<Topping> toppings ;
    private boolean isSpecialized;

    ItemType getType(){

        return null;
    };
    void setType(ItemType type){

    }
    List<Topping> getToppings(){

        return List.of();
    };
    boolean isSpecialized(){

        return false;
    }
    void setSpecialized(boolean specialized){

    }
    void addTopping(Topping topping){

    };



}
