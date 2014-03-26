package com.jungcheol.drinkdiary;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class CameraView extends SurfaceView implements Callback {
	
	private SurfaceHolder holder;
	private Camera camera;

	public CameraView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CameraView(Context context, Camera camera) {
		super(context);
		// TODO Auto-generated constructor stub
		
		this.camera = camera;
		
		holder = getHolder();
		holder.addCallback(this);
	}

	public CameraView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

		if (holder.getSurface() == null) {
			return;
		}
		
		try {
			camera.stopPreview();
		} catch (Exception e) {
			Log.d("", "Error stopping camera preview: " + e.getMessage());
		}
		
		try {
			camera.setPreviewDisplay(holder);
			camera.startPreview();
			
		} catch (Exception e) {
			Log.d("", "Error starting camera preview: " + e.getMessage());
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
		try {
			camera.setPreviewDisplay(holder);
			camera.startPreview();
			
		} catch (Exception e) {
			Log.d("", "Error starting camera preview: " + e.getMessage());
		}

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

		camera.setPreviewCallback(null);
		camera.stopPreview();
		camera.release();
		camera = null;
		
	}

}
