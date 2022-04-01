package com.techelevator;

import com.techelevator.machine.processing.ItemReader;
import com.techelevator.machine.processing.SalesReport;
import com.techelevator.machine.refreshments.Refreshment;
import com.techelevator.ui.CashMachine;
import com.techelevator.machine.processing.Transaction;
import com.techelevator.ui.UserInput;
import com.techelevator.view.Menu;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.techelevator.machine.processing.SalesReport.getRefreshmentsSalesReportList;

public class CaTEringCapstoneCLI
{

	private static final String SOURCE_FILE_NAME = "catering.csv";
	CashMachine cashMachine = new CashMachine();
	File sourceFile = new File(SOURCE_FILE_NAME);
	List<Refreshment> refreshments = ItemReader.getRefreshments(sourceFile);
	List<Transaction> transactions = new ArrayList<Transaction>();

	private Menu menu;

	public CaTEringCapstoneCLI(Menu menu) {
		this.menu = menu;
	}

	public static void main(String[] args) {
		Menu menu = new Menu();
		CaTEringCapstoneCLI cli = new CaTEringCapstoneCLI(menu);
		cli.run();
	}

	public void run()
	{
		while (true)
		{
			String choice = UserInput.getHomeScreenChoice();
			if (choice.equals("d"))
			{
				displayItems();
				run();
			}
			else if (choice.equals("p"))
			{
				displayPurchaseMenu();
				run();
			}
			else if (choice.equals("e"))
			{
				break;
			}
			else if (choice.equals("s"))
			{
				HashMap<String, Integer> salesReport = getRefreshmentsSalesReportList(refreshments, transactions);
				SalesReport.writeSalesReport(salesReport);
			}
			else
			{
				run();
			}

			break;
		}
	}

	private void displayItems()
	{

		for (Refreshment refreshment : refreshments)
		{
			int quantity = refreshment.getQuantity();
			String stringQuantity = "";
			if (quantity > 0)
			{
				stringQuantity = String.valueOf(quantity);
			}
			else
			{
				stringQuantity = "NO LONGER AVAILABLE";
			}

			String output = refreshment.getSlotLocation() + ") " +
					refreshment.getName() + " | $" +
					refreshment.getPrice() + " | Quantity: " +
					stringQuantity;
			System.out.println(output);
		}
	}

	private void displayPurchaseMenu()
	{
		String choice = UserInput.getPurchasingScreenChoice();

		if (choice.equals("m"))
		{
			try
			{
				double input = Double.parseDouble(UserInput.getMoneyInput());
				Transaction transaction = new Transaction(true, BigDecimal.valueOf(input));
				transactions.add(transaction);
				cashMachine.putMoney(transaction);
			}
			catch (Exception e)
			{
				displayPurchaseMenu();
				return;
			}
			displayPurchaseMenu();
		}
		else if (choice.equals("s"))
		{
			try
			{
				displayItems();
				String input = UserInput.getItemSelection();
				Refreshment refreshment = ItemReader.getRefreshmentByItemLocation(cashMachine, refreshments, input);
				if (!refreshment.equals(null))
				{
					Transaction transaction = new Transaction(false, refreshment.getPrice(), refreshment);
					cashMachine.buyProduct(transaction);
					transactions.add(transaction);
					refreshment.setQuantity(refreshment.getQuantity() - 1);
				}

			}
			catch (Exception e)
			{
				displayPurchaseMenu();
				return;
			}
			displayPurchaseMenu();
		}
		else if (choice.equals("f"))
		{
			Transaction transaction = new Transaction(false, BigDecimal.valueOf(0));
			cashMachine.giveChange(transaction);
			transactions.add(transaction);
			run();
		}
		else
		{
			displayPurchaseMenu();
		}
	}
}
