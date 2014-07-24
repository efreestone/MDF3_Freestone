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
	
	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_config);
		
		Button doneButton = (Button) this.findViewById(R.id.doneButton);
		doneButton.setOnClickListener(this);
	};
	
//	private void displayAlert(String message) {
//		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this)
//		.setMessage(message)
//		.setCancelable(true);
//		alertBuilder.create().show();
//		
//	}

	@Override
	public void onClick(View v) {
		Bundle extras = getIntent().getExtras();
		
		if (extras != null) {
			int widgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, 
					AppWidgetManager.INVALID_APPWIDGET_ID);
			
			if (widgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
				RadioGroup backgroundRadioGroup = (RadioGroup) findViewById(R.id.backgroundRadioGroup);
//				RadioButton darkRadioButton = (RadioButton) findViewById(R.id.darkRadioButton);
//				RadioButton lightRadioButton = (RadioButton) findViewById(R.id.lightRadioButton);
				
				backgroundRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						RadioButton selectedButton = (RadioButton) findViewById(checkedId);
						String radioText = selectedButton.getText().toString();
						Log.i("Radio", "Radio Selected" + radioText);
						
						if (radioText.equalsIgnoreCase("Dark Background/Light Text")) {
							
						} else {
							
						}
						
					}
				});
			}
			
		}
	}

}
