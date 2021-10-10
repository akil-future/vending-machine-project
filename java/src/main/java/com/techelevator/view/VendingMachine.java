package com.techelevator.view;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class VendingMachine {
    private Scanner in;
    private List<InventoryStocks> priceItems = new ArrayList<>();
    private BigDecimal currentBalance = new BigDecimal("0.00");
    private int insertedMoney;

    public BigDecimal getCurrentBalance() {
        return currentBalance.setScale(2, RoundingMode.HALF_UP);
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public VendingMachine(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public int getInsertedMoney(int money) {
        return insertedMoney;
    }

    public VendingMachine(int insertedMoney) {
        this.insertedMoney = insertedMoney;
    }

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

    public InventoryStocks getItem(String input) {
        InventoryStocks item = null;
        for (InventoryStocks itemList : priceItems) {
            if (itemList.getItemsSection().equalsIgnoreCase(input)) {
                item = itemList;
            }
        }
        return item;
    }

    public boolean isValidKeys(String validKeys) {
        boolean isValid = false;
        for (InventoryStocks keys : priceItems) {
            if (keys.getItemsSection().equalsIgnoreCase(validKeys)) {
                isValid = true;
            }
        }
        return isValid;
    }


    public BigDecimal feedMoney(Integer validMoney) {
        BigDecimal bigDecimal = BigDecimal.valueOf(validMoney);
        if (validMoney == 1 || validMoney == 2 || validMoney == 5 ||
                validMoney == 10) {
            currentBalance = currentBalance.add(bigDecimal);
            printLog("Feed Money", BigDecimal.valueOf(validMoney));
        }else {
            System.out.println("\nSorry, you've entered an invalid $ amount.");
        }
        return currentBalance;
    }

    public void printLog(String action, BigDecimal transactionAmount) {
        File log = new File("Log.txt");
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(log, true))){
            LocalDateTime dateObject = LocalDateTime.now();
            DateTimeFormatter formatObject = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a ");
            String date = dateObject.format(formatObject);
            writer.println(date + action + " " + currentBalance
                    + " " + currentBalance.add(transactionAmount).setScale(2, RoundingMode.HALF_UP) );
        } catch(FileNotFoundException e) {
            System.out.println("File doesn't exist.");
        }
    }

    public String getChange() {
        BigDecimal change = currentBalance;
        BigDecimal total = currentBalance;
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;

        while (change.compareTo(BigDecimal.ZERO) > 0) {
            if (change.compareTo(BigDecimal.valueOf(.25)) >= 0) {
                change = change.subtract(BigDecimal.valueOf(.25)).setScale
                        (2, RoundingMode.HALF_UP);
                quarters++;
            } else if (change.compareTo(BigDecimal.valueOf(.1)) >= 0) {
                change = change.subtract(BigDecimal.valueOf(.1)).setScale
                        (2, RoundingMode.HALF_UP);
                dimes++;
            } else if (change.compareTo(BigDecimal.valueOf(.05)) >= 0) {
                change = change.subtract(BigDecimal.valueOf(.05)).setScale
                        (2, RoundingMode.HALF_UP);
                nickels++;
            }
        }
        printLog("Give Change", total.negate());
        return "Total change: $" + total.setScale(2,RoundingMode.HALF_UP) + " / " + quarters +
                " quarters, " + dimes + " dimes," + " " + nickels + " " + "nickels.";
    }
}