package com.wooble.wooble.ui.home;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wooble.wooble.R;
import com.wooble.wooble.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {

 FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding=FragmentHomeBinding.inflate(inflater, container, false);

        requireActivity().setTitle("Home");

        binding.addPortfolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), Create_Portfolio_Activity.class);
                startActivity(intent);
            }
        });

        // Find the WebView by its unique ID
      //  WebView webView = findViewById(R.id.web);

        // loading http://www.google.com url in the WebView.
        binding.web.loadUrl("https://wooble.org/Manish");


        // this will enable the javascript.
        binding.web.getSettings().setJavaScriptEnabled(true);
        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        binding.web.setWebViewClient(new WebViewClient());


        return binding.getRoot();
    }
}