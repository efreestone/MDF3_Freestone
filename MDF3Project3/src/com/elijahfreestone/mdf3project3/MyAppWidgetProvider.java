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
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class MyAppWidgetProvider extends AppWidgetProvider { 
	int testInt = 0;
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		Log.i("Widget Provider", "onUpdate ID: " + appWidgetIds[0]); 
		
		testInt = testInt +1;
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
		appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
	}
	
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);     
	}

}
