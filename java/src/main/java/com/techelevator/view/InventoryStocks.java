package com.techelevator.view;

import java.math.BigDecimal;

public class InventoryStocks {
    private String itemsSection;
    private String itemsNames;
    private double itemsPrice;
    private String itemsTypes;
    private Integer quantityLeft;

    public InventoryStocks() {
    }

    public String getItemsSection() {
        return itemsSection;
    }

    public String getItemsNames() {
        return itemsNames;
    }

    public double getItemsPrice() {
        return itemsPrice;
    }

    public String getItemsTypes() {
        return itemsTypes;
    }

    public Integer getQuantityLeft() {
        return quantityLeft;
    }


    public InventoryStocks(String itemsSection, String itemsNames, double itemsPrice, String itemsTypes) {
        this.itemsSection = itemsSection;
        this.itemsNames = itemsNames;
        this.itemsPrice = itemsPrice;
        this.itemsTypes = itemsTypes;
        this.quantityLeft = 5;

    }

    public String purchaseItem() {
        quantityLeft--;
        return getItemsNames() + " $" + getItemsPrice() +
                "\n" + getDisplayMessage();
    }

    public String getDisplayMessage() {
        String displayMessage = "";
        if (itemsTypes.equals("Chip")) {
            displayMessage = "Crunch Crunch, Yum! Please enjoy your snack!";
        } else if (itemsTypes.equals("Candy")) {
            displayMessage = "Munch Munch, Yum! Please enjoy your snack!";
        } else if (itemsTypes.equals("Drink ")) {
            displayMessage = "Glug Glug, Yum! Please enjoy your drink!";
        } else if (itemsTypes.equals("Gum")) {
            displayMessage = "Chew Chew, Yum! Please enjoy your gum!";
        }
        return displayMessage;
    }
}