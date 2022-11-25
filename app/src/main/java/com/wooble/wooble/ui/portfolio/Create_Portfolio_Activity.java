package com.wooble.wooble.ui.portfolio;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;


import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;


import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import com.wooble.wooble.CircularImageCropperActivity;
import com.wooble.wooble.R;
import com.wooble.wooble.SessionManagement;
import com.wooble.wooble.databinding.ActivityCreatePortfolioBinding;


import org.json.JSONArray;
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
     String profileImage;
    String designation;

    ActivityResultLauncher<String> pickImage;
//    private  String userName, fullName, backGround, fb_Link, insta_Link, linkedin_Link, twitter_Link, whatsapp_Link, desigNation ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreatePortfolioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //loadProfileImage();
        Objects.requireNonNull(getSupportActionBar()).setTitle("Create Portfolio");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateFullProfileData();
            }
        });

        String[] item = new String[]{"Choose your Profession", "STUDENT", "SOFTWARE DEVELOPER", "VIDEO EDITOR", "RESEARCHER",
                "MARKETING STRATEGIST", "ARCHITECT", "FASHION DESIGNER", "CEO/FOUNDER", "OTHER"};

        binding.professionCategory.setAdapter(new ArrayAdapter<String>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, item));


        binding.professionCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                designation = binding.professionCategory.getSelectedItem().toString();
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



        pickImage=registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
               Intent intent=new Intent(Create_Portfolio_Activity.this, CircularImageCropperActivity.class);
               intent.putExtra("DATA", result.toString());
               startActivityForResult(intent, REQ);
            }
        });
    }

    private void openGallery() {
        pickImage.launch("image/*");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ){
            String result=data.getStringExtra("RESULT");
            Uri resultUri=null;
            if (result!=null){
                resultUri=Uri.parse(result);
            }
            try {
                bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),resultUri);
                //uploadProfileBitmap(bitmap);
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

    private void uploadProfileBitmap(final Bitmap bitmap) {
        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        profileEmail = sessionManagement.getSessionEmail();
        //our custom volley request
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, EndPoints.UPLOAD_ONLY_PROFILE_PIC,
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
                return params;
            }

            /*
             * Here we are passing image by renaming it with a unique name
             * */
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("pic", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                return params;
            }
        };

        //adding the request to volley
        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }

    public void loadProfileImage() {
        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        profileEmail = sessionManagement.getSessionEmail();
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, EndPoints.GET_ONLY_PROFILE_PIC,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            JSONArray array = new JSONArray(new String(response.data));
                            //Toast.makeText(getApplicationContext(), obj.getString("image"), Toast.LENGTH_SHORT).show();
                            JSONObject jObj = array.getJSONObject(0);
                            profileImage = jObj.getString("image");
                            Glide.with(Create_Portfolio_Activity.this)
                                    .load(profileImage)
                                    .placeholder(R.drawable.place_holder)
                                    .centerCrop()
                                    .into(binding.profilePic);
                            //Log.d("image",date);
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
                return params;
            }

        };

        //adding the request to volley
        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }

    private void updateFullProfileData() {

        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        profileEmail = sessionManagement.getSessionEmail();

        String username = binding.username.getText().toString().trim();
        String fullname = binding.fullName.getText().toString().trim();
        String about_your_self = binding.aboutYourself.getText().toString().trim();
        //String designation = binding.professionCategory.getSelectedItem().toString().trim();
        String fblink = binding.etFbLink.getText().toString().trim();
        String instalink = binding.instagramLink.getText().toString().trim();
        String linkedinlink = binding.etLinkedInLink.getText().toString().trim();
        String twitterlink = binding.twitterLink.getText().toString().trim();
        String whatsapplink = binding.etWhatsappNo.getText().toString().trim();

        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, EndPoints.UPLOAD_FULL_PROFILE,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            JSONObject obj = new JSONObject(new String(response.data));
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Create_Portfolio_Activity.this, PortfolioFragment.class);
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
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("profileEmail", profileEmail);
                params.put("username", username);
                params.put("fullname", fullname);
                params.put("designation", designation);
                params.put("background", about_your_self);
                params.put("fb_link", fblink);
                params.put("insta_link", instalink);
                params.put("linkedin_link", linkedinlink);
                params.put("twitter_link", twitterlink);
                params.put("whatsapp_link", whatsapplink);
                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("pic", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
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