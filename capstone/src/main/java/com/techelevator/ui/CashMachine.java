package com.techelevator.ui;

import com.techelevator.machine.processing.Log;
import com.techelevator.machine.processing.Transaction;
import com.techelevator.machine.refreshments.PopTart;
import com.techelevator.view.Color;
import com.techelevator.view.ColorDesignator;

import java.awt.*;
import java.math.BigDecimal;

public class CashMachine {
    static final BigDecimal ZERO = new BigDecimal(0);

    // attributes
    public static BigDecimal balance = ZERO;

    // constructors
    public CashMachine()
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

    public static BigDecimal buyProduct(Transaction transaction)
    {
        BigDecimal pending = balance.subtract(transaction.getDollarAmount());

        if (!transaction.isMoneyIn())
        {
            if (transaction.getPurchasable() != null)
            {
                if (pending.compareTo(ZERO) >= 0)
                {
                    balance = balance.subtract(transaction.getDollarAmount());
                    System.out.println(ColorDesignator.Yellow + transaction.getPurchasable().getName() + ColorDesignator.Reset);
                    System.out.println(ColorDesignator.Purple + "Price " + ColorDesignator.Reset + ColorDesignator.Red + "$" + transaction.getPurchasable().getPrice() + ColorDesignator.Reset);
                    System.out.println(ColorDesignator.Purple + "Your current balance is " + ColorDesignator.Reset + ColorDesignator.Green + "$" + balance + ColorDesignator.Reset);
                    System.out.println(ColorDesignator.Yellow + transaction.getPurchasable().printMessage() + ColorDesignator.Reset);
                    // giveChange(transaction);
                    Log log = new Log(transaction, balance);
                    log.writeLogEntry();
                }
            }
            else
            {
                return balance;
            }
        }

        return balance;
    }

    // methods
    public static BigDecimal giveChange(Transaction transaction)
    {
        // the pending variable is used here
        // for if in the future a transaction with dollar amount greater than zero
        // is ever passed into this function
        BigDecimal pending = balance.subtract(transaction.getDollarAmount());
        BigDecimal change = ZERO;

        if (!transaction.isMoneyIn() && transaction.getDollarAmount().equals(BigDecimal.valueOf(0)))
        {
            if (pending.compareTo(ZERO) >= 0)
            {
                change = balance.subtract(transaction.getDollarAmount());

                System.out.println(ColorDesignator.Purple + "Your change is " + ColorDesignator.Reset + ColorDesignator.Green + "$" +  change + ColorDesignator.Reset);

                int dollars = (int)Math.floor(Double.parseDouble(balance.toString()));
                BigDecimal coinChange = change.subtract(new BigDecimal(dollars));

                // converting fractional dollar amount into whole number
                // so BigDecimal math can be done easier
                coinChange = coinChange.multiply(new BigDecimal(100));

                int quarters = (int)Math.floor(Double.parseDouble(coinChange.divide(new BigDecimal(25)).toString()));
                coinChange = coinChange.remainder(new BigDecimal(25));

                int dimes = (int)Math.floor(Double.parseDouble(coinChange.divide(new BigDecimal(10)).toString()));
                coinChange = coinChange.remainder(new BigDecimal(10));

                int nickels = (int)Math.floor(Double.parseDouble(coinChange.divide(new BigDecimal(5)).toString()));

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

            return change;
        }
        return ZERO;
    }
}
