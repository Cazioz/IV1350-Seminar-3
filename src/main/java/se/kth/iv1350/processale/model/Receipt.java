package se.kth.iv1350.processale.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.processale.integration.AddressDTO;
import se.kth.iv1350.processale.integration.AmountDTO;
import se.kth.iv1350.processale.integration.Item;

/**
 *
 * @author Oscar Eklund
 * 
 * This class handles the receipt of the ongoing Sale
 */
public class Receipt {
    private final LocalDate date;
    private final LocalTime time;
    private final AddressDTO addressDTO;
    private AmountDTO totalVat;
    private AmountDTO amountPaid;
    private AmountDTO returnedChange;
    private AmountDTO priceOfSale;
    private List<Item> listOfItemsInSale = new ArrayList<>();
    private List<Quantity> quantitiesOfItems;
    
    /**
     * Creates an instance of receipt
     * 
     * @param addrDTO The address of the store
     */
    Receipt(AddressDTO addrDTO) {
        this.addressDTO = addrDTO;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.quantitiesOfItems = new ArrayList<>();
        this.priceOfSale = new AmountDTO(0.0, "SEK");
        this.totalVat = new AmountDTO(0.0, "SEK");
    }
    
    /**
    * Adds a new item to an ongoing Sale
    * 
    * @param itemToBeAdded The Item to be added to the ongoing sale
    */
    void addNewItem(Item itemToBeAdded) {
        listOfItemsInSale.add(itemToBeAdded);
        addItemQuantity(itemToBeAdded);
        this.priceOfSale = new AmountDTO(this.priceOfSale.getAmount() + 
                itemToBeAdded.getPriceOfProduct().getAmount(), "SEK");
        this.totalVat = new AmountDTO(this.totalVat.getAmount()
            + itemToBeAdded.getVATOfProduct(), "SEK");
    }
    
    /**
     * Returns the price before discounts are applied
     * 
     * @return Returns the Double priceBeforeDiscount
     */
    Double getPriceOfSale() {
        return this.priceOfSale.getAmount();
    }
    
    /**
     * Getter for getting the ArrayList of ItemDTOs
     * 
     * @return Returns the List<ItemDTO> of ItemDTOs in the current sale
     */
    List<Item> getItemsInSale() {
        return this.listOfItemsInSale;
    }
    
    /**
     * Getter for the quantities of ItemDTOs in the current sale
     * 
     * @return Returns the List<Quantity> of Quantities in the current sale.
     */
    List<Quantity> getQuantitiesInSale() {
        return this.quantitiesOfItems;
    }
    
    /**
     * Increases (or creates) quantity of itemToAdd
     * 
     * @param itemToAdd The item to track quantity of
     */
    void addItemQuantity(Item itemToAdd) {
        int indexOfItemInQuantityList = this.containsItem(itemToAdd);
        if(indexOfItemInQuantityList != -2) {
            Quantity newQuantity = new Quantity(itemToAdd, 
                    getQuantityAtIndex(indexOfItemInQuantityList) + 1.0);
            quantitiesOfItems.set(indexOfItemInQuantityList, newQuantity);
        }
        else {
            Quantity newQuantity = new Quantity(itemToAdd, 1.0);
            quantitiesOfItems.add(newQuantity);
        }
    }
     
    private Double getQuantityOfItem(Item itemToGetQuantityOf) {
        return this.getQuantityAtIndex(this.containsItem(itemToGetQuantityOf));
    }
    
    /**
    * Checks whether the quantity list (quantitiesOfItems) contains the 
    * specified Item
    * 
    * @param itemToCheck The Item to check if it exists in the quantity list
    * @return            Returns index (int) of Item in quantity List if it
                    exists, else -2
    */
    private int containsItem(Item itemToCheck) {
        int countPositionInQuantityList = -1;
        for (Quantity quantitiesInList : quantitiesOfItems) {
            countPositionInQuantityList++;
            if(quantitiesInList.getItem().
                    matchingItems(itemToCheck.getItemIdentifier())) {
                return countPositionInQuantityList;
            }
        }
        return -2;
    }
    
    /**
     * Returns quantity of item at the index
     * 
     * @param index The index of the requested quantity/item
     * @return      The quantity of the item at the index
     */
    private Double getQuantityAtIndex(int index) {
        return quantitiesOfItems.get(index).getQuantity();
    }
    
    /**
     * Enters the payment of a finalized Sale
     * 
     * @return Returns the change to be handed back to customer 
     */
    AmountDTO enterPayment(AmountDTO amountPaid) {
        this.amountPaid = amountPaid;
        this.returnedChange = new AmountDTO(amountPaid.getAmount() 
                - this.priceOfSale.getAmount(), "SEK");
        return this.returnedChange;
    }
    
    /**
     * Returns the Receipt in String format
     * 
     * @return Returns the Receipt in String format
     */
    String receiptToString() {
        StringBuilder receiptBuilder = new StringBuilder();
        receiptBuilder.append("-----RECEIPT-----\n");
        receiptBuilder.append("Date: " + date.toString() + "\n");
        receiptBuilder.append("Time: " + time.toString() + "\n");
        receiptBuilder.append("Name of Store: " 
                + addressDTO.getStoreName() + "\n");
        receiptBuilder.append(this.itemListToString());
        receiptBuilder.append("VAT: " + totalVat.getAmount() + "\n");
        receiptBuilder.append("Price of Sale: " + 
                priceOfSale.getAmount() + "kr\n");
        receiptBuilder.append("Amount Paid: " + amountPaid.getAmount() + "kr\n");
        receiptBuilder.append("Returned Change: " + 
                returnedChange.getAmount() + "kr\n");
        receiptBuilder.append(addressDTO.addressToString() + "\n");
        receiptBuilder.append("-----------------");
        
        return receiptBuilder.toString();
    }
    
    /**
     * Converts the list of items into a string
     * 
     * @return Returns a string containing the list of items
     */
    private String itemListToString() {
        StringBuilder itemListBuilder = new StringBuilder();
        
        for (Item itemInList : listOfItemsInSale) {
            itemListBuilder.append(itemInList.itemToString() + 
                    " - " + this.getQuantityOfItem(itemInList) + "st\n");
        }
        return itemListBuilder.toString();
    }
}