package com.techelevator.machine.processing;

import com.techelevator.machine.refreshments.*;
import com.techelevator.ui.ChangeDrawer;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ItemReader {
    // attributes
    private String sourceFile;

    // constructors

    // getters and setters

    // methods
    public static Refreshment getRefreshmentByItemLocation(ChangeDrawer changeDrawer, List<Refreshment> refreshments, String itemLocation)
    {
        for(Refreshment refreshment : refreshments)
        {
            if(refreshment.getSlotLocation().equalsIgnoreCase(itemLocation))
            {
                if(refreshment.inStock())
                {
                    if(changeDrawer.balance.compareTo(refreshment.getPrice()) >= 0)
                    {
                        return refreshment;
                    }
                    System.out.println("Sorry, you don't have enough money to purchase this item. Please try again.");
                    return null;
                }

                System.out.println("Item at " + itemLocation + " is no longer available.");
                return null;
            }
        }
        System.out.println("Item does not exist. ");
        return null;
    }

    public static List<Refreshment> getRefreshments(File sourceFile)
    {
        List<Refreshment> refreshments = new ArrayList<Refreshment>();

        try(Scanner fileScanner = new Scanner(sourceFile))
        {
            while(fileScanner.hasNextLine())
            {
                try {
                    String line = fileScanner.nextLine();
                    String[] properties = line.split(",");

                    String slotLocation = properties[0];
                    String itemName = properties[1];
                    String type = properties[2];
                    String stringPrice = properties[3];
                    BigDecimal price = new BigDecimal(stringPrice);



//                    Object object = new Object();
//
//                    Class<?> getType;
//                    try {
//                        getType = Class.forName(type);
//                        if (type.getClass().isAssignableFrom(getType))
//                        {
//                            getType.cast(object);
//
//                            if (getType.isInstance())
//                            {
//                                getType.getConstructors();
//                            }
//                        }
//                        else
//                        {
//                            throw new IllegalArgumentException();
//                        }
//                    } catch (ClassNotFoundException e)
//                    {
//                        e.printStackTrace();
//                    }


                    if (type.equals("Munchy"))
                    {
                        Munchy munchy = new Munchy(slotLocation, itemName, price, type);
                        refreshments.add(munchy);
                    }
                    else if (type.equals("Sandwich"))
                    {
                        Sandwich sandwich = new Sandwich(slotLocation, itemName, price, type);
                        refreshments.add(sandwich);
                    }
                    else if (type.equals("Drink"))
                    {
                        Drink drink = new Drink(slotLocation, itemName, price, type);
                        refreshments.add(drink);
                    }
                    else if (type.equals("Dessert"))
                    {
                        Dessert dessert = new Dessert(slotLocation, itemName, price, type);
                        refreshments.add(dessert);
                    }
                    else
                    {
                        // do nothing
                    }
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return refreshments;
    }
}
