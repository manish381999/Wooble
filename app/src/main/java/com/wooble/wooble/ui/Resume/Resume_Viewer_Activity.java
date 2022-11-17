package com.wooble.wooble.ui.Resume;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.wooble.wooble.databinding.ActivityResumeViewerBinding;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;


public class Resume_Viewer_Activity extends AppCompatActivity {
    ActivityResumeViewerBinding binding;

  private String pdfUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityResumeViewerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();

        pdfUrl = getIntent().getStringExtra("pdfUrl");

        new pdfDownload().execute(pdfUrl);



    }
private class pdfDownload extends AsyncTask<String, Void, InputStream>{

    @Override
    protected InputStream doInBackground(String... strings) {
        InputStream inputStream=null;

        try {
            URL url= new URL(strings[0]);
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            if (httpURLConnection.getResponseCode()==200){
                inputStream = new BufferedInputStream(httpURLConnection.getInputStream());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
return  inputStream;
    }

    @Override
    protected void onPostExecute(InputStream inputStream) {
        binding.pdfView.fromStream(inputStream)
                .password(null)
                .defaultPage(0)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .spacing(20)
                .load();
    }
}

}