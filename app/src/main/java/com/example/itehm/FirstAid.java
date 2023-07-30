package com.example.itehm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FirstAid extends AppCompatActivity {

    CardView cardburn;

    CardView cardbleed;

    CardView cardchoke;

    CardView cardbone;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_aid);
        cardburn = findViewById(R.id.burn);
        cardbleed = findViewById(R.id.bleed);
        cardchoke = findViewById(R.id.choke);
        cardbone = findViewById(R.id.bone);
        ImageView LeftIcon = findViewById(R.id.left_icon);
        ImageView RightIcon = findViewById(R.id.right_icon);
        TextView title = findViewById(R.id.toolbar_title);

        LeftIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(FirstAid.this, MainHealth.class));
            }
        });
        RightIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(FirstAid.this, MainActivity.class));
            }
        });

        cardburn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstAid.this, Burned.class));
            }
        });

        cardbleed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstAid.this, Bleeding.class));
            }
        });

        cardchoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstAid.this, Choking.class));
            }
        });

        cardbone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstAid.this, Bone.class));
            }
        });

    }
}