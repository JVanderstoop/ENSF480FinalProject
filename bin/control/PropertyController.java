/**
 * PropertyController.java found in bin/control directory
 * @author Karl Winkler - Karl.Winkler@ucalgary.ca]
 * @editor Josh Vanderstoop- joshua.vanderstoop@ucalgary.ca 
 * written for ENSF 480 Final Project
 */
package bin.control;

import java.sql.*;
import java.util.*;
import bin.database.Database; /// change import 
import bin.domain.*; // change import

public class PropertyController{
	
	/**
	 * default constructor
	 * 
	 * empty because there is nothing to initialise and there is no reason for
	 * the methods to not be static
	 */
	public PropertyController(){}
	

	/**
	 *puts a new preperty into the property database
	 * 
	 * @param newProperty property to be put into database
	 * @return true if the insertion worked, false otherwise
	 */
	public boolean createNewProperty(Property newProperty) {
		/*
		 * create connection to db
		 * create INSERT query
		 * add property to database
		 * close connection
		 */
		Database db = new Database();
		String queryStr = "INSERT INTO Property VALUES('"
									       +newProperty.getLandlord().getUsername()
										   +"', "+newProperty.getHouseID()
										   +", '"+newProperty.getAddress()
										   +"', "+newProperty.getRooms()
										   +", "+newProperty.getBathrooms()
										   +", "+newProperty.getFurnished()
										   +", '"+newProperty.getQuadrant()
										   +"', '"+newProperty.getPropertyType()
										   +"', True"
										   +", DATE '"+newProperty.getListing().getLastPayment()
										   +"', "+newProperty.getListing().getPaymentPeriod()
										   +", "+newProperty.getListing().getRent()
										   +", DATE '"+newProperty.getListing().getDatePosted()
										   +"', False"
										   +", "+newProperty.getListing().getFeeAmount()
										   +");";
		try{
			int r = db.update(queryStr);
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		notifyRenter();
		return true;
		
	}
	
	
	/** 
	 * queries the database for all of the properties that match given data and
	 * sets the users recent query
	 *  
	 *  @param propertyType type of the property being queried for
	 *  @param numBed number of bedrooms in the house
	 *  @param numBath number of bathrooms in the house
	 *  @param furnished boolean for if the house is furnished or not
	 *  @param quadrant quadrant of the city the house is located in
	 *  @return array list of the properties matching the query
	 */
	public ArrayList<Property> getRentals(String username, String propertyType,
			int numBed, double numBath, boolean furnished, String quadrant) {
		
		ArrayList<Property> returnProperties = new ArrayList<Property>();
		/*
		 * create connection to database
		 * depending on what is null or not query database based on it
		 * lots of if statements? 
		 */
		Database db = new Database();
		String queryStr = "SELECT * FROM Property WHERE "
				+ "propertyType = '"+ propertyType
				+ "' AND rooms = "+ numBed
				+ " AND bathrooms = "+ numBath
				+ " AND furnished = "+ furnished
				+ " AND quadrant = '"+quadrant 
				+ "' AND postedOnline = true And rented = false"
				+ ";";
		try{
			ResultSet r = null; 
			PreparedStatement statement = db.connect.prepareStatement(queryStr); 
			r = statement.executeQuery(); 
			
			//create property objecte for all of the returned properties
			while(r.next()) {
				Listing l = new Listing(r.getBoolean(9), r.getDate(10), r.getInt(11), 
				r.getDouble(12), r.getDate(13), r.getBoolean(14), 
				r.getDouble(15));
				Landlord llord = LoginController.getLandlord(r.getString(1));
				Property p = new Property(llord, r.getInt(2), 
						r.getString(3), r.getInt(4), r.getDouble(5), 
						r.getBoolean(6), r.getString(7), r.getString(8), l 
						);
				if(validateProperty(p)){ 
					returnProperties.add(p);
				}
				else{
					setOnlineStatus(p.getHouseID(), false);
				}
			}
			statement.close();
			r.close();
		}
         
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		LoginController lc = LoginController.getInstance();
		lc.setRenterQuery(username, queryStr);
		
		return returnProperties;
	}

	/**
	 * queries the database for all of the properties that match given data and
	 * sets the users recent query
	 * @param query the users past query, as saved to the database. 
	 * @return the set of rental properties that match that search 
	 */
	public ArrayList<Property> getRentals(String query) {
		
		ArrayList<Property> returnProperties = new ArrayList<Property>();
		/*
		 * create connection to database
		 * depending on what is null or not query database based on it
		 * lots of if statements? 
		 */
		Database db = new Database();
		try{
			ResultSet r = null; 
			PreparedStatement statement = db.connect.prepareStatement(query); 
			r = statement.executeQuery(); 
			
			//create property objecte for all of the returned properties
			while(r.next()) {
				Listing l = new Listing(r.getBoolean(9), r.getDate(10), r.getInt(11), 
				r.getDouble(12), r.getDate(13), r.getBoolean(14), 
				r.getDouble(15));
				Landlord llord = LoginController.getLandlord(r.getString(1));
				Property p = new Property(llord, r.getInt(2), 
						r.getString(3), r.getInt(4), r.getDouble(5), 
						r.getBoolean(6), r.getString(7), r.getString(8), l 
						);
				if(validateProperty(p)){
					System.out.println("HEllo"); 
					returnProperties.add(p);
				}
				else{
					setOnlineStatus(p.getHouseID(), false);
				}
			}
			statement.close();
			r.close();
		}
         
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return returnProperties;
	}

	/**
	 * checks if the last payment was more than one month ago
	 * 
	 * @param p property to be checked
	 * @return true if the last payment was less than one month ago false otherwise
	 */
	private boolean validateProperty(Property p){
		java.sql.Date lastPayment = p.getListing().getLastPayment();
		//subract the payment period in months from the current date. 					1 month = 2.628* 10 ^9			* number of months on listing period
		java.sql.Date currentTime = new java.sql.Date(System.currentTimeMillis() - ( (long)(2.628 * Math.pow(10, 9))  * p.getListing().getPaymentPeriod()));
		if(lastPayment.before(currentTime)){
			return false;
		}
		return true;

	}



	/**
	 * searches DB for any property with the ID number requested
	 * @param ID	the ID number of the property that is requested 
	 * @return property that matches that ID
	 */
	public ArrayList<Property> getPropertyByID(int ID)
	{
		ArrayList<Property> toReturn = new ArrayList<Property>() ; 
		Database db = new Database();
		String queryStr = "SELECT * FROM Property WHERE ID = " + ID;
		try{
			ResultSet r = null; 
			PreparedStatement statement = db.connect.prepareStatement(queryStr); 
			r = statement.executeQuery(); 
			
			//create property objecte for all of the returned properties
			while(r.next()) {
				Listing l = new Listing(r.getBoolean(9), r.getDate(10), r.getInt(11), 
				r.getDouble(12), r.getDate(13), r.getBoolean(14), 
				r.getDouble(15));
				Landlord llord = LoginController.getLandlord(r.getString(1));
				toReturn.add( new Property(llord, r.getInt(2), 
						r.getString(3), r.getInt(4), r.getDouble(5), 
						r.getBoolean(6), r.getString(7), r.getString(8), l 
						));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null; 
		}
		return toReturn; 
	}	
	
	/**
	 * returns all of the fees due for any landlord
	 * 
	 * @param username username for the landlord
	 * @return double value for the sum of fees due for every property owned by 
	 * the landlord
	 */
	public double getFees(String username) {
		Database db = new Database();

		String queryStr = "SELECT * FROM Property WHERE "
				+ "landlordUsername='"+ username + "';";
		 
		try{
			ResultSet r = null; 
			PreparedStatement statement = db.connect.prepareStatement(queryStr); 
			r = statement.executeQuery();
			//create property objecte for all of the returned properties
			double sum = 0;
			while(r.next()) {
				sum += r.getDouble(15);
			}
			r.close();
			statement.close();
			return sum;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	/**
	 * sets the fees for every property owned by a given landlord
	 * 
	 * @param username username for the landlord
	 * @param fees value that all properties fees will be set to
	 * @return boolean to check if the update worked
	 */
	public boolean setFees(String username, double fees) {
		Database db = new Database();
		//set fees to 0 and last payment date to the current date on each property
		String queryStr = "UPDATE Property SET feeAmount = "+ fees + ", lastPayment = '" + new java.sql.Date(new java.util.Date().getTime()) + "' WHERE "
				+ "landlordUsername = '"+ username + "';";
		try{
			int r = db.update(queryStr);
			System.out.println(r + " Rows affected");
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false; 
		}
		return true; 
		
	}

	/**
	 * sets the payment period for every property owned by a given landlord
	 * 
	 * @param username username for the landlord
	 * @param period value that all properties periods will be set to
	 * @return boolean to check if the update worked
	 */
	public boolean setPaymentPeriod(String username, int period) {
		Database db = new Database();
		//set fees to 0 and last payment date to the current date on each property
		String queryStr = "UPDATE Property SET paymentPeriod = "+ period + " WHERE "
				+ "landlordUsername = '"+ username + "';";
		try{
			int r = db.update(queryStr);
			System.out.println(r + " Rows affected");
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false; 
		}
		return true; 
	}
	
	
	
	/**
	 * simulate a notification being sent to the renters
	 * 
	 * there is some code that is commented out that could be used to select the correct users to
	 * be notified
	 */
	public void notifyRenter() {
		
//		String queryStr = "SELECT * FROM Renter";
//		try{
//			Result r = db.query(queryStr);
//			//create property objecte for all of the returned properties
//			while(r.next()) {
//				if(r.getString(2).matches("(.*)(" + propertyType + "|" + numBed + "|" + numBath + "|" + furnished + "|" + quadrant")(.*)")) {
//					System.out.println("Renter " + r.getString(1) + " has been notified");				}
//			}
//		}
//		catch(SQLException e) {
//			System.err.print(e);
//		}
		System.out.println("Renters have been notified");
	}
	
	/**
	 * sets a specific fee for a property matching property ID
	 * @param ID ID of property
	 * @param fee fee value to be set
	 */
	public void setThisFee(int ID, double fee) {
		
		Database db = new Database();
		String queryStr = "UPDATE Property SET Fees="+ fee +" WHERE "
				+ "ID='"+ ID + "';";
		try{
			int r = db.update(queryStr);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * set the rented value in the properties database
	 * 
	 * @param ID id of property to be changed
	 * @param rentalStatus status to be set
	 */
	public void setRented(int ID, boolean rentalStatus) {
		
		Database db = new Database();
		String queryStr = "UPDATE Property SET rented="+ rentalStatus +" WHERE "
				+ "ID='"+ ID + "';";
		try{
			int r = db.update(queryStr);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * set the online status of a property
	 * 
	 * @param ID id of the property to be changed
	 * @param onlineStatus status to be set
	 */
	public void setOnlineStatus(int ID, boolean onlineStatus) {
		
		Database db = new Database();
		String queryStr = "UPDATE Property SET postedOnline="+ onlineStatus +" WHERE "
				+ "ID='"+ ID + "';";
		try{
			int r = db.update(queryStr);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
