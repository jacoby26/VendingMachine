package com.techelevator.machine.refreshments;

import com.techelevator.interfaces.Purchasable;

import java.math.BigDecimal;

public abstract class Refreshment implements Purchasable
{
    // attributes
    private String slotLocation;
    private String name;
    private String type;
    private BigDecimal price;
    private int quantity = 7;

    // constructors
    public Refreshment()
    {
    }

    public Refreshment(String slotLocation, String name, BigDecimal price, String type)
    {
        this.slotLocation = slotLocation;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    // getters and setters

    public String getSlotLocation() {
        return slotLocation;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // methods
    public boolean inStock()
    {
        if (quantity > 0)
        {
            return true;
        }

        return false;
    }

    public abstract void printMessage();
}
