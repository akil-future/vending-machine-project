package com.techelevator;

import com.techelevator.view.InventoryStocks;
import com.techelevator.view.Menu;
import com.techelevator.view.VendingMachine;
import jdk.swing.interop.SwingInterOpUtils;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE};
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY,
			                         PURCHASE_MENU_OPTION_SELECT_PRODUCT,
			                         PURCHASE_MENU_OPTION_FINISH_TRANSACTION};
     Purchase purchase= new Purchase();
	Scanner userInput= new Scanner(System.in);
	private Menu menu;
	VendingMachine vendingMachine = new VendingMachine();

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
				System.out.println(purchase.getCurrentBalance());
				String choice= (String)menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
				if( choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
					System.out.println(" Please enter a valid dollar amount $:");
           			int money= Integer.parseInt(userInput.nextLine());
           			purchase.feedMoney(money);
				} else if( choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
					displayItems();
					System.out.println(" Please choose the items you need!");

				}else if(choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
					break;
				}

			}

			}

		}


