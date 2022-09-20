package com.wooble.wooble.ui.Blogs;

import android.content.Intent;

import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import com.wooble.wooble.databinding.FragmentBlogsBinding;

import java.util.Objects;


public class BlogsFragment extends Fragment {
    FragmentBlogsBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentBlogsBinding.inflate(inflater, container, false);

requireActivity().setTitle("Blogs");

        binding.addBlogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), Create_BlogsActivity.class);
                startActivity(intent);
            }
        });

        return binding.getRoot();
    }



}