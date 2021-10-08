package com.techelevator.view;

public class InventoryStocks {
    private String itemsSection;
    private String ItemsNames;
    private double itemsPrice;
    private String itemsTypes;
    private Integer quantityLeft;

    public String getItemsSection() {
        return itemsSection;
    }

    public String getItemsNames() {
        return ItemsNames;
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
        ItemsNames = itemsNames;
        this.itemsPrice = itemsPrice;
        this.itemsTypes = itemsTypes;
        this.quantityLeft = 5;

    }

}