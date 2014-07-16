package com.powerup.appshed.powerup;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by someone on 16.07.2014.
 */
public class PlayVideoActivity extends Activity {
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videoView = (VideoView)findViewById(R.id.video);/*
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() +"/"+R.raw.video));
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();
        videoView.start();
*/

        //String path = "android.resource://" + getPackageName() + "/" + R.raw.video2;
/*
        String path = Uri.parse("android.resource://" + "com.powerup.appshed.powerup" +"/raw/" + R.raw.video2).toString();
        Log.i("TAG", path);
        videoView.setVideoURI(Uri.parse(path));
        videoView.start();
*/
/*
        Uri video = Uri.parse("android.resource://com.powerup.appshed.powerup/raw/sample");
        videoView.setVideoURI(video);
        videoView.requestFocus();
        videoView.start();*/
    }
}
