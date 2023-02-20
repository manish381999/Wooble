package com.wooble.wooble.ui.Onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.wooble.wooble.MainActivity;
import com.wooble.wooble.R;
import com.wooble.wooble.SessionManagement;
import com.wooble.wooble.databinding.ActivityOnboardingNameBinding;


import java.util.Objects;

public class Onboarding_name_Activity extends AppCompatActivity {
ActivityOnboardingNameBinding binding;
    @SuppressLint("ObsoleteSdkInt")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOnboardingNameBinding.inflate(getLayoutInflater());
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

        binding.btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Onboarding_name_Activity.this, Onboarding_profession_Activity.class);
                intent.putExtra("NAME",binding.etName.getText().toString());
                startActivity(intent);
                overridePendingTransition(R.anim.anim_left_to_right, R.anim.anim_right_to_left);
            }
        });
    }


//    @Override
//    protected void onStart() {
//        super.onStart();
//        SessionManagement sessionManagement = new SessionManagement(Onboarding_name_Activity.this);
//        String userEmail = sessionManagement.getSessionEmail();
//        String userPassword = sessionManagement.getSessionPassword();
//
//        if(userEmail != null && userPassword != null){
//            Intent intent = new Intent(Onboarding_name_Activity.this, MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//        }
//        else{
//        }
//    }
}