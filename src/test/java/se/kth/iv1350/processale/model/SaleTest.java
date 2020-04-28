package se.kth.iv1350.processale.model;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.processale.integration.AddressDTO;
import se.kth.iv1350.processale.integration.AmountDTO;
import se.kth.iv1350.processale.integration.ExternalSystemHandler;
import se.kth.iv1350.processale.integration.Item;
import se.kth.iv1350.processale.integration.ItemIdentifier;

public class SaleTest {
    private Sale sale;
    private AddressDTO addressDTO;
    
    @BeforeEach
    public void setUp() {
        addressDTO = new AddressDTO("Västervägen 5", 
            12345, "Stockholm", "Kina", "ICA");
        sale = new Sale(addressDTO);
    }
    
    @AfterEach
    public void tearDown() {
        sale = null;
        addressDTO = null;
    }

    @Test
    public void testAddNewItemAndGetItemsInSale() {
        Item itemToBeAdded = new Item("Some Nice Milk", 1.0, new AmountDTO
        (15.0, "SEK"), 0.125, (new ItemIdentifier(1234.0)));
        sale.addNewItem(itemToBeAdded);
        Item resultItem = sale.getItemsInSale().get(0);
        boolean result = resultItem.matchingItems
            (itemToBeAdded.getItemIdentifier());
        assertTrue(result, "Items did not match");
        
    }

    @Test
    public void testEndSale() {
        Item itemToBeAdded = new Item("Some Nice Milk", 1.0, new AmountDTO
        (15.0, "SEK"), 0.125, (new ItemIdentifier(1234.0)));
        sale.addNewItem(itemToBeAdded);
        Double expResult = 15.0;
        Double result = sale.endSale();
        assertEquals(expResult, result, "The price was not 15.0");
    }

    @Test
    public void testEnterPayment() {
        AmountDTO amountPaid = new AmountDTO(22.0, "SEK");
        CashInRegister cashInRegister = 
                new CashInRegister(new AmountDTO(0.0, "SEK"));
        SaleLog saleLog = new SaleLog(cashInRegister);
        ExternalSystemHandler integ = new ExternalSystemHandler();
        
        Item itemToBeAdded = new Item("Some Nice Milk", 1.0, new AmountDTO
        (15.0, "SEK"), 0.125, (new ItemIdentifier(1234.0)));
        sale.addNewItem(itemToBeAdded);
        Double expResult = 7.0;
        AmountDTO result = sale.enterPayment(amountPaid, saleLog, integ);
        Double resultDouble = result.getAmount();
        assertEquals(expResult, resultDouble, "Change was not 7.0");
    }
    
}
