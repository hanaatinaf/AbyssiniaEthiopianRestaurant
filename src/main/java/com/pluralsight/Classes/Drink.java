package com.pluralsight.Classes;

import com.pluralsight.Abstract.Product;
import com.pluralsight.Enum.Size;

public class Drink extends Product {
    private  String flavor;

    public Drink( String name , Size size, String flavor) {
        super(size, name);
        this.flavor = flavor;

    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    @Override
    public double calculatePrice() {
        switch (getSize()){
            case SMALL :
                return  3.50;
            case MEDIUM:
                return  4.50;
            case LARGE:
                return  6.00;
            default:
                return  0;
        }

    }
    @Override
    public  String toString(){
        return  getName() + "( " + getSize().getDisplayName() + " , " + flavor + " ) ";
    }
}
