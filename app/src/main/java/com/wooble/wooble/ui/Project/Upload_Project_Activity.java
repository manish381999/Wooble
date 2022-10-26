package com.wooble.wooble.ui.Project;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;


import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.view.View;


import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;


import com.wooble.wooble.databinding.ActivityUploadProjectBinding;


import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Upload_Project_Activity extends AppCompatActivity {
    ActivityUploadProjectBinding binding;

    final int REQ_imageView1 = 10;
    final int REQ_imageView2 = 20;
    final int REQ_imageView3 = 30;
    final int REQ_imageView4 = 40;
    final int REQ_imageView5 = 50;
    final int REQ_imageView6 = 60;
    Uri image_Uri,  pdf_Uri;;
    private String pdfName ;
    private Bitmap bitmap;

    final int REQ_pdf = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUploadProjectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).setTitle("Upload Project");


        binding.imageView1.setOnClickListener(view -> imageView1());

        binding.imageView2.setOnClickListener(view -> imageView2());

        binding.imageView3.setOnClickListener(view -> imageView3());

        binding.imageView4.setOnClickListener(view -> imageView4());

        binding.imageView5.setOnClickListener(view -> imageView5());

        binding.imageView6.setOnClickListener(view -> imageView6());


        binding.addPdf.setOnClickListener(view -> openDocument());


        ExoPlayer exoPlayer = new ExoPlayer.Builder(this).build();

        binding.VideoPlayer.setPlayer(exoPlayer);
        MediaItem mediaItem = MediaItem.fromUri("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4");
        exoPlayer.addMediaItem(mediaItem);
        exoPlayer.prepare();

//        exoPlayer.play();


//        binding.VideoPlayer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setType("video/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent,"Select Video"),1);
//            }
//        });
//
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode==1 && resultCode==RESULT_OK && data!=null){
//          uri=data.getData();
//          binding.VideoPlayer.setPlayer((Player) uri);
//
//        }


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

    private void openDocument(){
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivityForResult(Intent.createChooser(intent,"Select Pdf File"),REQ_pdf);
    }

    @SuppressLint("Range")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_imageView1 && resultCode == RESULT_OK && data != null) {
            image_Uri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), image_Uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            binding.imageView1.setImageBitmap(bitmap);

        }else if (requestCode==REQ_imageView2 && resultCode==RESULT_OK && data!=null){
            image_Uri = data.getData();

            try {
                bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(), image_Uri);
            }catch (IOException e){
                e.printStackTrace();
            }
            binding.imageView2.setImageBitmap(bitmap);

        }else if (requestCode==REQ_imageView3 && resultCode==RESULT_OK && data!=null){
            image_Uri = data.getData();

            try {
                bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(), image_Uri);
            }catch (IOException e){
                e.printStackTrace();
            }
            binding.imageView3.setImageBitmap(bitmap);

        }else if (requestCode==REQ_imageView4 && resultCode==RESULT_OK && data!=null){
            image_Uri=data.getData();

            try {
                bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(), image_Uri);
            }catch (IOException e){
                e.printStackTrace();
            }
            binding.imageView4.setImageBitmap(bitmap);

        }else if (requestCode==REQ_imageView5 && resultCode==RESULT_OK && data!=null){
            image_Uri=data.getData();

            try {
                bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(), image_Uri);
            }catch (IOException e){
                e.printStackTrace();
            }
            binding.imageView5.setImageBitmap(bitmap);

        }else if (requestCode==REQ_imageView6 && resultCode==RESULT_OK && data!=null){
            image_Uri=data.getData();

            try {
                bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(), image_Uri);
            }catch (IOException e){
                e.printStackTrace();
            }
            binding.imageView6.setImageBitmap(bitmap);

        }else if (requestCode==REQ_pdf && resultCode==RESULT_OK &&data!=null){
            pdf_Uri=data.getData();

            if (pdf_Uri.toString().startsWith("content://")){
                Cursor cursor=null;
                try {
                    cursor = Upload_Project_Activity.this.getContentResolver().query(pdf_Uri, null, null, null, null);
                    if (cursor!=null && cursor.moveToFirst()){
                        pdfName=cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else if (pdf_Uri.toString().startsWith("file://")){
                pdfName=new File(pdf_Uri.toString()).getName();
            }

            binding.pdfTextview.setText(pdfName);


        }


    }




}