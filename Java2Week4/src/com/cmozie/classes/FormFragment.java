package com.cmozie.classes;


import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import webConnections.WebStuff;

import com.cmozie.Libz.FileStuff;
import com.cmozie.javaweek4.InfoActivity;

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
	
	public interface FormListener {
		public void onQueryAll();
		public void onPopSelect();
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		
		super.onCreateView(inflater, container, savedInstanceState);
		
		LinearLayout view = (LinearLayout) inflater.inflate(com.cmozie.javaweek4.R.layout.form, container, false);
		listview = (ListView) view.findViewById(R.id.list);
		
		
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
