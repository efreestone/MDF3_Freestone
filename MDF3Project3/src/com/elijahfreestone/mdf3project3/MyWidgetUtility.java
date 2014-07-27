/*
 * @author	Elijah Freestone 
 *
 * Project	MDF3Project3
 * 
 * Package	com.elijahfreestone.mdf3project3
 * 
 * Date		Jul 26, 2014
 */

package com.elijahfreestone.mdf3project3;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

// TODO: Auto-generated Javadoc
public class MyWidgetUtility {
	static Context myContext;
	static SharedPreferences preferences;
	static String passedBackgroundColor;
	static String passedTextColor;
	
	/**
	 * Instantiates a new my widget utility.
	 *
	 * @param context the context
	 */
	public MyWidgetUtility(Context context) {
		myContext = context;
	}
	
	/**
	 * Save preferences.
	 *
	 * @param backgroundColor the background color
	 * @param textColor the text color
	 */
	public static void savePreferences(String backgroundColor, String textColor) {
		preferences = MainActivity.preferences;
		//preferences = myContext.getSharedPreferences(PREF_NAME, 0);
		Editor editor = preferences.edit();
		passedBackgroundColor = backgroundColor;
		passedTextColor = textColor;
		
		editor.putString("backgroundColor", passedBackgroundColor);
		editor.putString("textColor", passedTextColor);
		editor.apply(); 
	}
	
	/**
	 * Gets the preferences.
	 *
	 * @return the preferences
	 */
	public static void getPreferences() {
		preferences = MainActivity.preferences;
		if (preferences != null) {
			passedBackgroundColor = preferences.getString("backgroundColor", "");
			passedTextColor = preferences.getString("textColor", "");
			Log.i("Pref", "Background Color = " + passedBackgroundColor);
			
			//Set vars on widget provider to set widget colors.
			MyAppWidgetProvider.backgroundColor = passedBackgroundColor;
			MyAppWidgetProvider.textColor = passedTextColor;
		}
	}

}
