package com.techelevator.ui;

import com.techelevator.machine.refreshments.Refreshment;

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

            balance = balance.add(transaction.getDollarAmount());
            System.out.println("Current balance: " + balance);
        }

        return balance;
    }

    public static void buyProduct(Transaction transaction)
    {
        BigDecimal pending = balance.subtract(transaction.getDollarAmount());

        if (pending.compareTo(zero) >= 0)
        {
            MathContext m = new MathContext(2);
            balance = balance.subtract(transaction.getDollarAmount());
        }

        if (!transaction.getPurchasable().equals(null))
        {
            System.out.println(transaction.getPurchasable().getName());
            System.out.println("Price $" + transaction.getPurchasable().getPrice());
            System.out.println("Your current balance is $" + balance);
            // System.out.println(transaction.getPurchasable().printMessage());
            // giveChange(transaction);
        }
    }

    // methods
    public static Transaction giveChange(Transaction transaction)
    {
        BigDecimal pending = balance.subtract(transaction.getDollarAmount());
        BigDecimal change = zero;

        if (pending.compareTo(zero) >= 0)
        {
            MathContext m = new MathContext(2);
            change = balance.subtract(transaction.getDollarAmount());
        }

        System.out.println("Your change is $" + change);
        balance = zero;
        return new Transaction(false, change);
    }
}
