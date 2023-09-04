package Finance;

import Exceptions.InvalidCodeException;
import Exceptions.ItemSoldOutException;
import Exceptions.NotEnoughMoneyException;
import Items.Inventory;
import Items.Item;
import com.techelevator.Display;
import com.techelevator.Log;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {

    private Log log;
    private CustomerAccount account;
    private Inventory inventory;
    private String codeName;

    public Transaction(CustomerAccount account, Inventory inventory, String codeName){
        this.account = account;
        this.inventory = inventory;
        this.codeName = codeName;
    }

    public CustomerAccount getAccount() {
        return account;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean checkCode() throws InvalidCodeException {
        if(inventory.getInventoryMap().containsKey(codeName)){
            return true;
        } else {
            throw new InvalidCodeException();
        }
    }

    public boolean checkSufficientFunds(boolean isDiscounted) throws NotEnoughMoneyException {
        if(!isDiscounted && account.getCurrentAmount().compareTo(inventory.getInventoryMap().get(codeName).
                getItemPrice()) >= 0){
            return true;
        } else if (isDiscounted && account.getCurrentAmount().compareTo(inventory.getInventoryMap().get(codeName).
                getItemPrice().subtract(BigDecimal.ONE)) >= 0) {
            return true;
        } else {
            throw new NotEnoughMoneyException();
        }
    }

    public boolean checkItemStock() throws ItemSoldOutException {
        if(inventory.getInventoryMap().get(codeName).getItemQuantity() > 0){
            return true;
        } else {
            throw new ItemSoldOutException();
        }
    }

    public boolean isDiscounted(int transactionCounter) {
        if(transactionCounter % 2 == 0 && LocalDate.now().getMonthValue() == 8) {
            return true;
        } else {
            return false;
        }
    }
    public void attemptPurchase(int transactionCounter) throws ItemSoldOutException, NotEnoughMoneyException, InvalidCodeException {

        BigDecimal remainingBalance = account.getCurrentAmount();
        codeName = codeName.toUpperCase();
        Item purchaseItem = null;
        boolean isDiscounted = isDiscounted(transactionCounter);

        //check code, stock, and sufficient funds
        if(checkCode() && checkItemStock() && checkSufficientFunds(isDiscounted)){
            purchaseItem = inventory.getInventoryMap().get(codeName);
        }

        //check for discount criteria and apply if needed
        if (isDiscounted) {
            remainingBalance = applyDiscount(remainingBalance, purchaseItem);
        } else {
            remainingBalance = noDiscount(remainingBalance, purchaseItem);
        }

        completePurchase(codeName, purchaseItem, remainingBalance, isDiscounted);
    }

    public void completePurchase(String codeName, Item purchaseItem, BigDecimal remainingBalance, boolean isDiscounted){
        // update inventory list and map
        inventory.updateInventoryList(purchaseItem);
        inventory.updateInventoryMap(purchaseItem, codeName);

        // update account balance
        account.setCurrentAmount(remainingBalance);
        Display.itemPriceBalance(isDiscounted, purchaseItem);

        //log purchase
        log = new Log(purchaseItem, account.getCurrentAmount(), isDiscounted);
        log.determineLogDisplay();
        log.printLog();

        //display purchase message
        Display.purchaseMessage(purchaseItem);
    }
    public CustomerAccount giveChange(CustomerAccount account){
        //convert dollars to cents and BigDecimal to int
        int cents = account.getCurrentAmount().multiply(BigDecimal.valueOf(100)).toBigInteger().intValue();
        System.out.println("THANK YOU FOR YOUR PURCHASE.");
        if(cents > 0){
            calculateChange(cents, account);
        }
        account.setCurrentAmount(BigDecimal.ZERO);
        return account;
    }

    public int[] calculateChange(int cents, CustomerAccount account){
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        int[] change = new int[3];

        quarters = cents / 25;
        cents = cents - (quarters * 25);
        dimes = cents / 10;
        cents = cents - (dimes * 10);
        nickels = cents / 5;

        Display.change(quarters, dimes, nickels);

        log = new Log("GIVE CHANGE", account.getCurrentAmount(), BigDecimal.valueOf(0, 2));
        log.determineLogDisplay();
        log.printLog();

        change[0] = quarters;
        change[1] = dimes;
        change[2] = nickels;
        return change;
    }

    public BigDecimal applyDiscount(BigDecimal remainingBalance, Item purchaseItem){
        remainingBalance = remainingBalance.add(BigDecimal.ONE).subtract(purchaseItem.getItemPrice());
        System.out.println("DISCOUNT APPLIED: HAPPY AUGUST!");
        return remainingBalance;
    }
    public BigDecimal noDiscount(BigDecimal remainingBalance, Item purchaseItem){
        remainingBalance = remainingBalance.subtract(purchaseItem.getItemPrice());
        return remainingBalance;
    }
}
