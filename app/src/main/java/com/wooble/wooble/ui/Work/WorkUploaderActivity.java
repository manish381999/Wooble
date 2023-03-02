package com.wooble.wooble.ui.Work;



import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;

import android.content.Context;

import android.graphics.Bitmap;


import android.net.Uri;

import android.os.Bundle;

import android.view.View;

import android.view.inputmethod.InputMethodManager;
import android.widget.MediaController;

import com.wooble.wooble.databinding.ActivityworkuploaderBinding;


import java.util.Objects;



public class WorkUploaderActivity extends AppCompatActivity {
    ActivityworkuploaderBinding binding;

    final int REQ_imageView1 = 10;
    final int REQ_imageView2 = 20;
    final int REQ_imageView3 = 30;
    final int REQ_imageView4 = 40;
    final int REQ_imageView5 = 50;
    final int REQ_imageView6 = 60;

    Uri image_Uri, pdf_Uri, video_Uri;

    private String pdfName;
    private Bitmap bitmap;

    String output;


    final int REQ_pdf = 70;
    final int REQ_video = 80;

    MediaController mediaController;
    String  email_id, project_name, aim_of_project, description, image_1, image_2, image_3, image_4, image_5, image_6, video, pdf_file, conclusion;

    @SuppressLint("ObsoleteSdkInt")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityworkuploaderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        setSupportActionBar(binding.imToolbar);




        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.blogEditor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.toolBox.setVisibility(View.VISIBLE);
                } else {
                    binding.toolBox.setVisibility(View.GONE);
                }
            }
        });

        binding.blogEditor.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(binding.blogEditor, InputMethodManager.SHOW_IMPLICIT);






//        email_id = project_name = aim_of_project = description = image_1 = image_2 = image_3 = image_4 = image_5 = image_6 = video = pdf_file = conclusion = "TlVMTA==";
//        mediaController = new MediaController(this);
//        mediaController.setAnchorView(binding.videoView);
//        binding.videoView.setMediaController(mediaController);
//        binding.videoView.stopPlayback();
//
//
//        binding.imageView1.setOnClickListener(view -> imageView1());
//
//        binding.imageView2.setOnClickListener(view -> imageView2());
//
//        binding.imageView3.setOnClickListener(view -> imageView3());
//
//        binding.imageView4.setOnClickListener(view -> imageView4());
//
//        binding.imageView5.setOnClickListener(view -> imageView5());
//
//        binding.imageView6.setOnClickListener(view -> imageView6());
//
//        binding.addPdf.setOnClickListener(view -> openDocument());
//
//
//        binding.videoView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                videoVideo();
//            }
//        });
//
//        binding.btUploadProject.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //binding.btUploadProject.setEnabled(false);
//                binding.spinKit.setVisibility(View.VISIBLE);
//                SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
//                email_id = sessionManagement.getSessionEmail();
//                project_name = binding.etProjectName.getText().toString().trim();
//                if (binding.etProjectAim.getText().toString().trim() != null) {
//                    aim_of_project = binding.etProjectAim.getText().toString().trim();
//                }
//                if (binding.aboutYourself.getText().toString().trim() != null) {
//                    description = binding.aboutYourself.getText().toString().trim();
//                }
//                if (binding.etConclusion.getText().toString().trim() != null) {
//                    conclusion = binding.etConclusion.getText().toString().trim();
//                }
//                Call<ResponseModel> call = Controller.getInstance()
//                        .getApiInterface()
//                        .insertProject(project_name, description, email_id,  image_1, image_2, image_3, image_4, image_5, image_6, video, conclusion, pdf_file, aim_of_project);
//
//                call.enqueue(new Callback<ResponseModel>() {
//                    @Override
//                    public void onResponse(Call<ResponseModel> call, retrofit2.Response<ResponseModel> response) {
//                        ResponseModel responseModel = response.body();
//                        output = responseModel.getMessage();
//                        Toast.makeText(WorkUploaderActivity.this, output, Toast.LENGTH_SHORT).show();
//                        binding.spinKit.setVisibility(View.GONE);
//                        Intent intent = new Intent(WorkUploaderActivity.this, ProjectFragment.class);
//                        startActivity(intent);
//                        finish();
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseModel> call, Throwable t) {
//                        binding.spinKit.setVisibility(View.GONE);
//                        Toast.makeText(WorkUploaderActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//                        //t.printStackTrace();
//                        //Intent intent = new Intent(Upload_Project_Activity.this, ProjectFragment.class);
//                        //startActivity(intent);
//                        //finish();
//                    }
//                });
//            }
//        });
//
//    }
//
//    public static String fileUriToBase64(Uri uri, ContentResolver resolver) {
//        String encodedBase64 = "";
//        try {
//            byte[] bytes = readBytes(uri, resolver);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                encodedBase64 = Base64.getEncoder().encodeToString(bytes);
//            }
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
//        return encodedBase64;
//    }
//
//    private static byte[] readBytes(Uri uri, ContentResolver resolver)
//            throws IOException {
//        InputStream inputStream = resolver.openInputStream(uri);
//        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
//
//        int bufferSize = 1024;
//        byte[] buffer = new byte[bufferSize];
//
//        int len = 0;
//        while ((len = inputStream.read(buffer)) != -1) {
//            byteBuffer.write(buffer, 0, len);
//        }
//
//        return byteBuffer.toByteArray();
//    }
//
//    private void imageView1() {
//        Intent pickImage1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(pickImage1, REQ_imageView1);
//    }
//
//    private void imageView2() {
//        Intent pickImage2 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(pickImage2, REQ_imageView2);
//    }
//
//    private void imageView3() {
//        Intent pickImage3 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(pickImage3, REQ_imageView3);
//    }
//
//    private void imageView4() {
//        Intent pickImage4 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(pickImage4, REQ_imageView4);
//    }
//
//    private void imageView5() {
//        Intent pickImage5 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(pickImage5, REQ_imageView5);
//    }
//
//    private void imageView6() {
//        Intent pickImage6 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(pickImage6, REQ_imageView6);
//    }
//
//    private void openDocument() {
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        intent.setType("application/pdf");
//        startActivityForResult(Intent.createChooser(intent, "Select Pdf File"), REQ_pdf);
//    }
//
//    private void videoVideo() {
//        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(intent, REQ_video);
//    }
//
//    @SuppressLint({"Range", "Recycle"})
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQ_imageView1 && resultCode == RESULT_OK && data != null) {
//            image_Uri = data.getData();
//
//            image_1 = fileUriToBase64(image_Uri, getContentResolver());
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), image_Uri);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            binding.imageView1.setImageBitmap(bitmap);
//
//        } else if (requestCode == REQ_imageView2 && resultCode == RESULT_OK && data != null) {
//            image_Uri = data.getData();
//
//            image_2 = fileUriToBase64(image_Uri, getContentResolver());
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), image_Uri);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            binding.imageView2.setImageBitmap(bitmap);
//
//        } else if (requestCode == REQ_imageView3 && resultCode == RESULT_OK && data != null) {
//            image_Uri = data.getData();
//
//            image_3 = fileUriToBase64(image_Uri, getContentResolver());
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), image_Uri);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            binding.imageView3.setImageBitmap(bitmap);
//
//        } else if (requestCode == REQ_imageView4 && resultCode == RESULT_OK && data != null) {
//            image_Uri = data.getData();
//
//            image_4 = fileUriToBase64(image_Uri, getContentResolver());
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), image_Uri);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            binding.imageView4.setImageBitmap(bitmap);
//
//        } else if (requestCode == REQ_imageView5 && resultCode == RESULT_OK && data != null) {
//            image_Uri = data.getData();
//
//            image_5 = fileUriToBase64(image_Uri, getContentResolver());
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), image_Uri);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            binding.imageView5.setImageBitmap(bitmap);
//
//        } else if (requestCode == REQ_imageView6 && resultCode == RESULT_OK && data != null) {
//            image_Uri = data.getData();
//            image_6 = fileUriToBase64(image_Uri, getContentResolver());
//
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), image_Uri);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            binding.imageView6.setImageBitmap(bitmap);
//
//        } else if (requestCode == REQ_pdf && resultCode == RESULT_OK && data != null) {
//            pdf_Uri = data.getData();
//
//            pdf_file = fileUriToBase64(pdf_Uri, getContentResolver());
//            if (pdf_Uri.toString().startsWith("content://")) {
//                Cursor cursor;
//                try {
//                    cursor = WorkUploaderActivity.this.getContentResolver().query(pdf_Uri, null, null, null, null);
//                    if (cursor != null && cursor.moveToFirst()) {
//                        pdfName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            } else if (pdf_Uri.toString().startsWith("file://")) {
//                pdfName = new File(pdf_Uri.toString()).getName();
//            }
//
//            binding.pdfTextview.setText(pdfName);
//
//
//        } else if (requestCode == REQ_video && resultCode == RESULT_OK && data != null) {
//            video_Uri = data.getData();
//            video = fileUriToBase64(video_Uri, getContentResolver());
//            binding.videoView.setVideoURI(video_Uri);
//        }
//
//
//    }
//



   }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}