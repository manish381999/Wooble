package com.wooble.wooble;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.wooble.wooble.databinding.ActivityMainBinding;
import com.wooble.wooble.ui.credentials.LoginActivity;
import com.wooble.wooble.ui.credentials.SignupActivity;
public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
    String email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        NavController navController= Navigation.findNavController(MainActivity.this,R.id.frame_layout);
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);
        Button logoutBtn = findViewById(R.id.logoutButton);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManagement sessionManagement = new SessionManagement(MainActivity.this);
                sessionManagement.removeSession();
                Intent i = new Intent(MainActivity.this, LoginActivity.class );
                startActivity(i);
                finish();
            }
        });
    }

    }



