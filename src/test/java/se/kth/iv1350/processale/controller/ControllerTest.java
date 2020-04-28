package se.kth.iv1350.processale.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.processale.integration.AddressDTO;
import se.kth.iv1350.processale.integration.AmountDTO;
import se.kth.iv1350.processale.integration.ExternalSystemHandler;
import se.kth.iv1350.processale.integration.Item;
import se.kth.iv1350.processale.integration.ItemIdentifier;
import se.kth.iv1350.processale.model.Sale;

public class ControllerTest {
    private ExternalSystemHandler integ;
    private AddressDTO addressDTO;
    private Controller contr;
    private Sale sale;
    
    @BeforeEach
    public void setUp() {
        integ = new ExternalSystemHandler();
        addressDTO = new AddressDTO("Västervägen 5", 
            12345, "Stockholm", "Kina", "ICA");
        contr = new Controller(integ, addressDTO);
        contr.startSale();
    }
    
    @AfterEach
    public void tearDown() {
        integ = null;
        addressDTO = null;
        contr = null;
    }

    @Test
    public void testAddNewItem() {
        ItemIdentifier idOfItemToBeAdded = new ItemIdentifier(1234.0);
        Item expectedItem = new Item("Some Nice Milk", 1.0, 
                new AmountDTO(15.0, "SEK"), 0.125, idOfItemToBeAdded);
        Item returnedItem = contr.addNewItem(idOfItemToBeAdded);
        boolean itemsAreEqual = returnedItem.matchingItems(idOfItemToBeAdded);
        
        assertTrue(itemsAreEqual);
    }
    
    @Test 
    public void testEndSale() {
        ItemIdentifier idOfItemToBeAdded = new ItemIdentifier(1234.0);
        Item expectedItem = new Item("Some Nice Milk", 1.0, 
                new AmountDTO(15.0, "SEK"), 0.125, idOfItemToBeAdded);
        contr.addNewItem(idOfItemToBeAdded);
        Double recievedPrice = contr.endSale();
        Double expectedPrice = 15.0;
        assertEquals(recievedPrice, expectedPrice, "Prices were not equal");
    }
    
    @Test
    public void testEnterPayment() {
        ItemIdentifier idOfItemToBeAdded = new ItemIdentifier(1234.0);
        contr.addNewItem(idOfItemToBeAdded);
        AmountDTO result = contr.enterPayment(new AmountDTO(23.0, "SEK"));
        Double resultDouble = result.getAmount();
        Double expectedResult = 8.0;
        assertEquals(resultDouble, expectedResult, "Change was not 8.0");
        
    }
    
}
