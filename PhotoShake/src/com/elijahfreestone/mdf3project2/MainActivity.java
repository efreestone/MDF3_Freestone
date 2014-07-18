/*
 * @author	Elijah Freestone 
 *
 * Project	MDF3Project2
 * 
 * Package	com.elijahfreestone.mdf3project2
 * 
 * Date		Jul 12, 2014
 */

package com.elijahfreestone.mdf3project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

// TODO: Auto-generated Javadoc
/**
 * The Class MainActivity.
 */
public class MainActivity extends Activity implements SensorEventListener {
	boolean sensorInitialized;
	Sensor myAccelSensor;
	SensorManager mySensorManager;
	float NOISE = 19.5f;
	Context myContext;
	static String TAG = "MainActivity";
	ImageView newImageView;
	static Camera myCamera;
	LaunchCamera launchCamera;
	static int myCameraID = 0;
	boolean hasCamera;
	FrameLayout previewFrame;
	
	//Button testButton;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override   
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		myContext = this;  
		newImageView = (ImageView) findViewById(R.id.newImageView);
		//myCamera = null;   
		
		sensorInitialized = false;  
		//Instantiate sensor and manager
		mySensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		if (mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
			myAccelSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		}
		mySensorManager.registerListener(this, myAccelSensor, SensorManager.SENSOR_DELAY_NORMAL);
		
		previewFrame = (FrameLayout) findViewById(R.id.previewFrame);
		
	} //onCreate close
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 * 
	 * onResume is used to reset the sensor listener
	 */
	public void onResume() {
		super.onResume();
		mySensorManager.registerListener(this, myAccelSensor, SensorManager.SENSOR_DELAY_NORMAL);
		previewFrame = (FrameLayout) findViewById(R.id.previewFrame);
		//launchCamera = new LaunchCamera(this, myCamera);
	} //onResume close 
	
	/* (non-Javadoc) 
	 * @see android.app.Activity#onPause()
	 * 
	 * onPause is used to unregister the sensor listener to avoid battery drainage
	 */
	public void onPause() {
		super.onPause();
		mySensorManager.unregisterListener(this);
		
		//Release Camera
		if (myCamera != null) {
			myCamera.release();
			previewFrame = null;
		}  
//		myCamera.release();
//		previewFrame = null;  
		
	} //onPause close

	/* (non-Javadoc)
	 * @see android.hardware.SensorEventListener#onAccuracyChanged(android.hardware.Sensor, int)
	 */
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	} //onAccuracyChanged close 

	/* (non-Javadoc)
	 * @see android.hardware.SensorEventListener#onSensorChanged(android.hardware.SensorEvent)
	 */
	@Override
	public void onSensorChanged(SensorEvent event) {
		float lastXAxis = 0;
		float lastYAxis = 0;
		float lastZAxis = 0; 
		
		float xAxis = event.values[0];
		float yAxis = event.values[1];
		float zAxis = event.values[2];
		
		//Set or create default values if first time sensor has been activated
		if (!sensorInitialized) {
			lastXAxis = xAxis;
			lastYAxis = yAxis;
			lastZAxis = zAxis;
			
			sensorInitialized = true;
		} else {
			//Get delta values for sensor data
			float xDelta = Math.abs(lastXAxis - xAxis);
			float yDelta = Math.abs(lastYAxis - yAxis);
			float zDelta = Math.abs(lastZAxis - zAxis);
			
			//Zero out delta values below threshold level
			if (xDelta < NOISE) xDelta = 0;
			if (yDelta < NOISE) yDelta = 0;
			if (zDelta < NOISE) zDelta = 0;
			
			if (xDelta > yDelta) {
				Log.i(TAG, "X axis"); 
				//preview.visibility = "visible";
				newImageView.setVisibility(View.GONE);
				//previewFrame.setVisibility(View.VISIBLE);
				myCamera = checkDeviceForCamera();
				launchCamera = new LaunchCamera(this, myCamera);
				//previewFrame = (FrameLayout) findViewById(R.id.previewFrame);
				previewFrame.addView(launchCamera);
			} else if (yDelta > xDelta) {
				Log.i(TAG, "Y axis");  
			}   
		}
	} //onSensorChanged close 
	
	public static Camera checkDeviceForCamera() {
		myCamera = null;
		try {
			myCamera = Camera.open();
		} catch (Exception e) {
			Log.e(TAG, "Camera Error" + e.getMessage().toString());
			e.printStackTrace();
		}
		return myCamera;
	}
	
	public void takeNewPicture(View view) {
		myCamera.takePicture(null, null, myPictureCallback);
	}
	
	private PictureCallback myPictureCallback = new PictureCallback() {

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {

			Bitmap newBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			if (newBitmap == null) {
				Toast.makeText(getApplicationContext(), "Picture not taken",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(), "Picture taken",
						Toast.LENGTH_SHORT).show();
				
				File newPictureDirectory = getDirectory();
				//Make sure directory exists or is creatable
				if (!newPictureDirectory.exists() && !newPictureDirectory.mkdirs()) {
					Log.e(TAG, "Error creating directory");
					return;
				}
				
				//Grab date to be used for naming the new image
				SimpleDateFormat newSimpleDateFormat = new SimpleDateFormat("yyyymmddhhmmss");
				String dateTaken = newSimpleDateFormat.format(new Date());
				//String pictureFileName = newPictureDirectory.getPath() + File.separator + "pic_" + dateTaken + ".jpg";
				String photoFile = "Picture_" + dateTaken + ".jpg";

			    String filename = newPictureDirectory.getPath() + File.separator + photoFile;
			    
			    Log.i(TAG, "file name" + filename);

			    File pictureFile = new File(filename);  
				
				//File newPictureFile = new File(pictureFile);
				
				try {
					FileOutputStream fileOutputStream;
					fileOutputStream = new FileOutputStream(pictureFile);
					fileOutputStream.write(data);
					fileOutputStream.close();
					Log.i(TAG, "File output stream TRY");

					MediaScannerConnection.scanFile(myContext, new String[] { pictureFile.toString() }, null,
						new MediaScannerConnection.OnScanCompletedListener() {

							@Override
							public void onScanCompleted(String path, Uri uri) {
								Log.i("ExternalStorage", "Scanned " + path + ":");
				                Log.i("ExternalStorage", "-> uri=" + uri);
							} 
						});
				} catch (FileNotFoundException e) { 
					Log.e(TAG, "File output stream File not found" + e.getMessage().toString());
				} catch (IOException e) {
					Log.e(TAG, "File output stream IO Exception" + e.getMessage().toString());
				}	
			}
			myCamera.release();  
		}
	};
	
	public File getDirectory() {
		//File localDirectory = myContext.getDir("dirname", Context.MODE_PRIVATE);
		File localDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		Log.i(TAG, "getDirectory called");
		return new File(localDirectory, "NewImage");
	} //getDirectory close  

}
