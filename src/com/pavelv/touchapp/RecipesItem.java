package com.pavelv.touchapp;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RecipesItem extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		Bundle extras = getIntent().getExtras();

		String title = "by " + extras.getString("title");
		String excerpt = extras.getString("excerpt");
		String description = extras.getString("description");
		String src = "";

		// outerloop:
		// for (int i = 0; i <= (description.length() - "src=".length()); i++) {
		// if (description.regionMatches(i, "src=", 0, "src=".length())) {
		//
		// for (int j = i+5; j <= (description.length() - ".jpg".length()); j++)
		// {
		//
		// src = src + description.charAt(j);
		//
		// if (description.regionMatches(j, ".jpg", 0, ".jpg".length())) {
		//
		// src = src + "jpg";
		// break outerloop;
		//
		// }
		// }
		// }
		// }

		setContentView(R.layout.recipes_item);

		TextView tv1 = (TextView) findViewById(R.id.textView1);

		tv1.setText(excerpt);

		TextView tv2 = (TextView) findViewById(R.id.textView2);

		tv2.setText(title);

		TextView tv3 = (TextView) findViewById(R.id.textView3);

		tv3.setText(Html.fromHtml(description, imgGetter, null));
		tv3.setMovementMethod(LinkMovementMethod.getInstance());

		// DownloadImageTask dit = (DownloadImageTask) new
		// DownloadImageTask((ImageView) findViewById(R.id.imageView1))
		// .execute(src);
	}

	private Html.ImageGetter imgGetter = new Html.ImageGetter() {

		@Override
		public Drawable getDrawable(String source) {
			URL url = null;
			Drawable bmp = null;
			try {
				url = new URL(source);
				URLConnection connection = url.openConnection();
				InputStream stream = connection.getInputStream();
				bmp = Drawable.createFromStream(stream, "src");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (Throwable t) {
				t.printStackTrace();
			}

			// Image size

			if (bmp != null) {
				bmp.setBounds(0, 0, bmp.getMinimumWidth() / 3,
						bmp.getMinimumHeight() / 3);
			}
			return bmp;
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
		RecipesGroup.group.back();
		return;
	}
}