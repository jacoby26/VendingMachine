package com.techelevator.machine.refreshments;

import java.math.BigDecimal;

public class PopTart
{
    // attributes
    private String slotLocation;
    private String name;
    private String flavor;
    private BigDecimal price;
    private int quantity = 7;

    // constructors
    public PopTart(String x, String flavor, BigDecimal price)
    {
        this.name = x;
        this.flavor = flavor;
        this.price = price;
    }

    // getters & setters
    public String getName()
    {
        return name;
    }

    // methods

}
