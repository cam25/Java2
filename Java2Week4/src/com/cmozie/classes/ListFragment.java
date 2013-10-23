package com.cmozie.classes;

import java.util.ArrayList;
import java.util.HashMap;

import com.cmozie.javaweek4.InfoActivity;
import com.cmozie.javaweek4.MainActivity;
import com.cmozie.javaweek4.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ListFragment extends Fragment {
	Context _context;
	private ListListener listener;
	public static String  zipp;
	
   // public static SimpleAdapter adapter;
    public ArrayList<HashMap<String, String>> mylist;
    public static ListView listview;
	public interface ListListener {
	
		public void gpsShow(String zipcode);
		public void getData();
	};

	 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		
		LinearLayout view = (LinearLayout) inflater.inflate(R.layout.form2, container, false);
		
		 listview = (ListView) view.findViewById(R.id.list2);
		
		
		 
		View listHeader = inflater.inflate(R.layout.list_header2, container, false);
		listview.addHeaderView(listHeader);
		
	mylist = MainActivity.mylist;
	
	
	Log.i("list", mylist.toString());
		
		
			listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Log.i("Row","Selected ="+ arg2 + "clicked");

				//array adapter for listview cells
					if (arg2 == 1 ) {
						
						//get gps
						listener.gpsShow(zipp);
						
						
					}
			}
		});
	

	
		return view;
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	
		
	}
	
	
	public void showData(){
		//SimpleAdapter adapter = new SimpleAdapter(getActivity(), mylist, R.layout.list_row2, new String[]{ "zipp","area","reg"}, new int[]{R.id.row1_2, R.id.row2_2,R.id.row3_2});
		//listview.setAdapter(adapter);
	
	}
	
	@Override
	public void onAttach(Activity activity)
	
	{
		super.onAttach(activity);
		try {
			listener = (ListListener) activity;
		} catch (ClassCastException e) {
			// TODO: handle exception
			throw new ClassCastException(activity.toString() + "Must Implement ListListener");
		}
	}
	
	
}
