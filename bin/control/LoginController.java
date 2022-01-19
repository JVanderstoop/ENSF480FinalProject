/**
 * LoginController.java found in bin/control directory
 * @author Karl Winkler - Karl.Winkler@ucalgary.ca 
 * @editor Josh Vanderstoop- joshua.vanderstoop@ucalgary.ca
 * written for ENSF 480 Final Project
 */
package bin.control;

import bin.domain.Person; 
import bin.database.Database; 
import bin.domain.Landlord; 
import bin.domain.Renter; 
import bin.domain.Manager;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class LoginController{
	/*
	singular instance of the controller
	*/
	private static LoginController loginInstance;
	
    /**
	 * set the login instance to the newly created instance 
	 */
	public LoginController() {
		loginInstance = this;

	}
	
	/**
	 * checks if login credentials are correct and returns a person object if they exist
	 * 
	 * 
	 * @param username username of the person loging in
	 * @param password password of the person loging in
	 * @return person if tehy exist, null otherwise
	 */
	public Person login(String username, String password) {
		/*
		 * recieve login form
		 * create database object
		 * format ResultSets as Person object
		 * return ResultSets of ResultSet of query
		 */	
		Database db = new Database();
		try{
			String queryStr = "SELECT * FROM Password WHERE username='"+username
					+"' AND password='"+password+"'";
			ResultSet r = null; 
			PreparedStatement statement = db.connect.prepareStatement(queryStr); 
			r = statement.executeQuery(); 
			
			Person p = null; 
			if (r.next()) {
			    int accountType = r.getInt(4);
				String name = r.getString(3);
				String lastQuery = r.getString(5);
				String email = r.getString(6);
				r.close();
				switch(accountType) 
				{
				case 1:	
					return new Renter(name, username, password, lastQuery); 
				case 2:
					return new Landlord(name, username, password, email); 
				case 3:
					return new Manager(name, username, password); 
				default:
					return null;
				
				}
			}
			statement.close();
			
			r.close();
			return p;
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.exit(1);
			return null;
		}
			
	}
	
	/**
	 * create a new account and put it into the correct databases
	 * 
	 * @param username username of the new account
	 * @param password password of the new account
	 * @param name name of the new persons account
	 * @param email email if the account is a landlord
	 * @param accountType type of the account
	 * @return boolean of whether the query worked
	 */
	public boolean signup(String username, String password, String name, String email, int accountType) {
		/*
		 * create INSERT query
		 */
		Database db = new Database();
		try{
			String queryStr = "INSERT INTO Password\nVALUES("  
					+"'" +username
					+"', '"+ password
					+"', '"+ name
					+"', '"+ accountType
					+"', '"+ email
					+"', "+ null
					+");";
			int r = db.update(queryStr);
			return true;
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	/**
	 * gets a Renter object from the database
	 * 
	 * @param username of the desired Renter
	 * @return Renter object
	 */
	public static Renter getRenter(String username) {
		Database db = new Database();
		try{
			String queryStr = "SELECT * FROM Password WHERE username='"+username+"'";
			ResultSet r = null; 
			PreparedStatement statement = db.connect.prepareStatement(queryStr); 
			r = statement.executeQuery();
			if(r.next()) {
				Renter rent = new Renter(r.getString(3), r.getString(1), r.getString(2), r.getString(5));
				r.close();
				statement.close();
				return rent;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null; 
	}
	
	/**
	 * gets a landlord object from the database
	 * 
	 * @param username of the desired landlord
	 * @return landlord object
	 */
	public static Landlord getLandlord(String username) {
		Database db = new Database();
		try{
			String queryStr = "SELECT * FROM Password WHERE username='"+username+"'";
			ResultSet r = null; 
			PreparedStatement statement = db.connect.prepareStatement(queryStr); 
			r = statement.executeQuery();
			if(r.next()) {
				Landlord ll = new Landlord(r.getString(3), r.getString(1), r.getString(2), r.getString(5));
				r.close();
				statement.close();
				return ll;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null; 
	}

	/**
	 * gets a manager object from the database
	 * 
	 * @param username of the desired manager
	 * @return manager object
	 */
	public static Manager getManager(String username) {
		Database db = new Database();
		try{
			String queryStr = "SELECT * FROM Password WHERE username='"+username+"'";
			ResultSet r = null; 
			PreparedStatement statement = db.connect.prepareStatement(queryStr); 
			r = statement.executeQuery();
			if(r.next()) {
				Manager m = new Manager(r.getString(3), r.getString(1), r.getString(2));
				r.close();
				statement.close();
				return m;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null; 
	}
	
	/**
	 * sets the renters query attribute 
	 * 
	 * @param username username of the renter
	 * @param query query string
	 */
	public void setRenterQuery(String username, String query) {
		
		Database db = new Database();
		String queryStr = "UPDATE Password SET recentSearch= ? WHERE "
				+ "username='"+ username + "'";
		try{
			PreparedStatement statement = db.connect.prepareStatement(queryStr); 
			statement.setString(1, query);
			statement.executeUpdate(); 
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * returns the instance of the singularity or creates it if it does not exist yet
	 * @return loginInstance
	 */
	public static LoginController getInstance() {
		if(loginInstance == null) {
			loginInstance = new LoginController();
		}
		return loginInstance;
	}
	
}
