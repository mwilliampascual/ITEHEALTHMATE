package com.example.itehm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CardView cardhealth;
    CardView carddiseas;
    CardView cardfirstaid;
    CardView cardhotline;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardhealth = findViewById(R.id.cardhealth);
        carddiseas = findViewById(R.id.carddiseas);
        cardfirstaid = findViewById(R.id.cardfirstaid);
        cardhotline = findViewById(R.id.cardhotline);


        cardhealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainHealth.class));
                showToast("Health Clicked");
            }
        });
        carddiseas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Symptoms.class));
                showToast("Disease Clicked");
            }
        });
        cardfirstaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FirstAid.class));
                showToast("First Aid Clicked");
            }
        });

        cardhotline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Hotline.class));
                showToast("Clinic Clicked");
            }
        });

    }

    private void showToast(String message){

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}