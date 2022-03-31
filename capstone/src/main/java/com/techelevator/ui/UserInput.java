package com.techelevator.ui;

import java.util.Scanner;

public class UserInput
{
    private static final Scanner input = new Scanner(System.in);


    public static String getHomeScreenChoice()
    {
        System.out.println();
        System.out.println("(D) Display caTEring Items");
        System.out.println("(P) Purchase");
        System.out.println("(E) Exit");
        System.out.println();
        System.out.print("Please select an option ");

        return input.nextLine().trim().toLowerCase().substring(0,1);
    }

    public static String getPurchasingScreenChoice()
    {
        System.out.println();
        System.out.println("(M) Feed Money ");
        System.out.println("(S) Select Item ");
        System.out.println("(F) Finish Transaction ");
        System.out.println();
        System.out.println("Your current balance is: " + String.valueOf(ChangeDrawer.getBalance()));
        System.out.println();
        System.out.print("Please select an option ");

        return input.nextLine().trim().toLowerCase().substring(0,1);
    }

    public static String getMoneyInput()
    {
        System.out.println();
        System.out.println("Enter a valid cash increment - 0.01, 0.05, 0.10, 0.25, 1, 2, 5, 10, or 20: ");

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
            return userEntry;
        }
        else
        {
            return "Invalid dollar amount.";
        }
    }

    public static String getItemSelection()
    {
        System.out.println();
        System.out.println("Enter the slot location of the item you would like to purchase. ");

        return input.nextLine().trim().toLowerCase().substring(0,2);
    }

}
