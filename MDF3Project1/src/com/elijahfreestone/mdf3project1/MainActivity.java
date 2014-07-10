/*
 * @author	Elijah Freestone 
 *
 * Project	MDF3Project1
 * 
 * Package	com.elijahfreestone.mdf3project1
 * 
 * Date		Jul 7, 2014
 */

package com.elijahfreestone.mdf3project1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

// TODO: Auto-generated Javadoc
/**
 * The Class MainActivity.
 */
public class MainActivity extends Activity {
	static Context myContext;
	static String TAG = "MainActivity";

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        myContext = this;
        
		if (NetworkConnection.connectionStatus(myContext)) {
			Log.i(TAG, "Network Connection Exists");
		} else {
			// Show No Connection alert
			noConnectionAlert();
		}
    }
    
	/**
	 * No connection alert creates and displays an alert dialog if no Network
	 * Connection is available.
	 */
	public static void noConnectionAlert() {
		// Create alert dialog for no connection
		AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.myContext).create();
		alertDialog.setTitle(R.string.noConnectionTitle);
		// Set alert message. setMessage only has a charSequence
		// version so getString must be used.
		alertDialog.setMessage(myContext.getString(R.string.noConnectionAlert));
		alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", (DialogInterface.OnClickListener) null);
		alertDialog.show();
	}
}

