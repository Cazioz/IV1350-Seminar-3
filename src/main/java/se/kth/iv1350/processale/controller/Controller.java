package se.kth.iv1350.processale.controller;

import se.kth.iv1350.processale.integration.ExternalSystemHandler;
import se.kth.iv1350.processale.integration.AddressDTO;
import se.kth.iv1350.processale.integration.AmountDTO;
import se.kth.iv1350.processale.model.Sale;
import se.kth.iv1350.processale.integration.ItemIdentifier;
import se.kth.iv1350.processale.integration.Item;
import se.kth.iv1350.processale.model.CashInRegister;
import se.kth.iv1350.processale.model.SaleLog;

/**
 *
 * @author Oscar Eklund
 * 
 * This is the controller class, all calls to the model pass through here.
 */
public class Controller {
    private final ExternalSystemHandler integ;
    private final AddressDTO addressDTO;
    private Sale sale;
    private CashInRegister cashInRegister;
    private SaleLog logOfSales;
    
    /**
     * Creates a new instance of Controller, 
     * amount available in register is hardcoded
     * 
     * @param extSysHandler Handles communication with external systems 
     *                       such as inventory and accounting
     * @param addrDTO       The address of the Store
     */
    public Controller(ExternalSystemHandler extSysHandler, AddressDTO addrDTO) {
        this.integ = extSysHandler; 
        this.addressDTO = addrDTO.getAddress();
        this.cashInRegister = new CashInRegister(new AmountDTO(500.0, "SEK"));
        this.logOfSales = new SaleLog(this.cashInRegister);
    }
    
    /**
     * Initiates a new sale, creates an instance of sale
     */
    public void startSale() {
        sale = new Sale(addressDTO);
    }
    
    /**
     * Adds a new item (if one is found) to an ongoing Sale
     * 
     * @param id The ItemIdentifier of the item to be added to the ongoing Sale
     * @return   Returns the item that was added to the sale according to the id
     */
    public Item addNewItem(ItemIdentifier id) {
        Item itemToAdd = integ.getItem(id);
        sale.addNewItem(itemToAdd);
        return itemToAdd;
    }
    
    /**
    * Returns the price of the sale
    * 
    * @return Returns the Double priceOfSale from the Receipt
    */ 
    public Double endSale() {
        return sale.endSale();
    }
    
    /**
     * Finalizes the sale by entering the amount paid
     * and returning the change to be returned to customer
     * 
     * @param amountPaid The AmountDTO paid by the customer
     * @return           Returns the change which is to be returned to customer
     */
    public AmountDTO enterPayment(AmountDTO amountPaid) {
        AmountDTO change = sale.enterPayment(amountPaid, logOfSales, integ);
        return change;
    }
}
