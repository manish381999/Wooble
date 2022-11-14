package com.wooble.wooble;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;


import android.annotation.SuppressLint;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.wooble.wooble.databinding.ActivityMainBinding;

import com.wooble.wooble.ui.Gallery.GalleryFragment;
import com.wooble.wooble.ui.Profile.ProfileActivity;
import com.wooble.wooble.ui.Setting.SettingActivity;
import com.wooble.wooble.ui.credentials.LoginActivity;
import com.wooble.wooble.ui.portfolio.Edit_Portfolio_Activity;
import com.wooble.wooble.ui.portfolio.EndPoints;
import com.wooble.wooble.ui.portfolio.VolleyMultipartRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
ActivityMainBinding binding;
    private ActionBarDrawerToggle toggle;

    String profileEmail;
    String profileImage;
    String coverImage;
    CircularImageView circularImageView;
    ImageView coverPic;
    TextView navUsername;
    TextView navProfession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_drawer);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        View headerView = navigationView.getHeaderView(0);
        navUsername = (TextView) headerView.findViewById(R.id.username);
        navProfession = (TextView) headerView.findViewById(R.id.profession);
        circularImageView = headerView.findViewById(R.id.profile_pic);
        coverPic = headerView.findViewById(R.id.cover_pic);
        loadProfileImage();
        loadProfileData();
        loadCoverImage();

        NavController navController= Navigation.findNavController(MainActivity.this,R.id.frame_layout);
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);


        toggle=new ActionBarDrawerToggle(this,binding.drawerLayout,R.string.start, R.string.close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.navigationDrawer.setNavigationItemSelectedListener(this);

    }




    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        toggle.onOptionsItemSelected(item);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navigation_profile:
                 startActivity(new Intent(MainActivity.this,ProfileActivity.class));
                 break;

            case R.id.navigation_template:
                Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();
                break;

            case R.id.navigation_edit_portfolio:
                startActivity(new Intent(MainActivity.this, Edit_Portfolio_Activity.class));
                break;

            case R.id.navigation_email_signature:
                Toast.makeText(this,"Email signature", Toast.LENGTH_SHORT).show();
                break;

            case R.id.navigation_setting:
               Intent intent=new Intent(MainActivity.this, SettingActivity.class);
                intent.putExtra("image",profileImage);
                startActivity(intent);
                break;

            case R.id.navigation_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;

            case R.id.navigation_social_media:
                Toast.makeText(this, "Social Media", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
    public void loadProfileImage() {
        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        profileEmail = sessionManagement.getSessionEmail();
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, EndPoints.GET_ONLY_PROFILE_PIC,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            //Log.d("image","image");
                            JSONArray array = new JSONArray(new String(response.data));
                            //Toast.makeText(getApplicationContext(), obj.getString("image"), Toast.LENGTH_SHORT).show();
                            JSONObject jObj = array.getJSONObject(0);
                            profileImage = jObj.getString("image");
                            //Log.d("image",profileImage);
                            Glide.with(MainActivity.this)
                                    .load(profileImage)
                                    .placeholder(R.drawable.place_holder)
                                    .centerCrop()
                                    .into(circularImageView);
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
    public void loadProfileData() {
        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        profileEmail = sessionManagement.getSessionEmail();
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, EndPoints.GET_FULL_PROFILE,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            //Log.d("image","image");
                            JSONArray array = new JSONArray(new String(response.data));
                            //Toast.makeText(getApplicationContext(), obj.getString("image"), Toast.LENGTH_SHORT).show();
                            JSONObject jObj = array.getJSONObject(0);
                            navUsername.setText(jObj.getString("fullname"));
                            navProfession.setText(jObj.getString("designation"));
                            //Log.d("fullname",jObj.getString("fullname"));

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
    public void loadCoverImage() {
        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        profileEmail = sessionManagement.getSessionEmail();
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, EndPoints.GET_ONLY_COVER_PIC,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            //Log.d("image","image");
                            JSONArray array = new JSONArray(new String(response.data));
                            //Toast.makeText(getApplicationContext(), obj.getString("image"), Toast.LENGTH_SHORT).show();
                            JSONObject jObj = array.getJSONObject(0);
                            coverImage = jObj.getString("image");
                            //Log.d("image",profileImage);
                            Glide.with(MainActivity.this)
                                    .load(coverImage)
                                    .placeholder(R.drawable.place_holder)
                                    .centerCrop()
                                    .into(coverPic);
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
}



