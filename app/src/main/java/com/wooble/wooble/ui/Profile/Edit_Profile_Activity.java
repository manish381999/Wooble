package com.wooble.wooble.ui.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wooble.wooble.databinding.ActivityEditProfileBinding;

import java.util.Objects;

public class Edit_Profile_Activity extends AppCompatActivity {
ActivityEditProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).setTitle("Edit Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

binding.btUpdate.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(Edit_Profile_Activity.this, ProfileActivity.class));
        finish();
    }
});







    }
}