package com.wooble.wooble.ui.Blogs;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.method.CharacterPickerDialog;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.wooble.wooble.databinding.ActivityBlogViewerBinding;

import java.util.Locale;
import java.util.Objects;

public class Blog_Viewer_Activity extends AppCompatActivity {
ActivityBlogViewerBinding binding;

String id,title,content,created_date,image;

    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityBlogViewerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

Objects.requireNonNull(getSupportActionBar()).setTitle("Blog viewer");
getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        id = getIntent().getStringExtra("id");
        title=getIntent().getStringExtra("title");
        content=getIntent().getStringExtra("content");
        created_date=getIntent().getStringExtra("created_date");
        image=getIntent().getStringExtra("image");

        binding.tvTitle.setText(title);
        binding.tvDescription.setText(content);
        binding.tvDate.setText(created_date);


         textToSpeech=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status!=TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });

         binding.mic.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 textToSpeech.speak(content, TextToSpeech.QUEUE_FLUSH, null);
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