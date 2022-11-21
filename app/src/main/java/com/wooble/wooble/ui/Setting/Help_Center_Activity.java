package com.wooble.wooble.ui.Setting;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.wooble.wooble.MainActivity;
import com.wooble.wooble.databinding.ActivityHelpCenterBinding;

import java.util.Objects;

public class Help_Center_Activity extends AppCompatActivity {

    ActivityHelpCenterBinding binding;
    String  data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHelpCenterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Objects.requireNonNull(getSupportActionBar()).setTitle("Help Center");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        data=getIntent().getStringExtra("image");

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Help_Center_Activity.this, MainActivity.class);
                intent.putExtra("image",data);
                startActivity(intent);

            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==android.R.id.home){
            onBackPressed();

        }
        return true;
    }
}