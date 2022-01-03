/**
 * Date.java found in bin/domain directory
 * @author Bradley Wachs - bradley.wachs@ucalgary.ca
 * written for ENSF 480 Final Project
 */
package bin.domain;

public class Date {
	
	/**
     * Data members:
     * @param YEAR: int		- Value for a year of a date (CE)
     * @param MONTH: int	- Value for a month of a date
     * @param DAY: int		- Value for a day of a date
     */
	private int year;
	private int month;
	private int day;
	
	/**
     * Date Constructor:
     * @param YEAR: int		- Value for a year of a date (CE)
     * @param MONTH: int	- Value for a month of a date
     * @param DAY: int		- Value for a day of a date
     */
	public Date(int year, int month, int day){
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	public Date() {}
	
	/**
     * getYear Method:
     * Gets the year value for this date object
     */
	public int getYear() {
		return year;
	}
	
	/**
     * getMonth Method:
     * Gets the month value for this date object
     */
	public int getMonth() {
		return month;
	}
	
	/**
     * getDay Method:
     * Gets the day value for this date object
     */
	public int getDay() {
		return day;
	}
	
	/**
     * setYear Method:
     * Sets the year value for this date object
     * @param YEAR: int		- Value for a year of a date (CE)
     */
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
     * setMonth Method:
     * Sets the month value for this date object
     * @param MONTH: int	- Value for a month of a date
     */
	public void setMonth(int month) {
		this.month = month;
	}
	
	/**
     * setDay Method:
     * Sets the day value for this date object
     * @param DAY: int		- Value for a day of a date
     */
	public void setDay(int day) {
		this.day = day;
	}
}
