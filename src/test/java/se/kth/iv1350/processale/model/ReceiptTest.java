package se.kth.iv1350.processale.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.processale.integration.AddressDTO;
import se.kth.iv1350.processale.integration.AmountDTO;
import se.kth.iv1350.processale.integration.Item;
import se.kth.iv1350.processale.integration.ItemIdentifier;

public class ReceiptTest {

    @Test
    public void testAddNewItemAndGetItems() {
        AddressDTO addressDTO = new AddressDTO("Västervägen 5", 
            12345, "Stockholm", "Kina", "ICA");
        Item itemToAddToReceipt = new Item("Some Nice Milk", 1.0, 
                new AmountDTO(15.0, "SEK"), 0.125, (new ItemIdentifier(1234.0)));
        Receipt instance = new Receipt(addressDTO);
        instance.addNewItem(itemToAddToReceipt);
        
        List<Item> listOfItemsInSale = instance.getItemsInSale();
        Item itemRetrievedFromReceipt = listOfItemsInSale.get(0);
        
        assertEquals(itemToAddToReceipt, itemRetrievedFromReceipt, 
                "items not equal");
    }   
    
    @Test 
    public void testAddQuantitySingleItem() {
        AddressDTO addressDTO = new AddressDTO("Västervägen 5", 
            12345, "Stockholm", "Kina", "ICA");
        Item itemToAddToReceipt = new Item("Some Nice Milk", 1.0, 
                new AmountDTO(15.0, "SEK"), 0.125, (new ItemIdentifier(1234.0)));
        Receipt instance = new Receipt(addressDTO);
        instance.addNewItem(itemToAddToReceipt);
        
        List<Quantity> quantities = instance.getQuantitiesInSale();
        Quantity quantityAtFirstIndex = quantities.get(0);
        assertEquals(quantityAtFirstIndex.getQuantity(), 1.0);
    }
    
    @Test
    public void testAddQuantitySingleItemMultipleTimes() {
        AddressDTO addressDTO = new AddressDTO("Västervägen 5", 
            12345, "Stockholm", "Kina", "ICA");
        Item itemToAddToReceipt = new Item("Some Nice Milk", 1.0, 
                new AmountDTO(15.0, "SEK"), 0.125, (new ItemIdentifier(1234.0)));
        Receipt instance = new Receipt(addressDTO);
        instance.addNewItem(itemToAddToReceipt);
        instance.addNewItem(itemToAddToReceipt);
        instance.addNewItem(itemToAddToReceipt);
        
        List<Quantity> quantities = instance.getQuantitiesInSale();
        Quantity quantityAtFirstIndex = quantities.get(0);
        assertEquals(quantityAtFirstIndex.getQuantity(), 3.0);
    }
    
    @Test
    public void testAddQuantityMultipleItems() {
        AddressDTO addressDTO = new AddressDTO("Västervägen 5", 
            12345, "Stockholm", "Kina", "ICA");
        Item itemToAddToReceipt = new Item("Some Nice Milk", 1.0, 
                new AmountDTO(15.0, "SEK"), 0.125, (new ItemIdentifier(1234.0)));
        Item secondItemToAddToReceipt = new Item("Sweet Potatoes", 0.5,
                new AmountDTO(25.0, "SEK"), 0.125, (new ItemIdentifier(9876.0)));
        Item thirdItemToAddToReceipt = new Item("Ketchup", 
                1.0, new AmountDTO(18.5, "SEK"), 0.125, (new ItemIdentifier(5.0)));

        Receipt instance = new Receipt(addressDTO);
        instance.addNewItem(itemToAddToReceipt);
        instance.addNewItem(secondItemToAddToReceipt);
        instance.addNewItem(thirdItemToAddToReceipt);
        
        List<Quantity> quantities = instance.getQuantitiesInSale();
        Quantity quantityAtSecondIndex = quantities.get(1);
        assertEquals(quantityAtSecondIndex.getQuantity(), 1.0);
    }
    
    @Test
    public void testAddQuantityMultipleItemsMultipleTimes() {
        AddressDTO addressDTO = new AddressDTO("Västervägen 5", 
            12345, "Stockholm", "Kina", "ICA");
        Item itemToAddToReceipt = new Item("Some Nice Milk", 1.0, 
                new AmountDTO(15.0, "SEK"), 0.125, (new ItemIdentifier(1234.0)));
        Item secondItemToAddToReceipt = new Item("Sweet Potatoes", 0.5, 
                new AmountDTO(25.0, "SEK"), 0.125, (new ItemIdentifier(9876.0)));
        Item thirdItemToAddToReceipt = new Item("Ketchup", 
                1.0, new AmountDTO(18.5, "SEK"), 0.125, (new ItemIdentifier(5.0)));

        Receipt instance = new Receipt(addressDTO);
        instance.addNewItem(itemToAddToReceipt);
        instance.addNewItem(secondItemToAddToReceipt);
        instance.addNewItem(itemToAddToReceipt);
        instance.addNewItem(thirdItemToAddToReceipt);
        instance.addNewItem(itemToAddToReceipt);
        instance.addNewItem(thirdItemToAddToReceipt);
        instance.addNewItem(thirdItemToAddToReceipt);
        instance.addNewItem(thirdItemToAddToReceipt);
        instance.addNewItem(thirdItemToAddToReceipt);
        
        List<Quantity> quantities = instance.getQuantitiesInSale();
        Quantity quantityAtThirdIndex = quantities.get(2);
        assertEquals(quantityAtThirdIndex.getQuantity(), 5.0);
    }
    
    @Test
    public void testPriceIncrease() {
        AddressDTO addressDTO = new AddressDTO("Västervägen 5", 
            12345, "Stockholm", "Kina", "ICA");
        Item itemToAddToReceipt = new Item("Some Nice Milk", 1.0, 
                new AmountDTO(15.0, "SEK"), 0.125, (new ItemIdentifier(1234.0)));
        Receipt instance = new Receipt(addressDTO);
        instance.addNewItem(itemToAddToReceipt);
        Double expectedPrice = 15.0;
        Double recievedPrice = instance.getPriceOfSale();
        assertEquals(expectedPrice, recievedPrice, "Prices were not equal");
    }
    
    public void testMultiplePriceIncrease() {
        AddressDTO addressDTO = new AddressDTO("Västervägen 5", 
            12345, "Stockholm", "Kina", "ICA");
        Item itemToAddToReceipt = new Item("Some Nice Milk", 1.0, 
                new AmountDTO(15.0, "SEK"), 0.125, (new ItemIdentifier(1234.0)));
        Item secondItemToAddToReceipt = new Item("Sweet Potatoes", 0.5,
                new AmountDTO(10.0, "SEK"), 0.125, (new ItemIdentifier(9876.0)));
        Receipt instance = new Receipt(addressDTO);
        instance.addNewItem(itemToAddToReceipt);
        instance.addNewItem(secondItemToAddToReceipt);
        Double expectedPrice = 25.0;
        Double recievedPrice = instance.getPriceOfSale();
        assertEquals(expectedPrice, recievedPrice, "Prices were not equal");
    }
    
    @Test
    public void testZeroPriceIncrease() {
        AddressDTO addressDTO = new AddressDTO("Västervägen 5", 
            12345, "Stockholm", "Kina", "ICA");
        Item itemToAddToReceipt = new Item("Some Nice Milk", 1.0, 
                new AmountDTO(15.0, "SEK"), 0.125, (new ItemIdentifier(1234.0)));
        Item secondItemToAddToReceipt = new Item("Sweet Potatoes", 0.5, 
                new AmountDTO(0.0, "SEK"), 0.125, (new ItemIdentifier(9876.0)));
        Receipt instance = new Receipt(addressDTO);
        instance.addNewItem(itemToAddToReceipt);
        instance.addNewItem(secondItemToAddToReceipt);
        Double expectedPrice = 15.0;
        Double recievedPrice = instance.getPriceOfSale();
        assertEquals(expectedPrice, recievedPrice, "Prices were not equal");
    }
    
        
    @Test
    public void testEnterPayment() {
        AddressDTO addressDTO = new AddressDTO("Västervägen 5", 
            12345, "Stockholm", "Kina", "ICA");
        Item itemToAddToReceipt = new Item("Some Nice Milk", 1.0, 
                new AmountDTO(15.0, "SEK"), 0.125, (new ItemIdentifier(1234.0)));
        Receipt instance = new Receipt(addressDTO);
        instance.addNewItem(itemToAddToReceipt);
        Double expectedResult = 12.0;
        AmountDTO result = instance.enterPayment(new AmountDTO(27.0, "SEK"));
        Double resultDouble = result.getAmount();
        assertEquals(expectedResult, resultDouble, 
                "Change returned was not 12.0");
    }
    
    @Test
    public void testReceiptToString() {
        AddressDTO addressDTO = new AddressDTO("Västervägen 5", 
            12345, "Stockholm", "Kina", "ICA");
        Item itemToAddToReceipt = new Item("Some Nice Milk", 1.0, 
                new AmountDTO(15.0, "SEK"), 0.125, (new ItemIdentifier(1234.0)));
        Item secondItemToAddToReceipt = new Item("Sweet Potatoes", 0.5, 
                new AmountDTO(10.0, "SEK"), 0.125, (new ItemIdentifier(9876.0)));
        Receipt instance = new Receipt(addressDTO);
        instance.addNewItem(itemToAddToReceipt);
        instance.addNewItem(secondItemToAddToReceipt);
        instance.enterPayment(new AmountDTO(30.0, "SEK"));
        
        String expectedReceiptString = ("-----RECEIPT-----\n" +
                "Date: " + LocalDate.now() + "\n" + 
                "Time: " + LocalTime.now() + "\n" + "Name of Store: ICA\n" + 
                "Some Nice Milk - Weight: 1.0kg - Price: 15.0kr - 1.0st\n" + 
                "Sweet Potatoes - Weight: 0.5kg - Price: 10.0kr - 1.0st\n" + 
                "VAT: 0.25\n" + 
                "Price of Sale: 25.0kr\n" + 
                "Amount Paid: 30.0kr\n" +
                "Returned Change: 5.0kr\n" + 
                "Västervägen 5, 12345, Stockholm, Kina\n" +
                "-----------------");
        String resultString = instance.receiptToString();
        assertEquals(expectedReceiptString, resultString,
                "Strings were not equal");
    }

}
