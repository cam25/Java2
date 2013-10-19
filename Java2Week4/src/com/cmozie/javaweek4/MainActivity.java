/*
 * project 			Java2Week3
 * 
 * package			com.cmozie.java2week2
 * 
 * name				cameronmozie
 * 
 * date				Oct 17, 2013
 */


package com.cmozie.javaweek4;


import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
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
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.TextView;
import com.cmozie.Libz.FileStuff;
import com.cmozie.classes.*;

import webConnections.*;


// TODO: Auto-generated Javadoc
/**
 * The Class MainActivity.
 */

@SuppressLint("HandlerLeak")
public class MainActivity extends Activity implements FormFragment.FormListener {

	//--public statics
	public static Context _context;

	public static Button searchButton;
	
	
	public int selected;
	//public int selected; 
	public static boolean IsButtonPress;
	public Spinner spinner = null;
	//layout
	TextView _popularZips;
	Button _pop;
	public static Button getRegion;
	EditText contentQuery;
	Uri searchALL;
	Uri searchFilter;
	public ArrayList<HashMap<String, String>> mylist;
	//bool
	Boolean _connected = false;
	public static SimpleAdapter adapter;
	public static Cursor cursor;
	

	//strings
	String _zipcode;
	String _areaCode;
	String _region;
	String _county;
	String _timezone;
	String _latitude;
	String _longitude;
	
	String _zipcode2;
	String _area_code2;
	String _region2;
	
	String temp;

	public int position;
	ListView listview;
	public String zipcode;
	public HashMap<String, String> displayMap;

	

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.formfrag);
		listview = (ListView) this.findViewById(R.id.list);
	
		
		View listHeader = this.getLayoutInflater().inflate(R.layout.list_header, null);
		listview.addHeaderView(listHeader);
		//setting contentView to my inflated view/form
		position = Spinner.INVALID_POSITION;
		_context = this;
		
		
		 //webConnection jar file usage
		 _connected = WebStuff.getConnectionStatus(_context);
		 if (_connected) {
			 
				
				
			Log.i("Network Connection", WebStuff.getConnectionType(_context));
		
			 
			 //array adapter for my cities where i create new objects for each location
		
		
			//getRegion = (Button) findViewById(R.id.getHistory);
					IsButtonPress = false;
					
			
			 //popular zipcodes onclick
			 //_pop = (Button) findViewById(R.id.popularZipcodes);
			
			 			if (savedInstanceState != null) {
			 				
			 			    mylist = (ArrayList<HashMap<String, String>>) savedInstanceState.getSerializable("mylist");
			 			    if (mylist != null) {
			 			    	adapter = new SimpleAdapter(_context, mylist, R.layout.list_row, new String[]{ "zipCode","areaCode","region"}, new int[]{R.id.row1, R.id.row2,R.id.row3});
			 					
			 					listview.setAdapter(adapter);
			 				
			 					rowSelect();
			 					
			 					
			 					
			 				}

			 			}
						
			 		
								// TODO Auto-generated method stub
							/*_pop.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									

									
								
							
			 			spinner.setVisibility(View.VISIBLE);
			 			spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			 				
			 				
							public void onItemSelected(AdapterView<?> parent,View v,int pos, long id){
			 							Log.i("HIT","THE SPINNER");
			 				
			 					
			 					try{
			 						JSONObject json = new JSONObject(FileStuff.readStringFile(_context, "temp", false));
			 						
			 						JSONArray ja = json.getJSONArray("zips");
			 						
			 						
			 					
			 						for (int i = 0; i < ja.length(); i++) {
			 							//sets a json object to access object values inside array
			 							
			 							
			 							JSONObject one = ja.getJSONObject(0);
			 							JSONObject two = ja.getJSONObject(0);
			 							
			 							
			 								_zipcode = one.getString("zip_code");
			 								_areaCode = one.getString("area_code");
			 								
			 								_region = one.getString("region");
			 								
			 								
			 								_zipcode2 = two.getString("zip_code");
			 								_area_code2 = two.getString("area_code");
			 								
			 								_region2 = two.getString("region");
			 								
			 								
			 						
			 						
			 					//setting of my switch case to work behind the scenes which switch at position of the cells of the spinner and query the api based on selected postion
			 				 position = spinner.getSelectedItemPosition();
			 				 
			 	
			 					switch (position) {
			 					
								case 0://new york
							
									zipcode = "content://" + ZipcodeContentProvider.AUTHORITY + "/zipcodes/NY";
									//Log.i("Main","uri = "+zipcode);
									searchFilter = Uri.parse(zipcode);
									
									Cursor NY = getContentResolver().query(searchFilter, null, null, null, null);
									//pulling in data from Local storage here
									display(NY);
									break;
									
								case 1://washington
									
									zipcode = "content://" + ZipcodeContentProvider.AUTHORITY + "/zipcodes/WA";
									
									searchFilter = Uri.parse(zipcode);
									
									Log.i("MAIN", "uri="+zipcode);
									cursor = getContentResolver().query(searchFilter, null, null, null, null);
									if (cursor != null ) {
										display(cursor);
										
									}

									
									break;
								
								case 2: // Miami
									
									zipcode = "content://" + ZipcodeContentProvider.AUTHORITY + "/zipcodes/MI";
									searchFilter = Uri.parse(zipcode);
									cursor = getContentResolver().query(searchFilter, null, null, null, null);
									if (cursor != null ) {
										display(cursor);
									}

									Log.i("MAIN", "uri="+zipcode);
									//Toast.makeText(_context, "Miami!", Toast.LENGTH_SHORT).show();
									break;
									
								case 3: //Chicago
									zipcode = "content://" + ZipcodeContentProvider.AUTHORITY + "/zipcodes/IL";
									searchFilter = Uri.parse(zipcode);
									cursor = getContentResolver().query(searchFilter, null, null, null, null);
									if (cursor != null ) {
										display(cursor);
										
									}
									Log.i("MAIN", "uri="+zipcode);
									//Toast.makeText(_context, "Chicago!", Toast.LENGTH_SHORT).show();
									break;
								case 4: //San Francisco
									zipcode = "content://" + ZipcodeContentProvider.AUTHORITY + "/zipcodes/SF";
									searchFilter = Uri.parse(zipcode);
									cursor = getContentResolver().query(searchFilter, null, null, null, null);
									if (cursor != null ) {
										display(cursor);
									}
									Log.i("MAIN", "uri="+zipcode);
									//Toast.makeText(_context, "San Fran!", Toast.LENGTH_SHORT).show();
									break;

								default:
									Toast.makeText(_context, "default hit!", Toast.LENGTH_SHORT).show();
									break;
									
									
								}
			 					
			 						}
			 					} catch (Exception e) {
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
			 						Log.e("Buffer Error", "Error converting result " + e.toString());
			 					}
			 						
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
							});*/
							/* getRegion.setOnClickListener(new OnClickListener() {
									//combine all the zipcodes and place call the query on all of them?
									
								
									@Override
									public void onClick(View v) {
										// TODO Auto-generated method stub
										
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
										
										Handler zipcodeHandler = new Handler() {

											
											@Override
											public void handleMessage(Message msg) {
												// TODO Auto-generated method stub
												Log.i("HIT","HANDLER");
												searchALL = Uri.parse("content://com.cmozie.classes.zipcodecontentprovider/zipcodes/");
												
												//ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String,String>>();
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
										startService(startZipcodeIntent);
										
										
									}
								
									
								});*/
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
		
		 	

		
		
		
			

		 }
	
/*	*//**
	 * Display.
	 *
	 * @param cursor the cursor
	 */
	public void display(Cursor cursor){
		
		
		  mylist = new ArrayList<HashMap<String,String>>();
		
		
		try{
		JSONObject json = new JSONObject(FileStuff.readStringFile(_context, "temp", false));
		JSONArray ja = json.getJSONArray("zips");
		
		
	
		for (int i = 0; i < ja.length(); i++) {
			//sets a json object to access object values inside array
			
			
			JSONObject one = ja.getJSONObject(i);
			JSONObject two = ja.getJSONObject(0);


			_zipcode = one.getString("zip_code");
			_areaCode = one.getString("area_code");
			_region = one.getString("region");
			_county = one.getString("county");
			_latitude = one.getString("latitude");
			_longitude = one.getString("longitude");
			_timezone = one.getString("time_zone");
			
			
			_zipcode2 = two.getString("zip_code");
			_area_code2 = two.getString("area_code");
			_region2 = two.getString("region");
				
		}
		
	} catch (Exception e) {
		Log.e("Buffer Error", "Error converting result " + e.toString());
	}
		//cursor
		cursor.moveToFirst();
		if (cursor.moveToFirst()) {
			
			for (int i = 0; i < cursor.getCount(); i++) {
				;
				displayMap = new HashMap<String, String>();
				
				if (cursor.getString(0).contentEquals("95105")) {
					
					Log.i("CURSOR", "match");
				}
				displayMap.put("zipCode", cursor.getString(1));
				displayMap.put("areaCode", cursor.getString(2));
				displayMap.put("region", cursor.getString(3));
				//displayMap.put("county", cursor.getString(4));
				
				cursor.moveToNext();
				
				mylist.add(displayMap);
				
				Log.i("mylist", mylist.toString());
				
				
			}
		}
		
		adapter = new SimpleAdapter(_context, mylist, R.layout.list_row, new String[]{ "zipCode","areaCode","region","county"}, new int[]{R.id.row1, R.id.row2,R.id.row3});
		
		listview.setAdapter(adapter);
		//calls my select row functoin 
		rowSelect();
		
	}
	
	/**
	 * Row select.
	 */
	private void rowSelect (){
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			
			
			@SuppressWarnings("unchecked")
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Log.i("Row","Selected ="+ arg2 + "clicked");

				//hashmap for listview cells at position
				HashMap<String, String> intentMap = (HashMap<String, String>) listview.getItemAtPosition(arg2);
				
				//if any of my cells are selected then i grab the zipcode areacode and region for those cells and 
				//store it inside 
					if (arg2 == 1 || arg2 == 2 || arg2 ==3 || arg2 == 4 || arg2 == 5|| arg2 == 6||arg2 == 7
							|| arg2 == 8|| arg2 == 9|| arg2 == 10|| arg2 == 11|| arg2 == 12|| arg2 == 13
							|| arg2 == 14|| arg2 == 15|| arg2 == 16|| arg2 == 17|| arg2 == 18|| arg2 == 19|| 
							arg2 == 20) {
						
						
						Intent infoIntent = new Intent(_context,InfoActivity.class);
						infoIntent.putExtra("zip_code", intentMap.get("zipCode"));
						infoIntent.putExtra("area_code", intentMap.get("areaCode"));
						infoIntent.putExtra("region", intentMap.get("region"));
					
					
						startActivityForResult(infoIntent, 0);
						Log.i("Map", intentMap.toString());
						Log.i("INTENT", infoIntent.toString());
						
						
					}
			}
		});
		
		
	};
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  if (resultCode == RESULT_OK && requestCode == 0) {
	  
	    if (data.hasExtra("zip_code")) {
	    	
	  	  Toast.makeText(getApplicationContext(), "MAIN ACTIVITY - Zipp Passed = " + data.getExtras().getString("zip_code"), Toast.LENGTH_SHORT).show();

		}
	  
	  
	  }
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onSaveInstanceState(android.os.Bundle)
	 */
	@Override
	public void onSaveInstanceState(Bundle outState)
	{
		
		outState.putString("zip_code",_zipcode);
		outState.putString("area_code", _areaCode);
		outState.putString("region", _region);
		
		outState.putInt("spinner", spinner.getSelectedItemPosition());
		outState.putSerializable("mylist", mylist);
		outState.putBoolean("button", _pop.isSelected());
	
		
		
		super.onSaveInstanceState(outState);
		
	}

	
	 /* (non-Javadoc)
 	 * @see android.app.Activity#onRestoreInstanceState(android.os.Bundle)
 	 */
 	@SuppressWarnings("unchecked")
	@Override  
	 public void onRestoreInstanceState(Bundle savedInstanceState) {  
	     
	   // Restore UI state from the savedInstanceState.  
	   // This bundle has also been passed to onCreate.  
	   
	    _zipcode = savedInstanceState.getString("_zip_code");
	    mylist = (ArrayList<HashMap<String, String>>) savedInstanceState.getSerializable("mylist");
	   
	   
	   Log.i("TEST", mylist + "was saved");
	   Log.i("TEST", _areaCode + "was saved");
	   Log.i("TEST", _region + "was saved");
	   
	   super.onRestoreInstanceState(savedInstanceState);
	   Log.i("Bundle",savedInstanceState.toString());
	  
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

	@Override
	public void onQueryAll() {
		// TODO Auto-generated method stub
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
		
		Handler zipcodeHandler = new Handler() {

			
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				Log.i("HIT","HANDLER");
				searchALL = Uri.parse("content://com.cmozie.classes.zipcodecontentprovider/zipcodes/");
				
				//ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String,String>>();
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
		startService(startZipcodeIntent);
		
		
	}
	
	
	@Override
	
	public void onPopSelect() {
	
		
		ArrayAdapter<Cities> listAdapter = new ArrayAdapter<Cities>(_context, android.R.layout.simple_spinner_item, new Cities[]{
                
                
                
                new Cities("", "New York", "NY"),
                new Cities("", "Washington", "DC"),
                new Cities("", "Miami", "FL"),
                new Cities("", "Chicago", "IL"),
                new Cities("", "San Francisco", "CA")
});

listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

spinner = (Spinner) findViewById(R.id.favList);
spinner.setAdapter(listAdapter);
		
			spinner.setVisibility(View.VISIBLE);
			spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
				
				
			public void onItemSelected(AdapterView<?> parent,View v,int pos, long id){
							//Log.i("HIT","THE SPINNER");
				
					
					try{
						JSONObject json = new JSONObject(FileStuff.readStringFile(_context, "temp", false));
						
						JSONArray ja = json.getJSONArray("zips");
						
						
					
						for (int i = 0; i < ja.length(); i++) {
							//sets a json object to access object values inside array
							
							
							JSONObject one = ja.getJSONObject(0);
							JSONObject two = ja.getJSONObject(0);
							
							
								_zipcode = one.getString("zip_code");
								_areaCode = one.getString("area_code");
								
								_region = one.getString("region");
								
								
								_zipcode2 = two.getString("zip_code");
								_area_code2 = two.getString("area_code");
								
								_region2 = two.getString("region");
								
								
						
						
					//setting of my switch case to work behind the scenes which switch at position of the cells of the spinner and query the api based on selected postion
				 position = spinner.getSelectedItemPosition();
				 
	
					switch (position) {
					
				case 0://new york
			
					zipcode = "content://" + ZipcodeContentProvider.AUTHORITY + "/zipcodes/NY";
					//Log.i("Main","uri = "+zipcode);
					searchFilter = Uri.parse(zipcode);
					
					Cursor NY = getContentResolver().query(searchFilter, null, null, null, null);
					//pulling in data from Local storage here
					display(NY);
					break;
					
				case 1://washington
					
					zipcode = "content://" + ZipcodeContentProvider.AUTHORITY + "/zipcodes/WA";
					
					searchFilter = Uri.parse(zipcode);
					
					Log.i("MAIN", "uri="+zipcode);
					cursor = getContentResolver().query(searchFilter, null, null, null, null);
					if (cursor != null ) {
						display(cursor);
						
					}

					
					break;
				
				case 2: // Miami
					
					zipcode = "content://" + ZipcodeContentProvider.AUTHORITY + "/zipcodes/MI";
					searchFilter = Uri.parse(zipcode);
					cursor = getContentResolver().query(searchFilter, null, null, null, null);
					if (cursor != null ) {
						display(cursor);
					}

					Log.i("MAIN", "uri="+zipcode);
					//Toast.makeText(_context, "Miami!", Toast.LENGTH_SHORT).show();
					break;
					
				case 3: //Chicago
					zipcode = "content://" + ZipcodeContentProvider.AUTHORITY + "/zipcodes/IL";
					searchFilter = Uri.parse(zipcode);
					cursor = getContentResolver().query(searchFilter, null, null, null, null);
					if (cursor != null ) {
						display(cursor);
						
					}
					Log.i("MAIN", "uri="+zipcode);
					//Toast.makeText(_context, "Chicago!", Toast.LENGTH_SHORT).show();
					break;
				case 4: //San Francisco
					zipcode = "content://" + ZipcodeContentProvider.AUTHORITY + "/zipcodes/SF";
					searchFilter = Uri.parse(zipcode);
					cursor = getContentResolver().query(searchFilter, null, null, null, null);
					if (cursor != null ) {
						display(cursor);
					}
					Log.i("MAIN", "uri="+zipcode);
					//Toast.makeText(_context, "San Fran!", Toast.LENGTH_SHORT).show();
					break;

				default:
					Toast.makeText(_context, "default hit!", Toast.LENGTH_SHORT).show();
					break;
					
					
				}
					
						}
					} catch (Exception e) {
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
						Log.e("Buffer Error", "Error converting result " + e.toString());
					}
						
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
			//_pop.setClickable(false);
			//_pop.setVisibility(View.GONE);
			
			
			
				
		
	}
	
	
	
}
