package com.example.ebcp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    TextView txtfullname, txtemail;
    Button btn;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_FULLNAME = "mypreffullname";
    private static final String KEY_EMAIL = "myprefemail";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        txtfullname = findViewById(R.id.textfullname);
        txtemail = findViewById(R.id.textemail);
        btn = findViewById(R.id.btn_update);

        //grab usename from login-->
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String usernamme = sharedPreferences.getString(KEY_FULLNAME,null);
        String emmail = sharedPreferences.getString(KEY_EMAIL,null);

        if (usernamme != null && emmail != null){
            txtfullname.setText("Hi!"+usernamme);
            txtemail.setText(emmail);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });

    }
}