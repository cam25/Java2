package com.cmozie.java2week2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class InfoActivity extends Activity{
	
	public static Context _context;
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.form2);
		ListView listview = (ListView) this.findViewById(R.id.list);
	
		
		View listHeader = this.getLayoutInflater().inflate(R.layout.list_header, null);
		listview.addHeaderView(listHeader);
		
		_context = this;
		
	}

}
