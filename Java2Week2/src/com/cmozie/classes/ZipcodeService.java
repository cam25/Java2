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

import java.net.MalformedURLException;
import java.net.URL;
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
	public static String searchAll;
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
		//String zips = extras.getString(enteredZipcode);
	
		 searchAll = "94105|33133|20001|10001|60602";

		
		//this is the base url of the api
				String baseURL = "http://zipfeeder.us/zip?";
				
				//key needed to use api
				String key = "key=EN4GbNMq";
				
				
				//creates finalURL as a URL
				URL UrlResult;
				//URL UrlResult2;
				String queryReply = null;
				queryReply2 = null;
				try{
					//sets the final url to the base plus the api key with the string parameter needed for search as well as the empty string that recieves a zipcode.
					UrlResult = new URL (baseURL + key + "&zips=" + searchAll);
				
					
					queryReply = WebStuff.getURLStringResponse(UrlResult);
					//storing of the file to local storage
					FileStuff.storeStringFile(this, "temp", queryReply, false);
					//FileStuff.storeStringFile(this, "temp2", queryReply2, false);
					Log.i("STORED FILE", "saved");
				}catch (MalformedURLException e){
					Log.e("BAD URL", "Malformed URL");
					UrlResult = null;
					//UrlResult2 = null;
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
