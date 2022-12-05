package com.wooble.wooble.ui.Project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.squareup.picasso.Picasso;
import com.wooble.wooble.R;
import com.wooble.wooble.databinding.ActivityProjectViewerBinding;
import com.wooble.wooble.ui.Resume.Resume_Viewer_Activity;

import java.util.Objects;

public class Project_Viewer_Activity extends AppCompatActivity {
    ActivityProjectViewerBinding binding;

    String file_id, email_id, project_name, aim_of_project, description, image_1, image_2, image_3, image_4, image_5, image_6, video, pdf_file, conclusion=null;

    //String url="http://172.168.0.182/wooble_api/upload/";
    String url="https://app.wooble.org/works/upload/";
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProjectViewerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).setTitle("Work Viewer");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        file_id = getIntent().getStringExtra("file_id");
        email_id = getIntent().getStringExtra("email_id");
        project_name = getIntent().getStringExtra("project_name");
        aim_of_project = getIntent().getStringExtra("aim_of_project");
        description = getIntent().getStringExtra("description");
        image_1 = url+getIntent().getStringExtra("image_1");
        image_2 = url+getIntent().getStringExtra("image_2");
        image_3 = url+getIntent().getStringExtra("image_3");
        image_4 = url+getIntent().getStringExtra("image_4");
        image_5 = url+getIntent().getStringExtra("image_5");
        image_6 = url+getIntent().getStringExtra("image_6");
        video = url+getIntent().getStringExtra("video");
        pdf_file = url+getIntent().getStringExtra("pdf_file");
        conclusion = getIntent().getStringExtra("conclusion");

        binding.tvProjectName.setText(project_name);
        binding.tvProjectAim.setText(aim_of_project);
        binding.tvDescription.setText(description);
        binding.tvConclusion.setText(conclusion);


        if (image_1 != null) {
            Picasso.get()
                    .load(image_1)
                    .into(binding.imageView1);
        } else {
            Picasso.get()
                    .load(R.drawable.place_holder)
                    .into(binding.imageView1);
            binding.imageView1.setVisibility(View.INVISIBLE);
        }


        if (image_2 != null) {
            Picasso.get()
                    .load(image_2)
                    .into(binding.imageView2);
        } else {
            Picasso.get()
                    .load(R.drawable.place_holder)
                    .into(binding.imageView2);
            binding.imageView2.setVisibility(View.INVISIBLE);
        }

        if (image_3 != null) {
            Picasso.get()
                    .load(image_3)
                    .into(binding.imageView3);
        } else {
            Picasso.get()
                    .load(R.drawable.place_holder)
                    .into(binding.imageView3);
            binding.imageView3.setVisibility(View.INVISIBLE);
        }


        if (image_4 != null) {
            Picasso.get()
                    .load(image_4)
                    .into(binding.imageView4);
        } else {
            Picasso.get()
                    .load(R.drawable.place_holder)
                    .into(binding.imageView4);
            binding.imageView4.setVisibility(View.INVISIBLE);
        }

        if (image_5 != null) {
            Picasso.get()
                    .load(image_5)
                    .into(binding.imageView5);
        } else {
            Picasso.get()
                    .load(R.drawable.place_holder)
                    .into(binding.imageView5);
            binding.imageView5.setVisibility(View.INVISIBLE);
        }

        if (image_6 != null) {
            Picasso.get()
                    .load(image_6)
                    .into(binding.imageView6);
        } else {
            Picasso.get()
                    .load(R.drawable.place_holder)
                    .into(binding.imageView6);
            binding.imageView6.setVisibility(View.INVISIBLE);
        }




        if (video == null) {
            binding.exoPlay.setVisibility(View.GONE);
        } else {
            ExoPlayer exoPlayer = new ExoPlayer.Builder(this).build();
            binding.exoPlay.setPlayer(exoPlayer);
            MediaItem mediaItem = MediaItem.fromUri(video);
            exoPlayer.addMediaItem(mediaItem);
            exoPlayer.prepare();
            exoPlayer.stop();
            exoPlayer.play();
            exoPlayer.stop();

        }


        if (pdf_file != null) {
            binding.pdfTitle.setText(project_name + ".pdf");
            binding.showPdf.setOnClickListener(v -> {
                Intent intent = new Intent(Project_Viewer_Activity.this, Resume_Viewer_Activity.class);
                intent.putExtra("pdfUrl", pdf_file);
                startActivity(intent);
            });

        } else {
            binding.showPdf.setVisibility(View.GONE);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }
}