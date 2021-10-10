package com.techelevator;

import com.techelevator.view.InventoryStocks;
import com.techelevator.view.Menu;
import com.techelevator.view.VendingMachine;
import jdk.swing.interop.SwingInterOpUtils;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
									MAIN_MENU_OPTION_EXIT};
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY,
			                         PURCHASE_MENU_OPTION_SELECT_PRODUCT,
			                         PURCHASE_MENU_OPTION_FINISH_TRANSACTION};


	Scanner userInput= new Scanner(System.in);
	private Menu menu;
	VendingMachine vendingMachine = new VendingMachine();
	//InventoryStocks inventoryStocks = new InventoryStocks();


	public VendingMachineCLI(Menu menu) throws FileNotFoundException {
		this.menu = menu;
		this.vendingMachine = new VendingMachine();
	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				displayItems();
				// display vending machine items
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				displayPurchaseMenu();
				// do purchase
			} else if(choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.exit(1);
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}

	public void displayItems() {
		for (InventoryStocks item : vendingMachine.getPriceItems()) {
			System.out.println(item.getItemsSection() + "|" + item.getItemsNames() + "|"
					+ item.getItemsTypes() + "|" + item.getItemsPrice()
					+ "|" + item.getQuantityLeft());
		}
	}
	public void displayPurchaseMenu(){
			while (true) {
				System.out.println("\nCurrent Balance is: $" + vendingMachine.getCurrentBalance());
				String choice= (String)menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

				if(choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
					System.out.println("This machine only accepts $1 | $2 | $5 | $10");
					System.out.print("Please enter a valid dollar amount: $");
           			int money= Integer.parseInt(userInput.nextLine());
					System.out.println(vendingMachine.feedMoney(money));

				} else if(choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
					displayItems();
					System.out.println("Please choose the items you need!");
					String selectedItems = userInput.nextLine();
					while(!vendingMachine.isValidKeys(selectedItems)) {
						System.out.println("Invalid key. Try again.");
						selectedItems = userInput.nextLine();
					} if(vendingMachine.isValidKeys(selectedItems)) {
						InventoryStocks item = vendingMachine.getItem(selectedItems);
						if(item == null) {
							System.out.println("Item does not exist.");
						} else {
							if (item.getQuantityLeft() > 0) {
								System.out.println("Item is available.");
								System.out.println(vendingMachine.dispenseItems(selectedItems));
								if(vendingMachine.getCurrentBalance().compareTo(new BigDecimal(item.getItemsPrice()))
										>= 0) {
									double purchasePrice = item.purchaseItem();
									vendingMachine.printLog(item.getItemsNames()+ " " +
											item.getItemsSection(), new BigDecimal(item.getItemsPrice()).negate());
									vendingMachine.setCurrentBalance
											(vendingMachine.getCurrentBalance().subtract
													(new BigDecimal(purchasePrice)));
								} else {
									System.out.println("Insufficient funds.");
								}
							} else {
								System.out.println("SOLD OUT.");
							}
						}
					}

				}else if(choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
					System.out.println(vendingMachine.getChange());
					break;
				}
			}
			}

		}


