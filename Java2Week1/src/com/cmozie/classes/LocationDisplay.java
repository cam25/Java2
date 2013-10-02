/*
 * project 			Java1Week4
 * 
 * package			com.cmozie.classes
 * 
 * name				cameronmozie
 * 
 * date				Sep 26, 2013
 */

package com.cmozie.classes;

import android.content.Context;
import android.widget.GridLayout;
import android.widget.TextView;

// TODO: Auto-generated Javadoc
/**
 * The Class LocationDisplay.
 */
public class LocationDisplay extends GridLayout {
	Context _context;
	TextView _zipcode;
	TextView _city;
	TextView _county;
	TextView _state;
	TextView _area_code;
	TextView _latitude;
	TextView _longitude;
	TextView _csa_name;
	TextView _cbsa_name;
	TextView _region;
	TextView _timezone;
	
	TextView _zipcode2;
	TextView _city2;
	TextView _county2;
	TextView _state2;
	TextView _area_code2;
	TextView _latitude2;
	TextView _longitude2;
	TextView _csa_name2;
	TextView _cbsa_name2;
	TextView _region2;
	TextView _timezone2;
	
	
	/**
	 * Instantiates a new location display.
	 *
	 * @param context the context
	 */
	public LocationDisplay(Context context){
		super(context);
		
		_context = context;
		this.setColumnCount(3);
		
		
		TextView zipcodeLabel = new TextView(_context);
		zipcodeLabel.setText("Zipcode:");
		_zipcode = new TextView(_context);
		
		TextView cityLabel = new TextView(_context);
		cityLabel.setText("City:");
		_city = new TextView(_context);
		
		TextView countyLabel = new TextView(_context);
		countyLabel.setText("County:");
		_county = new TextView(_context);
		
		TextView stateLabel = new TextView(_context);
		stateLabel.setText("State:");
		_state = new TextView(_context);
		
		TextView areaCodeLabel = new TextView(_context);
		areaCodeLabel.setText("Areacode:");
		_area_code = new TextView(_context);
		
		TextView latitudeLabel = new TextView(_context);
		latitudeLabel.setText("Latitude:");
		_latitude = new TextView(_context);
		
		TextView longitudeLabel = new TextView(_context);
		longitudeLabel.setText("Longitude:");
		_longitude = new TextView(_context);
		
		TextView csaNameLabel = new TextView(_context);
		csaNameLabel.setText("Csa-Name:");
		_csa_name = new TextView(_context);
		
		TextView cbsaNameLabel = new TextView(_context);
		cbsaNameLabel.setText("Cbsa-Name:");
		_cbsa_name = new TextView(_context);
		
		TextView regionLabel = new TextView(_context);
		regionLabel.setText("Region:");
		_region = new TextView(_context);
		
		TextView timeZoneLabel = new TextView(_context);
		timeZoneLabel.setText("Timezone:");
		_timezone = new TextView(_context);
		
		this.addView(zipcodeLabel);
		this.addView(_zipcode);
		
		this.addView(areaCodeLabel);
		this.addView(_area_code);
		
		this.addView(cityLabel);
		this.addView(_city);
		
		this.addView(countyLabel);
		this.addView(_county);
		
		this.addView(stateLabel);
		this.addView(_state);
	
		
		this.addView(latitudeLabel);
		this.addView(_latitude);
		
		this.addView(longitudeLabel);
		this.addView(_longitude);
		
		this.addView(csaNameLabel);
		this.addView(_csa_name);
		
		this.addView(cbsaNameLabel);
		this.addView(_cbsa_name);
		
		this.addView(regionLabel);
		this.addView(_region);
		
		this.addView(timeZoneLabel);
		this.addView(_timezone);
		
		
	
		
		_zipcode2 = new TextView(_context);
		this.addView(_zipcode2);
		
		
		_area_code2 = new TextView(_context);
		
		//this.addView(areaCodeLabel2);
		this.addView(_area_code2);
		
		_city2 = new TextView(_context);
		this.addView(_city2);
		
		_county2 = new TextView(_context);
		this.addView(_county2);
		
		_state2 = new TextView(_context);
		this.addView(_state2);
		
		_latitude2 = new TextView(_context);
		this.addView(_latitude2);
		
		_longitude2 = new TextView(_context);
		this.addView(_longitude2);
		
		_csa_name2 = new TextView(_context);
		this.addView(_cbsa_name2);
		
		_cbsa_name2 = new TextView(_context);
		this.addView(_cbsa_name2);
		
		_region2 = new TextView(_context);
		this.addView(_region2);
		
		_timezone2 = new TextView(_context);
		this.addView(_timezone2);
	
	}
	
	//set this method to void for no return but to just set the values of my json objects to the view next to the labels.
	/**
	 * Location info.
	 *
	 * @param area_code the area_code
	 * @param city the city
	 * @param county the county
	 * @param state the state
	 * @param latitude the latitude
	 * @param longitude the longitude
	 * @param csa_name the csa_name
	 * @param cbsa_name the cbsa_name
	 * @param region the region
	 * @param timezone the timezone
	 */
	public void locationInfo(String zipcode, String area_code, String city, String county, String state, String latitude, String longitude, String csa_name, String cbsa_name, String region, String timezone) {
		
		_zipcode.setText(zipcode);
		_area_code.setText(area_code);
		_city.setText(city);
		_county.setText(county);
		_state.setText(state);
		_latitude.setText(latitude);
		_longitude.setText(longitude);
		_csa_name.setText(csa_name);
		_cbsa_name.setText(cbsa_name);
		_region.setText(region);
		_timezone.setText(timezone);
	}
	
public void locationInfo2(String zipcode2, String area_code2, String city2, String county2, String state2, String latitude2, String longitude2, String csa_name2, String cbsa_name2, String region2, String timezone2) {
		
		_zipcode.setText(zipcode2);
		_area_code.setText(area_code2);
		_city.setText(city2);
		_county.setText(county2);
		_state.setText(state2);
		_latitude.setText(latitude2);
		_longitude.setText(longitude2);
		_csa_name.setText(csa_name2);
		_cbsa_name.setText(cbsa_name2);
		_region.setText(region2);
		_timezone.setText(timezone2);
	}

}
