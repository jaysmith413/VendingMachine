package com.techelevator;

import Finance.CustomerAccount;
import Items.Inventory;

import java.util.Scanner;

public class Menu {

    private Scanner userInput = new Scanner(System.in);

    public String displayMainMenu(){
        System.out.println("Welcome to the vending Machine");
        System.out.println("Please choose from the menu");
        System.out.println("AUGUST ONLY BOGODO SALE: buy one, get one dollar off any second item");
        System.out.println(1+ ") Display Vending Machine Items\n" +
                2 +") Purchase\n" +
                3 +  ") Exit");
        return userInput.nextLine();
    }

    public String displaySecondMenu(CustomerAccount account){
        System.out.println("Current money provided: $" + account.getCurrentAmount());
        System.out.println();
        System.out.println("(1) Feed Money\n" +
                "(2) Select Product\n" +
                "(3) Finish Transaction");
        return userInput.nextLine();
    }
    public String getCodeNameInput(){

        System.out.println("Please enter a code: ");
        return userInput.nextLine();
    }

    public String getFeedMoneyInput(){
        System.out.println("How much do you want to feed? ");
        return userInput.nextLine();
    }

}
