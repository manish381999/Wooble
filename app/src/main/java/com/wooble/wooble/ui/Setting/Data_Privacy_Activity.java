package com.wooble.wooble.ui.Setting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wooble.wooble.R;

import java.util.Objects;

public class Data_Privacy_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_privacy);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Data Privacy");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}