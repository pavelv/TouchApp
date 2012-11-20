package com.pavelv.touchapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PhotosList extends Activity {

	String API_KEY = "dcb74491ec5cbe64deb98b18df1125a9";
	FlickrPhoto[] photos;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.photos);

		findViewById(R.id.loader).setVisibility(View.VISIBLE);
		findViewById(R.id.ListView).setVisibility(View.GONE);

		final Handler handler = new Handler();

		new Thread(new Runnable() {
			@Override
			public void run() {

				HttpClient httpclient = new DefaultHttpClient();
				HttpGet httpget = new HttpGet(
						"http://api.flickr.com/services/rest/?method=flickr.photosets.getPhotos&format=json&photoset_id=72157627750718372&api_key=dcb74491ec5cbe64deb98b18df1125a9&nojsoncallback=1");

				HttpResponse response;

				try {
					response = httpclient.execute(httpget);
					HttpEntity entity = response.getEntity();
					InputStream inputstream = entity.getContent();
					if (entity != null) {

						BufferedReader bufferedreader = new BufferedReader(
								new InputStreamReader(inputstream));
						StringBuilder stringbuilder = new StringBuilder();
						String currentline = null;
						try {
							while ((currentline = bufferedreader.readLine()) != null) {
								stringbuilder.append(currentline + "\n");
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
						String result = stringbuilder.toString();

						JSONObject thedata = new JSONObject(result);
						JSONObject thephotosdata = thedata.getJSONObject("photoset");
						JSONArray thephotodata = thephotosdata.getJSONArray("photo");
						photos = new FlickrPhoto[thephotodata.length()];
						for (int i = 0; i < thephotodata.length(); i++) {
							JSONObject photodata = thephotodata.getJSONObject(i);
							photos[i] = new FlickrPhoto(photodata.getString("id"),
									photodata.getString("secret"),
									photodata.getString("server"),
									photodata.getString("farm"));
						}
						inputstream.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				handler.post(new Runnable() {
					@Override
					public void run() {
						GridView gridView = (GridView) findViewById(R.id.ListView);
						gridView.setVerticalSpacing(0);
						gridView.setHorizontalSpacing(-20);
						gridView.setAdapter(new FlickrGalleryAdapter(PhotosList.this, photos));
						gridView.setOnItemClickListener(itemClickListener);

						findViewById(R.id.loader).setVisibility(View.GONE);
						findViewById(R.id.ListView).setVisibility(View.VISIBLE);
					}
				});

			}
		}).start();
	}

	private OnItemClickListener itemClickListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View v, int position,
				long id) {
			Intent i = new Intent(PhotosList.this, PhotoItem.class);
			i.putExtra("url", photos[position].makeURL());
			View view = PhotosGroup.group
					.getLocalActivityManager()
					.startActivity("PhotoItem",
							i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
					.getDecorView();

			PhotosGroup.group.replaceView(view);

		}
	};
	
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
	public void onBackPressed() {
		PhotosGroup.group.back();
		return;
	}
}