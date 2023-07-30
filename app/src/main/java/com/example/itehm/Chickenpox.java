package com.example.itehm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Chickenpox extends AppCompatActivity   implements View.OnClickListener {

    private String[] recommendationsLow = {
            "Get vaccinated for chickenpox. The vast majority of medical authorities believe that getting the chickenpox vaccine is the best way to prevent chickenpox.",
            "Keep your immune system strong. Like any viral, bacterial or fungal infection, true prevention depends on the proper functioning of your immune system.",
            "Drink plenty of fluids and rest."
    };

    private String[] recommendationsMedium = {
            "Avoid other children and adults with chickenpox. Chickenpox is highly contagious because it not only spreads directly from touching the blisters, but also through the air, and it can survive for short periods of time in mucus on various objects.",
            "Getting more sleep (or better quality sleep), eating more fresh fruit and vegetables, cutting down on refined sugars, reducing your alcohol consumption, quitting cigarette smoking, practicing good hygiene and light exercise are all proven ways to keep your immune strong.",
            "Disinfect your house and hands. Because chickenpox is so contagious and can live outside the body for short periods of time, you should be vigilant about disinfecting your house as a form of prevention if your child or other household member is infected."
    };

    private String[] recommendationsHigh = {
            "Talk to your doctor about antiviral drugs. In addition to preventative vaccination, drugs called antivirals are recommended for people who have a high risk of complications from chickenpox, or sometimes they are prescribed to shorten the duration and prevent the spread of the infection. ",
            "Seek medical attention for proper diagnosis and treatment.",
            "Follow the prescribed medication and care instructions."
    };

    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7;
    private Button checkButton;
    private TextView resultTextView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chickenpox);

        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);
        checkBox5 = findViewById(R.id.checkBox5);
        checkBox6 = findViewById(R.id.checkBox6);
        checkBox7 = findViewById(R.id.checkBox7);
        checkButton = findViewById(R.id.checkButton);
        resultTextView = findViewById(R.id.resultTextView);
        ImageView LeftIcon = findViewById(R.id.left_icon);
        ImageView RightIcon = findViewById(R.id.right_icon);
        TextView title = findViewById(R.id.toolbar_title);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(100);

        LeftIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Chickenpox.this, Symptoms.class));
            }
        });
        RightIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Chickenpox.this, MainActivity.class));
            }
        });

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePercentage();
            }
        });
        ImageView disclaimerIcon = findViewById(R.id.disclaimerIcon);
        disclaimerIcon.setOnClickListener(this);
    }

    private void calculatePercentage() {
        int totalSymptoms = 7; // Total number of checkboxes representing symptoms
        int checkedSymptoms = 0;

        if (checkBox1.isChecked()) {
            checkedSymptoms++;
        }
        if (checkBox2.isChecked()) {
            checkedSymptoms++;
        }
        if (checkBox3.isChecked()) {
            checkedSymptoms++;
        }
        if (checkBox4.isChecked()) {
            checkedSymptoms++;
        }
        if (checkBox5.isChecked()) {
            checkedSymptoms++;
        }
        if (checkBox6.isChecked()) {
            checkedSymptoms++;
        }
        if (checkBox7.isChecked()) {
            checkedSymptoms++;
        }

        int percentage = (checkedSymptoms * 100) / totalSymptoms;

        String result;
        String recommendation;

        if (checkedSymptoms == 0) {
            result = "\nNo cause for concern.";
            recommendation = getRandomRecommendation(recommendationsLow);
        } else if (checkedSymptoms <= 2) {
            result = "\nFurther observation required.";
            recommendation = getRandomRecommendation(recommendationsMedium);
        } else {
            result = "\nPlease consult a doctor.";
            recommendation = getRandomRecommendation(recommendationsHigh);
        }

        resultTextView.setText("Result: " + percentage + "% " + result + "\n\nInformation: " + recommendation);

        progressBar.setProgress(percentage);
        updateProgressBarColor(percentage);
    }

    private String getRandomRecommendation(String[] recommendations) {
        int index = new Random().nextInt(recommendations.length);
        return recommendations[index];
    }

    private void updateProgressBarColor(int percentage) {
        int greenColor = getResources().getColor(R.color.colorGreen);
        int yellowColor = getResources().getColor(R.color.colorYellow);
        int orangeColor = getResources().getColor(R.color.colorOrange);
        int redColor = getResources().getColor(R.color.colorRed);

        progressBar.setProgress(percentage);

        if (percentage <= 25) {
            progressBar.setProgressTintList(ColorStateList.valueOf(greenColor));
        } else if (percentage <= 50) {
            progressBar.setProgressTintList(ColorStateList.valueOf(yellowColor));
        } else if (percentage <= 75) {
            progressBar.setProgressTintList(ColorStateList.valueOf(orangeColor));
        } else {
            progressBar.setProgressTintList(ColorStateList.valueOf(redColor));
        }
    }
    @Override
    public void onClick(View v) {
        showDisclaimerDialog();
    }

    private void showDisclaimerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Disclaimer and References")
                .setMessage("DISCLAIMER: HealthMate's symptom checker is provided for informational purposes only and should not be considered a substitute for professional medical advice, diagnosis, or treatment; always consult a qualified healthcare professional for personalized guidance.\n\n" +
                        "REFERENCES:\n- Chicken pox. By Dr. Karthikeya T M. Retrieved from (2023, April 19). https://www.msn.com/en-ph/health/condition/chicken-pox/hp-chicken-pox?source=bing_condition&fbclid=IwAR1h7eP21hNEi5aSMt2LVWHNPgMlE3gBYK9EU3L9cYislULX_IkkJWGcx0Y\n"  + "\n" + "Youtube Reference:" + "\n"+"https://youtu.be/r4cFT9_kZIc")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
