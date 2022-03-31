package com.techelevator.machine;

import com.techelevator.interfaces.Puchasable;

import java.math.BigDecimal;

public abstract class Refreshment implements Puchasable
{
    // attributes
    private String slotLocation;
    private String name;
    private String category;
    private BigDecimal price;
    private int quantity = 7;

    // constructors
    public Refreshment()
    {
    }
    public Refreshment(String slotLocation, String name, String category, BigDecimal price)
    {
        this.slotLocation = slotLocation;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    // getters and setters

    public String getSlotLocation() {
        return slotLocation;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
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
