package com.example.ebcp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PitList extends AppCompatActivity {


    Button pit_view1;
    Button pit_back;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit_list);

        pit_back=findViewById(R.id.pit_back);
        pit_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CompostPit.class);
                startActivity(intent);
            }
        });
        pit_view1=findViewById(R.id.pit_view1);
        pit_view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PitValues.class);
                startActivity(intent);
            }
        });

    }
}