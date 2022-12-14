package com.wooble.wooble.ui.portfolio;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewClient;

import com.wooble.wooble.databinding.FragmentPortfolioBinding;


public class PortfolioFragment extends Fragment {

 FragmentPortfolioBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding=FragmentPortfolioBinding.inflate(inflater, container, false);

        requireActivity().setTitle("Portfolio");

        binding.addPortfolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), Create_Portfolio_Activity.class);
                startActivity(intent);
            }
        });

        String a="https://wooble.org/RakeshKumarSutar";
        // Find the WebView by its unique ID
      //  WebView webView = findViewById(R.id.web);

        // loading http://www.google.com url in the WebView.
        binding.web.loadUrl(a);


        // this will enable the javascript.
        binding.web.getSettings().setJavaScriptEnabled(true);
        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        binding.web.setWebViewClient(new WebViewClient());


        binding.web.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });



        return binding.getRoot();
    }
}