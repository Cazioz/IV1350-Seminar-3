package se.kth.iv1350.processale.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.processale.model.Sale;

/**
 *
 * @author Oscar Eklund
 * 
 * This class is responsible for retrieving items from external inventory
 */
public class ExternalInventorySystem {
    private List<Item> listOfItems = new ArrayList<>();
    
    /**
     * Creates an instance of ExternalInventorySystem
     */
    ExternalInventorySystem() {
        addItems();
    }
    
    private void addItems() {
        listOfItems.add(new Item("Some Nice Milk", 1.0, 
                new AmountDTO(15.0, "SEK"), 0.125, (new ItemIdentifier(1234.0))));
        listOfItems.add(new Item("Sweet Potatoes", 0.5, 
                new AmountDTO(25.0, "SEK"), 0.125, (new ItemIdentifier(9876.0))));
        listOfItems.add(new Item("Ketchup", 1.0, 
                new AmountDTO(18.5, "SEK"), 0.125, (new ItemIdentifier(5.0))));
    }
    
    /**
     * Retrieves an item matching the id (if one is found)
     * 
     * @param id The ItemIdentifier that is to be matched
     * @return   The Item matching the id, else null
     */
    Item getItem(ItemIdentifier id) {
        for (Item itemInList : listOfItems) {
            if(itemInList.matchingItems(id)) {
                return itemInList;
            }
        }
        return null;
    }
    
    /**
     * Placeholder for future implementation to report sales to the 
     * external inventory system. Currently does nothing.
     * 
     * @param currentSale The current sale that shall be reported
     */
    void reportSale(Sale currentSale) {
        
    }
}
