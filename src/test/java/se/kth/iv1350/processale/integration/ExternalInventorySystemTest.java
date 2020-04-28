package se.kth.iv1350.processale.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExternalInventorySystemTest {
    private ExternalInventorySystem extInvSys;
    
    @BeforeEach
    public void setUp() {
        extInvSys = new ExternalInventorySystem();
    }
    
    @AfterEach
    public void tearDown() {
        extInvSys = null;
    }

    @Test
    public void testGetFirstItem() {
        ItemIdentifier id = new ItemIdentifier(1234.0);
        Item milk = new Item("Some Nice Milk", 1.0, 
                new AmountDTO(15.0, "SEK"), 0.125, (new ItemIdentifier(1234.0)));
        Item milkRetrievedFromInvSys = extInvSys.getItem(id);
        boolean result = milk.matchingItems
            (milkRetrievedFromInvSys.getItemIdentifier());
        assertTrue(result, "Items are not equal");
    }
    
    @Test
    public void testGetSecondItem() {
        ItemIdentifier id = new ItemIdentifier(9876.0);
        Item potatoes = new Item("Sweet Potatoes", 0.5, 
                new AmountDTO(25.0, "SEK"), 0.125, (new ItemIdentifier(9876.0)));
        Item potatoesRetrievedFromInvSys = extInvSys.getItem(id);
        boolean result = potatoes.matchingItems
            (potatoesRetrievedFromInvSys.getItemIdentifier());
        assertTrue(result, "Items are not equal");
    }
    
    @Test
    public void testGetLastItem() {
        ItemIdentifier ketchupId = new ItemIdentifier(5.0);
        Item ketchup = new Item("Ketchup", 1.0, 
                new AmountDTO(18.5, "SEK"), 0.125, (new ItemIdentifier(5.0)));
        Item ketchupRetrievedFromInvSys = extInvSys.getItem(ketchupId);
        boolean result = ketchup.matchingItems
            (ketchupRetrievedFromInvSys.getItemIdentifier());
        assertTrue(result, "Items are not equal");
    }
    
    @Test
    public void testGetNullItem() {
        ItemIdentifier idThatDoesNotExist = new ItemIdentifier(1337.0);
        Object recievedObject = extInvSys.getItem(idThatDoesNotExist);
        assertNull(recievedObject, "Object recieved was not null");
    }
    
}
