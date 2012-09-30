package com.cx.gif;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.cx.gif.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {

	private static final int REQUESTCODE_FILE = 2;
	private ListView lv;
	private List<String> items = new ArrayList<String>();
	private List<String> pathlist = new ArrayList<String>();
	private final String rootpath = "/sdcard/";
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button btn = (Button) findViewById(R.id.btn_choose);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, FileActivity.class);
				startActivityForResult(intent, REQUESTCODE_FILE);
			}
		});
		
		getFileDir(rootpath);
		pathlist=items;
		lv=(ListView) findViewById(R.id.listview);
		lv.setAdapter(new FileAdapter(this, items, pathlist));
	
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				Intent intent=new Intent(MainActivity.this, GifActivity.class);
				intent.putExtra("path", items.get(position));
				startActivity(intent);
			}
		});		
		

	}
	
	
	
	private void getFileDir(String filepath) {
		File sfile = new File(filepath);
		File[] files = sfile.listFiles();
		if(files!=null&&files.length!=0){	
			for (File file : files) {
			if(file.isDirectory()){
				getFileDir(file.getAbsolutePath());
			}
			else{
				if(file.getName().endsWith(".gif")){
					items.add(file.getAbsolutePath());
				}
			}
		}}
		
	
	}
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUESTCODE_FILE && resultCode == REQUESTCODE_FILE && data != null) {
			String filepath = data.getStringExtra("filepath");
			if (filepath.length() > 0) {
				Intent intent=new Intent(MainActivity.this, GifActivity.class);
				intent.putExtra("path", filepath);
				startActivity(intent);
			}
		}
	}

}