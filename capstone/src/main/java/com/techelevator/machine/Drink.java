package com.techelevator.machine;

import java.math.BigDecimal;

public class Drink extends Refreshment
{
    // constructors
    public Drink(String slotLocation, String itemName, BigDecimal price, String type)
    {
        super(slotLocation, itemName, price, type);
    }

    @Override
    public void printMessage()
    {
        System.out.println("Drinky, Drinky, Slurp Slurp!");
    }
}
