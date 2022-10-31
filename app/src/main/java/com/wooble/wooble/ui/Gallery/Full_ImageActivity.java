package com.wooble.wooble.ui.Gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.wooble.wooble.databinding.ActivityFullImageBinding;

import java.util.Objects;

public class Full_ImageActivity extends AppCompatActivity {
ActivityFullImageBinding binding;

String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityFullImageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();

        data=getIntent().getStringExtra("image");

        Glide.with(getApplicationContext())
                .load(data)
                .centerCrop()
                .into(binding.imageView);


    }
}