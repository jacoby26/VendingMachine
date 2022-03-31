package com.techelevator.machine.processing;

import com.techelevator.interfaces.Purchasable;
import com.techelevator.machine.refreshments.Refreshments;

import java.math.BigDecimal;

public class Transaction {
    public boolean inOrOut;
    public BigDecimal dollarAmount;
    public Refreshments refreshments;
    public Purchasable purchasable;
}
