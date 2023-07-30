package com.example.itehm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainHealth extends AppCompatActivity {

    CardView he_sleep;
    CardView he_activity;

    CardView he_blood_pressure;

    CardView he_diet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_health);
        he_sleep = findViewById(R.id.he_sleep);
        he_blood_pressure = findViewById(R.id.he_blood_pressure);
        he_activity = findViewById(R.id.he_activity);
        he_diet = findViewById(R.id.he_diet);
        ImageView LeftIcon = findViewById(R.id.left_icon);
        ImageView RightIcon = findViewById(R.id.right_icon);
        TextView title = findViewById(R.id.toolbar_title);

        LeftIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainHealth.this, MainActivity.class));
            }
        });
        RightIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainHealth.this, MainActivity.class));
            }
        });
        he_sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainHealth.this, SleepAnalyzer.class));
            }
        });

        he_blood_pressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainHealth.this, BloodPressure.class));
            }
        });

        he_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainHealth.this, ActivityAnalyzer.class));
            }
        });

        he_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainHealth.this, DietAnalyzer.class));
            }
        });
    }
}