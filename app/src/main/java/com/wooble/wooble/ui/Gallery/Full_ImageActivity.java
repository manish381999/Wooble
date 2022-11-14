package com.wooble.wooble.ui.Gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.wooble.wooble.SessionManagement;
import com.wooble.wooble.databinding.ActivityFullImageBinding;
import com.wooble.wooble.ui.portfolio.EndPoints;
import com.wooble.wooble.ui.portfolio.VolleyMultipartRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Full_ImageActivity extends AppCompatActivity {
ActivityFullImageBinding binding;

String data;
String titleData;
String captionData;
String id;
    private String profileEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityFullImageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();

        id = getIntent().getStringExtra("id");
        data=getIntent().getStringExtra("image");
        titleData=getIntent().getStringExtra("title");
        captionData=getIntent().getStringExtra("description");



        binding.cation.setText(captionData);

        Glide.with(getApplicationContext())
                .load(data)
                .centerCrop()
                .into(binding.imageView);

        binding.ivEdit.setOnClickListener(view -> {
           Intent intent=new Intent(Full_ImageActivity.this, EditGalleryActivity.class);
            intent.putExtra("id",id);
            intent.putExtra("image",data);
            intent.putExtra("title",titleData);
            intent.putExtra("description",captionData);
            startActivity(intent);
            finish();
        });

        binding.ivDelete.setOnClickListener(view -> {
            deleteImage();
        });

    }

    private void deleteImage() {

        //our custom volley request
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, EndPoints.DELETE_GALLERY_DATA,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            JSONObject obj = new JSONObject(new String(response.data));
                            //Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(Full_ImageActivity.this, GalleryFragment.class);
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
                params.put("file_id", id);
                return params;
            }

        };

        //adding the request to volley
        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }
}