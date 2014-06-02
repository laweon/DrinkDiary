package com.jungcheol.drinkdiary;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ListActivity extends Activity {
	
	private DatabaseHelper db;
	private InfoClass info;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		
		db = new DatabaseHelper(this);
		
		List<InfoClass> infoList = db.getAllInfo();
		Log.d("", "[ddLog] 11");
		for (int i = 0; i < infoList.size(); i++) {
			info = (InfoClass)infoList.get(i);
			Log.d("", "[ddLog] 22");
			Log.d("", "[ddLog] imgSrc : " + info.getImgSrc());
			Log.d("", "[ddLog] place : " + info.getPlace());
			Log.d("", "[ddLog] people : " + info.getPeople());
			Log.d("", "[ddLog] beer : " + info.getBeer());
			Log.d("", "[ddLog] soju : " + info.getSoju());
			Log.d("", "[ddLog] malgoli : " + info.getMalgoli());
			Log.d("", "[ddLog] whisky : " + info.getWhisky());
			Log.d("", "[ddLog] etc : " + info.getEtc());
			Log.d("", "[ddLog] ==");
			
		}
		

		
	}
/*
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		
		dbOpenHelper.close();
		super.onDestroy();
	}
*/	
}
