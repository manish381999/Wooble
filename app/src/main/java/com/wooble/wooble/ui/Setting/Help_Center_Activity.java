package com.wooble.wooble.ui.Setting;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.wooble.wooble.databinding.ActivityHelpCenterBinding;

import java.util.Objects;

public class Help_Center_Activity extends AppCompatActivity {

    ActivityHelpCenterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHelpCenterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Objects.requireNonNull(getSupportActionBar()).setTitle("Help Center");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Help_Center_Activity.this, SettingActivity.class));
            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}