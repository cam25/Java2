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

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;



// TODO: Auto-generated Javadoc
/**
 * The Class JSONQuery.
 */
public class JSONQuery {

	Context _context;
	String _zipcode;
	String _areaCode;
	String _city;
	String _county;
	String _state;
	String _latitude;
	String _longitude;
	String _csa_name;
	String _cbsa_name;
	String _region;
	String _timezone;
	LocationDisplay _locationDetails;
	
	/**
	 * Gets the lookup.
	 *
	 * @param zipcode the zipcode
	 * @return the lookup
	 */
	//set the getLookup function to a static URL so im able to return the final url of the query string and call the zipRequest class which holds my async request and pass the final url to that.
	static  URL getLookup(String zipcode){
		String baseURL = "http://zipfeeder.us/zip?";
		String key = "key=EN4GbNMq";
		String qs = "";
		try{
			qs = URLEncoder.encode(zipcode, "UTF-8");
		}catch (Exception e) {
			
			Log.e("Bad URL","Encoding Problem");
			qs = "";
		}
		URL finalURL;
		try{
			finalURL = new URL (baseURL + key + "&zips=" + qs);
			Log.i("URL",finalURL.toString());
			
			//zipRequest passed here for usage of the async
			zipRequest qr = new zipRequest();
			qr.execute(finalURL);
			
		}catch (MalformedURLException e){
			Log.e("BAD URL", "Malformed URL");
			finalURL = null;
		}
		return finalURL;
	}
	
		/**
		 * On post execute.
		 *
		 * @param result the result
		 */
		protected void onPostExecute(String result){
			
			Log.i("URL RESPONSE", result);
			try {
				//setting my json object and array
				JSONObject json = new JSONObject(result);
				JSONArray ja = json.getJSONArray("zips");
				
				
				Log.i("results",result);
				
					//loops through json array 
					for (int i = 0; i < ja.length(); i++) {
						//sets a json object to access object values inside array
						JSONObject one = ja.getJSONObject(i);
						
					//setting my text to the values to the strings of the json data
					_zipcode = one.getString("zip_code");
					_areaCode = one.getString("area_code");
					_city = one.getString("city");
					_state = one.getString("state");
					_county = one.getString("county");
					_csa_name = one.getString("csa_name");
					_cbsa_name = one.getString("cbsa_name");
					_latitude = one.getString("latitude");
					_longitude = one.getString("longitude");
					_region = one.getString("region");
					_timezone = one.getString("time_zone");
						 
					}
					Log.i("locations ", _areaCode + _city + _state + _county + _csa_name + _cbsa_name + _latitude + _longitude + _region + _timezone);
				
					//sets the values of the text by calling the locationInfo function inside of my Locationdisplay class
					_locationDetails.locationInfo(_areaCode, _city, _county, _state, _latitude, _longitude, _csa_name, _cbsa_name, _region, _timezone);  
					
					//confirms a valid zipcod here
					Toast toast = Toast.makeText(_context, "Valid Zipcode " + _zipcode , Toast.LENGTH_SHORT);
					toast.show();
					
				

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				
				//alert for json exception
				AlertDialog.Builder alert = new AlertDialog.Builder(_context);
				alert.setTitle("Error");
				alert.setMessage("There was an error searching for your request. Check connections or make sure zipcode is correct.");
				alert.setCancelable(false);
				alert.setPositiveButton("Alright", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {

						dialog.cancel();
					}
				});
				alert.show();
				e.printStackTrace();
				Log.e("JSON","JSON OBJECT EXCEPTION");
				
			}
		}
		
	}


	
