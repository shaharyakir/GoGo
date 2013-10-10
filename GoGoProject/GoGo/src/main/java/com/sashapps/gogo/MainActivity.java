package com.sashapps.gogo;

import android.animation.ObjectAnimator;
import android.app.ActionBar;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Replace TypeFace for all views
        ViewGroup root = (ViewGroup)findViewById(R.id.mainLayout);
        Utilities.setFont(this,root);

        // Handle the image animation
        ImageView backgroundMovingImage = new ImageView(this);
        backgroundMovingImage.setImageResource(R.drawable.cloud);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(200, RelativeLayout.LayoutParams.WRAP_CONTENT);
        backgroundMovingImage.setX(0);
        Random r = new Random();
        backgroundMovingImage.setY(r.nextFloat() * root.getHeight());
        backgroundMovingImage.setLayoutParams(lp);
        backgroundMovingImage.setId(900);
        root.addView(backgroundMovingImage);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    
}

class AnimateTask extends AsyncTask{

    @Override
    protected Object doInBackground(Object[] objects) {
        return null;
    }

    protected void onPostExecute(Bitmap result){
        int i=0;
        while (i<100){
            i++;
            Paint paint = new Paint();
            Point size = new Point();

            //getWindowManager().getDefaultDisplay().getSize(size);
            //int dest = size.x;
            int dest = 700;

            backgroundMovingImage.animate().translationX(dest).setDuration(5000).start();
            backgroundMovingImage.setX(0);
            Random r = new Random();
            backgroundMovingImage.setY(r.nextFloat() * root.getHeight());
        }
    }
}