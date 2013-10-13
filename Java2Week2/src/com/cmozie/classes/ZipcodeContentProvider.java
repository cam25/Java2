/*
 * project 			Java2Week2
 * 
 * package			com.cmozie.classes
 * 
 * name				cameronmozie
 * 
 * date				Oct 10, 2013
 */
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
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

// TODO: Auto-generated Javadoc
/**
 * The Class ZipcodeContentProvider.
 */
public class ZipcodeContentProvider extends ContentProvider {

	public static final String AUTHORITY = "com.cmozie.classes.zipcodecontentprovider";
	
	/**
	 * The Class RegionData.
	 */
	public static class RegionData implements BaseColumns {
		
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/zipcodes/");
		
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.cmozie.classes.item";
		
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.cmozie.classes.item";
		
		//define colums
		
		public static final String ZIP_COLUMN = "zipCodes";
		public static final String AREACODE_COLUMN = "areaCode";
		public static final String REGION_COLUMN = "region";
		
		
		public static final String[] PROJECTION = {"_Id",ZIP_COLUMN,AREACODE_COLUMN,REGION_COLUMN};
		
		/**
		 * Instantiates a new region data.
		 */
		private RegionData() {};
		
	}
	
	public static final int ITEMS = 1;
	public static final int ITEMS_ID = 2;
	public static final int ITEMS_REGION = 3;
	public static final int MIAMI = 4;
	public JSONObject two;
	private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	static  {
		uriMatcher.addURI(AUTHORITY, "zipcodes/", ITEMS);
		uriMatcher.addURI(AUTHORITY, "zipcodes/#", ITEMS_ID);
		uriMatcher.addURI(AUTHORITY, "zipcodes/*", MIAMI);
		uriMatcher.addURI(AUTHORITY, "zipcodes/region/*", ITEMS_REGION);
	}
	
	/* (non-Javadoc)
	 * @see android.content.ContentProvider#delete(android.net.Uri, java.lang.String, java.lang.String[])
	 */
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see android.content.ContentProvider#getType(android.net.Uri)
	 */
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

	/* (non-Javadoc)
	 * @see android.content.ContentProvider#insert(android.net.Uri, android.content.ContentValues)
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see android.content.ContentProvider#onCreate()
	 */
	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see android.content.ContentProvider#query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String)
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		
		MatrixCursor result = new MatrixCursor (RegionData.PROJECTION);
		
		String JSONString = FileStuff.readStringFile(getContext(),"temp", false);
		//Log.i("Content STRING", JSONString);
		//Log.i("Content STRING", JSONString2);
		JSONObject json;
		
		
		
		JSONArray ja = null;
		
		
		try {
			json = new JSONObject(JSONString);
			
			 //Log.i("TEST", json2.toString());
			 ja = json.getJSONArray("zips");
			
			
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
			
			int index = 0;
			Log.i("all2", String.valueOf(index));
			try {
				index = Integer.parseInt(itemId);
				Log.i("index", itemId);
				
			} catch (Exception e) {
				
				Log.e("Query", "Index format error");
				
				break;
			}
			
			if (index < 0 || index > ja.length()) {
				Log.e("query", "index out of range for" + uri.toString());
				break;
			}
				try {
					
					JSONObject two = ja.getJSONObject(index);
					
						
					
						
					
					//JSONObject two = ja.getJSONObject(index);
					
					if (two.getString("zip_code").contentEquals("20001")) {
						Log.i("WORKS", "WORKS");
					}
					
					
						Log.i("all", String.valueOf(index));
							//Log.i("JSON ARRAY", two.toString(index));
					
							
						
						String _areaCode = two.getString("area_code");
						String _zipcode = two.getString("zip_code");
						String _region = two.getString("region");
						
						
						result.addRow(new Object[] {index,_zipcode,_areaCode, _region});
					
						
					
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
		case MIAMI:
			String itemId2 = uri.getLastPathSegment();
			
			Log.i("queryId", itemId2);
			
			int index2 = 0;
			Log.i("all2", String.valueOf(index2));
			try {
				index = Integer.parseInt(itemId2);
				Log.i("index", itemId2);
				
			} catch (Exception e) {
				
				Log.e("Query", "Index format error");
				
				break;
			}
			
			if (index < 0 || index > ja.length()) {
				Log.e("query", "index out of range for" + uri.toString());
				break;
			}
			try {
				if (two.getString("_zip_code").contentEquals("33133")) {
					
					Log.i("MIAMI", "WOrks");
					boolean zippy = two.getString("zip_code").equals("33133");
					
					String _areaCode = two.getString("area_code");
					String _zipcode = two.getString("zip_code");
					String _region = two.getString("region");
					
					
					result.addRow(new Object[] {index,zippy,_areaCode, _region});
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
			
			
			
		
			
		
				
			
		
		}
		return result;
		
	}
	

	/* (non-Javadoc)
	 * @see android.content.ContentProvider#update(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[])
	 */
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

}
