package com.sashapps.gogo;

import android.animation.ObjectAnimator;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity {

    ViewGroup root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        root = (ViewGroup)findViewById(R.id.mainLayout);

        // Replace TypeFace for all views
        Utilities.setFont(this,root);

        // Handle the image animation
        View v = addImageToAnimate();
        animateMainScreen(v);

    }

    public void loginToGogo(View v){
        startActivity(new Intent("com.sashapps.gogo.LoginToGogoActivity"));
    }

    private View addImageToAnimate(){
        ImageView backgroundMovingImage = new ImageView(this);
        backgroundMovingImage.setImageResource(R.drawable.cloud);
        backgroundMovingImage.setId(View.generateViewId());
        //TODO:handle sizes with units
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(200, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin=-300;
        lp.bottomMargin=200;

        lp.addRule(RelativeLayout.BELOW,R.id.subTitle);
        backgroundMovingImage.setLayoutParams(lp);
        root.addView(backgroundMovingImage);

        return backgroundMovingImage;
    }

    private void animateMainScreen(View v){
        Paint paint = new Paint();
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        TranslateAnimation ta = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT, 0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 2.0f,
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.ABSOLUTE, 0f);
        ta.setRepeatCount(-1);
        ta.setRepeatMode(Animation.RESTART);
        ta.setInterpolator(new LinearInterpolator());
        ta.setDuration(8000);
        v.setAnimation(ta);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    
}
