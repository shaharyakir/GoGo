package com.sashapps.gogo;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class LoginToGogoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
        setContentView(R.layout.activity_logintogogo);


        // Replace TypeFace for all views
        ViewGroup root = (ViewGroup)findViewById(R.id.loginToGogoLayout);
        Utilities.setFont(this,root);

    }

    
    public void goBack(View v){
        this.finish();
        overridePendingTransition(R.anim.anim_in_back,R.anim.anim_out_back);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_to_gogo, menu);
        return true;
    }
    
}
