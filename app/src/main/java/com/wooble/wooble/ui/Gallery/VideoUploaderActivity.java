package com.wooble.wooble.ui.Gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.wooble.wooble.R;
import com.wooble.wooble.databinding.ActivityVideoUploaderBinding;

import java.util.Objects;

public class VideoUploaderActivity extends AppCompatActivity {
ActivityVideoUploaderBinding binding;
String data;

String resultUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityVideoUploaderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.imToolbar);


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        data=getIntent().getStringExtra("video");

        resultUri = String.valueOf(Uri.parse(data));

        ExoPlayer exoPlayer = new ExoPlayer.Builder(this).build();
        binding.exoPlay.setPlayer(exoPlayer);
        MediaItem mediaItem = MediaItem.fromUri(resultUri);
        exoPlayer.addMediaItem(mediaItem);
        exoPlayer.prepare();
        exoPlayer.stop();
        exoPlayer.play();
        exoPlayer.stop();

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}