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

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.Objects;

public class CompostPit extends AppCompatActivity {
    Button viewbtn,viewbtn2,viewbtn3;
    TextView tv,tv2;
    TextInputEditText editText,editText2;


    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_PITNAME = "myprefpitname";
    private static final String KEY_USERNAME = "myprefusername";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compost_pit);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);



        editText = findViewById(R.id.plantclickname);
        editText2 = findViewById(R.id.pitclickname);
        tv = findViewById(R.id.plantview);
        tv2 = findViewById(R.id.pitview);
        viewbtn3 = findViewById(R.id.valuesbutton);
        viewbtn2 = findViewById(R.id.viewpit);
        String plantViewName=editText.toString();
        String pitViewName=editText.toString();
        viewbtn = findViewById(R.id.viewbutton);
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
                        data[0] = "boom";

                        PutData putData = new PutData("https://effa838f09c8.ngrok.io/phpmyadmin/se/Plantlist2.php", "POST", field, data);
                        if (putData.startPut()) {

                            if (putData.onComplete()) {
                                String result = putData.getResult();
                                if (result.equals("")) {
                                    Toast.makeText(getApplicationContext(), "No Registered Garbage Plants", Toast.LENGTH_SHORT).show();
                                } else {
                                    tv.setText(result.replaceAll(",", Objects.requireNonNull(System.getProperty("line.separator"))));

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

// VIEWING THE PIT NAMES

        viewbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//triggering shared preference
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_PITNAME,tv2.getText().toString());
                editor.apply();


                //Start ProgressBar first (Set visibility VISIBLE)
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //Starting Write and Read data with URL
                        //Creating array for parameters
                        String[] field = new String[1];
                        field[0] = "gpname";

                        //Creating array for data
                        String[] data = new String[1];
                        data[0] = "bangplant";

                        PutData putData = new PutData("https://effa838f09c8.ngrok.io/phpmyadmin/se/Compostlist2.php", "POST", field, data);
                        if (putData.startPut()) {

                            if (putData.onComplete()) {
                                String result = putData.getResult();
                                if(result.equals(""))
                                {
                                    Toast.makeText(getApplicationContext(), "No Registered Pits", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    tv2.setText(result.replaceAll(",", Objects.requireNonNull(System.getProperty("line.separator"))));
                                    }


                                    Toast.makeText(getApplicationContext(), "Displaying...", Toast.LENGTH_SHORT).show();

                                }
                            }
                            else {

                                Toast.makeText(getApplicationContext(),"result",Toast.LENGTH_SHORT).show();
                            }
                        }

                    //End Write and Read data with URL
                });
        }


        });

viewbtn3.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        //triggering shared preference
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_PITNAME,editText2.getText().toString());
        editor.apply();


        Intent intent = new Intent(getApplicationContext(),PitValues.class);
        startActivity(intent);
    }
});





    }
}