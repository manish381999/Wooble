package com.wooble.wooble.ui.Project;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.wooble.wooble.R;
import com.wooble.wooble.databinding.ActivityProjectViewerBinding;

import java.util.Objects;

public class Project_Viewer_Activity extends AppCompatActivity {
ActivityProjectViewerBinding binding;
    MediaController mediaController;
    String file_id,email_id,project_name,aim_of_project,description,image_1,image_2,image_3,image_4,image_5,image_6,video,pdf_file,conclusion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityProjectViewerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).setTitle("Project Viewer");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        file_id=getIntent().getStringExtra("file_id");
        email_id=getIntent().getStringExtra("email_id");
        project_name=getIntent().getStringExtra("project_name");
        aim_of_project=getIntent().getStringExtra("aim_of_project");
        description=getIntent().getStringExtra("description");
        image_1=getIntent().getStringExtra("image_1");
        image_2=getIntent().getStringExtra("image_2");
        image_3=getIntent().getStringExtra("image_3");
        image_4=getIntent().getStringExtra("image_4");
        image_5=getIntent().getStringExtra("image_5");
        image_6=getIntent().getStringExtra("image_6");
        video=getIntent().getStringExtra("video");
        pdf_file=getIntent().getStringExtra("pdf_file");
        conclusion=getIntent().getStringExtra("conclusion");

        binding.tvProjectName.setText(project_name);
        binding.tvProjectAim.setText(aim_of_project);
        binding.tvDescription.setText(description);


        if(image_1!=null){
            Glide.with(getApplicationContext())
                    .load(image_1)
                    .centerCrop()
                    .into(binding.imageView1);
        }else {
            Glide.with(getApplicationContext())
                    .load(R.drawable.place_holder)
                    .centerCrop()
                    .into(binding.imageView1);
            binding.imageView1.setVisibility(View.INVISIBLE);
        }

        if(image_2!=null){
            Glide.with(getApplicationContext())
                    .load(image_2)
                    .centerCrop()
                    .into(binding.imageView2);
        }else {
            Glide.with(getApplicationContext())
                    .load(R.drawable.place_holder)
                    .centerCrop()
                    .into(binding.imageView2);
            binding.imageView2.setVisibility(View.INVISIBLE);
        }

        if(image_3!=null){
            Glide.with(getApplicationContext())
                    .load(image_3)
                    .centerCrop()
                    .into(binding.imageView3);
        }else {
            Glide.with(getApplicationContext())
                    .load(R.drawable.place_holder)
                    .centerCrop()
                    .into(binding.imageView3);
            binding.imageView3.setVisibility(View.INVISIBLE);
        }


        if(image_4!=null){
            Glide.with(getApplicationContext())
                    .load(image_4)
                    .centerCrop()
                    .into(binding.imageView4);
        }else {
            Glide.with(getApplicationContext())
                    .load(R.drawable.place_holder)
                    .centerCrop()
                    .into(binding.imageView4);
            binding.imageView4.setVisibility(View.INVISIBLE);
        }

        if(image_5!=null){
            Glide.with(getApplicationContext())
                    .load(image_5)
                    .centerCrop()
                    .into(binding.imageView5);
        }else {
            Glide.with(getApplicationContext())
                    .load(R.drawable.place_holder)
                    .centerCrop()
                    .into(binding.imageView5);
            binding.imageView5.setVisibility(View.INVISIBLE);
        }

        if(image_6!=null){
            Glide.with(getApplicationContext())
                    .load(image_6)
                    .centerCrop()
                    .into(binding.imageView6);
        }else {
            Glide.with(getApplicationContext())
                    .load(R.drawable.place_holder)
                    .centerCrop()
                    .into(binding.imageView6);
            binding.imageView6.setVisibility(View.INVISIBLE);
        }


        if (video == null) {
            binding.videoView.setVisibility(View.GONE);
        } else if (video != null) {
            binding.videoView.setVisibility(View.VISIBLE);
            mediaController = new MediaController(this);
            mediaController.setAnchorView(binding.videoView);
            binding.videoView.setMediaController(mediaController);
            binding.videoView.start();
            binding.videoView.setVideoURI(Uri.parse(video));
        }
        binding.tvConclusion.setText(conclusion);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return true;
    }
}