/*
 * @author	Elijah Freestone 
 *
 * Project	MDF3Project3
 * 
 * Package	com.elijahfreestone.mdf3project3
 * 
 * Date		Jul 22, 2014
 */

package com.elijahfreestone.mdf3project3;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

// TODO: Auto-generated Javadoc
/**
 * The Class DetailsActivity.
 */
public class DetailsActivity extends Activity implements
		DetailsActivityFragment.OnGetMoreInfoClicked {
	static String dvdTitle;
	String releaseDate, movieRating, criticRating, audienceRating;
	static Float ratingSelected;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details_fragment);

		DetailsActivityFragment detailsFragment = (DetailsActivityFragment) getFragmentManager()
				.findFragmentById(R.id.detailsFragment);

		// Grab intent extras to be displayed in textviews
		Intent newDetailsIntent = getIntent();
		dvdTitle = newDetailsIntent.getStringExtra("dvdTitle");
		releaseDate = newDetailsIntent.getStringExtra("releaseDate");
		movieRating = newDetailsIntent.getStringExtra("movieRating");
		criticRating = newDetailsIntent.getStringExtra("criticRating");
		audienceRating = newDetailsIntent.getStringExtra("audienceRating");

		if (newDetailsIntent != null) {
			if (detailsFragment != null) {
				detailsFragment.displayMovieDetails(dvdTitle, releaseDate,
						movieRating, criticRating, audienceRating);
			} else {
				Log.i("Details", "Details Frag is null!!");
			}
		}

		// Finish activity if device is changed to Landscape. Land xml replaces
		// this in 2 pane
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			finish();
			// detailsFragment.displayMovieDetails(dvdTitle, releaseDate,
			// movieRating, criticRating, audienceRating);
			Log.i("Details Fragment", "Details Fragment Finish called");
		}
	} // onCreate Close

	/*
	 * finish is called when the activity is exited (such as the back button)
	 * This creates a new intent and passes the title and rating back
	 */
	@Override
	public void finish() {
		Log.i("Details Activity", "Finish called");
		Intent detailsBackIntent = new Intent();
		detailsBackIntent.putExtra("dvdTitle", dvdTitle);
		detailsBackIntent.putExtra("releaseDate", releaseDate);
		detailsBackIntent.putExtra("movieRating", movieRating);
		detailsBackIntent.putExtra("criticRating", criticRating);
		detailsBackIntent.putExtra("audienceRating", audienceRating);
		detailsBackIntent.putExtra("ratingSelected", ratingSelected);
		setResult(RESULT_OK, detailsBackIntent);
		super.finish();
	} // finish Close

	/*
	 * Create implicit intent. This will pass a custom URL searching for the
	 * movie title on RottenTomatoes.com and open it in a browser
	 */
	@Override
	public void onGetMoreInfoClicked() {
		String baseURLString = "http://www.rottentomatoes.com/m/";
		String moddedTitle = dvdTitle.replace(" ", "_");
		String urlSearchMod = baseURLString + moddedTitle;
		Intent moreInfoIntent = new Intent(Intent.ACTION_VIEW,
				Uri.parse(urlSearchMod));

		startActivity(moreInfoIntent);

	} // onGetMoreInfoClicked Close

}
