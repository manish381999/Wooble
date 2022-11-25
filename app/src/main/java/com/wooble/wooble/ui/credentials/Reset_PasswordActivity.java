package com.wooble.wooble.ui.credentials;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wooble.wooble.R;
import com.wooble.wooble.databinding.ActivityResetPasswordBinding;

import java.util.Objects;

public class Reset_PasswordActivity extends AppCompatActivity {
ActivityResetPasswordBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityResetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();
    }
}