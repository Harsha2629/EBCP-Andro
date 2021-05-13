package com.example.ebcp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class PitManagement extends AppCompatActivity {

    TextInputEditText textInputEditTextLocation, textInputEditTextPlantName;
    Button btn;
    TextView txtusername;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USERNAME = "myprefusername";
    private static final String KEY_PLANTNAME = "myprefplantname";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit_management);

        txtusername = findViewById(R.id.text_name);
//grab usename from login-->
       sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
       String usernamme = sharedPreferences.getString(KEY_USERNAME,null);

       if (usernamme != null){
           txtusername.setText("Hi!"+usernamme);
       }



        textInputEditTextLocation = findViewById(R.id.location);
        textInputEditTextPlantName = findViewById(R.id.plantName);
       btn = findViewById(R.id.btn_register);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String location, plantName;
                location = String.valueOf(textInputEditTextLocation.getText());
                plantName = String.valueOf(textInputEditTextPlantName.getText());

                //triggering shared preference
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_PLANTNAME,textInputEditTextPlantName.getText().toString());
                editor.apply();
                if (!location.equals("") && !plantName.equals("")) {


                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[3];
                            field[0] = "username";
                            field[1] = "location";
                            field[2] = "plantname";
                            //Creating array for data
                            String[] data = new String[3];
                            data[0] = usernamme;
                            data[1] = location;
                            data[2] = plantName;
                            PutData putData = new PutData("https://effa838f09c8.ngrok.io/phpmyadmin/se/plantmang.php", "POST", field, data);
                            if (putData.startPut()) {

                                if (putData.onComplete()) {
                                    String result = putData.getResult();

                                    if (result.equals("Pit Registration Success")){

                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        String plantName = textInputEditTextPlantName.getText().toString();
                                        Intent intent = new Intent(getApplicationContext(),PitRegister.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {

                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }
                else {

                    Toast.makeText(getApplicationContext(), "All Fields Required",Toast.LENGTH_SHORT).show();

                }

                Intent i=new Intent(PitManagement.this, PitRegister.class);
                startActivity(i);
                Context context = getApplicationContext();
                CharSequence text="Successful!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

    }
}