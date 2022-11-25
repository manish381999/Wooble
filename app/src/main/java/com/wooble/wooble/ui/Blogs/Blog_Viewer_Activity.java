package com.wooble.wooble.ui.Blogs;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.wooble.wooble.R;
import com.wooble.wooble.databinding.ActivityBlogViewerBinding;
import com.wooble.wooble.ui.Project.Edit_Project_Activity;
import com.wooble.wooble.ui.Project.ProjectFragment;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Blog_Viewer_Activity extends AppCompatActivity {
ActivityBlogViewerBinding binding;

String id,title,content,created_date,image,full_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityBlogViewerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

Objects.requireNonNull(getSupportActionBar()).setTitle("Blog viewer");
getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        id = getIntent().getStringExtra("id");
        full_name=getIntent().getStringExtra("full_name");
        title=getIntent().getStringExtra("title");
//        System.out.println("ID "+file_id);
        content=getIntent().getStringExtra("content");
        created_date=getIntent().getStringExtra("created_date");
        image=getIntent().getStringExtra("image");
        binding.tvTitle.setText(title);
        binding.tvDescription.setText(content);
        binding.tvDate.setText(created_date);
        binding.tvFullName.setText(full_name);


        binding.ivDelete.setOnClickListener(v -> {
            Call<ResponseModel> call = Controller.getInstance()
                    .getApiInterface()
                    .deleteBlog(id);

            call.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    ResponseModel responseModel = response.body();
                    String output = responseModel.getMessage();
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), output, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Blog_Viewer_Activity.this, BlogFragment.class);
                            startActivity(intent);
                        }
                    }, 2000);
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });
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