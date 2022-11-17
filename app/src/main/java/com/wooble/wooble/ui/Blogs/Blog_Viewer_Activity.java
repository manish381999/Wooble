package com.wooble.wooble.ui.Blogs;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.wooble.wooble.databinding.ActivityBlogViewerBinding;

import java.util.Objects;

public class Blog_Viewer_Activity extends AppCompatActivity {
ActivityBlogViewerBinding binding;

String id,title,content,created_date,image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityBlogViewerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

Objects.requireNonNull(getSupportActionBar()).setTitle("Blog viewer");

        id = getIntent().getStringExtra("id");
        title=getIntent().getStringExtra("title");
        content=getIntent().getStringExtra("content");
        created_date=getIntent().getStringExtra("created_date");
        image=getIntent().getStringExtra("image");

        binding.tvTitle.setText(title);
        binding.tvDescription.setText(content);
        binding.tvDate.setText(created_date);


    }
}