package com.example.ebcp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.Objects;

public class PitValues extends AppCompatActivity {


    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_PITNAME = "myprefpitname";

    private Button backbtn;
    TextView tv1,tv2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit_values);

        tv1 = findViewById(R.id.pitnameview);
        tv2 = findViewById(R.id.tempdisplay);
        String temp = tv1.toString();
        //grab usename from login-->
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String pitnamme = sharedPreferences.getString(KEY_PITNAME, null);


        if (!temp.isEmpty()) {

            if (pitnamme != null) {
                tv1.setText(pitnamme);
            }


//Start ProgressBar first (Set visibility VISIBLE)
            String[] field = new String[1];
            field[0] = "cname";

            //Creating array for data
            String[] data = new String[1];
            data[0] = "Compost_pit_1";

            PutData putData = new PutData("https://effa838f09c8.ngrok.io/phpmyadmin/se/senvalist.php", "POST", field, data);
            if (putData.startPut()) {

                if (putData.onComplete()) {
                    String result = putData.getResult();
                    if (result.equals("")) {
                        Toast.makeText(getApplicationContext(), "No Registered Pits", Toast.LENGTH_SHORT).show();
                    } else {
                        tv2.setText(result.replaceAll(",", Objects.requireNonNull(System.getProperty("line.separator"))));
                    }

                    Toast.makeText(getApplicationContext(), "Displaying...", Toast.LENGTH_SHORT).show();

                }
            } else {

                Toast.makeText(getApplicationContext(), "result", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "Please enter a value", Toast.LENGTH_SHORT).show();
        }


        //End Write and Read data with URL


        backbtn = findViewById(R.id.backbtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

    }
    }
