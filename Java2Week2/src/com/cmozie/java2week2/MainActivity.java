/*
 * project 			Java2Week1
 * 
 * package			com.cmozie.java2week1
 * 
 * name				cameronmozie
 * 
 * date				Oct 3, 2013
 */
package com.cmozie.java2week2;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentQueryMap;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
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
	public static Button getRegion;
	EditText contentQuery;
	Uri searchALL;
	
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
	public int position;
	ListView listview;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.form);
		listview = (ListView) this.findViewById(R.id.list);
	
		
		View listHeader = this.getLayoutInflater().inflate(R.layout.list_header, null);
		listview.addHeaderView(listHeader);
		//setting contentView to my inflated view/form
		
		
		_context = this;
		
		 //webConnection jar file usage
		 _connected = WebStuff.getConnectionStatus(_context);
		 if (_connected) {
			 
			 
			Log.i("Network Connection", WebStuff.getConnectionType(_context));
			
			//if no connection
		}else if(!_connected) {
			
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
		 
		 
		 
		 //array adapter for my cities where i create new objects for each location
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
	
		getRegion = (Button) findViewById(R.id.getHistory);
		
		
		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String,String>>();
		
		
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
		 							Log.i("HIT","THE SPINNER");
		 					String zipcode = "";
		 					
		 					try{
		 						JSONObject json = new JSONObject(FileStuff.readStringFile(_context, "temp", false));
		 						//JSONObject json2 = new JSONObject(FileStuff.readStringFile(_context, "temp2", false));
		 						JSONArray ja = json.getJSONArray("zips");
		 						
		 						
		 					
		 						for (int i = 0; i < ja.length(); i++) {
		 							//sets a json object to access object values inside array
		 							
		 							
		 							JSONObject one = ja.getJSONObject(i);
		 							JSONObject two = ja.getJSONObject(0);
		 							
		 							
		 								_zipcode = one.getString("zip_code");
		 								_areaCode = one.getString("area_code");
		 								
		 								_region = one.getString("region");
		 								
		 								
		 								_zipcode2 = two.getString("zip_code");
		 								_area_code2 = two.getString("area_code");
		 								
		 								_region2 = two.getString("region");
		 								
		 								
		 							
		 						//setting my text to the values to the strings of the json data
		 					
		 						
		 						
		 							

		 						
		 					//setting of my switch case to work behind the scenes which switch at position of the cells of the spinner and query the api based on selected postion
		 				 position = spinner.getSelectedItemPosition();
		 					switch (position) {
		 					
							case 1:
								if (one.getString("zip_code").contentEquals("94105")) {
									zipcode = "content://" + ZipcodeContentProvider.AUTHORITY + "/zipcodes/" + 1 ;
								}
								
								break;
								
								
							case 2:
								if (one.getString("zip_code").contentEquals("33101")) {
									
									Log.i("ZIP", "WORKED");
									zipcode = "content://" + ZipcodeContentProvider.AUTHORITY + "/zipcodes/" + 2;
								}
								
								break;
							
							case 3:
								zipcode =  "content://" + ZipcodeContentProvider.AUTHORITY + "/zipcodes/" + 3;
								break;
							case 4:
								zipcode = "content://" + ZipcodeContentProvider.AUTHORITY + "/zipcodes/" + 4;
								break;
							case 5:
								zipcode = "content://" + ZipcodeContentProvider.AUTHORITY + "/zipcodes/" + 5;
								break;
							

							default:
								break;
								
							}
		 					
}
		 						
		 					} catch (Exception e) {
		 						Log.e("Buffer Error", "Error converting result " + e.toString());
		 					}
		 					Log.i("hit", zipcode);	
		 					//my handler
		 					Handler zipcodeHandler = new Handler() {

								
								@Override
								public void handleMessage(Message msg) {
									// TODO Auto-generated method stub
									Log.i("HIT","HANDLER");
									
									//string selected is my query reply from my ZipcodeService
									String selected = msg.obj.toString();
									Log.i("hit", selected);
									if (msg.arg1 == RESULT_OK && msg.obj != null) 
										Log.i("Serv.Response", msg.obj.toString());
									
									{
										
										searchALL = Uri.parse("content://" + ZipcodeContentProvider.AUTHORITY + "/zipcodes/" + position);
										
										Cursor cursor = getContentResolver().query(searchALL, null, null, null, null);
										//pulling in data from Local storage here
										display(cursor);
										Log.i("CONTENTPROVIDERURI",searchALL.toString());
										Log.i("CURSOR", cursor.toString());
										/*try{
											
											ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String,String>>();
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
											_cbsa_name2 = two.getString("cbsa_name");

											_latitude2 = two.getString("latitude");
											_longitude2 = two.getString("longitude");
											_region2 = two.getString("region");
											_timezone2 = two.getString("time_zone");
											
											HashMap<String, String> displayMap = new HashMap<String, String>();
											
											displayMap.put("zipCode", _zipcode);
											displayMap.put("zipCode2", _zipcode2);
											displayMap.put("areaCode", _areaCode);
											displayMap.put("areaCode2", _area_code2);
											displayMap.put("city", _city);
											displayMap.put("city2", _city2);
											displayMap.put("county", _county);
											displayMap.put("county2", _county2);
											displayMap.put("state", _state);
											displayMap.put("state2", _state2);
											displayMap.put("latitude", _latitude);
											displayMap.put("latitude2", _latitude2);
											displayMap.put("longitude", _longitude);
											displayMap.put("longitude2", _longitude2);
											displayMap.put("csa_name", _csa_name);
											displayMap.put("csa_name2", _csa_name2);
											displayMap.put("cbsa_name", _cbsa_name);
											displayMap.put("cbsa_name2", _cbsa_name2);
											displayMap.put("region", _region);
											displayMap.put("region2", _region2);
											displayMap.put("timezone", _timezone);
											displayMap.put("timezone2", _timezone2);
												 
											mylist.add(displayMap);
											
											Log.e("List", mylist.toString());
											
											}
											SimpleAdapter adapter = new SimpleAdapter(_context, mylist, R.layout.list_row, new String[]{"zipCode","areaCode","region"}, new int[]{R.id.row1, R.id.row2,R.id.row3});
											
											listview.setAdapter(adapter);
										} catch (Exception e) {
											Log.i("Buffer Error", "Error converting result " + e.toString());
										}*/
										
										
									}						
									
								}
								
								
							};
							
							//my intent services
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

		 getRegion.setOnClickListener(new OnClickListener() {
				//combine all the zipcodes and place call the query on all of them?
				
			
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Handler zipcodeHandler = new Handler() {

						
						@Override
						public void handleMessage(Message msg) {
							// TODO Auto-generated method stub
							Log.i("HIT","HANDLER");
							
							//string selected is my query reply from my ZipcodeService
							searchALL = Uri.parse("content://com.cmozie.classes.zipcodecontentprovider/zipcodes/");
									
									ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String,String>>();
									Cursor cursor = getContentResolver().query(searchALL, null, null, null, null);
									//pulling in data from Local storage here
									display(cursor);
									
									
								
									Log.i("CURSOR",cursor.toString());
								
								
								
											
							
						}
						
						
					};
					
					//my intent services
					Messenger zipcodeMessenger = new Messenger(zipcodeHandler);
					
					Intent startZipcodeIntent = new Intent(_context, ZipcodeService.class);
					startZipcodeIntent.putExtra(ZipcodeService.MESSENGER_KEY, zipcodeMessenger);
					startZipcodeIntent.putExtra(ZipcodeService.enteredZipcode,searchALL);
					//Log.i("ZIP", ZipcodeService.queryReply2.toString());
					startService(startZipcodeIntent);
					
				}
				
				
			});
		
			
			

		 }
	public void display(Cursor cursor){
		
		//String JSONString = FileStuff.readStringFile(_context, "temp", false);
		
		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String,String>>();
		
		
		try{
		JSONObject json = new JSONObject(FileStuff.readStringFile(_context, "temp", false));
		//JSONObject json2 = new JSONObject(FileStuff.readStringFile(_context, "temp2", false));
		JSONArray ja = json.getJSONArray("zips");
		
		
	
		for (int i = 0; i < ja.length(); i++) {
			//sets a json object to access object values inside array
			
			
			JSONObject one = ja.getJSONObject(i);
			JSONObject two = ja.getJSONObject(0);
			
			
				_zipcode = one.getString("zip_code");
				_areaCode = one.getString("area_code");
				
				_region = one.getString("region");
				
				
				_zipcode2 = two.getString("zip_code");
				_area_code2 = two.getString("area_code");
				
				_region2 = two.getString("region");
				
				
			
		//setting my text to the values to the strings of the json data
	
		
		
			

		}
		
	} catch (Exception e) {
		Log.e("Buffer Error", "Error converting result " + e.toString());
	}
		//cursor
		cursor.moveToFirst();
		if (cursor.moveToFirst()) {
			
			for (int i = 0; i < cursor.getCount(); i++) {
				;
				HashMap<String, String> displayMap = new HashMap<String, String>();
				
				if (cursor.getString(0).contentEquals("1000133333")) {
					
					Log.i("CURSOR", cursor.getString(0));
				}
				displayMap.put("zipCode", cursor.getString(1));
				displayMap.put("areaCode", cursor.getString(2));
				displayMap.put("region", cursor.getString(3));
				
				cursor.moveToNext();
				//Log.i("CURSOR", cursor.toString());
				mylist.add(displayMap);
				//Log.i("LIST", mylist.toString());
			}
		}
		
SimpleAdapter adapter = new SimpleAdapter(_context, mylist, R.layout.list_row, new String[]{ "zipCode","areaCode","region"}, new int[]{R.id.row1, R.id.row2,R.id.row3});
		
		listview.setAdapter(adapter);
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
