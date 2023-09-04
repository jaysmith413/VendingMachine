package Exceptions;

public class ItemSoldOutException extends Exception{
    public void errorMessage(){
        System.out.println("ITEM SOLD OUT");
    }
}
