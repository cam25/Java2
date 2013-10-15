package com.cmozie.java2week2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class InfoActivity extends Activity{
	
	public static Context _context;
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.form2);
		ListView listview = (ListView) this.findViewById(R.id.list2);
	
		
		View listHeader = this.getLayoutInflater().inflate(R.layout.list_header2, null);
		listview.addHeaderView(listHeader);
		
		_context = this;
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}





