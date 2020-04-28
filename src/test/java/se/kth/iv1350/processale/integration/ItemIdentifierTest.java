package se.kth.iv1350.processale.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemIdentifierTest {
    private ItemIdentifier identifier;
 
    @BeforeEach
    public void setUp() {
        identifier = new ItemIdentifier(5.0);
    }
    
    @AfterEach
    public void tearDown() {
        identifier = null;
    }

    @Test
    public void testGetIdentifier() {
        Double expResult = 5.0;
        Double result = identifier.getDoubleIdentifier();
        assertEquals(expResult, result, "Identifier was not 5.0");
    } 
}
