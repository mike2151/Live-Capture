package com.google.android.apps.watchme.welcome;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.google.android.apps.watchme.R;

/**
 * Created by Michael on 8/29/2015.
 */
public class social extends Activity {
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.socialaspect);

        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(R.color.app_blue)));
        bar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Live Capture</font>"));
        bar.setDisplayShowTitleEnabled(false);
        bar.setDisplayShowTitleEnabled(true);


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
        setContentView(R.layout.starttoday);
        Intent i = new Intent(getApplicationContext(), starttoday.class);
        startActivityForResult(i, 0);
    }

    private void onRightSwipe() {
        setContentView(R.layout.stream);
        Intent i = new Intent(getApplicationContext(), stream.class);
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
                    social.this.onLeftSwipe();

                    // Right swipe
                } else if (-diff > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    social.this.onRightSwipe();
                }
            } catch (Exception e) {

            }
            return false;
        }
    }
}