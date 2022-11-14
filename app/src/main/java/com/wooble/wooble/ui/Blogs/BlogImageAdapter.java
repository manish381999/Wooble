package com.wooble.wooble.ui.Blogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wooble.wooble.R;
import com.wooble.wooble.databinding.BlogImageItemLayoutBinding;

import java.util.List;

public class BlogImageAdapter extends RecyclerView.Adapter<BlogImageAdapter.BlogImageViewHolder>{

    Context context;
    List list;


    @NonNull
    @Override
    public BlogImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.blog_image_item_layout,parent,false);
        return new BlogImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogImageViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class BlogImageViewHolder extends RecyclerView.ViewHolder{
BlogImageItemLayoutBinding binding;
        public BlogImageViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=BlogImageItemLayoutBinding.bind(itemView);
        }
    }
}
