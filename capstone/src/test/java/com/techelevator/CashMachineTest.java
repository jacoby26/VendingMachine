package com.techelevator;

import com.techelevator.machine.processing.Transaction;
import com.techelevator.ui.CashMachine;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CashMachineTest {

    @Test
    public void when_MoneyIsAdded_balanceIsIncreased()
    {

        //arrange
        Transaction transaction = new Transaction(true, new BigDecimal(1));
        CashMachine cashMachine = new CashMachine();
        BigDecimal expected = new BigDecimal(1);
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
        Transaction transaction = new Transaction(false, new BigDecimal(1));
        CashMachine cashMachine = new CashMachine();
        BigDecimal expected = new BigDecimal(0);
        String message = "Added money to cash machine with an invalid put money transaction";

        //act
        BigDecimal actual = cashMachine.putMoney(transaction);

        //assert
        assertEquals(message, expected, actual);


    }

    @Test
    public void when_invalidPurchase_Balance_IsNoChange()
    {

        //arrange
        Transaction transaction = new Transaction(true, new BigDecimal(10));
        CashMachine cashMachine = new CashMachine();
        cashMachine.putMoney(transaction);
        BigDecimal expected = new BigDecimal(10);
        String message = "Added money to cash machine with an invalid put money transaction";

        //act
        transaction = new Transaction();

        //assert
        assertEquals(message, expected, actual);


    }
}
