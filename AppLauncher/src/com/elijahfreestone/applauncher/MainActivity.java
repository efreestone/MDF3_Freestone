/*
 * @author	Elijah Freestone 
 *
 * Project	AppLauncher
 * 
 * Package	com.elijahfreestone.applauncher
 * 
 * Date		Jul 9, 2014
 */
package com.elijahfreestone.applauncher;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

// TODO: Auto-generated Javadoc
/**
 * The Class MainActivity.
 */
public class MainActivity extends Activity {
	String amountEnteredString;

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final EditText amountEditText = (EditText) findViewById(R.id.amountEntered);
        
        Button newConvertButton = (Button) findViewById(R.id.convertButton);
        newConvertButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				amountEnteredString = amountEditText.getText().toString();
				if (amountEnteredString == null || amountEnteredString.equalsIgnoreCase("0")) {
					System.out.println("Nothing or zero entered");
				} else if (amountEnteredString.length() >=1) {
					System.out.println("button clicked and " + amountEnteredString + " entered");
				}

			}
		});

    }


    /* (non-Javadoc)
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}