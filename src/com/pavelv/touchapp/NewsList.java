package com.pavelv.touchapp;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

//import com.pavelv.tapp.ParseXMLNews;
//import com.pavelv.tapp.ParseXMLNewsMethods;
//import com.pavelv.tapp.R;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class NewsList extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.news);
		
//		new RadioBackground().execute();		

//		final ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();

//		String xml = GetXMLMethods
//				.getXML("http://www.touchmusic.org.uk/iphone.xml");
//		final Document doc = GetXMLMethods.XMLfromString(xml);
//
//		NodeList children = doc.getElementsByTagName("item");
//
//		for (int i = 0; i < children.getLength(); i++) {
//			HashMap<String, String> map = new HashMap<String, String>();
//			Element e = (Element) children.item(i);
//			map.put("title", GetXMLMethods.getValue(e, "title"));
//			map.put("pubDate", GetXMLMethods.getValue(e, "pubDate"));
//			map.put("description", GetXMLMethods.getValue(e, "description"));
//			mylist.add(map);
//		}

		// ParseInBackground pib = (ParseInBackground) new
		// ParseInBackground().execute();
		// final ArrayList<HashMap<String, String>> mylist = new
		// ParseInBackground().execute();
		// ArrayList<HashMap<String, String>> mylist = pib.doInBackground();

		ListAdapter adapter = new SimpleAdapter(this, NewsBackground.getPlayerStatus(),
				R.layout.list_layout, new String[] { "title", "pubDate" },
				new int[] { R.id.title, R.id.subtitle });

		View header = getLayoutInflater().inflate(R.layout.listview_header,
				null);

		Resources res = getResources();
		Drawable drawable = res.getDrawable(R.drawable.news_header);
		header.findViewById(R.id.header).setBackgroundDrawable(drawable);

		ListView listView = getListView();
		listView.addHeaderView(header);

		setListAdapter(adapter);

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

		Intent i = new Intent(this, ShowCity.class);
		ListView lv = getListView();
		HashMap<String, String> o = (HashMap<String, String>) lv
				.getItemAtPosition(position);

		i.putExtra("title", o.get("title"));
		i.putExtra("subtitle", o.get("pubDate"));
		i.putExtra("description", o.get("description"));

		// Create the view using FirstGroup's LocalActivityManager
		View view = NewsGroup.group
				.getLocalActivityManager()
				.startActivity("show_city",
						i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
				.getDecorView();

		// Again, replace the view
		NewsGroup.group.replaceView(view);
	}
	
	@Override
	public void onBackPressed() {
//		NewsGroup.group.back();
//        this.moveTaskToBack(true);
		return;
	}

//	 public boolean onKeyDown(int keyCode, KeyEvent event)  
//	     {
//	          if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
//	          {
//	             this.moveTaskToBack(true);
//	             return true;
//	          }
//	         return super.onKeyDown(keyCode, event);
//	     }
}
