package com.pavelv.touchapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

public class PhotoItem extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		Bundle extras = getIntent().getExtras();
		String url = extras.getString("url");
		url = url.replace("_s.jpg", "_m.jpg");

		setContentView(R.layout.photo_item);

		DownloadImageTask dit = (DownloadImageTask) new DownloadImageTask(
				(ImageView) findViewById(R.id.imageView1)).execute(url);

	}

	@Override
	public void onBackPressed() {
		RecipesGroup.group.back();
		return;
	}

}