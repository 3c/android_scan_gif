package com.cx.gif;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.util.AttributeSet;
import android.view.View;

class CustomGifView extends View {


	private Movie mMovie;
	private long mMovieStart;

	
	public CustomGifView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	public CustomGifView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public void init(String path){
		
		System.out.println("gif path ===" + path);
		try {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(path));
			ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
			byte[] temp = new byte[1024];
			int size = 0;
			while ((size = in.read(temp)) != -1) {
				out.write(temp, 0, size);
			}
			in.close();
			byte[] data = out.toByteArray();
			mMovie = Movie.decodeByteArray(data, 0, data.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void onDraw(Canvas canvas) {

		long now = android.os.SystemClock.uptimeMillis();
		if (mMovieStart == 0) { // first time
			mMovieStart = now;
		}
		if (mMovie != null) {
			int dur = mMovie.duration();
			if (dur == 0) {
				dur = 1000;
			}
			int relTime = (int) ((now - mMovieStart) % dur);
			mMovie.setTime(relTime);
			mMovie.draw(canvas, 0, 0);
			invalidate();
		}
	}

}
