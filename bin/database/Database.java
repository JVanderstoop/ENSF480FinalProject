/**
 * Database.java found in bin/database directory
 * @author Josh Vanderstoop- joshua.vanderstoop@ucalgary.ca
 * written for ENSF 480 Final Project
 */
package bin.database; 

import java.sql.*; 
 

public class Database {
    /**
     * Data members:
     * DBURL: String        - URL for the database
     * USERNAME: String     - username to access the given database 
     * PASSWORD: String     - password associated with the username above
     * connect: Connection  - connects to the database using the above parameters 
     */
    public final String DBURL;
    public final String USERNAME; 
    public final String PASSWORD; 
    public Connection connect; 

    /**
     * Database constructor 
     * @param DBURL URL for the database
     * @param USERNAME username to access the given database
     * @param PASSWORD password associated with the username above
     * called by super() in the Calculator constructor
     */
    public Database( String DBURL, String USERNAME, String PASSWORD)
    {
        this.DBURL = DBURL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    public Database()
    {
        this.DBURL = "jdbc:mysql://localhost:3306/ensf480"; 
        this.USERNAME = "YOURUSERNAME"; 
        this.PASSWORD = "YOURPASSWORD"; 
        if (initializeConnection())
        {
            //System.out.println("connected");
        }
        else
        {
            System.out.println("couldnt connect"); 
            System.exit(1);
        }
    }

    /**
     * initializeConnection method
     * uses connect and driverManager to obtain a connection to the SQL Database using the members of the current
     * object. 
     * will detect if the connection is not successful and print an error message
     * returns nothing 
     */
    public boolean initializeConnection()
    {
        try 
        { 
            connect = DriverManager.getConnection(this.DBURL, this.USERNAME, this.PASSWORD);
        } catch (SQLException e) 
        {
            return false;
        }
        return true;
    }

    public int update(String query) throws SQLException
    {
        int set; 
        PreparedStatement statement = connect.prepareStatement(query); 
        set = statement.executeUpdate(); 
        statement.close();
        return set;
    }


}
