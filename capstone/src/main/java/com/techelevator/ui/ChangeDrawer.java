package com.techelevator.ui;

import java.math.BigDecimal;
import java.math.MathContext;

public class ChangeDrawer {
    static BigDecimal zero = new BigDecimal(0);
    static MathContext m = new MathContext(2);

    // attributes
    public static BigDecimal balance = zero;

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

            balance = balance.add(transaction.getDollarAmount()).round(m);
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
            MathContext m = new MathContext(2);
            change = balance.subtract(transaction.getDollarAmount()).round(m);
        }

        System.out.println("Your change is $" + change);
        balance = zero;
        return new Transaction(false, change);
    }
}
