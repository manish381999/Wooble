package com.wooble.wooble.ui.Gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wooble.wooble.R;
import com.wooble.wooble.databinding.ActivityFullImage2Binding;
import com.wooble.wooble.databinding.ActivityFullImageBinding;

public class Full_Image_Activity extends AppCompatActivity {
ActivityFullImage2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFullImage2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}