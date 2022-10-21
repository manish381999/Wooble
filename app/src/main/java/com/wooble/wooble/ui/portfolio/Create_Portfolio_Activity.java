package com.wooble.wooble.ui.portfolio;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;

import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.wooble.wooble.R;
import com.wooble.wooble.SessionManagement;
import com.wooble.wooble.databinding.ActivityCreatePortfolioBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Create_Portfolio_Activity extends AppCompatActivity {
    ActivityCreatePortfolioBinding binding;
    final int REQ = 11;
    private Bitmap bitmap;

     String profileEmail;
//     EditText username;
//     EditText fullname;
//     EditText background;
//     EditText fb_link;
//     EditText insta_link;
//     EditText linkedin_link;
//     EditText twitter_link;
//     EditText whatsapp_link;
    private  String userName, fullName, backGround, fb_Link, insta_Link, linkedin_Link, twitter_Link, whatsapp_Link, desigNation ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        username = findViewById(R.id.username);
//        fullname = findViewById(R.id.full_name);
//        background = findViewById(R.id.about_yourself);
//        fb_link = findViewById(R.id.fb_link);
//        insta_link = findViewById(R.id.instagram_link);
//        linkedin_link = findViewById(R.id.et_LinkedIn_Link);
//        twitter_link = findViewById(R.id.twitter_link);
//        whatsapp_link = findViewById(R.id.et_whatsapp_no);

        binding = ActivityCreatePortfolioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).setTitle("Create Portfolio");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
//                Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
//                    Uri.parse("package:" + getPackageName()));
//            finish();
//            startActivity(intent);
//            return;
//        }

        binding.btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadBitmap(bitmap);
                Intent intent = new Intent(Create_Portfolio_Activity.this, PortfolioFragment.class);
                startActivity(intent);
                finish();

            }
        });

        String[] item = new String[]{"Choose your Profession", "STUDENT", "SOFTWARE DEVELOPER", "VIDEO EDITOR", "RESEARCHER",
                "MARKETING STRATEGIST", "ARCHITECT", "FASHION DESIGNER", "CEO/FOUNDER", "OTHER"};

        binding.professionCategory.setAdapter(new ArrayAdapter<String>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, item));


        binding.professionCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                desigNation = binding.professionCategory.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

    }

    private void openGallery() {
        Intent pickImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImage, REQ);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            binding.profilePic.setImageBitmap(bitmap);

        }
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
    private void uploadBitmap(final Bitmap bitmap) {

        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        profileEmail = sessionManagement.getSessionEmail();
        userName = binding.username.getText().toString().trim();
        fullName = binding.fullName.getText().toString().trim();
        backGround = binding.aboutYourself.getText().toString().trim();
        fb_Link = binding.etFbLink.getText().toString().trim();
        insta_Link = binding.instagramLink.getText().toString().trim();
        linkedin_Link = binding.etLinkedInLink.getText().toString().trim();
        twitter_Link = binding.twitterLink.getText().toString().trim();
        whatsapp_Link = binding.etWhatsappNo.getText().toString().trim();

//        System.out.println("hello"+ profileEmail);
//        System.out.println("hello"+ fullName);
//        System.out.println("hello"+ userName);
//        System.out.println("hello"+ desigNation);
//        System.out.println("hello"+ fb_Link);
//        System.out.println("hello"+ insta_Link);
//        System.out.println("hello"+ profileEmail);
//        System.out.println("hello"+ twitter_Link);
//        System.out.println("hello"+ whatsapp_Link);
//        System.out.println("hello"+ backGround);
        //getting the tag from the edittext
        //final String username = username.getText().toString().trim();

        //our custom volley request
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, EndPoints.UPLOAD_URL,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            JSONObject obj = new JSONObject(new String(response.data));
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {

            /*
             * If you want to add more parameters with the image
             * you can do it here
             * here we have only one parameter with the image
             * which is tags
             * */
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("profileEmail", profileEmail);
                params.put("fullname", fullName);
                params.put("username", userName);
                params.put("designation", desigNation);
                params.put("fb_link", fb_Link);
                params.put("insta_link", insta_Link);
                params.put("linkedin_link", insta_Link);
                params.put("twitter_link", twitter_Link);
                params.put("whatsapp_link", whatsapp_Link);
                params.put("background", backGround);
                return params;
            }

            /*
             * Here we are passing image by renaming it with a unique name
             * */
            @Override
            protected Map<String, VolleyMultipartRequest.DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("pic", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                System.out.println("hello"+ imagename);
                return params;
            }
        };

        //adding the request to volley
        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }
}