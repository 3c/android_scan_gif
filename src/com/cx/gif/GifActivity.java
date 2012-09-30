package com.cx.gif;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.cx.gif.R;

public class GifActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gif);
		String path=getIntent().getStringExtra("path");
		setContentView(R.layout.gif);
		CustomGifView view=(CustomGifView) findViewById(R.id.gif_view);
		view.init(path);
	}
}
