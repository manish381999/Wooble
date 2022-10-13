package com.wooble.wooble.ui.Gallery;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;


import com.wooble.wooble.databinding.ActivityImageUploaderBinding;


import java.io.IOException;
import java.util.Objects;

public class ImageUploaderActivity extends AppCompatActivity {
    ActivityImageUploaderBinding binding;

    final int REQ=12;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImageUploaderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Objects.requireNonNull(getSupportActionBar()).setTitle("Image Uploader");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        binding.btUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ImageUploaderActivity.this, GalleryFragment.class);
                startActivity(intent);
            }
        });

   binding.galleryImage.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           openGallery();
       }
   });


    }

private void openGallery(){
    Intent pickImage=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
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



