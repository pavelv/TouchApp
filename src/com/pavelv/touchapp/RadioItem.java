package com.pavelv.touchapp;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RadioItem extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		Bundle extras = getIntent().getExtras();

		String subtitle = extras.getString("itunes:subtitle");
		String title = extras.getString("title");
		String description = extras.getString("description");
		String link = extras.getString("link");
		String image = link.replace("mp3", "jpg");
		image = image.replace("radio/", "radio/images/");

		System.out.println(image);

		setContentView(R.layout.radio_item);

		TextView tv1 = (TextView) findViewById(R.id.textView1);

		tv1.setText(subtitle);

		TextView tv2 = (TextView) findViewById(R.id.textView2);

		tv2.setText(title);

		DownloadImageTask dit = (DownloadImageTask) new DownloadImageTask(
				(ImageView) findViewById(R.id.imageView1)).execute(image);

		TextView tv3 = (TextView) findViewById(R.id.textView3);

		tv3.setText(description);
		// tv3.setMovementMethod(LinkMovementMethod.getInstance());
		tv3.setText(description);

		TextView tv4 = (TextView) findViewById(R.id.textView4);
		tv4.setMovementMethod(LinkMovementMethod.getInstance());
		tv4.setText(link);
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
	public void onBackPressed() {
		CatalogueGroup.group.back();
		return;
	}

}