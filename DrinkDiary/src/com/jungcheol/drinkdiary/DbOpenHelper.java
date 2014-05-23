package com.jungcheol.drinkdiary;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "drink_diary.db";
	private static final String TABLE_NAME = "drink_diary";
	
	
	
	



		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub

			String query = "CREATE TABLE " + TABLE_NAME + 
					" (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
					+ "place TEXT, "
					+ "people TEXT, "
					+ "beer INTEGER, "
					+ "soju INTEGER, "
					+ "malgoli INTEGER, "
					+ "whisky INTEGER, "
					+ "etc INTEGER)";
			
			db.execSQL(query);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub

		}


	

	public void close() {
		db.close();
	}

	public long insert(String place, String people, String beer, String soju, String malgoli, String whisky, String etc) {
		ContentValues values = new ContentValues();
		values.put("place", place);
		values.put("people", people);
		values.put("beer", beer);
		values.put("soju", soju);
		values.put("malgoli", malgoli);
		values.put("whisky", whisky);
		values.put("etc", etc);
		
		return db.insert(TABLE_NAME, null, values);
		
	}
	
	public Cursor getAllColumns() {
		return db.query(TABLE_NAME, null, null, null, null, null, null);
		
	}
}


