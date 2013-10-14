package com.sashapps.gogo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class WhereToGoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheretogo);

        // Replace TypeFace for all views
        ViewGroup root = (ViewGroup)findViewById(R.id.whereToGoLayout);
        Utilities.setFont(this,root);
    }

    public void goBack(View v){
        this.finish();
        overridePendingTransition(R.anim.anim_in_back,R.anim.anim_out_back);
    }

    public void search(View v){
        DbOpenHelper db = new DbOpenHelper(getBaseContext());
        db.returnContinentByCountry("Spain");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.where_to_go, menu);
        return true;
    }
    
}
