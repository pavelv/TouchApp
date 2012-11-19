package com.pavelv.touchapp;

import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

public class Stream {
	
	private static MediaPlayer mp = new MediaPlayer();
	
	static void playSong(String songPath) {
		try {

			mp.reset();
			mp.setDataSource(songPath);
			mp.setVolume(1.0f, 1.0f);
			mp.prepare();
			mp.start();
		    System.out.println("We are here");

			// Setup listener so next song starts automatically
			mp.setOnCompletionListener(new OnCompletionListener() {

				@Override
				public void onCompletion(MediaPlayer arg0) {
					// nextSong();
				}

			});

		} catch (IOException e) {
			// Log.v(getString(R.string.app_name), e.getMessage());
		}
	}
	
	public static boolean getPlayerStatus() {
		if (mp.isPlaying() == true) {
			System.out.println("PLAYING");
			return true;
		} else {
		return false;
		}
	}
	
	public static void setOnPause() {
		mp.pause();
	}
	
	public static void setResume() {
		mp.start();
	}

}
