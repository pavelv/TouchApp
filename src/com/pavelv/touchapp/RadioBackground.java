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
public class RadioBackground extends AsyncTask {
	
	static ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
//	static ArrayList<HashMap<String, String>> mylist2 = new ArrayList<HashMap<String, String>>();

	@Override
	protected ArrayList<HashMap<String, String>> doInBackground(Object... params) {
		
		String xml = GetXMLMethods
				.getXML("http://www.touchmusic.org.uk/TouchPod/podcast.xml");
		Document doc = GetXMLMethods.XMLfromString(xml);

		NodeList children = doc.getElementsByTagName("item");

		for (int i = 0; i < children.getLength(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			Element e = (Element) children.item(i);
			map.put("itunes:subtitle",
					GetXMLMethods.getValue(e, "itunes:subtitle"));
			map.put("title", GetXMLMethods.getValue(e, "title"));
			map.put("description", GetXMLMethods.getValue(e, "description"));
			map.put("link", GetXMLMethods.getValue(e, "link"));
			mylist.add(map);
		}
		
		return mylist;
	}
	
	@Override
	protected void onPreExecute() {
//		super.onPreExecute();
//		String xml = GetXMLMethods
//				.getXML("http://www.touchmusic.org.uk/TouchPod/podcast.xml");
//		Document doc = GetXMLMethods.XMLfromString(xml);
//
//		NodeList children = doc.getElementsByTagName("item");
//
//		for (int i = 0; i < children.getLength(); i++) {
//			HashMap<String, String> map = new HashMap<String, String>();
//			Element e = (Element) children.item(i);
//			map.put("itunes:subtitle",
//					GetXMLMethods.getValue(e, "itunes:subtitle"));
//			map.put("title", GetXMLMethods.getValue(e, "title"));
//			map.put("description", GetXMLMethods.getValue(e, "description"));
//			map.put("link", GetXMLMethods.getValue(e, "link"));
//			mylist.add(map);
//		}
	}
	
	protected void onPostExecute(Boolean result) {
//		setContentView(R.layout.catalogue);
	}
	
	public static ArrayList<HashMap<String, String>> getPlayerStatus() {
		return mylist;
	}	

}