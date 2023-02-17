package com.wooble.wooble.ui.Search;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wooble.wooble.R;
import com.wooble.wooble.databinding.FragmentSearchBinding;


public class SearchFragment extends Fragment {

  FragmentSearchBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentSearchBinding.inflate(inflater, container, false);



        requireActivity().setTitle("Search");


        return binding.getRoot();
    }
}