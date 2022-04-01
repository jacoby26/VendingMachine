package com.techelevator;

import com.techelevator.machine.processing.Log;
import com.techelevator.machine.processing.SalesReport;
import com.techelevator.machine.processing.Transaction;
import com.techelevator.machine.refreshments.Munchy;
import com.techelevator.machine.refreshments.Refreshment;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SalesReportTest {

    private static final String SALES_REPORT_PATH = "salesreport.csv";
    private File salesReportFile = new File(SALES_REPORT_PATH);

    @Test
    public void when_SalesReportFileCreated_expect_True()
    {
        // arrange
        Refreshment refreshment = new Munchy("A6", "testmunchy", new BigDecimal(0), "Munchy");
        List<Refreshment> refreshments = new ArrayList<>();
        refreshments.add(refreshment);

        Transaction transaction = new Transaction(false, refreshment.getPrice(), refreshment);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        String message = "Oops! Log file does not exist";

        // act
        List<SalesReport> salesReportList = SalesReport.getRefreshmentsSalesReportList(refreshments, transactions);
        SalesReport.writeSalesReport(salesReportList);

        // assert
        Assert.assertTrue(salesReportFile.exists());
    }
}
