package com.example.tritonhacks24;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Challenges extends AppCompatActivity {

    Button submit1 = findViewById(R.id.submitBtn);
    ConstraintLayout challenge1 = findViewById(R.id.challenge1);
    Button submit2 = findViewById(R.id.submitBtn2);
    Button submit3 = findViewById(R.id.submitBtn3);
    Button submit4 = findViewById(R.id.submitBtn4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges);

        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                challenge1.setVisibility(View.INVISIBLE);
            }
        });
    }
}