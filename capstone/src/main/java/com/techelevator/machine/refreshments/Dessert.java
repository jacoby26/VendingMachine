package com.techelevator.machine.refreshments;

import java.math.BigDecimal;

public class Dessert extends Refreshment
{
    // constructors
    public Dessert(String slotLocation, String itemName, BigDecimal price, String type)
    {
        super(slotLocation, itemName, price, type);
    }

    @Override
    public String printMessage()
    {
        return "Sugar, Sugar, so Sweet!";
    }
}
