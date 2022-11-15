package com.wooble.wooble.ui.Resume;

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
import com.wooble.wooble.databinding.FragmentResumeBinding;
import com.wooble.wooble.ui.Blogs.BlogAdapter;
import com.wooble.wooble.ui.Blogs.Controller;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ResumeFragment extends Fragment {
FragmentResumeBinding binding;
    ArrayList<ResumeModel> resumeList;
    private String profileEmail;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentResumeBinding.inflate(getLayoutInflater(), container, false);

        requireActivity().setTitle("Resume");

        recyclerView = binding.RvResume;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        resumeList = new ArrayList<>();

        loadResumeData();
        binding.addResume.setOnClickListener(v -> {
            Intent intent=new Intent(getActivity(), UploadResumeActivity.class);
            startActivity(intent);
        });

        return binding.getRoot();
    }

    private void loadResumeData() {
        SessionManagement sessionManagement = new SessionManagement(getContext());
        profileEmail = sessionManagement.getSessionEmail();

        Call<ArrayList<ResumeModel>> call = Controller.getInstance()
                .getApiInterface()
                .getResume(profileEmail);


        call.enqueue(new Callback<ArrayList<ResumeModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ResumeModel>> call, Response<ArrayList<ResumeModel>> response) {
                resumeList = response.body();
                Collections.reverse(resumeList);
                for (int i = 0; i < resumeList.size(); i++) {
                    ResumeAdapter resumeAdapter = new ResumeAdapter(getContext(),resumeList);
                    binding.RvResume.setAdapter(resumeAdapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResumeModel>> call, Throwable t) {

            }
        });
    }


}