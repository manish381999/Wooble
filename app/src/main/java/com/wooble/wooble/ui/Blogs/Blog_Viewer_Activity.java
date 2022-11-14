package com.wooble.wooble.ui.Blogs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import com.wooble.wooble.databinding.ActivityBlogViewerBinding;

public class Blog_Viewer_Activity extends AppCompatActivity {
ActivityBlogViewerBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityBlogViewerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }
}