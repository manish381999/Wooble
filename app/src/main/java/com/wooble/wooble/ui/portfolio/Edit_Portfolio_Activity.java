package com.wooble.wooble.ui.portfolio;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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

import com.wooble.wooble.CircularImageCropperActivity;
import com.wooble.wooble.MainActivity;
import com.wooble.wooble.databinding.ActivityEditPortfolioBinding;

import java.io.IOException;
import java.util.Objects;




public class Edit_Portfolio_Activity extends AppCompatActivity {
ActivityEditPortfolioBinding binding;
    final int REQ = 13;
    private Bitmap bitmap;

    String designation;

    ActivityResultLauncher<String> pickImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityEditPortfolioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).setTitle("Edit Portfolio");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        binding.btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Edit_Portfolio_Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        String[] item = new String[]{"Choose your Profession", "STUDENT", "SOFTWARE DEVELOPER", "VIDEO EDITOR", "RESEARCHER",
                "MARKETING STRATEGIST", "ARCHITECT", "FASHION DESIGNER", "CEO/FOUNDER", "OTHER"};

        binding.professionCategory.setAdapter(new ArrayAdapter<String>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, item));



        binding.professionCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                designation=binding.professionCategory.getSelectedItem().toString();
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

      pickImage=registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
          @Override
          public void onActivityResult(Uri result) {
              Intent intent=new Intent(Edit_Portfolio_Activity.this, CircularImageCropperActivity.class);
              intent.putExtra("DATA", result.toString());
              startActivityForResult(intent ,REQ);
          }
      });
    }

    private void openGallery(){
      pickImage.launch("image/*");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == REQ && resultCode==RESULT_OK && data!=null){
                String result=data.getStringExtra("RESULT");
                Uri resultUri=null;
                if (result!=null){
                    resultUri=Uri.parse(result);
                }

                try {
                    bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),resultUri);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                binding.profilePic.setImageBitmap(bitmap);
            }
        }
    }
