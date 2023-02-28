package com.wooble.wooble.ui.Gallery;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.Toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.wooble.wooble.R;
import com.wooble.wooble.databinding.ActivityImageUploaderBinding;


import java.io.IOException;
import java.util.Objects;

public class ImageUploaderActivity extends AppCompatActivity {
    ActivityImageUploaderBinding binding;

    String data;


//    final int REQ = 12;
//    private Bitmap bitmap;
//    String return_id;
//    String profileEmail;
//
//    byte[] fileImage;
//    String title, description;
//    ActivityResultLauncher<String> pickImage;


    @SuppressLint("UseSupportActionBar")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImageUploaderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        setSupportActionBar(binding.imToolbar);


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        data=getIntent().getStringExtra("image");




        Glide.with(getApplicationContext())
                .load(Uri.parse(data))
                .into(binding.ivImage);




//        binding.btUpload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                uploadGalleryData();
//            }
//        });
//        binding.galleryImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openGallery();
//            }
//        });

//        pickImage = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
//            @Override
//            public void onActivityResult(Uri result) {
//                Intent intent = new Intent(ImageUploaderActivity.this, Gallery_Image_CropperActivity.class);
//                intent.putExtra("DATA", result.toString());
//                startActivityForResult(intent, REQ);
//            }
//        });
//
//    }
//
//    private void openGallery() {
//        pickImage.launch("image/*");
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == REQ) {
//            String result = data.getStringExtra("RESULT");
//            Uri resultUri = null;
//            if (result != null) {
//                resultUri = Uri.parse(result);
//            }
//
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), resultUri);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            binding.galleryImage.setImageBitmap(bitmap);
//        }
//    }
//
//
//    private void uploadGalleryData() {
//        binding.spinKit.setVisibility(View.VISIBLE);
//        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
//        profileEmail = sessionManagement.getSessionEmail();
//        String title = binding.imageTitle.getText().toString().trim();
//        String description = binding.imageDescription.getText().toString().trim();
//
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.WEBP, 20, byteArrayOutputStream);
//        fileImage = byteArrayOutputStream.toByteArray();
//
//        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, EndPoints.INSERT_GALLERY_DATA,
//                new Response.Listener<NetworkResponse>() {
//                    @Override
//                    public void onResponse(NetworkResponse response) {
//                        try {
//                            JSONObject obj = new JSONObject(new String(response.data));
//                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
//                            binding.spinKit.setVisibility(View.GONE);
//                            Intent intent = new Intent(ImageUploaderActivity.this, GalleryFragment.class);
//                            startActivity(intent);
//                            finish();
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                }) {
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("title", title);
//                params.put("description", description);
//                params.put("email_id", profileEmail);
//                return params;
//            }
//
//            @Override
//            protected Map<String, DataPart> getByteData() {
//                Map<String, DataPart> params = new HashMap<>();
//                long imageName = System.currentTimeMillis();
//                params.put("pic", new DataPart(imageName + ".WEBP", fileImage));
//                return params;
//            }
//        };
//
//        Volley.newRequestQueue(this).add(volleyMultipartRequest);
//
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        if (item.getItemId() == android.R.id.home) {
//            onBackPressed();
//
//        }
//        return true;
    }


//    public Bitmap StringToBitMap(String encodedString){
//        try{
//            byte [] encodeByte = Base64.decode(encodedString,Base64.DEFAULT);
//            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
//            return bitmap;
//        }
//        catch(Exception e){
//            e.getMessage();
//            return null;
//        }
//    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}



