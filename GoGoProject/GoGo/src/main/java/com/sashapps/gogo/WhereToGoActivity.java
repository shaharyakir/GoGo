package com.sashapps.gogo;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class WhereToGoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheretogo);

        // Replace TypeFace for all views
        ViewGroup root = (ViewGroup)findViewById(R.id.whereToGoLayout);
        Utilities.setFont(this,root);

        // Listview Listener
        ((ListView)findViewById(R.id.countryListView)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_LONG)
                        .show();
            }
        });

        // Handle the continent selection
        findViewById(R.id.imageLayout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent ev) {
                ImageView imageView = (ImageView) findViewById(R.id.world);
                final int action = ev.getAction();
                // (1)
                int nextImage = 0;
                final int evX = (int) ev.getX();
                final int evY = (int) ev.getY();

                String selectedContinent = "";

                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        //if (currentResource == R.drawable.world) {
                        //    nextImage = R.drawable.p2_ship_pressed;
                        //}
                        break;
                    case MotionEvent.ACTION_UP:

                        int touchColor = getHotspotColor(R.id.world_mask, evX, evY);
                        int tolerance = 50;
                        nextImage = R.drawable.world;


                        if (closeMatch(Color.RED, touchColor, tolerance)) {
                            nextImage = R.drawable.north_america;
                            selectedContinent = "North America";
                        } else if (closeMatch(Color.YELLOW, touchColor, tolerance)) {
                            nextImage = R.drawable.south_america;
                            selectedContinent = "South America";
                        } else if (closeMatch(Color.BLACK, touchColor, tolerance)) {
                            nextImage = R.drawable.australia;
                            selectedContinent = "Australia";
                        } else if (closeMatch(Color.WHITE, touchColor, tolerance)) {
                            nextImage = R.drawable.africa;
                            selectedContinent = "Africa";
                        } else if (closeMatch(Color.BLUE, touchColor, tolerance)) {
                            nextImage = R.drawable.europe;
                            selectedContinent = "Europe";
                        } else if (closeMatch(Color.GREEN, touchColor, tolerance)) {
                            nextImage = R.drawable.asia;
                            selectedContinent = "Asia";
                        }
                        break;
                } // end switch

                if (nextImage > 0) {
                    ((ImageView) imageView).setImageResource(nextImage);
                    imageView.setTag(nextImage);
                    setCountryList(selectedContinent);
                }
                return true;
            }
        });

    }



    public void goBack(View v){
        this.finish();
        overridePendingTransition(R.anim.anim_in_back,R.anim.anim_out_back);
    }

    public void setCountryList(String continent){

        if (!continent.isEmpty()){

            DbOpenHelper db = new DbOpenHelper(getBaseContext());

            ArrayList<String> s = db.returnCountriesByContinent(continent);

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,s);

            ((ListView)findViewById(R.id.countryListView)).setAdapter(arrayAdapter);

            db.close();
        }
        else{
            ((ListView)findViewById(R.id.countryListView)).setAdapter(null);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.where_to_go, menu);
        return true;
    }

    public int getHotspotColor (int hotspotId, int x, int y) {
        ImageView img = (ImageView) findViewById (hotspotId);
        img.setDrawingCacheEnabled(true);
        Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
        img.setDrawingCacheEnabled(false);
        return hotspots.getPixel(x, y);
    }

    public boolean closeMatch (int color1, int color2, int tolerance) {

        Log.d("Shahar","Searching for color:" + Integer.toString(color1));
        Log.d("Shahar","Diff R:" + Integer.toString((int) Math.abs (Color.red (color1) - Color.red (color2))));
        Log.d("Shahar","Diff G:" + Integer.toString((int) Math.abs (Color.green(color1) - Color.green(color2))));
        Log.d("Shahar","Diff B:" + Integer.toString((int) Math.abs (Color.blue(color1) - Color.blue (color2))));


        if ((int) Math.abs (Color.red (color1) - Color.red (color2)) > tolerance )
            return false;
        if ((int) Math.abs (Color.green(color1) - Color.green (color2)) > tolerance )
            return false;
        if ((int) Math.abs (Color.blue (color1) - Color.blue (color2)) > tolerance )
            return false;
        return true;
    }

    
}
