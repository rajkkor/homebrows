package com.example.vincentg.project;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

    /*
    * @author Vincent Grenfeldt
    * @Created 2016
    * @
    * */

public class MainActivity extends AppCompatActivity {
    Context CTX = this;
    //Login parameters
    String username, passwd;
    EditText USERNAME,PASSWD;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        //Links to the main page for browsing houses.
        final Intent browse = new Intent(this, BrowseHouses.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Context context = getApplicationContext();
        Button but = (Button) findViewById(R.id.login);
        //Toast that displays error/successmessage on Login.
        final Toast toast = new Toast(context);
        USERNAME = (EditText) findViewById(R.id.username);
        PASSWD = (EditText) findViewById(R.id.passwd);
        USERNAME.setText("root");
        PASSWD.setText("123");

//Triggers if you press login button.
        but.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {


                //parses input to readable
                passwd = PASSWD.getText().toString().trim();
                username = USERNAME.getText().toString().trim();

                DatabaseOperations DOP = new DatabaseOperations(CTX);
                Cursor CR = DOP.RetrieveLoginData(DOP);
                DOP.fetchAllData(DOP);
                //DOP.ClearDataBase(DOP);
                CR.moveToFirst();
                boolean LoginStatus = false;
                String NAME = "";
                if(username.length()<1||passwd.length()<1){
                    Toast.makeText(getBaseContext(),"Must fill in all fields!",Toast.LENGTH_LONG);
                }else{
                    do{
                        if(username.equals(CR.getString(0)) &&(passwd.equals(CR.getString(1)))){
                            LoginStatus = true;
                            NAME = CR.getString(0);
                        }

                    }while (CR.moveToNext());
                    if(LoginStatus){
                        toast.makeText(getBaseContext(),"Login Successful!",Toast.LENGTH_LONG).show();
                        startActivity(browse);
                    }else{
                        toast.makeText(getBaseContext(),"username or password does not match", Toast.LENGTH_LONG).show();
                        PASSWD.setText("");
                        USERNAME.setText("");
                        Log.i("DEBUGGING","couldn't log in");
                    }
                }


            }


        });
    }
    //Triggers if you press register button.
    public void Register(View view) {

        final Intent RegisterUser = new Intent(this, RegisterUser.class);
        startActivity(RegisterUser);
    }



}


