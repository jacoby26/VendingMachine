package com.techelevator.machine.processing;

import com.techelevator.interfaces.Purchasable;
import com.techelevator.machine.refreshments.Refreshment;

import java.math.BigDecimal;

public class Transaction {
    public boolean inOrOut;
    public BigDecimal dollarAmount;
    public Refreshment refreshment;
    public Purchasable purchasable;
}
