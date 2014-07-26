/*
 * @author	Elijah Freestone 
 *
 * Project	MDF3Project3
 * 
 * Package	com.elijahfreestone.mdf3project3
 * 
 * Date		Jul 25, 2014
 */

package com.elijahfreestone.mdf3project3;

import java.util.ArrayList;
import java.util.HashMap;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class WidgetBroadcastReceiver extends BroadcastReceiver {
	static int clickCount = 0;
	static ArrayList<HashMap<String, String>> myMovieList;

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {
			updateMovieWidget(context);
		}
		Log.i("Widget", "onReceive else");
	}
	
	public void updateMovieWidget(Context context) {
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
		
		if (clickCount <= 9) {
			Log.i("Widget", "Click count less than 9");
		} else {
			clickCount = 0;
			Log.i("Widget", "Click count reset");
		}
		
		Log.i("Widget", "updateMovieWidget");
		
		MyAppWidgetProvider.pushUpdateToWidget(context.getApplicationContext(), remoteViews);
	} 

}
