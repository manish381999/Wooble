package com.wooble.wooble.ui.Blogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wooble.wooble.R;
import com.wooble.wooble.databinding.BlogImageItemLayoutBinding;
import com.wooble.wooble.ui.Project.ProjectModel;

import java.util.List;

public class BlogImageAdapter extends RecyclerView.Adapter<BlogImageAdapter.BlogImageViewHolder> {
Context context;
    List<BlogImageModel> blogImageModelList;

    public BlogImageAdapter(Context context, List<BlogImageModel> blogImageModelList) {
        this.context = context;
        this.blogImageModelList = blogImageModelList;
    }


    @NonNull
    @Override
    public BlogImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.blog_image_item_layout,parent,false);
        return new BlogImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogImageViewHolder holder, int position) {

        BlogImageModel blogImageModel = blogImageModelList.get(position);
        Glide.with(context)
                .load(blogImageModel.getImage())
                .placeholder(R.drawable.place_holder)
                .into(holder.binding.blogImage);


    }

    @Override
    public int getItemCount() {
        return blogImageModelList.size();
    }

    public static class BlogImageViewHolder extends RecyclerView.ViewHolder{
  BlogImageItemLayoutBinding binding;
        public BlogImageViewHolder(@NonNull View itemView) {
            super(itemView);

            binding=BlogImageItemLayoutBinding.bind(itemView);
        }
    }
}
