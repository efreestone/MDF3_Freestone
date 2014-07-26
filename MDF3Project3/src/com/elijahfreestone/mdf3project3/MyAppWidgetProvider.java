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
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class MyAppWidgetProvider extends AppWidgetProvider { 
	int arrayPosition;
	static ArrayList<HashMap<String, String>> widgetMovieList = null;
	String movieString;
	String backgroundColor;
	String textColor;
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		//Log.i("Widget Provider", "onUpdate ID: " + appWidgetIds[0]); 
		
		arrayPosition = MainActivity.widgetClickCount++;
		
		movieString = "Loading... Please click plus to load a movie";
		
		if (widgetMovieList != null) {
			int arraySize = widgetMovieList.size();
			Log.i("Widget Array", "Widget Array = " + arraySize);
			String movieTitleString = widgetMovieList.get(arrayPosition).get("dvdTitle");
			String movieReleaseString = widgetMovieList.get(arrayPosition).get("releaseDate");
			String movieRatingString = widgetMovieList.get(arrayPosition).get("movieRating");
			
			movieString = movieTitleString + "\n" + movieReleaseString + "\n" + movieRatingString;
			Log.i("Widget", "Movie String = " + movieString); 
		} 
		
		if (arrayPosition < 9) {
			Log.i("Widget", "Click count = " + MainActivity.widgetClickCount);
		} else {
			MainActivity.widgetClickCount = 0;
			Log.i("Widget", "Click count reset = " + MainActivity.widgetClickCount);
		} 

		Log.i("Widget", "test int = " + arrayPosition); 
		
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.widget_layout);
		remoteViews.setTextViewText(R.id.movieTitle, movieString);
		
		Intent intent = new Intent(context, MyAppWidgetProvider.class);
		intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);
		remoteViews.setOnClickPendingIntent(R.id.buttonForward, pendingIntent);
		appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
		//pushUpdateToWidget(context, remoteViews);    
	} 
	
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);     
	}
	
	public static void pushUpdateToWidget(Context context, RemoteViews remoteViews) {
		ComponentName movieWidget = new ComponentName(context, MyAppWidgetProvider.class);
		AppWidgetManager myWidgetManager = AppWidgetManager.getInstance(context);
		myWidgetManager.updateAppWidget(movieWidget, remoteViews);
	} 

}
