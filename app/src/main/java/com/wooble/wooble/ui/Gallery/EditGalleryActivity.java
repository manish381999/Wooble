package com.wooble.wooble.ui.Gallery;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.bumptech.glide.Glide;
import com.wooble.wooble.databinding.ActivityEditGalleryBinding;

import java.io.IOException;
import java.util.Objects;

public class EditGalleryActivity extends AppCompatActivity {
ActivityEditGalleryBinding binding;

    String data;
    String titleData;
    String captionData;

    final int REQ=13;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityEditGalleryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

Objects.requireNonNull(getSupportActionBar()).setTitle("Edit Gallery");

        data=getIntent().getStringExtra("image");
        titleData=getIntent().getStringExtra("title");
        captionData=getIntent().getStringExtra("caption");



        Glide.with(getApplicationContext())
                .load(data)
                .centerCrop()
                .into(binding.galleryImage);

        binding.imageTitle.setText(titleData);
        binding.imageCaption.setText(captionData);

        binding.galleryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
    }

    private void openGallery() {
        Intent pickImage=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImage,REQ);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQ && resultCode==RESULT_OK && data!=null){
            Uri uri=   data.getData();

            try {
                bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),uri);

            } catch (IOException e) {
                e.printStackTrace();
            }
            binding.galleryImage.setImageBitmap(bitmap);
        }
    }
}