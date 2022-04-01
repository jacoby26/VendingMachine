package com.techelevator.machine.processing;

import com.techelevator.interfaces.Purchasable;
import com.techelevator.ui.Transaction;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log
{
    private static final String AUDIT_FILE_PATH = "auditlog.csv";
    private File auditFile = new File(AUDIT_FILE_PATH);

    // attributes
    private LocalDateTime timestamp = LocalDateTime.now();
    private String note;
    private String slotLocation = "";
    private BigDecimal transactionAmount;
    private BigDecimal endBalance;

    // constructors
    public Log(Transaction transaction, BigDecimal endBalance)
    {
        this.transactionAmount = transaction.getDollarAmount();

        if (transaction.getPurchasable() == null)
        {
            if(transaction.isMoneyIn())
            {
                this.note = "MONEY FED: ";
            }
            else
            {
                this.note = "CHANGE GIVEN: ";
            }
        }
        else
        {
            this.note = transaction.getPurchasable().getName();
            this.slotLocation = transaction.getPurchasable().getSlotLocation();
        }

        this.endBalance = endBalance;
    }

    // getters & setters
    public String getNote() {
        return note;
    }

    public String getSlotLocation() {
        return slotLocation;
    }

    // methods
    public String getTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = timestamp.format(formatter);
        return formatDateTime;
    }

    public String getTransactionAmount() {
        return "$" + transactionAmount.toString();
    }

    public String getEndBalance() {
        return "$" + endBalance.toString();
    }

    public void writeLogEntry()
    {
        if(!auditFile.exists())
        {
            try
            {
                // need to add headers to csv

                auditFile.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            try (
                    FileWriter fileWriter = new FileWriter(auditFile, true);
                    PrintWriter writer = new PrintWriter(fileWriter)
            )
            {
                String logEntry = getTimestamp() + "," +
                                getNote() + "," +
                                getSlotLocation() + "," +
                                getTransactionAmount() + "," +
                                getEndBalance() + "\n";

                writer.println(logEntry);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
