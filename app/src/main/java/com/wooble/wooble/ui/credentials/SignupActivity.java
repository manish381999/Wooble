package com.wooble.wooble.ui.credentials;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;



import com.wooble.wooble.databinding.ActivitySignupBinding;

import java.util.Objects;

public class SignupActivity extends AppCompatActivity {
ActivitySignupBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();




      gotoLogin();
    }

    private void gotoLogin() {
        binding.gotoLogin.setOnClickListener(view -> {
            Intent intent=new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }


}