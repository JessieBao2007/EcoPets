package com.example.tritonhacks24;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Adoption extends AppCompatActivity {

    public static String aniType = "elephant";
    public static String animalName = "Bob";

    public static int points = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoption);

        Button submitBtn = findViewById(R.id.habitatBtn);
        Button elephantBtn = findViewById(R.id.elephantBtn);
        Button turtleBtn = findViewById(R.id.turtleBtn);
        TextInputEditText nameInput = findViewById(R.id.animalName);

        ImageView elephantImage = findViewById(R.id.elephantImg);
        ImageView turtleImage = findViewById(R.id.turtleImg);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animalName = nameInput.getText().toString();
                Toast.makeText(Adoption.this, "Pet Name: " + animalName, Toast.LENGTH_SHORT).show();

                // open other view; create intent
                Intent aboutIntent = new Intent(Adoption.this, Home2.class);
                // start activity
                startActivity(aboutIntent);
            }
        });

        elephantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aniType = "elephant"; //elephant
                elephantImage.setImageResource(R.drawable.elephant);
                turtleImage.setImageResource(R.drawable.turtlebw);
                Toast.makeText(Adoption.this, "Pet: " + aniType, Toast.LENGTH_SHORT).show();

            }
        });

        turtleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aniType = "turtle"; //turtle
                turtleImage.setImageResource(R.drawable.turtle);
                elephantImage.setImageResource(R.drawable.elephantbw);
                Toast.makeText(Adoption.this, "Pet: " + aniType, Toast.LENGTH_SHORT).show();

            }
        });

    }
}