package com.wooble.wooble.ui.Blogs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wooble.wooble.R;
import com.wooble.wooble.databinding.ActivityCreateBlogsBinding;


import java.util.Random;


public class Create_BlogsActivity extends AppCompatActivity {
ActivityCreateBlogsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCreateBlogsBinding.inflate(getLayoutInflater());


        setContentView(binding.getRoot());

    }

    private void getSupportActionBar(Toolbar toolbar) {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }



}