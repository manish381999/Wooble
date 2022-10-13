package com.wooble.wooble.ui.Gallery;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wooble.wooble.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {
FragmentGalleryBinding binding;

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

        return binding.getRoot();
    }
}