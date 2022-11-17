package com.wooble.wooble.ui.Project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wooble.wooble.SessionManagement;
import com.wooble.wooble.databinding.FragmentProjectBinding;
import com.wooble.wooble.ui.Blogs.Controller;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProjectFragment extends Fragment {
FragmentProjectBinding binding;
    ArrayList<ProjectModel> projectList;
    String profileEmail;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProjectBinding.inflate(inflater, container, false);

        requireActivity().setTitle("Project");

        recyclerView = binding.RvProject;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        projectList = new ArrayList<>();

        loadProjectData();
        binding.addProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent=new Intent(getActivity(), Upload_Project_Activity.class);
              startActivity(intent);
            }
        });

        return binding.getRoot();

    }

    private void loadProjectData() {
        SessionManagement sessionManagement = new SessionManagement(getContext());
        profileEmail = sessionManagement.getSessionEmail();

        Call<ArrayList<ProjectModel>> call = Controller.getInstance()
                .getApiInterface()
                .getProject(profileEmail);


        call.enqueue(new Callback<ArrayList<ProjectModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProjectModel>> call, Response<ArrayList<ProjectModel>> response) {
                projectList = response.body();
                Collections.reverse(projectList);
                for (int i = 0; i < projectList.size(); i++) {
                    ProjectAdapter projectAdapter = new ProjectAdapter(getContext(),projectList);
                    binding.RvProject.setAdapter(projectAdapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProjectModel>> call, Throwable t) {

            }
        });
    }
}