package com.elijahfreestone.mdf3project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Environment;
import android.util.Log;

public class PictureHandler implements PictureCallback {
	Context context;
	String TAG = "PictureHandler";
	
	//Grab the context
	public PictureHandler(Context context) {
		this.context = context;
	}

	@Override
	public void onPictureTaken(byte[] data, Camera camera) {
		File newPictureDirectory = getDirectory();
		
		camera.startPreview();
		
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

	    File pictureFile = new File(filename); 
		
		//File newPictureFile = new File(pictureFile);
		
		try {
			FileOutputStream fileOutputStream;
			fileOutputStream = new FileOutputStream(pictureFile);
			fileOutputStream.write(data);
			fileOutputStream.close();
			Log.i(TAG, "File output stream TRY");
		} catch (FileNotFoundException e) {
			Log.e(TAG, "File output stream File not found" + e.getMessage().toString());
		} catch (IOException e) {
			Log.e(TAG, "File output stream IO Exception" + e.getMessage().toString());
		}	 
		
	} //onPictureTaken close
	
	public File getDirectory() {
		File localDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		Log.i(TAG, "getDirectory called");
		return new File(localDirectory, "NewImage");
	} //getDirectory close
 
}
