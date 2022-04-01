package com.techelevator.ui;

import com.techelevator.machine.processing.Log;
import com.techelevator.machine.refreshments.Refreshment;

import java.math.BigDecimal;
import java.math.MathContext;

public class ChangeDrawer {
    static final BigDecimal ZERO = new BigDecimal(0);

    // attributes
    public static BigDecimal balance = ZERO;

    // constructors
    public ChangeDrawer()
    {
        this.balance = ZERO;
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
            Log log = new Log(transaction, balance);
            log.writeLogEntry();
        }

        return balance;
    }

    public static void buyProduct(Transaction transaction)
    {
        BigDecimal pending = balance.subtract(transaction.getDollarAmount());

        if (pending.compareTo(ZERO) >= 0)
        {
            balance = balance.subtract(transaction.getDollarAmount());
        }

        if (!transaction.getPurchasable().equals(null))
        {
            System.out.println(transaction.getPurchasable().getName());
            System.out.println("Price $" + transaction.getPurchasable().getPrice());
            System.out.println("Your current balance is $" + balance);
            System.out.println(transaction.getPurchasable().printMessage());
            // giveChange(transaction);
            Log log = new Log(transaction, balance);
            log.writeLogEntry();
        }
    }

    // methods
    public static void giveChange(Transaction transaction)
    {
        BigDecimal pending = balance.subtract(transaction.getDollarAmount());
        BigDecimal change = ZERO;

        if (pending.compareTo(ZERO) >= 0)
        {
            change = balance.subtract(transaction.getDollarAmount());
        }

        System.out.println("Your change is $" + change);
        balance = ZERO;
        Transaction changeGiven = new Transaction(false, change);
        Log log = new Log(changeGiven, balance);
        log.writeLogEntry();
    }
}
