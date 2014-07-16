package com.powerup.appshed.powerup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;

import com.google.android.glass.app.Card;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

/**
 * Created by someone on 16.07.2014.
 */
public class VideoAssistsActivity extends Activity {
    private Context context;

    /** {@link com.google.android.glass.widget.CardScrollView} to use as the main content view. */
    private CardScrollView mCardScroller;

    /** "Hello World!" {@link android.view.View} generated by {@link #buildView()}. */
    private View mView;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        context = this;
        mView = buildView();

        mCardScroller = new CardScrollView(this);
        mCardScroller.setAdapter(new CardScrollAdapter() {
            @Override
            public int getCount() {
                return 1;
            }

            @Override
            public Object getItem(int position) {
                return mView;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return mView;
            }

            @Override
            public int getPosition(Object item) {
                if (mView.equals(item)) {
                    return 0;
                }
                return AdapterView.INVALID_POSITION;
            }
        });
        mCardScroller.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                // am.playSoundEffect(Sounds.DISALLOWED);
                startActivity(new Intent(context, TakeAPictureAfter.class));
                //TODO play video on separate activity
                //startActivity(new Intent(context, PlayVideoActivity.class));
                //startActivity(new Intent(context, Custom_CameraActivity.class));

            }
        });
        setContentView(mCardScroller);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCardScroller.activate();
    }

    @Override
    protected void onPause() {
        mCardScroller.deactivate();
        super.onPause();
    }

    /**
     * Builds a Glass styled "Hello World!" view using the {@link com.google.android.glass.app.Card} class.
     */
    private View buildView() {
        Card card = new Card(this);
        card.setImageLayout(Card.ImageLayout.FULL);
        card.addImage(R.drawable.video_play);
        card.setFootnote("Play");
        return card.getView();
    }
}
