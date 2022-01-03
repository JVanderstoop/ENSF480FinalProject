/**
 * Renter.java found in bin/domain directory
 * @author Bradley Wachs - bradley.wachs@ucalgary.ca
 * written for ENSF 480 Final Project
 */
package bin.domain;

import bin.control.EmailController; 

public class Renter extends Person{
	/**
	 * Data Members:
	 * SEARCHQUERY: String - The query for types of properties this renter is currently interested in
	 */
	private String searchQuery;
	
	/**
	 * Renter Constructor
	 * @param name: String			- Name for the renter
	 * @param username: String		- Username for the renters account
	 * @param password: String		- Password for the renters account
	 * @param searchQuery
	 */
	public Renter(String name, String username, String password, String searchQuery) {
		super(name, username, password);
		this.searchQuery = searchQuery;
	}
	
	/**
	 * Default Renter constructor to be used when setting up a Renter object with setters
	 */
	public Renter() {
		super();
	}
	
	/**
     * getSearchQuery Method:
     * Gets the query for types of properties this renter is currently interested in
     */
	public String getSearchQuery() {
		return searchQuery;
	}

	/**
	 * setSearchQuery Method:
     * Sets the query for types of properties this renter is currently interested in
	 * @param searchQuery - The query for types of properties this renter is currently interested in
	 */
	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}
	
	/**
	 * sendEmail Method:
	 * Sends a given message as an email from a renter to a landlord
	 * @param renterEmail - The email address of the renter
	 * @param landlord - The email address of the landlord
	 * @param message - The message in the email
	 */
	public void sendEmail(String renterEmail, Landlord landlord, String message) {
		EmailController ec = new EmailController();
		ec.sendEmail(renterEmail, landlord, message);
	}
}
