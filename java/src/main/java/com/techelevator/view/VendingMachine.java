package com.techelevator.view;

import com.techelevator.VendingMachineCLI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class VendingMachine {
    private  Scanner in;
    private List<InventoryStocks> priceItems = new ArrayList<>();
    private int [] purchase=new int[2];

    public VendingMachine() throws FileNotFoundException {
        File itemsInventory = new File("vendingmachine.csv");
        Scanner userInput = new Scanner(itemsInventory);
        while (userInput.hasNextLine()) {
            String displayItems = userInput.nextLine();
            String[] array = displayItems.split("\\|");
            InventoryStocks item = new InventoryStocks(array[0], array[1], Double.parseDouble(array[2]), array[3]);
            priceItems.add(item);
        }
    }
    public List<InventoryStocks> getPriceItems() {
        return priceItems;
    }

}