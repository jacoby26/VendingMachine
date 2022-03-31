package com.techelevator.machine.refreshments;

import java.math.BigDecimal;

public class Munchy extends Refreshments
{
    // constructors

    public Munchy(String slotLocation, String itemName, BigDecimal price, String type)
    {
        super(slotLocation, itemName, price, type);
    }

    // methods
    @Override
    public void printMessage()
    {
        System.out.println("Munchy, Munchy so Good!");
    }
}
