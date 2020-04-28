package se.kth.iv1350.processale.integration;

/**
 *
 * @author Oscar Eklund
 * 
 * This is the class that represents an item
 */
public class Item {
    private final String infoAboutProduct;
    private final Double weightOfProduct;
    private final AmountDTO priceOfProduct;
    private final Double VATRate;
    private final ItemIdentifier identifier;
    
    /**
     * Creates an instance of item
     * 
     * @param info    Information about the product
     * @param weight  Weight of the product in kg
     * @param price   Price of the product
     * @param VAT     Rate of VAT of the product
     * @param id      The ItemIdentifier of the product
     */
    public Item(String info, Double weight, AmountDTO price, 
            Double VAT, ItemIdentifier id) {
        this.infoAboutProduct = info;
        this.weightOfProduct = weight;
        this.priceOfProduct = price;
        this.VATRate = VAT;
        this.identifier = id;
    }
    
    /**
     * Returns the item as a string
     * 
     * @return Returns the item as a string
     */
    public String itemToString() {
        StringBuilder itemBuilder = new StringBuilder();
        itemBuilder.append(infoAboutProduct + " - Weight: " + weightOfProduct + 
                "kg - Price: " + priceOfProduct.getAmount() + "kr");
        return itemBuilder.toString();
    }
    
    /**
     * Getter for the ItemIdentifier of the Item
     * 
     * @return Returns the ItemIdentifier of the Item
     */
    public ItemIdentifier getItemIdentifier() {
        return identifier;
    }
    
    /**
    * Compares two Items to see if they have the 
    * same ItemIdentifier and thus are the same item
    * 
    * @param id The ItemIdentifier to be compared with
    * @return   Returns true if they are equal, false if they are not
    */
    
    public boolean matchingItems(ItemIdentifier id) {
        return identifier.getDoubleIdentifier().equals
            (id.getDoubleIdentifier());
    }
    
    /**
     * Getter which returns AmountDTO (price) of this product
     * 
     * @return Returns the AmountDTO (price) of this product
     */
    public AmountDTO getPriceOfProduct() {
        return priceOfProduct;
    }
    
    /**
     * Getter which returns the VAT of this product
     * 
     * @return Returns the VAT of the product
     */
    public Double getVATOfProduct() {
        return this.VATRate;
    }
}
