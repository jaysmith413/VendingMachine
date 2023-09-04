package Finance;

import com.techelevator.Log;

import java.math.BigDecimal;

public class CustomerAccount {

    private BigDecimal currentAmount = BigDecimal.ZERO;

    private Log log;


    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(BigDecimal currentAmount) {
        this.currentAmount = currentAmount;
    }

    public void feedMoney(String feedAmount){
        //set account to amount fed + current balance
        setCurrentAmount(new BigDecimal(feedAmount).add(getCurrentAmount()));

        //log FEED MONEY
        log = new Log("FEED MONEY", new BigDecimal(feedAmount).setScale(2),
            getCurrentAmount().setScale(2));
        log.determineLogDisplay();
        log.printLog();
    }
}
