package com.techelevator;

import Items.Inventory;
import Items.Item;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

class InventoryTest {
    private Inventory inventory;
    List<Item> actual = new ArrayList<>();

    @BeforeEach
    void setUp() {
        this.inventory = new Inventory();
    }

//    @Test
//    void populateInventoryList_Test_Code() {
//        actual = inventory.populateInventoryList(new File("main.csv"), actual);
//        String actualCode = actual.get(0).getItemCode();
//        String expectedCode ="A1";
//        assertEquals(expectedCode,actualCode);
//    }
//    @Test
//    void populateInventoryList_Test_Name() {
//        actual = inventory.populateInventoryList(new File("main.csv"), actual);
//        String actualCode = actual.get(1).getItemName();
//        String expectedCode = "Ginger Ayle";
//        assertEquals(expectedCode, actualCode);
//    }
//    @Test
//    void populateInventoryList_Test_Price() {
//        actual = inventory.populateInventoryList(new File("main.csv"), actual);
//        BigDecimal actualCode = actual.get(1).getItemPrice();
//        BigDecimal expectedCode = BigDecimal.valueOf(1.85);
//        assertEquals(expectedCode, actualCode);
//    }
//    @Test
//    void populateInventoryList_Test_Type() {
//        actual = inventory.populateInventoryList(new File("main.csv"), actual);
//        String actualCode = actual.get(1).getItemType();
//        String expectedCode = "Drink" ;
//        assertEquals(expectedCode, actualCode);
//    }
}