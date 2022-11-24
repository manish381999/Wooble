package com.wooble.wooble.ui.Blogs;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wooble.wooble.R;
import com.wooble.wooble.databinding.BlogImageItemLayoutBinding;
import com.wooble.wooble.ui.Gallery.GalleryAdapter;

import java.util.List;

public class BlogImageAdapter extends RecyclerView.Adapter<BlogImageAdapter.BlogImageViewHolder>{

    Context context;
    List<BlogImageModel> BlogImageList;

    public BlogImageAdapter(Context context, List<BlogImageModel> blogImageList) {
        this.context = context;
        BlogImageList = blogImageList;
    }

    @NonNull
    @Override
    public BlogImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.blog_image_item_layout,parent,false);
        return new BlogImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogImageViewHolder holder, int position) {

        BlogImageModel blogImageModel = BlogImageList.get(position);
        Glide.with(context)
                .load(blogImageModel.getImage_url())
                .placeholder(R.drawable.place_holder)
                .into(holder.binding.blogImage);

        System.out.println("hello "+ blogImageModel.getImage_url());

    }

    @Override
    public int getItemCount() {
        return BlogImageList.size();
    }

    public static class BlogImageViewHolder extends RecyclerView.ViewHolder{
        BlogImageItemLayoutBinding binding;

        public BlogImageViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=BlogImageItemLayoutBinding.bind(itemView);
        }
    }
}
