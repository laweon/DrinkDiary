package com.jungcheol.drinkdiary;

import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;

public class CreateActivity extends Activity {
	
	private Camera camera;
	private CameraView preview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.create);
		
		Button captureAction = (Button)findViewById(R.id.captureAction);
		captureAction.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				captureAction();
			}
		});
	}
	
	public void captureAction() {
		camera = getCameraInstance();
		
		preview = new CameraView(this, camera);
		
		FrameLayout screen = (FrameLayout)findViewById(R.id.cameraPreView);
		screen.addView(preview);
		
		Button capture = (Button)findViewById(R.id.captureBtn);
		capture.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				camera.takePicture(null, null, picture);
			}
		});		
	}
	
	public static Camera getCameraInstance() {
		Camera c = null;
		
		try {
			c = Camera.open();
		} catch (Exception e) {
			Log.d("", "Error create Camera instance: " + e.getMessage());
		}
		
		return c;
	}

	private PictureCallback picture = new PictureCallback() {
		
		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			// TODO Auto-generated method stub
			
			FileOutputStream out = null;
			
			File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			
			String fileName = System.currentTimeMillis() + ".jpg";
			File picture = new File(path, fileName);
			
			try {
				
				out = new FileOutputStream(picture);
				out.write(data);
				out.close();
				
			} catch (Exception e) {
				
				Log.d("", "Faild file access: " + e.getMessage());

			}
		}
	};
}
