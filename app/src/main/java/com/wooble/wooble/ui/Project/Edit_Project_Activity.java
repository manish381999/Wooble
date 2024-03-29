package com.wooble.wooble.ui.Project;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.squareup.picasso.Picasso;
import com.wooble.wooble.R;
import com.wooble.wooble.SessionManagement;
import com.wooble.wooble.databinding.ActivityEditProjectBinding;
import com.wooble.wooble.databinding.ActivityFullImageBinding;
import com.wooble.wooble.ui.Blogs.Controller;
import com.wooble.wooble.ui.Blogs.ResponseModel;
import com.wooble.wooble.ui.Gallery.Full_ImageActivity;
import com.wooble.wooble.ui.Resume.Resume_Viewer_Activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Base64;
import java.util.Objects;

import kotlinx.coroutines.Delay;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Edit_Project_Activity extends AppCompatActivity {
    ActivityEditProjectBinding binding;

    String file_id, email_id, project_name, aim_of_project, description, image_1, image_2, image_3, image_4, image_5, image_6, video, pdf_file, conclusion;

    final int REQ_imageView1 = 10;
    final int REQ_imageView2 = 20;
    final int REQ_imageView3 = 30;
    final int REQ_imageView4 = 40;
    final int REQ_imageView5 = 50;
    final int REQ_imageView6 = 60;

    Uri image_Uri_1, pdf_Uri, video_Uri, image_Uri_2,image_Uri_3,image_Uri_4,image_Uri_5,image_Uri_6;

    private String pdfName;
    private Bitmap bitmap;


    final int REQ_pdf = 70;
    final int REQ_video = 80;
    MediaController mediaController;
    //String url="http://172.168.0.182/wooble_api/upload/";
    String url="https://app.wooble.org/works/upload/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProjectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).setTitle("Edit Project");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        file_id = email_id = project_name = aim_of_project = description = image_1 = image_2 = image_3 = image_4 = image_5 = image_6 = video = pdf_file = conclusion = "TlVMTA==";
        mediaController=new MediaController(this);
        mediaController.setAnchorView(binding.videoView);
        binding.videoView.setMediaController(mediaController);
        binding.videoView.stopPlayback();

        binding.imageView1.setOnClickListener(view -> imageView1());

        binding.imageView2.setOnClickListener(view -> imageView2());

        binding.imageView3.setOnClickListener(view -> imageView3());

        binding.imageView4.setOnClickListener(view -> imageView4());

        binding.imageView5.setOnClickListener(view -> imageView5());

        binding.imageView6.setOnClickListener(view -> imageView6());


        binding.addPdf.setOnClickListener(view -> openDocument());

        binding.videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoVideo();
            }
        });

        file_id = getIntent().getStringExtra("file_id");
        email_id = getIntent().getStringExtra("email_id");
        project_name = getIntent().getStringExtra("project_name");
        aim_of_project = getIntent().getStringExtra("aim_of_project");
        description = getIntent().getStringExtra("description");
        image_1 = getIntent().getStringExtra("image_1");
        image_2 = getIntent().getStringExtra("image_2");
        image_3 = getIntent().getStringExtra("image_3");
        image_4 = getIntent().getStringExtra("image_4");
        image_5 = getIntent().getStringExtra("image_5");
        image_6 = getIntent().getStringExtra("image_6");
        video = getIntent().getStringExtra("video");
        pdf_file = getIntent().getStringExtra("pdf_file");
        conclusion = getIntent().getStringExtra("conclusion");

        System.out.println("Hii "+image_3);
        System.out.println("Hii "+image_2);

        binding.etProjectName.setText(project_name);
        binding.etProjectAim.setText(aim_of_project);
        binding.aboutYourself.setText(description);
        binding.etConclusion.setText(conclusion);
        //binding.videoView.setVideoURI(Uri.parse(video));
        //video= fileUriToBase64(Uri.parse(video),getContentResolver());
        //binding.addPdf.set
        if (image_1 != null) {
            Picasso.get()
                    .load(url+image_1)
                    .into(binding.imageView1);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                image_1 = Base64.getEncoder().encodeToString(image_1.getBytes());
            }

        } else if (image_1 == null) {
            image_1 = "TlVMTA==";

        } else {
            Picasso.get()
                    .load(R.drawable.place_holder)
                    .into(binding.imageView1);
        }

        if (image_2 != null) {
            Picasso.get()
                    .load(url+image_2)
                    .into(binding.imageView2);

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                image_2 = Base64.getEncoder().encodeToString(image_2.getBytes());
            }
        } else if (image_2 == null) {
            image_2 = "TlVMTA==";

        } else {
            Picasso.get()
                    .load(R.drawable.place_holder)
                    .into(binding.imageView2);
        }

        System.out.println(image_3 + "image_3");
        if (image_3 != null) {
            Picasso.get()
                    .load(url+image_3)
                    .into(binding.imageView3);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                image_3 = Base64.getEncoder().encodeToString(image_3.getBytes());
            }
        } else if (image_3 == null) {
            image_3 = "TlVMTA==";

        } else {
            Picasso.get()
                    .load(R.drawable.place_holder)
                    .into(binding.imageView3);
        }


        if (image_4 != null) {
            Picasso.get()
                    .load(url+image_4)
                    .into(binding.imageView4);

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                image_4 = Base64.getEncoder().encodeToString(image_4.getBytes());
            }
        } else if (image_4 == null) {
            image_4 = "TlVMTA==";
        } else {
            Picasso.get()
                    .load(R.drawable.place_holder)
                    .into(binding.imageView4);
        }

        if (image_5 != null) {
            Picasso.get()
                    .load(url+image_5)
                    .into(binding.imageView5);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                image_5 = Base64.getEncoder().encodeToString(image_5.getBytes());
            }
        } else if (image_5 == null) {
            image_5 = "TlVMTA==";

        } else {
            Picasso.get()
                    .load(R.drawable.place_holder)
                    .into(binding.imageView5);
        }

        if (image_6 != null) {
            Picasso.get()
                    .load(url+image_6)
                    .into(binding.imageView6);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                image_6 = Base64.getEncoder().encodeToString(image_6.getBytes());
            }
        } else if (image_6 == null) {
            image_6 = "TlVMTA==";

        } else {
            Picasso.get()
                    .load(R.drawable.place_holder)
                    .into(binding.imageView6);
        }

        if (video != null) {
            binding.videoView.setVideoURI(Uri.parse(url+video));
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                video = Base64.getEncoder().encodeToString(video.getBytes());
            }
        } else if (video == null) {
            video = "TlVMTA==";
        } else {
            binding.videoView.setVideoURI(Uri.parse(""));

        }

        if (pdf_file != null) {
            binding.pdfTextview.setText(project_name + ".pdf");
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                pdf_file = Base64.getEncoder().encodeToString(pdf_file.getBytes());
            }
        } else if (image_6 == null) {
            image_6 = "TlVMTA==";

        } else {

        }


        binding.ivDelete.setOnClickListener(v -> {


            AlertDialog.Builder builder = new AlertDialog.Builder(Edit_Project_Activity.this);
            builder.setMessage("Do you want to delete ?");
            builder.setTitle("");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                Call<ResponseModel> call = Controller.getInstance()
                        .getApiInterface()
                        .deleteProject(email_id, file_id);

                call.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        ResponseModel responseModel = response.body();
                        String output = responseModel.getMessage();
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Edit_Project_Activity.this, output, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Edit_Project_Activity.this, ProjectFragment.class);
                                startActivity(intent);
                            }
                        }, 2000);

                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Toast.makeText(Edit_Project_Activity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Edit_Project_Activity.this, ProjectFragment.class);
                        startActivity(intent);
                    }
                });            });
            builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                dialog.cancel();
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();





        });

        binding.btUpdateProject.setOnClickListener(v -> {
            binding.btUpdateProject.setEnabled(false);
            binding.spinKit.setVisibility(View.VISIBLE);
            SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                email_id = Base64.getEncoder().encodeToString(sessionManagement.getSessionEmail().getBytes());
            }
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                if (binding.etProjectName.getText().toString().trim() != null) {
                    project_name = Base64.getEncoder().encodeToString(binding.etProjectName.getText().toString().trim().getBytes());
                }
            }
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                if (binding.etProjectAim.getText().toString().trim() != null) {
                    aim_of_project = Base64.getEncoder().encodeToString(binding.etProjectAim.getText().toString().trim().getBytes());
                }
            }

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                if (binding.aboutYourself.getText().toString().trim() != null) {
                    description = Base64.getEncoder().encodeToString(binding.aboutYourself.getText().toString().trim().getBytes());
                }
            }
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                if (binding.etConclusion.getText().toString().trim() != null) {
                    conclusion = Base64.getEncoder().encodeToString(binding.etConclusion.getText().toString().trim().getBytes());
                }
            }

            Call<ResponseModel> call = Controller.getInstance()
                    .getApiInterface()
                    .updateProject(file_id, email_id, project_name, aim_of_project,
                            description, image_1, image_2, image_3, image_4, image_5,
                            image_6, video, pdf_file, conclusion);

            call.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    ResponseModel responseModel = response.body();
                    String output = responseModel.getMessage();
                    Toast.makeText(Edit_Project_Activity.this, output, Toast.LENGTH_SHORT).show();
                    binding.spinKit.setVisibility(View.GONE);
                    Intent intent = new Intent(Edit_Project_Activity.this, ProjectFragment.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    binding.spinKit.setVisibility(View.GONE);
                    Toast.makeText(Edit_Project_Activity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Edit_Project_Activity.this, ProjectFragment.class);
                    startActivity(intent);
                }
            });
        });
    }


    public static String fileUriToBase64(Uri uri, ContentResolver resolver) {
        String encodedBase64 = "";
        try {
            byte[] bytes = readBytes(uri, resolver);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                encodedBase64 = Base64.getEncoder().encodeToString(bytes);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return encodedBase64;
    }

    private static byte[] readBytes(Uri uri, ContentResolver resolver)
            throws IOException {
        InputStream inputStream = resolver.openInputStream(uri);
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }

        return byteBuffer.toByteArray();
    }

    private void imageView1() {
        Intent pickImage1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImage1, REQ_imageView1);
    }

    private void imageView2() {
        Intent pickImage2 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImage2, REQ_imageView2);
    }

    private void imageView3() {
        Intent pickImage3 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImage3, REQ_imageView3);
    }

    private void imageView4() {
        Intent pickImage4 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImage4, REQ_imageView4);
    }

    private void imageView5() {
        Intent pickImage5 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImage5, REQ_imageView5);
    }

    private void imageView6() {
        Intent pickImage6 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImage6, REQ_imageView6);
    }

    private void openDocument() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivityForResult(Intent.createChooser(intent, "Select Pdf File"), REQ_pdf);
    }

    private void videoVideo() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQ_video);
    }

    @SuppressLint({"Range", "Recycle"})
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_imageView1 && resultCode == RESULT_OK && data != null) {
            image_Uri_1 = data.getData();
            image_1 = fileUriToBase64(image_Uri_1, getContentResolver());
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), image_Uri_1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            binding.imageView1.setImageBitmap(bitmap);

        } else if (requestCode == REQ_imageView2 && resultCode == RESULT_OK && data != null) {
            image_Uri_2 = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), image_Uri_2);
                image_2 = fileUriToBase64(image_Uri_2, getContentResolver());
            } catch (IOException e) {
                e.printStackTrace();
            }
            binding.imageView2.setImageBitmap(bitmap);

        } else if (requestCode == REQ_imageView3 && resultCode == RESULT_OK && data != null) {
            image_Uri_3 = data.getData();

            image_3 = fileUriToBase64(image_Uri_3, getContentResolver());
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), image_Uri_3);
            } catch (IOException e) {
                e.printStackTrace();
            }
            binding.imageView3.setImageBitmap(bitmap);

        } else if (requestCode == REQ_imageView4 && resultCode == RESULT_OK && data != null) {
            image_Uri_4 = data.getData();

            image_4 = fileUriToBase64(image_Uri_4, getContentResolver());
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), image_Uri_4);
            } catch (IOException e) {
                e.printStackTrace();
            }
            binding.imageView4.setImageBitmap(bitmap);

        } else if (requestCode == REQ_imageView5 && resultCode == RESULT_OK && data != null) {
            image_Uri_5 = data.getData();
            image_5 = fileUriToBase64(image_Uri_5, getContentResolver());
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), image_Uri_5);
            } catch (IOException e) {
                e.printStackTrace();
            }
            binding.imageView5.setImageBitmap(bitmap);

        } else if (requestCode == REQ_imageView6 && resultCode == RESULT_OK && data != null) {
            image_Uri_6 = data.getData();
            image_6 = fileUriToBase64(image_Uri_6, getContentResolver());

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), image_Uri_6);
            } catch (IOException e) {
                e.printStackTrace();
            }
            binding.imageView6.setImageBitmap(bitmap);

        } else if (requestCode == REQ_pdf && resultCode == RESULT_OK && data != null) {
            pdf_Uri = data.getData();
            pdf_file = fileUriToBase64(pdf_Uri, getContentResolver());
            if (pdf_Uri.toString().startsWith("content://")) {
                Cursor cursor;
                try {
                    cursor = Edit_Project_Activity.this.getContentResolver().query(pdf_Uri, null, null, null, null);
                    if (cursor != null && cursor.moveToFirst()) {
                        pdfName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (pdf_Uri.toString().startsWith("file://")) {
                pdfName = new File(pdf_Uri.toString()).getName();
            }

            binding.pdfTextview.setText(pdfName);


        } else if (requestCode == REQ_video && resultCode == RESULT_OK && data != null) {
            video_Uri = data.getData();
            video = fileUriToBase64(video_Uri, getContentResolver());
            binding.videoView.setVideoURI(video_Uri);
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