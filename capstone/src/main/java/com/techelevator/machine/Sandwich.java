package com.techelevator.machine;

import java.math.BigDecimal;

public class Sandwich extends Refreshment
{
    // constructors
    public Sandwich(String slotLocation, String itemName, BigDecimal price, String type)
    {
        super(slotLocation, itemName, price, type);
    }

    @Override
    public void printMessage()
    {
        System.out.println("Sandwich So Delicious, Yum!");
    }
}