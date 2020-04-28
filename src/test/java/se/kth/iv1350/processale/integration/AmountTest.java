package se.kth.iv1350.processale.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AmountTest {
    private AmountDTO amount;

    @BeforeEach
    public void setUp() {
        amount = new AmountDTO(15.0, "SEK");
    }
    
    @AfterEach
    public void tearDown() {
        amount = null;
    }

    @Test
    public void testGetAmount() {
        Double expResult = 15.0;
        Double result = amount.getAmount();
        assertEquals(expResult, result, "The retrieved amount was not equal");
    }

    @Test
    public void testGetCurrency() {
        String expResult = "SEK";
        String result = amount.getCurrency();
        assertEquals(expResult, result, "The retrieved currency was not SEK");
    }
    
}
