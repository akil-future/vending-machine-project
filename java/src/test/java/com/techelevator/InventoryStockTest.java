package com.techelevator;

import com.techelevator.view.InventoryStocks;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InventoryStockTest {
    InventoryStocks inventoryStocks;
    @Before
    public void setUp() {
        new InventoryStocks("A1", "Potato",3.05, "Chip");
    }
    @Test
    public void RETURN_ITEMS_LIST_THAT_PURCHASED() {
        InventoryStocks items= new InventoryStocks("A1", "Potato",3.05, "Chip");
        Assert.assertEquals( "Potato" + " " + 3.05 +
                "\n" +"Crunch Crunch, Yum!",items.purchaseItem());
        InventoryStocks items2= new InventoryStocks("C1", "Cola",1.25, "Drink");
        Assert.assertEquals( "Cola" + " " + 1.25 +
                "\n" +"Glug Glug, Yum!",items2.purchaseItem());

    }
}
