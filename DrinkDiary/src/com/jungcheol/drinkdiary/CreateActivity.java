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
	
	private DbOpenHelper dbOpenHelper;
	private Cursor cursor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.create);


		
		dbOpenHelper = new DbOpenHelper(this);
		dbOpenHelper.open();
		
		dbOpenHelper.insert("1", "2", "3", "4", "5", "6", "7");
		dbOpenHelper.insert("11", "22", "33", "44", "55", "66", "77");
		
		preview = (ImageView)findViewById(R.id.preview);
		
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 4;
//		Bitmap bMap = BitmapFactory.decodeFile("/storage/emulated/0/Pictures/MyCameraApp/1395649145889.jpg");
//		Bitmap bMap = BitmapFactory.decodeFile("/storage/emulated/0/Pictures/NAVER_LINE/1395649145889.jpg");
		Bitmap bMap = BitmapFactory.decodeFile("/storage/emulated/0/Pictures/NAVER_LINE/IMG_aaaa.jpg", options);
		Bitmap bMap2 = Bitmap.createScaledBitmap(bMap, 300, 300, true);
		Log.d("", "[ddLog] 11" + bMap.toString());
//		Bitmap bMap = BitmapFactory.decodeFile("/storage/emulated/0/Pictures/NAVER_LINE/jc.png", options);
//		Bitmap bMap = BitmapFactory.decodeFile("/storage/emulated/0/Pictures/Private/20131204_142401.jpg");
//		Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.main_visual);
//		preview.setImageBitmap(bMap);
		
		preview.setImageBitmap(bMap2);
		
		

//		Log.d("", "[ddLog] 66" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
		/*
		try {
			Log.d("", "[ddLog] 11");
			File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MyCameraApp");	
			Log.d("", "[ddLog] 22");
			File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "IMG_aaaa.jpg");
			Log.d("", "[ddLog] 33" + mediaStorageDir.getPath());
			FileInputStream input_stream = new FileInputStream(file);
			Log.d("", "[ddLog] 44");
			preview.setImageDrawable(Drawable.createFromStream(input_stream, "IMG_aaaa.jpg"));
			Log.d("", "[ddLog] 55");
			input_stream.close();
			Log.d("", "[ddLog] 66");
		} catch (Exception e) {
			// TODO: handle exception
			
			Log.d("", "[ddLog] 99");
			
		}
		*/
		Toast.makeText(CreateActivity.this, 
	              "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show();
		
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
//			super.onActivityResult(requestCode, resultCode, data);
			
		Log.d("", "[ddLog] requestCode : " + requestCode);
		Log.d("", "[ddLog] resultCode : " + Integer.toString(resultCode));
		Log.d("", "[ddLog] RESULT_OK : " + Integer.toString(RESULT_OK));
		

		
		try {
			if (data == null) {
				Log.d("", "[ddLog] !data");
			} else {
				Log.d("", "[ddLog] data");
			}

		} catch (Exception e) {
			// TODO: handle exception
			Log.d("", "[ddLog] Exception");
			e.toString();
		}
		
        Bundle extras = data.getExtras();
        if (extras.keySet().contains("data") ){
        	Log.d("", "[ddLog] 11");
        	/*
            BitmapFactory.Options options = new BitmapFactory.Options();
            thumbnail = (Bitmap) extras.get("data");
            if (thumbnail != null) {
                Toast.makeText(this, "YES Thumbnail", Toast.LENGTH_LONG).show();
                BitmapFactory.Options opt = new BitmapFactory.Options();
                thumbnail = (Bitmap) extras.get("data");
                imageCam(thumbnail);
            }
            */
        } else {
        	Log.d("", "[ddLog] 22");
        	/*
            Uri imageURI = getIntent().getData();
            ImageView imageview = (ImageView)findViewById(R.id.imageView1);
            imageview.setImageURI(imageURI);

            if(imageURI != null){
                Toast.makeText(this, "YES Image Uri", Toast.LENGTH_LONG).show();
            }
            */
        }
        super.onActivityResult(requestCode, resultCode, data);  

		
			Log.d("", "[ddLog] 11111111111111111111");
			
			switch (requestCode) {
			case CAPTURE_IMAGE_ACTIVITY_REQUEST:
				
				Log.d("", "[ddLog] 222222222222222222222222");
				if (resultCode == Activity.RESULT_OK) {
					
					Log.d("", "[ddLog] 33333333333333333333333");
					
					try {
						/*
						if (bitmap != null && !bitmap.isRecycled()) {
							bitmap.recycle();
							bitmap = null;
						}
						*/
						
//						Bundle extra = data.getExtras();
						
						
						
						Log.d("", "[ddLog] 444444444444444444");
//						Log.d("", "[ddLog] MediaStore.EXTRA_OUTPUT : " + getRealPathFromURI(curImageURI));
						Log.d("", "[ddLog] 55555555555555555");
						
						File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MyCameraApp");					
						File mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_aaaa.jpg");
						FileInputStream input_stream = new FileInputStream(mediaFile);
						preview.setImageDrawable(Drawable.createFromStream(input_stream, "image.jpg"));
						Log.d("", "[ddLog] 6666666666");
//						bitmap = (Bitmap)data.getExtras().get(MediaStore.EXTRA_OUTPUT);
//						bitmap = (Bitmap)data.getExtras().get("data");
//						preview.setImageBitmap(bitmap);						
//						preview.setScaleType(ImageView.ScaleType.FIT_XY);

					} catch (Exception e) {
						// TODO: handle exception
						
						Log.d("", "[ddLog] 88888888888888" + e.toString());
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
		File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MyCameraApp");
	
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d("MyCameraApp", "failed to create directory");
				return null;
			}
		}
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//		File mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
		File mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_aaaa.jpg");
		
		
		
		return mediaFile;
	}
	
	private static Uri getOutputMediaFileUri() {
		return Uri.fromFile(getOutputMediaFile());
		
	}
	
	public String getRealPathFromURI(Uri contentUri) {
		String[] proj = {MediaStore.Images.Media.DATA};
		Cursor cursor = managedQuery(contentUri, proj, null, null, null);
		int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		
		return cursor.getString(columnIndex);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		
		dbOpenHelper.close();
		super.onDestroy();
	}	
	
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
