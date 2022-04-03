package com.techelevator.ui;

import com.techelevator.view.ColorDesignator;

import java.awt.*;
import java.util.Scanner;

public class UserInput
{
    private static final Scanner input = new Scanner(System.in);


    public static String getHomeScreenChoice() {
        System.out.println();
        System.out.println(ColorDesignator.Blue + "|-----------------------------------|" + ColorDesignator.Reset);
        System.out.println(ColorDesignator.Yellow + "\t" + "\t" + "\t" + "  " + "Main Menu" + ColorDesignator.Reset);
        System.out.println(ColorDesignator.Blue + "|-----------------------------------|" + ColorDesignator.Reset);
        System.out.println();
        System.out.println(ColorDesignator.Green +"(D) " + ColorDesignator.Reset +  "Display caTEring Items");
        System.out.println(ColorDesignator.Green +"(P) " + ColorDesignator.Reset +  "Purchase");
        System.out.println(ColorDesignator.Green +"(E) " + ColorDesignator.Reset +  "Exit");
        System.out.println();
        System.out.print(ColorDesignator.Yellow + "Please select an option " + ColorDesignator.Reset);

        try
        {
            return input.nextLine().trim().toLowerCase().substring(0, 1);
        }
        catch (Exception e)
        {
            return "";
        }
    }

    public static String getPurchasingScreenChoice()
    {
        System.out.println();
        System.out.println(ColorDesignator.Blue + "|-----------------------------------|" + ColorDesignator.Reset);
        System.out.println(ColorDesignator.Yellow + "\t" + "\t" + "  " + "Purchase Products" + ColorDesignator.Reset);
        System.out.println(ColorDesignator.Blue + "|-----------------------------------|" + ColorDesignator.Reset);
        System.out.println();
        System.out.println(ColorDesignator.Cyan +"(M) " + ColorDesignator.Reset + "Feed Money ");
        System.out.println(ColorDesignator.Cyan +"(S) " + ColorDesignator.Reset + "Select Item ");
        System.out.println(ColorDesignator.Cyan +"(F) " + ColorDesignator.Reset + "Finish Transaction ");
        System.out.println();
        System.out.println(ColorDesignator.Purple + "Your current balance is: " + ColorDesignator.Reset + ColorDesignator.Green + "$" + String.valueOf(CashMachine.getBalance()) + ColorDesignator.Reset);
        System.out.println();
        System.out.print(ColorDesignator.Yellow + "Please select an option " + ColorDesignator.Reset);

        try
        {
            return input.nextLine().trim().toLowerCase().substring(0,1);
        }
        catch (Exception e)
        {
            return "";
        }
    }

    public static String getMoneyInput()
    {
        System.out.println();
        System.out.println("Enter an accepted cash tender");
        System.out.println();
        System.out.println(ColorDesignator.Purple + "Change :" + ColorDesignator.Reset + ColorDesignator.Green + " 0.01, 0.05, 0.10, 0.25 " + ColorDesignator.Reset);
        System.out.println(ColorDesignator.Purple + "Dollar :" + ColorDesignator.Reset + ColorDesignator.Green + " 1, 5, 10, or 20 " + ColorDesignator.Reset);

        String userEntry = input.nextLine().replaceAll("[$]", "");
        if(userEntry.equals("0.01")
                || userEntry.equals("0.05")
                || userEntry.equals("0.10")
                || userEntry.equals("0.25")
                || userEntry.equals("1")
                || userEntry.equals("5")
                || userEntry.equals("10")
                || userEntry.equals("20"))
        {
            System.out.println();
            return userEntry;
        }
        else
        {
            System.out.println();
            return "Invalid dollar amount.";
        }
    }

    public static String getItemSelection()
    {
        System.out.println();
        System.out.println(ColorDesignator.Yellow + "Enter the slot location of the item you would like to purchase. " + ColorDesignator.Reset);

        try
        {
            return input.nextLine().trim().toLowerCase().substring(0,2);
        }
        catch (Exception e)
        {
            return "";
        }
    }

}
