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

import java.io.File;
import java.lang.ref.WeakReference;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
 
// TODO: Auto-generated Javadoc 
/**
 * The Class MainActivity controls the main UI, including click events and intents.
 */  
public class MainActivity extends Activity {
	static Context myContext;
	static String TAG = "MainActivity";
	static String responseString = null;
	static ListView myListView;
	final MyServiceHandler myServiceHandler = new MyServiceHandler(this);
	static DataManager myDataManager;
	static String myFileName = "string_from_url.txt";
	Intent myLauncherIntent;
	static String numberEnteredString;
	String intentType;   
	
	static TextView usdTV, eurTV, gbpTV, inrTV, cadTV, audTV, mxnTV, cnyTV, myrTV, aedTV;
	
    /* (non-Javadoc)   
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.setRequestedOrientation(0); 
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activity_main);
        
        myContext = this;  
        
        //Grab intent and extra (entered number)
        myLauncherIntent = getIntent();
        numberEnteredString = myLauncherIntent.getStringExtra("amountEnteredString");
        
        Log.i(TAG, "Number entered from intent = " + numberEnteredString);
        
        if (numberEnteredString == null) {
			numberEnteredString = "1";
		}
        
        Log.i(TAG, "Number entered = " + numberEnteredString);
        
        //Grab instance of DataManager
        myDataManager = DataManager.getInstance();
        
        //Grab text views by id to be accessed from JSONData
        usdTV = (TextView) this.findViewById(R.id.usdTV);
        eurTV = (TextView) this.findViewById(R.id.eurTV);
        gbpTV = (TextView) this.findViewById(R.id.gbpTV);
        inrTV = (TextView) this.findViewById(R.id.inrTV);
        cadTV = (TextView) this.findViewById(R.id.cadTV);
        audTV = (TextView) this.findViewById(R.id.audTV);
        mxnTV = (TextView) this.findViewById(R.id.mxnTV);
        cnyTV = (TextView) this.findViewById(R.id.cnyTV);
        myrTV = (TextView) this.findViewById(R.id.myrTV);
        aedTV = (TextView) this.findViewById(R.id.aedTV);
        
        Button refreshButton = (Button) this.findViewById(R.id.refreshButton);
		
		// Check if the file already exists
		File file = this.getFileStreamPath(myFileName);
		final Boolean fileExists = file.exists();
		if (fileExists) {
			// Display the data to the listview automatically if file exists
			JSONData.displayDataFromFile(); 
			

			Log.i("File", "File exists");
		} else {
			if (NetworkConnection.connectionStatus(myContext)) {
				// Show No File alert
				noFileAlert();
				// Call retrieveData to start the Service and get JSON data from
				// the API
				retrieveData(); 
			} else {
				// Show No Connection alert
				noConnectionAlert();
				//Disable refresh button
				refreshButton.setEnabled(false);
			} 

			Log.i("File", "File DOESN'T exist!!");
		} 
		
		//Set refreshButton onClick to display. Button disabled if no network
		refreshButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				JSONData.displayDataFromFile();
				
			}
		});
    }
    
	/*
	 * The Class MyServiceHandler creates a Service Handler with a Weak Ref to
	 * the MainActivty. This is done to avoid the memory leak
	 */
	private static class MyServiceHandler extends Handler {

		private final WeakReference<MainActivity> myActivity;

		public MyServiceHandler(MainActivity activity) {
			myActivity = new WeakReference<MainActivity>(activity);
		} 

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
					// Write the file to the system
					myDataManager.writeStringToFile(myContext, myFileName,
							DataService.responseString);

				} else {
					Log.i(TAG, "Data NOT created!!");
				}
			}

		} // HandleMessage Close
	} // MyHandler Close

	/*
	 * Retrieve data creates the Service Intent, adds extras and finally starts
	 * the Service.
	 */
	public void retrieveData() {
		Log.i(TAG, "retrieveData called");
		// Create Messenger class for ref to handler
		Messenger dataMessenger = new Messenger(myServiceHandler);

		// Create Intent to start service
		Intent startDataIntent = new Intent(myContext, DataService.class);
		startDataIntent.putExtra(DataService.MESSENGER_KEY, dataMessenger);

		// Start the service
		startService(startDataIntent);

	} // retrieveData Close
    
	/*
	 * noConnectionAlert creates and displays an alert dialog if no Network
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
	
	/*
	 * noFileAlert provides an alert dialog when a file doesn't exist on the
	 * device
	 */
	public void noFileAlert() {
		// Create alert dialog for no file
		AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
				.create();
		alertDialog.setTitle(R.string.noFileTitle);
		// Set alert message. setMessage only has a charSequence
		// version so getString must be used.
		alertDialog.setMessage(myContext.getString(R.string.noFileAlert));
		alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
				(DialogInterface.OnClickListener) null);
		alertDialog.show();
	}
}
