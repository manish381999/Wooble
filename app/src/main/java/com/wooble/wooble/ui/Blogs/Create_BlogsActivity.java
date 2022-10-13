package com.wooble.wooble.ui.Blogs;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;

import android.os.Bundle;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wooble.wooble.R;
import com.wooble.wooble.databinding.ActivityCreateBlogsBinding;


public class Create_BlogsActivity extends AppCompatActivity {
ActivityCreateBlogsBinding binding;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCreateBlogsBinding.inflate(getLayoutInflater());


        setContentView(binding.getRoot());




        // Find the WebView by its unique ID
        WebView webView = findViewById(R.id.web);

        // loading http://www.google.com url in the WebView.
        webView.loadUrl("file:///android_asset/editor/html/popular/full.html");


        // this will enable the javascript.
        webView.getSettings().setJavaScriptEnabled(true);
        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        webView.setWebViewClient(new WebViewClient());

    }






}