package se.kth.iv1350.processale.integration;

/**
 *
 * @author Oscar Eklund
 * 
 * This is a DTO which contains the address and name of the retail store
 */
public class AddressDTO {
    private final String street;
    private final int zipCode;
    private final String town;
    private final String country;
    private final String storeName;
    
    /**
     * Creates a new instance of the address of the store
     * 
     * @param streetName    Name of the street. 
     * @param zip           The zipcode.
     * @param townName      Name of the town.
     * @param countryName   Name of the country.
     * @param storeName     Name of the store
     */
    public AddressDTO(String streetName, int zip, 
            String townName, String countryName, String storeName) {
        this.street = streetName;
        this.zipCode = zip;
        this.town = townName;
        this.country = countryName;
        this.storeName = storeName;
    }
    
    /**
     * Method to get the address of the store
     * 
     * @return Returns the address of the store
     */
    public AddressDTO getAddress() {
        return this;
    }
    
    /**
     * Method to get the name of the store
     * 
     * @return Returns the name of the store
     */
    public String getStoreName() {
        return this.storeName;
    }
    
    /**
     * Returns the string format of the address
     * 
     * @return Returns the string format of the address
     */
    public String addressToString() {
        StringBuilder addressBuilder = new StringBuilder();
        addressBuilder.append(street + ", " 
                + zipCode + ", " + town + ", " + country);
        return addressBuilder.toString();
    }
    
 
}
