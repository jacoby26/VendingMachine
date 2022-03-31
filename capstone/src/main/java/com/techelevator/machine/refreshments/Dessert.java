package com.techelevator.machine.refreshments;

import java.math.BigDecimal;

public class Dessert extends Refreshments
{
    // constructors
    public Dessert(String slotLocation, String itemName, BigDecimal price, String type)
    {
        super(slotLocation, itemName, price, type);
    }

    @Override
    public void printMessage()
    {
        System.out.println("Sugar, Sugar, so Sweet!");
    }
}
