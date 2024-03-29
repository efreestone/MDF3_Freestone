/*
 * @author	Elijah Freestone 
 *
 * Project	MDF3Project1
 * 
 * Package	com.elijahfreestone.mdf3project1
 * 
 * Date		Jul 10, 2014
 */

package com.elijahfreestone.mdf3project1;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

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
	public static String numberEnteredString;
	static double numberEntered;

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

		JSONObject jsonObject = null;
		
		//Grab number entered from main activity
		numberEnteredString = MainActivity.numberEnteredString;
		if (numberEnteredString != null) {
			numberEntered = Double.parseDouble(numberEnteredString);
		} else {
			numberEntered = 0;
		}

		try {
			jsonObject = new JSONObject(JSONString);
			JSONObject jsonRatesObject = jsonObject.getJSONObject("rates");
			
			//Grab values for each Currency and multiply by amount entered in launcher.
			//Amount entered defaults to 1 if null
			usdValue = jsonRatesObject.optDouble("USD") * numberEntered; 
			eurValue = jsonRatesObject.optDouble("EUR") * numberEntered; 
			gbpValue = jsonRatesObject.optDouble("GBP") * numberEntered;  
			inrValue = jsonRatesObject.optDouble("INR") * numberEntered;
			cadValue = jsonRatesObject.optDouble("CAD") * numberEntered;
			audValue = jsonRatesObject.optDouble("AUD") * numberEntered;
			mxnValue = jsonRatesObject.optDouble("MXN") * numberEntered;
			cnyValue = jsonRatesObject.optDouble("CNY") * numberEntered;
			myrValue = jsonRatesObject.optDouble("MYR") * numberEntered;
			aedValue = jsonRatesObject.optDouble("AED") * numberEntered;
			
			//Create strings with values and Currency Code
			usdString = usdCode + " (Amount Entered) = " + usdValue;
			eurString = eurCode + " = " + eurValue;
			gbpString = gbpCode + " = " + gbpValue;
			inrString = inrCode + " = " + inrValue;
			cadString = cadCode + " = " + cadValue;
			audString = audCode + " = " + audValue;
			mxnString = mxnCode + " = " + mxnValue;
			cnyString = cnyCode + " = " + cnyValue;
			myrString = myrCode + " = " + myrValue;
			aedString = aedCode + " = " + aedValue;
			
			//Log.i("JSON File", gbpString);
			
			//Apply currency values to text views
			MainActivity.usdTV.setText(usdString);
			MainActivity.eurTV.setText(eurString);
			MainActivity.gbpTV.setText(gbpString);
			MainActivity.inrTV.setText(inrString);
			MainActivity.cadTV.setText(cadString);
			MainActivity.audTV.setText(audString);
			MainActivity.mxnTV.setText(mxnString);
			MainActivity.cnyTV.setText(cnyString);
			MainActivity.myrTV.setText(myrString);
			MainActivity.aedTV.setText(aedString);

		} catch (JSONException e) {
			Log.e("displayDataFromFile ERROR", e.getMessage().toString());
		}
	} // displayDataFromFile Close
}
