package com.example.tritonhacks24;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        Button habitatBtn = findViewById(R.id.submitBtn);
        Button unityBtn = findViewById(R.id.unityBtn);
        Button challengeBtn = findViewById(R.id.challengeBtn);
        Button customizeBtn = findViewById(R.id.customizeBtn);


        habitatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open other view; create intent
                Intent aboutIntent = new Intent(Home2.this, Habitat.class);
                // start activity
                startActivity(aboutIntent);
            }
        });

        unityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open other view; create intent
                Intent aboutIntent = new Intent(Home2.this, Unity.class);
                // start activity
                startActivity(aboutIntent);
            }
        });

        challengeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open other view; create intent
                Intent aboutIntent = new Intent(Home2.this, Challenges.class);
                // start activity
                startActivity(aboutIntent);
            }
        });

        customizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open other view; create intent
                Intent aboutIntent = new Intent(Home2.this, Customize.class);
                // start activity
                startActivity(aboutIntent);
            }
        });


    }
}