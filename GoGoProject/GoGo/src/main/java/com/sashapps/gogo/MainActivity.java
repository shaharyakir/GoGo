package com.sashapps.gogo;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Paint;
import android.graphics.Point;

import android.os.Bundle;
import android.app.Activity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.facebook.*;
import com.facebook.model.GraphUser;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    // ***************************
    // Facebook Intergration Part
    // ***************************

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("Facebook","onActivityResult()");
        Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
    }

    public void loginWithFacebook(View v){
        // start Facebook Login
        Session.openActiveSession(this, true, new Session.StatusCallback() {
            // callback when session changes state
            @Override
            public void call(Session session, SessionState state, Exception exception) {
                Log.d("Facebook", "in call()");
                if (session.isOpened()) {
                    Log.d("Facebook", "session opened()");
                    // make request to the /me API
                    //noinspection deprecation
                    Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {

                        // callback after Graph API response with user object
                        @Override
                        public void onCompleted(GraphUser user, Response response) {
                            if (user != null) {
                                Log.d("Facebook", user.getName());
                            }

                        }
                    });
                }
            }
        });
    }



}
