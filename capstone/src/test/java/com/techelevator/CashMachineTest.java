package com.techelevator;

import com.techelevator.interfaces.Purchasable;
import com.techelevator.machine.processing.ItemReader;
import com.techelevator.machine.processing.Transaction;
import com.techelevator.machine.refreshments.Munchy;
import com.techelevator.machine.refreshments.Refreshment;
import com.techelevator.ui.CashMachine;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class CashMachineTest {

    @Test
    public void when_MoneyIsAdded_balanceIsIncreased()
    {

        //arrange
        double initial = 1;
        Transaction transaction = new Transaction(true, new BigDecimal(initial));
        CashMachine cashMachine = new CashMachine();
        BigDecimal expected = new BigDecimal(initial);
        String message = "Failed to add money to cash machine with a valid put money transaction";

        //act
        BigDecimal actual = cashMachine.putMoney(transaction);

        //assert
        assertEquals(message, expected, actual);

    }
    @Test
    public void when_invalidPutMoneyIsAdded_balanceIsNoChange()
    {

        //arrange
        double initial = 1;
        Transaction transaction = new Transaction(false, new BigDecimal(initial));
        CashMachine cashMachine = new CashMachine();
        BigDecimal expected = new BigDecimal(0);
        String message = "Added money to cash machine with an invalid put money transaction";

        //act
        BigDecimal actual = cashMachine.putMoney(transaction);

        //assert
        assertEquals(message, expected, actual);


    }

    @Test
    public void when_transactionIsMoneyIn_Balance_IsNoChange()
    {

        //arrange
        double initial = 10;
        Transaction transaction = new Transaction(true, new BigDecimal(initial));
        CashMachine cashMachine = new CashMachine();
        cashMachine.putMoney(transaction);
        BigDecimal expected = new BigDecimal(10);
        String message = "Changed balance on an invalid purchase.";

        //act
        transaction = new Transaction(false, new BigDecimal(initial));
        BigDecimal actual = cashMachine.buyProduct(transaction);

        //assert
        assertEquals(message, expected, actual);
    }

    @Test
    public void when_isNoPurchasable_Balance_IsNoChange()
    {
        //arrange
        double initial = 4;
        Transaction transaction = new Transaction(true, new BigDecimal(initial));
        CashMachine cashMachine = new CashMachine();
        cashMachine.putMoney(transaction);
        BigDecimal expected = new BigDecimal(initial);
        String message = "Changed balance on a purchase with not enough money to make purchase";

        //act
        transaction = new Transaction(false, new BigDecimal(initial));
        BigDecimal actual = cashMachine.buyProduct(transaction);

        //assert
        assertEquals(message, expected, actual);
    }

    @Test
    public void when_notEnoughBalance_Balance_IsNoChange()
    {
        //arrange
        double initial = 4;
        Refreshment refreshment = new Munchy("A6", "testmunchy", new BigDecimal(4.20), "Munchy");
        Transaction transaction = new Transaction(true, new BigDecimal(initial));
        CashMachine cashMachine = new CashMachine();
        cashMachine.putMoney(transaction);
        BigDecimal expected = new BigDecimal(initial);
        String message = "Changed balance on a purchase with not enough money to make purchase";

        //act
        transaction = new Transaction(false, refreshment.getPrice(), refreshment);
        BigDecimal actual = cashMachine.buyProduct(transaction);

        //assert
        assertEquals(message, expected, actual);
    }

    @Test
    public void when_ValidPurchase_Balance_Change()
    {
        //arrange
        double initial = 10;
        Refreshment refreshment = new Munchy("A6", "testmunchy", new BigDecimal(4.20), "Munchy");
        Transaction transaction = new Transaction(true, new BigDecimal(initial));
        CashMachine cashMachine = new CashMachine();
        cashMachine.putMoney(transaction);
        BigDecimal expected = new BigDecimal(initial).subtract(refreshment.getPrice());
        String message = "Changed balance on a purchase with not enough money to make purchase";

        //act
        transaction = new Transaction(false, refreshment.getPrice(), refreshment);
        BigDecimal actual = cashMachine.buyProduct(transaction);

        //assert
        assertEquals(message, expected, actual);
    }

    @Test
    public void when_transactionIsNotMoneyInAndNoDollarAmount_expect_GiveChange()
    {
        // arrange
        double initial = 10;
        Transaction transaction = new Transaction(true, BigDecimal.valueOf(initial));
        CashMachine cashMachine = new CashMachine();
        cashMachine.putMoney(transaction);
        BigDecimal expected = BigDecimal.valueOf(initial);
        String message = "Failed to provide proper change with valid giveChange transaction";

        // act
        transaction = new Transaction(false, BigDecimal.valueOf(0));
        BigDecimal actual = cashMachine.giveChange(transaction);

        // assert
        assertEquals(message, expected, actual);
    }

    @Test
    public void when_transactionIsMoneyInAndNoDollarAmount_expect_NoChange()
    {
        // arrange
        double initial = 10;
        Transaction transaction = new Transaction(true, BigDecimal.valueOf(initial));
        CashMachine cashMachine = new CashMachine();
        cashMachine.putMoney(transaction);
        BigDecimal expected = BigDecimal.valueOf(0);
        String message = "Gave change on an invalid giveChange transaction";

        // act
        transaction = new Transaction(true, BigDecimal.valueOf(0));
        BigDecimal actual = cashMachine.giveChange(transaction);

        // assert
        assertEquals(message, expected, actual);
    }

    @Test
    public void when_transactionIsMoneyInAndDollarAmount_expect_NoChange()
    {
        // arrange
        double initial = 10;
        Transaction transaction = new Transaction(true, BigDecimal.valueOf(initial));
        CashMachine cashMachine = new CashMachine();
        cashMachine.putMoney(transaction);
        BigDecimal expected = BigDecimal.valueOf(0);
        String message = "Gave change on an invalid giveChange transaction";

        // act
        transaction = new Transaction(true, BigDecimal.valueOf(5));
        BigDecimal actual = cashMachine.giveChange(transaction);

        // assert
        assertEquals(message, expected, actual);
    }

    @Test
    public void when_PendingGreaterThanBalance_expect_NoChange()
    {
        // arrange
        double initial = 10;
        Transaction transaction = new Transaction(true, BigDecimal.valueOf(initial));
        CashMachine cashMachine = new CashMachine();
        cashMachine.putMoney(transaction);
        BigDecimal expected = BigDecimal.valueOf(0);
        String message = "Gave change on an invalid giveChange transaction";

        // act
        transaction = new Transaction(true, BigDecimal.valueOf(0));
        BigDecimal actual = cashMachine.giveChange(transaction);

        // assert
        assertEquals(message, expected, actual);
    }
}
