package com.wooble.wooble.ui.credentials;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wooble.wooble.databinding.ActivityForgotPasswordBinding;

import java.util.Objects;

public class Forgot_PasswordActivity extends AppCompatActivity {
ActivityForgotPasswordBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();

    gotoLogin();

    }

    private void gotoLogin(){
        binding.gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Forgot_PasswordActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}