/*
 * @author	Elijah Freestone 
 *
 * Project	AppLauncher
 * 
 * Package	com.elijahfreestone.applauncher
 * 
 * Date		Jul 9, 2014
 */

package com.elijahfreestone.applauncher;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

// TODO: Auto-generated Javadoc
/** 
 * The Class MainActivity. 
 */ 
public class MainActivity extends Activity {
	String amountEnteredString;
	static String responseString = null;
	static String TAG = "Launch Activity";
	//final MyServiceHandler myServiceHandler = new MyServiceHandler(this);
	static Context myContext;  
 
    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    	myContext = this; 
        
        final EditText amountEditText = (EditText) findViewById(R.id.amountEntered);
        
        Button newConvertButton = (Button) findViewById(R.id.convertButton);
        newConvertButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				amountEnteredString = amountEditText.getText().toString();
				if (amountEnteredString.equalsIgnoreCase("") || amountEnteredString.equalsIgnoreCase("0")) {
					System.out.println("Nothing or zero entered");
					noValueAlert();
				} else if (amountEnteredString.length() >=1) {
					System.out.println("button clicked and " + amountEnteredString + " entered");
					startLaunchIntent();
				}
			 	
			}
		});
    } // onCreate close  
    
    /**
     * Start launch intent passes the entered value which is used to calculate currency.
     */
    public void startLaunchIntent() { 
    	Log.i("Launch Intent", "Launch Intent started");
 
    	// Create Intent to start activity
    	Intent startLaunchIntent = new Intent();
    	startLaunchIntent.setAction(Intent.ACTION_SEND);
    	startLaunchIntent.setType("text/plan");
    	startLaunchIntent.putExtra("amountEnteredString", amountEnteredString);

    	// Start the activity
    	startActivity(startLaunchIntent); 
    }
    
    /*
	 * noConnectionAlert creates and displays an alert dialog if no Network
	 * Connection is available.
	 */
	public static void noValueAlert() {
		// Create alert dialog for no connection
		AlertDialog alertDialog = new AlertDialog.Builder(myContext).create();
		alertDialog.setTitle(R.string.noValueTitle);
		// Set alert message. setMessage only has a charSequence
		// version so getString must be used.
		alertDialog.setMessage(myContext.getString(R.string.noValueAlert));
		alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", (DialogInterface.OnClickListener) null);
		alertDialog.show();
	}
    
}
