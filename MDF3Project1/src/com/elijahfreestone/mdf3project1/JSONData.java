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
	static String userRatingFile = "user_rating.txt";
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
		JSONArray newReleaseArray = null;

		try {
			jsonObject = new JSONObject(JSONString);
			newReleaseArray = jsonObject.getJSONArray("movies");
			int arraySize = newReleaseArray.length();

			// Loop through array from file and extract fields
			for (int i = 0; i < arraySize; i++) {

				dvdTitle = newReleaseArray.getJSONObject(i).getString("title");
				// Log.i("displayData newReleaseArray", "Title: " + dvdTitle);
				releaseDate = newReleaseArray.getJSONObject(i)
						.getJSONObject("release_dates").getString("dvd");
				// Log.i("displayData newReleaseArray", "Release Date: " +
				// releaseDate);
				movieRating = newReleaseArray.getJSONObject(i).getString(
						"mpaa_rating");
				criticRating = newReleaseArray.getJSONObject(i)
						.getJSONObject("ratings").getString("critics_rating");


				// Instantiate Hash Map for array and pass in strings with
				// key/value pairs
				HashMap<String, String> displayMap = new HashMap<String, String>();
				displayMap.put("dvdTitle", dvdTitle);
				displayMap.put("releaseDate", releaseDate);
				displayMap.put("movieRating", movieRating);
				displayMap.put("criticRating", criticRating);

				// Add hash maps to array list
				myList.add(displayMap);
			}

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
