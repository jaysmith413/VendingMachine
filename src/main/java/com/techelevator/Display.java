package com.techelevator;

import Items.Item;

import java.math.BigDecimal;

public class Display {


    public static void itemPriceBalance(boolean isDiscounted, Item purchaseItem){
        if(isDiscounted == false){
            System.out.println(purchaseItem.getItemName() + " $" + purchaseItem.getItemPrice());
        } else{
            System.out.println(purchaseItem.getItemName() + " $" + (purchaseItem.getItemPrice().subtract(BigDecimal.ONE)));
        }
    }
    public static void purchaseMessage(Item eachItem){
        if (eachItem.getItemType().equals("Drink")) {
            System.out.println("Glug Glug, Yum!");
        } else if (eachItem.getItemType().equals("Munchy")) {
            System.out.println("Crunch Crunch, Yum!");
        } else if (eachItem.getItemType().equals("Gum")) {
            System.out.println("Chew Chew, Yum!");
        } else if (eachItem.getItemType().equals("Candy")) {
            System.out.println("Yummy Yummy, So Sweet!");
        }
    }
    public static void change(int quarters, int dimes, int nickels){
        System.out.println("Your change includes " + quarters + " quarters, " + dimes + " dimes, and "
                + nickels + " nickels.");
        System.out.println();
    }
}
