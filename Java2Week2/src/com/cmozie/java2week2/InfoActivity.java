package com.cmozie.java2week2;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cmozie.Libz.FileStuff;
import com.cmozie.classes.ZipcodeContentProvider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class InfoActivity extends Activity{
	
	public static Context _context;
	ListView listview;
	Uri finalUri;
	public String zipp;
	
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.form2);
		ListView listview = (ListView) this.findViewById(R.id.list2);
	
		
		View listHeader = this.getLayoutInflater().inflate(R.layout.list_header2, null);
		listview.addHeaderView(listHeader);
		
		_context = this;
		
		
		
		
		Intent activityInfo = getIntent();
		Log.i("test", activityInfo.toString());
		if (activityInfo != null) {
			
			ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String,String>>();
			
			 zipp = activityInfo.getExtras().getString("zip_code");
			String area = activityInfo.getExtras().getString("area_code");
			String reg = activityInfo.getExtras().getString("region");
			
		/*	finalUri = Uri.parse("content://com.cmozie.classes.zipcodecontentprovider/zipcodes/");
			Cursor cursor = getContentResolver().query(finalUri, null, null, null, null);
			display(cursor);*/
			HashMap<String, String> displayMap = new HashMap<String, String>();
			
			displayMap.put("zipp", zipp);
			displayMap.put("area", area);
			displayMap.put("reg", reg);
			
			mylist.add(displayMap);
			if (zipp == null) {
				
			zipp = "No String Found";
			}
			
			Log.i("Zipp", zipp);
			Log.i("area", area);
			Log.i("reg", reg);
	
			Log.i("mylist", mylist.toString());
					
					
				
		
			SimpleAdapter adapter = new SimpleAdapter(_context, mylist, R.layout.list_row2, new String[]{ "zipp","area","reg"}, new int[]{R.id.row1_2, R.id.row2_2,R.id.row3_2});
			
		listview.setAdapter(adapter);
		
		
			//SimpleAdapter adapter = new SimpleAdapter(_context, null, R.layout.list_row2, new String[]{ "zipp","area","reg"}, new int[]{R.id.row1_2, R.id.row2_2,R.id.row3_2});
			//ArrayAdapter<String> adapter = new ArrayAdapter<String>(_context, R.id.list2);
			//listview.setAdapter(adapter);
			
		}
			//cursor
	
		
	}
	
	@Override
	public void finish() {
	    Intent data = new Intent();
	    data.putExtra("zip_code",(zipp));
	    data.putExtra("areaCode",("area_code"));
	    setResult(RESULT_OK, data);
	    super.finish();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void display(Cursor cursor){
		
		
		  ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String,String>>();
		
		
		try{
		JSONObject json = new JSONObject(FileStuff.readStringFile(_context, "temp", false));
		JSONArray ja = json.getJSONArray("zips");
		
		
	
		for (int i = 0; i < ja.length(); i++) {
			//sets a json object to access object values inside array
			
			
			JSONObject one = ja.getJSONObject(i);
			


			String _zipcode = one.getString("zip_code");
			String _areaCode = one.getString("area_code");
			String _region = one.getString("region");
		/*	String _county = one.getString("county");
			String _latitude = one.getString("latitude");
			String _longitude = one.getString("longitude");
			String _timezone = one.getString("time_zone");*/
			
			HashMap<String, String> displayMap = new HashMap<String, String>();
			
			displayMap.put("zipCode", _zipcode);
			displayMap.put("areaCode", _areaCode);
			displayMap.put("region", _region);
		/*	displayMap.put("county", _county);
			displayMap.put("latidue", _latitude);
			displayMap.put("longitude", _longitude);
			displayMap.put("timezone", _timezone);*/
		
				
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
					
					//Log.i("CURSOR", "match");
				}
				displayMap.put("zipCode", cursor.getString(1));
				displayMap.put("areaCode", cursor.getString(2));
				displayMap.put("region", cursor.getString(3));
			/*	displayMap.put("county", cursor.getString(4));
				displayMap.put("latitude", cursor.getString(5));
				displayMap.put("longitude", cursor.getString(6));
				displayMap.put("time_zone", cursor.getString(7));*/
				
				
				cursor.moveToNext();
				//Log.i("CURSOR", cursor.toString());
				mylist.add(displayMap);
				
				
				
				
			}
		}
		
		//SimpleAdapter adapter = new SimpleAdapter(_context, mylist, R.layout.list_row2, new String[]{ "zipCode","areaCode","region"}, new int[]{R.id.row1_2, R.id.row2_2,R.id.row3_2,R.id.row4_2,R.id.row5_2,R.id.row6_2,R.id.row7_2});
		
		//listview.setAdapter(adapter);
		
		
	}

}





