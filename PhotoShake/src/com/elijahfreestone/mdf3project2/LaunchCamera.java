/*
 * @author	Elijah Freestone 
 *
 * Project	MDF3Project2
 * 
 * Package	com.elijahfreestone.mdf3project2
 * 
 * Date		Jul 17, 2014
 */

package com.elijahfreestone.mdf3project2;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

// TODO: Auto-generated Javadoc
@SuppressLint("ViewConstructor")
public class LaunchCamera extends SurfaceView implements SurfaceHolder.Callback {
	private SurfaceHolder mySurfaceHolder;
	private Camera myCamera;
	String TAG = "LaunchCamera";
	
	/**
	 * Instantiates a new launch camera.
	 *
	 * @param context the context
	 * @param camera the camera
	 */
	public LaunchCamera(Context context, Camera camera) {
		super(context);
		myCamera = camera;
		mySurfaceHolder = getHolder();
		mySurfaceHolder.addCallback(this);
		
	}

	/* (non-Javadoc)
	 * @see android.view.SurfaceHolder.Callback#surfaceChanged(android.view.SurfaceHolder, int, int, int)
	 */
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
	}

	/* (non-Javadoc)
	 * @see android.view.SurfaceHolder.Callback#surfaceCreated(android.view.SurfaceHolder)
	 */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		try {
			myCamera.setPreviewDisplay(holder);      
			Camera.Parameters myParameters = myCamera.getParameters();
			myParameters.set("orientation", "portrait");
			myParameters.set("rotation", "90");
			myCamera.startPreview();
		} catch (IOException e) { 
			Log.e(TAG, "Preview Error" + e.getMessage().toString());
			e.printStackTrace(); 
		}
	} 

	/* (non-Javadoc)
	 * @see android.view.SurfaceHolder.Callback#surfaceDestroyed(android.view.SurfaceHolder)
	 */
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		if (myCamera != null) {
			this.getHolder().removeCallback(this); 
			myCamera.release();
			myCamera = null; 
	    }
	}

}
