package com.wooble.wooble;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.wooble.wooble.databinding.ActivitySocialMediaBinding;

import java.util.Objects;


public class Social_media_Activity extends AppCompatActivity {
ActivitySocialMediaBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySocialMediaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Objects.requireNonNull(getSupportActionBar()).setTitle("Social media");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

binding.facebook.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String url = "https://www.facebook.com/theWooble/";

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
});

binding.instagram.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String url = "https://www.instagram.com/wooble_org/";

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
});

binding.twitter.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String url="https://twitter.com/woobleO";


        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
});

binding.linkedIn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String url="https://www.linkedin.com/company/thewooble";

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
});
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}