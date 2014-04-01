package com.jungcheol.drinkdiary;

import android.os.Bundle;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;
import android.widget.TabHost.TabSpec;

public class DrinkDiaryMainActivity extends TabActivity {
	
	static final String PREFS_NAME = "LoginPrefs";
	
	private static final String DATABASE_NAME = "user.db";
	private static final int DATABASE_VERSION = 1;
	private static final String USER_TABLE_NAME = "user";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drink_diary_main);
		
		Intent HomeIntent = new Intent().setClass(this, HomeActivity.class);
		Intent CreateIntent = new Intent(this, CreateActivity.class);
		Intent ListIntent = new Intent(this, ListActivity.class);
		Intent ProfileIntent = new Intent(this, ProfileActivity.class);
				
		TabHost tabHost = getTabHost();		
		
//		tabHost.getTabWidget().setDividerDrawable(R.drawable.ic_launcher);
		
		TabSpec HomeTabSpec = tabHost.newTabSpec("HomeTabSpec").setIndicator("", getResources().getDrawable(R.drawable.tabmenu_01_indicator));
		HomeTabSpec.setContent(HomeIntent);
		tabHost.addTab(HomeTabSpec);

		TabSpec CreateTabSpec = tabHost.newTabSpec("CreateTabSpec").setIndicator("", getResources().getDrawable(R.drawable.tabmenu_02_indicator));
		CreateTabSpec.setContent(CreateIntent);
		tabHost.addTab(CreateTabSpec);
		
		TabSpec ListTabSpec = tabHost.newTabSpec("ListTabSpec").setIndicator("", getResources().getDrawable(R.drawable.tabmenu_03_indicator));
		ListTabSpec.setContent(ListIntent);
		tabHost.addTab(ListTabSpec);
		
		TabSpec ProfileTabSpec = tabHost.newTabSpec("ProfileTabSpec").setIndicator("", getResources().getDrawable(R.drawable.tabmenu_04_indicator));
		ProfileTabSpec.setContent(ProfileIntent);
		tabHost.addTab(ProfileTabSpec);
		
		for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
			tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#f8f8ff"));
			tabHost.getTabWidget().setStripEnabled(false);
		}
		
//		tabHost.setCurrentTab(1);
		
	}
	
	private static class UserHelper extends SQLiteOpenHelper {

		public UserHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			
			String query = "CREATE TABLE " + USER_TABLE_NAME + 
					" (ID TEXT, PASS TEXT)";
			db.execSQL(query);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.drink_diary_main, menu);
		return true;
	}

}
