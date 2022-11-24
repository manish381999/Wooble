package com.wooble.wooble.ui.Blogs;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.wooble.wooble.R;
import com.wooble.wooble.SessionManagement;
import com.wooble.wooble.databinding.ActivityBlogViewerBinding;
import com.wooble.wooble.ui.Gallery.GalleryAdapter;
import com.wooble.wooble.ui.Gallery.GalleryModel;
import com.wooble.wooble.ui.Project.Edit_Project_Activity;
import com.wooble.wooble.ui.Project.ProjectFragment;
import com.wooble.wooble.ui.portfolio.EndPoints;
import com.wooble.wooble.ui.portfolio.VolleyMultipartRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Blog_Viewer_Activity extends AppCompatActivity {
ActivityBlogViewerBinding binding;

    RecyclerView recyclerView;
String id,title,content,created_date,image,full_name;
    List<BlogImageModel> blogImageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityBlogViewerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

Objects.requireNonNull(getSupportActionBar()).setTitle("Blog viewer");
getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        id = getIntent().getStringExtra("id");
        full_name=getIntent().getStringExtra("full_name");
        title=getIntent().getStringExtra("title");
//        System.out.println("ID "+file_id);
        content=getIntent().getStringExtra("content");
        created_date=getIntent().getStringExtra("created_date");
        image=getIntent().getStringExtra("image");
        binding.tvTitle.setText(title);
        binding.tvDescription.setText(content);
        binding.tvDate.setText(created_date);
        binding.tvFullName.setText(full_name);
        recyclerView = binding.RvBlogImage;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        blogImageList = new ArrayList<>();

        loadBlogImages();

        binding.ivDelete.setOnClickListener(v -> {
            Call<ResponseModel> call = Controller.getInstance()
                    .getApiInterface()
                    .deleteBlog(id);

            call.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    ResponseModel responseModel = response.body();
                    String output = responseModel.getMessage();
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), output, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Blog_Viewer_Activity.this, BlogFragment.class);
                            startActivity(intent);
                        }
                    }, 2000);
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });
        });

    }


    public void loadBlogImages() {
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, EndPoints.GET_BLOG_IMAGES,
                new com.android.volley.Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            //Log.d("image","image");
                            JSONArray array = new JSONArray(new String(response.data));
                            System.out.println("Array "+array);
                            //Toast.makeText(getApplicationContext(), obj.getString("image"), Toast.LENGTH_SHORT).show();
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                blogImageList.add(0,new BlogImageModel(
                                        product.getString("image")
                                ));
                            }

                            BlogImageAdapter blogImageAdapter = new BlogImageAdapter(getApplicationContext(), blogImageList);
                            recyclerView.setAdapter(blogImageAdapter);
                           // binding.shimmerViewContainer.stopShimmer();
                            //binding.shimmerLayout.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        //binding.shimmerViewContainer.stopShimmer();
                        //binding.shimmerLayout.setVisibility(View.GONE);
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("file_id", id);
                System.out.println("file_id "+id);
                return params;
            }

        };

        //adding the request to volley
        Volley.newRequestQueue(getApplicationContext()).add(volleyMultipartRequest);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return true;
    }
}