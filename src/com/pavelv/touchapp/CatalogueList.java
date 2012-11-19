package com.pavelv.touchapp;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class CatalogueList extends ListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
//		new CatInBg().execute();
		
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.catalogue);

//		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
		
//		ArrayList<HashMap<String, String>> mylist = new CatInBg().doInBackground();
		
//		if (CatalogueBackground.Status == "PENDING") {
//			
//		}
//		
//		if (CatalogueBackground.mylist == null) {
//			System.out.println("NNNNUUULLL");
//		}

		ListAdapter adapter = new SimpleAdapter(this, CatalogueBackground.getPlayerStatus(),
				R.layout.list_layout, new String[] { "artist", "title" },
				new int[] { R.id.title, R.id.subtitle });

		View header = getLayoutInflater().inflate(R.layout.listview_header,
				null);

		Resources res = getResources();
		Drawable drawable = res.getDrawable(R.drawable.catalogue_header);
		header.findViewById(R.id.header).setBackgroundDrawable(drawable);

		ListView listView = getListView();
		listView.addHeaderView(header);

//		String xml = GetXMLMethods
//				.getXML("http://electric-window-7475.herokuapp.com/releases/publisher/Touch.xml");
//		Document doc = GetXMLMethods.XMLfromString(xml);
//
//		NodeList children = doc.getElementsByTagName("release");
//
//		for (int i = 0; i < children.getLength(); i++) {
//			HashMap<String, String> map = new HashMap<String, String>();
//			Element e = (Element) children.item(i);
//			map.put("artist", GetXMLMethods.getValue(e, "artist"));
//			map.put("title", GetXMLMethods.getValue(e, "title"));
//			map.put("release_date", GetXMLMethods.getValue(e, "release_date"));
//			map.put("catalogue_number",
//					GetXMLMethods.getValue(e, "catalogue_number"));
//			map.put("cover_art_url", GetXMLMethods.getValue(e, "cover_art_url"));
//			map.put("track_listing", GetXMLMethods.getValue(e, "track_listing"));
//			map.put("mp3_sample_url",
//					GetXMLMethods.getValue(e, "mp3_sample_url"));
//			map.put("description", GetXMLMethods.getValue(e, "description"));
//			map.put("release_url", GetXMLMethods.getValue(e, "release_url"));
//			mylist.add(map);
//
//			adapter = new SimpleAdapter(this, mylist, R.layout.list_layout,
//					new String[] { "artist", "title" }, new int[] { R.id.title,
//							R.id.subtitle });

			setListAdapter(adapter);

//		}

		// ParseCatInBg pib = (ParseCatInBg) new ParseCatInBg().execute();
		// final ArrayList<HashMap<String, String>> mylist = new
		// ParseInBackground().execute();
		// mylist = pib.doInBackground();
		//
		// adapter = new SimpleAdapter(this, mylist, R.layout.list_layout,
		// new String[] { "artist", "title" }, new int[] { R.id.title,
		// R.id.subtitle });
		//
		// setListAdapter(adapter);

	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		final Button play;
		final Button pause;

		play = (Button) findViewById(R.id.button1);
		play.setVisibility(View.INVISIBLE);
		pause = (Button) findViewById(R.id.button2);
		pause.setVisibility(View.INVISIBLE);

		if (Stream.getPlayerStatus() == true) {
			play.setVisibility(View.VISIBLE);
		} else {
			System.out.println("No-no-no");
		}

		play.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// when play is clicked show stop button and hide play button
				play.setVisibility(View.GONE);
				pause.setVisibility(View.VISIBLE);
				Stream.setOnPause();
			}
		});

		pause.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// when play is clicked show stop button and hide play button
				pause.setVisibility(View.GONE);
				play.setVisibility(View.VISIBLE);
				Stream.setResume();
			}
		});
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Intent i = new Intent(this, CatalogueItem.class);
		ListView lv = getListView();
		HashMap<String, String> o = (HashMap<String, String>) lv
				.getItemAtPosition(position);

		i.putExtra("artist", o.get("artist"));
		i.putExtra("title", o.get("title"));
		i.putExtra("release_date", o.get("release_date"));
		i.putExtra("catalogue_number", o.get("catalogue_number"));
		i.putExtra("cover_art_url", o.get("cover_art_url"));
		i.putExtra("track_listing", o.get("track_listing"));
		i.putExtra("description", o.get("description"));
		i.putExtra("mp3_sample_url", o.get("mp3_sample_url"));
		i.putExtra("release_url", o.get("release_url"));

		// Create the view using FirstGroup's LocalActivityManager
		View view = CatalogueGroup.group
				.getLocalActivityManager()
				.startActivity("CatalogueItem",
						i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
				.getDecorView();

		// Again, replace the view
		CatalogueGroup.group.replaceView(view);
	}
	
	@Override
	public void onBackPressed() {
//		CatalogueGroup.group.back();
		return;
	}
}