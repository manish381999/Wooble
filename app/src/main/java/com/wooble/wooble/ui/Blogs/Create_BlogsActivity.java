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


    public void bold_btn(View view) {
        binding.boldBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spannable spannableString=new SpannableStringBuilder(binding.createBlogs.getText());
                spannableString.setSpan(new StyleSpan(Typeface.BOLD),
                        binding.createBlogs.getSelectionStart(),
                        binding.createBlogs.getSelectionEnd(),0);

                binding.createBlogs.setText(spannableString);
            }
        });

    }

    public void italic_btn(View view) {
        binding.italicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spannable spannableString=new SpannableStringBuilder(binding.createBlogs.getText());
                spannableString.setSpan(new StyleSpan(Typeface.ITALIC),
                        binding.createBlogs.getSelectionStart(),
                        binding.createBlogs.getSelectionEnd(),0);

                binding.createBlogs.setText(spannableString);
            }
        });
    }

    public void underline_btn(View view) {
        binding.underlineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spannable spannableString=new SpannableStringBuilder(binding.createBlogs.getText());
                spannableString.setSpan(new UnderlineSpan(),
                        binding.createBlogs.getSelectionStart(),
                        binding.createBlogs.getSelectionEnd(),0);

                binding.createBlogs.setText(spannableString);
            }
        });
    }

    public void default_btn(View view) {

        String stringText=binding.createBlogs.getText().toString();
        binding.createBlogs.setText(stringText);
    }

    public void left_btn(View view) {
        binding.createBlogs.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        Spannable spannableString=new SpannableStringBuilder(binding.createBlogs.getText());
        binding.createBlogs.setText(spannableString);

    }

    public void center_btn(View view) {
        binding.createBlogs.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        Spannable spannableString=new SpannableStringBuilder(binding.createBlogs.getText());
        binding.createBlogs.setText(spannableString);
    }

    public void right_btn(View view) {
        binding.createBlogs.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        Spannable spannableString=new SpannableStringBuilder(binding.createBlogs.getText());
        binding.createBlogs.setText(spannableString);
    }

    public void add_image_btn(View view) {
        ImageView imageView=new ImageView(Create_BlogsActivity.this);

        imageView.setImageResource(R.mipmap.ic_launcher);

        addView(imageView,200, 200);
        colorrandom(imageView);

    }

    public void colorrandom(ImageView imageView) {

        // Initialising the Random();
        Random random = new Random();

        // adding the random background color
        int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));

        // setting the background color
        imageView.setBackgroundColor(color);
    }

    public void addView(ImageView imageView, int width, int height){
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(width,height);
        layoutParams.setMargins(0,10,0, 10);

        imageView.setLayoutParams(layoutParams);
        binding.linerLayout.addView(imageView);
    }
}