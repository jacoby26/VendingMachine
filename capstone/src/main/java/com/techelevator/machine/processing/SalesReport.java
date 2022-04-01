package com.techelevator.machine.processing;

import com.techelevator.interfaces.Purchasable;
import com.techelevator.machine.refreshments.Refreshment;
import com.techelevator.ui.Transaction;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SalesReport
{
    private static final String SALES_REPORT_PATH = "salesreport.csv";
    private File salesReportFile = new File(SALES_REPORT_PATH);

    // attributes
    private String name;
    private int quantity;

    // constructors
    public SalesReport(String name, int quantity)
    {
        this.name = name;
        this.quantity = quantity;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    // methods
    public static List<SalesReport> getRefreshmentsSalesReportList(List<Refreshment> refreshments, List<Transaction> transactions)
    {
        List<SalesReport> salesReportList = new ArrayList<>();

        for (Refreshment refreshment : refreshments)
        {
            int count = 0;
            for (Transaction transaction : transactions)
            {
                if (transaction.getPurchasable().getName().equals(refreshment.getName()))
                {
                    count++;
                }
            }

            SalesReport salesReport = new SalesReport(refreshment.getName(), count);
            salesReportList.add(salesReport);
        }

        return salesReportList;
    }

    public void writeSalesReport(List<SalesReport> salesReportList)
    {
        if(!salesReportFile.exists())
        {
            try {
                // need to add headers to csv

                salesReportFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (
                FileWriter fileWriter = new FileWriter(salesReportFile, false);
                PrintWriter writer = new PrintWriter(fileWriter)
        )
        {
            for (SalesReport salesReport: salesReportList)
            {
                String reportEntry = getName() + getQuantity() + "\n";
                writer.println(reportEntry);
            }
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
