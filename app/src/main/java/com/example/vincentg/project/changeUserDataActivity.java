package com.example.vincentg.project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by vince on 2017-02-20.
 */

public class changeUserDataActivity extends AppCompatActivity{
    Context CTX = this;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changeuserdata);
        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Change user Data");
        setSupportActionBar(toolbar); //NO PROBLEM !!!!
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));


        final Intent back = new Intent(this,BrowseHouses.class);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(back);

            }
        });
        Button delete = (Button)findViewById(R.id.delete);
        Button changepasswd = (Button)findViewById(R.id.button2);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Toast toast = Toast.makeText(getBaseContext(), "you can check out but you can never leave", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        final EditText PASSWD = (EditText) findViewById(R.id.passwd);
        final EditText CON_PASS = (EditText) findViewById(R.id.conpasswd);
        final Intent returntoLogin = new Intent(this, MainActivity.class);

        changepasswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String passwd = PASSWD.getText().toString().trim();
                String con_pass = CON_PASS.getText().toString().trim();
                if(!(passwd.equals(con_pass))){

                    final Toast toast = Toast.makeText(getBaseContext(), "password is not matching", Toast.LENGTH_LONG);
                    toast.show();
                }

                else{
                    DatabaseOperations DB = new DatabaseOperations(CTX);
                    DB.UpdatePassword(DB,passwd,"root");
                    startActivity(returntoLogin);

                }

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
