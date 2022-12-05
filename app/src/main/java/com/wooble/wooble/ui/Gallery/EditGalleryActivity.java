package com.wooble.wooble.ui.Gallery;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.wooble.wooble.databinding.ActivityEditGalleryBinding;
import com.wooble.wooble.ui.portfolio.EndPoints;
import com.wooble.wooble.ui.portfolio.VolleyMultipartRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.net.URL;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EditGalleryActivity extends AppCompatActivity {
ActivityEditGalleryBinding binding;

    String data;
    String titleData;
    String captionData;
    String id;

    byte[] fileImage;
    final int REQ=13;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditGalleryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Edit Gallery");
        id = getIntent().getStringExtra("id");
        data = getIntent().getStringExtra("image");
        titleData = getIntent().getStringExtra("title");
        captionData = getIntent().getStringExtra("description");
        Glide.with(getApplicationContext())
                .load(data)
                .centerCrop()
                .into(binding.galleryImage);

        binding.imageTitle.setText(titleData);
        binding.imageCaption.setText(captionData);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(data);
                    Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    image.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
                    fileImage = byteArrayOutputStream.toByteArray();
                } catch(IOException e) {
                    System.out.println(e);
                }
            }
        });
        thread.start();

        binding.galleryImage.setOnClickListener(view -> openGallery());

        binding.btUpdate.setOnClickListener(v -> updateGalleryData());
    }

    private void openGallery() {
        Intent pickImage=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImage,REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQ && resultCode==RESULT_OK && data!=null){
            Uri uri=data.getData();
            try {
                bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
                    fileImage = byteArrayOutputStream.toByteArray();
                }
            });
            thread.start();

            binding.galleryImage.setImageBitmap(bitmap);
        }
    }

    private void updateGalleryData() {
        String title = binding.imageTitle.getText().toString().trim();
        String description = binding.imageCaption.getText().toString().trim();

        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, EndPoints.UPDATE_GALLERY_DATA,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            JSONObject obj = new JSONObject(new String(response.data));
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(EditGalleryActivity.this, GalleryFragment.class);
                            startActivity(intent);
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("title", title);
                params.put("description", description);
                params.put("file_id", id);
                params.put("image_path", data.substring(data.lastIndexOf('/') + 1));
                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imageName = System.currentTimeMillis();
                params.put("pic", new DataPart(imageName + ".WEBP", fileImage));
                return params;
            }
        };
        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==android.R.id.home){
            onBackPressed();

        }
        return true;
    }
}