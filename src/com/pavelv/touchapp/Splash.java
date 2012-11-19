package com.pavelv.touchapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash);
		new AsyncHelp().execute();
//		Thread timer = new Thread() {
//			@Override
//			public void run() {
//				try {
//					sleep(1000);					
//				} catch (InterruptedException ie) {
//					ie.printStackTrace();
//				} finally {
					Intent i = new Intent("com.pavelv.touchapp.TOUCH");
					startActivity(i);
//				}
//			}
//		};
//		timer.start();		
	}

}
