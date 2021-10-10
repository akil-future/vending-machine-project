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

    //A1, B1 , C1, ETC
    public String getItemsNames() {
        return itemsNames;
    }

    //CRISPY CHIPS
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

    public double purchaseItem() {
        quantityLeft--;
        return itemsPrice;
    }

    public String getDisplayMessage() {
        String displayMessage = "";
        if (itemsTypes.equals("Chip")) {
            displayMessage = "Crunch Crunch, Yum!";
        } else if (itemsTypes.equals("Candy")) {
            displayMessage = "Munch Munch, Yum!";
        } else if (itemsTypes.equals("Drink")) {
            displayMessage = "Glug Glug, Yum!";
        } else if (itemsTypes.equals("Gum")) {
            displayMessage = "Chew Chew, Yum!";
        }
        return displayMessage;
    }
}