package com.wooble.wooble.ui.Gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wooble.wooble.databinding.ActivityEditGalleryBinding;

public class EditGalleryActivity extends AppCompatActivity {
ActivityEditGalleryBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityEditGalleryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}