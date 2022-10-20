package com.wooble.wooble.ui.Profile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;


import com.wooble.wooble.databinding.ActivityProfileBinding;

import java.io.IOException;
import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;
    final int REQ_COVER_PIC = 22;
    final int REQ_PROFILE_PIC = 33;
    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.addCoverPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                coverImage();
            }
        });

        binding.addProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileImage();
            }
        });


        binding.btEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, Edit_Profile_Activity.class));
            }
        });

    }

    private void coverImage() {
        Intent pickImg1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImg1, REQ_COVER_PIC);
    }

    private void profileImage(){
        Intent picImg2=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(picImg2, REQ_PROFILE_PIC);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_COVER_PIC && resultCode == RESULT_OK && data != null) {
            Uri uri1 = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            binding.coverPic.setImageBitmap(bitmap);

        }else if (requestCode==REQ_PROFILE_PIC && resultCode== RESULT_OK && data!=null){
            Uri uri2 = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri2);
            }catch (IOException e){
                e.printStackTrace();
            }
            binding.profilePic.setImageBitmap(bitmap);
        }
    }
}
