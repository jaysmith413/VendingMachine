package Items;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

public class Inventory {

private Map<String, Item> inventoryMap = new HashMap<>();
private List<Item> inventoryList = new ArrayList<>();

    public Map<String, Item> getInventoryMap() {
        return inventoryMap;
    }

    public void updateInventoryList(Item purchaseItem) {
        for(int i = 0; i < inventoryList.size(); i++){
            if(inventoryList.get(i).getItemCode().equals(purchaseItem.getItemCode())){
                inventoryList.get(i).setItemQuantity(inventoryList.get(i).getItemQuantity() - 1);
            }
        }
    }
    public void updateInventoryMap(Item purchaseItem, String codeName){
        purchaseItem.setItemQuantity(purchaseItem.getItemQuantity() - 1);
        inventoryMap.put(codeName, purchaseItem);
    }

    public void populateInventory(File inputFile, Map<String, Item> inventoryMap) {
        // load data from existing file
        String itemCode;
        String itemName;
        BigDecimal itemPrice;
        String itemType;


        if(inputFile.exists()) {
            try (Scanner inputScanner = new Scanner(inputFile)) {
                while (inputScanner.hasNextLine()) {
                    String lineInput = inputScanner.nextLine();
                    String[] words = lineInput.split(",");
                    itemCode = words[0];
                    itemName = words[1];
                    itemPrice = new BigDecimal(words[2]);
                    itemType = words[3];

                    itemPrice = new BigDecimal(words[2]);
                    inventoryMap.put(itemCode, new Item(itemCode, itemName, itemPrice, itemType, 5));
                    inventoryList.add(new Item(itemCode, itemName, itemPrice, itemType, 5));

                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        this.inventoryMap = inventoryMap;


    }
    public void displayInventory(){

        for(Item eachItem: this.inventoryList){
            System.out.println(eachItem.getItemCode() + " | " + eachItem.getItemName() +
                    "| $" + eachItem.getItemPrice() + " | " + eachItem.getItemType() + " | " +
                    eachItem.getItemQuantity() + " remaining");
        }
        System.out.println();
    }
}
