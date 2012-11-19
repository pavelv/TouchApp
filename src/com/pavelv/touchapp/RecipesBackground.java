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
public class RecipesBackground extends AsyncTask {
	
	static ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
//	static ArrayList<HashMap<String, String>> mylist2 = new ArrayList<HashMap<String, String>>();

	@Override
	protected ArrayList<HashMap<String, String>> doInBackground(Object... params) {
		
		String xml = GetXMLMethods
				.getXML("http://www.touchmusic.org.uk/recipebook/categories.xml");
		Document doc = GetXMLMethods.XMLfromString(xml);

		NodeList children = doc.getElementsByTagName("category");

		for (int i = 0; i < children.getLength(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			Element e = (Element) children.item(i);

			if ("left".compareToIgnoreCase(GetXMLMethods.getValue(e, "title")
					.toString()) != 0) {

				if ("conceptual, historical, literary etc"
						.compareToIgnoreCase(GetXMLMethods.getValue(e, "title")
								.toString()) == 0) {
					map.put("title", "Conceptual, Historical, Literary");
				} else {
					map.put("title", GetXMLMethods.getValue(e, "title"));
				}

				for (int j = 0; j <= (GetXMLMethods.getValue(e, "title")
						.toString().length() - "Vegetarian".length()); j++) {
					if (GetXMLMethods
							.getValue(e, "title")
							.toString()
							.regionMatches(j, "Vegetarian", 0,
									"Vegetarian".length())) {
						map.put("title", "Vegetarian & Vegan");
					}
				}

				for (int j = 0; j <= (GetXMLMethods.getValue(e, "title")
						.toString().length() - "Soups".length()); j++) {
					if (GetXMLMethods.getValue(e, "title").toString()
							.regionMatches(j, "Soups", 0, "Soups".length())) {
						map.put("title", "Soups & Starters");
					}
				}

				map.put("id", GetXMLMethods.getValue(e, "id"));
				mylist.add(map);

			}
		}
		
		return mylist;
	}
	
	@Override
	protected void onPreExecute() {
//		super.onPreExecute();
//		String xml = GetXMLMethods
//				.getXML("http://www.touchmusic.org.uk/recipebook/categories.xml");
//		Document doc = GetXMLMethods.XMLfromString(xml);
//
//		NodeList children = doc.getElementsByTagName("category");
//
//		for (int i = 0; i < children.getLength(); i++) {
//			HashMap<String, String> map = new HashMap<String, String>();
//			Element e = (Element) children.item(i);
//
//			if ("left".compareToIgnoreCase(GetXMLMethods.getValue(e, "title")
//					.toString()) != 0) {
//
//				if ("conceptual, historical, literary etc"
//						.compareToIgnoreCase(GetXMLMethods.getValue(e, "title")
//								.toString()) == 0) {
//					map.put("title", "Conceptual, Historical, Literary");
//				} else {
//					map.put("title", GetXMLMethods.getValue(e, "title"));
//				}
//
//				for (int j = 0; j <= (GetXMLMethods.getValue(e, "title")
//						.toString().length() - "Vegetarian".length()); j++) {
//					if (GetXMLMethods
//							.getValue(e, "title")
//							.toString()
//							.regionMatches(j, "Vegetarian", 0,
//									"Vegetarian".length())) {
//						map.put("title", "Vegetarian & Vegan");
//					}
//				}
//
//				for (int j = 0; j <= (GetXMLMethods.getValue(e, "title")
//						.toString().length() - "Soups".length()); j++) {
//					if (GetXMLMethods.getValue(e, "title").toString()
//							.regionMatches(j, "Soups", 0, "Soups".length())) {
//						map.put("title", "Soups & Starters");
//					}
//				}
//
//				map.put("id", GetXMLMethods.getValue(e, "id"));
//				mylist.add(map);
//
//			}
//		}
	}
	
	protected void onPostExecute(Boolean result) {
//		setContentView(R.layout.catalogue);
	}
	
	public static ArrayList<HashMap<String, String>> getPlayerStatus() {
		return mylist;
	}	

}