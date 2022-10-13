package com.wooble.wooble.ui.home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.wooble.wooble.MainActivity;
import com.wooble.wooble.R;
import com.wooble.wooble.SessionManagement;
import com.wooble.wooble.databinding.ActivityCreatePortfolioBinding;
import com.wooble.wooble.ui.ImageLoadTask;
import com.wooble.wooble.ui.credentials.LoginActivity;
import com.wooble.wooble.ui.credentials.SignupActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Create_Portfolio_Activity extends AppCompatActivity {
ActivityCreatePortfolioBinding binding;
    private String category;
    final int REQ=11;
    private Bitmap bitmap;

    private CircularImageView circularImageView;
    private Spinner spinner;
    private EditText etusername, etfullname, etbackground, etfb_link, etinsta_link, etlinkedin_link, ettwitter_link, etwhatsapp_link;

    private String username, fullname, designation, background, fb_link, insta_link, linkedin_link, twitter_link, whatsapp_link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCreatePortfolioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).setTitle("Create Portfolio");

        etusername = (EditText) findViewById(R.id.username);
        etfullname = (EditText) findViewById(R.id.full_name);
        etbackground = (EditText) findViewById(R.id.about_yourself);
        etfb_link = (EditText) findViewById(R.id.fb_link);
        etinsta_link = (EditText) findViewById(R.id.instagram_link);
        etlinkedin_link = (EditText) findViewById(R.id.LinkedIn_Link);
        ettwitter_link = (EditText) findViewById(R.id.twitter_link);
        etwhatsapp_link = (EditText) findViewById(R.id.et_whatsapp_no);
        spinner = findViewById(R.id.profession_category);
        circularImageView = findViewById(R.id.profile_pic);
        username = fullname= background= fb_link= insta_link= linkedin_link= twitter_link= whatsapp_link = "";



        binding.btSave.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              SessionManagement sessionManagement = new SessionManagement(Create_Portfolio_Activity.this);
              String userEmail = sessionManagement.getSessionEmail();

              String URL="http://172.168.2.86/api/profile.php";
              username = etusername.getText().toString().trim();
              fullname = etfullname.getText().toString().trim();
              background = etbackground.getText().toString().trim();
              fb_link = etfb_link.getText().toString().trim();
              insta_link = etinsta_link.getText().toString().trim();
              linkedin_link = etlinkedin_link.getText().toString().trim();
              twitter_link = ettwitter_link.getText().toString().trim();
              whatsapp_link = etwhatsapp_link.getText().toString().trim();


              if(!username.equals("") && !fullname.equals("") && !background.equals(""))
              {
                  StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                      @Override
                      public void onResponse(String response) {
//                            System.out.println("Response" + response);
                          if (response.equals("success")) {
                              Toast.makeText(Create_Portfolio_Activity.this, "Profile Saved", Toast.LENGTH_SHORT).show();
                              Intent intent=new Intent(Create_Portfolio_Activity.this, HomeFragment.class);
                              startActivity(intent);
                              finish();
                          } else if (response.equals("failure")) {
                              Toast.makeText(Create_Portfolio_Activity.this, "Something went wrong try again...", Toast.LENGTH_SHORT).show();
                          }

                      }
                  }, new Response.ErrorListener() {
                      @Override
                      public void onErrorResponse(VolleyError error) {
                          Toast.makeText(Create_Portfolio_Activity.this, error.toString().trim(),Toast.LENGTH_SHORT).show();

                      }
                  }){
                      @Nullable
                      @Override
                      protected Map<String, String> getParams() throws AuthFailureError {

                          Map<String,String> data = new HashMap<>();
                          data.put("userEmail", userEmail);
                          data.put("fullname",fullname);
                          data.put("username",username);
                          data.put("designation",designation);
                          data.put("background",background);
                          data.put("fb_link", fb_link);
                          data.put("insta_link", insta_link);
                          data.put("linkedin_link", linkedin_link);
                          data.put("twitter_link", twitter_link);
                          data.put("whatsapp_link", whatsapp_link);
                          return data;
                      }
                  };

                  RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                  requestQueue.add(stringRequest);

              }

          }
      });






//        binding.professionCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                category=binding.professionCategory.getSelectedItem().toString();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//

        binding.professionCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                designation=binding.professionCategory.getSelectedItem().toString().trim();
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

    @Override
    protected void onStart() {
        super.onStart();

        SessionManagement sessionManagement = new SessionManagement(Create_Portfolio_Activity.this);
        String profileEmail = sessionManagement.getSessionEmail();
        String[] item=new String[]{"Choose your Profession","STUDENT","SOFTWARE DEVELOPER","VIDEO EDITOR", "RESEARCHER",
                "MARKETING STRATEGIST", "ARCHITECT", "FASHION DESIGNER", "CEO/FOUNDER", "OTHER"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item,item);
        binding.professionCategory.setAdapter(adapter);
        // url to post our data
        String url = "http://172.168.2.86/api/retriveprofile.php";

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(Create_Portfolio_Activity.this);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // on below line passing our response to json object.
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are checking if the response is null or not.
                    if (jsonObject.getString("username") == null) {
                        // displaying a toast message if we get error
                        Toast.makeText(Create_Portfolio_Activity.this, "Please enter valid id.", Toast.LENGTH_SHORT).show();
                    } else {
                        // if we get the data then we are setting it in our text views in below line.
                        etfullname.setText(jsonObject.getString("full_name"));
                        etusername.setText(jsonObject.getString("username"));
                        int spinnerPosition = adapter.getPosition(jsonObject.getString("designation"));
                        spinner.setSelection(spinnerPosition);
                        etfb_link.setText(jsonObject.getString("fb_link"));
                        etinsta_link.setText(jsonObject.getString("insta_link"));
                        etlinkedin_link.setText(jsonObject.getString("linkedin_link"));
                        ettwitter_link.setText(jsonObject.getString("twitter_link"));
                        etwhatsapp_link.setText(jsonObject.getString("whatsapp_link"));
                        etbackground.setText(jsonObject.getString("background"));
                        new ImageLoadTask("http://172.168.2.86/api/profile_pic/6347f9a6c32b2-1665661350.jpg",circularImageView).execute();

                    }
                    // on below line we are displaying
                    // a success toast message.
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(Create_Portfolio_Activity.this, "Fail to get profile" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                // as we are passing data in the form of url encoded
                // so we are passing the content type below
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() {

                // below line we are creating a map for storing our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our key and value pair to our parameters.
                params.put("email", profileEmail);

                // at last we are returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }
    private void openGallery(){
        Intent pickImage=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImage,REQ);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQ && resultCode==RESULT_OK && data!=null){
            Uri uri=data.getData();

            System.out.println("hello"+uri);
            try{
                bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                System.out.println("hello1"+bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            binding.profilePic.setImageBitmap(bitmap);


        }
    }
}