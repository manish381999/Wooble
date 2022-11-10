package com.wooble.wooble.ui.Resume;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.MenuItem;
import android.view.View;


import com.wooble.wooble.databinding.ActivityUploadResumeBinding;

import java.io.File;
import java.util.Objects;

public class UploadResumeActivity extends AppCompatActivity {
ActivityUploadResumeBinding binding;

    final int REQ_pdf = 70;
    Uri pdf_Uri;
    private String pdfName ;
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
        Intent intent=new Intent(UploadResumeActivity.this, ResumeFragment.class);
        startActivity(intent);
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

    @SuppressLint({"Range", "Recycle"})
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_pdf && resultCode == RESULT_OK && data != null) {
            pdf_Uri = data.getData();

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
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==android.R.id.home){
            onBackPressed();

        }
        return true;
    }
}