/**
 * Report.java found in bin/domain directory
 * @author Bradley Wachs - bradley.wachs@ucalgary.ca
 * written for ENSF 480 Final Project
 */
package bin.domain;

import bin.control.LoginController;
import bin.database.Database; 
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Report {
	/**
	 * Data Members:
	 * NUMHOUSESLISTED: int 			- The number of houses that have been listed past a specified period
	 * NUMHOUSESRENTED: int				- The number of houses that are rented past a specified period
	 * NUMACTIVELISTINGS: int			- The number of houses that are currently listed past a specified period
	 * LISTOFHOUSESRENTED: Property[]	- List of the houses that are currently rented past a specified period
	 */
	private int numHousesListed;
	private int numHousesRented;
	private int numActiveListings;
	private Property[] listOfHousesRented;
	
	/**
	 * Report Constructor:
	 * @param numHousesListed - The number of houses that have been listed past a specified period
	 * @param numHousesRented - The number of houses that are rented past a specified period
	 * @param numActiveListings - The number of houses that are currently listed past a specified period
	 * @param listOfHousesRented - List of the houses that are currently rented past a specified period
	 */
	public Report(int numHousesListed, int numHousesRented, int numActiveListings, Property[] listOfHousesRented) {
		this.numHousesListed = numHousesListed;
		this.numHousesRented = numHousesRented;
		this.numActiveListings = numActiveListings;
		this.listOfHousesRented = listOfHousesRented;
	}
	
	/**
	 * Default Report constructor to be used when setting up a Report object with setters
	 */
	public Report() {}
	
	
	
	/**
	 * Report Constructor:
	 * Constructs a report with relevant information given a start date to look for information from
	 * @param date - Start date for the report
	 */
	/*
	 * The following pages were used as reference when writing the logic to use the java.sql.date class:
	 * https://stackoverflow.com/questions/19924236/query-comparing-dates-in-sql
	 * https://stackoverflow.com/questions/26637168/how-to-convert-a-date-to-milliseconds
	 */
	public Report(java.sql.Date date) {
		ArrayList <Property> listOfProperties = new ArrayList<Property>();
		Database db = new Database();
		//propertyController pc = new propertyController();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		numHousesListed = 0;
		numHousesRented = 0;
		numActiveListings = 0;
		
		String queryStr = "SELECT * FROM Property WHERE datePosted >="
						  + date;
		try
		{
			ResultSet queryRes = null; 
			PreparedStatement statement = db.connect.prepareStatement(queryStr); 
			queryRes = statement.executeQuery();
			
			while(queryRes.next()) {
				numHousesListed++;
				
				if(queryRes.getBoolean(9) == true ) {
					numActiveListings++;
				}
				
				if(queryRes.getBoolean(14) == true) {
					numHousesRented++;
					/*String myDate = "2014/10/29 18:10:45";
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
						Date date = sdf.parse(myDate).getTime();
						long millis = date.getTime();*/
					java.sql.Date lastPayment = null;
					java.sql.Date datePosted = null;
					try {
						lastPayment = new java.sql.Date(sdf.parse(queryRes.getString(10)).getTime());
						datePosted = new java.sql.Date(sdf.parse(queryRes.getString(13)).getTime());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Listing propertyListing = new Listing(Boolean.parseBoolean(queryRes.getString(9)), lastPayment, 
													Integer.parseInt(queryRes.getString(11)), 
													Double.parseDouble(queryRes.getString(12)), datePosted, 
													Boolean.parseBoolean(queryRes.getString(14)), 
													Double.parseDouble(queryRes.getString(15)));
					
					Property newProperty = new Property(LoginController.getLandlord(queryRes.getString(1)), 
														Integer.parseInt(queryRes.getString(2)), queryRes.getString(3), 
														Integer.parseInt(queryRes.getString(4)), 
														Double.parseDouble(queryRes.getString(5)), 
														Boolean.parseBoolean(queryRes.getString(6)), 
														queryRes.getString(7), queryRes.getString(8), propertyListing);
					listOfProperties.add(newProperty);
				}
			}
			queryRes.close();
			statement.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		listOfHousesRented = new Property[listOfProperties.size()];
		for(int i = 0; i < listOfProperties.size(); i++ )
		{
			listOfHousesRented[i] = listOfProperties.get(i);
		}
	}
	
	/**
     * getNumHousesListed Method:
     * Gets the number of houses that have been listed past a specified period
     */
	public int getNumHousesListed() {
		return numHousesListed;
	}
	
	/**
     * getNumHousesRented Method:
     * Gets the number of houses that are rented past a specified period
     */
	public int getNumHousesRented() {
		return numHousesRented;
	}
	
	/**
     * getNumActiveListings Method:
     * Gets the number of houses that are currently listed past a specified period
     */
	public int getNumActiveListings() {
		return numActiveListings;
	}
	
	/**
     * getListOfHousesRented Method:
     * Gets an array of the houses that are currently rented past a specified period
     */
	public Property[] getListOfHousesRented() {
		return listOfHousesRented;
	}
	
	/**
	 * setNumHousesListed Method:
     * Sets the number of houses that have been listed past a specified period
	 * @param numHousesListed - The number of houses that have been listed past a specified period
	 */
	public void setNumHousesListed(int numHousesListed) {
		this.numHousesListed = numHousesListed;
	}
	
	/**
     * setNumHousesRented Method:
     * Sets the number of houses that are rented past a specified period
     * @param numHousesRented - The number of houses that are rented past a specified period
     */
	public void setNumHousesRented(int numHousesRented) {
		this.numHousesRented = numHousesRented;
	}
	
	/**
     * setNumActiveListings Method:
     * Sets the number of houses that are currently listed past a specified period
     * @param numActiveListings - The number of houses that are currently listed past a specified period
     */
	public void setNumActiveListings(int numActiveListings) {
		this.numActiveListings = numActiveListings;
	}
	
	/**
	 * getListOfHousesRented Method:
     * Gets an array of the houses that are currently rented past a specified period
	 * @param listOfHousedRented - The houses that are currently rented past a specified period
	 */
	public void setListOfHousedRented(Property[] listOfHousedRented) {
		this.listOfHousesRented = listOfHousedRented;
	}
	
	
}