package com.example.itehm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class Bone extends AppCompatActivity {

    private static final String YOUTUBE_VIDEO_URL = "https://youtu.be/V1YiDNEqOHM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bone);
        ImageView LeftIcon = findViewById(R.id.left_icon);
        ImageView RightIcon = findViewById(R.id.right_icon);
        TextView title = findViewById(R.id.toolbar_title);

        LeftIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Bone.this, FirstAid.class));
            }
        });
        RightIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Bone.this, MainActivity.class));
            }
        });

        TextView ownerTextView = findViewById(R.id.owner);
        ownerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                watchYouTubeVideo();
            }
        });

        TextView hotlineTwoTextView = findViewById(R.id.hotline_derma);
        hotlineTwoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhoneNumber(hotlineTwoTextView.getText().toString());
            }
        });

        VideoView videoView = findViewById(R.id.video_view);
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.brokenbones);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
    }

    private void watchYouTubeVideo() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(YOUTUBE_VIDEO_URL));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
