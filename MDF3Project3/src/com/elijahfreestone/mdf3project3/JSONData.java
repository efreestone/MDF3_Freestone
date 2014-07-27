/*
 * @author	Elijah Freestone 
 *
 * Project	MDF3Project3
 * 
 * Package	com.elijahfreestone.mdf3project3
 * 
 * Date		Jul 22, 2014
 */

package com.elijahfreestone.mdf3project3;

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
	static String filePathDir = "data/data/com.elijahfreestone.java2project3/files/string_from_url.txt";

	static String JSONString;
	static ArrayList<HashMap<String, String>> myList;

	/*
	 * Display data from file pulls string from locally stored file and creates
	 * ArrayList.
	 */
	public static void displayDataFromFile() {
		// Grab instance of DataManager
		myDataManager = DataManager.getInstance();

		myContext = MainActivity.myContext;

		String dvdTitle, releaseDate, movieRating, criticRating, audienceRating;

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
				// Log.i("displayData newReleaseArray", "Rating: " +
				// criticRating);
				audienceRating = newReleaseArray.getJSONObject(i)
						.getJSONObject("ratings").getString("audience_rating");

				// Instantiate Hash Map for array and pass in strings with
				// key/value pairs
				HashMap<String, String> displayMap = new HashMap<String, String>();
				displayMap.put("dvdTitle", dvdTitle);
				displayMap.put("releaseDate", releaseDate);
				displayMap.put("movieRating", movieRating);
				displayMap.put("criticRating", criticRating);
				displayMap.put("audienceRating", audienceRating);

				// Add hash maps to array list
				myList.add(displayMap);
			}

			// Create simple adapter and set up with array
			SimpleAdapter listAdapter = new SimpleAdapter(myContext, myList,
					R.layout.listview_row, new String[] { "dvdTitle",
							"releaseDate", "movieRating" }, new int[] {
							R.id.dvdTitle, R.id.releaseDate, R.id.movieRating });

			MainActivityFragment.myListView.setAdapter(listAdapter);
			
			sendArrayListToWidget();

		} catch (JSONException e) {
			Log.e("displayDataFromFile ERROR", e.getMessage().toString());
		}
	} // displayDataFromFile Close
	
	public static void sendArrayListToWidget() {
		MyAppWidgetProvider.widgetMovieList = myList; 
	}

}
