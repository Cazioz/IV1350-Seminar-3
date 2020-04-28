package se.kth.iv1350.processale.view;

import se.kth.iv1350.processale.controller.Controller;
import se.kth.iv1350.processale.integration.AmountDTO;
import se.kth.iv1350.processale.integration.ItemIdentifier;

/**
 * 
 * @author Oscar Eklund
 * 
 * This program has no view, instead, 
 * this class is a placeholder for the entire view
 */
public class View {
    private Controller contr;
    
     /**
      * Creates a new instance of the view
      * 
      * @param controller Used to pass calls to the model layer
      */
    public View(Controller controller) {
        this.contr = controller;
    }
    
    /**
     * A sample execution of the program, which just performs an example of how
     * a sale could be performed.
     */
    public void sampleExecution() {
        contr.startSale();
        System.out.println("A new sale has been started.");
        contr.addNewItem(new ItemIdentifier(1234.0));
        System.out.println("An item has been added (milk)");
        Double price = contr.endSale();
        System.out.println("Sale has ended, price was " + price + "kr");
        AmountDTO change = contr.enterPayment(new AmountDTO(20.0, "SEK"));
        System.out.println("Sale has been paid, " + change.getAmount()
                + "kr change returned");
    }
}
