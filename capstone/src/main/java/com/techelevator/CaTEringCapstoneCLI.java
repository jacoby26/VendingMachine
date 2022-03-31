package com.techelevator;

import com.techelevator.machine.ItemReader;
import com.techelevator.machine.Refreshment;
import com.techelevator.ui.UserInput;
import com.techelevator.view.Menu;

import java.io.File;
import java.util.List;

public class CaTEringCapstoneCLI
{

	private static final String sourceFileName = "catering.csv";


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
			}
		}
	}

	private void displayItems()
	{
		File sourceFile = new File(sourceFileName);
		List<Refreshment> refreshments = ItemReader.getRefreshments(sourceFile);
		while (true)
		{
			for (Refreshment refreshment: refreshments)
			{
				String output = refreshment.getSlotLocation() + ") " +
						refreshment.getName() + " $" +
						refreshment.getPrice() + " " +
						refreshment.getType();
				System.out.println(output);
			}

		}
	}
}
