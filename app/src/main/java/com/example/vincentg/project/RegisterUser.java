package com.example.vincentg.project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 * Created by vince on 2017-01-26.
 */

public class RegisterUser extends AppCompatActivity {
    EditText USER_NAME,PASSWD,CON_PASS;
    String user_name, passwd, con_pass;
    Context ctx = this;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        final Context context = getApplicationContext();
        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Register User");
        setSupportActionBar(toolbar); //NO PROBLEM !!!!
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));


        final Intent back = new Intent(this,MainActivity.class);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(back);

            }
        });

        USER_NAME = (EditText) findViewById(R.id.username);
        PASSWD = (EditText) findViewById(R.id.passwd);
        CON_PASS = (EditText) findViewById(R.id.conpasswd);
        final Intent returntoLogin = new Intent(this, BrowseHouses.class);
        Button but = (Button) findViewById(R.id.reg);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_name = USER_NAME.getText().toString();
                passwd = PASSWD.getText().toString();
                con_pass = CON_PASS.getText().toString();
                if (user_name.matches("")) {
                    Toast.makeText(getBaseContext(), "You did not enter a username", Toast.LENGTH_SHORT).show();

                }else if(passwd.matches("")){
                    Toast.makeText(getBaseContext(), "You did not enter a password", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(!(passwd.equals(con_pass))){
                        final Toast toast = Toast.makeText(getBaseContext(), "password is not matching", Toast.LENGTH_LONG);
                        toast.show();
                    }else{
                        DatabaseOperations DB = new DatabaseOperations(ctx);

                        if (!DB.InsertLogin(DB,user_name,passwd)){
                            Toast.makeText(getBaseContext(),"a User with that username allready exists",Toast.LENGTH_LONG).show();
                            USER_NAME.setText("");
                            PASSWD.setText("");
                            CON_PASS.setText("");
                        }else{
                            Toast.makeText(getBaseContext(),"User successfully created",Toast.LENGTH_LONG).show();
                            startActivity(returntoLogin);
                        }

                    }
                }

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu, menu);
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
        if (id == R.id.action_maps) {
            Log.i("DEB","pressed google maps");
            final Intent maps = new Intent(this,googlemaps.class);
            startActivity(maps);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /**/

}
