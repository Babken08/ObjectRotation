package com.example.android.objectrotationapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private GestureDetector mGestureDetector;
    private ImageView img;
    TextView text;
    private static final String TAG = "ssssssssssssssss";

    private float radians;
    private double degree;
    private float imgParam;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
//        toolbar.setSubtitleTextColor(getColor(R.color.colorWhite));
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);


        text = (TextView) findViewById(R.id.gesture_text);
        img = (ImageView) findViewById(R.id.rotate_img);

        if(savedInstanceState != null) {
            title = savedInstanceState.getString("title", "");
            imgParam = savedInstanceState.getFloat("imgParam");
            img.setRotationY(imgParam);
        }else {
            title = (String) getSupportActionBar().getTitle();
        }
        getSupportActionBar().setTitle(title);



        AndroidGestureDetector androidGestureDetector = new AndroidGestureDetector();
        mGestureDetector = new GestureDetector(MainActivity.this, androidGestureDetector);
    }

    class AndroidGestureDetector implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.i(TAG, "onSingleTapConfirmed");
            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.i(TAG, "onDoubleTap");
            return false;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {

            Log.i(TAG, "onDoubleTapEvent");
            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            Log.i(TAG, "onDown");
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.i(TAG, "onShowPress");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.i(TAG, "onSingleTapUp");
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            if (e1.getX() < e2.getX()) {
                radians = e2.getX() - e1.getX();
            }

            if (e1.getX() > e2.getX()) {
                radians = e1.getX()-e2.getX();
            }
            if (e1.getY() < e2.getY()) {
                Log.i(TAG, "onScroll y1 < y2" + e1.getX() + "-" + e2.getX());
            }
            if (e1.getY() > e2.getY()) {
                Log.i(TAG, "onScroll y1 > y2" + e1.getX() + "-" + e2.getX());
            }


            imgParam = e2.getX();
            img.setRotationY(e2.getX());

            degree = Math.sin( Math.toRadians(radians));
            if(degree < 0.0) {
                degree = Math.abs(degree);
            }
            if(degree > 0.2 && degree < 0.25){
                title = "Degree =  "+"15 " + "%";
            }
            if(degree > 0.45 && degree < 0.65) {
                title = "Degree =  "+"30 " + "%";

            }
            if(degree > 0.65 && degree < 0.8) {
                title = "Degree =  "+"45 " + "%";
            }
            if(degree > 0.8 && degree < 0.9) {
                getSupportActionBar().setTitle("Degree =  "+"60 " + "%");
            }
            if(degree > 0.94 && degree < 1.2) {
                title = "Degree =  "+"90 " + "%";
            }
            if(degree >=0.0 && degree <= 0.2){
                title = "Degree =  "+"180 " + "%";
            }

            getSupportActionBar().setTitle(title);
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putFloat("imgParam", imgParam);
        outState.putString("title", title);
    }
}
