package com.wooble.wooble.ui.Project;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wooble.wooble.databinding.FragmentProjectBinding;


public class ProjectFragment extends Fragment {
FragmentProjectBinding binding;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProjectBinding.inflate(inflater, container, false);

        requireActivity().setTitle("Project");

        binding.addProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent=new Intent(getActivity(), Upload_Project_Activity.class);
              startActivity(intent);
            }
        });





        return binding.getRoot();

    }
}