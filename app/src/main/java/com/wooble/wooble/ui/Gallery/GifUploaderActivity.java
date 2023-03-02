package com.wooble.wooble.ui.Gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;


import com.bumptech.glide.Glide;
import com.wooble.wooble.databinding.ActivityGifUploaderBinding;

import java.util.Objects;

public class GifUploaderActivity extends AppCompatActivity {
ActivityGifUploaderBinding binding;

    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityGifUploaderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.imToolbar);




        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        data=getIntent().getStringExtra("gif");

        Glide.with(getApplicationContext())
                .asGif()
                .load(Uri.parse(data))
                .into(binding.ivGif);







    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}