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

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
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
	final MyServiceHandler myServiceHandler = new MyServiceHandler(this);
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
				if (amountEnteredString == null || amountEnteredString.equalsIgnoreCase("0")) {
					System.out.println("Nothing or zero entered");
				} else if (amountEnteredString.length() >=1) {
					System.out.println("button clicked and " + amountEnteredString + " entered");
					startLaunchIntent();
				}
				
			}
		});
    } // onCreate close  
    
	/*
	 * The Class MyServiceHandler creates a Service Handler with a Weak Ref to
	 * the MainActivty. This is done to avoid the memory leak
	 */
	private static class MyServiceHandler extends Handler {
		private final WeakReference<MainActivity> myActivity;

		// Instantiate the handler
		public MyServiceHandler(MainActivity activity) {
			myActivity = new WeakReference<MainActivity>(activity);
		}  
 
		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(Message message) {

			MainActivity activity = myActivity.get();
			if (activity != null) {
				Object returnObject = message.obj;
				if (message.arg1 == RESULT_OK && returnObject != null) {
					try {
						responseString = (String) message.obj;
						Log.i(TAG, "handleMessage " + responseString);
					} catch (Exception e) {
						Log.e("handleMessage", e.getMessage().toString());
					}
				} else {
					Log.i(TAG, "Data NOT created!!");
				}
			}

		} // HandleMessage Close
	} // MyHandler Close 
    
    public void startLaunchIntent() { 
    	Log.i("Launch Intent", "Launch Intent started");
    	
    	// Create Messenger class for ref to handler
    	Messenger dataMessenger = new Messenger(myServiceHandler);

    	// Create Intent to start activity
    	Intent startLaunchIntent = new Intent();
    	startLaunchIntent.setAction(Intent.ACTION_SEND);
    	startLaunchIntent.setType("text/plan");
    	startLaunchIntent.putExtra("amountEnteredString", amountEnteredString);

    	// Start the activity
    	startActivity(startLaunchIntent); 
    }
    
}
