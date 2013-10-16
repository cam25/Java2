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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class InfoActivity extends Activity{
	
	public static Context _context;
	ListView listview;
	Uri finalUri;
	public String zipp;
	public SimpleAdapter adapter;
	
	public ArrayList<HashMap<String, String>> mylist;
	
	
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
			
			
		 mylist = new ArrayList<HashMap<String,String>>();
			
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
					
			showGPS(zipp);
		
			 adapter = new SimpleAdapter(_context, mylist, R.layout.list_row2, new String[]{ "zipp","area","reg"}, new int[]{R.id.row1_2, R.id.row2_2,R.id.row3_2});
			
		listview.setAdapter(adapter);
		
		
		if (savedInstanceState != null) {
				
			 adapter = new SimpleAdapter(_context, mylist, R.layout.list_row2, new String[]{ "zipp","area","reg"}, new int[]{R.id.row1_2, R.id.row2_2,R.id.row3_2});
			
			listview.setAdapter(adapter);
			
			
					
				}
			//SimpleAdapter adapter = new SimpleAdapter(_context, null, R.layout.list_row2, new String[]{ "zipp","area","reg"}, new int[]{R.id.row1_2, R.id.row2_2,R.id.row3_2});
			//ArrayAdapter<String> adapter = new ArrayAdapter<String>(_context, R.id.list2);
			//listview.setAdapter(adapter);
			
		}
			//cursor
		
		
	}
	
	@Override  
	 public void onRestoreInstanceState(Bundle savedInstanceState) {  
	     
	   // Restore UI state from the savedInstanceState.  
	   // This bundle has also been passed to onCreate.  
	   
	    
	    mylist = (ArrayList<HashMap<String, String>>) savedInstanceState.getSerializable("mylist");
	   
	   
	   Log.i("TEST", mylist + "was saved");
	 
	   
	   super.onRestoreInstanceState(savedInstanceState);
	   Log.i("Bundle",savedInstanceState.toString());
	  
	 }
	public void showGPS(String zipcode) {
    	Intent intent = new Intent(Intent.ACTION_VIEW,
    			
    			
			Uri.parse("google.navigation:q="+ zipp));
    	
    	startActivity(intent);
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
	
	

}





