/**
 * Listing.java found in bin/domain directory
 * @author Bradley Wachs - bradley.wachs@ucalgary.ca
 * written for ENSF 480 Final Project
 */
package bin.domain; 


public class Listing {
	/**
	 * Data members:
     * POSTEDONLINE: boolean		- Status of if a listing of a property is posted online
     * LASTPAYMENT: java.sql.Date	- Date the last payment was made for a listing of a property
     * PAYMENTPERIOD: int			- Number of days between each payment for a listing of a property
     * RENT: double					- Price of rent in dollars for a listing of a property
     * DATEPOSTED: java.sql.Date	- Date that a listing of a property was posted
     * RENTED: boolean				- Status of if a property is rented
     * FEEAMOUNT: double			- Price in dollars of the fees for listing of a property
	 */
	private boolean postedOnline;
	private java.sql.Date lastPayment;
	private int paymentPeriod;
	private double rent;
	private java.sql.Date datePosted;
	private boolean rented;
	private double feeAmount;
	
	/**
	 * Listing constructor:
	 * @param postedOnline		- Status of if a listing of a property is posted online
	 * @param lastPayment		- Date the last payment was made for a listing of a property
	 * @param paymentPeriod		- Number of days between each payment for a listing of a property
	 * @param rent				- Price of rent in dollars for a listing of a property
	 * @param datePosted		- Date that a listing of a property was posted
	 * @param rented			- Status of if a property is rented
	 * @param feeAmount			- Price in dollars of the fees for listing of a property
	 */
	public Listing(boolean postedOnline, java.sql.Date lastPayment, int paymentPeriod,
				   double rent, java.sql.Date datePosted, boolean rented, double feeAmount) {
		this.postedOnline = postedOnline;
		this.lastPayment = lastPayment;
		this.paymentPeriod = paymentPeriod;
		this.rent = rent;
		this.datePosted = datePosted;
		this.rented = rented;
		this.feeAmount = feeAmount;
	}
	
	/**
	 * Default Listing constructor for setting up a Listing object with setters
	 */
	public Listing()
	{
		
	}
	
	/**
     * getPostedOnline Method:
     * Gets the posted online status for this Listing object
     */
	public boolean getPostedOnline() {
		return postedOnline;
	}
	
	/**
     * getLastPayment Method:
     * Gets the date of when the last payment on the property associated with this listing object was made
     */
	public java.sql.Date getLastPayment() {
		return lastPayment;
	}
	
	/**
     * getPaymentPeriod Method:
     * Gets the number of days between each expected payment for the listing of a property
     */
	public int getPaymentPeriod() {
		return paymentPeriod;
	}
	
	/**
     * getRent Method:
     * Gets the price in dollars for the cost of rent
     */
	public double getRent() {
		return rent;
	}
	
	/**
     * getDatePosted Method:
     * Gets the date that a property was posted
     */
	public java.sql.Date getDatePosted() {
		return datePosted;
	}
	
	/**
     * getRented Method:
     * Gets the status of if a property is rented
     */
	public boolean getRented() {
		return rented;
	}
	
	/**
     * getFeeAmount Method:
     * Gets the price in dollars of the fees for a listing of a property
     */
	public double getFeeAmount() {
		return feeAmount;
	}
	
	/**
     * setPostedOnline Method:
     * Sets the posted online status for this Listing object
     * @param - The posted online status for this Listing object
     */
	public void setPostedOnline(boolean postedOnline) {
		this.postedOnline = postedOnline;
	}
	
	/**
     * setLastPayment Method:
     * Sets the date of when the last payment on the property associated with this listing object was made
     * @param - The date of when the last payment on the property associated with this listing object was made
     */
	public void setLastPayment(java.sql.Date lastPayment) {
		this.lastPayment = lastPayment;
	}
	
	/**
     * setPaymentPeriod Method:
     * Sets the number of days between each expected payment for the listing of a property
     * @param paymentPeriod - The number of days between each expected payment for the listing of a property
     */
	public void setPaymentPeriod(int paymentPeriod){
		this.paymentPeriod = paymentPeriod;
	}
	
	/**
     * setRent Method:
     * Sets the price in dollars for the cost of rent
     * @param rent - The price in dollars for the cost of rent for a property
     */
	public void setRent(double rent) {
		this.rent = rent;
	}
	
	/**
     * setDatePosted Method:
     * Sets the date that a property was posted on
     * @param datePosted - The date that a property was posted on
     */
	public void setDatePosted(java.sql.Date datePosted) {
		this.datePosted = datePosted;
	}
	
	/**
     * setRented Method:
     * Sets the status of if a property is rented
     * @param rented - Rented status of a property
     */
	public void setRented(boolean rented) {
		this.rented = rented;
	}
	
	/**
     * setFeeAmount Method:
     * Sets the price in dollars of the fees for a listing of a property
     * @param feeAmount - Price in dollars for the fees of a property
     */
	public void setFeeAmount(double feeAmount) {
		this.feeAmount = feeAmount;
	}
}
