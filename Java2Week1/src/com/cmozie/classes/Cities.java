/*
 * project 			Java2Week1
 * 
 * package			com.cmozie.classes
 * 
 * name				cameronmozie
 * 
 * date				Oct 3, 2013
 */
package com.cmozie.classes;

// TODO: Auto-generated Javadoc
//class thats going to allow me to set values to spinner
/**
 * The Class Cities.
 */
public class Cities {

	
	public String zipcode = "";
	public String city = "";
	public String state = "";
	
	//singleton/constructor to set values of my variables
	/**
	 * Instantiates a new cities.
	 *
	 * @param _zipcode the _zipcode
	 * @param _city the _city
	 * @param _state the _state
	 */
	public Cities(String _zipcode, String _city, String _state){
		
		zipcode = _zipcode;
		city = _city;
		state = _state;
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return ( city+"("+ state +")");
		
	}
}
