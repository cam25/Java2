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

import com.cmozie.javaweek4.MainActivity;
import com.cmozie.javaweek4.R;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;


// TODO: Auto-generated Javadoc
/**
 * The Class FormFragment.
 */
public class FormFragment extends Fragment {
	Button _pop;
	public static Button getRegion;
	ListView listview;
	static Spinner spinner;
	public static Context _context;
	private FormListener listener;
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
	public String zipcode;
	public HashMap<String, String> displayMap;
	public static ArrayList<HashMap<String, String>> mylist;
	
	public static LinearLayout view;
	//bool
	Boolean _connected = false;
	public static SimpleAdapter adapter;
	
	
	/**
	 * The listener interface for receiving form events.
	 * The class that is interested in processing a form
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addFormListener<code> method. When
	 * the form event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see FormEvent
	 */
	public interface FormListener {
		
		/**
		 * On query all.
		 */
		public void onQueryAll();
		
		/**
		 * On pop select.
		 */
		public void onPopSelect();
		
		/**
		 * Display.
		 *
		 * @param cursor the cursor
		 */
		public void display(Cursor cursor);
		
		/**
		 * Row select.
		 */
		public void rowSelect();
	}
	
	/* (non-Javadoc)
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		
		super.onCreateView(inflater, container, savedInstanceState);
		
		LinearLayout view = (LinearLayout) inflater.inflate(com.cmozie.javaweek4.R.layout.form, container, false);
		listview = (ListView) view.findViewById(com.cmozie.javaweek4.R.id.list);
		
		
		View listHeader = inflater.inflate(com.cmozie.javaweek4.R.layout.list_header, null);
		listview.addHeaderView(listHeader);
	
		
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				
					listener.rowSelect();
				
					
					}
			});
		 //final Spinner spinner = (Spinner) view.findViewById(com.cmozie.javaweek4.R.id.favList);
		 _pop = (Button) view.findViewById(com.cmozie.javaweek4.R.id.popularZipcodes);
		getRegion = (Button) view.findViewById(com.cmozie.javaweek4.R.id.getHistory);

					
				_pop.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						_pop.setVisibility(View.GONE);
						
						listener.onPopSelect();
						
					}
							

					
				});
				
				 getRegion.setOnClickListener(new OnClickListener() {
						//combine all the zipcodes and place call the query on all of them?
						
					
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							listener.onQueryAll();
						}
					
						
					});
//if no connection
				 

		
		return view;
	};
	
	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	
 if (savedInstanceState != null) {
             
           MainActivity.mylist = (ArrayList<HashMap<String, String>>) savedInstanceState.getSerializable("mylist");
             if (mylist != null) {
            	 
            	 listview = (ListView) view.findViewById(com.cmozie.javaweek4.R.id.list);
 				
                     adapter = new SimpleAdapter(_context, mylist, R.layout.list_row, new String[]{ "zipCode","areaCode","region"}, new int[]{R.id.row1, R.id.row2,R.id.row3});
                         
                         listview.setAdapter(adapter);
                 
                        
                         
                         
                         
                 }

         }
	}
		
	 
	
	/* (non-Javadoc)
	 * @see android.app.Fragment#onAttach(android.app.Activity)
	 */
	@Override
	public void onAttach(Activity activity){
		
		super.onAttach(activity);
		
		try {
			
			listener = (FormListener) activity;
			
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + "Must implement FormListener");
		}
	}
}
