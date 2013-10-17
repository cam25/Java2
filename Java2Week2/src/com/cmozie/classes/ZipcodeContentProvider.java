/*
 * project 			Java2Week3
 * 
 * package			com.cmozie.classes
 * 
 * name				cameronmozie
 * 
 * date				Oct 17, 2013
 */

package com.cmozie.classes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cmozie.Libz.FileStuff;
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

	
	/**
	 * The Enum City.
	 */
	public enum City {
		NY,
		WA,
		MI,
		CG,
		SF
		
		
	}
	
	City searchCity;
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
	
	public JSONObject one;
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
				one = ja.getJSONObject(i);
				
				//if (one.getString("zip_code").contentEquals("20001")||one.getString("zip_code").contentEquals("94105")||one.getString("zip_code").contentEquals("33133")||one.getString("zip_code").contentEquals("10001")||one.getString("zip_code").contentEquals("60018")) {
					
				if 	(one.getString("zip_code").contentEquals("20001")|| 
						one.getString("zip_code").contentEquals("10001")||
						one.getString("zip_code").contentEquals("33133")|| 
						one.getString("zip_code").contentEquals("60602")|| 
						one.getString("zip_code").contentEquals("94105")|| 
						one.getString("zip_code").contentEquals("20002")||
						one.getString("zip_code").contentEquals("94107")||
						one.getString("zip_code").contentEquals("94108")||
						one.getString("zip_code").contentEquals("94110")||
						one.getString("zip_code").contentEquals("33132")||
						one.getString("zip_code").contentEquals("33134")||
						one.getString("zip_code").contentEquals("33127")||
						one.getString("zip_code").contentEquals("20001")||
						one.getString("zip_code").contentEquals("20002")||
						one.getString("zip_code").contentEquals("20018")||
						one.getString("zip_code").contentEquals("20032")||
						one.getString("zip_code").contentEquals("10002")||
						one.getString("zip_code").contentEquals("10005")||
						one.getString("zip_code").contentEquals("60018")||
						one.getString("zip_code").contentEquals("60068")||
						one.getString("zip_code").contentEquals("60067")||
						one.getString("zip_code").contentEquals("60106")||
						one.getString("zip_code").contentEquals("60131")||
						one.getString("zip_code").contentEquals("60602")||
						one.getString("zip_code").contentEquals("60603")) {

				
				String _areaCode = one.getString("area_code");
				String _zipcode = one.getString("zip_code");
				String _region = one.getString("region");
				
				
			
			result.addRow(new Object[] {i + 1, _zipcode, _areaCode,_region});
				}
		
			
		
			
				//}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}	
		
		
	
			break;
			
		case ITEMS_ID:
			String itemId = uri.getLastPathSegment();
			
		//	Log.i("CP","City enum value for NY=" + City.NY.name());
			
			Log.i("queryId", itemId);
			
			
			
			int index = 0;
			
			index = Integer.parseInt(itemId);
			Log.i("index", itemId);
			//Log.i("all2", String.valueOf(index));
			try {
				
				
			} catch (Exception e) {
				
				Log.e("Query", "Index format error");
			
				
				break;
			}
			
			if (index < 0 || index > ja.length()) {
				Log.e("query", "index out of range for" + uri.toString());
				break;
			}
			
			
				
					
			Log.d("CP", "index="+index);
			switch(index) {
			case 0: //NY
				try {
					for (int i = 0; i < ja.length(); i++) {

						JSONObject two = ja.getJSONObject(i);

						if (two.get("city").equals("New York")) {
							Log.i("CP", "NY");

							String _zipcode = two.getString("zip_code");
							String _areaCode = two.getString("area_code");
							String _region = two.getString("region");
							result.addRow(new Object[] {index,_zipcode,_areaCode, _region});
						} else {
							//Log.i("CP", "No Match in JSON file");
						}
					}

				} catch (JSONException e) {
					Log.e("CP", "JSON Exception, filtered request");
					e.printStackTrace();
				}

				break;
				
			case 1: //DC
				try {
					for (int i = 0; i < ja.length(); i++) {

						JSONObject two = ja.getJSONObject(i);

						//Log.d("CP", "two.get(city)="+two.get("city"));
						if (two.get("city").equals("Washington")) {
							//Log.i("CP", "DC");
						
						String _zipcode = two.getString("zip_code");
						String _areaCode = two.getString("area_code");
						String _region = two.getString("region");
						result.addRow(new Object[] {index,_zipcode,_areaCode, _region});
						} else {
						//	Log.i("CP", "No Match in JSON file");
						}
					}

				} catch (JSONException e) {
					Log.e("CP", "JSON Exception, filtered request");
					e.printStackTrace();
				}


				break;
			case 2: //FL
				try {
					for (int i = 0; i < ja.length(); i++) {

						JSONObject two = ja.getJSONObject(i);

						//Log.d("CP", "two.get(city)="+two.get("city"));
						if (two.get("city").equals("Miami")) {
							//Log.i("CP", "DC");
						
						String _zipcode = two.getString("zip_code");
						String _areaCode = two.getString("area_code");
						String _region = two.getString("region");
						result.addRow(new Object[] {index,_zipcode,_areaCode, _region});
						} else {
						//	Log.i("CP", "No Match in JSON file");
						}
					}

				} catch (JSONException e) {
					Log.e("CP", "JSON Exception, filtered request");
					e.printStackTrace();
				}
				break;
			case 3: //IL
				try {
					for (int i = 0; i < ja.length(); i++) {

						JSONObject two = ja.getJSONObject(i);

						//Log.d("CP", "two.get(city)="+two.get("city"));
						if (two.get("state").equals("IL")) {
							//Log.i("CP", "DC");
						
						String _zipcode = two.getString("zip_code");
						String _areaCode = two.getString("area_code");
						String _region = two.getString("region");
						result.addRow(new Object[] {index,_zipcode,_areaCode, _region});
						} else {
						//	Log.i("CP", "No Match in JSON file");
						}
					}

				} catch (JSONException e) {
					Log.e("CP", "JSON Exception, filtered request");
					e.printStackTrace();
				}
				break;
			case 4: //CA
				try {
					for (int i = 0; i < ja.length(); i++) {

						JSONObject two = ja.getJSONObject(i);

						//Log.d("CP", "two.get(city)="+two.get("city"));
						if (two.get("state").equals("CA")) {
							//Log.i("CP", "DC");
						
						String _zipcode = two.getString("zip_code");
						String _areaCode = two.getString("area_code");
						String _region = two.getString("region");
						result.addRow(new Object[] {index,_zipcode,_areaCode, _region});
						} else {
						//	Log.i("CP", "No Match in JSON file");
						}
					}

				} catch (JSONException e) {
					Log.e("CP", "JSON Exception, filtered request");
					e.printStackTrace();
				}
				break;
			default:
				Log.d("CP", "default");
				break;
			}
				
				break;
		/*case MIAMI:
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
				
					if (two.getString("city").contentEquals("Miami")) {
						
					
					Log.i("MIAMI", "WOrks");
					//boolean zippy = two.getString("zip_code").equals("33133");
					
					String _areaCode = two.getString("area_code");
					String _zipcode = two.getString("zip_code");
					String _region = two.getString("region");
					
					
					result.addRow(new Object[] {index,_zipcode,_areaCode, _region});
					}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;*/
			
			
			
		
			
		
				
			
		
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
