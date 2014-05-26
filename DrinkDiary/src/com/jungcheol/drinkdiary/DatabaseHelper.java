package com.jungcheol.drinkdiary;

import java.util.ArrayList;
import java.util.List;

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
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		String query = "CREATE TABLE " + TABLE_NAME + 
				" (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
				+ "imgUri TEXT, "
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

	public void insert(String imgUri, String place, String people, String beer, String soju, String malgoli, String whisky, String etc) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("imgUri", imgUri);
		values.put("place", place);
		values.put("people", people);
		values.put("beer", beer);
		values.put("soju", soju);
		values.put("malgoli", malgoli);
		values.put("whisky", whisky);
		values.put("etc", etc);
		
		db.insert(TABLE_NAME, null, values);
		db.close();
	}
	
	public List<InfoClass> getAllInfo() {
		SQLiteDatabase db = this.getReadableDatabase();
		List<InfoClass> infoList = new ArrayList<InfoClass>();
		String selectQuery = "SELECT id, imgUri, place, people, beer, soju, malgoli, whisky, etc FROM " + TABLE_NAME;
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		if (cursor.moveToFirst()) {
			do {
				InfoClass info = new InfoClass();
				info.setId(cursor.getInt(0));
				info.setImgUri(cursor.getString(1));
				info.setPlace(cursor.getString(2));
				info.setPeople(cursor.getString(3));
				info.setBeer(cursor.getInt(4));
				info.setSoju(cursor.getInt(5));
				info.setMalgoli(cursor.getInt(6));
				info.setWhisky(cursor.getInt(7));
				info.setEtc(cursor.getInt(8));
				
				infoList.add(info);
			} while (cursor.moveToNext());
		}
		
		return infoList;
	}
	
	public InfoClass getInfo(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT id, imgUri, place, people, beer, soju, malgoli, whisky, etc FROM " + TABLE_NAME + " WHERE id = ?";
		String[] args= {String.valueOf(id)};
		Cursor cursor = db.rawQuery(selectQuery, args);
		
		InfoClass info = null;
		
		if (cursor.moveToFirst()) {
			info = new InfoClass(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), 
					cursor.getInt(5), cursor.getInt(6), cursor.getInt(7), cursor.getInt(8));
		}
		
		return info;
	}
/*	
	public Cursor getAllColumns() {
		return db.query(TABLE_NAME, null, null, null, null, null, null);
		
	}
*/	
}


