package com.cmozie.classes;

//class thats going to allow me to set values to spinner
public class Cities {

	
	public int zipcode = 0;
	public String city = "";
	public String state = "";
	
	//singleton/constructor to set values of my variables
	public Cities(int _zipcode, String _city, String _state){
		
		_zipcode = zipcode;
		_city = city;
		_state = state;
		
	}

	public String toString(){
		return (city + "(" + state  +" ) " );
		
	}
}
