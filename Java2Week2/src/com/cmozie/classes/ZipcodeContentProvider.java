package com.cmozie.classes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cmozie.Libz.FileStuff;
import com.cmozie.java2week2.MainActivity;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.hardware.Camera.Area;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

public class ZipcodeContentProvider extends ContentProvider {

	public static final String AUTHORITY = "com.cmozie.classes.zipcodecontentprovider";
	
	public static class RegionData implements BaseColumns {
		
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/zipcodes/");
		
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.cmozie.classes.item";
		
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.cmozie.classes.item";
		
		//define colums
		
		public static final String ZIP_COLUMN = "zipCodes";
		public static final String AREACODE_COLUMN = "areaCode";
		public static final String REGION_COLUMN = "region";
		
		
		public static final String[] PROJECTION = {"_Id",ZIP_COLUMN,AREACODE_COLUMN,REGION_COLUMN};
		
		private RegionData() {};
		
	}
	
	public static final int ITEMS = 1;
	public static final int ITEMS_ID = 2;
	public static final int ITEMS_REGION = 3;
	
	private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	private static final int MIAMI = 0;
	
	static  {
		uriMatcher.addURI(AUTHORITY, "zipcodes/", ITEMS);
		uriMatcher.addURI(AUTHORITY, "zipcodes/#", ITEMS_ID);
		uriMatcher.addURI(AUTHORITY, "zipcodes/region/*", ITEMS_REGION);
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		String ret = getContext().getContentResolver().getType(RegionData.CONTENT_URI);
		
		Log.d("type", "Get something" + ret);
		
		String type = null;
		
		switch (uriMatcher.match(uri)) {
		case ITEMS:
			type = RegionData.CONTENT_TYPE;
			
			Log.i("TYPE", type);
			break;
			
		case ITEMS_ID:
			type = RegionData.CONTENT_ITEM_TYPE;
			
			default:
				break;
		}
		return type;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		
		MatrixCursor result = new MatrixCursor (RegionData.PROJECTION);
		
		String JSONString = FileStuff.readStringFile(getContext(),"temp", false);
		String JSONString2 = FileStuff.readStringFile(getContext(),"temp2", false);
		//Log.i("Content STRING", JSONString);
		Log.i("Content STRING", JSONString2);
		JSONObject json;
		
		
		
		JSONArray ja = null;
		
		
		try {
			json = new JSONObject(JSONString);
			
			 //Log.i("TEST", json2.toString());
			 ja = json.getJSONArray("zips");
			Log.i("JSON", ja.toString());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (ja == null) {
			return result;
		}
		
		
		
		switch (uriMatcher.match(uri)) {
		case ITEMS:
			
			String placeRequested = uri.getLastPathSegment();
			
			Log.i("place",placeRequested);
		for (int i = 0; i < ja.length(); i++) {
			
			
			
			try {
				JSONObject one = ja.getJSONObject(i);
				
				//if (one.getString("zip_code").contentEquals("20001")||one.getString("zip_code").contentEquals("94105")||one.getString("zip_code").contentEquals("33133")||one.getString("zip_code").contentEquals("10001")||one.getString("zip_code").contentEquals("60018")) {
					
			
				
				String _areaCode = one.getString("area_code");
				String _zipcode = one.getString("zip_code");
				String _region = one.getString("region");
				
				
				Log.i("all", String.valueOf(i));
			result.addRow(new Object[] {i + 1, _zipcode, _areaCode,_region});
			
		
			
		
			
				//}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}	
		
		
	
			break;
			
		case ITEMS_ID:
			String itemId = uri.getLastPathSegment();
			
			Log.i("queryId", itemId);
			
			int index = 1;
			//Log.i("all2", String.valueOf(index));
			try {
				index = Integer.parseInt(itemId);
				Log.i("index", itemId);
				
			} catch (Exception e) {
				
				Log.e("Query", "Index format error");
				
				break;
			}
			
			if (index <= 0 || index > ja.length()) {
				Log.e("query", "index out of range for" + uri.toString());
				break;
			}
				try {
					
					JSONObject two = ja.getJSONObject(index - 1);
					
					//if (two.getString("zip_code").contentEquals("10001")|| two.getString("zip_code").contentEquals("33127")) {
						
							
					
							
						
						String _areaCode = two.getString("area_code");
						String _zipcode = two.getString("zip_code");
						String _region = two.getString("region");
						
						
						result.addRow(new Object[] {index,_zipcode,_areaCode, _region});
					
						
					
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Log.e("query", "index out of range for " + uri.toString());
				break;
			
			
			
				
			
		
		}
		return result;
		
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

}
