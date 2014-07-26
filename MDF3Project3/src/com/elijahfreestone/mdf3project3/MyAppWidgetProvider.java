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

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class MyAppWidgetProvider extends AppWidgetProvider { 
	//Integer testInt;
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		//Log.i("Widget Provider", "onUpdate ID: " + appWidgetIds[0]); 
		
		Integer testInt = MainActivity.testInt;
		
		//Log.i("Widget", "before ++ " + testInt);
		testInt = WidgetBroadcastReceiver.clickCount++;
		
//		if (testInt == null) {
//			testInt = 0;
//		} else {
//			testInt++;
//		}
		
		if (testInt < 9) {
			Log.i("Widget", "Click count = " + WidgetBroadcastReceiver.clickCount);
		} else {
			WidgetBroadcastReceiver.clickCount = 0;
			Log.i("Widget", "Click count reset = " + WidgetBroadcastReceiver.clickCount);
		} 

		Log.i("Widget", "test int = " + testInt);
		
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.widget_layout);
		remoteViews.setTextViewText(R.id.movieTitle, String.valueOf(testInt));
		
		Intent intent = new Intent(context, MyAppWidgetProvider.class);
		intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);
		remoteViews.setOnClickPendingIntent(R.id.button1, pendingIntent);
		//appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
		pushUpdateToWidget(context, remoteViews);
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
