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
        System.out.print("Please select an option ");

        return input.nextLine().trim().toLowerCase().substring(0,1);
    }


}
