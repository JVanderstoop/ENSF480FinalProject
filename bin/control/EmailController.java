/**
 * EmailController.java found in bin/control directory
 * @author Karl Winkler - Karl.Winkler@ucalgary.ca - UCID: 30088699
 * written for ENSF 480 Final Project
 */
package bin.control;

import bin.domain.Landlord; 
public class EmailController{
	
	/**
	 * default constructor
	 * 
	 * empty because there is nothing to initialise and there is no reason for
	 * the methods to not be static
	 */
	public EmailController(){}
	
	/**
	 * simulates sending the desired message form a renter to a landlord
	 * 
	 * @param renterEmail email opf the sender 
	 * @param landlord email of the recievr
	 * @param message message being sent
	 * @return true if the message is sent false otherwise
	 */
	public boolean sendEmail(String renterEmail, Landlord landlord, String message) {
		String landlordEmail = landlord.getContactEmail();
		
		System.out.println("Email sent from " + renterEmail + " to " + landlordEmail);
		System.out.println("Message sent:\n" + message);
		
		return true;
	}
}
