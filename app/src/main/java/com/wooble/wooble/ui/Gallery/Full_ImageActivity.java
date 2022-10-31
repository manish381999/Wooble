package com.wooble.wooble.ui.Gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wooble.wooble.databinding.ActivityFullImageBinding;

public class Full_ImageActivity extends AppCompatActivity {
ActivityFullImageBinding binding;

String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityFullImageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}