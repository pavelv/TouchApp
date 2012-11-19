package com.pavelv.touchapp;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TabHost;

public class Touch extends TabActivity {

	public TabHost tabHost;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tabs);
		// Get the tabHost
		this.tabHost = getTabHost();

		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab

		// Create an Intent to launch the first Activity for the tab (to be
		// reused)
		intent = new Intent().setClass(this, NewsGroup.class);

		// Initialize a TabSpec for the first tab and add it to the TabHost
		spec = tabHost
				.newTabSpec("NewsGroup")
				.setIndicator("News",
						getResources().getDrawable(R.drawable.news_icon)) // Replace
																			// null
																			// with
																			// R.drawable.your_icon
																			// to
																			// set
																			// tab
																			// icon
				.setContent(intent);
		tabHost.addTab(spec);

		// //////////////////////////////////////////////////////////////////////

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, PhotosGroup.class);

		// Initialize a TabSpec for the second tab and add it to the TabHost
		spec = tabHost
				.newTabSpec("PhotosGroup")
				.setIndicator("Photos",
						getResources().getDrawable(R.drawable.photos_icon)) // Replace
																			// null
																			// with
																			// R.drawable.your_icon
																			// to
																			// set
																			// tab
																			// icon
				.setContent(intent);
		tabHost.addTab(spec);

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, CatalogueGroup.class);

		// Initialize a TabSpec for the second tab and add it to the TabHost
		spec = tabHost
				.newTabSpec("CatalogueGroup")
				.setIndicator("Catalogue",
						getResources().getDrawable(R.drawable.catalogue_icon)) // Replace
																				// null
																				// with
																				// R.drawable.your_icon
																				// to
																				// set
																				// tab
																				// icon
				.setContent(intent);
		tabHost.addTab(spec);

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, RadioGroup.class);

		// Initialize a TabSpec for the second tab and add it to the TabHost
		spec = tabHost
				.newTabSpec("RadioGroup")
				.setIndicator("Radio",
						getResources().getDrawable(R.drawable.radio_icon)) // Replace
																			// null
																			// with
																			// R.drawable.your_icon
																			// to
																			// set
																			// tab
																			// icon
				.setContent(intent);
		tabHost.addTab(spec);

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, RecipesGroup.class);

		// Initialize a TabSpec for the second tab and add it to the TabHost
		spec = tabHost
				.newTabSpec("RecipesGroup")
				.setIndicator("Recipes",
						getResources().getDrawable(R.drawable.recipes_icon)) // Replace
																				// null
																				// with
																				// R.drawable.your_icon
																				// to
																				// set
																				// tab
																				// icon
				.setContent(intent);
		tabHost.addTab(spec);

		// Tab to open by default

		tabHost.setCurrentTab(0);
	}
}
