package com.techelevator;

import com.techelevator.view.InventoryStocks;
import com.techelevator.view.VendingMachine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class VendingMachineTest {
    VendingMachine vendingMachine;

    @Before
    public void setUp() throws FileNotFoundException {
        vendingMachine = new VendingMachine();
    }

    @Test
    public void GET_ITEMS_FROM_LIST_RETURN_ITEM_LIST() {
        // arrange

        List<String> expected = Arrays.asList("A1", "A2", "A3");

        InventoryStocks actual = vendingMachine.getItem("A1");
        Assert.assertEquals("A1", actual.getItemsSection());
        Assert.assertEquals("Potato Crisps", actual.getItemsNames());
        Assert.assertEquals(3.05, actual.getItemsPrice(), 0);
        Assert.assertEquals("Chip", actual.getItemsTypes());


    }

    @Test
    public void RETURN_TRUE_IF_THE_IT_IS_VALID_KEY() {
        //ARRANGE
        String productList = ("A1");
        boolean isValidKey = vendingMachine.isValidKeys(productList.toLowerCase(Locale.ROOT));
        //assert
        Assert.assertTrue("true", vendingMachine.isValidKeys(productList.toLowerCase(Locale.ROOT)));
    }

    @Test
    public void RETURN_FALSE_IF_THE_IT_IS_IN_VALID_KEY() {
        String productList = ("B8");
        boolean isValidKey = vendingMachine.isValidKeys(productList.toLowerCase(Locale.ROOT));
        //assert
        Assert.assertEquals("false", false, vendingMachine.isValidKeys(productList.toLowerCase(Locale.ROOT)));
    }
    @Test
    public void FEED_ONLY_VALID_MONEY() {

        BigDecimal bigDecimal1= vendingMachine.feedMoney(1);
        BigDecimal bigDecimal2= vendingMachine.feedMoney(2);
        BigDecimal bigDecimal5= vendingMachine.feedMoney(5);
        BigDecimal bigDecimal10= vendingMachine.feedMoney(10);
            Assert.assertEquals(BigDecimal.valueOf(1).setScale(2, RoundingMode.HALF_UP),bigDecimal1);
            Assert.assertEquals(BigDecimal.valueOf(3).setScale(2, RoundingMode.HALF_UP),bigDecimal2);
            Assert.assertEquals(BigDecimal.valueOf(8).setScale(2, RoundingMode.HALF_UP),bigDecimal5);
            Assert.assertEquals(BigDecimal.valueOf(18).setScale(2, RoundingMode.HALF_UP),bigDecimal10);

    }
    @Test
    public void FEED_ONLY_INVALID_MONEY() {

        BigDecimal bigDecimal1 = vendingMachine.feedMoney(3);

        Assert.assertEquals(BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP), bigDecimal1);
    }
    @Test
    public void RETURN_CHANGE_FOR_USER() {

        vendingMachine.setCurrentBalance(BigDecimal.valueOf(5));
       Assert.assertEquals("Total change: $" +
               BigDecimal.valueOf(5).setScale(2,RoundingMode.HALF_UP) + " / " + 20 +
               " quarters, " + 0 + " dimes," + " " + 0 + " " + "nickels.",vendingMachine.getChange() );
    }

    }