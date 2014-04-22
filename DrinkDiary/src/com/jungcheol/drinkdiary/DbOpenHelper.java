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

public class DbOpenHelper {
	
	private static final String DATABASE_NAME = "drink_diary.db";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME = "drink_diary";
	
	public static SQLiteDatabase db;
	private DatabaseHelper databaseHelper;
	private Context ctx;
	
	private class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub

			String query = "CREATE TABLE " + TABLE_NAME + 
					" (place TEXT, people TEXT, beer Text, soju TEXT, malgoli TEXT, whisky TEXT, etc TEXT)";
			db.execSQL(query);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub

		}

	}
	
	public DbOpenHelper(Context context) {
		// TODO Auto-generated constructor stub
		
		this.ctx = context;
	}
		
	public DbOpenHelper open() throws SQLException {
		databaseHelper = new DatabaseHelper(ctx, DATABASE_NAME, null, DATABASE_VERSION);
		db = databaseHelper.getWritableDatabase();
		
		return this;
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


