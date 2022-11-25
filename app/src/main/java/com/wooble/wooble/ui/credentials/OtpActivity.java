package com.wooble.wooble.ui.credentials;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.wooble.wooble.databinding.ActivityOtpBinding;


import java.util.Objects;

public class OtpActivity extends AppCompatActivity {
ActivityOtpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();
        binding.btnVerify.setOnClickListener(view -> {
            Intent intent=new Intent(OtpActivity.this, Reset_PasswordActivity.class);
            startActivity(intent);
        });

    }
}