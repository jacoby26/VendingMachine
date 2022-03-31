package com.techelevator.ui;

import java.math.BigDecimal;

public class ChangeDrawer {
    static BigDecimal zero = new BigDecimal(0);

    // attributes
    private static BigDecimal balance = zero;

    // constructors
    public ChangeDrawer()
    {
        this.balance = zero;
    }

    // getters & setters
    public static BigDecimal getBalance() {
        return balance;
    }

    public static BigDecimal putMoney(Transaction transaction)
    {
        if (transaction.isMoneyIn())
        {
            balance.add(transaction.getDollarAmount());
            System.out.println("Current balance: " + balance);
        }

        return balance;
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
