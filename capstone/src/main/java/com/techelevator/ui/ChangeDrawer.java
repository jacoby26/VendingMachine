package com.techelevator.ui;

import java.math.BigDecimal;

public class ChangeDrawer {

    // attributes
    private static BigDecimal balance;
    static BigDecimal zero = new BigDecimal(0);

    // constructors
    public ChangeDrawer(){}

    // getters & setters

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void putMoney(Transaction transaction)
    {
        BigDecimal pending = balance.subtract(transaction.getDollarAmount());

        if (transaction.isMoneyIn())
        {
            balance.add(transaction.getDollarAmount());
            System.out.println("Current balance: " + balance);
        }
    }

    // methods
    public static Transaction giveChange(Transaction transaction)
    {
        BigDecimal pending = balance.subtract(transaction.getDollarAmount());
        BigDecimal change = zero;

        if (pending.compareTo(zero) >= 0)
        {
            change = balance.subtract(transaction.getDollarAmount());
        }

        System.out.println("Your change is $" + change);
        balance = zero;
        return new Transaction(false, change);
    }
}
