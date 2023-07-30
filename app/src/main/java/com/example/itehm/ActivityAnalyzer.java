package com.example.itehm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityAnalyzer extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextWeight, editTextHeight, editTextAge;
    private Button buttonCalculate;
    private TextView textViewResult;
    private Spinner spinnerGender, spinnerActivityLevel, spinnerMacronutrient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyzer);

        editTextWeight = findViewById(R.id.editTextWeight);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextAge = findViewById(R.id.editTextAge);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);

        spinnerGender = findViewById(R.id.spinnerGender);
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this, R.array.genders, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(genderAdapter);

        spinnerActivityLevel = findViewById(R.id.spinnerActivityLevel);
        ArrayAdapter<CharSequence> activityLevelAdapter = ArrayAdapter.createFromResource(this, R.array.activity_levels, android.R.layout.simple_spinner_item);
        activityLevelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerActivityLevel.setAdapter(activityLevelAdapter);

        spinnerMacronutrient = findViewById(R.id.spinnerMacronutrient);
        ArrayAdapter<CharSequence> macronutrientAdapter = ArrayAdapter.createFromResource(this, R.array.macronutrient_ratios, android.R.layout.simple_spinner_item);
        macronutrientAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMacronutrient.setAdapter(macronutrientAdapter);

        ImageView LeftIcon = findViewById(R.id.left_icon);
        ImageView RightIcon = findViewById(R.id.right_icon);
        TextView title = findViewById(R.id.toolbar_title);

        LeftIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ActivityAnalyzer.this, MainHealth.class));
            }
        });

        RightIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ActivityAnalyzer.this, MainActivity.class));
            }
        });

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput()) {
                    calculateTDEE();
                } else {
                    Toast.makeText(ActivityAnalyzer.this, "Please enter all the required information", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageView disclaimerIcon = findViewById(R.id.disclaimerIcon);
        disclaimerIcon.setOnClickListener(this);
    }

    private boolean validateInput() {
        String weight = editTextWeight.getText().toString();
        String height = editTextHeight.getText().toString();
        String age = editTextAge.getText().toString();

        return !TextUtils.isEmpty(weight) && !TextUtils.isEmpty(height) && !TextUtils.isEmpty(age);
    }

    private void calculateTDEE() {
        double weight = Double.parseDouble(editTextWeight.getText().toString());
        double height = Double.parseDouble(editTextHeight.getText().toString());
        int age = Integer.parseInt(editTextAge.getText().toString());
        double bmr = calculateBMR(weight, height, age);
        double tdee = calculateActivityFactor() * bmr;
        String result = String.format("TDEE: %.2f calories per day", tdee);
        String recommendation = generateRecommendation(tdee);
        textViewResult.setText(result + "\n\n" + recommendation);
    }

    private double calculateBMR(double weight, double height, int age) {
        double bmr;
        String gender = spinnerGender.getSelectedItem().toString();

        if (gender.equals("Male")) {
            bmr = (10 * weight) + (6.25 * height) - (5 * age) + 5;
        } else {
            bmr = (10 * weight) + (6.25 * height) - (5 * age) - 161;
        }
        return bmr;
    }

    private double calculateActivityFactor() {
        double activityFactor;
        String activityLevel = spinnerActivityLevel.getSelectedItem().toString();

        if (activityLevel.equals("Sedentary")) {
            activityFactor = 1.2;
        } else if (activityLevel.equals("Lightly Active")) {
            activityFactor = 1.375;
        } else if (activityLevel.equals("Moderately Active")) {
            activityFactor = 1.55;
        } else if (activityLevel.equals("Very Active")) {
            activityFactor = 1.725;
        } else {
            activityFactor = 1.9;
        }
        return activityFactor;
    }

    private String generateRecommendation(double tdee) {
        String recommendation;

        if (tdee < 1500) {
            recommendation = "Your TDEE is quite low. Consider increasing your calorie intake to meet your body's needs.";
        } else if (tdee >= 1500 && tdee < 2500) {
            recommendation = "Your TDEE is within the normal range. Maintain a balanced diet and exercise regularly for optimal health.";
        } else {
            recommendation = "Your TDEE is high. If you're looking to lose weight, consider creating a calorie deficit through a combination of diet and exercise.";
        }
        return "Recommendation: " + recommendation;
    }

    @Override
    public void onClick(View v) {
        showDisclaimerDialog();
    }

    private void showDisclaimerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Disclaimer and References")
                .setMessage("DISCLAIMER: HealthMate's TDEE calculator is provided for informational purposes only and should not be considered a substitute for professional medical advice, diagnosis, or treatment; always consult a qualified healthcare professional for personalized guidance.\n\n")

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
