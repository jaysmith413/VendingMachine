package com.techelevator;

import Finance.CustomerAccount;
import Finance.Transaction;
import Items.Inventory;
import Items.Item;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class TransactionTest {
    private Transaction transactionTest = new Transaction(new CustomerAccount(), new Inventory(), "");

    @Test
    public void calculateChange_3_1_1() {
        int[] actual = transactionTest.calculateChange(90, new CustomerAccount());
        int[] expected = {3, 1, 1};

        Assert.assertEquals(expected[0], actual[0]);
        Assert.assertEquals(expected[1], actual[1]);
        Assert.assertEquals(expected[2], actual[2]);
    }

    @Test
    public void calculateChange_0_1_1() {
        int[] actual = transactionTest.calculateChange(15, new CustomerAccount());
        int[] expected = {0, 1, 1};

        Assert.assertEquals(expected[0], actual[0]);
        Assert.assertEquals(expected[1], actual[1]);
        Assert.assertEquals(expected[2], actual[2]);
    }

    @Test
    public void calculateChange_8_2_0() {
        int[] actual = transactionTest.calculateChange(220, new CustomerAccount());
        int[] expected = {8, 2, 0};

        Assert.assertEquals(expected[0], actual[0]);
        Assert.assertEquals(expected[1], actual[1]);
        Assert.assertEquals(expected[2], actual[2]);
    }

    @Test
    public void calculateChange_0_0_0() {
        int[] actual = transactionTest.calculateChange(0, new CustomerAccount());
        int[] expected = {0, 0, 0};

        Assert.assertEquals(expected[0], actual[0]);
        Assert.assertEquals(expected[1], actual[1]);
        Assert.assertEquals(expected[2], actual[2]);
    }

    @Test
    public void applyDiscount_65cents() {
        BigDecimal actual = transactionTest.applyDiscount(BigDecimal.valueOf(10), new Item("A1",
                "U-Chews", BigDecimal.valueOf(1.65), "Gum", 5));
        BigDecimal expected = BigDecimal.valueOf(9.35);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void applyDiscount_965() {
        BigDecimal actual = transactionTest.applyDiscount(BigDecimal.valueOf(10), new Item("A1",
                "U-Chews", BigDecimal.valueOf(10.65), "Gum", 5));
        BigDecimal expected = BigDecimal.valueOf(.35);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void applyDiscount_500() {
        BigDecimal actual = transactionTest.applyDiscount(BigDecimal.valueOf(10), new Item("A1",
                "U-Chews", BigDecimal.valueOf(6.00), "Gum", 5));
        BigDecimal expected = BigDecimal.valueOf(5.00);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void noDiscount_400() {
        BigDecimal actual = transactionTest.noDiscount(BigDecimal.valueOf(10), new Item("A1",
                "U-Chews", BigDecimal.valueOf(6.00), "Gum", 5));
        BigDecimal expected = BigDecimal.valueOf(4.00);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void noDiscount_0() {
        BigDecimal actual = transactionTest.noDiscount(BigDecimal.valueOf(10), new Item("A1",
                "U-Chews", BigDecimal.valueOf(10.00), "Gum", 5));
        BigDecimal expected = BigDecimal.valueOf(0.00);

        Assert.assertEquals(expected, actual);
    }
}
