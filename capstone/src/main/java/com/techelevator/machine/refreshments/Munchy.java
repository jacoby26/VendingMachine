package com.techelevator.machine.refreshments;

import java.math.BigDecimal;

public class Munchy extends Refreshment
{
    // constructors

    public Munchy(String slotLocation, String itemName, BigDecimal price, String type)
    {
        super(slotLocation, itemName, price, type);
    }

    // methods
    @Override
    public String printMessage()
    {
        return "Munchy, Munchy so Good!";
    }
}
