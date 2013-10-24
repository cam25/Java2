
package com.cmozie.javaweek4;

import java.util.ArrayList;
import java.util.HashMap;

import com.cmozie.classes.ListFragment.ListListener;
import com.cmozie.javaweek4.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
// TODO: Auto-generated Javadoc

/**
 * The Class InfoActivity.
 */
public class InfoActivity extends Activity implements ListListener{
	
	public static Context _context;
	public static ListView listview;
	Uri finalUri;
	public String zipp;
	public SimpleAdapter adapter;
	boolean isShown;
	Cursor cursor;
	public ArrayList<HashMap<String, String>> mylist;
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.listfrag);
		_context = this;
		
			
		Intent activityInfo = getIntent();
		
		if (activityInfo != null) {
			
			
			
			
			//array list to hold my data from intent
			
			//Log.i("Zip", zipp);
			mylist = new ArrayList<HashMap<String,String>>();
			//Log.i("List", mylist.toString());
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
			listview = (ListView) this.findViewById(com.cmozie.javaweek4.R.id.list2);
			 SimpleAdapter adapter = new SimpleAdapter(this, mylist, R.layout.list_row2, new String[]{ "zipp","area","reg"}, new int[]{R.id.row1_2, R.id.row2_2,R.id.row3_2});
         listview.setAdapter(adapter);
			
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

	@Override
	public void finish() {
	    Intent data = new Intent();
	    data.putExtra("zipCode",(zipp));
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

	@Override
	public void gpsShow(String zipcode) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(Intent.ACTION_VIEW,
    			
    			
				Uri.parse("google.navigation:q="+ zipp));
	    	//starts the intent activity
	    	startActivity(intent);
		
	}
	@Override
	public void getData() {
		// TODO Auto-generated method stub
		Intent activityInfo = getIntent();
		//Log.i("test", activityInfo.toString());
		
		
		if (activityInfo != null) {
			
			Log.i("GETDATA", "WORKING!");
			
			
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
			
       	
		}
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		  if (resultCode == RESULT_OK && requestCode == 0) {
		  
		    if (data.hasExtra("zip_code")) {
		    	Log.i("data", data.getStringExtra(zipp));
		  	  Toast.makeText(getApplicationContext(), "MAIN ACTIVITY - Zipp Passed = " + data.getExtras().getString("zip_code"), Toast.LENGTH_SHORT).show();

			}
		  
		  
		  }
		}
	

}





