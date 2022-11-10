package com.wooble.wooble.ui.Resume;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wooble.wooble.R;
import com.wooble.wooble.databinding.ResumeItemLayoutBinding;

import java.util.List;

public class ResumeAdapter extends RecyclerView.Adapter<ResumeAdapter.ResumeViewHolder> {
Context context;
List list;

    public ResumeAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ResumeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.resume_item_layout,parent,false);
        return new ResumeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResumeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ResumeViewHolder extends RecyclerView.ViewHolder{

        ResumeItemLayoutBinding binding;
        public ResumeViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ResumeItemLayoutBinding.bind(itemView);
        }
    }
}
