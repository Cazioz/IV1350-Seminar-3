package se.kth.iv1350.processale.model;

import se.kth.iv1350.processale.integration.Item;

/**
 *
 * @author Oscar Eklund
 * 
 * This class represents quantity of an item
 */
public class Quantity {
    private final Item item;
    private Double quantityOfItem;

    Quantity(Item itemToAdd, Double quantity) {
        item = itemToAdd;
        quantityOfItem = quantity;
    }
    
    void setQuantityOfItem(Double quantity) {
        this.quantityOfItem = quantity;
    }
    
    Item getItem() {
        return this.item;
    }
    
    Double getQuantity() {
        return this.quantityOfItem;
    }
}
