package com.wooble.wooble.ui.Setting;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.wooble.wooble.R;
import com.wooble.wooble.SessionManagement;
import com.wooble.wooble.databinding.ActivitySettingBinding;
import com.wooble.wooble.ui.credentials.LoginActivity;

import java.util.Objects;

public class SettingActivity extends AppCompatActivity {
ActivitySettingBinding binding;
String  data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       Objects.requireNonNull(getSupportActionBar()).setTitle("");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        data=getIntent().getStringExtra("image");


        Glide.with(SettingActivity.this)
                .load(data)
                .placeholder(R.drawable.place_holder)
                .centerCrop()
                .into(binding.profilePic);


        binding.helpCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent=new Intent(SettingActivity.this, Help_Center_Activity.class);
                intent.putExtra("image",data);
                startActivity(intent);

            }
        });

binding.privacyPolicy.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(SettingActivity.this, Privacy_Policy_Activity.class));
    }
});

binding.dataPrivacy.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        //startActivity(new Intent(SettingActivity.this, Data_Privacy_Activity.class));
    }
});

binding.terms.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        //startActivity(new Intent(SettingActivity.this, Terms_and_conditions_Activity.class));
    }
});

binding.end.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        //startActivity(new Intent(SettingActivity.this, End_User_License_AgreementActivity.class));
    }
});

binding.rateUs.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        try{
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+getPackageName())));
        }
        catch (ActivityNotFoundException e){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName())));
        }
        //Toast.makeText(SettingActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
    }
});


        binding.signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManagement sessionManagement = new SessionManagement(SettingActivity.this);
                sessionManagement.removeSession();
                Intent i = new Intent(SettingActivity.this, LoginActivity.class );
                startActivity(i);
                finish();
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