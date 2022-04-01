package com.techelevator;

import com.techelevator.machine.processing.ItemReader;
import com.techelevator.machine.refreshments.Munchy;
import com.techelevator.machine.refreshments.Refreshment;
import com.techelevator.ui.ChangeDrawer;
import com.techelevator.ui.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ItemReaderTests {

    String SOURCE_FILE_NAME = "catering.csv";
    ChangeDrawer changeDrawer = new ChangeDrawer();
    File sourceFile = new File(SOURCE_FILE_NAME);
    List<Refreshment> refreshments = ItemReader.getRefreshments(sourceFile);

    @Test
    public void when_RefreshmentNotExist_expect_Null()
    {
        // arrange
        String slotLocation = "ZZ";
        Refreshment expected = null;
        String message = "Error - finding an item where no slot machine location exists";

        // act
        Refreshment actual = ItemReader.getRefreshmentByItemLocation(changeDrawer, refreshments, slotLocation);

        // assert
        assertEquals(message, expected, actual);
    }

    @Test
    public void when_RefreshmentQuantity0_expect_Null()
    {
        // arrange
        String slotLocation = "A6";
        Refreshment refreshment = new Munchy(slotLocation, "testmunchy", new BigDecimal(4.20), "Munchy");
        refreshments.add(refreshment);
        refreshment.setQuantity(0);
        Refreshment expected = null;
        String message = "Error - finding an item when item is no longer available";

        // act
        Refreshment actual = ItemReader.getRefreshmentByItemLocation(changeDrawer, refreshments, slotLocation);

        // assert
        assertEquals(message, expected, actual);
    }

    @Test
    public void when_NotEnoughMoney_expect_Null()
    {
        // arrange
        String slotLocation = "A6";
        Refreshment refreshment = new Munchy(slotLocation, "testmunchy", new BigDecimal(4.20), "Munchy");
        refreshments.add(refreshment);
        Refreshment expected = null;
        String message = "Error - successfully purchasing an item when changeDrawer doesn't have enough moeny";

        // act
        Refreshment actual = ItemReader.getRefreshmentByItemLocation(changeDrawer, refreshments, slotLocation);

        // assert
        assertEquals(message, expected, actual);
    }

    @Test
    public void when_EnoughMoneyAndRefreshmentFoundAndInStock_expect_Refreshment()
    {
        // arrange
        String slotLocation = "A6";
        Refreshment refreshment = new Munchy(slotLocation, "testmunchy", new BigDecimal(4.20), "Munchy");
        refreshments.add(refreshment);
        Transaction transaction = new Transaction(true, new BigDecimal(5));
        changeDrawer.putMoney(transaction);
        Refreshment expected = refreshment;
        String message = "Error - failing to purchase an item when all needed conditions are met";

        // act
        Refreshment actual = ItemReader.getRefreshmentByItemLocation(changeDrawer, refreshments, slotLocation);

        // assert
        assertEquals(message, expected, actual);
    }

    @Test
    public void when_blankFile_expect_noRefreshments()
    {
        // arrange
        File blankFile = new File("blankfile.csv");

        if(!blankFile.exists()) {
            try {
                blankFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        List<Refreshment> expected = new ArrayList<Refreshment>();
        String message = "Error - finding a list of refreshments when no refreshments exist";

        // act
        List<Refreshment> actual = ItemReader.getRefreshments(blankFile);

        // assert
        assertEquals(message, expected, actual);
    }

    @Test
    public void when_ValidFile_expect_Refreshments()
    {
        // arrange
        File refreshmentsFile = new File(SOURCE_FILE_NAME);

        if(!refreshmentsFile.exists()) {
            try {
                refreshmentsFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        List<Refreshment> expectedRefreshments = refreshments;
        boolean expected = true;
        String message = "Error - finding no refreshments when a valid list of refreshments exist";

        // act
        List<Refreshment> actualRefreshments = ItemReader.getRefreshments(sourceFile);
        boolean actual = true;

        for (int i = 0; i < actualRefreshments.size(); i++) {
            if(actualRefreshments.get(i).getName() != refreshments.get(i).getName())
            {
                actual = false;
            }
        }

        // assert
        assertEquals(message, expected, actual);
    }
}
