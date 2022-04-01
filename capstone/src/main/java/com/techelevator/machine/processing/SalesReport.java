package com.techelevator.machine.processing;

import com.techelevator.machine.refreshments.Refreshment;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalesReport
{
    private static final String localDate = LocalDate.now().toString();
    private static final String SALES_REPORT_PATH = "salesreport" + localDate + ".csv";
    private static File salesReportFile = new File(SALES_REPORT_PATH);

    // attributes
    private static String name;
    private static int quantity;

    // constructors
    public SalesReport(String name, int quantity)
    {
        this.name = name;
        this.quantity = quantity;
    }

    // getters and setters
    public static String getName() {
        return name;
    }

    public static int getQuantity() {
        return quantity;
    }

    // methods
    public static HashMap<String, Integer> getRefreshmentsSalesReportList(List<Refreshment> refreshments, List<Transaction> transactions)
    {
        // List<SalesReport> salesReportList = new ArrayList<>();
        HashMap<String, Integer> salesReport = new HashMap<>();

        for (Refreshment refreshment : refreshments)
        {
            int count = 0;
            for (Transaction transaction : transactions)
            {
                try
                {
                    if (transaction.getPurchasable().getName().equals(refreshment.getName()))
                    {
                        count++;
                    }
                }
                catch (NullPointerException e)
                {
                    // do nothing
                }
            }

            String key = refreshment.getName();
            int value = count;
            salesReport.put(key, value);

            //SalesReport salesReport = new SalesReport(refreshment.getName(), count);
            //salesReportList.add(salesReport);
        }

        return salesReport;
        //return salesReportList;
    }

    public static void writeSalesReport(HashMap<String, Integer> salesReport)
    {
        if(!salesReportFile.exists())
        {
            try {
                salesReportFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (
                FileWriter fileWriter = new FileWriter(salesReportFile, true);
                PrintWriter writer = new PrintWriter(fileWriter)
        )
        {
            salesReport.forEach((key, value) -> {

                String reportEntry = key + "," + value;
                writer.println(reportEntry);
            });
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
