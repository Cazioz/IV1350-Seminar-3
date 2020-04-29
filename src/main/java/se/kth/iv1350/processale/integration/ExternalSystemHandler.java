package se.kth.iv1350.processale.integration;

import se.kth.iv1350.processale.model.Sale;

/**
 *
 * @author Oscar Eklund
 * 
 * This class instantiates the classes handling the external systems, 
 * as well as directing calls to them.
 */
public class ExternalSystemHandler {
    private final ExternalInventorySystem extInvSys;
    private final ExternalAccountingSystem extAccSys;
    private final Printer printer;
    
    public ExternalSystemHandler() {
        extInvSys = new ExternalInventorySystem();
        extAccSys = new ExternalAccountingSystem();
        printer = new Printer();
    }
    
    /**
     * Calls the external inventory system and returns item corresponding to
     * identifier if an item is found
     * 
     * @param id The identifier of the item
     * @return   The item corresponding to id if it is found
     */
    public Item getItem(ItemIdentifier id) {
        return extInvSys.getItem(id);
    }
    
    /**
     * Placeholder for future implementation to report sales to the 
     * external accounting and inventory systems. Currently does nothing.
     * 
     * @param currentSale The current sale that shall be reported
     */
    public void reportSale(Sale currentSale) {
        extInvSys.reportSale(currentSale);
        extAccSys.reportSale(currentSale);
    }
    
    /**
     * Prints the receipt (placeholder, simply prints to system.out)
     * 
     * @param receiptString The string version of receipt
     */
    public void printReceipt(String receiptString) {
        printer.printReceipt(receiptString);
    }
}
