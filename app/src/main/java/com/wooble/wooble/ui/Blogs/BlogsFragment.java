package com.wooble.wooble.ui.Blogs;

import android.content.Intent;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wooble.wooble.SessionManagement;
import com.wooble.wooble.databinding.FragmentBlogsBinding;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BlogsFragment extends Fragment {
    FragmentBlogsBinding binding;

    ArrayList<BlogModel> blogList;

    //the recyclerview
    RecyclerView recyclerView;
    private String profileEmail;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentBlogsBinding.inflate(inflater, container, false);

      requireActivity().setTitle("Blogs");

        recyclerView = binding.RvBlog;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        blogList = new ArrayList<>();

        loadBlogData();
        binding.addBlogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent=new Intent(getActivity(),Create_BlogsActivity.class);
                startActivity(intent);
            }
        });

        return binding.getRoot();
    }

    public void loadBlogData() {
        SessionManagement sessionManagement = new SessionManagement(getContext());
        profileEmail = sessionManagement.getSessionEmail();
        Call<ArrayList<BlogModel>> call = Controller.getInstance()
                .getApiInterface()
                .getAllBlogs(profileEmail);

        call.enqueue(new Callback<ArrayList<BlogModel>>() {
            @Override
            public void onResponse(Call<ArrayList<BlogModel>> call, Response<ArrayList<BlogModel>> response) {
                blogList = response.body();
                Collections.reverse(blogList);
                for (int i = 0; i < blogList.size(); i++) {

                    BlogAdapter blogAdapter = new BlogAdapter(getContext(),blogList);

                    binding.RvBlog.setAdapter(blogAdapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<BlogModel>> call, Throwable t) {

            }
        });
    }



}