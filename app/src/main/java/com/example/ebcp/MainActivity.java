package com.example.ebcp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CardView cardProfile;
    CardView cardPitMang;
    CardView cardRecc;
    CardView cardCompost;
    CardView cardWasteMang;
    CardView cardAbout;
    CardView cardContact;
    CardView cardLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardProfile = findViewById(R.id.cardProfile);
        cardPitMang = findViewById(R.id.cardPitMang);
        cardRecc = findViewById(R.id.cardRecc);
        cardCompost = findViewById(R.id.cardCompost);
        cardWasteMang = findViewById(R.id.cardWasteMang);
        cardAbout = findViewById(R.id.cardAbout);
        cardContact = findViewById(R.id.cardContact);
        cardLogout = findViewById(R.id.cardLogout);
        cardLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        cardRecc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, Recommendation.class);
                startActivity(i);
            }
        });

        cardAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, AboutUs.class);
                startActivity(i);

            }
        });

        cardCompost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, CompostPit.class);
                startActivity(i);

            }
        });
        cardPitMang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, PitManagement.class);
                startActivity(i);

            }
        });

        cardWasteMang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, WasteManagement.class);
                startActivity(i);

            }
        });

        cardProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, Profile.class);
                startActivity(i);

            }
        });

        cardContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Our Executives will be in touch with you shortly!",Toast.LENGTH_SHORT).show();

            }
        });



    }
}