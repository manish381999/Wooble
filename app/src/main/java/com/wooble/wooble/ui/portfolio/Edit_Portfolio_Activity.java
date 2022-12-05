package com.wooble.wooble.ui.portfolio;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

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
import com.wooble.wooble.MainActivity;
import com.wooble.wooble.R;
import com.wooble.wooble.SessionManagement;
import com.wooble.wooble.databinding.ActivityEditPortfolioBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class Edit_Portfolio_Activity extends AppCompatActivity {
ActivityEditPortfolioBinding binding;
    final int REQ = 13;
    private Bitmap bitmap;

    String designation;

    byte[] fileImage;
    ActivityResultLauncher<String> pickImage;
    private String profileEmail;

    String[] item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityEditPortfolioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).setTitle("Edit Portfolio");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadProfileData();
        
        binding.btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateFullProfileData();

            }
        });


        item = new String[]{"Choose your Profession", "STUDENT", "SOFTWARE DEVELOPER", "VIDEO EDITOR", "RESEARCHER",
                "MARKETING STRATEGIST", "ARCHITECT", "FASHION DESIGNER", "CEO/FOUNDER", "OTHER"};

        binding.professionCategory.setAdapter(new ArrayAdapter<String>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, item));



        binding.professionCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                designation=binding.professionCategory.getSelectedItem().toString();
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
              Intent intent=new Intent(Edit_Portfolio_Activity.this, CircularImageCropperActivity.class);
              intent.putExtra("DATA", result.toString());
              startActivityForResult(intent ,REQ);
          }
      });
    }

    private void openGallery(){
      pickImage.launch("image/*");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == REQ && resultCode==RESULT_OK && data!=null){
                String result=data.getStringExtra("RESULT");
                Uri resultUri=null;
                if (result!=null){
                    resultUri=Uri.parse(result);
                }

                try {
                    bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),resultUri);

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
                binding.profilePic.setImageBitmap(bitmap);
            }
        }

    public void loadProfileData() {
        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        profileEmail = sessionManagement.getSessionEmail();
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, EndPoints.GET_FULL_PROFILE,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            JSONArray array = new JSONArray(new String(response.data));
                            JSONObject jObj = array.getJSONObject(0);
                            binding.username.setText(jObj.getString("username"));
                            binding.fullName.setText(jObj.getString("fullname"));
                            binding.aboutYourself.setText(jObj.getString("background"));
                            binding.etFbLink.setText(jObj.getString("fb_link"));
                            binding.instagramLink.setText(jObj.getString("insta_link"));
                            binding.etLinkedInLink.setText(jObj.getString("insta_link"));
                            binding.twitterLink.setText(jObj.getString("twitter_link"));
                            binding.etWhatsappNo.setText(jObj.getString("whatsapp_link"));
                            String data = jObj.getString("profile_pic");
                            for(int i = 0; i < item.length; i++)
                            {
                                if(item[i].equals(jObj.getString("designation")))
                                {
                                    binding.professionCategory.setSelection(i);
                                }
                            }
                            Glide.with(Edit_Portfolio_Activity.this)
                                    .load(jObj.getString("profile_pic"))
                                    .placeholder(R.drawable.place_holder)
                                    .centerCrop()
                                    .into(binding.profilePic);

                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        URL url = new URL(data);
                                        Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                        image.compress(Bitmap.CompressFormat.WEBP, 20, byteArrayOutputStream);
                                        fileImage = byteArrayOutputStream.toByteArray();
                                    } catch(IOException e) {
                                        System.out.println(e);
                                    }
                                }
                            });
                            thread.start();

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
                params.put("profileEmail", profileEmail);
                return params;
            }

        };

        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }

    private void updateFullProfileData() {

        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        profileEmail = sessionManagement.getSessionEmail();

        String username = binding.username.getText().toString().trim();
        String fullname = binding.fullName.getText().toString().trim();
        String about_your_self = binding.aboutYourself.getText().toString().trim();
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
                            Intent intent = new Intent(Edit_Portfolio_Activity.this, MainActivity.class);
                            startActivity(intent);
                            finishAffinity();
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
                params.put("pic", new DataPart(imagename + ".png", fileImage));
                return params;
            }

        };

        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }
    }
