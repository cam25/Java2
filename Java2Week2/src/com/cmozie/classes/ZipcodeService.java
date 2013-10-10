/*
 * project 			Java2Week1
 * 
 * package			com.cmozie.classes
 * 
 * name				cameronmozie
 * 
 * date				Oct 3, 2013
 */
package com.cmozie.classes;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import webConnections.WebStuff;

import com.cmozie.Libz.FileStuff;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

// TODO: Auto-generated Javadoc
/**
 * The Class ZipcodeService.
 */
public class ZipcodeService extends IntentService {
	public static final String MESSENGER_KEY = "messenger";
	public static final String enteredZipcode = "zipcode";
	public static  String queryReply2;

	/**
	 * Instantiates a new zipcode service.
	 */
	public ZipcodeService() {
		super("ZipcodeService");
		// TODO Auto-generated constructor stub
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}

	/* (non-Javadoc)
	 * @see android.app.IntentService#onHandleIntent(android.content.Intent)
	 */
	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		Log.i("ONHandleIntent", "Started");

		
		Bundle extras = intent.getExtras();
		Messenger messenger = (Messenger) extras.get(MESSENGER_KEY);
		String zips = extras.getString(enteredZipcode);
	
		String searchAll = "94105|94107|94108|94122|94103|94116|94110|33133|33132|33134|33127|33109|33129|33101|20001|20002|20003|20020|20018|20037|20032|10001|10002|10005|10004|10006|10007|10009|60018|60068|60067|60106|60131|60602|60603";

		
		//this is the base url of the api
				String baseURL = "http://zipfeeder.us/zip?";
				
				//key needed to use api
				String key = "key=EN4GbNMq";
				//this empty string accepts an empty string which will be for zipcodes entered
				String qs = "";
				//String searchAll = "33127|20001|20002|20003|10001|10002|10005|10004|60018|60068|60067|60106|60131|60602|60603";
				try{
				qs = URLEncoder.encode(zips, "UTF-8");
					
				}catch (Exception e) {
					
					//if an error in the api show the bad url alert
					
				
					Log.e("Bad URL","Encoding Problem");
					qs = "";
				}
				
				//creates finalURL as a URL
				URL UrlResult;
				URL UrlResult2;
				String queryReply = null;
				queryReply2 = null;
				try{
					//sets the final url to the base plus the api key with the string parameter needed for search as well as the empty string that recieves a zipcode.
					UrlResult = new URL (baseURL + key + "&zips=" + searchAll);
					UrlResult2 = new URL (baseURL + key + "&zips=" + searchAll);
					//logs the final url query
					Log.i("URL",UrlResult2.toString());
					
					
					queryReply = WebStuff.getURLStringResponse(UrlResult);
					queryReply2 = WebStuff.getURLStringResponse(UrlResult2);
					//storing of the file to local storage
					FileStuff.storeStringFile(this, "temp", queryReply, false);
					FileStuff.storeStringFile(this, "temp2", queryReply2, false);
					Log.i("STORED FILE", "saved");
				}catch (MalformedURLException e){
					Log.e("BAD URL", "Malformed URL");
					UrlResult = null;
					UrlResult2 = null;
				}
				Log.i("OnHandleIntent","Done looking up zipcode");
				
				//setting my message
		Message message = Message.obtain();
		message.arg1 = Activity.RESULT_OK;
		
		//This is where the problem from parsing json data was coming..Have to set the message.obj = to the queryReply url
		message.obj = queryReply;
		
		try {
			messenger.send(message);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			Log.e("On handleintent",e.getMessage().toString());
			e.printStackTrace();
		}
	}

}
