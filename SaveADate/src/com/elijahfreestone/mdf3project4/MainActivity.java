/*
 * @author	Elijah Freestone 
 *
 * Project	MDF3Project4
 * 
 * Package	com.elijahfreestone.mdf3project4
 * 
 * Date		Jul 28, 2014
 */

package com.elijahfreestone.mdf3project4;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * MainActivity handles the webview and Interface for JavaScript
 */
@SuppressLint("SetJavaScriptEnabled") 
public class MainActivity extends Activity {
	WebView myWebView; 

	/* (non-Javadoc)  
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override   
	protected void onCreate(Bundle savedInstanceState) {
		String backgroundColor = "#7ED0E0";
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); 
		
		//Grab webview and load test url
		myWebView = (WebView) findViewById(R.id.myWebView);
		myWebView.loadUrl("file:///android_asset/index.html");
		myWebView.setBackgroundColor(Color.parseColor(backgroundColor));
		
		//Grab web settings and enable javascript
		WebSettings webSettings = myWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);  
		
		myWebView.addJavascriptInterface(new EventAppInterface(this), "Android");
  
	} //onCreate close                   
	
	/*
	 * EventAppInterface is the interface for JavaScript to communicate with the
	 * Native Code. It creates an Intent with the data input from the webview
	 * and sends it along to the handling application chosen by the user
	 */
	public class EventAppInterface {   
		Context myContext;         
		
		/*
		 * Instantiates a new event app interface.
		 *
		 * @param context the context
		 */
		EventAppInterface(Context context) { 
			myContext = context; 
			Log.i("JavaScript", "Interface Instantiated"); 
		}            
		
		/*
		 * Save event creates an Intent with variables input in webview.
		 *
		 * @param eventTitle the event title
		 * @param eventDate the event date
		 * @param eventType the event type
		 * @param attendValue the attend value
		 * @param eventDetails the event details
		 */
		@JavascriptInterface
		public void saveEvent(String eventTitle, String eventDate,
				String eventType, String attendValue, String eventDetails) {
			Log.i("JavaScript", "saveEvent called");

			// Create Intent
			Intent emailIntent = new Intent(Intent.ACTION_SEND);
			emailIntent.setType("message/rfc822");
			emailIntent.putExtra(Intent.EXTRA_EMAIL,
					new String[] { "efreestone@gmail.com" });
			emailIntent.putExtra(Intent.EXTRA_SUBJECT, eventTitle);
			emailIntent.putExtra(Intent.EXTRA_TEXT, eventTitle + "\n"
					+ eventDate + "\n" + eventType + "\n" + attendValue + "\n"
					+ eventDetails);

			startActivity(emailIntent); 
		}                                                  
	} //EventAppInterface close    

} 
