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

public class Stepthroat extends AppCompatActivity  implements View.OnClickListener {

    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7, checkBox8, checkBox9;
    private Button checkButton;
    private TextView resultTextView;

    private ProgressBar progressBar;

    private String[] recommendationsLow = {
            "If you are around someone who has strep, wash your hands often. Don't drink from the same glass or use the same eating utensils. Don't share toothbrushes.",
            "Keep your immune system strong. Like any viral, bacterial or fungal infection, true prevention depends on the proper functioning of your immune system.",
            "Bacteria can live for a short time on doorknobs, water faucets, and other objects. It's a good idea to wash your hands regularly."
    };

    private String[] recommendationsMedium = {
            "Use tissues to rub your nose and cover your mouth.",
            "Washing your hands with soap and water is the best way to keep them clean. At times when you don’t have access to soap and water, use hand sanitizer instead."
    };

    private String[] recommendationsHigh = {
            "If you suspect you have strep throat, make an appointment with your doctor.",
            "Seek medical attention for proper diagnosis and treatment.",
            "Follow the prescribed medication and care instructions."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stepthroat);

        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);
        checkBox5 = findViewById(R.id.checkBox5);
        checkBox6 = findViewById(R.id.checkBox6);
        checkBox7 = findViewById(R.id.checkBox7);
        checkBox8 = findViewById(R.id.checkBox8);
        checkBox9 = findViewById(R.id.checkBox9);
        checkButton = findViewById(R.id.checkButton);
        resultTextView = findViewById(R.id.resultTextView);
        ImageView LeftIcon = findViewById(R.id.left_icon);
        ImageView RightIcon = findViewById(R.id.right_icon);
        TextView title = findViewById(R.id.toolbar_title);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(100);

        LeftIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Stepthroat.this, Symptoms.class));
            }
        });
        RightIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Stepthroat.this, MainActivity.class));
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
        int totalSymptoms = 9; // Total number of checkboxes representing symptoms
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
        if (checkBox8.isChecked()) {
            checkedSymptoms++;
        }
        if (checkBox9.isChecked()) {
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
                        "REFERENCES:\n- Strep throat. By Mayo Clinic Staff. Retrieved from (2022, November 30 ). https://www.mayoclinic.org/diseases-conditions/strep-throat/symptoms-causes/syc-20350338\n"  + "\n" + "Youtube Reference:" + "\n"+"https://youtu.be/r4cFT9_kZIc")
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
