package com.wooble.wooble;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;


import android.annotation.SuppressLint;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;
import com.wooble.wooble.databinding.ActivityMainBinding;
import com.wooble.wooble.ui.credentials.LoginActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
ActivityMainBinding binding;
    private ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        NavController navController= Navigation.findNavController(MainActivity.this,R.id.frame_layout);
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);

        toggle=new ActionBarDrawerToggle(this,binding.drawerLayout,R.string.start, R.string.close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();




        binding.navigationDrawer.setNavigationItemSelectedListener(this);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        toggle.onOptionsItemSelected(item);
        return true;
    }


    @SuppressLint("NonConstantResourceId")
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navigation_profile:
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                break;

            case R.id.navigation_email_signature:
                Toast.makeText(this,"Email signature", Toast.LENGTH_SHORT).show();
                break;

            case R.id.navigation_setting:
                Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
                break;

            case R.id.navigation_social_media:
                Toast.makeText(this, "Social Media", Toast.LENGTH_SHORT).show();
                break;

            case R.id.navigation_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;

            case R.id.navigation_rate_us:
                Toast.makeText(this, "Rate Us", Toast.LENGTH_SHORT).show();
                break;

            case  R.id.navigation_logout:
                SessionManagement sessionManagement = new SessionManagement(MainActivity.this);
                sessionManagement.removeSession();
                Intent i = new Intent(MainActivity.this, LoginActivity.class );
                startActivity(i);
                finish();
                break;

        }
        return true;
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}



