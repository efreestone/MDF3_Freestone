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

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class WidgetConfigActivity extends Activity implements OnClickListener {
	String backgroundColorSelected;
	String textColorSelected;
	static String backgroundColor;
	static String textColor;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_config);
		
		Button doneButton = (Button) this.findViewById(R.id.doneButton);
		doneButton.setOnClickListener(this);
		
		RadioGroup backgroundRadioGroup = (RadioGroup) findViewById(R.id.backgroundRadioGroup);
		
		backgroundColor = "#ffffff";
		textColor = "#000000";

		backgroundColorSelected = "test";
		if (backgroundRadioGroup != null) {

			backgroundRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override 
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					RadioButton selectedButton = (RadioButton) findViewById(checkedId);
					String radioText = selectedButton.getText().toString();
					Log.i("Radio", "Radio Selected" + radioText);

					if (radioText.equalsIgnoreCase("Dark Background/Light Text")) {
						backgroundColorSelected = radioText;
						backgroundColor = "#000000";
						textColor = "#ffffff";
					} else {
						backgroundColorSelected = radioText;
						backgroundColor = "#ffffff";
						textColor = "#000000";
					}
				}
			});
		}
	};

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 * 
	 * onClick saves background/text color prefs and finishes config 
	 */
	@Override
	public void onClick(View v) { 
		Bundle extras = getIntent().getExtras();
		
		if (extras != null) {
			int widgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, 
					AppWidgetManager.INVALID_APPWIDGET_ID); 
			
			MyWidgetUtility.savePreferences(backgroundColor, textColor); 
			
			if (widgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
				//RemoteViews remoteView = new RemoteViews(getPackageName(), R.layout.widget_layout);
				//remoteView.setTextViewText(R.id.movieTitle, backgroundColorSelected);
				//remoteView.setTextColor(R.id.movieTitle, textColor.);
				//remoteView.setInt(widgetId, methodName, value)
				//remoteView.setImageViewResource(R.id.backgroundColor, R.drawable.rounded_corner);
				//remoteView.setInt(R.id.backgroundColor, "setColorFilter", Color.parseColor(backgroundColor));
				//remoteView.setInt(R.id.backgroundColor, "setAlpha", alpha);
				
				//AppWidgetManager.getInstance(this).updateAppWidget(widgetId, remoteView);
				//MyAppWidgetProvider.pushUpdateToWidget(this, remoteView);
				Log.i("Widget", "Widget ID: " + widgetId); 
				
//				PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
//						getIntent(), PendingIntent.FLAG_UPDATE_CURRENT);
//				remoteView.setOnClickPendingIntent(R.id.buttonForward, pendingIntent);
				
//				Intent uriIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriString));
//				PendingIntent pendingIntent2 = PendingIntent.getBroadcast(this, 0, uriIntent, 0);
//				remoteView.setOnClickPendingIntent(R.id.buttonBack, pendingIntent2);
//				
//				AppWidgetManager.getInstance(this).updateAppWidget(widgetId, remoteView);
				
				Intent resultValue = new Intent();
				resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
				setResult(RESULT_OK, resultValue); 
				
				finish();  
				
			}
			
		}
	}

}
