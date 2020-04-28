package se.kth.iv1350.processale.integration;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PrinterTest {
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;
    
    @BeforeEach
    public void setUp() {     
        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }
    
    @AfterEach
    public void tearDown() {
        printoutBuffer = null;
        System.setOut(originalSysOut);
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
        Printer instance = new Printer();
        instance.printReceipt(receiptString);
        
        String printout = printoutBuffer.toString();
        String expectedOutput = "Price of Sale: 25.0kr\n";
        assertTrue(printout.contains(expectedOutput), 
                "Receipt did not print correctly");
    }
    
}
