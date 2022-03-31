package com.techelevator.ui;

import com.techelevator.interfaces.Purchasable;

import java.math.BigDecimal;

public class Transaction {

    // attributes
    private boolean isMoneyIn;
    private BigDecimal dollarAmount;
    private Purchasable purchasable;

    // constructors
    public Transaction(boolean inOrOut, BigDecimal dollarAmount)
    {
        this.isMoneyIn = inOrOut;
        this.dollarAmount = dollarAmount;
        this.purchasable = null;
    }

    public Transaction(boolean inOrOut, BigDecimal dollarAmount, Purchasable purchasable)
    {
        this.isMoneyIn = inOrOut;
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
