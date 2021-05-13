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
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;


public class PitRegister extends AppCompatActivity {




    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_PLANTNAME = "myprefplantname";


    TextView textplantname;
    TextInputEditText textInputEditTextPitname, textInputEditTextVolume, textInputEditTextLocation;
    CheckBox cbh, cbp, cbt, cbo, cba;
    Button btnregister;

//gpn from pitmanagement-->pitname, humidity temeperature oxygen pressure
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit_register);

        textInputEditTextPitname = findViewById(R.id.pitName);
        textInputEditTextVolume = findViewById(R.id.volume);

        boolean cbbh,cbbp,cbbt,cbbo,cbba;
        textplantname = findViewById(R.id.plantNamedisp);
        //grab usename from login-->
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String plantnamme = sharedPreferences.getString(KEY_PLANTNAME,null);

        if (plantnamme != null){
            textplantname.setText(plantnamme);
        }

        cbh = findViewById(R.id.humidity);
        cbp = findViewById(R.id.pressure);
        cbt = findViewById(R.id.temperature);
        cbo = findViewById(R.id.oxygen);
        cba = findViewById(R.id.airflow);



        btnregister = findViewById(R.id.btn_register);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pitname, volume, location;
                pitname = String.valueOf(textInputEditTextPitname.getText());
                volume = String.valueOf(textInputEditTextVolume.getText());

                String scbh,scbt,scbo,scbp,scba;
                //humidity

                if(cbh.isChecked()){
                    scbh = "Yes";
                } else {
                    scbh = "No";
                }
                //pressure

                if(cbp.isChecked()){
                    scbp = "Yes";
                } else {
                    scbp = "No";
                }
                //temperature

                if(cbt.isChecked()){
                    scbt = "Yes";
                } else {
                    scbt = "No";
                }
                //oxygen

                if(cbo.isChecked()){
                    scbo = "Yes";
                } else {
                    scbo = "No";
                }
                //airflowmeter

                if(cba.isChecked()){
                    scba = "Yes";
                } else {
                    scba = "No";
                }


                if (!pitname.equals("") && !volume.equals("")) {


                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                                     @Override
                                     public void run() {
                                         //Starting Write and Read data with URL
                                         //Creating array for parameters
                                         String[] field = new String[8];
                                         field[0] = "pitname";
                                         field[1] = "volume";
                                         field[2]="Pressuresensor";
                                         field[3]="Oxygensensor";
                                         field[4]="Airflowmeter";
                                         field[5]="Temperaturesensor";
                                         field[6]="Humiditysensor";
                                         field[7]="Garbageplantname";
                                         //Creating array for data
                                         String[] data = new String[8];
                                         data[0] = pitname;
                                         data[1] = volume;
                                         data[2] =scbp;
                                         data[3] =scbo;
                                         data[4] =scba;
                                         data[5] =scbt;
                                         data[6] =scbh;
                                         data[7] =plantnamme;


                                         PutData putData = new PutData("https://effa838f09c8.ngrok.io/phpmyadmin/se/pitmang.php", "POST", field, data);

                                         if (putData.startPut()) {

                                             if (putData.onComplete()) {
                                                 String result = putData.getResult();
                                                 System.out.println(result);
                                                 if (result.equals("Pit Registered")){
                                                     Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                                     Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                     startActivity(intent);
                                                     finish();
                                                 } else {

                                                     Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                                 }
                                             }
                                         }
                                     }
                                 });
                } else {

                    Toast.makeText(getApplicationContext(), "All Fields Required",Toast.LENGTH_SHORT).show();

                }
                Intent i=new Intent(PitRegister.this, MainActivity.class);
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