/**
 * GUI.java found in bin/gui directory
 * @author Josh Vanderstoop- joshua.vanderstoop@ucalgary.ca 
 * written for ENSF 480 Final Project
 */
package bin.gui; 

import bin.control.*;
import bin.domain.*;

import javax.swing.*;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random; 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI extends Main implements ActionListener{

    /**
     * declaring attributes of the GUI
     */

    public ActionEvent e; 
    /**
     * what the user sees 
     */
    public JFrame mainWindow = new JFrame("GUI");

    public JPanel   userInfoPanel = new JPanel(), 
                    signInPanel = new JPanel(),
                    editPropertyPanel = new JPanel(new FlowLayout());; 

    /**
     * textfields for user inputs
     */
    private TextField   usernameField = new TextField(16),
                        passwordField = new TextField(16),
                        numberOfBedroomsField = new TextField(25),
                        numberOfBathroomsField = new TextField(25),
                        rentField = new TextField(10), 
                        addressField = new TextField(30), 
                        propertyIDField = new TextField(16), 
                        nameField = new TextField(16), 
                        emailField = new TextField(48), 
                        dateField = new TextField(11), 
                        messageField = new TextField(80), 
                        searchByIDField = new TextField(16), 
                        feesField = new TextField(16), 
                        periodField = new TextField(16);

    /**
     * note the use of buttons as members so that any operation can utilize them
     */
    public Button   renterButton = new Button("Renter login"), 
                    landlordButton = new Button("Landlord login"), 
                    managerButton = new Button("Manager login"), 
                    useAsGuest = new Button("Continue as Guest"), 
                    signUpButton = new Button("Sign up"), 
                    logInButton = new Button("Log in"), 
                    searchButton = new Button("Search"),  
                    recentSearchButton = new Button("View Results from Last Search"), 
                    getNotificicationsButton = new Button("Get notified for your recent search"), 
                    registerPropertyButton = new Button("Register a new property"), 
                    editPropertyButton = new Button("edit selected property(update, delete)"), 
                    completeRegistrationButton = new Button("Complete Registration"), 
                    payFeesButton = new Button("Pay All Fees"), 
                    addFeesButton = new Button("Update fees and Return Home"),
                    logOutButton = new Button("Log Out"), 
                    confirmSignUpButton = new Button("Confirm Signing Up"), 
                    findUserButton = new Button("Find User"), 
                    viewReportButton = new Button("View Report"), 
                    emailLandlordButton = new Button("Email Landlord"), 
                    goBackButton = new Button("Go Back"), 
                    updateButton = new Button("Update"), 
                    returnHomeButton = new Button("Return Home"), 
                    updatePeriodButton = new Button("Update period and Return Home"); 

    /**
     * String Arrays to set up the combo boxes below 
     */
    private String[]    propertyTypeArray = {"Apartment", "Attached House", "Detached House", "Townhouse", "Basement Suite"},
                        furnishingArray = {"Furnished", "Unfurnished"}, 
                        quadrantArray = {"NW", "NE", "SW", "SE"}, 
                        signUpAccountArray = {"Renter", "Landlord", "Manager"}, 
                        listingStatusArray = {"Active", "Rented", "Cancelled", "Suspended"};

    /**
     * combo boxes for user ease and minimal user error in typing string values
     */                 
    public JComboBox<String>    propertyTypeBox = new JComboBox<String>(propertyTypeArray), 
                                furnishingBox = new JComboBox<String>(furnishingArray), 
                                quadrantBox = new JComboBox<String>(quadrantArray), 
                                accountTypeBox = new JComboBox<String>(signUpAccountArray), 
                                listingStatusBox = new JComboBox<String>(listingStatusArray);
         
    /**
     * for landlords to add properties with random ID's
     */
    public int propertyID = 0; 

    /**
     * constrcutor for the GUI to build the main window
     */
    public GUI()
    {
        mainWindow.setLayout(new FlowLayout());
        mainWindow.setSize(900, 500);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setResizable(false);        
    }

    /**
     * insert pages for the user to understand how the app works and begin to use it
     */
    public void begin()
    {
        //randomize the propertyID number so there are less duplicates 
        Random rand = new Random();
        propertyID = rand.nextInt(1000) + 1; //any number between 1 - 1000
        //start to build the panels
        generateUserInfoPanel(); 
        generateSignInPanel(); 
        mainWindow.getContentPane().removeAll();//clear window 
        mainWindow.add(userInfoPanel);
        mainWindow.add(signInPanel);
        mainWindow.validate();
        mainWindow.repaint();
        mainWindow.setVisible(true); //show to user
    }

    /**
     * create the textpanel and title to show the user
     */
    public void generateUserInfoPanel()
    {
        userInfoPanel.setLayout(new BorderLayout());
        userInfoPanel.setPreferredSize(new Dimension(850, 400));
        userInfoPanel.add(informationLabel(), BorderLayout.CENTER);     
    }

    /**
     * @return text area where the user can learn about the app and its creators
     */
    public JTextArea informationLabel()
    {
        JTextArea informationLabel = new JTextArea(
            "          Welcome! This is the property management system for Group 20.\n"+ 
            "This application was written by Josh Vanderstoop, Karl Winkler, and Bradley Wachs,\n" +
            "for the ENSF 480 Final Project, as provided by Dr. Moussavi from the University Of \n" + 
            "Calgary. If you have not already connected to your own local database, do the following:\n" + 
            "   - open the Database.java file found in bin/database.\n" +
            "   - change the DBURL, USERNAME, and PASSWORD declarations on lines 35 - 37 to suit your \n" + 
            "       personal mySQL URL, username and password. If you do not complete this step you \n" + 
            "       not be able to use the program.\n\n" + 
            "Please choose an option below to either log in as a registered user, or continue as a guest\n"
        );
        Font userInformation = new Font(null, 0, 15);
        informationLabel.setFont(userInformation);
        informationLabel.setPreferredSize(new Dimension(850, 350));
        informationLabel.setEditable(false);
        informationLabel.setLineWrap(true);
        informationLabel.setFont(userInformation);
        return informationLabel;
    }

    /**
     * add the buttons and make them active to users can begin to use the app
     */
    public void generateSignInPanel()
    {
        signInPanel.setLayout(new GridLayout(1, 5, 20, 20)); 
        signInPanel.setPreferredSize(new Dimension(800, 50));
        signInPanel.add(renterButton); 
        renterButton.addActionListener(this);
        signInPanel.add(landlordButton); 
        landlordButton.addActionListener(this);
        signInPanel.add(managerButton); 
        managerButton.addActionListener(this);
        signInPanel.add(useAsGuest);
        useAsGuest.addActionListener(this);
        signInPanel.add(signUpButton); 
        signUpButton.addActionListener(this); 
    }

    /**
     * creates a home page for the user
     * @param userType defines what kind of user is present[1 = unregistered, 2 = registered, 3 = landlord, 4 = manager]
     */
    public void buildHomePage(int userType)
    {
        mainWindow.setVisible(false);
        mainWindow.getContentPane().removeAll();
        if (userType == 0) //guest user. no sign in needed. 
        {
            //set up the panel specific to the home page of an unregistered renter
            JPanel searchPanel = new JPanel(new GridLayout(6, 2, 10, 20));
            searchPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Search Properties That Match:"));
            searchPanel.add(new JLabel("Property Type: ", SwingConstants.RIGHT));
            searchPanel.add(propertyTypeBox); 
            propertyTypeBox.addActionListener(this);
            searchPanel.add(new JLabel("Number of Bedrooms: ", SwingConstants.RIGHT));
            searchPanel.add(numberOfBedroomsField); 
            searchPanel.add(new JLabel("Number of Bathrooms: ", SwingConstants.RIGHT));
            searchPanel.add(numberOfBathroomsField); 
            searchPanel.add(new JLabel("Furnished/Unfurnished: ", SwingConstants.RIGHT));
            searchPanel.add(furnishingBox);
            furnishingBox.addActionListener(this);
            searchPanel.add(new JLabel("City Quadrant: ", SwingConstants.RIGHT));
            searchPanel.add(quadrantBox);
            quadrantBox.addActionListener(this);
            searchPanel.add(new JLabel(""));
            searchPanel.add(searchButton); 
            searchButton.addActionListener(this);
            mainWindow.add(searchPanel);  
        }
        if (userType == 1 ) //registered Renter
        {
            //set up the page for a registered renter. 
            JPanel searchPanel = new JPanel(new GridLayout(8, 2, 10, 20));
            searchPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Search Properties That Match:"));
            searchPanel.add(recentSearchButton); 
            recentSearchButton.addActionListener(this);
            searchPanel.add(getNotificicationsButton); 
            getNotificicationsButton.addActionListener(this);
            searchPanel.add(new JLabel("Property Type: ", SwingConstants.RIGHT));
            propertyTypeBox.setPreferredSize(new Dimension(20, 10)); 
            searchPanel.add(propertyTypeBox); 
            propertyTypeBox.addActionListener(this);
            searchPanel.add(new JLabel("Number of Bedrooms: ", SwingConstants.RIGHT));
            searchPanel.add(numberOfBedroomsField); 
            searchPanel.add(new JLabel("Number of Bathrooms: ", SwingConstants.RIGHT));
            searchPanel.add(numberOfBathroomsField); 
            searchPanel.add(new JLabel("Furnished/Unfurnished: ", SwingConstants.RIGHT));
            searchPanel.add(furnishingBox);
            furnishingBox.addActionListener(this);
            searchPanel.add(new JLabel("City Quadrant: ", SwingConstants.RIGHT));
            searchPanel.add(quadrantBox);
            quadrantBox.addActionListener(this);
            searchPanel.add(new JLabel("")); 
            searchPanel.add(searchButton); 
            searchButton.addActionListener(this);
            searchPanel.add(new JLabel("")); 
            searchPanel.add(logOutButton); 
            logOutButton.addActionListener(this);
            mainWindow.add(searchPanel);  
        }
        if (userType == 2 ) //landlord
        {
            //set up a homepage for landlords
            JPanel landlordHomePanel = new JPanel(new GridLayout(5, 2, 10, 20));
            landlordHomePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Landlord Home Page"));
            landlordHomePanel.add(registerPropertyButton); 
            registerPropertyButton.addActionListener(this);
            landlordHomePanel.add(new JLabel("")); 
            landlordHomePanel.add(new JLabel("Your Fees Due: ", SwingConstants.RIGHT)); 
            //get database to find landlord fees due from all properties with landlordusername
            PropertyController pc = new PropertyController(); 
            landlordHomePanel.add(new JLabel( ""+ pc.getFees(super.user.getUsername())  , SwingConstants.CENTER));
            landlordHomePanel.add(new JLabel("Property to edit ID#: ", SwingConstants.RIGHT)); 
            landlordHomePanel.add(propertyIDField); 
            landlordHomePanel.add(new JLabel("")); 
            landlordHomePanel.add(editPropertyButton);  
            editPropertyButton.addActionListener(this);  
            landlordHomePanel.add(payFeesButton); 
            payFeesButton.addActionListener(this);
            landlordHomePanel.add(logOutButton); 
            logOutButton.addActionListener(this);
            mainWindow.add(landlordHomePanel); 
        }
        if (userType == 3) //manager
        {
            //set up the manager homepage
            JPanel managerHomePanel = new JPanel(new GridLayout(6, 3, 20, 10)); 
            managerHomePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Manager Home Page"));
            //edit properties (add fees, remove listing)
            managerHomePanel.add(new JLabel("Property to edit ID#: ", SwingConstants.RIGHT)); 
            managerHomePanel.add(propertyIDField); 
            managerHomePanel.add(editPropertyButton);  
            editPropertyButton.addActionListener(this); 
            //find users 
            managerHomePanel.add(new JLabel("Locate User with Username, Account Type: ", SwingConstants.RIGHT));
            managerHomePanel.add(usernameField); 
            managerHomePanel.add(accountTypeBox);
            managerHomePanel.add(new JLabel("")); 
            managerHomePanel.add(new JLabel("")); 
            managerHomePanel.add(findUserButton); 
            findUserButton.addActionListener(this);
            //view report
            managerHomePanel.add(new JLabel("View summary Report starting on Date: ", SwingConstants.RIGHT));
            dateField.setText("[YYYY-MM-DD]"); //format for user to follow 
            managerHomePanel.add(dateField); 
            managerHomePanel.add(viewReportButton); 
            viewReportButton.addActionListener(this); 
            //search by property ID#
            managerHomePanel.add(new JLabel("Search Properties by ID#: ", SwingConstants.RIGHT)); 
            managerHomePanel.add(searchByIDField);
            managerHomePanel.add(searchButton); 
            searchButton.addActionListener(this);

            //log out
            managerHomePanel.add(new JLabel("")); 
            managerHomePanel.add(new JLabel(""));
            managerHomePanel.add(logOutButton); 
            logOutButton.addActionListener(this);
            mainWindow.add(managerHomePanel); 
        }
        mainWindow.validate();
        mainWindow.repaint();
        mainWindow.setVisible(true);
    }

    /**
     * will let the user enter their login information to begin use as a registered member
     */
    public void loginPrompt()
    {
        //create panel
        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 10, 20));
        loginPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Log in"));
        loginPanel.add(new JLabel("Username: ", SwingConstants.RIGHT));
        loginPanel.add(usernameField); 
        loginPanel.add(new JLabel("Password: ", SwingConstants.RIGHT)); 
        loginPanel.add(passwordField); 
        loginPanel.add(new JLabel("")); 
        loginPanel.add(logInButton); 
        logInButton.addActionListener(this);
        //update interface to show changes
        mainWindow.setVisible(false); 
        mainWindow.getContentPane().removeAll();    
        mainWindow.add(loginPanel);
        mainWindow.validate();
        mainWindow.repaint();
        mainWindow.setVisible(true); 
    }

    /**
     * registering a property, availible to landlords only
     */
    public void registerProperty()
    {
        mainWindow.setVisible(false);
        mainWindow.getContentPane().removeAll();
        JPanel registerPropertyPage = new JPanel(new GridLayout(9, 2, 10, 10)); 
        registerPropertyPage.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Register Property"));        
        registerPropertyPage.add(new JLabel("Property Type: ", SwingConstants.RIGHT));
        propertyTypeBox.setPreferredSize(new Dimension(20, 10)); 
        registerPropertyPage.add(propertyTypeBox); 
        propertyTypeBox.addActionListener(this);
        registerPropertyPage.add(new JLabel("Number of Bedrooms: ", SwingConstants.RIGHT));
        registerPropertyPage.add(numberOfBedroomsField); 
        registerPropertyPage.add(new JLabel("Number of Bathrooms: ", SwingConstants.RIGHT));
        registerPropertyPage.add(numberOfBathroomsField); 
        registerPropertyPage.add(new JLabel("Furnished/Unfurnished: ", SwingConstants.RIGHT));
        registerPropertyPage.add(furnishingBox);
        furnishingBox.addActionListener(this);
        registerPropertyPage.add(new JLabel("City Quadrant: ", SwingConstants.RIGHT));
        registerPropertyPage.add(quadrantBox);
        quadrantBox.addActionListener(this);
        registerPropertyPage.add(new JLabel("Monthly Rent: ", SwingConstants.RIGHT)); 
        registerPropertyPage.add(rentField); 
        registerPropertyPage.add(new JLabel("Address: ", SwingConstants.RIGHT)); 
        registerPropertyPage.add(addressField); 
        registerPropertyPage.add(new JLabel("Email you would like to be contacted at: ", SwingConstants.RIGHT)); 
        registerPropertyPage.add(emailField); 

        registerPropertyPage.add(new JLabel("")) ; 
        registerPropertyPage.add(completeRegistrationButton); 
        completeRegistrationButton.addActionListener(this);
        mainWindow.add(registerPropertyPage); 
        mainWindow.validate();
        mainWindow.repaint();
        mainWindow.setVisible(true);
    }

    /**
     * unregistered users who want to sign up can use this to become registered 
     * @param accountType defines what kind of user they plan to be, will prompt for additional information as needed 
     */
    public void signUserUp(int accountType)
    {
        mainWindow.setVisible(false);
        mainWindow.getContentPane().removeAll();
        JPanel signUpPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        signUpPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Register User"));
        if(accountType==1 || accountType == 2) //renter or manager 
        {
            signUpPanel.setLayout(new GridLayout(4, 2, 10, 10));
        }
        if(accountType ==2) //landlord specific entry. other users will not need this
        {
            signUpPanel.add(new JLabel("Your Email: ", SwingConstants.RIGHT)); 
            signUpPanel.add(emailField);
            signUpPanel.setLayout(new GridLayout(5, 2, 10, 10));
        }
        //common entries
        signUpPanel.add(new JLabel("Your Name: ", SwingConstants.RIGHT)); 
        signUpPanel.add(nameField); 
        signUpPanel.add(new JLabel("New Username: ", SwingConstants.RIGHT));
        signUpPanel.add(usernameField); 
        signUpPanel.add(new JLabel("Your Password: ", SwingConstants.RIGHT)); 
        signUpPanel.add(passwordField);  
        signUpPanel.add(new JLabel(""));
        signUpPanel.add(confirmSignUpButton); 
        confirmSignUpButton.addActionListener(this);
        mainWindow.add(signUpPanel); 
        mainWindow.validate();
        mainWindow.repaint();
        mainWindow.setVisible(true);
    }

    /**
     * displays the results of a users search in a tabbed pane with each matching property on a new tab
     * @param results the set of results for the property query
     */
    public void displaySearchResults(ArrayList<Property> results)
    {
        mainWindow.setVisible(false);
        mainWindow.getContentPane().removeAll();
        JTabbedPane searchResultsTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        Listing listingInfo = new Listing();  //to ensure that the listing is active

        for(int i =0; i < results.size(); i++) //to show every one of the results
        {
            listingInfo = results.get(i).getListing(); //set the listing to be for the current property
            if (listingInfo.getPostedOnline() == true)
            {
                searchResultsTabbedPane.addTab("Property: " + results.get(i).getHouseID(), createPropertyPanel(results.get(i), listingInfo));
                //add the new tab with the ID as a header, and the property information in the body of the tab. 
            }
        }
        mainWindow.add(searchResultsTabbedPane);
        JPanel contactPanel = new JPanel(new FlowLayout()); 
        contactPanel.setPreferredSize(new Dimension(800, 100));
        messageField.setText("[Enter the message you would like to send]");
        propertyIDField.setText("[Enter the Properties ID#]");
        emailField.setText("[Enter your email]"); 
        contactPanel.add(emailField); 
        contactPanel.add(propertyIDField);  
        contactPanel.add(messageField);
        contactPanel.add(emailLandlordButton); 
        emailLandlordButton.addActionListener(this);
        contactPanel.add(goBackButton); 
        goBackButton.addActionListener(this);
        mainWindow.add(contactPanel); 
        mainWindow.validate();
        mainWindow.repaint();
        mainWindow.setVisible(true);
    }
    
    /**
     * builds a page for the specific property that was given to the method
     * @param toVeiw the property the user wants to see 
     * @param toDisplay the listing information to show 
     * @return a panel that will be held within the tab as the only component
     */
    public JPanel createPropertyPanel(Property toVeiw, Listing toDisplay)
    {
        JPanel propertyViewer = new JPanel(new GridLayout(3, 2, 100, 10)); 
        propertyViewer.setPreferredSize(new Dimension(850, 300));
        Font viewPropertyFont = new Font(null, 0, 20);
        JLabel addressLabel = new JLabel("Address: " + toVeiw.getAddress(), SwingConstants.CENTER);
        addressLabel.setFont(viewPropertyFont); 
        propertyViewer.add(addressLabel); 
        JLabel numofRoomsLabel = new JLabel("Number of Bedrooms: " + toVeiw.getRooms(), SwingConstants.CENTER);
        numofRoomsLabel.setFont(viewPropertyFont); 
        propertyViewer.add(numofRoomsLabel); 
        JLabel numofBathroomsLabel = new JLabel("Number of Bathrooms: " + toVeiw.getBathrooms(), SwingConstants.CENTER);
        numofBathroomsLabel.setFont(viewPropertyFont); 
        propertyViewer.add(numofBathroomsLabel); 
        JLabel isFurnishedLabel; 
        //furnishing information
        if (toVeiw.getFurnished() == false)
        {
            isFurnishedLabel = new JLabel("This property does not come furnished.", SwingConstants.CENTER); 
        }
        else
        {
            isFurnishedLabel = new JLabel("This property comes furnished.", SwingConstants.CENTER); 
        }
        isFurnishedLabel.setFont(viewPropertyFont);
        propertyViewer.add(isFurnishedLabel);
        JLabel quadrantLabel = new JLabel("City Quadrant: " + toVeiw.getQuadrant(), SwingConstants.CENTER);
        quadrantLabel.setFont(viewPropertyFont); 
        propertyViewer.add(quadrantLabel);
        JLabel rentLabel = new JLabel("Monthly Rent: " + String.valueOf(toDisplay.getRent()), SwingConstants.CENTER); 
        rentLabel.setFont(viewPropertyFont); 
        propertyViewer.add(rentLabel); 
        return propertyViewer; 
    }

    /**
     * give landlords and managers the opportunity to change the status of a listing. 
     */
    public void editProperty()
    {
        mainWindow.setVisible(false);
        editPropertyPanel.setPreferredSize(new Dimension(850, 100));
        editPropertyPanel.add(new JLabel("Set Listing status: ")); 
        editPropertyPanel.add(listingStatusBox); 
        editPropertyPanel.add(updateButton);
        mainWindow.add(editPropertyPanel); 
        updateButton.addActionListener(this);
        mainWindow.validate();
        mainWindow.repaint();
        mainWindow.setVisible(true);
    }

    /**
     * shows the manager the report that they requested in a panel on the main window. 
     * @param report the report created with the users inputted date
     * @param date the date that the report begins on
     */
    public void showReport(Report report, String date)
    {
        mainWindow.setVisible(false);
        mainWindow.getContentPane().removeAll();
        JPanel reportPanel = new JPanel(new BorderLayout());
        reportPanel.setPreferredSize(new Dimension(850, 450)); 
        reportPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Summary Report"));
        JPanel embeddedPanel = new JPanel(new GridLayout(3, 1, 10, 10)); 
        embeddedPanel.setPreferredSize(new Dimension(400, 200));
        Font reportFont = new Font(null, 0, 15);

        JLabel numberListed = new JLabel("Total Number of houses Listed since " + date + ": "+ report.getNumHousesListed() , SwingConstants.CENTER );
        numberListed.setFont(reportFont); 
        embeddedPanel.add(numberListed); 

        JLabel numberRented = new JLabel("Total Number of houses Rented since " + date + ": " + report.getNumHousesRented() , SwingConstants.CENTER );
        numberRented.setFont(reportFont); 
        embeddedPanel.add(numberRented); 

        JLabel activeListings = new JLabel("Total Number of active listings since " + date + ": " + report.getNumActiveListings() , SwingConstants.CENTER );
        activeListings.setFont(reportFont); 
        embeddedPanel.add(activeListings); 

        String listOfRentedHouses = ""; //to put in text area
        String error = "";
         
        for(int i = 0; i < report.getListOfHousesRented().length; i++)
        { 
            error += report.getListOfHousesRented()[i].getLandlord().getUsername();
            error += report.getListOfHousesRented()[i].getHouseID();
            error += report.getListOfHousesRented()[i].getAddress();
            listOfRentedHouses += "  Land owner:  " + report.getListOfHousesRented()[i].getLandlord().getUsername()  + "   Property ID:  " + report.getListOfHousesRented()[i].getHouseID() + "     Address:  " + report.getListOfHousesRented()[i].getAddress() + "\n"; 
        }
  
        //the text area that holds the list of rented houses
        JTextArea listOfRented = new JTextArea(listOfRentedHouses); 
        listOfRented.setSize(new Dimension(400, 200)); 
        listOfRented.setLineWrap(true);
        listOfRented.setEditable(false);
        listOfRented.setVisible(true);
        //a pane that will let users scroll through the text area above
        JScrollPane scroll = new JScrollPane(listOfRented); 
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        reportPanel.add(embeddedPanel, BorderLayout.WEST); 
        reportPanel.add(scroll, BorderLayout.EAST); 
        reportPanel.add(returnHomeButton, BorderLayout.SOUTH); 
        returnHomeButton.addActionListener(this);
        mainWindow.add(reportPanel);
        mainWindow.validate();
        mainWindow.repaint();
        mainWindow.setVisible(true);
    }

    /**
     * when renters search for properties, this method will sort their search parameters and 
     * @return the results of the search
     */
    public ArrayList<Property> renterSearch()
    {
        int numofRooms = 0; //for parsing values into 
        double numofBaths = 0; 
        int failureFlag = 0; //if there is an exception, throw flag 
        try
        {
            numofRooms = Integer.parseInt(numberOfBedroomsField.getText()); //try to parse int
        } catch (NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(mainWindow, "Please enter an integer value for the number of bedroom field.\n Ex.) 1");
            failureFlag = 1; //if caught, need to not search
        }
        try
        {
            numofBaths = Double.parseDouble(numberOfBathroomsField.getText()); //try to parse double
        } catch (NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(mainWindow, "Please enter a double value for the number of bedroom field.\n Ex.) 1.5");
            failureFlag = 1; //if caught, need to not search
        }

        if (failureFlag == 1)
        {
            return null; //dont search with current values, will prompt for other values
        }
        
        if(super.user.getUsername() == null)
        {
            super.user.setUsername("guest1234"); //this username setting will only apply to renters that are not registered, and thus will not need a query saved
        }
        //to build the search from the users inputs
        PropertyController toSearch = new PropertyController(); 
        Boolean furnishings = false; 
        String selection = (String)furnishingBox.getSelectedItem();
        if (selection.equals("Furnished"))
        {
            furnishings = true; 
        }
        ArrayList<Property> searchResults = toSearch.getRentals(
            super.user.getUsername(),
            (String)propertyTypeBox.getSelectedItem(), 
            numofRooms, 
            numofBaths, 
            furnishings,
            (String)quadrantBox.getSelectedItem()
        ); 

        return searchResults; 
    }



    /**
     * for managers to view a renter based on their information. 
     * @param r renter to be viewed
     */
    public void displayRenterProfile(Renter r)
    {
        mainWindow.setVisible(false);
        mainWindow.getContentPane().removeAll();
        JPanel renterProfile = new JPanel(new GridLayout(5, 1, 10, 10));
        renterProfile.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Renter Profile"));
        Font font = new Font(null, 0, 15);

        JLabel nameLabel = new JLabel("Name: " + r.getName(), SwingConstants.CENTER); 
        nameLabel.setFont(font);
        renterProfile.add(nameLabel); 

        JLabel usernameLabel = new JLabel("Username: " + r.getUsername(), SwingConstants.CENTER); 
        usernameLabel.setFont(font);
        renterProfile.add(usernameLabel);

        JLabel passwordLabel = new JLabel("Password: " + r.getPassword(), SwingConstants.CENTER); 
        passwordLabel.setFont(font);
        renterProfile.add(passwordLabel);

        //for the saved search 
        JTextArea queryArea = new JTextArea(r.getSearchQuery()); 
        queryArea.setSize(new Dimension(700, 200)); 
        queryArea.setLineWrap(true);
        queryArea.setEditable(false);
        queryArea.setVisible(true);
        renterProfile.add(queryArea); 

        renterProfile.add(returnHomeButton, BorderLayout.SOUTH); 
        returnHomeButton.addActionListener(this);
        mainWindow.add(renterProfile); 
        mainWindow.validate();
        mainWindow.repaint();
        mainWindow.setVisible(true);
    }

    /**
     * for managers to view a landlord based on their information
     * @param l
     */
    public void displayLandlordProfile(Landlord l)
    {
        mainWindow.setVisible(false);
        mainWindow.getContentPane().removeAll();
        JPanel landlordProfile = new JPanel(new GridLayout(8, 2, 15, 20)); 
        landlordProfile.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Landlord Profile"));
        Font font = new Font(null, 0, 15);

        JLabel nameLabel = new JLabel("Name: " + l.getName(), SwingConstants.CENTER); 
        nameLabel.setFont(font);
        landlordProfile.add(nameLabel); 

        JLabel usernameLabel = new JLabel("Username: " + l.getUsername(), SwingConstants.CENTER); 
        usernameLabel.setFont(font);
        landlordProfile.add(usernameLabel);

        JLabel passwordLabel = new JLabel("Password: " + l.getPassword(), SwingConstants.CENTER); 
        passwordLabel.setFont(font);
        landlordProfile.add(passwordLabel);

        JLabel emailLabel = new JLabel("Profile Email: " + l.getContactEmail(), SwingConstants.CENTER); 
        emailLabel.setFont(font);
        landlordProfile.add(emailLabel);

        //get the fees owed by a landlord. 
        PropertyController pc = new PropertyController(); 
        JLabel feesLabel = new JLabel("Fees Owed by Lanldlord: " + pc.getFees(l.getUsername()), SwingConstants.CENTER ); 
        feesLabel.setFont(font);
        landlordProfile.add(feesLabel);
        landlordProfile.add(new JLabel(""));

        //set landlord fees
        JLabel setFeesLabel = new JLabel("Set fees on each of their properties to: ", SwingConstants.CENTER); 
        setFeesLabel.setFont(font);
        landlordProfile.add(setFeesLabel);
        feesField.setText("[Dollars]");
        landlordProfile.add(feesField); 
        landlordProfile.add(new JLabel(""));
        landlordProfile.add(addFeesButton);
        addFeesButton.addActionListener(this);

        //set landlord payment period
        JLabel setPeriodLabel = new JLabel("Set payment period on each of their properties to: ", SwingConstants.CENTER); 
        setPeriodLabel.setFont(font);
        landlordProfile.add(setPeriodLabel);
        periodField.setText("[Months]");
        landlordProfile.add(periodField); 
        landlordProfile.add(new JLabel(""));
        landlordProfile.add(updatePeriodButton); 
        updatePeriodButton.addActionListener(this);

        landlordProfile.add(new JLabel(""));
        landlordProfile.add(returnHomeButton); 
        returnHomeButton.addActionListener(this);
        mainWindow.add(landlordProfile); 
        mainWindow.validate();
        mainWindow.repaint();
        mainWindow.setVisible(true);
    }

    /**
     * for managers to view other managers info, not part of requirements although helpful 
     * @param m manager to be shown
     */
    public void displayManagerProfile(Manager m)
    {
        mainWindow.setVisible(false);
        mainWindow.getContentPane().removeAll();
        JPanel managerProfile = new JPanel(new GridLayout(4, 1, 10, 10)); 
        managerProfile.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Manager Profile"));
        Font font = new Font(null, 0, 15);
        
        JLabel nameLabel = new JLabel("Name: " + m.getName(), SwingConstants.CENTER); 
        nameLabel.setFont(font);
        managerProfile.add(nameLabel); 

        JLabel usernameLabel = new JLabel("Username: " + m.getUsername(), SwingConstants.CENTER); 
        usernameLabel.setFont(font);
        managerProfile.add(usernameLabel);

        JLabel passwordLabel = new JLabel("Password: " + m.getPassword(), SwingConstants.CENTER); 
        passwordLabel.setFont(font);
        managerProfile.add(passwordLabel);

        managerProfile.add(returnHomeButton); 
        returnHomeButton.addActionListener(this);
        mainWindow.add(managerProfile); 
        mainWindow.validate();
        mainWindow.repaint();
        mainWindow.setVisible(true);
    }





    /**
     * any button, or combobox that has .addActionListener(this) will use this method to react to user events such as button presses 
     * 
     * most of these actions will pull information from the last page and send to the DB via controllers
     */
    public void actionPerformed(ActionEvent e)
    {
        int accountType =0; 
        if (e.getSource().equals(renterButton))//account type ==1 
        {
            accountType = 1; 
            loginPrompt(); 
        }
        if (e.getSource().equals(landlordButton))//account type == 2
        {
            accountType = 2; 
            loginPrompt();
        }
        if (e.getSource().equals(managerButton))//account type == 3 
        {
            accountType = 3; 
            loginPrompt();
        }
        if (e.getSource().equals(useAsGuest))//unregistered
        {    
            buildHomePage(0); //no login needed
        }
        if (e.getSource().equals(signUpButton))
        {
            //sign up a user 
            //JOptionPane.showMessageDialog(mainWindow, "Sign me up"); 
            mainWindow.setVisible(false);
            mainWindow.getContentPane().remove(signInPanel);
            mainWindow.add(new JLabel("Please Select an account type to register.")); 
            mainWindow.add(accountTypeBox); 
            accountTypeBox.addActionListener(this);
            mainWindow.validate();
            mainWindow.repaint();
            mainWindow.setVisible(true);
        }
        if (e.getSource().equals(logInButton))
        {
            //log this user in with their information provided
            LoginController log_in = LoginController.getInstance(); 
            super.user = log_in.login((String)usernameField.getText(), (String)passwordField.getText()); //set the user to be the returned person
            //send the user to the correct location
            if (super.user instanceof Manager)
            {
                buildHomePage(3); 
            }
            else if (super.user instanceof Landlord)
            {
                buildHomePage(2); 
            }
            else if (super.user instanceof Renter)
            {
                buildHomePage(1); //must be a renter
            }
            else if (super.user == null) //failure 
            {
                JOptionPane.showMessageDialog(mainWindow, "Could not log you in, please try again");
            }
        }
        if (e.getSource().equals(searchButton))
        {
            //searching the database for propertie(s)
            if(super.user instanceof Renter)
            {   
                //if the user is a renter, display many results based on their search 
                ArrayList<Property> searchResults = renterSearch(); 
                if (searchResults.size() == 0)// no result 
                {
                    JOptionPane.showMessageDialog(mainWindow, "There are no properties that match your search");
                    return; 
                }
                displaySearchResults(searchResults); //show the results
            }
            else if(super.user instanceof Manager)
            {
                //if the user is a manager, display only one result based on the ID #
                int ID =0; 
                try
                {
                    ID = Integer.parseInt(searchByIDField.getText()); //try to parse int
                } catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(mainWindow, "Please enter an integer value");
                    return;
                }
                //the arrayList will have 0-1 elements to use the same search method. 
                PropertyController pc = new PropertyController();
                ArrayList<Property> result = pc.getPropertyByID(ID);
                if (result.size() == 0)// no result 
                {
                    JOptionPane.showMessageDialog(mainWindow, "That property is not on the database.");
                    return; 
                }
                displaySearchResults(result); 
            }
        }
        if (e.getSource().equals(getNotificicationsButton))
        {
            JOptionPane.showMessageDialog(mainWindow, "Simulation of notifications being shown"); 
        }
        if (e.getSource().equals(recentSearchButton))
        {
            //pull recent search from the users database, build a property from that
            PropertyController pc = new PropertyController(); 
            String query = ((Renter) super.user).getSearchQuery(); 
            ArrayList<Property> results = pc.getRentals(query); 
            displaySearchResults(results); 
        }
        if (e.getSource().equals(registerPropertyButton))
        {
            //landlord wants to register a property
            registerProperty();
        }
        if (e.getSource().equals(editPropertyButton))
        {
            //landlord  or manager wants to edit a properties status
            editProperty();
        }
        if (e.getSource().equals(completeRegistrationButton))
        {
            //user has registered a new property, send this to the database
            int numofRooms = 0, rent = 0; //for parsing into
            double numofBaths = 0; 
            int failureFlag = 0; 
            try
            {
                numofRooms = Integer.parseInt(numberOfBedroomsField.getText()); //try to parse int
            } catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(mainWindow, "Please enter an integer value for the number of bedrooms field.\n Ex.) 1");
                failureFlag = 1; //if caught, need to not register
            }
            try
            {
                rent = Integer.parseInt(rentField.getText()); //try to parse int
            } catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(mainWindow, "Please enter an integer value for the rent field.\n Ex.) 100");
                failureFlag = 1; //if caught, need to not register
            }
            try
            {
                numofBaths = Double.parseDouble(numberOfBathroomsField.getText()); //try to parse double
            } catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(mainWindow, "Please enter a double value for the number of bathrooms field.\n Ex.) 1.5");
                failureFlag = 1; //if caught, need to not register 
            }

            if (failureFlag == 1)
            {
                return; //dont search with current values, will prompt for other values
            }
            
            //making the current date 
            long millis=System.currentTimeMillis();  
            java.sql.Date date=new java.sql.Date(millis);

            Boolean furnishings = false; 
            String selection = (String)furnishingBox.getSelectedItem();
            if (selection.equals("Furnished"))
            {
                furnishings = true; 
            }
            //build landlord for the property to receive
            Landlord lord = new Landlord(super.user.getName(), super.user.getUsername(), super.user.getPassword(), emailField.getText());

            PropertyController addition = new PropertyController(); 
            //create a listing for the property to receive
            Listing list = new Listing(
                true, 
                date, 
                2, //60 days for each payment period (2 months)
                rent, 
                date, 
                false, 
                20 
            );
            //create the property from the values given by the user
            Property toAdd = new Property(
                lord, 
                propertyID,  
                addressField.getText(), 
                numofRooms, 
                numofBaths, 
                furnishings, 
                (String)quadrantBox.getSelectedItem(), 
                (String)propertyTypeBox.getSelectedItem(), 
                list
            );

            propertyID++; //increase ID# for the next prop that is added. 

            if(addition.createNewProperty(toAdd)) //add to DB
            {
                JOptionPane.showMessageDialog(mainWindow, "Property Registered!");
                buildHomePage(2); //return home
            }
            else
            {   
                JOptionPane.showMessageDialog(mainWindow, "There was an error, try again");
            }
            
        }
        if(e.getSource().equals(payFeesButton))
        {
            /* need to update fees in DB on the properties that the landlord owns, simply sets to 0 and simulates a payment */
            PropertyController pc = new PropertyController(); 
            pc.setFees(super.user.getUsername(), 0); //set all the landlords properties to have no fees associated

            JOptionPane.showMessageDialog(mainWindow, "The fees have been charged to the bank account associated with your email address");
        }
        if(e.getSource().equals(addFeesButton))
        {
            //manager updates the fees for a landlord and their properties 
            PropertyController pc = new PropertyController(); 
            double fees = 0; 
            try {
                fees = Double.parseDouble(feesField.getText());
            }catch(NumberFormatException n)
            {
                JOptionPane.showMessageDialog(mainWindow, "please enter a double value (Ex. 20.5) in the fees box, try again");
                return; 
            }
            if(pc.setFees(usernameField.getText(), fees))
            {
                buildHomePage(3); //return home 
            } 
            else{
                JOptionPane.showMessageDialog(mainWindow, "please try again.");
                return; 
            }
        }
        if(e.getSource().equals(updatePeriodButton))
        {
            //manager updates the payment period for a landlord and their properties
            PropertyController pc = new PropertyController(); 
            int period = 0; 
            try {
                period = Integer.parseInt(periodField.getText());
            }catch(NumberFormatException n)
            {
                JOptionPane.showMessageDialog(mainWindow, "please enter an integer value (Ex. 2) in the fees box, try again");
                return; 
            }
            if(pc.setPaymentPeriod(usernameField.getText(), period))
            {
                buildHomePage(3); //return home
            } 
            else{
                JOptionPane.showMessageDialog(mainWindow, "please try again.");
                return; 
            }
        }
        if(e.getSource().equals(logOutButton))
        {
            //First, save the renters last search to the Database before logging out. 
            if(super.user instanceof Renter)
            {
                String queryStr = ""; 
                try{
                    Boolean furnishings = false; 
                    String selection = (String)furnishingBox.getSelectedItem();
                    if (selection.equals("Furnished"))
                    {
                        furnishings = true; 
                    }
                    queryStr = "SELECT * FROM Property WHERE "
                        + "propertyType=\'"+ (String)propertyTypeBox.getSelectedItem()
                        + "\' AND rooms=\'"+ Integer.parseInt(numberOfBedroomsField.getText())
                        + "\' AND bathrooms=\'"+ Double.parseDouble(numberOfBathroomsField.getText())
                        + "\' AND furnished=\'"+ furnishings
                        + "\' AND quadrant=\'"+ (String)quadrantBox.getSelectedItem();
                } catch (NumberFormatException n)
                {
                    n.printStackTrace();
                }
                LoginController lc = LoginController.getInstance(); 
                lc.setRenterQuery(super.user.getUsername(), queryStr);
            }
            //return to the very begining of the program once information is saved
            begin(); 
        }
        if(e.getSource().equals(accountTypeBox))
        {
            //user choses what kind of account they want
            String userChoice = (String)accountTypeBox.getSelectedItem(); //read whats chosen
            if(userChoice.equals("Renter"))
            {
                signUserUp(1);
                accountType = 1; 
            }
            if(userChoice.equals("Landlord"))
            {
                signUserUp(2);
                accountType = 2; 
            }
            if(userChoice.equals("Manager"))
            {
                signUserUp(3);
                accountType = 3; 
            }
        }
        if(e.getSource().equals(confirmSignUpButton))
        {
            //user creates their account, and adds it to DB
            String userChoice = (String)accountTypeBox.getSelectedItem(); //read whats chosen
            if(userChoice.equals("Renter"))
            {
                accountType = 1; 
            }
            if(userChoice.equals("Landlord"))
            {
                accountType = 2; 
            }
            if(userChoice.equals("Manager"))
            {
                accountType = 3; 
            } 
            //ensure the account can be added, then add it
            LoginController signMeUp = LoginController.getInstance(); 
            boolean worked = signMeUp.signup( 
                usernameField.getText(), 
                passwordField.getText(), 
                nameField.getText(), 
                emailField.getText(), 
                accountType
            );
            if (worked == true)
            {
                loginPrompt(); 
            }
            else 
            {
                JOptionPane.showMessageDialog(mainWindow, "Your account could not be created, please try again.");
                return;
            }
            JOptionPane.showMessageDialog(mainWindow, "Creating successful, please log in with your new credentials");
        }
        if(e.getSource().equals(findUserButton))
        {
            // figure out the account type from options box
            String userChoice = (String)accountTypeBox.getSelectedItem(); 
            accountType = 0 ; 
            if(userChoice.equals("Renter"))
            {   
                Renter renter = null; 
                renter = LoginController.getRenter(usernameField.getText()); 
                displayRenterProfile(renter); 
            }
            if(userChoice.equals("Landlord"))
            {
                Landlord landlord = null;  
                landlord = LoginController.getLandlord(usernameField.getText());
                displayLandlordProfile(landlord);
            }
            if(userChoice.equals("Manager"))
            {
                Manager manager = null;
                manager = LoginController.getManager(usernameField.getText()); 
                displayManagerProfile(manager); 
            } 
        }
        if (e.getSource().equals(viewReportButton))
        {
            //manager wants to create a report
            String theDate = dateField.getText(); 
            SimpleDateFormat parseDate = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsed = null;
            Report theReport = new Report();  
            try{
                parsed = parseDate.parse(theDate);
                
            } catch (ParseException p)
            {
                p.printStackTrace();
                System.exit(1);
            }
            java.sql.Date sql = new java.sql.Date(parsed.getTime());
            theReport = new Report(sql);
            showReport(theReport, theDate ); 
            
        }
        if(e.getSource().equals(emailLandlordButton))
        {
            //simulate the emailing of a landlord
            JOptionPane.showMessageDialog(mainWindow, "Your message: \n" + messageField.getText() + "\nHas been sent to the landlord of the property with ID: " + propertyIDField.getText());
        }
        if(e.getSource().equals(goBackButton))
        {
            //return to the previous page
            if(super.user instanceof Manager)
            {
                buildHomePage(3);
            }
            else if (super.user instanceof Renter)
            {
                buildHomePage(1);
            } else {
                buildHomePage(0);
            }
        }
        if(e.getSource().equals(updateButton))
        {
            //landlord or manager updating a property 
            //updateProperty using last value in propertyIDField
            PropertyController pc = new PropertyController();
            //get the ID requested 
            int i= 0;
            try{
                 i = Integer.parseInt(propertyIDField.getText()); 
            }catch(NumberFormatException n)
            {
                n.printStackTrace();
                JOptionPane.showMessageDialog(mainWindow, "Failed");
                return; 
            }
            //set the status' in the DB of the listing
            if(listingStatusBox.getSelectedItem().equals("Rented"))
            {
                pc.setRented(i, true);
                pc.setOnlineStatus(i, false);
            }
            else if(listingStatusBox.getSelectedItem().equals("Active"))
            {
                pc.setRented(i, false);
                pc.setOnlineStatus(i, true);
            }
            else if(listingStatusBox.getSelectedItem().equals("Suspended"))
            {
                pc.setRented(i, true);
                pc.setOnlineStatus(i, false);
            }
            else if(listingStatusBox.getSelectedItem().equals("Cancelled"))
            {
                pc.setRented(i, true);
                pc.setOnlineStatus(i, false);
            }

            //let the user know it worked, clear the update panel. 
            JOptionPane.showMessageDialog(mainWindow, "Updated Property: " + propertyIDField.getText() + "\nto have status: " + listingStatusBox.getSelectedItem());
            mainWindow.setVisible(false);
            mainWindow.getContentPane().remove(editPropertyPanel);
            mainWindow.validate();
            mainWindow.repaint();
            mainWindow.setVisible(true);
        }
        if (e.getSource().equals(returnHomeButton))
        {
            buildHomePage(3); //manager
        }
    }
}
