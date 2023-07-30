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

public class Diarrhea extends AppCompatActivity implements View.OnClickListener {

    private String[] recommendationsLow = {
            "Keep your hands clean. The most common cause of acute bouts of diarrhea is infection from some sort of microorganism either viral, bacterial or parasitic.",
            "Maintain good hygiene practices.",
            "Wash your hands before every meal and after using the bathroom. You should also wash your hands after changing diapers, playing with pets, and handling money."
    };

    private String[] recommendationsMedium = {
            "Drink clean water. The tap water where you live may not taste very good, but virtually all municipal sources in the United States are disinfected with chlorine and other chemicals, so it's unlikely to transmit an infection to you.",
            "Regular physical activity can help you prevent, delay, or manage chronic diseases..",
            "If you are concerned with the quality of your tap water at home, buy a multi-stage reverse osmosis water filtration system."
    };

    private String[] recommendationsHigh = {
            "Doctor or Specialist consultation is a must.",
            "See your doctor if diarrhea is a common problem for you. The occasional bout of diarrhea is normal, but there may be a problem if you experience diarrhea on a regular basis.",
            "Ask your doctor about antibiotics. Antibiotics can both trigger and help prevent diarrhea, depending on the cause."
    };

    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7, checkBox8;
    private Button checkButton;
    private TextView resultTextView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diarrhea);

        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);
        checkBox5 = findViewById(R.id.checkBox5);
        checkBox6 = findViewById(R.id.checkBox6);
        checkBox7 = findViewById(R.id.checkBox7);
        checkBox8 = findViewById(R.id.checkBox8);
        checkButton = findViewById(R.id.checkButton);
        resultTextView = findViewById(R.id.resultTextView);
        ImageView LeftIcon = findViewById(R.id.left_icon);
        ImageView RightIcon = findViewById(R.id.right_icon);
        TextView title = findViewById(R.id.toolbar_title);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(100);

        LeftIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Diarrhea.this, Symptoms.class));
            }
        });
        RightIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Diarrhea.this, MainActivity.class));
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
        int totalSymptoms = 8; // Total number of checkboxes representing symptoms
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
                        "REFERENCES:\n-  Diarrhea. By Mayo Clinic Staff. Retrieved from (2021, August 18). https://www.mayoclinic.org/diseases-conditions/diarrhea/symptoms-causes/syc-20352241?fbclid=IwAR0dvVO7q7XJfoCe1NM_1Bp5PPyYBA1jtWHkM0zJ7Yk7IsJHV1Unz_c66O0\n"  + "\n" + "Youtube Reference:" + "\n"+"https://youtu.be/r4cFT9_kZIc")
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
