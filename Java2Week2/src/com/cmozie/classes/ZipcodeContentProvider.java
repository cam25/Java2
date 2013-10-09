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
		Log.i("Content STRING", JSONString);
		JSONObject json;
		JSONArray ja = null;
		
		try {
			json = new JSONObject(JSONString);
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
			
			
		for (int i = 0; i < ja.length(); i++) {
			
			try {
				
				
				JSONObject one = ja.getJSONObject(i);
				JSONObject two = ja.getJSONObject(0);
				String _areaCode = one.getString("area_code");
				String _zipcode = one.getString("zip_code");
				String _region = one.getString("region");
				
				
				if (MainActivity.getRegion.isPressed()) {
					String _zipcode2 = two.getString("zip_code");
					String _area_code2 = two.getString("area_code");
					String _region2 = one.getString("region");
					
					/*String[] zips = {"22314", "22312", "11221"};
					StringBuilder sb = new StringBuilder("http://zipfeeder.us/zip?key=EN4GbNMq&zips=");

					for (int s = 0; i < zips.length; i++)
					sb.append(zips[s] + "|");

					//remove the last pipe (|) from the last zip code.
					sb.deleteCharAt(sb.length() - 1);

					Log.i("string", sb.toString());*/
					
					result.addRow(new Object[] {i + 1, _zipcode2, _area_code2,_region2});
				}
					
				
				
				
				
	
				//i was passing too many values in my object which was causing crash here.
				result.addRow(new Object[] {i + 1, _zipcode, _areaCode,_region});
			
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}	
			break;
		case ITEMS_ID:
			String itemId = uri.getLastPathSegment();
			Log.i("queryId", itemId);
			
			int index;
			
			try {
				index = Integer.parseInt(itemId);
				Log.i("index", itemId);
				
			} catch (Exception e) {
				
				Log.e("Query", "Index format error");
				
				break;
			}
			if (index <= 0 || index > ja.length()) {
				
				Log.e("query", "index out of range for " + uri.toString());
				break;
			}
			
			
			try {
				JSONObject one = ja.getJSONObject(index - 1);
				JSONObject two = ja.getJSONObject(0);
				String _areaCode = two.getString("area_code");
				String _zipcode = two.getString("zip_code");
				String _region = two.getString("region");
			
				
				//i was passing too many values in my object which was causing crash here.
				result.addRow(new Object[] {index, _areaCode, _zipcode,_region});
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			break;
			default:
				Log.e("query", "Invalid uri = " + uri.toString());
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
