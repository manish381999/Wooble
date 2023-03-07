package com.wooble.wooble.ui.Setting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wooble.wooble.R;
import com.wooble.wooble.databinding.ActivityPrivacyPolicyBinding;

import java.util.Objects;

public class Privacy_Policy_Activity extends AppCompatActivity {
ActivityPrivacyPolicyBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPrivacyPolicyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.imToolbar);


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);




    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}