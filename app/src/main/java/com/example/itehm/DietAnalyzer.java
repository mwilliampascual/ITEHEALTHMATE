package com.example.itehm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class DietAnalyzer extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextCalories;
    private Button buttonCalculate;
    private TextView textViewResult;
    private TextView textViewHighFoods;
    private NotificationManager notificationManager;
    private static final String CHANNEL_ID = "diet_reminder_channel";
    private static final int NOTIFICATION_ID = 1;
    private String[] highProteinFoods = {"Chicken breast", "Shrimp", "Tofu", "Eggs", "Salmon"};
    private String[] highCarbFoods = {"Oats", "Brown rice", "Quinoa", "Sweet potatoes", "Beans"};
    private String[] highFatFoods = {"Avocado", "Almonds", "Olive oil", "Peanut", "Butter"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_analyzer);

        editTextCalories = findViewById(R.id.editTextCalories);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);
        textViewHighFoods = findViewById(R.id.textViewHighFoods);
        ImageView LeftIcon = findViewById(R.id.left_icon);
        ImageView RightIcon = findViewById(R.id.right_icon);
        TextView title = findViewById(R.id.toolbar_title);




        LeftIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(DietAnalyzer.this, MainHealth.class));
            }
        });
        RightIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(DietAnalyzer.this, MainActivity.class));
            }
        });

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateDiet();
            }
        });

        notificationManager = getSystemService(NotificationManager.class);

        ImageView disclaimerIcon = findViewById(R.id.disclaimerIcon);
        disclaimerIcon.setOnClickListener(this);
    }

    private void calculateDiet() {
        String caloriesText = editTextCalories.getText().toString();
        if (!caloriesText.isEmpty()) {
            int calories = Integer.parseInt(caloriesText);
            int protein = (int) (calories * 0.2);
            int carbs = (int) (calories * 0.5);
            int fats = (int) (calories * 0.3);

            StringBuilder resultBuilder = new StringBuilder();
            resultBuilder.append("Protein: ").append(protein).append("g\n");
            resultBuilder.append("Carbs: ").append(carbs).append("g\n");
            resultBuilder.append("Fats: ").append(fats).append("g");

            String result = resultBuilder.toString();

            if (calories < 1500) {
                result += "\n\nInformation: Consider increasing your calorie intake for optimal nutrition.";
            } else if (calories > 2500) {
                result += "\n\nInformation: Consider reducing your calorie intake for weight management.";
            } else {
                result += "\n\nInformation: Your calorie intake is within a healthy range.";
            }

            textViewResult.setText(result);

            StringBuilder highFoodsBuilder = new StringBuilder();
            highFoodsBuilder.append("High Protein Foods:\n");
            for (String food : highProteinFoods) {
                highFoodsBuilder.append("- ").append(food).append("\n");
            }
            highFoodsBuilder.append("\nHigh Carb Foods:\n");
            for (String food : highCarbFoods) {
                highFoodsBuilder.append("- ").append(food).append("\n");
            }
            highFoodsBuilder.append("\nHigh Fat Foods:\n");
            for (String food : highFatFoods) {
                highFoodsBuilder.append("- ").append(food).append("\n");
            }

            String highFoodsText = highFoodsBuilder.toString();
            textViewHighFoods.setText(highFoodsText);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            String finalResult = result;
            builder.setTitle("Set Reminder")
                    .setMessage("Do you want to set a reminder for this result?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            showNotification(finalResult);
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        } else {
            textViewResult.setText("");
        }
    }

    private void showNotification(String result) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Diet Consisting", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(this, DietAnalyzer.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        String notificationText = "This Reminder is equal to the entered calories intake!";

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_cart)
                .setContentTitle("Diet Consisting")
                .setContentText(notificationText)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(result))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    @Override
    public void onClick(View v) {
        showDisclaimerDialog();
    }

    private void showDisclaimerDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Disclaimer and References")
                .setMessage("DISCLAIMER: HealthMate's Calories Analyzer is provided for informational purposes only and should not be considered a substitute for professional medical advice, diagnosis, or treatment; always consult a qualified healthcare professional for personalized guidance.\n\n" +
                        "REFERENCES:\nMacro, T. K. (2023). Top 15 healthy carb, protein, and fat rich foods. Healthy Eater. https://healthyeater.com/carb-protein-fat-rich-foods")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        android.app.AlertDialog dialog = builder.create();
        dialog.show();
    }

}