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
import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

// TODO: Auto-generated Javadoc
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
  
	}                            

}
