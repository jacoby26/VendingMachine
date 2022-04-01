package com.techelevator.machine.refreshments;

import java.math.BigDecimal;

public class Drink extends Refreshment
{
    // constructors
    public Drink(String slotLocation, String itemName, BigDecimal price, String type)
    {
        super(slotLocation, itemName, price, type);
    }

    @Override
    public String printMessage()
    {
        return "Drinky, Drinky, Slurp Slurp!";
    }
}
