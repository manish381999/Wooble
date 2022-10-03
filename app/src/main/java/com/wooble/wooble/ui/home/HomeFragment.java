package com.wooble.wooble.ui.home;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wooble.wooble.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {

 FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding=FragmentHomeBinding.inflate(inflater, container, false);

        requireActivity().setTitle("Home");

        binding.addPortfolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), Create_Portfolio_Activity.class);
                startActivity(intent);
            }
        });

        return binding.getRoot();
    }
}