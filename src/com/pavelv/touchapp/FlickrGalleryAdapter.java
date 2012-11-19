package com.pavelv.touchapp;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

class FlickrGalleryAdapter extends BaseAdapter {
	private Context context;
	private FlickrPhoto[] photos;

	LayoutInflater inflater;

	public FlickrGalleryAdapter(Context _context, FlickrPhoto[] _items) {
		context = _context;
		photos = _items;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return photos.length;
	}

	@Override
	public Object getItem(int position) {
		return photos[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView image = null;
		ViewHolder holder;
		
        if (convertView == null) {
            // first time this view has been created so inflate our layout
//            convertView = View.inflate(this, R.layout.my_grid_item, null);
         convertView = inflater.inflate(R.layout.row, null);  
   		 image = (ImageView) convertView.findViewById(R.id.ImageView);
   		 image.setImageBitmap(imageFromUrl(photos[position].makeURL()));
   		 
// 		 DownloadImageTask dit = (DownloadImageTask) new
// 		 DownloadImageTask((ImageView) convertView.findViewById(R.id.ImageView))
// 		 .execute(photos[position].makeURL());   		 
   		 
            holder = new ViewHolder();
            holder.thumbImage = (ImageView) convertView.findViewById(R.id.ImageView);
            convertView.setTag(holder); // set the View holder
        } else {
        	holder = (ViewHolder) convertView.getTag();
//        	image = (ImageView) convertView;
        }		

//		View photoRow = inflater.inflate(R.layout.row, null);
//
//		 ImageView image = (ImageView) photoRow.findViewById(R.id.ImageView);
//		 image.setImageBitmap(imageFromUrl(photos[position].makeURL()));
		 
		// DownloadImageTask dit = (DownloadImageTask) new
		// DownloadImageTask((ImageView) photoRow.findViewById(R.id.ImageView))
		// .execute(photos[position].makeURL());

//		Bitmap mIcon11 = null;
//		try {
//			InputStream in = new java.net.URL(photos[position].makeURL())
//					.openStream();
//			mIcon11 = BitmapFactory.decodeStream(in);
//		} catch (Exception e) {
//			Log.e("Error", e.getMessage());
//			e.printStackTrace();
//		}
//		ImageView image = (ImageView) photoRow.findViewById(R.id.ImageView);
//		image.setImageBitmap(mIcon11);

		return convertView;
	}
	
	public class ViewHolder {
	    ImageView thumbImage;
	}

	 public Bitmap imageFromUrl(String url) {
	
	 Bitmap bitmapImage;
	
	 URL imageUrl = null;
	 try {
	 imageUrl = new URL(url);
	 } catch (MalformedURLException e) {
	 e.printStackTrace();
	 }
	 try {
	 HttpURLConnection httpConnection = (HttpURLConnection) imageUrl
	 .openConnection();
	 httpConnection.setDoInput(true);
	 httpConnection.connect();
	 InputStream is = httpConnection.getInputStream();
	
	 bitmapImage = BitmapFactory.decodeStream(is);
	 } catch (IOException e) {
	 e.printStackTrace();
	 bitmapImage = Bitmap.createBitmap(50, 50,
	 Bitmap.Config.ALPHA_8);
	 }
	 return bitmapImage;
	 }
}