package com.pavelv.touchapp;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

@SuppressLint("NewApi")
public class AsyncHelp extends AsyncTask {

	@Override
	protected Boolean doInBackground(Object... params) {
		new CatalogueBackground().execute();
		new RadioBackground().execute();
		new RecipesBackground().execute();
		return true;
	}
	
	@Override
	protected void onPreExecute() {
//		super.onPreExecute();
		new NewsBackground().execute();
	}
	
	protected void onPostExecute(Boolean result) {
//		setContentView(R.layout.catalogue);
	}

}
