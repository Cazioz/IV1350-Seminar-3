package se.kth.iv1350.processale.integration;

/**
 *
 * @author Oscar Eklund
 * 
 * This class represents an amount of money
 */
public class AmountDTO {
    private final Double amount;
    private final String currency;
    
    /**
     * Creates an instance of Amount
     * 
     * @param amount    The amount of money
     * @param currency  The type of currency (eg SEK, USD, Bitcoin)
     */
    public AmountDTO(Double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }
    
    /**
     * Getter for the amount of money
     * 
     * @return Returns the amount of money
     */
    public Double getAmount() {
        return this.amount;
    }
    
    /**
     * Getter for the type of currency 
     * 
     * @return Returns a string with type of currency
     */
    public String getCurrency() {
        return this.currency;
    }
}
