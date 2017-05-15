package com.example.vincentg.project;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static android.support.v7.appcompat.R.attr.height;


/**
 * Created by vince on 2017-01-23.
 */

public class BrowseHouses extends AppCompatActivity{




    private boolean toggle = true;
    private GoogleApiClient mGoogleApiClient;

    public void startMaps(){
        /*
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);*/
    }


    @Override
    protected void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.activity_main);

        //maps init



        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); //NO PROBLEM !!!!
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));

        final Intent back = new Intent(this, MainActivity.class);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(back);

            }
        });


        final LinearLayout lm = (LinearLayout) findViewById(R.id.main);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams info = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        info.setMargins(40, 10, 20, 10);



        //fake data for demo
        String gator[] = {
                "RYckepungsvägen 24",
                "dragonvägen 25",
                "drakpungsvägen 67",
                "kakanvägen 32",
                "RYckepungsvägen 24",
                "dragonvägen 25",
                "drakpungsvägen 67",
                "kakanvägen 32",
                "RYckepungsvägen 24",
                "dragonvägen 25",
                "drakpungsvägen 67",
                "kakanvägen 32"
        };


        //generar bara 10 för demonstration
        //TODO:: add infinite loading feed and check if the houses are within a range hooking up to google maps
        for (int j = 1; j <= 10; j++) {
            // Create Linear Layout which will contain the vertical laout for when you tap on the item
            final LinearLayout lv = new LinearLayout(this);
            lv.setOrientation(LinearLayout.VERTICAL);

            //create Layout for the preview feed
            final LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);
            ll.setLayoutParams(params);

            ll.setPadding(0, 0, 0, 20);

            //Layout for Information when you tap the item
            final View child1 = LayoutInflater.from(this).inflate(
                    R.layout.bottom, null);
            final ImageView Exit = new ImageView(this);
            Exit.setImageResource(R.drawable.close);
            Exit.setLayoutParams(new ViewGroup.LayoutParams(200, 200));
            lv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    lv.setBackgroundResource(R.color.colorPrimaryDark);
                    //child1.startAnimation(slide_down);
                    child1.setVisibility(View.VISIBLE);
                    Exit.setVisibility(View.VISIBLE);
                    startMaps();
                    toggle = false;



                    Log.i("DEBUGGING", "DEBUGG MESSAGE");

                }

            });
            Exit.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    lv.setBackgroundResource(R.color.dark2);
                    child1.setVisibility(View.GONE);
                    Exit.setVisibility(View.GONE);


                    toggle = true;
                }
            });


            //IMAGE VIEW
            //adds Image View and appends it to the linear layout
            ImageView img = new ImageView(this);


            //Images from my Webserver neatly organized.
            //TODO:: Add Json Data Fetch for personal information aswell

            String IMAGE_URL = "http://178.62.215.164/Images/img" + j + ".jpg";

            ImageLoader mImageLoader = MySingleton.getInstance(this).getImageLoader();
            mImageLoader.get(IMAGE_URL, ImageLoader.getImageListener(img,
                    R.drawable.img4, R.drawable.img5));
            img.setLayoutParams(new ViewGroup.LayoutParams(200, 200));
            ll.addView(img);


            //Linearlayout to the right to order the information nicely
            LinearLayout ll2 = new LinearLayout(this);
            ll2.setOrientation(LinearLayout.VERTICAL);
            ll2.setLayoutParams(info);

            //Appends information.
            TextView Adress = new TextView(this);
            Adress.setText(gator[j - 1]);
            Adress.setTextColor(Color.WHITE);
            ll2.addView(Adress);

            TextView yta = new TextView(this);
            yta.setText("rum: 2:::::240m^2");
            yta.setTextSize(10);
            yta.setTextColor(getResources().getColor(R.color.lowtext));
            ll2.addView(yta);

            TextView kommun = new TextView(this);
            kommun.setText("Wallentuna");
            kommun.setTextSize(12);
            kommun.setTextColor(getResources().getColor(R.color.lowtext));
            ll2.addView(kommun);

            TextView contact = new TextView(this);
            contact.setText("Donald Trump");
            contact.setTextColor(getResources().getColor(R.color.lowtext));
            contact.setTextSize(12);
            ll2.addView(contact);

            ll.addView(ll2);

            Exit.setVisibility(View.GONE);
            ll.addView(Exit);
            lv.addView(ll);
            child1.setVisibility(View.GONE);
            lv.addView(child1);

            lm.addView(lv);

        }
    }

    //Optional functions to add later if we have time.
/*----------------------------------------------------------------*/
    //ToolbarHandling.
    /*----------------------------------------------------------------*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }
        if (id == R.id.action_search) {
            final Intent DetailedBrowseHouse = new Intent(this, DetailedBrowseHouse.class);
            startActivity(DetailedBrowseHouse);
            return true;
        }
        if (id == R.id.action_user) {
            final Intent changeUserDataActivity = new Intent(this, changeUserDataActivity.class);
            startActivity(changeUserDataActivity);
            return true;
        }
        if (id == R.id.action_maps) {
            Log.i("DEB","pressed google maps");
            final Intent maps = new Intent(this,googlemaps.class);
            startActivity(maps);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


/*
* Maps Handling Down here
* */


}
