package com.wooble.wooble.ui.home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;


import com.wooble.wooble.databinding.ActivityCreatePortfolioBinding;

import java.io.IOException;
import java.util.Objects;

public class Create_Portfolio_Activity extends AppCompatActivity {
ActivityCreatePortfolioBinding binding;
    private String category;
    final int REQ=11;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCreatePortfolioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Objects.requireNonNull(getSupportActionBar()).setTitle("Create Portfolio");

      binding.btSave.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent=new Intent(Create_Portfolio_Activity.this, HomeFragment.class);
              startActivity(intent);
              finish();

          }
      });


        String[] item=new String[]{"Choose your Profession","STUDENT","SOFTWARE DEVELOPER","VIDEO EDITOR", "RESEARCHER",
                                   "MARKETING STRATEGIST", "ARCHITECT", "FASHION DESIGNER", "CEO/FOUNDER", "OTHER"};

        binding.professionCategory.setAdapter(new ArrayAdapter<String>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item,item));



//        binding.professionCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                category=binding.professionCategory.getSelectedItem().toString();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//

        binding.professionCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category=binding.professionCategory.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

    }

    private void openGallery(){
        Intent pickImage=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImage,REQ);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQ && resultCode==RESULT_OK && data!=null){
            Uri uri=data.getData();

            try{
                bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            binding.profilePic.setImageBitmap(bitmap);

        }
    }
}