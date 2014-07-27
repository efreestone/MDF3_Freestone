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
	
	/** The my context. */
	static Context myContext;
	
	/** The preferences. */
	static SharedPreferences preferences;
	
	/** The passed background color. */
	static String passedBackgroundColor;
	
	/** The passed text color. */
	static String passedTextColor;
	
	/** The pref name. */
	static String PREF_NAME = "com.elijahfreestone.mdf3project3";
	
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
			
			MyAppWidgetProvider.backgroundColor = passedBackgroundColor;
			MyAppWidgetProvider.textColor = passedTextColor;
		}
	}

}
