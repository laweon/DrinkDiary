package com.jungcheol.drinkdiary;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;


@SuppressLint("NewApi")
public class ListActivity extends Activity {
	
	private DatabaseHelper db;
	private InfoClass info;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		db = new DatabaseHelper(this);
		
		List<InfoClass> infoList = db.getAllInfo();
		Log.d("", "[ddLog] 11");
		for (int i = 0; i < infoList.size(); i++) {
			info = (InfoClass)infoList.get(i);
			Log.d("", "[ddLog] 22");
			Log.d("", "[ddLog] id : " + info.getId());
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
		
		Button sBtn = (Button)findViewById(R.id.loginBtn);
		sBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Log.d("", "[ddLog] sBtn");
				
				EditText idTxt = (EditText)findViewById(R.id.idTxt);
				EditText passTxt = (EditText)findViewById(R.id.passTxt);
				
				String body = "id=" + idTxt.getText().toString() + "&pass=" + passTxt.getText().toString();
				try {
					
//					URL u = new URL("http://192.168.0.138:8080/WebAppTest/login.jsp");
					URL u = new URL("http://192.168.0.138:8080/WebAppTest/setc.jsp");
					HttpURLConnection c = (HttpURLConnection)u.openConnection();
					c.setRequestMethod("POST");
					c.setDoInput(true);
					c.setDoOutput(true);
					c.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
					
					OutputStream os = c.getOutputStream();
					os.write(body.getBytes());
					os.flush();
					os.close();
				
					List<String> cookies = c.getHeaderFields().get("set-cookie");
					
					if (cookies != null) {
						for (String cookie : cookies) {
							Log.d("", "[ddLog] cookie : " + cookie.split(";\\s*")[0]);
							
						}
					}
				
					/*
					Map<String, List<String>> headers = c.getHeaderFields();
					Iterator<String> it = headers.keySet().iterator();
					while (it.hasNext()) {
						String key = it.next();
						List<String> values = headers.get(key);
						StringBuffer sb = new StringBuffer();
						
						for (int i = 0; i < values.size(); i++) {
							sb.append(";" + values.get(i));
						}
						
						Log.d("", "[ddLog] " + key + "=" + sb.toString().substring(1));
						
					}
*/
					/*
					BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
					String buf = null;
					
					Log.d("", "[ddLog] 9999");
					while ((buf = br.readLine()) != null) {
						Log.d("", "[ddLog] buf : " + buf);
						Log.d("", "[ddLog] 0000");
						
					}
					*/
										
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Log.d("", "[ddLog] Exception : " + e.toString());
					e.printStackTrace();
				} finally {
					
				}
			}
		});

		
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
