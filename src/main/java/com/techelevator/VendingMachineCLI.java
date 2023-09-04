package com.techelevator;

import Exceptions.InvalidCodeException;
import Exceptions.ItemSoldOutException;
import Exceptions.NotEnoughMoneyException;
import Finance.CustomerAccount;
import Finance.Transaction;
import Items.Inventory;
import Items.Item;

import java.io.File;
import java.util.*;

public class VendingMachineCLI {

	static File inputFile = new File("main.csv");
	static Menu menu = new Menu();
	static Inventory inventory = new Inventory();

	static Map<String, Item> inventoryMap = new HashMap<>();

	static Transaction transaction;

	static CustomerAccount account = new CustomerAccount();

	static int transactionCounter = 1;


	public static void main(String[] args) {
		VendingMachineCLI cli = new VendingMachineCLI();
		//populate inventory list and map from input file
		inventory.populateInventory(inputFile, inventoryMap);
		cli.runFirstMenu();
	}

	public void runFirstMenu(){
		String option = menu.displayMainMenu();
		//options for main menu

		while (option.equals("1")) {
			//display vending machine items
			inventory.displayInventory();
			//run main menu again
			option = menu.displayMainMenu();
		}
		while (option.equals("2")) {
			//display second menu and get user input
			String secondOption = menu.displaySecondMenu(account);
			//run second menu with input
			runSecondMenu(secondOption);
		}
		System.exit(0);
	}
	public void runSecondMenu(String secondOption){
		while (secondOption.equals("1") || secondOption.equals("2") || secondOption.equals("3")) {
			if (secondOption.equals("1")) {
				//feed money and save account information
				String feedMoneyInput = menu.getFeedMoneyInput();
				account.feedMoney(feedMoneyInput);
				//display and run second menu again
				secondOption = menu.displaySecondMenu(account);
			}
			if (secondOption.equals("2")) {
				//get code from user
				inventory.displayInventory();
				String codeName = menu.getCodeNameInput();
				//attempt purchase with given code
				transaction = new Transaction(account, inventory, codeName);
				try {
					transaction.attemptPurchase(transactionCounter);
				} catch (ItemSoldOutException e) {
					transactionCounter--;
					e.errorMessage();
				} catch (NotEnoughMoneyException e) {
					transactionCounter--;
					e.errorMessage();
				} catch (InvalidCodeException e) {
					transactionCounter--;
					e.errorMessage();
				}
				inventory = transaction.getInventory();
				account = transaction.getAccount();
				transactionCounter++;

				secondOption = menu.displaySecondMenu(account);
			}
			if (secondOption.equals("3")) {
				account = transaction.giveChange(account);
				runFirstMenu();
			}
		}
	}
}
