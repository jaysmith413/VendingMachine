package Exceptions;

public class NotEnoughMoneyException extends Exception{
    public void errorMessage(){
        System.out.println("OUT OF MONEY, CANNOT MAKE PURCHASE. PLEASE ADD MORE MONEY");
    }
}
