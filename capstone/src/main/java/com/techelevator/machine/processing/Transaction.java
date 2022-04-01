package com.techelevator.machine.processing;

import com.techelevator.interfaces.Purchasable;

import java.math.BigDecimal;

public class Transaction {

    // attributes
    private boolean isMoneyIn;
    private BigDecimal dollarAmount;
    private Purchasable purchasable;

    // constructors
    public Transaction(boolean isMoneyIn, BigDecimal dollarAmount)
    {
        this.isMoneyIn = isMoneyIn;
        this.dollarAmount = dollarAmount;
        this.purchasable = null;
    }

    public Transaction(boolean isMoneyIn, BigDecimal dollarAmount, Purchasable purchasable)
    {
        this.isMoneyIn = isMoneyIn;
        this.dollarAmount = dollarAmount;
        this.purchasable = purchasable;
    }

    // getters and setters
    public boolean isMoneyIn() {
        return isMoneyIn;
    }

    public BigDecimal getDollarAmount() {
        return dollarAmount;
    }

    public Purchasable getPurchasable() {
        return purchasable;
    }

    // methods
}
