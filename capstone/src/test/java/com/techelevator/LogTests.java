package com.techelevator;

import com.techelevator.machine.processing.Log;
import com.techelevator.machine.processing.Transaction;
import com.techelevator.machine.refreshments.Munchy;
import com.techelevator.machine.refreshments.Refreshment;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;

public class LogTests {
    private static final String AUDIT_FILE_PATH = "auditlog.csv";
    private File auditFile = new File(AUDIT_FILE_PATH);

    @Test
    public void when_LogFileCreated_expect_True()
    {
        // arrange
        Refreshment refreshment = new Munchy("A6", "testmunchy", new BigDecimal(0), "Munchy");
        Transaction transaction = new Transaction(false, refreshment.getPrice(), refreshment);
        Log log = new Log(transaction, new BigDecimal(0));
        String message = "Oops! Log file does not exist";

        // act
        log.writeLogEntry();

        // assert
        Assert.assertTrue(auditFile.exists());
    }
}
