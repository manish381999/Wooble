package com.wooble.wooble.ui.Resume;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.wooble.wooble.SessionManagement;
import com.wooble.wooble.databinding.ActivityUploadResumeBinding;
import com.wooble.wooble.ui.Blogs.Controller;
import com.wooble.wooble.ui.Blogs.ResponseModel;
import com.yalantis.ucrop.util.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class UploadResumeActivity extends AppCompatActivity {
ActivityUploadResumeBinding binding;

    final int REQ_pdf = 70;
    Uri pdf_Uri;
    private String pdfName ;
    String resume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUploadResumeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


Objects.requireNonNull(getSupportActionBar()).setTitle("Resume Uploader");

getSupportActionBar().setDisplayHomeAsUpEnabled(true);

binding.uploadPdfBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
//        Intent intent=new Intent(UploadResumeActivity.this, ResumeFragment.class);
//        startActivity(intent);
        insertData();
    }
});
        binding.addPdf.setOnClickListener(view -> openDocument());
    }


    private void openDocument() {
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivityForResult(Intent.createChooser(intent,"Select Pdf File"),REQ_pdf);
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
        // this dynamically extends to take the bytes you read
        InputStream inputStream = resolver.openInputStream(uri);
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

        // this is storage overwritten on each iteration with bytes
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        // we need to know how may bytes were read to write them to the
        // byteBuffer
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }

        // and then we can return your byte array.
        return byteBuffer.toByteArray();
    }


    @SuppressLint({"Range", "Recycle"})
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_pdf && resultCode == RESULT_OK && data != null) {
            pdf_Uri = data.getData();
           resume =  fileUriToBase64(pdf_Uri, getContentResolver());
            if (pdf_Uri.toString().startsWith("content://")) {
                Cursor cursor;
                try {
                    cursor = UploadResumeActivity.this.getContentResolver().query(pdf_Uri, null, null, null, null);
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
        }
    }

    void insertData(){
        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        String email_id = sessionManagement.getSessionEmail();
        String title = binding.pdfTitle.getText().toString().trim();

        Call<ResponseModel> call = Controller.getInstance()
                .getApiInterface()
                .insertResume(email_id,title,resume);

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, retrofit2.Response<ResponseModel> response) {
                ResponseModel responseModel = response.body();
                String output = responseModel.getMessage();
                Toast.makeText(UploadResumeActivity.this, output, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(UploadResumeActivity.this, ResumeFragment.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(UploadResumeActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
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