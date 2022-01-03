/**
 * Main.java found in bin/gui directory
 * @author Josh Vanderstoop- joshua.vanderstoop@ucalgary.ca - UCID: 30101947
 * written for ENSF 480 Final Project
 */
package bin.gui; 

import bin.domain.*;

public class Main{

    // interface object 
    public static GUI gui;

    public static Person user; 


    /**
     * to compile: javac -cp .:lib/mysql-connector-java-8.0.23.jar bin/control/*.java bin/database/*.java bin/gui/*.java bin/domain/*.java
     * 
     * to run: java -cp .;lib/mysql-connector-java-8.0.23.jar bin/gui/Main
     */
    public static void main(String[] args)
    {
        user = new Person(); 
        gui = new GUI(); 
        gui.begin(); 
    }
}
