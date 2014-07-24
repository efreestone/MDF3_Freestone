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
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

// TODO: Auto-generated Javadoc
/**
 * The Class DetailsActivityFragment handles all UI elements on DetailsActivity.
 */
public class DetailsActivityFragment extends Fragment {
	TextView titleTextView, releaseTextView, movieRatingTextView,
			criticRatingTextView, audienceRatingTextView;
	RatingBar movieRatingBar;
	Float ratingSelected;

	/*
	 * The Interface OnGetMoreInfoClicked.
	 */
	public interface OnGetMoreInfoClicked {
		// onGetMoreInfoClicked creates and calls implicit intent in
		// DetailsActivity
		void onGetMoreInfoClicked();
	}

	private OnGetMoreInfoClicked parentActivity;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onAttach(android.app.Activity)
	 */
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);

		if (activity instanceof OnGetMoreInfoClicked) {
			parentActivity = (OnGetMoreInfoClicked) activity;
		} else {
			Log.e("Detail Fragment", "Must implement OnGetMoreInfoClick");
		}

	} // onAttach Close

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater,
	 * android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Create/inflate view and return
		View detailsView = inflater.inflate(R.layout.activity_details,
				container);

		ratingSelected = 0.0f;

		// Grab all text views by id
		titleTextView = (TextView) detailsView.findViewById(R.id.titleTextView);
		releaseTextView = (TextView) detailsView
				.findViewById(R.id.releaseTextView);
		movieRatingTextView = (TextView) detailsView
				.findViewById(R.id.movieRatingTextView);
		criticRatingTextView = (TextView) detailsView
				.findViewById(R.id.criticRatingTextView);
		audienceRatingTextView = (TextView) detailsView
				.findViewById(R.id.audienceRatingTextView);

		// Grab button by id and set onClick for implicit intent
		Button moreInfoButton = (Button) detailsView
				.findViewById(R.id.moreInfoButton);
		moreInfoButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				parentActivity.onGetMoreInfoClicked();
			} // onClick Close
		}); // onClickListener Close

		movieRatingBar = (RatingBar) detailsView
				.findViewById(R.id.movieRatingBar);
		movieRatingBar
				.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {

					@Override
					public void onRatingChanged(RatingBar ratingBar,
							float rating, boolean fromUser) {
						DetailsActivity.ratingSelected = movieRatingBar
								.getRating();
					}
				});

		return detailsView;
	} // onCreateView Close

	/*
	 * displayMovieDetails displays details of the movie selected, called from
	 * DetailsActivity
	 */
	public void displayMovieDetails(String dvdTitle, String releaseDate,
			String movieRating, String criticRating, String audienceRating) {
		// Set textviews with strings from intent extras
		titleTextView.setText("DVD Title: " + dvdTitle);
		releaseTextView.setText("Released: " + releaseDate);
		movieRatingTextView.setText("MPAA Rating: " + movieRating);
		criticRatingTextView.setText("Critic Rating: " + criticRating);
		audienceRatingTextView.setText("Audience Rating: " + audienceRating);

		Log.i("Details Fragment", "Display Movie Details Called");
	} // displayMovieDetails Close

}
