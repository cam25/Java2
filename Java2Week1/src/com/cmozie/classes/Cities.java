package com.cmozie.classes;

//class thats going to allow me to set values to spinner
public class Cities {

	
	public int zipcode = 0;
	public String city = "";
	public String state = "";
	
	//singleton/constructor to set values of my variables
	public Cities(int _zipcode, String _city, String _state){
		
		zipcode = _zipcode;
		city = _city;
		state = _state;
		
	}

	public String toString(){
		return (city + "(" + state  +" ) " );
		
	}
}
