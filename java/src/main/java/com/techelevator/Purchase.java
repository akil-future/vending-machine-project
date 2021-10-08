package com.techelevator;

import java.math.BigDecimal;

public class Purchase {
    private double insertedMoney;
   private String Product;
   private BigDecimal currentBalance;

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setInsertedMoney(double insertedMoney) {
        this.insertedMoney = insertedMoney;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public Purchase() {
        this.currentBalance=BigDecimal.ZERO;
    }

    public double getInsertedMoney() {
        return insertedMoney;
    }

    public String getProduct() {
        return Product;
    }
    public void feedMoney(int validMoney) {
        BigDecimal bigDecimal= BigDecimal.valueOf(validMoney);
        if(validMoney==1|| validMoney==2 || validMoney==5|| validMoney==10) {
            currentBalance=currentBalance.add(bigDecimal);
        } else {
            System.out.println(" Please enter a valid dollar amount $");
        }
        
    }
    }
