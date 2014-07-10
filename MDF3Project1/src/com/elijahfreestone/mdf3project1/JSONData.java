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

		double usdValue, eurValue, gbpValue, inrValue, cadValue, audValue, mxnValue, cnyValue, myrValue, aedValue;
		String usdString, eurString, gbpString, inrString, cadString, audString, mxnString, cnyString, myrString, aedString;
		String usdCode = "USD", eurCode = "EUR", gbpCode = "GBP", inrCode = "INR", cadCode = "CAD", 
				audCode = "AUD", mxnCode = "MXN", cnyCode = "CNY", myrCode = "MYR", aedCode = "AED";

		Log.i("File", "Display Data Called");

		JSONString = DataManager.readStringFromFile(myContext, myFileName);

		Log.i("JSONString", JSONString);

		// Create ArrayList with hashmap
		myList = new ArrayList<HashMap<String, String>>();
		JSONObject jsonObject = null;

		try {
			jsonObject = new JSONObject(JSONString);
			JSONObject jsonRatesObject = jsonObject.getJSONObject("rates");
			
			//Grab values for each Currency
			usdValue = jsonRatesObject.optDouble("USD"); 
			eurValue = jsonRatesObject.optDouble("EUR"); 
			gbpValue = jsonRatesObject.optDouble("GBP");  
			inrValue = jsonRatesObject.optDouble("INR");
			cadValue = jsonRatesObject.optDouble("CAD");
			audValue = jsonRatesObject.optDouble("AUD");
			mxnValue = jsonRatesObject.optDouble("MXN");
			cnyValue = jsonRatesObject.optDouble("CNY");
			myrValue = jsonRatesObject.optDouble("MYR");
			aedValue = jsonRatesObject.optDouble("AED");
			
			//Create strings with values and Currency Code
			usdString = usdCode + " = " + usdValue;
			eurString = eurCode + " = " + eurValue;
			gbpString = gbpCode + " = " + gbpValue;
			inrString = inrCode + " = " + inrValue;
			cadString = cadCode + " = " + cadValue;
			audString = audCode + " = " + audValue;
			mxnString = mxnCode + " = " + mxnValue;
			cnyString = cnyCode + " = " + cnyValue;
			myrString = myrCode + " = " + myrValue;
			aedString = aedCode + " = " + aedValue;
			
			Log.i("JSON File", gbpString);
			
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
