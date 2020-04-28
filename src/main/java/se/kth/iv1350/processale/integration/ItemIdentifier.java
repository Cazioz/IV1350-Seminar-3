package se.kth.iv1350.processale.integration;

/**
 * 
 * @author Oscar Eklund
 * 
 * This class contains the identifier of an item
 */
public class ItemIdentifier {
    private final Double identifier;
    
    /**
     * Creates an instance of identifier
     * 
     * @param id the identifier of an item, 
     *           may for example represent a barcode
     */
    public ItemIdentifier(Double id) {
        this.identifier = id;
    }
    
    /**
     * Getter that returns the ItemIdentifiers Double identifier
     * 
     * @return Returns the ItemIdentifiers Double identifier
     */
    public Double getDoubleIdentifier() {
        return this.identifier;
    }
    
}
