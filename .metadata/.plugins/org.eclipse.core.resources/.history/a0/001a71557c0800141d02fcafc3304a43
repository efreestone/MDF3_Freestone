package com.elijahfreestone.mdf3project1;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.widget.SimpleAdapter;

// TODO: Auto-generated Javadoc
/**
 * The Class JSONData creates/displays JSON Data from file.
 */
public class JSONData {
	static Context myContext;
	static DataManager myDataManager;
	static String myFileName = "string_from_url.txt";
	static String filePathDir = "data/data/com.elijahfreestone.mdf3project1/files/string_from_url.txt";

	static String JSONString;
	static ArrayList<HashMap<String, String>> myList;
	public static String userRatingString;

	/*
	 * Display data from file pulls string from locally stored file and creates
	 * ArrayList.
	 */
	public static void displayDataFromFile() {
		// Grab instance of DataManager
		myDataManager = DataManager.getInstance();

		myContext = MainActivity.myContext;

		String dvdTitle, releaseDate, movieRating, criticRating;

		Log.i("File", "Display Data Called");

		JSONString = DataManager.readStringFromFile(myContext, myFileName);

		Log.i("JSONString", JSONString);

		// Create ArrayList with hashmap
		myList = new ArrayList<HashMap<String, String>>();
		JSONObject jsonObject = null;

		try {
			jsonObject = new JSONObject(JSONString);
			JSONObject jsonFirstRatesObject = jsonObject.getJSONObject("rates");
			//JSONObject jsonSecondRatesObject = jsonFirstRatesObject.getJSONObject("rates");
			//int arraySize = newReleaseArray.length();
			String newJSONString = jsonObject.toString();
			Log.i("JSON File", "Rates: " + newJSONString); 
			
			double gbpValue = jsonFirstRatesObject.optDouble("GBP");  
			
			String gbpString = "GBP = " + gbpValue;
			Log.i("JSON File", "GBP = " + gbpValue);
			
			MainActivity.testTextView.setText(gbpString);

			// Create simple adapter and set up with array
			SimpleAdapter listAdapter = new SimpleAdapter(myContext, myList,
					R.layout.listview_row, new String[] { "dvdTitle",
							"releaseDate", "movieRating" }, new int[] {
							R.id.currencyName, R.id.currencyValue });

			MainActivity.myListView.setAdapter(listAdapter);

		} catch (JSONException e) {
			Log.e("displayDataFromFile ERROR", e.getMessage().toString());
		}
	} // displayDataFromFile Close
}
