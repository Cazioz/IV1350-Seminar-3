package se.kth.iv1350.processale.startup;

import se.kth.iv1350.processale.integration.ExternalSystemHandler;
import se.kth.iv1350.processale.controller.Controller;
import se.kth.iv1350.processale.view.View;
import se.kth.iv1350.processale.integration.AddressDTO;

/**
 *
 * @author Oscar Eklund
 * 
 * This class contains the <code>main</code> method which performs all
 * startup of the application.
 */
public class Main {

    public static void main(String[] args) {
        ExternalSystemHandler integ = new ExternalSystemHandler();
        AddressDTO addressDTO = new AddressDTO("Västervägen 5", 
            12345, "Stockholm", "Kina", "ICA");
        Controller contr = new Controller(integ, addressDTO);
        View view = new View(contr);
        view.sampleExecution();
    }
    
}
