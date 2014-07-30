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

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends Activity {
	WebView myWebView;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Grab webview and load test url
		myWebView = (WebView) findViewById(R.id.myWebView);
		myWebView.loadUrl("https://www.google.com/"); 

	}   

}
