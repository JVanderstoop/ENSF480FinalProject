/**
 * Landlord.java found in bin/domain directory
 * @author Bradley Wachs - bradley.wachs@ucalgary.ca
 * written for ENSF 480 Final Project
 */

 package bin.domain; 


import bin.control.PropertyController; 

public class Landlord extends Person{
	/**
     * Data members:
     * CONTACTEMAIL: String		- Contact email for this landlord
     */
	private String contactEmail;
	
	/**
	 * Landlord Constructor
	 * @param name: String			- Name for the landlord
	 * @param username: String		- Username for the landlords account
	 * @param password: String		- Password for the landlords account
	 * @param contactEmail: String	- Landlords contact email
	 */
	public Landlord(String name, String username, String password, String contactEmail){
		super(name, username, password);
		this.contactEmail = contactEmail;
	}

	/**
	 * Default Landlord constructor to be used when setting up a Landlord object with setters
	 */
	public Landlord()
	{
		super(); 
	}
	
	/**
     * getContactEmail Method:
     * Gets the contact email value for this Landlord object
     */
	public String getContactEmail() {
		return contactEmail;
	}
	
	/**
     * setContactEmail Method:
     * Sets the contactEmail value for this Landlord object
     * * @param contactEmail: String	- Landlords contact email
     */
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	
	/**
	 * createProperty Method:
	 * Inserts a given property into the SQL database
	 * @param property: Property		- Property that is to be inserted into the database
	 */
	public void createProperty(Property property) {
		PropertyController pc = new PropertyController();
		pc.createNewProperty(property);
	}
	
	/**
	 * createListing Method:
	 * Sets the listing variable for a given property
	 * @param property	- The property that the listing is to be inserted into
	 * @param listing	- The listing that is to be inserted into the property
	 */
	public void createListing(Property property, Listing listing) {
		property.setListing(listing);
	}
	
	/**
	 * changeListingStatus Method:
	 * Changes the postedOnline variable in the listing object of a given property
	 * @param property		- The property whose listing status is changed
	 * @param postedOnline	- New listing status for the property
	 */
	public void changeListingStatus(Property property, boolean postedOnline) {	// Update database?
		PropertyController pc = new PropertyController();
		
		property.getListing().setPostedOnline(postedOnline);
		
		pc.setOnlineStatus(property.getHouseID(), postedOnline);
	}
	
	/**
	 * makePayment Method:
	 * Makes a payment for a given property
	 * @param property			- The property that the payment is to be made on
	 * @param paymentAmount		- Dollar amount of the payment on the property
	 */
	public void makePayment(Property property, double paymentAmount) {
		PropertyController pc = new PropertyController();
		
		double newFeeAmount = property.getListing().getFeeAmount() - paymentAmount;
		property.getListing().setFeeAmount(newFeeAmount);
		
		pc.setThisFee(property.getHouseID(), newFeeAmount);
	}
	
	/**
	 * removeListing Method:
	 * Removes the listing of a given property
	 * @param property		- The property whose listing is being removed
	 */
	public void removeListing(Property property) {	// Update database?
		PropertyController pc = new PropertyController();
		
		property.setListing(null);
		
		pc.setOnlineStatus(property.getHouseID(), false);
	}
	
	/**
	 * changeRented Method:
	 * Changes the rented status of a given property from true to false or from false to true
	 * @param property		- The property whose rented status is being changed
	 */
	public void changeRented(Property property) {	// Update database?
		PropertyController pc = new PropertyController();
		
		property.getListing().setRented(!property.getListing().getRented());
		
		pc.setRented(property.getHouseID(), !property.getListing().getRented());
	}
	
	/**
	 * changeRented Method:
	 * Changes the rented status of a given property to the given status
	 * @param property		- The property whose rented status is being changed
	 * @param rented		- Desired rented status
	 */
	public void changeRented(Property property, boolean rented) {	// Update database?
		PropertyController pc = new PropertyController();
		
		property.getListing().setRented(rented);
		
		pc.setRented(property.getHouseID(), rented);
	}
}