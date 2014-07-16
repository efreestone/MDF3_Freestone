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

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

// TODO: Auto-generated Javadoc
/**
 * The Class MainActivity.
 */
public class MainActivity extends Activity implements SensorEventListener {
	boolean sensorInitialized;
	Sensor myAccelSensor;
	SensorManager mySensorManager;
	float NOISE = 19.6f;
	Context myContext;
	String TAG = "MainActivity";

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		myContext = this;
		sensorInitialized = false;
		//Instantiate sensor and manager
		mySensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		
		if (mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
			myAccelSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		}
		
		mySensorManager.registerListener(this, myAccelSensor, SensorManager.SENSOR_DELAY_NORMAL);
		
	} //onCreate close
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 * 
	 * onResume is used to reset the sensor listener
	 */
	public void onResume() {
		super.onResume();
		mySensorManager.registerListener(this, myAccelSensor, SensorManager.SENSOR_DELAY_NORMAL);
	} //onResume close
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 * 
	 * onPause is used to unregister the sensor listener to avoid battery drainage
	 */
	public void onPause() {
		super.onPause();
		mySensorManager.unregisterListener(this);
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
		
		if (!sensorInitialized) {
			lastXAxis = xAxis;
			lastYAxis = yAxis;
			lastZAxis = zAxis;
			
			sensorInitialized = true;
		} else {
			float xDelta = Math.abs(lastXAxis - xAxis);
			float yDelta = Math.abs(lastYAxis - yAxis);
			float zDelta = Math.abs(lastZAxis - zAxis);
			
			if (xDelta < NOISE) xDelta = 0;
			if (yDelta < NOISE) yDelta = 0;
			if (zDelta < NOISE) zDelta = 0;
			
			lastXAxis = xAxis;
			lastYAxis = yAxis;
			lastZAxis = zAxis;
			
			if (xDelta > yDelta) {
				Log.i(TAG, "X axis");
			} else if (yDelta > xDelta) {
				Log.i(TAG, "Y axis");
			} else {
				//Log.i(TAG, "No axis");
			}
			
//			if (xAxis < NOISE) {
//				Log.i(TAG, "x axis");
//				//sensorInitialized = false;
//			} 
//			
//			if (yAxis < NOISE) {
//				Log.i(TAG, "y axis");
//				//sensorInitialized = false;
//			}
//			
//			if (zAxis < NOISE) {
//				Log.i(TAG, "z axis");
//				//sensorInitialized = false;
//			}
		}
	} //onSensorChanged close 

}
