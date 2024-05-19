package com.example.tritonhacks24;

import static com.example.tritonhacks24.Adoption.aniType;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Customize extends AppCompatActivity {
    public int points = 100;

    private TextView pointsTextView;
    private ImageView accessoryImage;
    private ImageView animalImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);

        pointsTextView = findViewById(R.id.pointsTextView);
        accessoryImage = findViewById(R.id.accessoryImage);
        animalImage = findViewById(R.id.animalImage);

        Button hatButton = findViewById(R.id.hatButton);
        Button bowButton = findViewById(R.id.bowButton);
        Button crownButton = findViewById(R.id.crownButton);

        if (aniType.equals("turtle")) {
            animalImage.setImageResource(R.drawable.turtlejiya);
            setAccessoryMargin(R.dimen.turtle_trash_top_margin);
        }
        else{
            animalImage.setImageResource(R.drawable.elephantjiya);
            setAccessoryMargin(R.dimen.elephant_trash_top_margin);
        }

        hatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeAccessory(R.drawable.hat, 20);
            }
        });

        bowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeAccessory(R.drawable.bow, 30);
            }
        });

        crownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeAccessory(R.drawable.crown, 50);
            }
        });
    }

    private void changeAccessory(int accessoryResId, int cost) {
        if (points >= cost) {
            points -= cost;
            pointsTextView.setText("Points: " + points);

            // Set the new accessory image resource based on animal type
            if (aniType.equals("turtle")) {
                // If it's a turtle, set the turtle accessory image
                if (accessoryResId == R.drawable.hat) {
                    accessoryImage.setImageResource(R.drawable.hat);
                    setAccessoryMargin(R.dimen.turtle_hat_top_margin);
                } else if (accessoryResId == R.drawable.bow) {
                    accessoryImage.setImageResource(R.drawable.bow);
                    setAccessoryMargin(R.dimen.turtle_bow_top_margin);
                } else if (accessoryResId == R.drawable.crown) {
                    accessoryImage.setImageResource(R.drawable.crown);
                    setAccessoryMargin(R.dimen.turtle_crown_top_margin);
                } else {
                    accessoryImage.setImageResource(accessoryResId);
                    resetAccessoryMargin();
                }
            } else if (aniType.equals("elephant")){
                // Default to elephant if aniType is not turtle
                accessoryImage.setImageResource(accessoryResId);
                // Adjust margins for elephant accessories if needed
                if (accessoryResId == R.drawable.hat) {
                    setAccessoryMargin(R.dimen.elephant_hat_top_margin);
                } else if (accessoryResId == R.drawable.bow) {
                    setAccessoryMargin(R.dimen.elephant_bow_top_margin);
                } else if (accessoryResId == R.drawable.crown) {
                    setAccessoryMargin(R.dimen.elephant_crown_top_margin);
                } else {
                    accessoryImage.setImageResource(accessoryResId);
                    resetAccessoryMargin();
                }
            }
        } else {
            Toast.makeText(this, "You don't have enough points!", Toast.LENGTH_SHORT).show();
        }
    }

    // Helper method to set accessory image top margin dynamically
    private void setAccessoryMargin(int marginDimenId) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) accessoryImage.getLayoutParams();
        layoutParams.topMargin = getResources().getDimensionPixelSize(marginDimenId);
        accessoryImage.setLayoutParams(layoutParams);
    }

    // Helper method to reset accessory image margins
    private void resetAccessoryMargin() {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) accessoryImage.getLayoutParams();
        layoutParams.topMargin = 0; // Reset to default margin
        accessoryImage.setLayoutParams(layoutParams);
    }
}
