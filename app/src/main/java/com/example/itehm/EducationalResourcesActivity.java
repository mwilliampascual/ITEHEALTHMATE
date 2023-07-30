package com.example.itehm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;


import androidx.appcompat.app.AppCompatActivity;

public class EducationalResourcesActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView contentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_educational_resources);

        titleTextView = findViewById(R.id.titleTextView);
        contentTextView = findViewById(R.id.contentTextView);
        ImageView LeftIcon = findViewById(R.id.left_icon);
        ImageView RightIcon = findViewById(R.id.right_icon);

        LeftIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });
        RightIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(EducationalResourcesActivity.this, MainActivity.class));
            }
        });

        // Get the educational resources data from intent or other sources
        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");

        // Set the title and content in the TextViews
        titleTextView.setText(title);
        contentTextView.setText(content);
    }
    @Override
    public void onBackPressed() {
        // Check if the EducationalResourcesActivity is currently open
        if (getSupportFragmentManager().findFragmentByTag("EducationalResourcesFragment") != null) {
            // If it is, navigate back to the BloodPressure fragment without refreshing the result
            getSupportFragmentManager().popBackStack();
        } else {
            // Otherwise, perform the default back button behavior
            super.onBackPressed();
        }
    }

}
