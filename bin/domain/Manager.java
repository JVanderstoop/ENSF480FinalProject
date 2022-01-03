/**
 * Manager.java found in bin/domain directory
 * @author Bradley Wachs - bradley.wachs@ucalgary.ca
 * written for ENSF 480 Final Project
 */
package bin.domain;

public class Manager extends Person{
	
	/**
	 * Manager Constructor
	 * @param name: String			- Name for the manager
	 * @param username: String		- Username for the managers account
	 * @param password: String		- Password for the managers account
	 */
	public Manager(String name, String username, String password) {
		super(name, username, password);
	}
	
	/**
	 * Default Manager constructor
	 */
	public Manager() {
		super();
	}
	
	/**
	 * Gets the summary report of properties listed from a given date until present time
	 * @param date - Starting date for the listings of a report
	 */
	public Report getSummaryReport(java.sql.Date date) {
		return new Report(date);
	}
}