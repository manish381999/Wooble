package com.wooble.wooble.ui.Resume;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.wooble.wooble.databinding.FragmentResumeBinding;


public class ResumeFragment extends Fragment {
FragmentResumeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentResumeBinding.inflate(getLayoutInflater(), container, false);

        requireActivity().setTitle("Resume");

        binding.addResume.setOnClickListener(v -> {
            Intent intent=new Intent(getActivity(), UploadResumeActivity.class);
            startActivity(intent);
        });

        return binding.getRoot();
    }
}