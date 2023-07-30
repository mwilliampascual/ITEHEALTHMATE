package com.example.itehm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Symptoms extends AppCompatActivity {

    CardView sy_fever;
    CardView sy_flu;
    CardView sy_cough;
    CardView sy_diarrhea;

    CardView sy_stepthroat;
    CardView sy_chickenpox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);
        sy_fever = findViewById(R.id.sy_fever);
        sy_flu = findViewById(R.id.sy_flu);
        sy_cough = findViewById(R.id.sy_cough);
        sy_diarrhea = findViewById(R.id.sy_diarrhea);
        sy_stepthroat = findViewById(R.id.sy_stepthroat);
        sy_chickenpox = findViewById(R.id.sy_chickenpox);
        ImageView LeftIcon = findViewById(R.id.left_icon);
        ImageView RightIcon = findViewById(R.id.right_icon);
        TextView title = findViewById(R.id.toolbar_title);

        LeftIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Symptoms.this, MainActivity.class));
            }
        });
        RightIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Symptoms.this, MainActivity.class));
            }
        });
        sy_fever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Symptoms.this, Fever.class));
            }
        });

        sy_flu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Symptoms.this, Cold.class));
            }
        });

        sy_cough.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Symptoms.this, Cough.class));
            }
        });

        sy_diarrhea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Symptoms.this, Diarrhea.class));
            }
        });

        sy_stepthroat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Symptoms.this, Stepthroat.class));
            }
        });

        sy_chickenpox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Symptoms.this, Chickenpox.class));
            }
        });
    }
}