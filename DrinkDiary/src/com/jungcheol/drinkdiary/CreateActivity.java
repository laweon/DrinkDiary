package com.jungcheol.drinkdiary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateActivity extends Activity {
	
//	private Camera camera;
//	private CameraView preview;
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST = 100;
	private Uri fileUri;
	private Bitmap bitmap;
	private ImageView preview;
	
	private DatabaseHelper db;
	private Cursor cursor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.create);
				
		db = new DatabaseHelper(this);
		
//		dbOpenHelper.insert("1", "2", "3", "4", "5", "6", "7");
//		dbOpenHelper.insert("11", "22", "33", "44", "55", "66", "77");
		
		preview = (ImageView)findViewById(R.id.preview);
		
		preview.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				Intent actionViewIntent = new Intent(Intent.ACTION_VIEW);
				actionViewIntent.setDataAndType(fileUri, "image/jpg");
				startActivity(actionViewIntent);
			}
		});
		
//		Toast.makeText(CreateActivity.this, 
//	              "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show();
		
		ImageView captureAction = (ImageView)findViewById(R.id.captureImage);
//		Button captureAction = (Button)findViewById(R.id.captureAction);
		captureAction.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub			
//				captureAction();
				
				Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				
				fileUri = getOutputMediaFileUri();
				intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);	
			
				startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST);
			}
		});
		
		ImageView submitAction = (ImageView)findViewById(R.id.submitImage);
		submitAction.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Log.d("", "[ddLog] submitAction()");
				
				EditText placeTxt = (EditText)findViewById(R.id.placeTxt);
				EditText peopleTxt = (EditText)findViewById(R.id.peopleTxt);
				EditText beerTxt = (EditText)findViewById(R.id.beerTxt);
				EditText sojuTxt = (EditText)findViewById(R.id.sojuTxt);
				EditText malgoliTxt = (EditText)findViewById(R.id.malgoliTxt);
				EditText whiskyTxt = (EditText)findViewById(R.id.whiskyTxt);
				EditText etcTxt = (EditText)findViewById(R.id.etcTxt);
				
				InfoClass info = new InfoClass();
				
//				info.setImgSrc(fileUri.toString());
				info.setImgSrc("/g");
				info.setPlace(placeTxt.getText().toString());
				info.setPeople(peopleTxt.getText().toString());
				info.setBeer(Integer.parseInt(beerTxt.getText().toString()));
				info.setSoju(Integer.parseInt(sojuTxt.getText().toString()));
				info.setMalgoli(Integer.parseInt(malgoliTxt.getText().toString()));
				info.setWhisky(Integer.parseInt(whiskyTxt.getText().toString()));
				info.setEtc(Integer.parseInt(etcTxt.getText().toString()));
				
				/*
				InfoClass info = new InfoClass();
				
				info.setImgSrc("/aaaa");
				info.setPlace("a");
				info.setPeople("b");
				info.setBeer(1);
				info.setSoju(2);
				info.setMalgoli(3);
				info.setWhisky(4);
				info.setEtc(5);
				
				*/
				
				int result = db.insert(info);
			}
		});
	}
	/*
	@Override
		protected void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
			
			if (bitmap != null) {
				preview.setImageBitmap(bitmap);
			}
		}
	*/
	@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);
				
			switch (requestCode) {
			case CAPTURE_IMAGE_ACTIVITY_REQUEST:
				
				if (resultCode == Activity.RESULT_OK) {
								
					try {
						/*
						if (bitmap != null && !bitmap.isRecycled()) {
							bitmap.recycle();
							bitmap = null;
						}
						*/
											
						BitmapFactory.Options options = new BitmapFactory.Options();
						options.inSampleSize = 4;

						Bitmap src = BitmapFactory.decodeFile(fileUri.getPath(), options);
						Bitmap bMap = Bitmap.createScaledBitmap(src, 30, 30, true);
						
						preview.setImageBitmap(bMap);
					} catch (Exception e) {
						// TODO: handle exception
					
						Log.d("", "[ddLog] onActivityResult Exception : " + e.toString());
					}
				}
				
				break;

			default:
				break;
			}
		}


	
/*
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
*/
	
	private static File getOutputMediaFile() {
		File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "ddCameraApp");
	
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d("ddCameraApp", "failed to create directory");
				return null;
			}
		}
		
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		File mediaFile = new File(mediaStorageDir.getPath() + File.separator + timeStamp + ".jpg");
		
		return mediaFile;
	}
	
	private static Uri getOutputMediaFileUri() {
		return Uri.fromFile(getOutputMediaFile());
		
	}
	
/*	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		
		dbOpenHelper.close();
		super.onDestroy();
	}	
*/	
/*	
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
*/
	
}
