package com.example.tritonhacks24;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Challenges extends AppCompatActivity {

    private Button submit1;
    private ConstraintLayout challenge1;
    private Button submit2;
    private ConstraintLayout challenge2;

    private Button submit3;
    private ConstraintLayout challenge3;

    private Button submit4;
    private ConstraintLayout challenge4;

    private TextView allDoneText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges);

        ImageView homeBtn = findViewById(R.id.homeBtn);

        // Initialize views here after setContentView
        submit1 = findViewById(R.id.submitBtn);
        challenge1 = findViewById(R.id.challenge1);

        submit2 = findViewById(R.id.submitBtn2);
        challenge2 = findViewById(R.id.challenge2);

        submit3 = findViewById(R.id.submitBtn3);
        challenge3 = findViewById(R.id.challenge3);

        submit4 = findViewById(R.id.habitatBtn);
        challenge4 = findViewById(R.id.challenge4);

        allDoneText = findViewById(R.id.allDoneText);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open other view; create intent
                Intent aboutIntent = new Intent(Challenges.this, Home2.class);
                // start activity
                startActivity(aboutIntent);
            }
        });

        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                challenge1.setVisibility(View.GONE);
                checkAllChallengesComplete();
                Adoption.points +=30;
            }
        });

        submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                challenge2.setVisibility(View.GONE);
                checkAllChallengesComplete();
                Adoption.points+=30;
            }
        });

        submit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                challenge3.setVisibility(View.GONE);
                checkAllChallengesComplete();
                Adoption.points+=20;
            }
        });

        submit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                challenge4.setVisibility(View.GONE);
                checkAllChallengesComplete();
                Adoption.points+=10;
            }
        });
    }
    private void checkAllChallengesComplete() {
        if (challenge1.getVisibility() == View.GONE &&
                challenge2.getVisibility() == View.GONE &&
                challenge3.getVisibility() == View.GONE &&
                challenge4.getVisibility() == View.GONE) {
            allDoneText.setVisibility(View.VISIBLE);
        }
    }
}
