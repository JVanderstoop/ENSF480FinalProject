/**
 * Person.java found in bin/domain directory
 * @author Bradley Wachs - bradley.wachs@ucalgary.ca
 * written for ENSF 480 Final Project
 */
package bin.domain;

public class Person {
	/**
	 * Data Members:
	 * NAME: String			- Name for the person
	 * USERNAME: String		- Username for the persons account
	 * PASSWORD: String		- Password for the persons account
	 */
	private String name;
	private String username;
	private String password;
	
	
	/**
	 * Person Constructor
	 * @param name: String			- Name for the person
	 * @param username: String		- Username for the persons account
	 * @param password: String		- Password for the persons account
	 */
	public Person(String name, String username, String password) {
		this.name = name;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Default Person constructor to be used when setting up a Person object with setters
	 */
	public Person() {
		setName("name");
		setUsername("username");
		setPassword("password");
	}
	
	/**
     * getName Method:
     * Gets the name of this Person object
     */
	public String getName() {
		return name;
	}
	
	/**
     * getUsername Method:
     * Gets the username of this Person object
     */
	public String getUsername() {
		return username;
	}
	
	/**
     * getPassword Method:
     * Gets the password of this Person object
     */
	public String getPassword() {
		return password;
	}
	
	/**
	 * setName Method:
     * Sets the name of this Person object
	 * @param name	- The name this Person objects name will be set to
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * setUserame Method:
     * Sets the username of this Person object
	 * @param name	- The username this Person objects username will be set to
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * setPassword Method:
     * Sets the password of this Person object
	 * @param name	- The password this Person objects password will be set to
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
