package com.wooble.wooble.ui.Blogs;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.wooble.wooble.R;
import com.wooble.wooble.SessionManagement;
import com.wooble.wooble.databinding.ActivityBlogViewerBinding;

import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Blog_Viewer_Activity extends AppCompatActivity {
    ActivityBlogViewerBinding binding;
    TextToSpeech textToSpeech;

    String file_id, title, content, created_date, image, email_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBlogViewerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).setTitle("Blog viewer");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        file_id = getIntent().getStringExtra("id");
        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        email_id = sessionManagement.getSessionEmail();
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        created_date = getIntent().getStringExtra("last_updated");
        binding.tvTitle.setText(title);
        binding.tvDescription.loadData(content, "text/html", "UTF-8");
        binding.tvDate.setText(created_date);
        binding.tvFullName.setText(email_id);
        //textToSpeech();
        binding.ivDelete.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(Blog_Viewer_Activity.this);
            builder.setMessage("Do you want to delete ?");
            builder.setTitle("");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                Call<ResponseModel> call = Controller.getInstance()
                        .getApiInterface()
                        .deleteRow(file_id,email_id);
                call.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        ResponseModel responseModel = response.body();
                        String output = responseModel.getMessage();
                        Toast.makeText(getApplicationContext(), output, Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Blog_Viewer_Activity.this, BlogFragment.class);
                        startActivity(intent);
                        finish();
                        //Intent intent = new Intent().setClassName(Blog_Viewer_Activity.this,"com.wooble.wooble.ui.Blogs.BlogFragment.class");
                        //Blog_Viewer_Activity.this.startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            });
            builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                dialog.cancel();
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });

        binding.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Blog_Viewer_Activity.this, Edit_BlogActivity.class);
                intent.putExtra("id",file_id);
                //intent.putExtra("full_name",blogModel.getFull_name());
                //intent.putExtra("full_name",blogModel.getEmail_id());
                intent.putExtra("title",title);
                intent.putExtra("content",content);
                //intent.putExtra("created_date",blogModel.getLast_updated());
                //intent.putExtra("image",blogModel.getImage());
                startActivity(intent);
                finish();

            }
        });

    }


    private void textToSpeech() {
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.UK);

                    textToSpeech.setSpeechRate(0.6f);
                }

            }
        });

//        binding.mic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (textToSpeech.isSpeaking()) {
//                    textToSpeech.stop();
//                    binding.mic.setImageResource(R.drawable.ic_play);
//                } else {
//                    textToSpeech.speak(content, TextToSpeech.QUEUE_FLUSH, null, null);
//                    binding.mic.setImageResource(R.drawable.ic_pause);
//
//                }
//
//            }
//        });

    }


    @Override
    public void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();

        }
        super.onDestroy();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }
}