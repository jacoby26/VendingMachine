package com.techelevator;

import com.techelevator.machine.processing.ItemReader;
import com.techelevator.machine.refreshments.*;
import com.techelevator.ui.ChangeDrawer;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class RefreshmentTests {

    String SOURCE_FILE_NAME = "catering.csv";
    File sourceFile = new File(SOURCE_FILE_NAME);
    List<Refreshment> refreshments = ItemReader.getRefreshments(sourceFile);

    @Test
    public void when_itemInStock_expect_True()
    {
        // arrange
        Refreshment refreshment = refreshments.get(0);
        boolean expected = true;
        String message = "Error - failing to detect an object that is actually present.";

        // act
        boolean actual = refreshment.inStock();

        // assert
        assertEquals(message, expected, actual);
    }

    @Test
    public void when_itemNotInStock_expect_True()
    {
        // arrange
        Refreshment refreshment = new Munchy("A6", "testmunchy", new BigDecimal(4.20), "Munchy");
        refreshment.setQuantity(0);
        boolean expected = false;
        String message = "Error - detecting an object that is not present.";

        // act
        boolean actual = refreshment.inStock();

        // assert
        assertEquals(message, expected, actual);
    }

    @Test
    public void when_itemIsMunchy_expect_MunchyPrintMessage()
    {
        // arrange
        Refreshment refreshment = new Munchy("A6", "testmunchy", new BigDecimal(4.20), "Munchy");
        String expected = "Munchy, Munchy, so Good!";
        String message = "Error - failing to display proper munchy print message.";

        // act
        String actual = refreshment.printMessage();

        // assert
        assertEquals(message, expected, actual);
    }

    @Test
    public void when_itemIsSandwich_expect_SandwichPrintMessage()
    {
        // arrange
        Refreshment refreshment = new Sandwich("A6", "testmunchy", new BigDecimal(4.20), "Sandwich");
        String expected = "Sandwich So Delicious, Yum!";
        String message = "Error - failing to display proper sandwich print message.";

        // act
        String actual = refreshment.printMessage();

        // assert
        assertEquals(message, expected, actual);
    }

    @Test
    public void when_itemIsDrink_expect_DrinkPrintMessage()
    {
        // arrange
        Refreshment refreshment = new Drink("A6", "testmunchy", new BigDecimal(4.20), "Drink");
        String expected = "Drinky, Drinky, Slurp Slurp!";
        String message = "Error - failing to display proper drink print message.";

        // act
        String actual = refreshment.printMessage();

        // assert
        assertEquals(message, expected, actual);
    }

    @Test
    public void when_itemIsDessert_expect_DessertPrintMessage()
    {
        // arrange
        Refreshment refreshment = new Dessert("A6", "testmunchy", new BigDecimal(4.20), "Dessert");
        String expected = "Sugar, Sugar, so Sweet!";
        String message = "Error - failing to display proper drink print message.";

        // act
        String actual = refreshment.printMessage();

        // assert
        assertEquals(message, expected, actual);
    }
}
