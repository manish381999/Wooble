package com.wooble.wooble.ui.Project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wooble.wooble.R;
import com.wooble.wooble.databinding.ProjectItemLayoutBinding;

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
        holder.binding.tvProjectName.setText(projectModel.getWork_title());
        holder.binding.tvProjectAim.setText(projectModel.getAim_of_work());
        holder.binding.tvProjectDescription.setText(projectModel.getWork_description());

        holder.binding.ivEdit.setOnClickListener(v -> {
            Intent intent=new Intent(context, Edit_Project_Activity.class);
            intent.putExtra("file_id",projectModel.getEntry_id());
            intent.putExtra("email_id",projectModel.getEmail_id());
            intent.putExtra("project_name",projectModel.getWork_title());
            intent.putExtra("aim_of_project",projectModel.getAim_of_work());
            intent.putExtra("description",projectModel.getWork_description());
            intent.putExtra("image_1",projectModel.getImage_1());
            intent.putExtra("image_2",projectModel.getImage_2());
            intent.putExtra("image_3",projectModel.getImage_3());
            intent.putExtra("image_4",projectModel.getImage_4());
            intent.putExtra("image_5",projectModel.getImage_5());
            intent.putExtra("image_6",projectModel.getImage_6());
            intent.putExtra("video",projectModel.getVideo());
            intent.putExtra("pdf_file",projectModel.getPdf_file());
            intent.putExtra("conclusion",projectModel.getConclusion());
            context.startActivity(intent);
        });

        holder.binding.readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Project_Viewer_Activity.class);
                intent.putExtra("file_id",projectModel.getEntry_id());
                intent.putExtra("email_id",projectModel.getEmail_id());
                intent.putExtra("project_name",projectModel.getWork_title());
                intent.putExtra("aim_of_project",projectModel.getAim_of_work());
                intent.putExtra("description",projectModel.getWork_description());
                intent.putExtra("image_1",projectModel.getImage_1());
                intent.putExtra("image_2",projectModel.getImage_2());
                intent.putExtra("image_3",projectModel.getImage_3());
                intent.putExtra("image_4",projectModel.getImage_4());
                intent.putExtra("image_5",projectModel.getImage_5());
                intent.putExtra("image_6",projectModel.getImage_6());
                intent.putExtra("video",projectModel.getVideo());
                intent.putExtra("pdf_file",projectModel.getPdf_file());
                intent.putExtra("conclusion",projectModel.getConclusion());
                context.startActivity(intent);
            }
        });


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
