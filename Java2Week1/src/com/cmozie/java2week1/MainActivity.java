package com.cmozie.java2week1;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cmozie.Libz.FileStuff;
import com.cmozie.classes.*;

import webConnections.*;


// TODO: Auto-generated Javadoc
/**
 * The Class MainActivity.
 */

@SuppressLint("HandlerLeak")
public class MainActivity extends Activity {

	//--public statics
	public static Context _context;

	public static Button searchButton;
	
	static Spinner spinner = null;
	
	public int selected; 
	
	FileStuff m_file;
	
	//layout
	TextView _popularZips;
	Button _pop;
	
	//bool
	Boolean _connected = false;
	

	//strings
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

	String _zipcode2;
	String _city2;
	String _county2;
	String _state2;
	String _area_code2;
	String _latitude2;
	String _longitude2;
	String _csa_name2;
	String _cbsa_name2;
	String _region2;
	String _timezone2;
	
	
	
	ArrayList<String>_stacks = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//inflating my form.xml
		View view;
		LayoutInflater layoutInflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = layoutInflater.inflate(R.layout.form, null);
		
		//setting contentView to my inflated view/form
		setContentView(view);

		_context = this;
		
		 //webConnection jar file usage
		 _connected = WebStuff.getConnectionStatus(_context);
		 if (_connected) {
			 
			 
			Log.i("Network Connection", WebStuff.getConnectionType(_context));
			
			//if no connection
		}else if(!_connected) {
			
			
			FileStuff.readStringFile(_context, "temp", _connected);
			Log.i("new", FileStuff.readStringFile(_context, "temp", _connected));
			
			 
			//alert for connection
			AlertDialog.Builder alert = new AlertDialog.Builder(_context);
			alert.setTitle("Connection Required!");
			alert.setMessage("You need to connect to an internet service!");
			alert.setCancelable(false);
			alert.setPositiveButton("Alright", new DialogInterface.OnClickListener() {
			
				@Override
				public void onClick(DialogInterface dialog, int which) {

					dialog.cancel();
				}
			});
			alert.show();
		
		}
		 
		 ArrayAdapter<Cities> listAdapter = new ArrayAdapter<Cities>(_context, android.R.layout.simple_spinner_item, new Cities[]{
				 
				 new Cities("", "San Francisco", "CA"),
				 new Cities("", "Miami", "FL"),
				 new Cities("", "Washington", "DC"),
				 new Cities("", "New York", "NY"),
				 new Cities("", "Chicago", "IL")
		 });
		 listAdapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
		
		 spinner = (Spinner) findViewById(R.id.favList);
		spinner.setAdapter(listAdapter);
	
		 //popular zipcodes onclick
		 _pop = (Button) findViewById(R.id.popularZipcodes);
		 _pop.setOnClickListener(new OnClickListener() {
		 		
		 		public void onClick(View v) {
		 			// TODO Auto-generated method stub
		 			
		 			//informing the user how to select a popular zipcode
		 			AlertDialog.Builder alert = new AlertDialog.Builder(_context);
		 			alert.setTitle("What to do?");
		 			alert.setMessage("Click the yellow box to view the locations and select one!");
		 			alert.setCancelable(false);
		 			alert.setPositiveButton("Thanks!", new DialogInterface.OnClickListener() {
		 				
		 				@Override
		 				public void onClick(DialogInterface dialog, int which) {

		 					dialog.cancel();
		 				}
		 			});
		 			alert.show();

		 			
		 			spinner.setVisibility(View.VISIBLE);
		 			spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		 				
		 				
						public void onItemSelected(AdapterView<?> parent,View v,int pos, long id){
		 							 					
		 					String zipcode = "";
		 					
		 					int position = spinner.getSelectedItemPosition();
		 					switch (position) {
		 					
							case 0:
								zipcode = "94105|94106";
								break;
								
							case 1:
								zipcode = "33133|33132";
								break;
							
							case 2:
								zipcode =  "20001|20002";
								break;
							case 3:
								zipcode = "10001|10002";
								break;
							case 4:
								zipcode = "60018|60068";
								break;
							

							default:
								break;
							}
		 							
		 	
		 					Handler zipcodeHandler = new Handler() {

								
								@Override
								public void handleMessage(Message msg) {
									// TODO Auto-generated method stub
									String selected = msg.obj.toString() ;
									if (msg.arg1 == RESULT_OK && msg.obj != null) 
										Log.i("Serv.Response", msg.obj.toString());
									
									{
										try {
											Log.i("Second", "TEST");
											JSONObject json = new JSONObject(selected);
											
											JSONArray ja = json.getJSONArray("zips");
											
											for (int i = 0; i < ja.length(); i++) {
												//sets a json object to access object values inside array
												
												
												JSONObject one = ja.getJSONObject(i);
												JSONObject two = ja.getJSONObject(0);
												
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
											
											_zipcode2 = two.getString("zip_code");
											_area_code2 = two.getString("area_code");
											_city2 = two.getString("city");
											_county2 = two.getString("county");
											_state2 = two.getString("state");
										

											_latitude2 = two.getString("latitude");
											_longitude2 = two.getString("longitude");
											_region2 = two.getString("region");
											_timezone2 = two.getString("time_zone");
											
											
												 
											}
											Log.i("one", _areaCode + _city + _state + _county + _csa_name + _cbsa_name + _latitude + _longitude + _region + _timezone);
										
											Log.i("FIRST",_zipcode2 );
											//sets the values of the text by calling the locationInfo function inside of my Locationdisplay class
											//locationInfo(_areaCode, _city, _county, _state, _latitude, _longitude, _csa_name, _cbsa_name, _region, _timezone);  
										
											locationInfo(_zipcode, _areaCode, _city, _county, _state, _latitude, _longitude, _csa_name, _cbsa_name, _region, _timezone);
											locationInfo2(_zipcode2, _area_code2, _city2, _county2, _state2, _latitude2, _longitude2, _csa_name2, _cbsa_name2, _region2, _timezone2);
											//Trying to read the file stored.
											Toast toast = Toast.makeText(getBaseContext(),"Search Complete + Reading local Storage....  " + FileStuff.readStringFile(_context, "temp", false), Toast.LENGTH_SHORT);
											
											toast.show();
											
											//m_file.storeStringFile(_context, fullURLString, content, external)
											
											
										} catch (Exception e) {
											// TODO: handle exception
											//Alert for any error in entering into textfield if not a zipcode
											
											AlertDialog.Builder alert = new AlertDialog.Builder(_context);
											alert.setTitle("Error");
											alert.setMessage("There was an error searching for your request. Check connections or make sure zipcode is correct. USA zipcodes only.");
											alert.setCancelable(false);
											alert.setPositiveButton("Alright", new DialogInterface.OnClickListener() {
												
												@Override
												public void onClick(DialogInterface dialog, int which) {

													dialog.cancel();
												}
											});
											alert.show();
											Log.e("Handle Message", e.getMessage().toString());
										}
										
										
									}						
									
								}
								
								
							};
							Messenger zipcodeMessenger = new Messenger(zipcodeHandler);
							
							Intent startZipcodeIntent = new Intent(_context, ZipcodeService.class);
							startZipcodeIntent.putExtra(ZipcodeService.MESSENGER_KEY, zipcodeMessenger);
							startZipcodeIntent.putExtra(ZipcodeService.enteredZipcode,zipcode);
							startService(startZipcodeIntent);
		 					
		 				}
		 			
		 				public void onNothingSelected(AdapterView<?>parent){
		 					Log.i("Aborted", "None Selected");
		 					
		 				}
		 				
		 				
		 			});
		 			
		 			//sets button to non clickable once clicked once 
		 			_pop.setClickable(false);
		 			_pop.setVisibility(View.GONE);
		 			
		 		}
		 			
		 	});

		 

		 }
	
		
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
public void locationInfo(String zipcode,String area_code, String city, String county, String state, String latitude, String longitude, String csa_name, String cbsa_name, String region, String timezone) {
		
		((TextView) findViewById(R.id.location_zipcode)).setText(zipcode);
		((TextView) findViewById(R.id.location_areacode)).setText(area_code);
		((TextView) findViewById(R.id.location_city)).setText(city);;
		((TextView) findViewById(R.id.location_county)).setText(county);
		((TextView) findViewById(R.id.location_state)).setText(state);
		((TextView) findViewById(R.id.location_latitude)).setText(latitude);
		((TextView) findViewById(R.id.location_longitude)).setText(longitude);
		((TextView) findViewById(R.id.location_csa_name)).setText(csa_name);
		((TextView) findViewById(R.id.location_cbsa_name)).setText(cbsa_name);
		((TextView) findViewById(R.id.location_region)).setText(region);
		((TextView) findViewById(R.id.location_timezone)).setText(timezone);
}

public void locationInfo2(String zipcode,String area_code, String city, String county, String state, String latitude, String longitude, String csa_name, String cbsa_name, String region, String timezone) {
	
	((TextView) findViewById(R.id.location_zipcode2)).setText(zipcode);
	((TextView) findViewById(R.id.location_areacode2)).setText(area_code);
	((TextView) findViewById(R.id.location_city2)).setText(city);
	((TextView) findViewById(R.id.location_county2)).setText(county);
	((TextView) findViewById(R.id.location_state2)).setText(state);

	((TextView) findViewById(R.id.location_latitude2)).setText(latitude);
	((TextView) findViewById(R.id.location_longitude2)).setText(longitude);
	((TextView) findViewById(R.id.location_region2)).setText(region);
	((TextView) findViewById(R.id.location_timezone2)).setText(timezone);
}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
}
