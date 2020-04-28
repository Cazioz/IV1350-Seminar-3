package se.kth.iv1350.processale.view;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import se.kth.iv1350.processale.controller.Controller;
import se.kth.iv1350.processale.integration.AddressDTO;
import se.kth.iv1350.processale.integration.ExternalSystemHandler;

public class ViewTest {
    
    private View instanceToTest;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;
    
    @BeforeEach
    public void setUp() {
        ExternalSystemHandler integ = new ExternalSystemHandler();
        AddressDTO addressDTO = new AddressDTO("Västervägen 5", 
            12345, "Stockholm", "Kina", "ICA");
        Controller contr = new Controller(integ, addressDTO);
        instanceToTest = new View(contr);
        
        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }
    
    @AfterEach
    public void tearDown() {
        instanceToTest = null;
        
        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    @Test
    public void testRunFakeExecution() {
        instanceToTest.sampleExecution();
        String printout = printoutBuffer.toString();
        String expectedOutput = "started";
        String secondExpectedOutput = "added";
        String expectedPrice = "15.0kr";
        String expectedChange = "5.0kr";
        assertTrue(printout.contains(expectedOutput), 
                "UI did not start correctly.");
        assertTrue(printout.contains(secondExpectedOutput), 
                "Item was not added");
        assertTrue(printout.contains(expectedPrice), 
                "Price was not 15.0kr");
        assertTrue(printout.contains(expectedChange), 
                "Change was not 5.0kr");
    }
}
