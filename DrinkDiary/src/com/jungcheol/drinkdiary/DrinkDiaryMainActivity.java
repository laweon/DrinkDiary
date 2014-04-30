package com.jungcheol.drinkdiary;

import android.os.Bundle;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;
import android.widget.TabHost.TabSpec;

public class DrinkDiaryMainActivity extends TabActivity implements OnTabChangeListener {
	
	TabHost tabHost;
	
	static final String PREFS_NAME = "LoginPrefs";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drink_diary_main);
		
		Intent HomeIntent = new Intent().setClass(this, HomeActivity.class);
		Intent CreateIntent = new Intent(this, CreateActivity.class);
		Intent ListIntent = new Intent(this, ListActivity.class);
		Intent ProfileIntent = new Intent(this, ProfileActivity.class);
				
		tabHost = getTabHost();		
		tabHost.setOnTabChangedListener(this);
		
//		tabHost.getTabWidget().setStripEnabled(false);
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
			tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#e1e4ea"));
			
		}
		
//		tabHost.setCurrentTab(0);
//		tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#e1e4ea"));
		

		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.drink_diary_main, menu);
		return true;
	}

	@Override
	public void onTabChanged(String tabId) {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
			tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#e1e4ea"));
		}
		
		tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#797979"));
	}
	


}
