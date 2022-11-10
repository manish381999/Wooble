package com.wooble.wooble.ui.Blogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wooble.wooble.R;
import com.wooble.wooble.databinding.BlogItemLayoutBinding;

import java.util.List;


public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.BlogViewHolder>{

    Context context;
    List list;

    public BlogAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BlogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view= LayoutInflater.from(context).inflate(R.layout.blog_item_layout, parent, false);
        return new BlogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder{

        BlogItemLayoutBinding binding;
        public BlogViewHolder(@NonNull View itemView) {
            super(itemView);

            binding=BlogItemLayoutBinding.bind(itemView);
        }
    }
}
