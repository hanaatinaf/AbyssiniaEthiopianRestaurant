package com.pluralsight.Classes;

import com.pluralsight.Abstract.Topping;
import com.pluralsight.Enum.Size;

public class PermiumTopping extends Topping {
    private  boolean isExtra;
    public PermiumTopping(String name, double priceSmall, double priceMedium, double priceLarge) {
        super(name, priceSmall, priceMedium, priceLarge);
    }

    public boolean isExtra() {
        return isExtra;
    }

    public void setExtra(boolean extra) {
        this.isExtra = extra;
    }
    @Override
    public  double getPrice(Size size){
        double basePrice = super.getPrice(size);

        // extra premim cost +4 regardeless of the size
        if (isExtra){
            return  basePrice + 4;
        }
        return  basePrice;
    }
    @Override
    public  String toString(){
        return  getName() + (isExtra ? " Permimum +$4" : " ");
    }

}
