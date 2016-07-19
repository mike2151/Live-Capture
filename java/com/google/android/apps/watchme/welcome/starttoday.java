package com.google.android.apps.watchme.welcome;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.android.apps.watchme.MainActivity;
import com.google.android.apps.watchme.R;

/**
 * Created by Michael on 8/29/2015.
 */
public class starttoday extends Activity {
    private GestureDetector gestureDetector;
    public Button startbutton;
    public CheckBox donotshowagain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starttoday);

        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(R.color.app_blue)));
        bar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Live Capture</font>"));
        bar.setDisplayShowTitleEnabled(false);
        bar.setDisplayShowTitleEnabled(true);
        donotshowagain = (CheckBox) findViewById(R.id.checkBox);
        startbutton = (Button) findViewById(R.id.getstartedbutton);


        startbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //do tests for checkbox here
                if (donotshowagain.isChecked() == true) {
                    //now we are going to make it so welcome screen never shows up
                    SharedPreferences preferences = getApplicationContext().getSharedPreferences("Live_Capture", android.content.Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("SHOW_MAIN_SCREEN", false);
                    editor.commit();


                }

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(i, 0);



            }

        });




        gestureDetector = new GestureDetector(new SwipeGestureDetector());
    }

  /* ... */



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gestureDetector.onTouchEvent(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }

    private void onLeftSwipe() {
      //nothing
    }

    private void onRightSwipe() {
        setContentView(R.layout.socialaspect);
        Intent i = new Intent(getApplicationContext(), social.class);
        startActivityForResult(i, 0);
    }

    // Private class for gestures
    private class SwipeGestureDetector
            extends GestureDetector.SimpleOnGestureListener {
        // Swipe properties, you can change it to make the swipe
        // longer or shorter and speed
        private static final int SWIPE_MIN_DISTANCE = 120;
        private static final int SWIPE_MAX_OFF_PATH = 200;
        private static final int SWIPE_THRESHOLD_VELOCITY = 200;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2,
                               float velocityX, float velocityY) {
            try {
                float diffAbs = Math.abs(e1.getY() - e2.getY());
                float diff = e1.getX() - e2.getX();

                if (diffAbs > SWIPE_MAX_OFF_PATH)
                    return false;

                // Left swipe
                if (diff > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    starttoday.this.onLeftSwipe();

                    // Right swipe
                } else if (-diff > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    starttoday.this.onRightSwipe();
                }
            } catch (Exception e) {

            }
            return false;
        }
    }
}