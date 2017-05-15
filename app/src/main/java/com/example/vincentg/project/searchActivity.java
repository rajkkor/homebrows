package com.example.vincentg.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by vince on 2017-02-20.
 */

public class searchActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchactivity);




        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); //NO PROBLEM !!!!
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));

        final Intent back = new Intent(this,MainActivity.class);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(back);

            }
        });
    }

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
            final Intent DetailedBrowseHouse = new Intent(this,DetailedBrowseHouse.class);
            startActivity(DetailedBrowseHouse);
            return true;
        }
        if (id == R.id.action_user) {
            final Intent changeUserDataActivity = new Intent(this,changeUserDataActivity.class);
            startActivity(changeUserDataActivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /**/
}
