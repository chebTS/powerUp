package com.powerup.appshed.powerup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.google.android.glass.app.Card;
import com.google.android.glass.media.Sounds;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by someone on 16.07.2014.
 */
public class DirectionActivity extends Activity {
    private Context context;

    /** {@link com.google.android.glass.widget.CardScrollView} to use as the main content view. */
    private CardScrollView mCardScroller;

    /** "Hello World!" {@link android.view.View} generated by {@link #buildViews()}. */
    private List<View> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        context = this;
        views = buildViews();

        mCardScroller = new CardScrollView(this);
        mCardScroller.setAdapter(new CardScrollAdapter() {
            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public Object getItem(int position) {
                return views.get(position);
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return views.get(position);
            }

            @Override
            public int getPosition(Object item) {
                /*if (mView.equals(item)) {
                    return 0;
                }*/
                return AdapterView.INVALID_POSITION;
            }
        });
        mCardScroller.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Plays disallowed sound to indicate that TAP actions are not supported.
  //              AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//                am.playSoundEffect(Sounds.DISALLOWED);
                startActivity(new Intent(context, MatchElectronicActivity.class));

            }
        });

        //mCardScroller.setKeepScreenOn(true);
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
    private List<View> buildViews() {
        List<View> views = new ArrayList<View>();


        Card card = new Card(this);
        card.setImageLayout(Card.ImageLayout.LEFT);
        card.addImage(R.drawable.directions1);
        card.setText("James, walk 100 feet to the left");
//        card.setFootnote("Tap to continue");
        views.add(card.getView());

        card = new Card(this);
        card.setImageLayout(Card.ImageLayout.LEFT);
        card.addImage(R.drawable.directions2);
        card.setText("Great, just another 30 feet straight");
        card.setFootnote("Tap to continue");
        views.add(card.getView());


        return views;
    }

}
