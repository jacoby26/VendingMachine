package com.techelevator;

import com.techelevator.machine.processing.ItemReader;
import com.techelevator.machine.refreshments.Refreshment;
import com.techelevator.ui.ChangeDrawer;
import com.techelevator.ui.Transaction;
import com.techelevator.ui.UserInput;
import com.techelevator.view.Menu;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CaTEringCapstoneCLI
{

	private static final String sourceFileName = "catering.csv";
	ChangeDrawer changeDrawer = new ChangeDrawer();
	List<Refreshment> refreshments = new ArrayList<Refreshment>();

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
			else
			{
				run();
			}

			break;
		}
	}

	private void displayItems()
	{
		File sourceFile = new File(sourceFileName);
		refreshments = ItemReader.getRefreshments(sourceFile);

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
					refreshment.getPrice() + " | " +
					refreshment.getType() + " | Quantity: " +
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
				changeDrawer.putMoney(transaction);
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
				String input = UserInput.getItemSelection();

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
			changeDrawer.giveChange(transaction);
			run();
		}
		else
		{
			displayPurchaseMenu();
		}
	}
}
