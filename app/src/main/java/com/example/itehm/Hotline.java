package com.example.itehm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.core.app.NotificationCompat;


import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Hotline extends AppCompatActivity {

    private Button callbtn, callbtn1, callbtn2, callbtn3;
    private ImageView LeftIcon, RightIcon, imageView1, imageView2, imageView3, imageView4;

    // Notification channel constants
    private static final String CHANNEL_ID = "call_notification_channel";
    private static final String CHANNEL_NAME = "Call Notifications";
    private static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotline);

        callbtn = findViewById(R.id.callbtn);
        callbtn1 = findViewById(R.id.callbtn1);
        callbtn2 = findViewById(R.id.callbtn2);
        callbtn3 = findViewById(R.id.callbtn3);
        LeftIcon = findViewById(R.id.left_icon);
        RightIcon = findViewById(R.id.right_icon);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);

        LeftIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Hotline.this, MainActivity.class));
            }
        });

        RightIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Hotline.this, MainActivity.class));
            }
        });

        callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "(02)8559 3930";
                dialPhoneNumber(phoneNumber);
            }
        });

        callbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "(02)8517 1674";
                dialPhoneNumber(phoneNumber);
            }
        });

        callbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "(02)8564 1524";
                dialPhoneNumber(phoneNumber);
            }
        });

        callbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "(02)8125 0214";
                dialPhoneNumber(phoneNumber);
            }
        });

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink("https://goo.gl/maps/sF6s3a3zhctwRPcd7");
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink("https://goo.gl/maps/hA3phhgQuSeaDWLXA");
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink("https://goo.gl/maps/eARmpkyc9L1Z55f47");
            }
        });

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink("https://goo.gl/maps/LtPLbfUdtYTKvJHN8");
            }
        });
    }

    private void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
            showNotification(phoneNumber);
        }
    }


    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return sdf.format(new Date());
    }

    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return sdf.format(new Date());
    }

    private void showNotification(String phoneNumber) {
        // Create the notification channel (for Android Oreo and above)
        createNotificationChannel();

        String notificationText = "Call Initiated\nPhone Number: " + phoneNumber +
                "\nTime: " + getCurrentTime() +
                "\nDate: " + getCurrentDate();

        // Create the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Call Initiated")
                .setContentText(phoneNumber)
                .setSmallIcon(R.drawable.ic_home)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(notificationText)) // Display long text in the notification
                .setAutoCancel(true);

        // Create the intent to open the app when notification is clicked
        Intent intent = new Intent(this, Hotline.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        // Show the notification
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }
    }



    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    private void openLink(String link) {
        Uri uri = Uri.parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
