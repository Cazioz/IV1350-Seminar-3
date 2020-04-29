package se.kth.iv1350.processale.model;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.processale.integration.AmountDTO;

/**
 *
 * @author Oscar Eklund
 * 
 * This class is responsible for logging completed sales
 */
public class SaleLog {
    private List<Sale> logOfSales;
    private CashInRegister cashInRegister;
    
    /**
     * Creates a new instance of SaleLog
     * @param cashInRegister
     */
    public SaleLog(CashInRegister cashInRegister) {
        this.logOfSales = new ArrayList<>();
        this.cashInRegister = cashInRegister;
    }
    
    /**
     * Adds a sale to the log of completed sales
     * 
     * @param saleToBeLogged The Sale that is to be logged
     */
    void addSaleToLog(Sale saleToBeLogged) {
        this.logOfSales.add((saleToBeLogged));
        this.cashInRegister.increaseCashInRegister(
                new AmountDTO(saleToBeLogged.endSale(), "SEK"));
    }
}
