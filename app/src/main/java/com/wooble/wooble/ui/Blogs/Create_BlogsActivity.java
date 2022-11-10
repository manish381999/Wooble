package com.wooble.wooble.ui.Blogs;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.wooble.wooble.R;
import com.wooble.wooble.databinding.ActivityCreateBlogsBinding;

import java.io.IOException;
import java.util.Objects;


public class Create_BlogsActivity extends AppCompatActivity {
    ActivityCreateBlogsBinding binding;


//    String imageView;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateBlogsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        setSupportActionBar(binding.appBar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_);


binding.publish.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(Create_BlogsActivity.this,BlogsFragment.class);
        startActivity(intent);
    }
});

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==33 && resultCode==RESULT_OK && data!=null){
            Uri uri=data.getData();

            try {
                bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),uri);

            } catch (IOException e) {
                e.printStackTrace();
            }
            ImageView imageView=new ImageView(Create_BlogsActivity.this);
            imageView.setImageBitmap(bitmap);
            addView(imageView, 400, 400);

        }

        }

       public void addView(ImageView imageView, int width, int height){
           LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(width, height);
           layoutParams.setMargins(5,0,5,0);

           imageView.setLayoutParams(layoutParams);
           binding.linerLayout.addView(imageView);
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
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 33);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
