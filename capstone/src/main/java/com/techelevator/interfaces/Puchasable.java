package com.techelevator.interfaces;

import java.math.BigDecimal;

public interface Puchasable
{
    public String getSlotLocation();
    public String getName();
    public String getCategory();
    public BigDecimal getPrice();
    public int getQuantity();
}
