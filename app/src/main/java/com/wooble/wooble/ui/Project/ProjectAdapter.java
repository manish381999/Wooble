package com.wooble.wooble.ui.Project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wooble.wooble.R;
import com.wooble.wooble.databinding.ProjectItemLayoutBinding;
import com.wooble.wooble.ui.Resume.ResumeModel;

import java.util.ArrayList;
import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>{
    Context context;
    List<ProjectModel> ProjectList;

    public ProjectAdapter(Context context, List<ProjectModel> projectList) {
        this.context = context;
        ProjectList = projectList;
    }


    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.project_item_layout,parent,false );
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        ProjectModel projectModel = ProjectList.get(position);
        holder.binding.tvProjectName.setText(projectModel.getProject_name());
        holder.binding.tvProjectAim.setText(projectModel.getAim_of_project());
        holder.binding.tvProjectDescription.setText(projectModel.getDescription());

    }

    @Override
    public int getItemCount() {
        return ProjectList.size();
    }

    public static class ProjectViewHolder extends RecyclerView.ViewHolder{

        ProjectItemLayoutBinding binding;
        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ProjectItemLayoutBinding.bind(itemView);
        }
    }
}
