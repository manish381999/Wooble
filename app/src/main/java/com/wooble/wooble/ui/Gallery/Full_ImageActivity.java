package com.wooble.wooble.ui.Gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.wooble.wooble.databinding.ActivityFullImageBinding;

import java.util.Objects;

public class Full_ImageActivity extends AppCompatActivity {
ActivityFullImageBinding binding;

String data;
String titleData;
String captionData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityFullImageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();

        data=getIntent().getStringExtra("image");
        titleData=getIntent().getStringExtra("title");
        captionData=getIntent().getStringExtra("caption");


        binding.cation.setText(captionData);

        Glide.with(getApplicationContext())
                .load(data)
                .centerCrop()
                .into(binding.imageView);


        binding.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent=new Intent(Full_ImageActivity.this, EditGalleryActivity.class);
                intent.putExtra("image",data);
                intent.putExtra("title",titleData);
                intent.putExtra("caption",captionData);
                startActivity(intent);
            }
        });

    }
}