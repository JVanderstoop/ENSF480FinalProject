/**
 * Property.java found in bin/domain directory
 * @author Bradley Wachs - bradley.wachs@ucalgary.ca
 * written for ENSF 480 Final Project
 */
package bin.domain; 

public class Property {
	/**
	 * Data Members:
	 * LANDLORD: Landlord		- The landlord of this property
	 * HOUSEID: int				- House id of this property
	 * ADDRESS: String			- Address of this property
	 * ROOMS: int				- Number of rooms in this property
	 * BATHROOMS: double		- Number of bathrooms in this property
	 * FURNISHED: boolean		- Status of if this property is furnished or not
	 * QUADRANT: String			- Quadrant of the city this property is located in
	 * PROPERTYTYPE: String		- The type of this property (ex: Duplex, Apartment.etc)
	 * LISTING: Listing			- Listing object to store listing information for this property
	 */
	private Landlord landlord;
	private int houseID;
	private String address;
	private int rooms;
	private double bathrooms;
	private boolean furnished;
	private String quadrant;
	private String propertyType;
	private Listing listing;
	
	/**
	 * 
	 * @param landlord			- The landlord of this property
	 * @param houseID			- House id of this property
	 * @param address			- Address of this property
	 * @param rooms				- Number of rooms in this property
	 * @param bathrooms			- Number of bathrooms in this property
	 * @param furnished			- Status of if this property is furnished or not
	 * @param quadrant			- Quadrant of the city this property is located in
	 * @param propertyType		- The type of this property (ex: Duplex, Apartment.etc)
	 * @param listing			- Listing object to store listing information for this property
	 */
	public Property(Landlord landlord, int houseID,
					String address, int rooms, double bathrooms,
					boolean furnished, String quadrant,
					String propertyType, Listing listing) {
		this.landlord = landlord;
		this.houseID = houseID;
		this.address = address; 
		this.rooms = rooms; 
		this.bathrooms = bathrooms;
		this.furnished = furnished;
		this.quadrant = quadrant;
		this.propertyType = propertyType;
		this.listing = listing;
	}
	
	/**
	 * Default Property constructor to be used when setting up a Property object with setters
	 */
	public Property() {
		
	}
	
	/**
     * getLandlord Method:
     * Gets the landlord of this property
     */
	public Landlord getLandlord(){
		return landlord;
	}
	
	/**
     * getHouseID Method:
     * Gets the house id of this property
     */
	public int getHouseID(){
		return houseID;
	}
	
	/**
     * getAddress Method:
     * Gets the address of this property
     */
	public String getAddress(){ 
		return address;
	}
	
	/**
     * getRooms Method:
     * Gets the number of rooms in this property
     */
	public int getRooms(){
		return rooms;
	}
	
	/**
     * getBathrooms Method:
     * Gets the number of bathrooms in this property
     */
	public double getBathrooms() {
		return bathrooms;
	}
	
	/**
     * getFurnished Method:
     * Gets the status of if this property is furnished or not
     */
	public boolean getFurnished() {
		return furnished;
	}
	
	/**
     * getQuadrant Method:
     * Gets the quadrant of the city this property is located in
     */
	public String getQuadrant() {
		return quadrant;
	}
	
	/**
     * getPropertyType Method:
     * Gets the type of this property (ex: Duplex, Apartment.etc)
     */
	public String getPropertyType(){
		return propertyType;
	}
	
	/**
     * getListing Method:
     * Gets the listing object to store listing information for this property
     */
	public Listing getListing() {
		return listing;
	}
	
	/**
	 * setLandlord Method:
     * Sets the landlord of this property
	 * @param landlord - The landlord of this property
	 */
	public void setLandlord(Landlord landlord) {
		this.landlord = landlord;
	}
	
	/**
	 * setHouseID Method:
     * Sets the house id of this property
	 * @param houseID - House id of this property
	 */
	public void setHouseID(int houseID) {
		this.houseID = houseID;
	}
	
	/**
	 * setAddress Method:
     * Sets the address of this property
	 * @param address - Address of this property
	 */
	public void setAddress(String address) {
		this.address = address; 
	}
	
	/**
	 * setRooms Method:
     * Sets the number of rooms in this property
	 * @param rooms - Number of rooms in this property
	 */
	public void setRooms(int rooms) {
		this.rooms = rooms; 
	}
	
	/**
	 * setBathrooms Method:
     * Sets the number of bathrooms in this property
	 * @param bathrooms - Number of bathrooms in this property
	 */
	public void setBathrooms(double bathrooms) {
		this.bathrooms = bathrooms;
	}
	
	/**
	 * setFurnished Method:
     * Sets the status of if this property is furnished or not
	 * @param furnished - Status of if this property is furnished or not
	 */
	public void setFurnished(boolean furnished) {
		this.furnished = furnished;
	}
	
	/**
	 * setQuadrant Method:
     * Sets the quadrant of the city this property is located in
	 * @param quadrant - Quadrant of the city this property is located in
	 */
	public void setQuadrant(String quadrant) {
		this.quadrant = quadrant;
	}
	
	/**
	 * setPropertyType Method:
     * Sets the type of this property (ex: Duplex, Apartment.etc)
	 * @param propertyType - The type of this property (ex: Duplex, Apartment.etc)
	 */
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	
	/**
	 * setListing Method:
     * Sets the listing object to store listing information for this property
	 * @param listing - The listing object to store listing information for this property
	 */
	public void setListing(Listing listing) {
		this.listing = listing;
	}
}