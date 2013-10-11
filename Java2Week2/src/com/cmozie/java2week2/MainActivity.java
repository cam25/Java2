/*
 * project 			Java2Week2
 * 
 * package			com.cmozie.java2week2
 * 
 * name				cameronmozie
 * 
 * date				Oct 10, 2013
 */

package com.cmozie.java2week2;


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
	String _region;
	String _zipcode2;
	String _area_code2;
	String _region2;
	
	String temp;

	public int position;
	ListView listview;
	

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
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
				 
			
				 
				 new Cities("", "New York", "NY"),
				 new Cities("", "Washington", "DC"),
				 new Cities("", "Miami", "FL"),
				 new Cities("", "Chicago", "IL"),
				 new Cities("", "San Francisco", "CA")
		 });
		 
		 listAdapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
		
		 spinner = (Spinner) findViewById(R.id.favList);
		spinner.setAdapter(listAdapter);
	
		getRegion = (Button) findViewById(R.id.getHistory);
				
		
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
		 						
		 						JSONArray ja = json.getJSONArray("zips");
		 						
		 						
		 					
		 						for (int i = 0; i < ja.length(); i++) {
		 							//sets a json object to access object values inside array
		 							
		 							
		 							JSONObject one = ja.getJSONObject(2);
		 							JSONObject two = ja.getJSONObject(2);
		 							
		 							
		 								_zipcode = one.getString("zip_code");
		 								_areaCode = one.getString("area_code");
		 								
		 								_region = one.getString("region");
		 								
		 								
		 								_zipcode2 = two.getString("zip_code");
		 								_area_code2 = two.getString("area_code");
		 								
		 								_region2 = two.getString("region");
		 								
		 								
		 						
		 						
		 					//setting of my switch case to work behind the scenes which switch at position of the cells of the spinner and query the api based on selected postion
		 				 position = spinner.getSelectedItemPosition();
		 				 
		 				Log.i("all", String.valueOf(position));
		 				//Log.i("Pos", parent.getItemAtPosition(pos).toString());
		 					switch (position) {
		 					
							case 0:
						
								zipcode = "content://" + ZipcodeContentProvider.AUTHORITY + "/zipcodes/" + 1 ;

								break;
								
							case 1:
								
								zipcode = "content://" + ZipcodeContentProvider.AUTHORITY + "/zipcodes/" + 2;
								break;
							
							case 2:
								
								zipcode =  "content://" + ZipcodeContentProvider.AUTHORITY + "/zipcodes/" + 3;
								break;
							case 3:
								zipcode = "content://" + ZipcodeContentProvider.AUTHORITY + "/zipcodes/" + 4;
								break;
							case 4:
								zipcode = "content://" + ZipcodeContentProvider.AUTHORITY + "/zipcodes/" + 5;
								break;
							

							default:
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
							
							//string selected is my query reply from my ZipcodeService
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
					//Log.i("ZIP", ZipcodeService.queryReply2.toString());
					startService(startZipcodeIntent);
					
				}
				
				
			});
		
		 if (savedInstanceState != null) {
				
				Log.i("Bundle","Test");
			
				ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String,String>>();
				//ArrayList<HashMap<String, String>> mylist = (ArrayList<HashMap<String, String>>) savedInstanceState.getSerializable("saved");
					
				
					Bundle bundle = new Bundle();
					
					
					try{
					JSONObject json = new JSONObject(FileStuff.readStringFile(_context, "temp", false));
					JSONArray ja = json.getJSONArray("zips");
					
					bundle.putString("json", json.toString());
					
					savedInstanceState.putString("saved", json.toString());
					Log.i("JSONBUNDLE", bundle.toString());
				
					for (int i = 0; i < ja.length(); i++) {
						//sets a json object to access object values inside array
						
						
						JSONObject one = ja.getJSONObject(i);
						JSONObject two = ja.getJSONObject(i);
						
						
							_zipcode = one.getString("zip_code");
							_areaCode = one.getString("area_code");
							
							_region = one.getString("region");
							
							
							_zipcode2 = two.getString("zip_code");
							_area_code2 = two.getString("area_code");
							
							_region2 = two.getString("region");
			
						

					}
					
					HashMap<String, String> displayMap = new HashMap<String, String>();
					
					
					displayMap.put("zipCode", ja.getString(1));
					displayMap.put("areaCode", ja.getString(2));
					displayMap.put("region", ja.getString(3));
					
				
					//Log.i("CURSOR", cursor.toString());
					mylist.add(displayMap);
					if (savedInstanceState != null) {
						
						savedInstanceState.getStringArray(mylist.toString());
					}
				} catch (Exception e) {
					Log.e("Buffer Error", "Error converting result " + e.toString());
				}
					//cursor
					if (savedInstanceState != null) {
						SimpleAdapter adapter = new SimpleAdapter(_context, mylist, R.layout.list_row, new String[]{ "zipCode","areaCode","region"}, new int[]{R.id.row1, R.id.row2,R.id.row3});
						
						listview.setAdapter(adapter);
					}
							
				spinner.setSelection(savedInstanceState.getInt("spinner"));
			
					
			}else{
				
				
			} 
			

		 }
	
	/**
	 * Display.
	 *
	 * @param cursor the cursor
	 */
	public void display(Cursor cursor){
		
		
		  ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String,String>>();
		
		
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
				HashMap<String, String> displayMap = new HashMap<String, String>();
				
				if (cursor.getString(0).contentEquals("95105")) {
					
					Log.i("CURSOR", "match");
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
	 * @see android.app.Activity#onSaveInstanceState(android.os.Bundle)
	 */
	@Override
	public void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		outState.putString("zip_code",_zipcode);
		outState.putString("area_code", _areaCode);
		outState.putString("region", _region);
		outState.putInt("spinner", spinner.getSelectedItemPosition());
		
		
	}

	
	 /* (non-Javadoc)
 	 * @see android.app.Activity#onRestoreInstanceState(android.os.Bundle)
 	 */
 	@Override  
	 public void onRestoreInstanceState(Bundle savedInstanceState) {  
	   super.onRestoreInstanceState(savedInstanceState);  
	   // Restore UI state from the savedInstanceState.  
	   // This bundle has also been passed to onCreate.  
	   
	  savedInstanceState.get("saved");
	  savedInstanceState.get(_areaCode);
	  savedInstanceState.get(_region);
	   Log.i("TEST", "saved" + "was saved");
	   Log.i("TEST", _areaCode + "was saved");
	   Log.i("TEST", _region + "was saved");
	   
	   
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
	
	
	
}
