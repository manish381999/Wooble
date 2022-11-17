package com.wooble.wooble.ui.Project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.wooble.wooble.R;

import java.util.Objects;

public class Edit_Project_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_project);


        Objects.requireNonNull(getSupportActionBar()).setTitle("Edit Project");

    }

}