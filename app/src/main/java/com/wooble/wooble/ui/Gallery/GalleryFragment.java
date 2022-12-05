package com.wooble.wooble.ui.Gallery;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.wooble.wooble.SessionManagement;


import com.wooble.wooble.databinding.FragmentGalleryBinding;
import com.wooble.wooble.ui.portfolio.EndPoints;
import com.wooble.wooble.ui.portfolio.VolleyMultipartRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GalleryFragment extends Fragment {
FragmentGalleryBinding binding;

    List<GalleryModel> galleryList;

    //the recyclerview
    RecyclerView recyclerView;

    String profileEmail;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentGalleryBinding.inflate(inflater, container, false);
        requireActivity().setTitle("Gallery");
        binding.addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),ImageUploaderActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = binding.RvGallery;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        galleryList = new ArrayList<>();
        loadGalleryImage();
        return binding.getRoot();
    }

    public void loadGalleryImage() {
        SessionManagement sessionManagement = new SessionManagement(getContext());
        profileEmail = sessionManagement.getSessionEmail();
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, EndPoints.GET_GALLERY_DATA,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            //Log.d("image","image");
                            JSONArray array = new JSONArray(new String(response.data));
                            //Toast.makeText(getApplicationContext(), obj.getString("image"), Toast.LENGTH_SHORT).show();
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                galleryList.add(0,new GalleryModel(
                                        product.getString("id"),
                                        product.getString("image"),
                                        product.getString("title"),
                                        product.getString("description")
                                ));
                            }

                            GalleryAdapter adapter = new GalleryAdapter(getContext(), galleryList);
                            recyclerView.setAdapter(adapter);
                            binding.shimmerViewContainer.stopShimmer();
                            binding.shimmerLayout.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        binding.shimmerViewContainer.stopShimmer();
                        binding.shimmerLayout.setVisibility(View.GONE);
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("profileEmail", profileEmail);
                return params;
            }

        };
        Volley.newRequestQueue(getContext()).add(volleyMultipartRequest);
    }

    @Override
    public void onPause() {
        binding.shimmerViewContainer.startShimmer();
        super.onPause();
    }

    @Override
    public void onResume(){
        binding.shimmerViewContainer.startShimmer();
        super.onResume();
    }
}