package se.kth.iv1350.processale.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.processale.integration.AmountDTO;
import se.kth.iv1350.processale.integration.Item;
import se.kth.iv1350.processale.integration.ItemIdentifier;

public class QuantityTest {
    private Quantity quantity;
    private Item milkItem;
    
    @BeforeEach
    public void setUp() {
        milkItem = new Item("Some Nice Milk", 1.0, new AmountDTO(15.0, "SEK"), 
                0.125,(new ItemIdentifier(1234.0)));
        quantity = new Quantity(milkItem, 2.0);
    }
    
    @AfterEach
    public void tearDown() {
        milkItem = null;
        quantity = null;
    }

    @Test
    public void testGetQuantity() {
        Double expectedQuantity = 2.0;
        Double result = quantity.getQuantity();
        assertEquals(expectedQuantity, result, "Quantity was not 2.0");
    }
    
    @Test
    public void testSetQuantity() {
        Double expectedQuantity = 5.0;
        quantity.setQuantityOfItem(expectedQuantity);
        Double result = quantity.getQuantity();
        assertEquals(expectedQuantity, result, "Quantity was not 5.0");
    }
    
    @Test
    public void testGetItem() {
        Item expectedItem = new Item("Some Nice Milk", 1.0, 
                new AmountDTO(15.0, "SEK"), 0.125,(new ItemIdentifier(1234.0)));
        Item result = quantity.getItem();
        boolean equals = expectedItem.matchingItems(result.getItemIdentifier());
        assertTrue(equals, "Items were not equal");
    }
    
    
}
