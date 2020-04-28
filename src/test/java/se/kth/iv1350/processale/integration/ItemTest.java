package se.kth.iv1350.processale.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    private Item milkItem;

    @BeforeEach
    public void setUp() {
        milkItem = new Item("Some Nice Milk", 1.0, new AmountDTO(15.0, "SEK"), 
                0.125,(new ItemIdentifier(1234.0)));
    }
    
    @AfterEach
    public void tearDown() {
        milkItem = null;
    }
    
    @Test
    public void testEquals() {
        Item equalMilkItem = new Item("Some Nice Milk", 1.0, 
                new AmountDTO(15.0, "SEK"), 0.125, (new ItemIdentifier(1234.0)));
        boolean result = milkItem.matchingItems(equalMilkItem.getItemIdentifier());
        assertTrue(result, "Objects are not equal");
    }

    @Test
    public void testNotEquals() {
        Item notEqualKetchupItem = new Item("Ketchup", 1.0, 
                new AmountDTO(18.5, "SEK"), 0.125, (new ItemIdentifier(5.0)));
        boolean result = 
                milkItem.matchingItems(notEqualKetchupItem.getItemIdentifier());
        assertFalse(result, "Objects are equal");
    }  
    
    @Test 
    public void testToString() {
        String expectedResult = "Some Nice Milk - Weight: 1.0kg - "
                + "Price: 15.0kr";
        String result = milkItem.itemToString();
        boolean equals = expectedResult.equals(result);
        assertEquals(expectedResult, result, "Strings were not equal");
    }
    
    @Test
    public void testGetPriceOfProduct() {
        AmountDTO result = milkItem.getPriceOfProduct();
        Double resultDouble = result.getAmount();
        Double expectedResult = 15.0;
        assertEquals(resultDouble, expectedResult, "Price of milk wasn't 15.0");
    }
    
    @Test
    public void testGetVATOfProduct() {
        Double result = milkItem.getVATOfProduct();
        Double expectedResult = 0.125;
        assertEquals(result, expectedResult, "VAT of milk was not 0.125");
    }
}
