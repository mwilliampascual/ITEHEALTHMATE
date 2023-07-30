package com.example.itehm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.NotificationCompat;


import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class SleepAnalyzer extends AppCompatActivity implements View.OnClickListener {

    private String[] oversleepArticles = {
            "For people who suffer from hypersomnia, oversleeping is actually a medical disorder. The condition causes people to suffer from extreme sleepiness throughout the day, which is not usually relieved by napping.",
            "Obstructive sleep apnea, a disorder that causes people to stop breathing momentarily during sleep, can also lead to an increased need for sleep.",
            "For some people prone to headaches, sleeping longer than usual on a weekend or vacation can cause head pain.",
            "The most common causes we look at when someone says they're sleeping more than nine hours a night is if it’s a medication effect or a medical, psychiatric, or neurological disorder,"
    };

    private String[] insufficientSleepArticles = {
            "Common causes of insomnia include stress, an irregular sleep schedule, poor sleeping habits, mental health disorders like anxiety and depression, physical illnesses and pain, medications, neurological problems, and specific sleep disorders.",
            "Lack of alertness. Even missing as little as 1.5 hours can have an impact on how you feel.",
            "Excessive daytime sleepiness. It can make you very sleepy and tired during the day.",
            "Impaired memory. Lack of sleep can affect your ability to think, remember and process information"
    };

    private EditText sleepDurationEditText;
    private Button analyzeButton;
    private TextView resultTextView;
    private CountDownTimer sleepTimer;

    private Ringtone alarmRingtone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_analyzer);

        sleepDurationEditText = findViewById(R.id.sleepDurationEditText);
        analyzeButton = findViewById(R.id.analyzeButton);
        resultTextView = findViewById(R.id.resultTextView);
        ImageView LeftIcon = findViewById(R.id.left_icon);
        ImageView RightIcon = findViewById(R.id.right_icon);
        TextView title = findViewById(R.id.toolbar_title);

        LeftIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SleepAnalyzer.this, MainHealth.class));
            }
        });
        RightIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SleepAnalyzer.this, MainActivity.class));
            }
        });

        analyzeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                analyzeSleep();
            }
        });
        ImageView disclaimerIcon = findViewById(R.id.disclaimerIcon);
        disclaimerIcon.setOnClickListener(this);
    }

    private void analyzeSleep() {
        String sleepDurationStr = sleepDurationEditText.getText().toString();

        if (sleepDurationStr.isEmpty()) {
            resultTextView.setText("Please enter a sleep duration.");
            return;
        }

        try {
            int sleepHours;
            int sleepMinutes;

            if (sleepDurationStr.contains(":")) {
                String[] parts = sleepDurationStr.split(":");
                sleepHours = Integer.parseInt(parts[0]);
                sleepMinutes = Integer.parseInt(parts[1]);
            } else {
                sleepHours = Integer.parseInt(sleepDurationStr);
                sleepMinutes = 0;
            }

            if (sleepHours >= 8) {
                resultTextView.setText("Sufficient sleep.");

                if (sleepHours > 8) {
                    int additionalHours = sleepHours - 8;
                    resultTextView.append("\nYou have exceeded the recommended amount of sleep by " + additionalHours + " hours more than recommended.\n");
                    int randomIndex = new Random().nextInt(oversleepArticles.length);
                    String oversleepArticle = oversleepArticles[randomIndex];
                    resultTextView.append("\nInformation: " + oversleepArticle);
                }
            } else {
                resultTextView.setText("Insufficient sleep.");

                int recommendedHours = 8 - sleepHours;
                int recommendedMinutes = 0 - sleepMinutes;

                if (recommendedMinutes < 0) {
                    recommendedHours--;
                    recommendedMinutes = 60 - Math.abs(recommendedMinutes);
                }

                resultTextView.append("\nYou need " + recommendedHours + " hours and " + recommendedMinutes + " minutes more of sleep.\n");
                int randomIndex = new Random().nextInt(insufficientSleepArticles.length);
                String insufficientSleepArticle = insufficientSleepArticles[randomIndex];
                resultTextView.append("\nInformation: " + insufficientSleepArticle);

                // Show the timer confirmation dialog after showing the result
                showTimerConfirmationDialog(recommendedHours, recommendedMinutes);
            }
        } catch (NumberFormatException e) {
            resultTextView.setText("Invalid input. Please enter sleep duration in HH:mm format (e.g., 7:30).");
        }
    }

    private void showTimerConfirmationDialog(final int recommendedHours, final int recommendedMinutes) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Set Timer");
        builder.setMessage("Do you want to set a timer?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                startSleepTimer(recommendedHours, recommendedMinutes);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void startSleepTimer(int recommendedHours, int recommendedMinutes) {
        int recommendedMillis = (recommendedHours * 60 + recommendedMinutes) * 60 * 1000; // Convert hours and minutes to milliseconds

        sleepTimer = new CountDownTimer(recommendedMillis, 1000) {
            public void onTick(long millisUntilFinished) {
                long hours = millisUntilFinished / (1000 * 60 * 60);
                long minutes = (millisUntilFinished % (1000 * 60 * 60)) / (1000 * 60);
                long seconds = (millisUntilFinished % (1000 * 60)) / 1000;

                String timerText = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                resultTextView.setText("Insufficient sleep.\n\nYou need to sleep for " + recommendedHours + " hours and " + recommendedMinutes + " minutes more.\n\nTimer: " + timerText);
            }

            public void onFinish() {
                resultTextView.setText("Insufficient sleep.\n\nYou need to sleep for " + recommendedHours + " hours and " + recommendedMinutes + " minutes more.\n\nTimer: 00:00:00");
                playAlarm();
            }
        }.start();
    }

    private void playAlarm() {
        try {
            // Create an AlertDialog to give the option to turn off the alarm
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Alarm");
            builder.setMessage("Wake up!");
            builder.setPositiveButton("Turn Off", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    stopAlarm();
                }
            });
            builder.setCancelable(false);
            builder.show();

            // Play the alarm sound
            Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            alarmRingtone = RingtoneManager.getRingtone(getApplicationContext(), alarmUri);
            alarmRingtone.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopAlarm() {
        // Stop the alarm sound
        if (alarmRingtone != null && alarmRingtone.isPlaying()) {
            alarmRingtone.stop();
        }

        // Cancel the notification
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(0);
    }



    @Override
    protected void onStop() {
        super.onStop();
        if (sleepTimer != null) {
            sleepTimer.cancel();
        }
    }

    @Override
    public void onClick(View v) {
        showDisclaimerDialog();
    }

    private void showDisclaimerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Disclaimer and References")
                .setMessage("DISCLAIMER: HealthMate's Sleep Analyzer calculator is provided for informational purposes only and should not be considered a substitute for professional medical advice, diagnosis, or treatment; always consult a qualified healthcare professional for personalized guidance.\n\n")

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

