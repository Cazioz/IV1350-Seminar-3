package se.kth.iv1350.processale.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AddressDTOTest {
    private AddressDTO addressDTO;
    
    @BeforeEach
    public void setUp() {
        addressDTO = new AddressDTO("Västervägen 5", 
            12345, "Stockholm", "Kina", "ICA");
    }
    
    @AfterEach
    public void tearDown() {
        addressDTO = null;
    }

    @Test
    public void testGetStoreName() {
        String expResult = "ICA";
        String result = addressDTO.getStoreName();
        assertEquals(expResult, result, "Store name was not ICA");
    }

    @Test
    public void testAddressToString() {
        String expResult = "Västervägen 5, 12345, Stockholm, Kina";
        String result = addressDTO.addressToString();
        assertEquals(expResult, result, "String was not equal to expected");
    }
    
}
