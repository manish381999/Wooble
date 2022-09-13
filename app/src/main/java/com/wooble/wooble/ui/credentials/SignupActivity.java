package com.wooble.wooble.ui.credentials;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.wooble.wooble.databinding.ActivitySignupBinding;

import java.util.Objects;

public class SignupActivity extends AppCompatActivity {
ActivitySignupBinding binding;

FirebaseAuth auth;
FirebaseDatabase database;

FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        currentUser=auth.getCurrentUser();


      gotoLogin();
      UploadUserDetails();
    }

    private void gotoLogin() {
        binding.gotoLogin.setOnClickListener(view -> {
            Intent intent=new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void UploadUserDetails(){

    }
}