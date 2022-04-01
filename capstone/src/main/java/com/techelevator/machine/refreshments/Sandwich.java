package com.techelevator.machine.refreshments;

import java.math.BigDecimal;

public class Sandwich extends Refreshment
{
    // constructors
    public Sandwich(String slotLocation, String itemName, BigDecimal price, String type)
    {
        super(slotLocation, itemName, price, type);
    }

    @Override
    public String printMessage()
    {
        return "Sandwich So Delicious, Yum!";
    }
}
