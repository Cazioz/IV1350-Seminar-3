package se.kth.iv1350.processale.integration;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExternalSystemHandlerTest {
    private ExternalSystemHandler extSysHandler;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;
    
    @BeforeEach
    public void setUp() {
        extSysHandler = new ExternalSystemHandler();
        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }
    
    @AfterEach
    public void tearDown() {
        extSysHandler = null;
        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    @Test
    public void testGetFirstItem() {
        ItemIdentifier id = new ItemIdentifier(1234.0);
        Item milk = new Item("Some Nice Milk", 1.0, 
                new AmountDTO(15.0, "SEK"), 0.125, (new ItemIdentifier(1234.0)));
        Item milkRetrievedFromInvSys = extSysHandler.getItem(id);
        boolean result = milk.matchingItems
            (milkRetrievedFromInvSys.getItemIdentifier());
        assertTrue(result, "Items are not equal");
    }
    
    @Test
    public void testGetSecondItem() {
        ItemIdentifier id = new ItemIdentifier(9876.0);
        Item potatoes = new Item("Sweet Potatoes", 0.5, 
                new AmountDTO(25.0, "SEK"), 0.125, (new ItemIdentifier(9876.0)));
        Item potatoesRetrievedFromInvSys = extSysHandler.getItem(id);
        boolean result = potatoes.matchingItems
            (potatoesRetrievedFromInvSys.getItemIdentifier());
        assertTrue(result, "Items are not equal");
    }
    
    @Test
    public void testGetLastItem() {
        ItemIdentifier ketchupId = new ItemIdentifier(5.0);
        Item ketchup = new Item("Ketchup", 1.0, 
                new AmountDTO(18.5, "SEK"), 0.125, (new ItemIdentifier(5.0)));
        Item ketchupRetrievedFromInvSys = extSysHandler.getItem(ketchupId);
        boolean result = ketchup.matchingItems
            (ketchupRetrievedFromInvSys.getItemIdentifier());
        assertTrue(result, "Items are not equal");
    }   
    
    @Test
    public void testGetNullItem() {
        ItemIdentifier idThatDoesNotExist = new ItemIdentifier(1337.0);
        Object recievedObject = extSysHandler.getItem(idThatDoesNotExist);
        assertNull(recievedObject, "Object recieved was not null");
    }
    
    @Test
    public void testPrintReceipt() {
        String receiptString = ("Date: " + LocalDate.now() + "\n" + 
                "Time: " + LocalTime.now() + "\n" + "Name of Store: ICA\n" + 
                "Some Nice Milk - Weight: 1.0kg - Price: 15.0kr - 1.0st\n" + 
                "Sweet Potatoes - Weight: 0.5kg - Price: 10.0kr - 1.0st\n" + 
                "VAT: 0.25\n" + 
                "Price of Sale: 25.0kr\n" + 
                "Amount Paid: 30.0kr\n" +
                "Returned Change: 5.0kr\n" + 
                "Västervägen 5, 12345, Stockholm, Kina\n");
        extSysHandler.printReceipt(receiptString);
        
        String printout = printoutBuffer.toString();
        String expectedOutput = "Price of Sale: 25.0kr\n";
        assertTrue(printout.contains(expectedOutput), 
                "Receipt did not print correctly");
    }
}
