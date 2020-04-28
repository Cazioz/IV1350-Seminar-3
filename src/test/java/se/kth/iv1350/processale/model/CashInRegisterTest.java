package se.kth.iv1350.processale.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.processale.integration.AmountDTO;

public class CashInRegisterTest {
    private AmountDTO amountInRegister;
    private CashInRegister register;
    
    @BeforeEach
    public void setUp() {
        amountInRegister = new AmountDTO(500.0, "SEK");
        register = new CashInRegister(amountInRegister);
    }
    
    @AfterEach
    public void tearDown() {
        register = null;
        amountInRegister = null;
    }

    @Test
    public void testIncreaseCashInRegister() {
        AmountDTO amountIncrease = new AmountDTO(50.0, "SEK");
        Double expResult = 550.0;
        AmountDTO returnedAmount = register.increaseCashInRegister(amountIncrease);
        Double result = returnedAmount.getAmount();
        assertEquals(expResult, result, 
                "There was not 550.0 present in the register");
    }
    
}
