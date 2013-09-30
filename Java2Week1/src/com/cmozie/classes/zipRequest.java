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

import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import webConnections.WebStuff;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import com.cmozie.java2week1.MainActivity;



	// TODO: Auto-generated Javadoc
/**
	 * The Class zipRequest.
	 */
	public class zipRequest extends AsyncTask<URL, Void, String>{
		
		
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
		//LocationDisplay _locationDetails = new LocationDisplay(MainActivity._context);
		
		
		
		/* (non-Javadoc)
		 * @see android.os.AsyncTask#doInBackground(Params[])
		 */
		@Override
		protected String doInBackground(URL...urls){
			String response = "";
		for (URL url : urls) {
			response = WebStuff.getURLStringResponse(url);
		}
			return response;
		}
		
/* (non-Javadoc)
 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
 */
protected void onPostExecute(String result){
			
			Log.i("URL RESPONSE", result);
			try {
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
					Log.i("one", _areaCode + _city + _state + _county + _csa_name + _cbsa_name + _latitude + _longitude + _region + _timezone);
					
					 
					//sets the values of the text by calling the locationInfo function inside of my Locationdisplay class
					//_locationDetails.locationInfo(_areaCode, _city, _county, _state, _latitude, _longitude, _csa_name, _cbsa_name, _region, _timezone);  
					
					//locationInfo(_areaCode, _city, _county, _state, _latitude, _longitude, _csa_name, _cbsa_name, _region, _timezone);
					Toast toast = Toast.makeText(MainActivity._context, "Valid Zipcode " + _zipcode , Toast.LENGTH_SHORT);
					toast.show();
					

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				
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

	

