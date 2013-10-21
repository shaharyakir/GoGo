package com.sashapps.gogo;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class WhatToDoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whattodo);

        // Replace TypeFace for all views
        ViewGroup root = (ViewGroup)findViewById(R.id.what_to_do_activity_layout);
        Utilities.setFont(this,root);

        // Extract country from intent
        String country = getIntent().getExtras().getString("country");
        TextView t = (TextView)findViewById(R.id.what_to_do_title);
        t.setText(t.getText() + " " + country + "?");
    }

    public void goBack(View v){
        this.finish();
        overridePendingTransition(R.anim.anim_in_back,R.anim.anim_out_back);
    }

    public void loadActivities(View v){
        ((Button)v).getText();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.what_to_do, menu);
        return true;
    }
    
}
