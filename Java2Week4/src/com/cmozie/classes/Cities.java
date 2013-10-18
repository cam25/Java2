/*
 * project 			Java2Week3
 * 
 * package			com.cmozie.classes
 * 
 * name				cameronmozie
 * 
 * date				Oct 17, 2013
 */

package com.cmozie.classes;

// TODO: Auto-generated Javadoc
//class thats going to allow me to set values to spinner
/**
 * The Class Cities.
 */
public class Cities {

	private static Cities _instance = null;
	
	
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
	
	/**
	 * Gets the single instance of Cities.
	 *
	 * @return single instance of Cities
	 */
	public Cities getInstance()
	{
		if (_instance == null)
		{
			//return new Cities()
		}
		
		return _instance;
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return ( city+"("+ state +")");
		
	}
}
