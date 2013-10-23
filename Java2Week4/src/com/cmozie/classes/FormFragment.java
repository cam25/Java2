package com.cmozie.classes;


import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import webConnections.WebStuff;

import com.cmozie.Libz.FileStuff;
import com.cmozie.javaweek4.InfoActivity;
import com.cmozie.javaweek4.MainActivity;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
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
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;


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
	//bool
	Boolean _connected = false;
	public static SimpleAdapter adapter;
	
	
	public interface FormListener {
		public void onQueryAll();
		public void onPopSelect();
		public void display(Cursor cursor);
		public void rowSelect();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		
		super.onCreateView(inflater, container, savedInstanceState);
		
		LinearLayout view = (LinearLayout) inflater.inflate(com.cmozie.javaweek4.R.layout.form, container, false);
		listview = (ListView) view.findViewById(com.cmozie.javaweek4.R.id.list);
		
		
		View listHeader = inflater.inflate(com.cmozie.javaweek4.R.layout.list_header, null);
		listview.addHeaderView(listHeader);
		//adapter = new SimpleAdapter(getActivity(), mylist,com.cmozie.javaweek4.R.layout.list_row, new String[]{ "zipCode","areaCode","region","county"}, new int[]{com.cmozie.javaweek4.R.id.row1, com.cmozie.javaweek4.R.id.row2,com.cmozie.javaweek4.R.id.row3});
		
		
       //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), com.cmozie.javaweek4.R.layout.list_row) ;
     
        //listview.setAdapter(adapter);
        
      
		
		
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
