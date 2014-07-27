/*
 * @author	Elijah Freestone 
 *
 * Project	MDF3Project3
 * 
 * Package	com.elijahfreestone.mdf3project3
 * 
 * Date		Jul 23, 2014
 */

package com.elijahfreestone.mdf3project3;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.widget.RemoteViews;

// TODO: Auto-generated Javadoc
public class MyAppWidgetProvider extends AppWidgetProvider { 
	static int arrayPosition;
	static ArrayList<HashMap<String, String>> widgetMovieList = null;
	String movieString;
	static String backgroundColor; 
	static String textColor;
	static AppWidgetManager myWidgetManager;
	String movieTitleString;
	String movieReleaseString;
	String movieRatingString;
	String criticRatingString;
	String audienceRatingString;
	
	/* (non-Javadoc)
	 * @see android.appwidget.AppWidgetProvider#onUpdate(android.content.Context, android.appwidget.AppWidgetManager, int[])
	 */
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		Log.i("Widget Provider", "onUpdate ID: " + appWidgetIds[0]); 
		
		myWidgetManager = appWidgetManager;
		
		//Get background/text color from prefs.
		MyWidgetUtility.getPreferences(); 
		
		if (backgroundColor == null) {
			backgroundColor = "#ffffff";
			textColor = "#000000";
		}
		
		//Increment array position to get next movie in arraylist
		arrayPosition = MainActivity.widgetClickCount++;
		
		movieString = "Movie list loaded. Please click forward to load a movie";
		
		Intent uriIntent = new Intent(context, DetailsActivity.class);
		
		if (widgetMovieList != null) {
			int arraySize = widgetMovieList.size();
			Log.i("Widget Array", "Widget Array = " + arraySize);
			//Grab movie details from movie arraylist
			movieTitleString = widgetMovieList.get(arrayPosition).get("dvdTitle");
			movieReleaseString = widgetMovieList.get(arrayPosition).get("releaseDate");
			movieRatingString = widgetMovieList.get(arrayPosition).get("movieRating");
			criticRatingString = widgetMovieList.get(arrayPosition).get("criticRating");
			audienceRatingString = widgetMovieList.get(arrayPosition).get("audienceRating");
			
			movieString = movieTitleString + "\n" + movieReleaseString + "\n" + movieRatingString;
			//Log.i("Widget", "Movie String = " + movieString); 
		} 
		
		//Reset array position to avoid going past the length of arraylist
		if (arrayPosition < 9) {
			Log.i("Widget", "Click count = " + MainActivity.widgetClickCount);
		} else {
			MainActivity.widgetClickCount = 0;
			Log.i("Widget", "Click count reset = " + MainActivity.widgetClickCount);
		} 

		Log.i("Widget", "test int = " + arrayPosition);   
		
		//Grab widget and set background/text color
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.widget_layout);
		remoteViews.setTextViewText(R.id.widgetMovieTitle, movieString);
		remoteViews.setInt(R.id.backgroundColor, "setColorFilter", Color.parseColor(backgroundColor));
		remoteViews.setTextColor(R.id.widgetMovieTitle, Color.parseColor(textColor));
		
		//Create intent for movie selection in widget.
		//This currently always sets to the first item in the arraylist. Not sure why yet.
		uriIntent.putExtra("dvdTitle", movieTitleString);
		uriIntent.putExtra("releaseDate", movieReleaseString);
		uriIntent.putExtra("movieRating", movieRatingString);
		uriIntent.putExtra("criticRating", criticRatingString);
		uriIntent.putExtra("audienceRating", audienceRatingString); 
		
		PendingIntent pendingIntent2 = PendingIntent.getActivity(context, 0, uriIntent, 0);
		remoteViews.setOnClickPendingIntent(R.id.widgetMovieTitle, pendingIntent2);
		
		//Create intent for forward button
		Intent intent = new Intent(context, MyAppWidgetProvider.class);
		intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE); 
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);
		remoteViews.setOnClickPendingIntent(R.id.buttonForward, pendingIntent);
		appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
		//pushUpdateToWidget(context, remoteViews);     
	} 
	
	/* (non-Javadoc)
	 * @see android.appwidget.AppWidgetProvider#onDeleted(android.content.Context, int[])
	 */
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds); 
		MainActivity.preferences.edit().clear(); 
	}  
	
}
