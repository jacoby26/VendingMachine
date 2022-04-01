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

        int dollars = (int)Math.floor(Double.parseDouble(balance.toString()));
        BigDecimal coinChange = change.subtract(new BigDecimal(dollars));

        int quarters = 0;
        int dimes = 0;
        int nickels = 0;

        // I build this series of if statements to get coin change
        // because all the more elegant code we tried
        // kicked into infinite while loops
        if (coinChange.compareTo(new BigDecimal(0)) > 0)
        {
            // subtracting all the possible quarters from change
            if (coinChange.compareTo(new BigDecimal(0.75)) > 0)
            {
                quarters = 3;
            }
            else if (coinChange.compareTo(new BigDecimal(0.5)) > 0)
            {
                quarters = 2;
            }
            else if (coinChange.compareTo(new BigDecimal(0.25)) > 0)
            {
                quarters = 1;
            }
            BigDecimal coinChangeNoQuarters = coinChange.subtract(new BigDecimal(quarters * 0.25));

            // subtracting all the possible dimes from change
            if (coinChangeNoQuarters.compareTo(new BigDecimal(0.2)) > 0)
            {
                dimes = 2;
            }
            else if (coinChangeNoQuarters.compareTo(new BigDecimal(0.1)) > 0)
            {
                dimes = 1;
            }
            BigDecimal coinChangeNoDimes = coinChange.subtract(new BigDecimal(dimes * 0.1));

            if (coinChangeNoDimes.compareTo(new BigDecimal(0.04)) > 0)
            {
                nickels = 1;
            }
        }

        System.out.println("You have received " +
                dollars + " dollars, " +
                quarters + " quarters, " +
                dimes + " dimes, and " +
                nickels + " nickels.");

        balance = ZERO;
        Transaction changeGiven = new Transaction(false, change);
        Log log = new Log(changeGiven, balance);
        log.writeLogEntry();
    }
}
