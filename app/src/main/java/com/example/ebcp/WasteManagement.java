package com.example.ebcp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import android.view.View;

import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;


import com.vishnusivadas.advanced_httpurlconnection.PutData;



public class WasteManagement extends AppCompatActivity {

 
    TextView lv;
    Button viewbtn;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USERNAME = "myprefusername";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waste_management);

        lv = findViewById(R.id.listview);

//grab usename from login-->
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String usernamme = sharedPreferences.getString(KEY_USERNAME,null);


        viewbtn = findViewById(R.id.button);
        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //Start ProgressBar first (Set visibility VISIBLE)
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //Starting Write and Read data with URL
                        //Creating array for parameters
                        String[] field = new String[1];
                        field[0] = "username";

                        //Creating array for data
                        String[] data = new String[1];
                        data[0] = usernamme;

                        PutData putData = new PutData("https://effa838f09c8.ngrok.io/phpmyadmin/se/Plantlist2.php", "POST", field, data);
                        if (putData.startPut()) {

                            if (putData.onComplete()) {
                                String result = putData.getResult();
                                if (result.equals("")) {
                                    Toast.makeText(getApplicationContext(), "No Registered Garbage Plants", Toast.LENGTH_SHORT).show();
                                } else {

                                        lv.setText(result.replaceAll(",", System.getProperty("line.separator")));



                                    Toast.makeText(getApplicationContext(), "Displaying...", Toast.LENGTH_SHORT).show();

                                }
                            }
                                else {

                                    Toast.makeText(getApplicationContext(),"result",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        //End Write and Read data with URL
                    });
            }


        });




    }
}