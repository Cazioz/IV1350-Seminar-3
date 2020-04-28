package se.kth.iv1350.processale.model;

import se.kth.iv1350.processale.integration.AmountDTO;

/**
 *
 * @author Oscar Eklund
 
 This class represents the AmountDTO present in the real register
 */
public class CashInRegister {
    private AmountDTO amountPresentInRegister;
    
    /**
     * Creates an instance of CashInRegister
     * 
     * @param amountPresentInRegister The amount present in the register
     */
    public CashInRegister(AmountDTO amountPresentInRegister) {
        this.amountPresentInRegister = amountPresentInRegister;
    }
    
    /**
     * Increases the amount present in the Cash Register
     * 
     * @param amountIncrease The amount to increase by
     * @return               Returns the amount present after increasing
     */
    AmountDTO increaseCashInRegister(AmountDTO amountIncrease) {
        amountPresentInRegister = new AmountDTO(
            amountPresentInRegister.getAmount() + 
                amountIncrease.getAmount(), amountIncrease.getCurrency());
        return amountPresentInRegister;
    }
}
