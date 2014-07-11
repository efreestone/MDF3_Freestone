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

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

// TODO: Auto-generated Javadoc
/**
 * The Class NetworkConnection handles network connection testing before a call
 * to the API can be attempted.
 */
public class NetworkConnection {
	
	/** The response string. */
	static String responseString;

	/**
	 * Connection status.
	 * 
	 * @param myContext
	 *            the my context
	 * @return the boolean
	 */
	public static Boolean connectionStatus(Context myContext) {
		Boolean connectionBool = false;

		ConnectivityManager connectionManager = (ConnectivityManager) myContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();

		if (networkInfo != null) {
			if (networkInfo.isConnected()) {
				connectionBool = true;
			}
		}
		return connectionBool;
	} // connectionStatus Close
}
