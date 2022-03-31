package com.techelevator.interfaces;

import java.math.BigDecimal;

public interface Purchasable {
    public String getSlotLocation();
    public String getName();
    public String getType();
    public BigDecimal getPrice();
    public int getQuantity();
}
