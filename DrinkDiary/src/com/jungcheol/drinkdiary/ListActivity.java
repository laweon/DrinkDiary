package com.jungcheol.drinkdiary;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

public class ListActivity extends Activity {
	
	private DbOpenHelper dbOpenHelper;
	private Cursor cursor;
	private InfoClass infoclass;
	private ArrayList<InfoClass> infoArray; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		
		dbOpenHelper = new DbOpenHelper(this);
		dbOpenHelper.open();
		
		cursor = dbOpenHelper.getAllColumns();
		
		while (cursor.moveToNext()) {
			/*
			infoclass = new InfoClass(
					cursor.getString(cursor.getColumnIndex("place")), 
					cursor.getString(cursor.getColumnIndex("people")), 
					cursor.getString(cursor.getColumnIndex("beer")), 
					cursor.getString(cursor.getColumnIndex("soju")), 
					cursor.getString(cursor.getColumnIndex("malgoli")), 
					cursor.getString(cursor.getColumnIndex("whisky")), 
					cursor.getString(cursor.getColumnIndex("etc"))
					);
			
			infoArray.add(infoclass);
			*/
			Log.d("", "place: " + cursor.getString(cursor.getColumnIndex("place")));
			Log.d("", "people: " + cursor.getString(cursor.getColumnIndex("people")));
			Log.d("", "beer: " + cursor.getString(cursor.getColumnIndex("beer")));
			Log.d("", "soju: " + cursor.getString(cursor.getColumnIndex("soju")));
			Log.d("", "malgoli: " + cursor.getString(cursor.getColumnIndex("malgoli")));
			Log.d("", "whisky: " + cursor.getString(cursor.getColumnIndex("whisky")));
			Log.d("", "etc: " + cursor.getString(cursor.getColumnIndex("etc")));
			
		}
		
		cursor.close();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		
		dbOpenHelper.close();
		super.onDestroy();
	}
	
}
