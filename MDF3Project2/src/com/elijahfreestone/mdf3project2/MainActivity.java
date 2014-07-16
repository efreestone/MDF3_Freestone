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

// TODO: Auto-generated Javadoc
/**
 * The Class MainActivity.
 */
public class MainActivity extends Activity implements SensorEventListener {
	boolean sensorInitialized;
	Sensor myAccelSensor;
	SensorManager mySensorManager;
	float NOISE = 2.0f;
	Context myContext;

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
		myAccelSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mySensorManager.registerListener(this, myAccelSensor, SensorManager.SENSOR_DELAY_NORMAL);
		
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 * 
	 * onResume is used to reset the sensor listener
	 */
	public void onResume() {
		super.onResume();
		mySensorManager.registerListener(this, myAccelSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 * 
	 * onPause is used to unregister the sensor listener to avoid battery drainage
	 */
	public void onPause() {
		super.onPause();
		mySensorManager.unregisterListener(this);
	}

	/* (non-Javadoc)
	 * @see android.hardware.SensorEventListener#onAccuracyChanged(android.hardware.Sensor, int)
	 */
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see android.hardware.SensorEventListener#onSensorChanged(android.hardware.SensorEvent)
	 */
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		
	} 

}
