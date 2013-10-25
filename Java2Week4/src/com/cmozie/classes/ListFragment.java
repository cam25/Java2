/*
 * project 			javaWeek4
 * 
 * package			com.cmozie.classes
 * 
 * name				cameronmozie
 * 
 * date				Oct 24, 2013
 */
package com.cmozie.classes;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cmozie.Libz.FileStuff;
import com.cmozie.javaweek4.R;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

// TODO: Auto-generated Javadoc
/**
 * The Class ListFragment.
 */
public class ListFragment extends Fragment {
	Context _context;
	
	public static String  zipp;
	public static Button getRegion;
   // public static SimpleAdapter adapter;
    public ArrayList<HashMap<String, String>> mylist;
    public static ListView listview;
    
    String _zipcode;
	String _areaCode;
	String _region;
	String _county;
	String _timezone;
	String _latitude;
	String _longitude;
	public HashMap<String, String> displayMap;
    
	/**
	 * The listener interface for receiving list events.
	 * The class that is interested in processing a list
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addListListener<code> method. When
	 * the list event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see ListEvent
	 */
	public interface ListListener {

		/**
		 * Gets the data.
		 *
		 * @return the data
		 */
		public void getData();
	
	};
	private ListListener listener;
	
	 
	/* (non-Javadoc)
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		
		LinearLayout view = (LinearLayout) inflater.inflate(R.layout.form2, container, false);
	
		 listview = (ListView) view.findViewById(R.id.list2);
		 View listHeader = inflater.inflate(com.cmozie.javaweek4.R.layout.list_header2, null);
			listview.addHeaderView(listHeader);
			ArrayList<String> items = new ArrayList<String> ();

			
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.list_header2,items);
			listview.setAdapter(adapter);
	
		return view;
		
	}
	
	/* (non-Javadoc)
	 * @see android.app.Fragment#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	
		
	}
	
	
	
	/**
	 * Show data.
	 */
	public void showData(){
		
Intent activityInfo = getActivity().getIntent();
		
		if (activityInfo != null) {

			mylist = new ArrayList<HashMap<String,String>>();
		
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
			
			 SimpleAdapter adapter = new SimpleAdapter(getActivity(), mylist, R.layout.list_row2, new String[]{ "zipp","area","reg"}, new int[]{R.id.row1_2, R.id.row2_2,R.id.row3_2});
         listview.setAdapter(adapter);
			
		}
		
	}
	
	public void display(Cursor cursor){
		
		  mylist = new ArrayList<HashMap<String,String>>();
		
		
		try{
		JSONObject json = new JSONObject(FileStuff.readStringFile(getActivity(), "temp", false));
		JSONArray ja = json.getJSONArray("zips");
		
		
	
		for (int i = 0; i < ja.length(); i++) {
			//sets a json object to access object values inside array
			
			
			JSONObject one = ja.getJSONObject(i);
		


			_zipcode = one.getString("zip_code");
			_areaCode = one.getString("area_code");
			_region = one.getString("region");
			_county = one.getString("county");
			_latitude = one.getString("latitude");
			_longitude = one.getString("longitude");
			_timezone = one.getString("time_zone");
			
		
				
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
				
				ListView listview = (ListView) getActivity().findViewById(com.cmozie.javaweek4.R.id.list2);
			SimpleAdapter	adapter = new SimpleAdapter(getActivity(), mylist, R.layout.list_row2, new String[]{ "zipCode","areaCode","region","county"}, new int[]{R.id.row1_2, R.id.row2_2,R.id.row3_2});
              
              listview.setAdapter(adapter);
              
              
			}
			}
		}
	
	
	
	/* (non-Javadoc)
	 * @see android.app.Fragment#onAttach(android.app.Activity)
	 */
	@Override
	public void onAttach(Activity activity)
	
	{
		super.onAttach(activity);
		
		
		try {
			
			
			if (listener instanceof ListListener) {
				
				listener = (ListListener) activity;
			}
			
			
		} catch (ClassCastException e) {
			// TODO: handle exception
			throw new ClassCastException(activity.toString() + "Must Implement ListListener");
		}
	}
	
	
}
