package com.jungcheol.drinkdiary;

import android.os.Bundle;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class DrinkDiaryMainActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drink_diary_main);
		
		Intent HomeIntent = new Intent().setClass(this, HomeActivity.class);
		Intent CreateIntent = new Intent(this, CreateActivity.class);
		Intent ListIntent = new Intent(this, ListActivity.class);
		Intent ProfileIntent = new Intent(this, ProfileActivity.class);
		
		TabHost tabHost = getTabHost();
		
		TabSpec HomeTabSpec = tabHost.newTabSpec("HomeTabSpec").setIndicator("홈");
		HomeTabSpec.setContent(HomeIntent);
		tabHost.addTab(HomeTabSpec);
		
		TabSpec CreateTabSpec = tabHost.newTabSpec("CreateTabSpec").setIndicator("만들기");
		CreateTabSpec.setContent(CreateIntent);
		tabHost.addTab(CreateTabSpec);
		
		TabSpec ListTabSpec = tabHost.newTabSpec("ListTabSpec").setIndicator("목록");
		ListTabSpec.setContent(ListIntent);
		tabHost.addTab(ListTabSpec);
		
		TabSpec ProfileTabSpec = tabHost.newTabSpec("ProfileTabSpec").setIndicator("프로파일");
		ProfileTabSpec.setContent(ProfileIntent);
		tabHost.addTab(ProfileTabSpec);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.drink_diary_main, menu);
		return true;
	}

}
