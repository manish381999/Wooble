package com.wooble.wooble.ui.Onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.wooble.wooble.R;
import com.wooble.wooble.databinding.ActivityOnboardingProfessionBinding;

import java.util.Objects;

public class Onboarding_profession_Activity extends AppCompatActivity {
ActivityOnboardingProfessionBinding binding;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOnboardingProfessionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().setStatusBarColor(Color.TRANSPARENT);

        }else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        name=getIntent().getStringExtra("NAME");

        binding.tvIm.setText("Hey " + name + ", You're a...");

        binding.btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Onboarding_profession_Activity.this, Onboarding_image_Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_left_to_right, R.anim.anim_right_to_left);
            }
        });
    }
}