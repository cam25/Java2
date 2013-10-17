/*
 * project 			Java2Week2
 * 
 * package			com.cmozie.java2week2
 * 
 * name				cameronmozie
 * 
 * date				Oct 17, 2013
 */
package com.cmozie.java2week2;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
// TODO: Auto-generated Javadoc

/**
 * The Class InfoActivity.
 */
public class InfoActivity extends Activity{
	
	public static Context _context;
	ListView listview;
	Uri finalUri;
	public String zipp;
	public SimpleAdapter adapter;
	boolean isShown;
	public ArrayList<HashMap<String, String>> mylist;
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.form2);
		ListView listview = (ListView) this.findViewById(R.id.list2);
	
		
		View listHeader = this.getLayoutInflater().inflate(R.layout.list_header2, null);
		listview.addHeaderView(listHeader);
		
		_context = this;
		AlertDialog.Builder alert = new AlertDialog.Builder(_context);
			alert.setTitle("Info");
			alert.setMessage("Select the location to launch GPS");
			alert.setCancelable(false);
			alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			
				@Override
				public void onClick(DialogInterface dialog, int which) {

					dialog.cancel();
				}
			});
			alert.show();
		
			//on click listenter for the listview
			listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Log.i("Row","Selected ="+ arg2 + "clicked");

				//array adapter for listview cells
					if (arg2 == 1 ) {
						
						//get gps
						showGPS(zipp);
						
						
					}
			}
		});
		
		
			Intent activityInfo = getIntent();
			Log.i("test", activityInfo.toString());
			
		if (activityInfo != null) {
			
			Toast.makeText(_context, "Second Activity", Toast.LENGTH_SHORT).show();
			
			
			//array list to hold my data from intent
			mylist = new ArrayList<HashMap<String,String>>();
			
			//strings to hold my values
			 zipp = activityInfo.getExtras().getString("zip_code");
			String area = activityInfo.getExtras().getString("area_code");
			String reg = activityInfo.getExtras().getString("region");
	
			HashMap<String, String> displayMap = new HashMap<String, String>();
			
			
			//storing my values inside hashmap
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
	
			 adapter = new SimpleAdapter(_context, mylist, R.layout.list_row2, new String[]{ "zipp","area","reg"}, new int[]{R.id.row1_2, R.id.row2_2,R.id.row3_2});
			
		listview.setAdapter(adapter);
		
		if (savedInstanceState != null) {
				
			 adapter = new SimpleAdapter(_context, mylist, R.layout.list_row2, new String[]{ "zipp","area","reg"}, new int[]{R.id.row1_2, R.id.row2_2,R.id.row3_2});
			
			listview.setAdapter(adapter);

			}
		}

	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onRestoreInstanceState(android.os.Bundle)
	 */
	@SuppressWarnings("unchecked")
	@Override  
	 public void onRestoreInstanceState(Bundle savedInstanceState) {  
	     
	   // Restore UI state from the savedInstanceState.  
	   // This bundle has also been passed to onCreate.  
	   
	    
	    mylist = (ArrayList<HashMap<String, String>>) savedInstanceState.getSerializable("mylist");
	   
	   
	   Log.i("TEST", mylist + "was saved");
	 
	   
	   super.onRestoreInstanceState(savedInstanceState);
	   Log.i("Bundle",savedInstanceState.toString());
	  
	 }
	
	/**
	 * Show gps.
	 *
	 * @param zipcode the zipcode
	 */
	public void showGPS(String zipcode) {
    	Intent intent = new Intent(Intent.ACTION_VIEW,
    			
    			
			Uri.parse("google.navigation:q="+ zipp));
    	//starts the intent activity
    	startActivity(intent);
    }
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#finish()
	 */
	@Override
	public void finish() {
	    Intent data = new Intent();
	    data.putExtra("zip_code",(zipp));
	    data.putExtra("areaCode",("area_code"));
	    setResult(RESULT_OK, data);
	    super.finish();
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





