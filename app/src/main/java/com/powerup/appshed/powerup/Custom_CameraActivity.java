package com.powerup.appshed.powerup;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.powerup.appshed.powerup.view.CameraPreview;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by someone on 16.07.2014.
 */
public class Custom_CameraActivity extends Activity {
    private Camera mCamera;
    private CameraPreview mCameraPreview;
    private LinearLayout containerClick;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        mCamera = getCameraInstance();
        mCameraPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mCameraPreview);

        containerClick = (LinearLayout)findViewById(R.id.cotainer_click);
        containerClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCamera.takePicture(null, null, mPicture);
                finish();

            }
        });
        containerClick.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mCamera.takePicture(null, null, mPicture);
                finish();

                return false;
            }
        });


/*
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCamera.takePicture(null, null, mPicture);
                finish();
            }
        });*/
        /*preview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mCamera.takePicture(null, null, mPicture);
                finish();
                return false;
            }
        });*/
//        Button captureButton = (Button) findViewById(R.id.button_capture);
//        captureButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mCamera.takePicture(null, null, mPicture);
//            }
//        });
    }

    private Camera getCameraInstance() {
        Camera camera = null;
        try {
            camera = Camera.open();
        } catch (Exception e) {
            // cannot get camera or does not exist
        }
        return camera;
    }

    Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File pictureFile = getOutputMediaFile();
            if (pictureFile == null) {
                return;
            }
            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e) {

            } catch (IOException e) {
            }
        }
    };

    private static File getOutputMediaFile() {
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "MyCameraApp");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator
                + "IMG_" + timeStamp + ".jpg");

        return mediaFile;
    }
}
