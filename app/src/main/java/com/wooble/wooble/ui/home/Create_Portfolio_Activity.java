package com.wooble.wooble.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wooble.wooble.R;

import java.util.Objects;

public class Create_Portfolio_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_portfolio);


        Objects.requireNonNull(getSupportActionBar()).setTitle("Create Portfolio");

    }
}